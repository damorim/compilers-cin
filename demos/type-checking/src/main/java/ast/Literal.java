package ast;

public class Literal extends Expression {
    private char c;

    public Literal(char c) {
        this.c = c;
    }
}
