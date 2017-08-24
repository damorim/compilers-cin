import org.junit.Test;

public class BasicTest {

    @Test
    public void testOne() {
        // a = b
        java.util.List tokens = new java.util.ArrayList();
        tokens.add(new TokenId("a"));
        tokens.add(Constants.EQUALS);
        tokens.add(new TokenId("b"));
        (new Parser(tokens)).stmt();
    }

    @Test
    public void testTwo() {
        // a = 10
        java.util.List tokens2 = new java.util.ArrayList();
        tokens2.add(new TokenId("a"));
        tokens2.add(Constants.EQUALS);
        tokens2.add(new TokenNum("10"));
        (new Parser(tokens2)).stmt();        
    }

    @Test
    public void testThree() {
        // a = 10 + 5
        java.util.List tokens3 = new java.util.ArrayList();
        tokens3.add(new TokenId("a"));
        tokens3.add(Constants.EQUALS);
        tokens3.add(new TokenNum("10"));
        tokens3.add(new TokenOp("+"));        
        tokens3.add(new TokenNum("5"));        
        (new Parser(tokens3)).stmt();                        
    }
}
