package foo;

public class FooArray {
  String[] s = new String[]{"noise"};
  String[] t = new String[]{"hello", "everyone"};    
  void read() {
    t[1] = t[1] + "!"; 
  }
}