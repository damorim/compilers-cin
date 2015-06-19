package depend;

import java.util.Set;

import com.ibm.wala.classLoader.IField;
import com.ibm.wala.classLoader.IMethod;

/****
 * 
 * auxiliary class denoting a pair of sets (of field references):
 * one to characterize the field reads of one method and
 * another to characterize the field writes of one method
 * 
 *
 ***/
public class RWSet {
  
  public static AccessInfo makeAccessInfo( 
      IMethod accessMethod, 
      int accessLineNumber, 
      IField ifield) {    
    return new AccessInfo(accessMethod.getDeclaringClass(), accessMethod, accessLineNumber, ifield);
  }
  
  protected Set<AccessInfo> readSet, writeSet;
  
  public RWSet(Set<AccessInfo> readSet, Set<AccessInfo> writeSet) {
    super();
    this.readSet = readSet;
    this.writeSet = writeSet;
  }

  public String toString() {
    StringBuffer sb = new StringBuffer();
    sb.append("READS FROM:");
    sb.append("\n");
    for (AccessInfo readAccessInfo: readSet) {
      sb.append(readAccessInfo.toString() + "\n");
    }
    sb.append("\n");
    sb.append("WRITES TO:");
    sb.append("\n");
    for (AccessInfo writeAccessInfo: writeSet) {
      sb.append(writeAccessInfo.toString() + "\n");
    }
    return sb.toString();
  }
}