package instr;

import instr.transform.EntryExitTransformer;

import java.io.PrintWriter;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.util.TraceClassVisitor;

public class ClassInstrumenter implements ClassFileTransformer {
  public static boolean DEBUG       = false;
  public static String INTERESTED  = "examples";

  /**
   * this method is invoked for every
   * class that the JVM is about to load!
   */
  @Override
  public byte[] transform(ClassLoader loader, String className,
                          Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
                          byte[] classfileBuffer) throws IllegalClassFormatException {
    
    byte[] result = classfileBuffer;

    if (className.contains(INTERESTED)) {
      
      // building class node object
      ClassReader cr = new ClassReader(classfileBuffer);
      ClassNode cnode = new ClassNode(Opcodes.ASM4);
      cr.accept(cnode, 0);

      // transforming class node object
      EntryExitTransformer transformer = new EntryExitTransformer();
      // StatementTransformer transformer = new StatementTransformer();
      // MemoryAccessTransformer transformer = new MemoryAccessTransformer();
      
      try {
        
        transformer.transform(cnode);
      }
      catch (RuntimeException _) {
        _.printStackTrace();
        throw _;
      }

      // building JVM bytecodes
      ClassWriter cw = new ClassWriter(0);

      if (DEBUG == true) {
        TraceClassVisitor tracer = new TraceClassVisitor(cw, new PrintWriter(System.out));
        cnode.accept(tracer);
      } else {
        cnode.accept(cw);
      }

      // spitting bytecodes out
      result = cw.toByteArray();
    }

    return result;
  }
}