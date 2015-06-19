package rwsets.Area;

import japa.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import rwsets.Helper;
import rwsets.RWTest;

import com.ibm.wala.classLoader.IClass;
import com.ibm.wala.classLoader.IMethod;
import com.ibm.wala.shrikeCT.InvalidClassFileException;
import com.ibm.wala.types.ClassLoaderReference;
import com.ibm.wala.types.TypeReference;
import com.ibm.wala.util.CancelException;
import com.ibm.wala.util.WalaException;
import com.ibm.wala.util.io.CommandLine;
import com.ibm.wala.util.warnings.Warnings;

import depend.MethodDependencyAnalysis;
import depend.util.Util;
import depend.util.graph.SimpleGraph;


public class TestArea extends RWTest {
  
  @Before
  public void setup() {
    JAR_FILENAME = EXAMPLES_JAR + SEP + "Area.jar";
  }
  
  @Test
  public void test0() throws IOException, WalaException, CancelException, ParseException, InvalidClassFileException {
    String strCompUnit = EXAMPLES_SRC + SEP + "Area/Forma.java";
    String line = "if (ler == 1) {";
    String expectedResultFile = TEST_DIR + SEP + "/rwsets/Area/TestArea.test0.data";
    checkDeps("Area", strCompUnit, line, JAR_FILENAME, expectedResultFile);
  }
 
  @Test
  public void testPrimitiveTypeDependency() throws IOException, WalaException, CancelException, ParseException, InvalidClassFileException {
    String strCompUnit = EXAMPLES_SRC + SEP + "Area/Forma.java";
    String line = "m < 0";   
    String expectedResultFile = TEST_DIR + SEP + "/rwsets/Area/TestArea.testPrimitiveTypeDependency.data";
    checkDeps("Area", strCompUnit, line, JAR_FILENAME, expectedResultFile);
  }
   
  @Test
  public void testReferenceDependency() throws IOException, WalaException, CancelException, ParseException, InvalidClassFileException {
    String strCompUnit = EXAMPLES_SRC + SEP + "Area/Forma.java";
    String line = "System.out.println(\"Área do retângulo: \" + ret.area());";    
    String expectedResultFile = TEST_DIR + SEP + "/rwsets/Area/TestArea.testReferenceDependency.data";
    checkDeps("Area", strCompUnit, line, JAR_FILENAME, expectedResultFile);
  }
  
  @Test
  public void testSetMedida() throws IOException, WalaException, CancelException, ParseException, InvalidClassFileException {
    String strCompUnit = EXAMPLES_SRC + SEP + "Area/Circunferencia.java";
    String line = "setMedida(0, raio);";
    String expectedResultFile = TEST_DIR + SEP + "/rwsets/Area/TestArea.testSetMedida.data";
    checkDeps("Area", strCompUnit, line, JAR_FILENAME, expectedResultFile);
  }
  
  @Ignore @Test
  public void testGetMedida() throws IOException, WalaException, CancelException, ParseException, InvalidClassFileException {
    String strCompUnit = EXAMPLES_SRC + SEP + "Area/Retangulo.java";
    String line = "getMedida(0) + getMedida(1)";
    String expectedResultFile = TEST_DIR + SEP + "/rwsets/Area/TestArea.testGetMedida.data";
    checkDeps("Area", strCompUnit, line, JAR_FILENAME, expectedResultFile);
  }

  @Test
  public void testArrayDependency() throws IOException, WalaException, CancelException, ParseException, InvalidClassFileException {
    String strCompUnit = EXAMPLES_SRC + SEP + "Area/Forma.java";
    String line = "medida[i] = m;";    
    String expectedResultFile = TEST_DIR + "/rwsets/Area/TestArea.testArrayDependency.data";
    checkDeps("Area", strCompUnit, line, JAR_FILENAME, expectedResultFile);
  }
  
}