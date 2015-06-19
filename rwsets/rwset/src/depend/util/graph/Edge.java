package depend.util.graph;

import com.ibm.wala.classLoader.IField;
import com.ibm.wala.classLoader.IMethod;

public class Edge {

  // writer of data
  IMethod writer;
  int writerLine;
  
  // reader of data
  IMethod reader;
  int readerLine;
  
  // field (can be declared in a third method/class) 
  IField ifield;
  
  boolean direct;

  public Edge(
      IMethod writer, 
      int writerLine, 
      IMethod reader, 
      int readerLine,
      IField ifield) {
    this(writer, writerLine, reader, readerLine, ifield, true);
  }

  public Edge(IMethod writer, int writerLine, IMethod reader, int readerLine,
      IField ifield, boolean direct) {
    super();
    this.writer = writer;
    this.writerLine = writerLine;
    this.reader = reader;
    this.readerLine = readerLine;
    this.ifield = ifield;
    this.direct = direct;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (direct ? 1231 : 1237);
    result = prime * result + ((ifield == null) ? 0 : ifield.hashCode());
    result = prime * result + ((reader == null) ? 0 : reader.hashCode());
    result = prime * result + ((writer == null) ? 0 : writer.hashCode());
    result = prime * result + writerLine;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Edge other = (Edge) obj;
    if (direct != other.direct)
      return false;
    if (ifield == null) {
      if (other.ifield != null)
        return false;
    } else if (!ifield.equals(other.ifield))
      return false;
    if (reader == null) {
      if (other.reader != null)
        return false;
    } else if (!reader.equals(other.reader))
      return false;
    if (writer == null) {
      if (other.writer != null)
        return false;
    } else if (!writer.equals(other.writer))
      return false;
    if (writerLine != other.writerLine)
      return false;
    return true;
  }

}