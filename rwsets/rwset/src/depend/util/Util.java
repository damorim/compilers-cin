package depend.util;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import com.ibm.wala.util.strings.Atom;
import com.ibm.wala.classLoader.IClass;
import com.ibm.wala.classLoader.IMethod;
import com.ibm.wala.ipa.cha.ClassHierarchy;
import com.ibm.wala.util.WalaException;
import com.ibm.wala.viz.DotUtil;

import depend.util.graph.SimpleGraph;

public class Util {
  
  /************** CONSTANTS **************/
  
  public static String APP_PREFIX;

  private static Properties PROPS;
  
  private static final String OUTPUT_PATH = System.getProperty("user.dir") + System.getProperty("file.separator") + "results" + System.getProperty("file.separator");

  private static final String DEFAULT_GRAPH_OUTPUT_PATH = OUTPUT_PATH + "results.pdf"; 
    
  private static final String DEFAULT_DOT_OUTPUT_PATH = OUTPUT_PATH + "results.dot";
  
  /************** PROPERTY NAMES **************/
  
  private static final String LIST_ALL_CLASSES_PROPERTY_NAME = "listAllClasses";
  
  private static final String LIST_APP_CLASSES_PROPERTY_NAME = "listAppClasses";
  
  private static final String LIST_APP_METHODS_PROPERTY_NAME = "listAppMethods";
  
  private static final String REPORT_TYPE_PROPERTY_NAME = "reportType";
  
  private static final String APP_PREFIX_PROPERTY_NAME = "appPrefix";
  
  private static final String DOT_EXECUTABLE_PATH_PROPERTY_NAME = "dotPath";

  private static final String GRAPH_OUTPUT_PATH_PROPERTY_NAME = "graphFileOutputPath";

  private static final String DOT_OUTPUT_PATH_PROPERTY_NAME = "dotFileOutputPath";

  public static final String FORWARD_DEPENDENCIES_PROPERTY_NAME = "forwardDependencies";

  public static final String WITH_INDIRECTS_PROPERTY_NAME = "withIndirects";

  /************** classification of methods and classes ******************/
  public static boolean isRelevantMethod(IMethod meth) {
    IClass klass = meth.getDeclaringClass();
    String pack = klass.getName().getPackage().toString();
    // a => b = !a || b    
    boolean result = 
        (!(pack.startsWith("java/lang") || (klass.getName().toString().startsWith("Ljava/lang/Object")))) &&
        isRelevantClass(klass) && 
        (!meth.isNative() && !meth.isSynthetic());

    return result;
  }

  private static boolean isRelevantClass(IClass klass) {
    //    return classLoader.getName().toString().equals("Application");
    String pack = klass.getName().getPackage().toString();
    return 
        pack.startsWith("java/util") || 
        pack.startsWith(APP_PREFIX);
  }

  public static boolean isAppClass(IClass klass) {
    //  return classLoader.getName().toString().equals("Application"); 
    Atom pack = klass.getName().getPackage();
    return pack != null ? pack.toString().startsWith(APP_PREFIX) : false;
  }
  
  public static void setProperties(Properties _props) {
    PROPS = _props;
    APP_PREFIX = _props.getProperty(APP_PREFIX_PROPERTY_NAME);  
    if (APP_PREFIX == null) {
      throw new RuntimeException("Please, specifiy \"appPrefix\" parameter");
    }
  }
  
  private static StringBuffer warningMessages = new StringBuffer();
  public static void logWarning(String msg) {
    warningMessages.append(msg);
    warningMessages.append("\n");
  }
 
  public static boolean getBooleanProperty(String string, boolean defaultBoolean) {
    Object o = PROPS.get(string);
    boolean result;
    if (o != null) {
      String s = (String) o;
      result = Boolean.parseBoolean(s);
    } else {
      result = defaultBoolean;
    }
    return result;
  }

  public static boolean getBooleanProperty(String string) {
    return getBooleanProperty(string, false);
  }

  public static String getStringProperty(String propertyName, String defaultString) {
    Object o = PROPS.get(propertyName);
    String result;
    if (o != null) {
      result = (String) o;
    } else {
      result = defaultString;
    }
    return result;
  }

  public static String getStringProperty(String string) {
    return getStringProperty(string, "");
  }
  
  public static void dumpResults(SimpleGraph depGraph) throws IOException, WalaException {
    String reportType = Util.getStringProperty(REPORT_TYPE_PROPERTY_NAME).trim();
    if (reportType.equals("list")) {
      System.out.println(depGraph.toString());
    } else if (reportType.equals("dot")) {
      // results.dot      
      String dotResultsPath = Util.getStringProperty(DOT_OUTPUT_PATH_PROPERTY_NAME, DEFAULT_DOT_OUTPUT_PATH);
      System.out.println("Saving .dot file to: " + dotResultsPath);
      File dotFile = new File(dotResultsPath);
      FileWriter fw = new FileWriter(dotFile);
      fw.append(depGraph.toDotString());
      fw.flush();
      fw.close();
      String graphPdfPath = Util.getStringProperty(GRAPH_OUTPUT_PATH_PROPERTY_NAME, DEFAULT_GRAPH_OUTPUT_PATH);
      System.out.println("Saving .pdf file to: " + graphPdfPath);
      DotUtil.spawnDot(Util.getStringProperty(DOT_EXECUTABLE_PATH_PROPERTY_NAME), graphPdfPath, dotFile);
    }
  }
  
  
  /**
   * Generates output
   * 
   * @param method
   * @param map
   * @throws IOException
   * @throws WalaException
   */
  @Deprecated
  public static void dumpResults(IMethod method, Map<IMethod,String> map) throws IOException, WalaException {
    
    String reportType = Util.getStringProperty(REPORT_TYPE_PROPERTY_NAME).trim();
    
    if (reportType.equals("list")) {
      
      System.out.printf("data dependencies to method %s\n", method);

      // printing dependencies
      for (Entry<IMethod, String> m: map.entrySet()) {
        if (Util.isAppClass(m.getKey().getDeclaringClass())) {
          System.out.printf("  %s\n", m.getKey() + m.getValue());
        }      
      }

    } else if (reportType.equals("dot")) {
      //TODO: you may want to print before propagating 
      //data dependencies
      
      /**
       * generate dot
       */
      StringBuffer sb = new StringBuffer();
      sb.append("digraph \"DirectedGraph\" {\n");
      sb.append(" graph [concentrate = true];\n");
      sb.append(" center=true;\n");
      sb.append(" fontsize=6;\n");
      sb.append(" node [ color=blue,shape=\"box\"fontsize=6,fontcolor=black,fontname=Arial];\n");
      sb.append(" edge [ color=black,fontsize=6,fontcolor=black,fontname=Arial];\n");
      
      for (Entry<IMethod, String> m: map.entrySet()) {
        if (Util.isAppClass(m.getKey().getDeclaringClass())) {
          sb.append(m);
          sb.append(" -> ");
          sb.append(m.getKey() + m.getValue());
          sb.append("\n");
        }      
      }
      sb.append("}\n");
      
      /**
       * results.dot
       */
      String dotResultsPath = Util.getStringProperty(DOT_OUTPUT_PATH_PROPERTY_NAME, DEFAULT_DOT_OUTPUT_PATH);
      System.out.println("Outputing dot file to: " + dotResultsPath);
      File dotFile = new File(dotResultsPath);
      FileWriter fw = new FileWriter(dotFile);
      fw.append(sb);
      fw.flush();
      fw.close();
      String graphPdfPath = Util.getStringProperty(GRAPH_OUTPUT_PATH_PROPERTY_NAME, DEFAULT_GRAPH_OUTPUT_PATH);
      System.out.println("Outputing graph file to: " + graphPdfPath);
      DotUtil.spawnDot(Util.getStringProperty(DOT_EXECUTABLE_PATH_PROPERTY_NAME), graphPdfPath, dotFile);
      
    }
  }

  /**
   * find all methods from the class hieararchy
   * 
   * @param cha
   * @return
   */
  @Deprecated
  public static Set<IMethod> findAllMethods(ClassHierarchy cha) {
    Set<IMethod> allMethods = new HashSet<IMethod>();
    
    // configuration    
    boolean printAllClasses = getBooleanProperty(LIST_ALL_CLASSES_PROPERTY_NAME);
    if (printAllClasses) {
      System.out.println("All classes:");
    }

    boolean printAppClasses = getBooleanProperty(LIST_APP_CLASSES_PROPERTY_NAME);
    if (printAppClasses) {
      System.out.println("Application classes:");
    }

    boolean printAppMethods = getBooleanProperty(LIST_APP_METHODS_PROPERTY_NAME);

    Set<IClass> visitedClasses = new HashSet<IClass>();
    List<IClass> toVisitClass = new ArrayList<IClass>();
    toVisitClass.add(cha.getRootClass());
    
    while (!toVisitClass.isEmpty()) {
      IClass iclass = toVisitClass.remove(0);

      if (visitedClasses.contains(iclass)) {
        continue;
      }
      visitedClasses.add(iclass);

      toVisitClass.addAll(cha.getImmediateSubclasses(iclass));
      
      if (printAllClasses) {
        System.out.println(iclass);
      }

      if (!Util.isRelevantClass(iclass)) {
        continue;
      }

      boolean toPrint = false;
      if (printAppClasses && Util.isAppClass(iclass)) {
        toPrint = true;
        System.out.println(iclass);
      }

      // iclass was not visited and is a public application class
      for (IMethod imethod : iclass.getAllMethods()) {
        if (imethod.getDeclaringClass() != iclass) {
          continue; // will be visited by superclass
        }

        if (allMethods.contains(imethod)) {
          continue;
        }

        if (toPrint && printAppMethods) {
          System.out.printf(" %s\n", imethod);
        }

        allMethods.add(imethod);
      }
    }

    return allMethods;
    
  }

}
