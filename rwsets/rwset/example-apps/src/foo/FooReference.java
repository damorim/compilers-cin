package foo;

public class FooReference {
  String s = "NOISE";
  String t = "World";    
  void read() {
    System.out.println(t);
  }
  void write() {
    t = null; 
  }
}