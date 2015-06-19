package foo;

public class A {
  
  public int x;
  int y;
  int z;
  
  public void m() {
    x = 10;
//    l();
  }
  
  public void l() {
    x++;  // B.n() will not be dependent on A.l() if you comment this line 
//    o();
  }
  
  public void o() {
    if (x > 20) {
      y = 2;
    }
  }

}
