package examples;

public class Foo {
  
  public static void main(String[] args) {
    bar();
  }
  
  static void bar() {
    throw new RuntimeException();
  }

}
