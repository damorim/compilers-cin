public class BinaryExpr implements Expr {
    String op;
    Expr arg1, arg2;

    public BinaryExpr(String op, Expr arg1, Expr arg2) {
        this.op = op;
        this.arg1 = arg1;
        this.arg2 = arg2;
    }
    
    public void accept(Visitor vis) {
        vis.visit(this);
    }
}
