package rwsets.escola;

import japa.parser.ParseException;

import java.io.IOException;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import rwsets.RWTest;

import com.ibm.wala.shrikeCT.InvalidClassFileException;
import com.ibm.wala.util.CancelException;
import com.ibm.wala.util.WalaException;

public class GeeoIpTest extends RWTest {
  
  @Before
  public void setup() {
    JAR_FILENAME = EXAMPLES_JAR + SEP + "escola.jar";
  }

 @Test
  public void testAtualizarProfessor() throws IOException, WalaException,
      CancelException, ParseException, InvalidClassFileException {
    String strCompUnit = EXAMPLES_SRC + SEP + "escola-src" + SEP + "escola" + SEP + "fachadaEscola" + SEP + "Escola.java";
    String line = "pessoas.atualizar(professor);";
    String expectedResultFile = TEST_DIR + SEP   + "rwsets/escola/GeeoIpTest.testAtualizarProfessor.data";
    
    checkDeps("escola", strCompUnit, line, JAR_FILENAME, expectedResultFile);
  }

   @Test
  public void testFindNameInArray() throws ParseException,
      InvalidClassFileException, IOException, WalaException, CancelException {

    String strCompUnit = EXAMPLES_SRC + SEP + "escola-src" + SEP + "escola" + SEP + "fachadaEscola" + SEP + "Escola.java";
    String line = "if (repositorio.equalsIgnoreCase(\"array\")) {";
    String expectedResultFile = TEST_DIR + SEP + "rwsets/escola/GeeoIpTest.testFindNameInArray.data";
    
    checkDeps("escola", strCompUnit, line, JAR_FILENAME, expectedResultFile);
    
    }

   @Test
  public void testFindPerson() throws ParseException,
      InvalidClassFileException, IOException, WalaException, CancelException {

    String strCompUnit = EXAMPLES_SRC + SEP + "escola-src" + SEP + "escola" + SEP + "fachadaEscola" + SEP + "Escola.java";
    String line = " Pessoa p = pessoas.procurar(cpf);// verifica se a pessoa ja foi";
    String expectedResultFile = TEST_DIR + SEP + "rwsets/escola/GeeoIpTest.testFindPerson.data";
    
    checkDeps("escola", strCompUnit, line, JAR_FILENAME, expectedResultFile);
   }

  @Ignore(value="MethodDependencyAnalysis.java:444 (NullPointerException)")
  @Test
  public void testPaginaPrincipal() throws ParseException,
      InvalidClassFileException, IOException, WalaException, CancelException {

    String strCompUnit = EXAMPLES_SRC + SEP + "escola-src" + SEP + "escola" + SEP + "classesBase" + SEP + "Boletim.java";
    String line = "this.disciplinas.inserir(d);";
    String expectedResultFile = TEST_DIR + SEP + "rwsets/escola/GeeoIpTest.testInserirDisciplinaBoletim.data";
    
    checkDeps("escola", strCompUnit, line, JAR_FILENAME, expectedResultFile);
  }

  /*
   * @Test public void testPaginaPrincipal2() throws ParseException,
   * InvalidClassFileException, IOException, WalaException, CancelException {
   * 
   * String strCompUnit = EXAMPLES_SRC + SEP + "GeeoIp" + SEP + "dados" + SEP +
   * "IteratorListaPessoa.java"; String geeoip = EXAMPLES_JAR + SEP + "Oi.jar";
   * Assert.assertTrue((new File(strCompUnit)).exists()); Assert.assertTrue((new
   * File(geeoip)).exists()); String line =
   * "this.repositorio = new RepositorioListaPessoa();";
   * 
   * SimpleGraph sg = depend.Main.analyze(geeoip, "dados", strCompUnit, line);
   * 
   * System.out.println(sg.toDotString()); String expectedResultFile = TEST_DIR
   * + SEP + "rwsets/geeoip/GeeoIpTest.testFindPerson.data";
   * Assert.assertEquals(Helper.readFile(expectedResultFile), sg.toDotString());
   * Assert.assertEquals(1, 1);
   * 
   * }
   */
}
