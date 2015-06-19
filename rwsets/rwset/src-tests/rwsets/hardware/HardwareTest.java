package rwsets.hardware;


import japa.parser.ParseException;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import rwsets.RWTest;

import com.ibm.wala.shrikeCT.InvalidClassFileException;
import com.ibm.wala.util.CancelException;
import com.ibm.wala.util.WalaException;

public class HardwareTest extends RWTest {
  
  @Before
  public void setup() {
    JAR_FILENAME = EXAMPLES_JAR + SEP + "hardware.jar";
  }
  
  String srcdir = EXAMPLES_SRC + SEP + "hardware-src" ;

  @Test
  public void testArquivoPrintln() throws IOException, WalaException,
      CancelException, ParseException, InvalidClassFileException {
    String strCompUnit =  srcdir + SEP + "hardware" + SEP + "Montador.java";
    String expectedResultFile = TEST_DIR + SEP + "rwsets/hardware/Hardware.testArquivoPrintln.data";
    String line = "io.println(\"END;\");";
    
    checkDeps("hardware", strCompUnit, line, JAR_FILENAME, expectedResultFile);
  }

  @Test
  public void testGetBinario() throws IOException, WalaException,
      CancelException, ParseException, InvalidClassFileException {
    String strCompUnit =  srcdir + SEP +  "hardware" + SEP + "Montador.java";
    String expectedResultFile = TEST_DIR + SEP  + "rwsets/hardware/Hardware.testGetBinario.data";
    String line = "opcode = R.OPCODE.get(i).binario;";

    checkDeps("hardware", strCompUnit, line, JAR_FILENAME, expectedResultFile);
  }
  
  @Test
  public void testImprimir() throws IOException, WalaException,
      CancelException, ParseException, InvalidClassFileException {
    String strCompUnit = srcdir + SEP +  "hardware" + SEP + "Montador.java";
    String expectedResultFile = TEST_DIR + SEP + "rwsets/hardware/Hardware.testImprimir.data";
    String line = "imprimir(formata_R(RS, RT, RD, shamt, opcode, funcao),assembly);";

    checkDeps("hardware", strCompUnit, line, JAR_FILENAME, expectedResultFile);

  }
  
  @Test
  public void testOpcodeAdd() throws IOException, WalaException,
      CancelException, ParseException, InvalidClassFileException {
    String strCompUnit = srcdir + SEP +  "hardware" + SEP + "TipoJ.java";
    String line = "OPCODE.add(new Estrutura(\"j\",\"000010\"));";
    String expectedResultFile = TEST_DIR + SEP + "rwsets/hardware/TipoJ.testOpcodeAdd.data";
    
    checkDeps("hardware", strCompUnit, line, JAR_FILENAME, expectedResultFile);
  }
  

}
