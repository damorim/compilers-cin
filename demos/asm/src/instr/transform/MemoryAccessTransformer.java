package instr.transform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

public class MemoryAccessTransformer {
  
  /** method of interest **/
  public static String MOI = "callret/instrumentation/examples/Sample$WTF.<init>()V";
  
  
  @SuppressWarnings("unchecked")
  public void transform(ClassNode cn) {

    for (MethodNode mn : (List<MethodNode>) cn.methods) {
      
      // method location
      String location = cn.name + "." + mn.name + mn.desc;
      boolean inMOI = false;
      if (MOI.equals(location)) {
        inMOI = true;
      }
      
      InsnList insns = mn.instructions;
      if (insns.size() == 0) { 
        continue;
      }
      Iterator<AbstractInsnNode> j = insns.iterator();
      while (j.hasNext()) {
        AbstractInsnNode in = j.next();
        int op = in.getOpcode();

        
        if ((op >= Opcodes.IASTORE && op <= Opcodes.SASTORE) ||
            (op >= Opcodes.IALOAD && op <= Opcodes.SALOAD)) {
          // array writes in MOI  
          InsnList list = arrayAccessInstr(op, location);
          insns.insertBefore(in, list);
          insns.remove(in);
        } else if (in instanceof FieldInsnNode) {
          // field name
          FieldInsnNode tmp = (FieldInsnNode)in;
          // class name
          String owner = tmp.owner;
          // field name
          String fieldName = tmp.name;
          
          boolean isStatic;
          boolean isStore;
          //TODO: this will not cover updates on field array
          if (op == Opcodes.PUTFIELD) {
            isStatic = false;
            isStore = true;
          } else if (op == Opcodes.PUTSTATIC) {
            isStatic = true;
            isStore = true;
          } else if (op >= Opcodes.GETFIELD) {
            isStatic = false;
            isStore = false;
          } else if (op <= Opcodes.GETSTATIC) {
            isStatic = true;
            isStore = false;
          } else {
            throw new RuntimeException("UNEXPECTED");
          }
          
          if (inMOI) {
            if (isStore) {
              InsnList list = fieldAccessInstr(isStatic, isStore, owner, fieldName, location);
              insns.insert(in.getPrevious(), list);
            }
          } else if (!isStore) {
              InsnList list = fieldAccessInstr(isStatic, isStore, owner, fieldName, location);
              insns.insert(in.getPrevious(), list);
          }
        }
      }
      // notify on entry
      mn.maxStack += 10;
    } 
  }
 
  static class FieldAccess {
    boolean isStatic;
    boolean isRead;
    Object ref;
    String className;
    String fieldName;
    String source;
    public FieldAccess(
        boolean isStatic, 
        boolean isRead, 
        Object ref, 
        String className,
        String fieldName, 
        String source) {
      this.isStatic = isStatic;
      this.isRead = isRead;
      this.ref = ref;
      this.className = className;
      this.fieldName = fieldName;
      this.source = source;
    }
    @Override
    public int hashCode() {
      return (isStatic?1:0) + (isRead?1:0) + ref.hashCode() + 
          className.hashCode() + fieldName.hashCode() + 
          source.hashCode(); 
    }
    @Override
    public boolean equals(Object obj) {
      boolean result = false;
      if (obj instanceof FieldAccess) {
        FieldAccess tmp = (FieldAccess) obj;
        result = isStatic == tmp.isStatic && 
            isRead == tmp.isRead && 
            ref == tmp.ref && 
            className.equals(tmp.className) && 
            fieldName.equals(tmp.fieldName) &&
            source.equals(tmp.source);
      }
      return result;
    }
  }
  
  static class ArrayAccess {
    Object aref;
    int index;
    String location;
    public ArrayAccess(
        Object aref,
        int index,
        String location) {
      this.aref = aref;
      this.index = index;
      this.location = location;
    }
    @Override
    public int hashCode() {
      return aref.hashCode() + index + location.hashCode(); 
    }
    @Override
    public boolean equals(Object obj) {
      boolean result = false;
      if (obj instanceof ArrayAccess) {
        ArrayAccess tmp = (ArrayAccess) obj;
        result = 
            aref == tmp.aref &&
            index == tmp.index &&
            location.equals(tmp.location);
      }
      return result;
    }
  }
  
  /**
   * writes on regular fields
   */
  static Map<Object, List<FieldAccess>> instanceWrites = new HashMap<Object, List<FieldAccess>>();
  static Map<String, List<FieldAccess>> staticWrites = new HashMap<String, List<FieldAccess>>(); 
  
  /**
   * reads on regular fields
   */
  static Map<Object, List<FieldAccess>> instanceReads = new HashMap<Object, List<FieldAccess>>();
  static Map<String, List<FieldAccess>> staticReads = new HashMap<String, List<FieldAccess>>();    
  
  /**
   * writes and reads on array fields
   */
  static Map<Object, Set<Integer>> arrayWrites = new HashMap<Object, Set<Integer>>();
  static Set<ArrayAccess> arrayReads = new HashSet<ArrayAccess>();
  
  
  public static void dump() {
    System.out.printf("%d static field interactions!\n", staticReads.size());
    System.out.printf("%d instance field interactions!\n", instanceReads.size());
    System.out.printf("%d array interactions!\n", arrayReads.size());
  }
  
  /*************** generation of instrumentation code ****************/
  
  private InsnList fieldAccessInstr(boolean isStatic, boolean isStore, String className, String fieldName, String source) {
    InsnList il = new InsnList();    
    String adviceClassName = "callret/instrumentation/MemoryAccessTransformer";
    String adviceMethodName;
    if (isStore) {
      adviceMethodName = "put";
    } else {
      adviceMethodName = "get";
    }
    if (isStatic) {
      adviceMethodName += "static";
    } else {      
      adviceMethodName += "field";
    }
    // filling instruction list with other arguments
    String signature;
    if (isStatic) {
      signature = "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V";
    } else { /*instance*/
      signature = "(Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V";
      if (isStore) {
        il.add(new InsnNode(Opcodes.DUP2));
        il.add(new InsnNode(Opcodes.POP));
      } else {
        il.add(new InsnNode(Opcodes.DUP));
      }
    }
    il.add(new LdcInsnNode(className));
    il.add(new LdcInsnNode(fieldName));
    il.add(new LdcInsnNode(source));    
    il.add(new MethodInsnNode(Opcodes.INVOKESTATIC, adviceClassName, adviceMethodName, signature));
    return il;
  }
  

  private InsnList arrayAccessInstr(int op, String location) {
    InsnList il = new InsnList();    
    String adviceClassName = "callret/instrumentation/MemoryAccessTransformer";
    // filling instruction list with other arguments
    String adviceMethodName;
    String signature;
    switch (op) {
    /* stores */
    case Opcodes.IASTORE:
      adviceMethodName = "iastore";
      signature = "([IIILjava/lang/String;)V";      
      break;
    case Opcodes.LASTORE:
      adviceMethodName = "lastore";
      signature = "([LILLjava/lang/String;)V";      
      break;
    case Opcodes.FASTORE:
      adviceMethodName = "fastore";
      signature = "([FIFLjava/lang/String;)V";      
      break;
    case Opcodes.DASTORE:
      adviceMethodName = "dastore";
      signature = "([DIDLjava/lang/String;)V";      
      break;
    case Opcodes.AASTORE:
      adviceMethodName = "aastore";
      signature = "([Ljava/lang/Object;ILjava/lang/Object;Ljava/lang/String;)V";      
      break;
    case Opcodes.BASTORE:
      adviceMethodName = "bastore";
      signature = "([BIBLjava/lang/String;)V";      
      break;
    case Opcodes.CASTORE:
      adviceMethodName = "castore";
      signature = "([CICLjava/lang/String;)V";      
      break;
    case Opcodes.SASTORE:
      adviceMethodName = "sastore";
      signature = "([SISLjava/lang/String;)V";      
      break;
    /* loads */
    case Opcodes.IALOAD:
      adviceMethodName = "iaload";
      signature = "([IILjava/lang/String;)I";      
      break;
    case Opcodes.LALOAD:
      adviceMethodName = "laload";
      signature = "([LILjava/lang/String;)L";      
      break;
    case Opcodes.FALOAD:
      adviceMethodName = "faload";
      signature = "([FILjava/lang/String;)F";      
      break;
    case Opcodes.DALOAD:
      adviceMethodName = "daload";
      signature = "([DILjava/lang/String;)D";      
      break;
    case Opcodes.AALOAD:
      adviceMethodName = "aaload";
      signature = "([Ljava/lang/Object;ILjava/lang/String;)Ljava/lang/Object;";      
      break;
    case Opcodes.BALOAD:
      adviceMethodName = "baload";
      signature = "([BILjava/lang/String;)B";      
      break;
    case Opcodes.CALOAD:
      adviceMethodName = "caload";
      signature = "([CILjava/lang/String;)C";      
      break;
    case Opcodes.SALOAD:
      adviceMethodName = "saload";
      signature = "([SILjava/lang/String;)S";      
      break;      
      
    default:
      throw new UnsupportedOperationException();
    }
    il.add(new LdcInsnNode(location));
    il.add(new MethodInsnNode(Opcodes.INVOKESTATIC, adviceClassName, adviceMethodName, signature));
    return il;
  }
  
  /************************* listeners **********************/
  
  public static void putfield(Object ref, String className, String fieldName, String source) {
    if (!source.equals(MOI)) {
      throw new RuntimeException("UNEXPECTED");
    }
    List<FieldAccess> tmp = instanceWrites.get(ref);
    if (tmp == null) {
      tmp = new ArrayList<FieldAccess>();
      instanceWrites.put(ref, tmp);
    }
    FieldAccess acc = new FieldAccess(false, false, ref, className, fieldName, source);
    if (!tmp.contains(acc)) {
      tmp.add(acc);  
    }
  }
  
  public static void putstatic(String className, String fieldName, String source) {
    if (!source.equals(MOI)) {
      throw new RuntimeException("UNEXPECTED");
    }
    List<FieldAccess> tmp = staticWrites.get(className);
    if (tmp == null) {
      tmp = new ArrayList<FieldAccess>();
      staticWrites.put(className, tmp);
    }
    FieldAccess acc = new FieldAccess(true, false, null, className, fieldName, source);
    if (!tmp.contains(acc)) {
      tmp.add(acc);  
    }
  }

  public static void getfield(Object ref, String className, String fieldName, String source) {
    if (source.equals(MOI)) {
      throw new RuntimeException("UNEXPECTED");
    }
    List<FieldAccess> list = instanceWrites.get(ref);
    if (list != null) {
      for (FieldAccess acc : list) {
        if (acc.ref == ref && acc.fieldName.equals(fieldName)) { // BINGO!
          // BINGO!
          List<FieldAccess> tmp = instanceReads.get(ref);
          if (tmp == null) {
            tmp = new ArrayList<FieldAccess>();
            instanceReads.put(ref, tmp);
          }
          FieldAccess acc2 = new FieldAccess(false, true, ref, className, fieldName, source);
          if (!tmp.contains(acc2)) {
            tmp.add(acc2);  
          }
        }
      }
    }
  }
  
  public static void getstatic(String className, String fieldName, String source) {
    if (source.equals(MOI)) {
      throw new RuntimeException("UNEXPECTED");
    }
    List<FieldAccess> list = staticWrites.get(className);
    if (list != null) {
      for (FieldAccess acc : list) {
        if (acc.className.equals(className) && acc.fieldName.equals(fieldName)) { // BINGO!
          List<FieldAccess> tmp = staticReads.get(className);
          if (tmp == null) {
            tmp = new ArrayList<FieldAccess>();
            staticReads.put(className, tmp);
          }
          FieldAccess acc2 = new FieldAccess(true, false, null, className, fieldName, source);
          if (!tmp.contains(acc2)) {
            tmp.add(acc2);  
          }
        }
      }
    }
  }

  /* array accesses */
  public static void iastore(int[] ar, int index, int val, String location) {
    ar[index] = val;
    writeArrayIndex(ar, index, location);
  }
  
  public static void lastore(long[] ar, int index, long val, String location) {
    ar[index] = val;
    writeArrayIndex(ar, index, location);
  }
  
  public static void fastore(float[] ar, int index, float val, String location) {
    ar[index] = val;
    writeArrayIndex(ar, index, location);
  }
  
  public static void dastore(double[] ar, int index, double val, String location) {
    ar[index] = val;
    writeArrayIndex(ar, index, location);
  }
  
  public static void aastore(Object[] ar, int index, Object val, String location) {
    ar[index] = val;
    writeArrayIndex(ar, index, location);
  }
  
  public static void bastore(byte[] ar, int index, byte val, String location) {
    ar[index] = val;
    writeArrayIndex(ar, index, location);
  }
  
  public static void castore(char[] ar, int index, char val, String location) {
    ar[index] = val;
    writeArrayIndex(ar, index, location);
  }
  
  public static void sastore(short[] ar, int index, short val, String location) {
    ar[index] = val;
    writeArrayIndex(ar, index, location);
  }
  
  public static void writeArrayIndex(Object aref, int index, String location) {
    Set<Integer> set = arrayWrites.get(aref);
    if (set == null) {
      set = new HashSet<Integer>();
      arrayWrites.put(aref, set);
    }
    if (location.equals(MOI)) {
      set.add(index);
    } else {
      set.remove(index); // killed
    }
  }
  
  public static int iaload(int[] ar, int index, String location) {
    readArrayIndex(ar, index, location);
    return ar[index];
  }
  
  public static long laload(long[] ar, int index, String location) {
    readArrayIndex(ar, index, location);
    return ar[index];    
  }
  
  public static float faload(float[] ar, int index, String location) {
    
    return ar[index];
  }
  
  public static double daload(double[] ar, int index, String location) {
    readArrayIndex(ar, index, location);
    return ar[index];
  }
  
  public static Object aaload(Object[] ar, int index, String location) {
    readArrayIndex(ar, index, location);
    return ar[index];
  }
  
  public static byte baload(byte[] ar, int index, String location) {
    readArrayIndex(ar, index, location);
    return ar[index];
  }
  
  public static char caload(char[] ar, int index, String location) {
    readArrayIndex(ar, index, location);
    return ar[index];
  }
  
  public static short saload(short[] ar, int index, String location) {
    readArrayIndex(ar, index, location);
    return ar[index];
  }
    
  private static void readArrayIndex(Object aref, int index, String location) {
    Set<Integer> indices = arrayWrites.get(aref);
    if (indices != null) {
      if (indices.contains(index)) {
        arrayReads.add(new ArrayAccess(aref, index, location));
      }
    }
  }

}