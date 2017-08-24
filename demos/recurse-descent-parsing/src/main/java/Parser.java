import java.util.List;

public class Parser {

    List<Token> tokens;
    int lookahead;

    // In practice, tokens are sent to the parser one-by-one as
    // opposed to all-at-once
    Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    boolean is(String tokenType) {
        boolean result;
        try {
            result = tokens.get(lookahead).type().equals(tokenType);
        } catch (Exception exception) {
            result = false;
        }
        return result;
    }

    void match(String tokenType) {
        Token t = tokens.get(lookahead);
        if (t.type().equals(tokenType)) {
            lookahead++;
        } else {
            throw new RuntimeException("Syntax Error!");            
        }
    }

    /** Grammar representation **/

    /**
     * (1) id op expr()
     * (2) num
     **/    
    void expr() {
        if (is("id")) {
            match("id");
            if (is("op")) {
                match("op");
                expr();
            }            
        } else if (is("num")) {
            match("num");
            if (is("op")) {
                match("op");
                expr();
            }                     
        } else {
            throw new RuntimeException("Syntax Error!");
        }

    }

    /**
     * (1) if ( expr() ) then stmt()
     * (2) id = expr()
     **/
    void stmt() {
        if (is("if")) {
            match("if"); match("op_par"); expr(); match("cl_par"); match("then"); stmt();                   } else if (is("id")) {
            match("id"); match("="); expr(); 
        } else {
            throw new RuntimeException("Syntax Error!");
        }
    }
}
