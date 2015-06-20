package rwsets.CaixaEletronico;

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


public class TestCaixaEletronico extends RWTest {
  
  @Before
  public void setup() {
    JAR_FILENAME = EXAMPLES_JAR + SEP + "Caixa.jar";
  }
  
  @Test
  public void test0() throws IOException, WalaException, CancelException, ParseException, InvalidClassFileException {
    String strCompUnit = EXAMPLES_SRC + SEP + "CaixaEletronico/Conta.java";
    String line = "if(saques<3) {";
    String expectedResultFile = TEST_DIR + SEP + "/rwsets/CaixaEletronico/TestCaixaEletronico.test0.data";
    checkDeps("Caixa", strCompUnit, line, JAR_FILENAME, expectedResultFile);
  }
  
  @Ignore @Test
  public void testBasicDoesNotCrash() throws IOException, WalaException, CancelException, ParseException, InvalidClassFileException {
    String strCompUnit = EXAMPLES_SRC + SEP + "CaixaEletronico/Caixa.java";
    String line = "System.out.println(\"Cadastrando novo cliente.\");";   
    checkDeps("Caixa", strCompUnit, line, JAR_FILENAME, null);
  }
  
  @Test
  public void testPrimitiveTypeDependency() throws IOException, WalaException, CancelException, ParseException, InvalidClassFileException {
    String strCompUnit = EXAMPLES_SRC + SEP + "CaixaEletronico/Conta.java";
    String line = "saldo >= valor";   
    String expectedResultFile = TEST_DIR + SEP + "/rwsets/CaixaEletronico/TestCaixaEletronico.testPrimitiveTypeDependency.data";
    checkDeps("Caixa", strCompUnit, line, JAR_FILENAME, expectedResultFile);
  }
   
  @Test
  public void testReferenceDependency() throws IOException, WalaException, CancelException, ParseException, InvalidClassFileException {
    String strCompUnit = EXAMPLES_SRC + SEP + "CaixaEletronico/Conta.java";
    String line = "System.out.println(\"Nome: \" + this.nome);";    
    String expectedResultFile = TEST_DIR + SEP + "/rwsets/CaixaEletronico/TestCaixaEletronico.testReferenceDependency.data";
    checkDeps("Caixa", strCompUnit, line, JAR_FILENAME, expectedResultFile);
  }
  
  @Test
  public void testExibeMenu() throws IOException, WalaException, CancelException, ParseException, InvalidClassFileException {
    String strCompUnit = EXAMPLES_SRC + SEP + "CaixaEletronico/Conta.java";
    String line = "exibeMenu();";
    String expectedResultFile = TEST_DIR + SEP + "/rwsets/CaixaEletronico/TestCaixaEletronico.testExibeMenu.data";
    checkDeps("Caixa", strCompUnit, line, JAR_FILENAME, expectedResultFile);
  }
  
  @Test
  public void testEscolheOpcao() throws IOException, WalaException, CancelException, ParseException, InvalidClassFileException {
    String strCompUnit = EXAMPLES_SRC + SEP + "CaixaEletronico/Conta.java";
    String line = "escolheOpcao(opcao);";
    String expectedResultFile = TEST_DIR + SEP + "/rwsets/CaixaEletronico/TestCaixaEletronico.testEscolheOpcao.data";
    checkDeps("Caixa", strCompUnit, line, JAR_FILENAME, expectedResultFile);
  }

}