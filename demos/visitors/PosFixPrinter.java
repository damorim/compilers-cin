public class PosFixPrinter implements Visitor {

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
        this.visit(exp.arg2);
        System.out.print(exp.op);
    }
    
    public void visit(LiteralExpr exp) {
        System.out.print(exp.cte);        
    }
}
