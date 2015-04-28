package ast;

public class ArrayIndexing extends Expression {

    private Expression e1, e2;

    public ArrayIndexing(Expression e1, Expression e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    public Expression getE1() {
        return e1;
    }

    public Expression getE2() {
        return e2;
    }
    
		
}
