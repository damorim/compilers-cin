package foo;

public class FooFlowSensitivity {

  int x, y, z;
  static int w;
  int[] o = new int[5];

  public void read(){
    int a =  bar();
    int b = x;
    foo();
    if(a + b + w > 20){
      foobar();
      int c = z;
      b = c;
    }
  }

  public void read1(){
    int a =  w;
    foo();
    if(a > 20){
      foobar();
    }
  }

  public void readLoop(){
    int a = x;
    while(a > 0){
      bar();
      foobar();
    }
  }

  public void readNonDependencyInFlow(){
    int a = w;
    int c = a+20; // If we pick this line it will also add "x" in the accumulated read set for this line!
    int b = x; 
  }

  public void readSimpleReads(){
    int a = x;
    int b = y;
    int c = z;
  }

  public void readArray(){
    int a = x;
    int b = y;
    int d = o[1];
    if(d > 10){
      int c = z;
    }
  }

  public void readSingleLineMultipleBlocks(){
    int a = x;
    int b = a > 10 ? y : z; // This line has 4 (!) basic blocks
    if(b > 30){
      foo();
    }
  }
  
  private void foobar() {
    w = 10;
    x = 20;
    y = 40;
    z = 30;
    o[1] = 50;
  }

  private void foo() {
    w = x + y;
  }

  private int bar() {
    return y;
  }
}
