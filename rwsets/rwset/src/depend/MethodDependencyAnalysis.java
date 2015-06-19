package depend;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Queue;
import java.util.Set;

import com.ibm.wala.analysis.pointers.HeapGraph;
import com.ibm.wala.cfg.ControlFlowGraph;
import com.ibm.wala.classLoader.CallSiteReference;
import com.ibm.wala.classLoader.IBytecodeMethod;
import com.ibm.wala.classLoader.IClass;
import com.ibm.wala.classLoader.IField;
import com.ibm.wala.classLoader.IMethod;
import com.ibm.wala.core.tests.callGraph.CallGraphTestUtil;
import com.ibm.wala.examples.drivers.PDFTypeHierarchy;
import com.ibm.wala.ipa.callgraph.AnalysisCache;
import com.ibm.wala.ipa.callgraph.AnalysisOptions;
import com.ibm.wala.ipa.callgraph.AnalysisScope;
import com.ibm.wala.ipa.callgraph.CGNode;
import com.ibm.wala.ipa.callgraph.CallGraph;
import com.ibm.wala.ipa.callgraph.CallGraphBuilderCancelException;
import com.ibm.wala.ipa.callgraph.impl.Everywhere;
import com.ibm.wala.ipa.callgraph.propagation.InstanceFieldKey;
import com.ibm.wala.ipa.callgraph.propagation.InstanceKey;
import com.ibm.wala.ipa.callgraph.propagation.LocalPointerKey;
import com.ibm.wala.ipa.callgraph.propagation.PointerAnalysis;
import com.ibm.wala.ipa.callgraph.propagation.PointerKey;
import com.ibm.wala.ipa.cfg.AbstractInterproceduralCFG;
import com.ibm.wala.ipa.cfg.InterproceduralCFG;
import com.ibm.wala.ipa.cha.ClassHierarchy;
import com.ibm.wala.ipa.cha.ClassHierarchyException;
import com.ibm.wala.shrikeCT.InvalidClassFileException;
import com.ibm.wala.ssa.DefUse;
import com.ibm.wala.ssa.IR;
import com.ibm.wala.ssa.ISSABasicBlock;
import com.ibm.wala.ssa.SSAArrayLoadInstruction;
import com.ibm.wala.ssa.SSAArrayReferenceInstruction;
import com.ibm.wala.ssa.SSAArrayStoreInstruction;
import com.ibm.wala.ssa.SSACFG;
import com.ibm.wala.ssa.SSAFieldAccessInstruction;
import com.ibm.wala.ssa.SSAGetInstruction;
import com.ibm.wala.ssa.SSAInstruction;
import com.ibm.wala.ssa.SSAInvokeInstruction;
import com.ibm.wala.ssa.SSAPutInstruction;
import com.ibm.wala.types.FieldReference;
import com.ibm.wala.util.CancelException;
import com.ibm.wala.util.WalaException;
import com.ibm.wala.util.collections.ReverseIterator;
import com.ibm.wala.util.config.AnalysisScopeReader;
import com.ibm.wala.util.graph.Graph;
import com.ibm.wala.util.graph.traverse.Topological;
import com.ibm.wala.util.io.FileProvider;
import com.ibm.wala.util.warnings.Warnings;
import com.ibm.wala.viz.DotUtil;

import depend.util.CallGraphGenerator;
import depend.util.Timer;
import depend.util.Util;
import depend.util.graph.Edge;
import depend.util.graph.SimpleGraph;

/**
 * Method dependency analysis based on:
 * 
 * - Read-Write (RW) sets to object fields - propagation of accesses through
 * private method calls
 * 
 * @author damorim
 * 
 */
public class MethodDependencyAnalysis {

  /**
   * constants
   */
  // this is expensive: it demands the creation of the
  // application call graph
  private static final boolean PROPAGATE_CALLS = true;

  /**
   * instance fields
   */
  AnalysisScope scope;
  private ClassHierarchy cha;
  AnalysisOptions options;
  // several caches
  static AnalysisCache cache;
  // application(instance)-specific caches
  private Map<IMethod, RWSet> rwSets = new HashMap<IMethod, RWSet>();

  /**
   * Callgraph generator for this analysis
   */
  private CallGraphGenerator cgGenerator;

  Timer timer = new Timer();
  Properties p;
  /**
   * parse input and call init
   * 
   * @param args
   * @throws IOException
   * @throws IllegalArgumentException
   * @throws WalaException
   * @throws CancelException
   */
  public MethodDependencyAnalysis(Properties p) throws IOException,
      IllegalArgumentException, WalaException, CancelException {
    
    this.p = p;  
    
    setup();
    
  }

  /**
   * clear all internal data
   * 
   * @throws Exception
   */
  public void reset() throws Exception {
    scope = null;
    cha = null;
    options = null;
    cache = null;
    rwSets = new HashMap<IMethod, RWSet>();
  }

  /**
   * all the work is done here!
   * 
   * in several steps
   * 
   * @param appJar
   *          path to the .jar file with all .class application class files
   * @param exclusionFile
   *          a WALA specification indicating what class files to ignore
   * 
   * @throws IOException
   * @throws ClassHierarchyException
   * @throws WalaException
   * @throws CancelException
   */
  public void run() throws IOException, ClassHierarchyException,
      WalaException, CancelException {
    
    boolean debugTime = Boolean.parseBoolean(p.getProperty("measureTime"));

    // Extracting direct read-write sets for all methods
    // and classes within the analysis scope
    directRWSets();
    if (debugTime) {
      timer.stop();
      timer.show("building direct RW Sets");
    }

    if (PROPAGATE_CALLS) {

      // building the call graph
      CallGraphGenerator cgg = this.getCallGraphGenerator();
      Graph<CGNode> graph = cgg.getPrunedCallGraph();
      if (debugTime) {
        timer.stop();
        timer.show("done building call graph");
      }

      if (Util.getBooleanProperty("genCallGraph")) {
        System.out.println("=== (dot)");
        DotUtil.dotify(graph, null, PDFTypeHierarchy.DOT_FILE, "/tmp/cg.pdf",
            Util.getStringProperty("dotPath"));
        System.out.println("===");
      }
      
      //simple example of how to use pointer analysis. 
      // searchAliasedPointers(cgg);
        
      // propagate RWSet from callees (only private methods) to callers
      propagateRWSets(graph);
      if (debugTime) {
        timer.stop();
        timer.show("done propagating RW sets");
      }

    }

    if (debugTime) {
      System.out.println(">> timing");
    }
    
    if (Util.getBooleanProperty("printWalaWarnings")) {
      System.out.println(Warnings.asString());
    }

  }

  //print possible alias for pointers. 
  //more info at: http://wala.sourceforge.net/wiki/index.php/UserGuide:PointerAnalysis#Heap_Graph
  private void searchAliasedPointers(CallGraphGenerator cgg)
      throws CallGraphBuilderCancelException {
    PointerAnalysis pa = cgg.getPointerAnalysis();
    Iterator<PointerKey> it = pa.getHeapModel().iteratePointerKeys();
    HeapGraph hgraph = pa.getHeapGraph();
    while (it.hasNext()) {
 
      PointerKey pkey = it.next();
      if (pkey instanceof InstanceFieldKey) { 
        //pointer to a field associated with a set of instances.
        //Note that a single field can have multiple PointerKeys.
        InstanceFieldKey ifk = (InstanceFieldKey) pkey;
        
        //filter relevant fields
        if (ifk.getField().toString().contains(Util.APP_PREFIX)) {
          IField ifield = ifk.getField();
          System.out.println("Analyzing the field/site: "
              + ifield + "--" + ifk.getInstanceKey());
        } else {
          continue;
        }
      } else if (pkey instanceof LocalPointerKey) {
       //pointer to a local
        LocalPointerKey lpk = (LocalPointerKey) pkey;
        IMethod lpkMethod = lpk.getNode().getMethod();
        
        //filter relevant fields
        //More info: http://wala.sourceforge.net/wiki/index.php/UserGuide:IR#Value_Numbering
        if (lpkMethod.toString().contains(Util.APP_PREFIX)) {
          IR ir = cache.getIRFactory().makeIR(lpkMethod, Everywhere.EVERYWHERE,
              options.getSSAOptions());
           
          //Looks like all this crap wasn't needed to get the names of the variables.
          //I'm leaving it for now because there is some useful code and info
          //that may be of interest in the future.
//          DefUse df = cache.getSSACache().findOrCreateDU(ir, Everywhere.EVERYWHERE);
//          SSAInstruction defInstruction = df.getDef(lpk.getValueNumber());
//          
//          if (defInstruction == null) { //it's a parameter or a constant
//            continue;
//          } else {
//            int index = -1;
//            SSAInstruction[] allInstructions = ir.getInstructions();
//            for (int i = 0; i < allInstructions.length; i++) {
//              //TODO SSAInstruction "equals" method use reference comparison, and we create multiple IR objects.
//              //We should keep an canonical version of every IR, or update to the latest version.
//              //This issue was fixed recently (see https://github.com/wala/WALA/pull/59).
//              SSAInstruction cur = allInstructions[i];
//              if (cur != null && defInstruction.toString().equals(cur.toString())) {
//                index = i;
//                break;
//              }
//            }
//            String[] names = ir.getLocalNames(index, lpk.getValueNumber());
            String[] names = ir.getLocalNames(ir.getInstructions().length - 1, lpk.getValueNumber());
            System.out.println("Analyzing local variable " + Arrays.toString(names) + " in method " + lpkMethod);
        } else {
          continue;
        }
      } else {
        continue;
      }
      
      Iterator<Object> pointedInstances = hgraph.getSuccNodes(pkey);
      while (pointedInstances.hasNext()) {
        InstanceKey ikey = (InstanceKey) pointedInstances.next();
        Iterator<Object> possibleAlias = hgraph.getPredNodes(ikey);

        while (possibleAlias.hasNext()) {
          PointerKey aliasPKey = (PointerKey) possibleAlias.next();
          if (!aliasPKey.equals(pkey)) {
            if (aliasPKey instanceof InstanceFieldKey) {
              InstanceFieldKey aliasIFK = (InstanceFieldKey) aliasPKey;
              IField aliasIField = aliasIFK.getField();
              System.out.println(" > possible alias: field " + aliasIField);
            
            } else if (aliasPKey instanceof LocalPointerKey) {
              LocalPointerKey aliasLPK = (LocalPointerKey) aliasPKey;
              IMethod lpkMethod = aliasLPK.getNode().getMethod();
              IR ir = cache.getIRFactory().makeIR(lpkMethod, Everywhere.EVERYWHERE,
                  options.getSSAOptions());
              String[] names = ir.getLocalNames(ir.getInstructions().length - 1, aliasLPK.getValueNumber());
              System.out.println(" > possible alias: local " + Arrays.toString(names) + " in method " + lpkMethod);
            } else {
              System.out.println(" > unhandled pointer type: " + aliasPKey.getClass());
            }
          }
        }
      }
    }
  }

  private void setup() throws IOException,
      ClassHierarchyException {
    String appJar = (String) p.get("appJar");
    if (appJar == null) {
      throw new UnsupportedOperationException(
          "expected command-line to include -appJar");
    }
    
    String exclusionFile = p.getProperty("exclusionFile", CallGraphTestUtil.REGRESSION_EXCLUSIONS);
    Warnings.clear();
    
    File tmp;
    if (exclusionFile != null) {
      tmp = new FileProvider().getFile(exclusionFile); 
    } else {
      tmp = new File(CallGraphTestUtil.REGRESSION_EXCLUSIONS);
    }

    boolean debugTime = Boolean.parseBoolean(p.getProperty("measureTime"));

    if (debugTime) {
      timer.start();
    }
    // very important: define scope of analysis!
    // + relevant: what is in appJar and Java libraries
    // - not relevant: what is in exclusionFile
    
    scope = AnalysisScopeReader.makeJavaBinaryAnalysisScope(appJar, tmp);
    if (debugTime) {
      timer.stop();
      System.out.println("<< timing");
      timer.show("done building scope");
    }

    // Class Hierarchy Analysis (CHA)
    cha = ClassHierarchy.make(scope);
    options = new AnalysisOptions(scope, null);
    cache = new AnalysisCache();
    if (debugTime) {
      timer.stop();
      timer.show("done building CHA");
    }
  }

  /**
   * This method navigates through each and every method in the scope of
   * analysis (see scope) and builds a RWSet object for that method. It uses CHA
   * to perform the navigation.
   */
  private void directRWSets() {
    // finds rw-sets for all application methods
    List<IClass> toVisit = new ArrayList<IClass>();
    IClass root = getCHA().getRootClass();
    toVisit.add(root);
    Set<IMethod> visited = new HashSet<IMethod>();
    while (!toVisit.isEmpty()) {
      IClass cur = toVisit.remove(0);
      // want to visit all classes regardless it is application
      // as not application classes can be parent of application
      // classes.
      toVisit.addAll(getCHA().getImmediateSubclasses(cur));
      if (Util.isAppClass(cur)) {
        for (IMethod imethod : cur.getAllMethods()) {
          if (imethod.isNative() || imethod.isAbstract()
              || visited.contains(imethod)) {
            continue;
          }
          visited.add(imethod);
          updateRWSet(imethod);
        }
      }
    }
  }

  /***
   * This method builds a RWSet for a given method. It does **not** consider
   * indirect field reads and writes through other (say, private) methods
   **/
  private void updateRWSet(IMethod imethod) {
    // cache results for this call
    RWSet result = rwSets.get(imethod);
    if (result != null) {
      return;
    }

    IR ir = cache.getIRFactory().makeIR(imethod, Everywhere.EVERYWHERE,
        options.getSSAOptions());

    Set<AccessInfo> readSet = new HashSet<AccessInfo>();
    Set<AccessInfo> writeSet = new HashSet<AccessInfo>();
    Map<Integer, FieldReference> ssaVar = new HashMap<Integer, FieldReference>();

    SSAInstruction[] instructions = ir.getInstructions();
    for (int i = 0; i < instructions.length; i++) {
      SSAInstruction ins = instructions[i];
      if (ins == null)
        continue;
      int kind = -1;
      if (ins instanceof SSAGetInstruction) {
        kind = 0;
      } else if (ins instanceof SSAPutInstruction) {
        kind = 1;
      } else if (ins instanceof SSAArrayLoadInstruction) {
        kind = 2;
      } else if (ins instanceof SSAArrayStoreInstruction) {
        kind = 3;
      }
      switch (kind) {
      case -1:
        break;
      case 0:
      case 1:
        SSAFieldAccessInstruction fai = (SSAFieldAccessInstruction) ins;
        // A FieldReference object denotes an access (read or write) to a field
        FieldReference fr = fai.getDeclaredField();
        Set<AccessInfo> set = (kind == 0) ? readSet : writeSet;
        IBytecodeMethod method = (IBytecodeMethod) ir.getMethod();
        try {
          int sourceLineNum = method.getLineNumber(method.getBytecodeIndex(i));
          
          // access field
          IClass iclass = getCHA().lookupClass(fr.getDeclaringClass());
          IField ifield = iclass.getField(fr.getName());
          
          AccessInfo accessInfo = RWSet.makeAccessInfo(method, sourceLineNum, ifield);
          set.add(accessInfo);
        } catch (InvalidClassFileException e) {
          e.printStackTrace();
        }
        // remembering ssa-definition if one exists
        int def = fai.getDef();
        if (def != -1) {
          ssaVar.put(def, fr);
        }
        break;
      case 2:
      case 3:
        SSAArrayReferenceInstruction ari = (SSAArrayReferenceInstruction) ins;
        int arRef = ari.getArrayRef();
        fr = ssaVar.get(arRef);
        if (fr != null) {
          set = (kind == 2) ? readSet : writeSet;
          IBytecodeMethod method1 = (IBytecodeMethod) ir.getMethod();
          try {
            int sourceLineNum = method1.getLineNumber(method1
                .getBytecodeIndex(i));
            
            // access field
            IClass iclass = getCHA().lookupClass(fr.getDeclaringClass());
            IField ifield = iclass.getField(fr.getName());
            
            AccessInfo accessInfo = RWSet.makeAccessInfo(method1, sourceLineNum, ifield);
            set.add(accessInfo);
          } catch (InvalidClassFileException e) {
            e.printStackTrace();
          }
        }
        break;
      default:
        throw new RuntimeException("UNEXPECTED");
      }
    }

    result = new RWSet(readSet, writeSet);
    rwSets.put(imethod, result);
  }

  /***
   * This method propagates information of RWSet from callees to callers. It
   * needs call-graph information to identify the callees of a method.
   * 
   * This propagation is important since otherwise the impact of the call of the
   * private method may not be observable.
   * 
   * @param appJar
   * @param exclusionFile
   * @throws IllegalArgumentException
   * @throws WalaException
   * @throws CancelException
   * @throws IOException
   **/
  private void propagateRWSets(Graph<CGNode> callGraph)
      throws IllegalArgumentException, WalaException, CancelException,
      IOException {
    /**
     * Propagate information across the edges of the control-flow graph. For
     * that, we use the reverse topological order so to reduce the number of
     * iterations we need to process for computing fix point.
     */
    Iterator<CGNode> it = ReverseIterator.reverse(Topological
        .makeTopologicalIter(callGraph));
    while (it.hasNext()) {
      CGNode node = it.next();
      IMethod cMethod = node.getMethod();
      if (!Util.isRelevantMethod(cMethod)) {
        continue;
      }
      RWSet rwSetC = null;
      Iterator<CGNode> it2 = callGraph.getPredNodes(node);
      while (it2.hasNext()) {
        CGNode p = it2.next();
        IMethod pMethod = p.getMethod();
        if (!Util.isRelevantMethod(pMethod)) {
          continue;
        }
        RWSet rwSetP = rwSets.get(pMethod);
        if (rwSetP == null) {
          Util.logWarning("no RW-set info for method " + pMethod.toString());
          continue;
        }
        if (rwSetC == null) {
          rwSetC = rwSets.get(cMethod);
          if (rwSetC == null) {
            Util.logWarning("no RW-set info for method " + cMethod);
            continue;
          }
        }
        // propagate!
        rwSetP.writeSet.addAll(rwSetC.writeSet);
        rwSetP.readSet.addAll(rwSetC.readSet);
      }
    }
  }

  /**
   * Returns the dependencies graph for method <code>method</code> and possibly filtering the
   * result by choosing a target line <code>sourceLine</code>.
   * If <code>isWriterMethod</code> is <code>true</code> this method will return a graph for the
   * dependents of <code>method</code>, else the graph will contain mostly the methods to which <code>method</code>
   * depends.
   * @param method
   * @param sourceLine
   * @param forwardDependencies
   * @param withIndirects 
   * @return
   * @throws CallGraphBuilderCancelException
   * @throws ClassHierarchyException
   * @throws IOException
   */
  public SimpleGraph getDependenciesGraph(IMethod method, int sourceLine,
                                           boolean forwardDependencies, boolean withIndirects)
          throws CallGraphBuilderCancelException, ClassHierarchyException, IOException {
    if (method == null) {
      throw new RuntimeException("Could not find informed method!");
    }
    SimpleGraph dependencyGraph = new SimpleGraph();

    if(!forwardDependencies){
      Set<AccessInfo> reads = rwSets.get(method).readSet;
      Set<CallSiteReference> callSites = null;
      CallGraph cg = this.getCallGraphGenerator().getFullCallGraph();
      if (sourceLine >= 0) {
        reads = new HashSet<AccessInfo>();
        callSites = new HashSet<CallSiteReference>();
        this.findFlowingInfo(method, sourceLine, cg, reads, callSites);
      }
      for (AccessInfo access : reads) {
        this.fillGraph(method, dependencyGraph, false, false, access, true);
      }

      if(withIndirects){
        this.fillGraphWithIndirects(dependencyGraph, method, callSites, cg,
            new HashSet<IMethod>(), true);
      }
    } else {
      Set<AccessInfo> writes = rwSets.get(method).writeSet;
      Set<CallSiteReference> callSites = null;
      CallGraph cg = this.getCallGraphGenerator().getFullCallGraph();
      if (sourceLine >= 0) {
        writes = new HashSet<AccessInfo>();
        callSites = new HashSet<CallSiteReference>();
        this.findEscapingInfo(method, sourceLine, cg, writes, callSites);
      }
      for (AccessInfo access : writes) {
        this.fillGraph(method, dependencyGraph, false, false, access, false);
      }

      if(withIndirects){
        this.fillGraphWithIndirects(dependencyGraph, method, callSites, cg,
            new HashSet<IMethod>(), false);
      }
    }
    dependencyGraph.setTargetMethod(method);
    return dependencyGraph;
  }

  private void fillGraphWithIndirects(SimpleGraph graph, IMethod method,
                                      Set<CallSiteReference> callSites,
                                      CallGraph cg, Set<IMethod> visited, boolean isReadAccess){
    if(!visited.contains(method)){
      visited.add(method);
      Set<CGNode> nodes = cg.getNodes(method.getReference());
      for (CGNode cgNode : nodes) {
        if(callSites != null){
          for (CallSiteReference callSiteReference : callSites) {
            Set<CGNode> possibleTargets = cg.getPossibleTargets(cgNode, callSiteReference) ;
            addIndirectEdges(graph, isReadAccess, possibleTargets, cgNode.getMethod());
          }
        } else {
          Set<CGNode> allPossibleTargets = toSet(cg.getSuccNodes(cgNode));
          addIndirectEdges(graph, isReadAccess, allPossibleTargets, cgNode.getMethod());
        }
        Iterator<CGNode> succNodes = cg.getSuccNodes(cgNode);
        while (succNodes.hasNext()) {
          CGNode successorNode = (CGNode) succNodes.next();
          fillGraphWithIndirects(graph, successorNode.getMethod(), null, cg, visited, isReadAccess);
        }
      }
    }
  }

  private void addIndirectEdges(SimpleGraph graph, boolean isReadAccess,
      Set<CGNode> possibleTargets, IMethod callerMethod) {
    for (CGNode possibleTarget : possibleTargets) {
      RWSet rwSet = this.rwSets.get(possibleTarget.getMethod());
      if(rwSet != null){
        if(isReadAccess){
          Set<AccessInfo> readSet = rwSet.readSet;
          for (AccessInfo accessInfo : readSet) {
            graph.add(new Edge(accessInfo.accessMethod, accessInfo.accessLineNumber,
                                callerMethod, accessInfo.accessLineNumber,
                                accessInfo.iField, false));
          }
        } else {
          Set<AccessInfo> writeSet = rwSet.writeSet;
          for (AccessInfo accessInfo : writeSet) {
            graph.add(new Edge(callerMethod, accessInfo.accessLineNumber,
                                accessInfo.accessMethod, accessInfo.accessLineNumber,
                                accessInfo.iField, false));
          }
        }
      }
    }
  }

  private void fillGraph(IMethod method, SimpleGraph result, boolean onlyPublicClasses,
                          boolean onlyPublicMethods, AccessInfo accessInfo,
                          boolean isReadAccess) {
    for (Map.Entry<IMethod, RWSet> entry : rwSets.entrySet()) {
      IMethod accessor = entry.getKey();
      if (onlyPublicClasses && !accessor.getDeclaringClass().isPublic()) {
        continue;
      }
      if (onlyPublicMethods && !accessor.isPublic()) {
        continue;
      }
      
      Set<AccessInfo> accessSet = isReadAccess ? entry.getValue().writeSet : entry.getValue().readSet;
      for (AccessInfo readOrWriteAccessInfo : accessSet) {
        IMethod writer;
        int writeLineNumber;
        IMethod reader;
        int readLineNumber;
        if (readOrWriteAccessInfo.iField.equals(accessInfo.iField)) {
          if(isReadAccess){
            writer = readOrWriteAccessInfo.accessMethod;
            writeLineNumber = readOrWriteAccessInfo.accessLineNumber;
            reader = accessInfo.accessMethod;
            readLineNumber = accessInfo.accessLineNumber;
          } else {
            writer = accessInfo.accessMethod;
            writeLineNumber = accessInfo.accessLineNumber;
            reader = readOrWriteAccessInfo.accessMethod;
            readLineNumber = readOrWriteAccessInfo.accessLineNumber;
          }
          Edge edge = new Edge(writer, writeLineNumber, reader, readLineNumber,
                                readOrWriteAccessInfo.iField);
          result.add(edge);
        }
      }
    }
  }

  public ClassHierarchy getCHA() {
    return cha;
  }

  public CallGraphGenerator getCallGraphGenerator() throws ClassHierarchyException, IOException{
    if(this.cgGenerator == null){
      this.cgGenerator = new CallGraphGenerator(this.scope, this.getCHA());
    }
    return this.cgGenerator;
  }

  /**
   * Finds the read set that flows to line <code>sourceLine</code> in method <code>method</code>.
   * @param method target method that contains the source line to find the flowing read set
   * @param sourceLine
   * @param cg the callgraph used to build the control flow graph
   * @return
   */
  private Set<AccessInfo> findFlowingInfo(IMethod method, int sourceLine, CallGraph cg,
                                              Set<AccessInfo> reads,
                                              Set<CallSiteReference> callSites){
    AbstractInterproceduralCFG<ISSABasicBlock> interproceduralCFG = new InterproceduralCFG(cg);

    List<ISSABasicBlock> initialBlocks = this.getBasicBlocksForSourceLine(method, sourceLine);

    /* Accounting for all possible CFGs of a given method.
     * The loop below is just a generalization in case we want to use a more precise callgraph
     * in the future.
     * Right now it should iterate only once as we are using a simple 0-CFA callgraph building algorithm
     * and as such there should be only one CGNode for each method.
     */
    Set<CGNode> cgNodes = cg.getNodes(method.getReference());
    if(cgNodes.size() > 0){
      for (CGNode cgNode : cgNodes) { 
        ControlFlowGraph<SSAInstruction, ISSABasicBlock> cfg = interproceduralCFG.getCFG(cgNode);
        doFlowingInfoAnalysis(cg, reads, callSites, initialBlocks, cgNode.getMethod(), cgNode, cfg);
      }
    } else {
      SSACFG cfg = cache.getIR(method).getControlFlowGraph();
      doFlowingInfoAnalysis(cg, reads, callSites, initialBlocks, method, null, cfg);
    }
    return reads;
  }

  private void doFlowingInfoAnalysis(CallGraph cg, Set<AccessInfo> reads,
                                      Set<CallSiteReference> callSites,
                                      List<ISSABasicBlock> initialBlocks,
                                      IMethod method, CGNode cgNode,
                                      ControlFlowGraph<SSAInstruction,
                                      ISSABasicBlock> cfg) {
    Queue<ISSABasicBlock> blocksWorkList = new LinkedList<ISSABasicBlock>(initialBlocks);
    Set<ISSABasicBlock> visitedBlocks = new HashSet<ISSABasicBlock>();
    while(!blocksWorkList.isEmpty()){
      ISSABasicBlock block = blocksWorkList.poll();
      visitFlowingBlock(block, method, cgNode, cg, reads, callSites);
      visitedBlocks.add(block);
      addToWorkList(cfg.getPredNodes(block), blocksWorkList, visitedBlocks);
    }
  }

  private void visitFlowingBlock(ISSABasicBlock block, IMethod method, CGNode methodNode, CallGraph cg,
      Set<AccessInfo> reads, Set<CallSiteReference> callSites) {
    RWSet methodRWSet = rwSets.get(method);
    Set<AccessInfo> readSet = methodRWSet.readSet;
    
    Set<IMethod> methods = this.rwSets.keySet();
    int instructionIndex = block.getFirstInstructionIndex();
    Iterator<SSAInstruction> blockIterator = block.iterator();
    while (blockIterator.hasNext()) {
      SSAInstruction ssaInstruction = (SSAInstruction) blockIterator.next();
      instructionIndex++;
      if(ssaInstruction == null){
        continue;
      }
      if(ssaInstruction instanceof SSAInvokeInstruction){
        SSAInvokeInstruction invokeInstruction = (SSAInvokeInstruction) ssaInstruction;
        if(methodNode != null){
          for (CGNode possibleTarget : cg.getPossibleTargets(methodNode, invokeInstruction.getCallSite())) {
            if (methods.contains(possibleTarget.getMethod())) {
              reads.addAll(rwSets.get(possibleTarget.getMethod()).readSet);
            }
          }
        }
        callSites.add(invokeInstruction.getCallSite());
      } else if(ssaInstruction instanceof SSAGetInstruction){
        IField iField = this.getInstructionField((SSAFieldAccessInstruction) ssaInstruction);
        updateSet(reads, method, readSet, instructionIndex, iField);
      }
    }
  }

  /* ############################ ESCAPING INFO ANALYSIS ############################ */
  /*
   * TODO: The following three methods implement the escaping analysis, they are, however, almost
   * code clones of the previous three methods and it is *highly* recommended to find a away
   * to refactor them out of here.
   */
  
  private void findEscapingInfo(IMethod method, int sourceLine, CallGraph cg,
                                Set<AccessInfo> writes, Set<CallSiteReference> callSites){
    AbstractInterproceduralCFG<ISSABasicBlock> interproceduralCFG = new InterproceduralCFG(cg);

    List<ISSABasicBlock> initialBlocks = this.getBasicBlocksForSourceLine(method, sourceLine);

    Set<CGNode> cgNodes = cg.getNodes(method.getReference());
    if(cgNodes.size() > 0){
      for (CGNode cgNode : cgNodes) { 
        ControlFlowGraph<SSAInstruction, ISSABasicBlock> cfg = interproceduralCFG.getCFG(cgNode);
        doEscapingInfoAnalysis(cg, writes, callSites, initialBlocks, cgNode.getMethod(), cgNode, cfg);
      }
    } else {
      // Oddly our method has no corresponding node in the callgraph.
      ControlFlowGraph<SSAInstruction, ISSABasicBlock> cfg = cache.getIR(method).getControlFlowGraph();
      doEscapingInfoAnalysis(cg, writes, callSites, initialBlocks, method, null, cfg);
    }
  }

  private void doEscapingInfoAnalysis(CallGraph cg, Set<AccessInfo> writes,
                                      Set<CallSiteReference> callSites,
                                      List<ISSABasicBlock> initialBlocks,
                                      IMethod method, CGNode cgNode,
                                      ControlFlowGraph<SSAInstruction,
                                      ISSABasicBlock> cfg) {
    Queue<ISSABasicBlock> blocksWorkList = new LinkedList<ISSABasicBlock>(initialBlocks);
    Set<ISSABasicBlock> visitedBlocks = new HashSet<ISSABasicBlock>();
    while(!blocksWorkList.isEmpty()){
      ISSABasicBlock block = blocksWorkList.poll();
      visitEscapingBlock(block, method, cgNode, cg, writes, callSites);
      visitedBlocks.add(block);
      addToWorkList(cfg.getSuccNodes(block), blocksWorkList, visitedBlocks);
    }
  }

  private void visitEscapingBlock(ISSABasicBlock block, IMethod method, CGNode methodNode,
                                    CallGraph cg, Set<AccessInfo> writes,
                                    Set<CallSiteReference> callSites) {
    RWSet methodRWSet = rwSets.get(method);
    Set<AccessInfo> writeSet = methodRWSet.writeSet;

    Set<IMethod> methods = this.rwSets.keySet();
    int instructionIndex = block.getFirstInstructionIndex();
    Iterator<SSAInstruction> blockIterator = block.iterator();
    while (blockIterator.hasNext()) {
      SSAInstruction ssaInstruction = (SSAInstruction) blockIterator.next();
      instructionIndex++;
      if(ssaInstruction == null){
        continue;
      }
      if(ssaInstruction instanceof SSAInvokeInstruction){
        SSAInvokeInstruction invokeInstruction = (SSAInvokeInstruction) ssaInstruction;
        if(methodNode != null){
          for (CGNode possibleTarget : cg.getPossibleTargets(methodNode, invokeInstruction.getCallSite())) {
            if (methods.contains(possibleTarget.getMethod())) {
              writes.addAll(rwSets.get(possibleTarget.getMethod()).writeSet);
            }
          }
        }
        callSites.add(invokeInstruction.getCallSite());
      } else if(ssaInstruction instanceof SSAPutInstruction){
        IField iField = getInstructionField((SSAPutInstruction) ssaInstruction);
        updateSet(writes, method, writeSet, instructionIndex, iField);
      } else if(ssaInstruction instanceof SSAArrayStoreInstruction){
        SSAArrayStoreInstruction storeInstruction = (SSAArrayStoreInstruction) ssaInstruction;
        DefUse defUse = cache.getDefUse(cache.getIR(method));
        int numberOfUses = storeInstruction.getNumberOfUses();
        
         /*We are only following the definitions-uses through one level to detect array field reads that
         may be used solely to store elements in the array itself, i.e., in SSA form a field array
         store command such as "x.a[1] = 10;" is implemented using two instructions, one that defines
         a variable through a SSAGetInstruction and another that executes an SSAArrayStoreInstruction
         on the previously defined variable.*/
        for (int i = 0; i < numberOfUses; i++) {
          int use = storeInstruction.getUse(i);
          SSAInstruction def = defUse.getDef(use);
          if(def != null && def instanceof SSAGetInstruction){
            int currentNumberOfWrites = writes.size();
            updateSet(writes, method, writeSet, instructionIndex,
                       this.getInstructionField((SSAFieldAccessInstruction) def));
            if(writes.size() > currentNumberOfWrites){
              break;
            }
          }
        }
      }
    }
  }

  /* ############################ END OF ESCAPING INFO ANALYSIS ############################ */
  
  /**
   * Returns the IField for the {@link FieldReference} contained in instruction <code>fieldAccessInstruction</code>.
   * @param fieldAccessInstruction
   * @return
   */
  private IField getInstructionField(SSAFieldAccessInstruction fieldAccessInstruction) {
    FieldReference fieldReference = fieldAccessInstruction.getDeclaredField();
    IClass iClass = getCHA().lookupClass(fieldReference.getDeclaringClass());
    IField iField = iClass.getField(fieldReference.getName());
    return iField;
  }

  /**
   * Updates the {@link AccessInfo} set <code>accessInfoTarget</code> by adding AccessInfo objects from
   * <code>accessInfosSource</code> if they have the same source line number as the instruction in
   * index <code>instructionIndex</code> <b>and</b> if their <code>iField</code> attribute equals
   * the <code>iField</code> parameter.
   * @param accessInfoTarget
   * @param method
   * @param accessInfosSource
   * @param instructionIndex
   * @param iField
   */
  private void updateSet(Set<AccessInfo> accessInfoTarget, IMethod method,
      Set<AccessInfo> accessInfosSource, int instructionIndex, IField iField) {
    int sourceLine = getSourceLine((IBytecodeMethod) method, instructionIndex);
    for (AccessInfo accessInfo : accessInfosSource) {
      if(sourceLine == accessInfo.accessLineNumber && accessInfo.iField.equals(iField)){
       accessInfoTarget.add(accessInfo);
      }
    }
  }

  /**
   * Add all basic blocks of <code>nodes</code> to <code>blocksWorkList</code> that
   * haven't been visited before.
   * @param blocksWorkList queue of basic blocks to which the predecessors may be added
   * @param visitedBlocks list of basic blocks that have already been visited and that should not
   * be added to <code>blocksWorkList</code> again
   * @param nodes
   */
  private void addToWorkList(
      Iterator<ISSABasicBlock> nodes,
      Queue<ISSABasicBlock> blocksWorkList, Set<ISSABasicBlock> visitedBlocks) {
    while (nodes.hasNext()) {
      ISSABasicBlock issaBasicBlock = nodes.next();
      if(!visitedBlocks.contains(issaBasicBlock)){
        blocksWorkList.add(issaBasicBlock);
      }
    }
  }

  /**
   * Returns the SSA basic blocks that make up source line <code>sourceLine</code>.
   * @param method the method that contains the source line indicated by <code>sourceLine</code>,
   * it <b>must</b> also be an instance of {@link IBytecodeMethod}
   * @param sourceLine the source line to get the basic blocks for
   * @return a list containing the basic blocks that may compose <code>sourceLine</code>
   */
  private List<ISSABasicBlock> getBasicBlocksForSourceLine(IMethod method, int sourceLine){
    List<ISSABasicBlock> ssaBasicBlocks = new ArrayList<ISSABasicBlock>();
    IR ir = cache.getIRFactory().makeIR(method, Everywhere.EVERYWHERE, options.getSSAOptions());
    SSAInstruction[] instructions = ir.getInstructions();
    for (int i = 0; i < instructions.length; i++) {
      SSAInstruction ssaInstruction = instructions[i];
      if(ssaInstruction == null){
        continue;
      }
      if(getSourceLine((IBytecodeMethod) method, i) == sourceLine){
        ISSABasicBlock basicBlock = ir.getBasicBlockForInstruction(ssaInstruction);
        if(!ssaBasicBlocks.contains(basicBlock)){
          ssaBasicBlocks.add(basicBlock);
        }
      }
    }
    return ssaBasicBlocks;
  }

  /**
   * Returns the source line for the instruction at index <code>instructionIndex</code> in method
   * <code>bMethod</code>.
   * If unable to determine the source line number this method returns -1. 
   * @param bMethod method to determine the line number
   * @param instructionIndex
   * @return the source line number for instruction at index <code>instructionIndex</code> in method
   * <code>bMethod</code> or -1 if unable to determine the source line number.
   */
  private int getSourceLine(IBytecodeMethod bMethod, int instructionIndex){
    int sourceLineNum = -1;
    try {
      sourceLineNum = bMethod.getLineNumber(bMethod.getBytecodeIndex(instructionIndex));
    } catch (InvalidClassFileException e) {
      e.printStackTrace();
    }
    return sourceLineNum;
  }

  private <T> Set<T> toSet(Iterator<T> tIterator){
    Set<T> tSet = new HashSet<T>();
    while (tIterator.hasNext()) {
      T cgNode = tIterator.next();
      tSet.add(cgNode);
    }
    return tSet;
  }

}