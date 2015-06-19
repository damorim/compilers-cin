package depend.util.graph;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import com.ibm.wala.classLoader.IMethod;
import com.ibm.wala.types.Descriptor;
import com.ibm.wala.types.TypeName;
import com.ibm.wala.types.TypeReference;

public class SimpleGraph {

  private Set<Edge> edges = new HashSet<Edge>();

  private IMethod targetMethod;

  public String toDotString() {
    StringBuffer sb = new StringBuffer();
    sb.append("digraph \"DirectedGraph\" {\n");
    sb.append(" graph [concentrate = true];\n");
    sb.append(" center=true;\n");
    sb.append(" fontsize=6;\n");
    sb.append(" node [ color=blue,shape=\"box\"fontsize=6,fontcolor=black,fontname=Arial];\n");
    sb.append(" edge [ color=black,fontsize=6,fontcolor=black,fontname=Arial];\n");
    if(this.getTargetMethod() != null){
      sb.append("\"").append(toString(this.getTargetMethod())).append("\"");
      sb.append("[").append("color=\"red\", fontsize=\"6\", fontname=\"Arial\"").append("];").append("\n");
    }
    sb.append(toString());
    sb.append("}\n");
    return sb.toString();
  }

  @Override
  public String toString() {
    
    String[] strEdgesAr = new String[edges.size()];

    int i = 0;
    for (Edge edge : edges) {
      StringBuffer sb = new StringBuffer();
      sb.append("\"");
      sb.append(toString(edge.writer));
      sb.append("\"");

      sb.append(" -> ");

      sb.append("\"");
      sb.append(toString(edge.reader));
      sb.append("\"");

      sb.append(" [");
      sb.append("label=\"");

      sb.append(toString(edge.ifield.getFieldTypeReference().getName()));
      sb.append(" ");
      sb.append(toString(edge.ifield.getDeclaringClass().getName()));
      sb.append(".");
      sb.append(edge.ifield.getName());
      sb.append(" : ");
      sb.append(edge.writerLine);

      sb.append("\"");
      if(!edge.direct){
        sb.append(";").append("style=dashed");
        sb.append(";").append("arrowhead=\"none\"");
      }
      sb.append(" ]");
      
      // update array
      strEdgesAr[i++] = sb.toString();
    }
    
    // sorting array
    Arrays.sort(strEdgesAr, new Comparator<String>() {
      @Override
      public int compare(String o1, String o2) {
        return o1.compareTo(o2);
      }
    });
    
    StringBuffer result = new StringBuffer();    
    for (String s : strEdgesAr) {
      result.append(s);
      result.append("\n");
    }
    
    return result.toString();
  }
  
  private Object toString(IMethod meth) {
    StringBuffer sb = new StringBuffer();
    Descriptor desc = meth.getDescriptor();
    sb.append(toString(desc.getReturnType()));
    sb.append(" ");
    sb.append(toString(meth.getDeclaringClass().getReference().getName()));
    sb.append(".");
    sb.append(meth.getName());
    sb.append("(");
    TypeName[] paramTypes = desc.getParameters(); 
    for (int i = 0 ; i < desc.getNumberOfParameters(); i++) {
      TypeName tn = paramTypes[i];
      sb.append(toString(tn));
      if (i < desc.getNumberOfParameters() - 1) {
        sb.append(",");
      }
    }
    sb.append(")");    
    return sb.toString();
  }

  private String toString(TypeName tn) {
    String result;
    if (tn.isPrimitiveType()) {
      if (tn == TypeReference.BooleanName) {
        result = "boolean";
      } else if (tn == TypeReference.ByteName) {
        result = "byte";
      } else if (tn == TypeReference.CharName) {
        result = "char";
      } else if (tn == TypeReference.DoubleName) {
        result = "double";
      } else if (tn == TypeReference.FloatName) {
        result = "float";
      } else if (tn == TypeReference.IntName) {
        result = "int";
      } else if (tn == TypeReference.LongName) {
        result = "long";
      } else if (tn == TypeReference.ShortName) {
        result = "short";
      } else if (tn == TypeReference.VoidName) {
        result = "void";
      } else {
        throw new UnsupportedOperationException();
      }
    } else {
      result = tn.toString().substring(1);
    } 
    return result;
  }

  public void add(Edge edge) {
    edges.add(edge);
  }

  public IMethod getTargetMethod() {
    return targetMethod;
  }

  public void setTargetMethod(IMethod targetMethod) {
    this.targetMethod = targetMethod;
  }

}