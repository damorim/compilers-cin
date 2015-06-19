package rwsets;

import japa.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;

import com.ibm.wala.shrikeCT.InvalidClassFileException;
import com.ibm.wala.util.CancelException;
import com.ibm.wala.util.WalaException;

import depend.util.graph.SimpleGraph;

public class RWTest {
  
  public final String USER_DIR = System.getProperty("user.dir");
  public final String SEP = System.getProperty("file.separator");
  public final String EXAMPLES = USER_DIR + SEP + "example-apps";
  public final String TEST_DIR = USER_DIR + SEP + "src-tests";
  public final String EXAMPLES_SRC = EXAMPLES + SEP + "src";
  public final String EXAMPLES_JAR = EXAMPLES;
  public final String RESOURCES_DIR = USER_DIR + SEP + "dat";
  public final String EXCLUSION_FILE = RESOURCES_DIR + SEP + "ExclusionAllJava.txt";
  public final String EXCLUSION_FILE_FOR_CALLGRAPH = RESOURCES_DIR + SEP + "ExclusionForCallGraph.txt";
  
  public String JAR_FILENAME;

  @Before
  public void setup() {
    Assert.assertTrue((new File(EXCLUSION_FILE)).exists());
    Assert.assertTrue((new File(EXCLUSION_FILE_FOR_CALLGRAPH)).exists());
  }

  /**
   * Helper to facilitate testing:
   * 
   *  + it checks if the compilation unit can be found
   *  + it checks if the jar file (with classes) can be found
   *  + it runs the analyzer and checks if it matches with saved file (for regression)
   * 
   * @param prefix only classes that starts with this prefix will be considered (including package names)
   * @param compilationUnitName name of the compilation unit
   * @param targetLineInCompilationUnit name of line of interest in compilation unit
   * @param jarFileName name of the jar file where application resides
   * @param expectedResultFileName file with the graph representation encoding dependencies in dot format
   * 
   * @throws IOException
   * @throws WalaException
   * @throws CancelException
   * @throws ParseException
   * @throws InvalidClassFileException
   * @throws FileNotFoundException
   */
  protected void checkDeps(
      String prefix, 
      String compilationUnitName, 
      String targetLineInCompilationUnit,  
      String jarFileName, 
      String expectedResultFileName 
      ) throws IOException, WalaException, CancelException, ParseException, InvalidClassFileException, FileNotFoundException {

    Assert.assertTrue((new File(compilationUnitName)).exists());
    Assert.assertTrue((new File(jarFileName)).exists());

    String[] lineAndClass = depend.util.parser.Util.getLineAndWALAClassName(targetLineInCompilationUnit, compilationUnitName);
    
    SimpleGraph sg = depend.Main.analyze(jarFileName, prefix, Integer.parseInt(lineAndClass[0]), lineAndClass[1]);
    
    if (expectedResultFileName != null) {
      Assert.assertEquals(Helper.readFile(expectedResultFileName), sg.toDotString());
    }
  }


}