public class VisitorAdaptor implements Visitor {

    public void visit(Expr exp) {
        if (exp instanceof LiteralExpr) {
            visit((LiteralExpr)exp);
        } else if (exp instanceof BinaryExpr) {
            visit((BinaryExpr)exp);
        } else {
            throw new RuntimeException("MISSING CASE");
        }
    }

    public void visit(BinaryExpr exp) {
        this.visit(exp.arg1);
        this.visit(exp.op);
        this.visit(exp.arg2);
    }
    
    public void visit(LiteralExpr exp) {}

    public void visit(String op) {}
}
