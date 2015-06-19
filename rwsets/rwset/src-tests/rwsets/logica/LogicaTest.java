package rwsets.logica;

import japa.parser.ParseException;

import java.io.IOException;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import rwsets.RWTest;

import com.ibm.wala.shrikeCT.InvalidClassFileException;
import com.ibm.wala.util.CancelException;
import com.ibm.wala.util.WalaException;

public class LogicaTest extends RWTest {
  
  @Before
  public void setup() {
    JAR_FILENAME = EXAMPLES_JAR + SEP + "logica.jar";
  }
  
  String srcdir = EXAMPLES_SRC + SEP + "logica-src" ;

  @Test
  public void testClearClausulas() throws IOException, WalaException, CancelException, ParseException, InvalidClassFileException {

    String strCompUnit = srcdir + SEP + "logica" + SEP + "Resolucao.java";
    String line = "clausulas.clear();";
    String expectedResultFile = TEST_DIR + SEP + "rwsets/logica/LogicaTest.testClearClausulas.data";
    checkDeps("logica", strCompUnit, line, JAR_FILENAME, expectedResultFile);
  }

  @Test
  public void testFunctionsHorn() throws IOException, WalaException, CancelException, ParseException, InvalidClassFileException {

    String strCompUnit = srcdir + SEP +  "logica" + SEP + "Resolucao.java";
    String line = "if (Functions.verifyHorn(exp)){";
    String expectedResultFile = TEST_DIR + SEP + "rwsets/logica/LogicaTest.testFunctionsHorn.data";
    checkDeps("logica", strCompUnit, line, JAR_FILENAME, expectedResultFile);
  }

  @Test
  public void testFunctionsSAT() throws IOException, WalaException, CancelException, ParseException, InvalidClassFileException {
    
    String strCompUnit = srcdir + SEP +  "logica" + SEP + "Resolucao.java";
    String line = "if (Functions.SAT(exp, clausulas)){";
    String expectedResultFile = TEST_DIR + SEP + "rwsets/logica/LogicaTest.testFunctionsSAT.data";    
    checkDeps("logica", strCompUnit, line, JAR_FILENAME, expectedResultFile);
  }
  
  @Ignore
  @Test
  public void testResolucaoFNC() throws IOException, WalaException, CancelException, ParseException, InvalidClassFileException {

    String strCompUnit = srcdir + SEP +  "logica" + SEP + "Resolucao.java";
    String line = " if (Functions.verifyFNC(exp)){";
    String expectedResultFile = TEST_DIR + SEP + "rwsets/logica/LogicaTest.testResolucaoFNC2.data";
    checkDeps("logica", strCompUnit, line, JAR_FILENAME, expectedResultFile);
  }
  
  @Test
  public void testResolucaoFNC2() throws IOException, WalaException, CancelException, ParseException, InvalidClassFileException {

    String strCompUnit = srcdir + SEP + "logica" + SEP + "Resolucao2.java";
    String line = "return Functions.verifyFNC(exp);";
    String expectedResultFile = TEST_DIR + SEP + "rwsets/logica/LogicaTest.testResolucaoFNC2.data";
    checkDeps("logica", strCompUnit, line, JAR_FILENAME, expectedResultFile);
  }
  
  @Test
  public void testFunctionsIsLiteral() throws IOException, WalaException, CancelException, ParseException, InvalidClassFileException {

    String strCompUnit =srcdir + SEP +  "logica" + SEP + "Functions.java";
    String line = "if (isLiteral(clausulas.get(i)))";
    String expectedResultFile = TEST_DIR + SEP + "rwsets/logica/LogicaTest.testFunctionsIsLiteral.data";
    checkDeps("logica", strCompUnit, line, JAR_FILENAME, expectedResultFile);
  }
  
  
  //  i = eliminar(clausulas, cl, i, j);
  @Test
  public void testFunctionsEliminar() throws IOException, WalaException, CancelException, ParseException, InvalidClassFileException {

    String strCompUnit = srcdir + SEP +  "logica" + SEP + "Functions.java";
    String line = "i = eliminar(clausulas, cl, i, j);";
    String expectedResultFile = TEST_DIR + SEP + "rwsets/logica/LogicaTest.testFunctionsEliminar.data";
    checkDeps("logica", strCompUnit, line, JAR_FILENAME, expectedResultFile);
  } 

}