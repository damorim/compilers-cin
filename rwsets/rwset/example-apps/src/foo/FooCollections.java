package foo;

import java.util.ArrayList;
import java.util.List;

public class FooCollections {
  List<String> s = new ArrayList<String>();
  List<String> t = new ArrayList<String>();     
  void read() {
    System.out.println(t.size());
  }
  void write() {
    t.add("Hello");
  }
  void read2() {
    System.out.println(s.size());
  }
  void write2() {
    s.add("Hello");
  }
}