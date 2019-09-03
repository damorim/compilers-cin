import org.junit.Test;
import org.junit.Assert;

public class BasicTest {

    @Test
    public void testOne() {
        // a = b
        java.util.List tokens = new java.util.ArrayList();
        tokens.add(new TokenId("a"));
        tokens.add(Constants.EQUALS);
        tokens.add(new TokenId("b"));
        Parser p = new Parser(tokens);
        p.stmt();
        Assert.assertEquals(p.lookahead, tokens.size());
    }

    @Test
    public void testTwo() {
        // a = 10
        java.util.List tokens2 = new java.util.ArrayList();
        tokens2.add(new TokenId("a"));
        tokens2.add(Constants.EQUALS);
        tokens2.add(new TokenNum("10"));
        Parser p = new Parser(tokens2);
        p.stmt();
        Assert.assertEquals(p.lookahead, tokens2.size());
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
        Parser p = new Parser(tokens3);
        p.stmt();
        Assert.assertEquals(p.lookahead, tokens3.size());
    }

    @Test
    public void testIf() {
        // if ( a ) then  a = a + 1
        java.util.List tokens3 = new java.util.ArrayList();
        tokens3.add(Constants.IF);
        tokens3.add(Constants.OPEN_PAR);
        tokens3.add(new TokenId("a"));
        tokens3.add(Constants.CLOSE_PAR);
        tokens3.add(Constants.THEN);
        tokens3.add(new TokenId("a"));
        tokens3.add(Constants.EQUALS);
        tokens3.add(new TokenNum("a"));
        tokens3.add(new TokenOp("+"));        
        tokens3.add(new TokenNum("1"));
        Parser p = new Parser(tokens3);
        p.stmt();
        Assert.assertEquals(p.lookahead, tokens3.size());        
    }

    @Test(expected=java.lang.RuntimeException.class)
    public void testFour() {
        // x + +
        java.util.List tokens3 = new java.util.ArrayList();
        tokens3.add(new TokenId("x"));
        tokens3.add(new TokenOp("+"));
        tokens3.add(new TokenOp("+"));        
        Parser p = new Parser(tokens3);
        p.stmt(); // should throw Syntax Error
    }
    
}
