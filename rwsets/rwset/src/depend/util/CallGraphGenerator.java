package depend.util;


import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

import com.ibm.wala.classLoader.IClass;
import com.ibm.wala.classLoader.IMethod;
import com.ibm.wala.ipa.callgraph.AnalysisCache;
import com.ibm.wala.ipa.callgraph.AnalysisOptions;
import com.ibm.wala.ipa.callgraph.AnalysisScope;
import com.ibm.wala.ipa.callgraph.CGNode;
import com.ibm.wala.ipa.callgraph.CallGraph;
import com.ibm.wala.ipa.callgraph.CallGraphBuilderCancelException;
import com.ibm.wala.ipa.callgraph.Entrypoint;
import com.ibm.wala.ipa.callgraph.impl.DefaultEntrypoint;
import com.ibm.wala.ipa.callgraph.propagation.PointerAnalysis;
import com.ibm.wala.ipa.cha.ClassHierarchy;
import com.ibm.wala.ipa.cha.ClassHierarchyException;
import com.ibm.wala.ipa.cha.IClassHierarchy;
import com.ibm.wala.types.ClassLoaderReference;
import com.ibm.wala.util.CancelException;
import com.ibm.wala.util.WalaException;
import com.ibm.wala.util.collections.HashSetFactory;
import com.ibm.wala.util.graph.Graph;
import com.ibm.wala.util.graph.GraphSlicer;

public class CallGraphGenerator {

  AnalysisScope scope;
  ClassHierarchy cha;

  private CallGraph cg;
  private PointerAnalysis pa;

  public CallGraphGenerator(AnalysisScope scope, ClassHierarchy cha) throws IOException, ClassHierarchyException {
    this.scope = scope;
    this.cha = cha;
  }

  public Graph<CGNode> getPrunedCallGraph() throws WalaException, IllegalArgumentException, CancelException, IOException {
    CallGraph cg = getFullCallGraph();
    
    return GraphSlicer.prune(cg, new com.ibm.wala.util.Predicate<CGNode>(){
      @Override
      public boolean test(CGNode node) {
        return Util.isRelevantMethod(node.getMethod()); 
      }
    });
    
  }

  public CallGraph getFullCallGraph() throws CallGraphBuilderCancelException {
    if(this.cg == null){
      Iterable<Entrypoint> entrypoints = entryPoints(scope.getApplicationLoader(), cha);
      AnalysisOptions options = new AnalysisOptions(scope, entrypoints);
      com.ibm.wala.ipa.callgraph.CallGraphBuilder builder = com.ibm.wala.ipa.callgraph.impl.Util.makeZeroOneContainerCFABuilder(options, new AnalysisCache(), cha, scope);
      this.cg = builder.makeCallGraph(options, null);
      this.pa = builder.getPointerAnalysis();
    }
    return cg;
  }
  
  public PointerAnalysis getPointerAnalysis() throws CallGraphBuilderCancelException {
    //build cg/pa if needed
    getFullCallGraph();
    return this.pa;
  }

  private HashSet<Entrypoint> result = HashSetFactory.make();

  private Iterable<Entrypoint> entryPoints(ClassLoaderReference clr, IClassHierarchy cha) {
    if (cha == null) {
      throw new IllegalArgumentException("cha is null");
    }
    if (result.size() == 0) {
      for (IClass klass : cha) {
        if (Util.isAppClass(klass)) {
          for (IMethod m : klass.getAllMethods()) {
            if (m!=null && m.isPublic() &&/* !m.isInit() && !m.isClinit() &&*/ !m.isNative() /*&& !isMain(m)*/) {
              result.add(new DefaultEntrypoint(m, cha));  
            }
          }
        }
      }
    }
    return new Iterable<Entrypoint>() {
      public Iterator<Entrypoint> iterator() {
        return result.iterator();
      }
    };
  }

  @SuppressWarnings("unused")
  private boolean isMain(IMethod m) {
    if (m.isPublic() && m.isStatic() && m.getSignature().endsWith("([Ljava/lang/String;)V")) {
      return true;
    } else {
      return false;
    }
  }


}
