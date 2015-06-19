package depend.util;

public class ProgressMarker {

  private int total;
  private int count;

  ProgressMarker(int total) {
    this.total = total;
  }

  void move() {
    for (int i = 1; i <= 4; i++) {
      System.out.print('\b');  
    }
    if (count > total) {
      System.err.println("beyond limits");
    } else {
      System.out.printf("%3.0f%%", ((((double)++count)/total)*100));
    }
  }

  @SuppressWarnings("static-access")
  public static void main(String[] args) throws InterruptedException {
    ProgressMarker pm = new ProgressMarker(100);
    for (int i = 0; i < pm.total; i++) {
      Thread.currentThread().sleep(10);
      pm.move();
    }
  }


}
