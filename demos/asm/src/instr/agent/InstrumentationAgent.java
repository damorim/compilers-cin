package instr.agent;

import instr.ClassInstrumenter;

import java.io.IOException;
import java.lang.instrument.Instrumentation;


/**
 * This is the instrumentation agent class that 
 * must be passed in the command-line using
 * the command below.
 * 
 * -javaagent:jarpath[=options]
 */
public class InstrumentationAgent {
  
  /**
   * This method is the entry point of the java agent.
   */
  public static void premain (String agentArgs, Instrumentation inst) throws IOException {
      //System.out.printf("> %s" , agentArgs);
    inst.addTransformer(new ClassInstrumenter());
  }
}