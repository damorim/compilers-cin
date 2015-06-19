package depend.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.ibm.wala.classLoader.IMethod;
import com.ibm.wala.types.TypeReference;

/***
 * 
 * Class to present method in RANDOOP format
 * 
 */
public class RandoopFormatter {
  
  public static List<String> toRandoopFormat(Set<IMethod> scopeSet) {
    List<String> result = new ArrayList<String>(scopeSet.size());

    for (IMethod m : scopeSet) {
      StringBuffer sb = new StringBuffer();
      if (m.isInit()) {
        sb.append("cons : ");
      } else {
        sb.append("method : ");
      }

      //classname
      sb.append(m.getDeclaringClass().getReference().getName().toString().substring(1));
      sb.append('.');
      if (m.isInit()) {
        sb.append("<init>");
      } else {
        String name = m.getSelector().toString();
        sb.append(name.substring(0,name.indexOf('(')));
      }

      sb.append('(');
      int nParameters = m.getNumberOfParameters();
      int start;
      if (m.isStatic()) {
        start = 0;
      } else {
        start = 1; //skip 'this'
      }
      boolean hasParameter = false;

      for (int i = start; i < nParameters ; i++) {
        hasParameter = true;
        TypeReference tr = m.getParameterType(i);
        if (tr.isClassType()) {
          sb.append(tr.getName().toString().substring(1));
        } else if (tr.isArrayType()) {
          //          sb.append(tr.getArrayElementType().getName().toString().substring(1));
          TypeReference tarr = tr.getArrayElementType();
          if (tarr.isClassType()) {
            sb.append(tr.getName().toString());
            sb.append(';');
          } else {
            sb.append('[');
            sb.append(tarr.getName().toString());
          }
        } else if (tr.isPrimitiveType()) {
          sb.append(translatePrimitiveCode(tr.getName().toString()));
        } else {
          sb.append(tr.getName().toString());
        }
        sb.append(", ");
      }
      if (hasParameter) {
        sb.deleteCharAt(sb.length() - 1);
        sb.deleteCharAt(sb.length() - 1);
      }
      sb.append(')');

      String finalname = sb.toString().replace('/', '.');
      result.add(finalname);
      //      System.out.println(finalname);
    }

    return result;
  }

  private static String translatePrimitiveCode(String code) {
    String result;
    if (code.equals("Z")) {
      result = "boolean";
    } else if (code.equals("B")) {
      result = "byte";
    } else if (code.equals("C")) {
      result = "char";
    } else if (code.equals("D")) {
      result = "double";
    } else if (code.equals("F")) {
      result = "float";
    } else if (code.equals("I")) {
      result = "int";
    } else if (code.equals("J")) {
      result = "long";
    } else if (code.equals("S")) {
      result = "short";
    } else if (code.equals("V")) {
      result = "void";
    } else {
      throw new RuntimeException("value unknown!");
    }
    return result;
  }


}
