package foo;

public class B {
  
  int z;
  
  static float w;
  
  static {
    w = 10;
  }
  
  public void n(A a, C c) {
    
    z = a.x;
    
    n_(a, c);
    
  }

  private void n_(A a, C c) {
    if (a.y + a.z > c.y + w) {
      System.out.println("HELLO");
    }
  }

}
