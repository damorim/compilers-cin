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
        return tokens.get(lookahead).type().equals(tokenType);
    }

    void match(String tokenType) {
        Token t = tokens.get(lookahead);
        if (t.type().equals(tokenType)) {
            lookahead++;
        } else {
            throw new RuntimeException("Syntax Error!");            
        }
    }

    void expr() {
        match("id");
        if (is("op")) {
            match("op");
            expr();
        }
    }

    void stmt() {
        if (is("if")) {
            match("if"); match("op_par"); expr(); match("cl_par"); match("then"); stmt();            
        } else if (is("id")) {
            match("id"); match("="); expr(); 
        } else {
            throw new RuntimeException("Syntax Error!");
        }
    }
}
