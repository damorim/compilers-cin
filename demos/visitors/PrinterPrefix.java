public class PrinterPrefix extends VisitorAdaptor {

    public void visit(LiteralExpr exp) {
        System.out.print(exp.cte);
    }

    public void visit(String op) {
        System.out.print(op);
    }


    public void visit(BinaryExpr exp) {
        this.visit(exp.op);
        System.out.print("(");
        this.visit(exp.arg1);
        System.out.print(",");        
        this.visit(exp.arg2);
        System.out.print(")");        
    }
    
}
