package rwsets.manuip;

import japa.parser.ParseException;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import rwsets.RWTest;

import com.ibm.wala.shrikeCT.InvalidClassFileException;
import com.ibm.wala.util.CancelException;
import com.ibm.wala.util.WalaException;


public class ManuIpTest extends RWTest {
  
  @Before
  public void setup() {
    JAR_FILENAME = EXAMPLES_JAR + SEP + "biblioteca.jar";
  }
  
  String srcdir = EXAMPLES_SRC + SEP + "biblioteca-src" ;

 @Test
  public void testSalaDados() throws IOException, WalaException, CancelException, ParseException, InvalidClassFileException {
    
    String strCompUnit = srcdir + SEP + "biblioteca" + SEP + "fachada" + SEP + "Principal.java";
    String line = "String[] salaDados = mSala.getCacheDados();";
    String expectedResultFile = TEST_DIR + SEP + "rwsets/manuip/ManuIpTest.testSalaDadosCache.data";
    checkDeps("fachada", strCompUnit, line, JAR_FILENAME, expectedResultFile);
    
  }
 @Test
 public void testRealizarEmprestimo() throws IOException, WalaException, CancelException, ParseException, InvalidClassFileException {
   String strCompUnit = srcdir + SEP + "biblioteca" + SEP + "fachada" + SEP + "Principal.java";
   String line = "String[] salaDados = mSala.getCacheDados();";
   String expectedResultFile = TEST_DIR + SEP + "rwsets/manuip/ManuIpTest.testRealizarEmprestimo.data";
   checkDeps("fachada", strCompUnit, line, JAR_FILENAME, expectedResultFile);
   
 }
 @Test
 public void testInserirLivro() throws IOException, WalaException, CancelException, ParseException, InvalidClassFileException {
   String strCompUnit = srcdir + SEP + "biblioteca" + SEP + "negocios" + SEP + "ManipularLivro.java";
   String line = "generico.inserirLivro(livro);";
   String expectedResultFile = TEST_DIR + SEP + "rwsets/manuip/ManuIpTest.testInserirLivro.data";
   checkDeps("fachada", strCompUnit, line, JAR_FILENAME, expectedResultFile);
 }

 @Test
 public void testCampoValido() throws IOException, WalaException, CancelException, ParseException, InvalidClassFileException {
   String strCompUnit = srcdir + SEP + "biblioteca" + SEP + "negocios" + SEP + "ManipularLivro.java";
   String line = "if (!campoValido(assunto)) {";
   String expectedResultFile = TEST_DIR + SEP + "rwsets/manuip/ManuIpTest.testCampoValido.data";
   checkDeps("fachada", strCompUnit, line, JAR_FILENAME, expectedResultFile);
 }
 
}