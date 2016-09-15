package instr.transform;

import java.util.Iterator;
import java.util.List;

import org.objectweb.asm.Attribute;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.InnerClassNode;
import org.objectweb.asm.tree.MethodNode;

public class StatementTransformer
{
    public StatementTransformer() {
        // empty
    }

    @SuppressWarnings("unchecked")
    public void transform(ClassNode cn)
    {
        System.out.println("cn.name: " + cn.name);

        /**
         * Attributes
         */
        List<Attribute> allAttributes = cn.attrs;
        if (allAttributes != null)
        {
          for (Attribute attr : allAttributes)
          {
            System.out.println("    attr.type: " + attr.type + ", attr.toString: " + attr.toString());
          }
        }

        /**
         * Fields
         */
        List<FieldNode> allFields = cn.fields;
        for (FieldNode field : allFields)
        {
          System.out.println("    field.name: " + field.name);
        }

        /**
         * InnerClasses
         */
        List<InnerClassNode> allInnerClassNode = cn.innerClasses;
        for (InnerClassNode innerClass : allInnerClassNode)
        {
          System.out.println("    innerClass.innerName: " + innerClass.innerName +
                                  ", innerClass.name: " + innerClass.name +
                                  ", innerClass.outerName: " + innerClass.outerName);
        }

        /**
         * Interfaces
         */

        /**
         * Methods
         */
        List<MethodNode> allMethods = cn.methods;
        for (MethodNode mn : allMethods)
        {
            // Constructor || Destructor
            if ("<init>".equals(mn.name) || "<clinit>".equals(mn.name))
                continue;

            // method without lines (instructions)
            if (mn.instructions.size() == 0)
                continue;

            // /////////////////////////////////////
            System.out.println("    mn.name: " + mn.name);

            Iterator<AbstractInsnNode> j = mn.instructions.iterator();
            while (j.hasNext())
            {
                AbstractInsnNode abs_ins = j.next();
                System.out.println("        abs_ins.getOpcode(): " + abs_ins.getOpcode());
            }
        }
    }
}
