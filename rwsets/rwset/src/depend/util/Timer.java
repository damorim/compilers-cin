package depend.util;

public class Timer {
  
  private long now;
  private long then;
  
  public void start() {
    then = System.currentTimeMillis();
  }
  
  public void stop() {
    now = System.currentTimeMillis();
  }
  
  public void show(String string) {
    if (now <= then) {
      throw new RuntimeException("Please, use the Timer class properly");
    }
    System.out.printf("%s: %dms\n", string, now - then);
    then = now = System.currentTimeMillis(); // invalidate;
  }

}
