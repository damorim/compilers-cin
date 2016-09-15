package instr.transform;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.LineNumberNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.TryCatchBlockNode;

public class EntryExitTransformer { 
  
  public EntryExitTransformer() { } 

  @SuppressWarnings("unchecked")
  public void transform(ClassNode cn) {

    for (MethodNode mn : (List<MethodNode>) cn.methods) {
      InsnList insns = mn.instructions;
      if (insns.size() == 0) { 
        continue;
      }
      Iterator<AbstractInsnNode> j = insns.iterator();
      
      List<LabelNode> exceptionHandlers = new ArrayList<LabelNode>();
      
      for (int i = 0; i < mn.tryCatchBlocks.size(); i++) {
        LabelNode lnode = ((TryCatchBlockNode) mn.tryCatchBlocks.get(i)).handler;
        if (lnode != null) {
          exceptionHandlers.add(lnode);
        }
      }

      while (j.hasNext()) {
        AbstractInsnNode in = j.next();
        int op = in.getOpcode();
        
        boolean isRet = (op >= Opcodes.IRETURN && op <= Opcodes.RETURN) || op == Opcodes.RET;

        
        if (op == Opcodes.ATHROW) {
          // notify on explicit throws
          InsnList il = notify(cn, mn, "throwE");
          insns.insert(in.getPrevious(), il);
        } else if (isRet) {
          // notify on exits
          InsnList il = notify(cn, mn, "exit");
          insns.insert(in.getPrevious(), il);
        } else if (exceptionHandlers.contains(in)) {
          // notify on catches
          InsnList il = notify(cn, mn, "catchE");
          AbstractInsnNode place = getNextRelevant(j);
          insns.insert(place.getPrevious(), il);          
        }
                
      }
      // notify on entry 
      InsnList il = notify(cn, mn, "entry");      
      insns.insert(il); 
      mn.maxStack += 10;
    } 
  }
  
  private AbstractInsnNode getNextRelevant(Iterator<AbstractInsnNode> it) {
    AbstractInsnNode insn = null;
    while (it.hasNext()) {
      insn = it.next();
      if (insn != null && !(insn instanceof LineNumberNode)) {
        break;
      }
    }
    if (insn == null) {
      throw new RuntimeException();
    }
    return insn;
  }
  
  private InsnList notify(ClassNode cn, MethodNode mn, String event) {
    InsnList il = new InsnList();
    String fullyQmName = "L" + cn.name + ";" + mn.name + mn.desc;
    il.add(new LdcInsnNode(fullyQmName));
    il.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "instr/agent/InstrumentationState", event, "(Ljava/lang/String;)V"));
    return il;
  }
 
}