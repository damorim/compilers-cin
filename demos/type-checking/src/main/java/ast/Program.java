package ast;

import ast.visitors.Visitor;

public class Program implements Node {

    private Declaration d;
    private Expression e;

    public Program(Declaration d1, Expression x) {
        this.d = d1;
        this.e = x;
    }

    public void accept(Visitor vis) {
        vis.visit(this);
    }

    public Declaration getDeclaration() {
        return d;
    }

    public Expression getExpression() {
        return e;
    }

}
