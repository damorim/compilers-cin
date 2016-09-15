package instr;

import instr.agent.InstrumentationState;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * This is a wrapper class for calling the
 * main method on the instrumented class.
 * 
 * The only reason we need this is to have
 * the chance to access the instrumentation
 * state before the VM shuts down.
 * 
 * 
 * @author damorim
 *
 */
public class Wrapper {
  
  public static void main(String[] args) throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
    // clear instrumentation state
    InstrumentationState.clear();
    // first parameter is for class name
    String[] args_ = new String[args.length-1];
    System.arraycopy(args, 1, args_, 0, args.length-1);
    // calling main of instrumented class through reflection
    Class<?> clazz = Class.forName(args[0]);
    Method meth = clazz.getMethod("main", String[].class);
    try {
      meth.invoke(null, (Object) args_);   
    } catch (InvocationTargetException _) {
      _.getCause().printStackTrace();
    }
    // finally dump instrumentation results
    InstrumentationState.dump();
  }

}
