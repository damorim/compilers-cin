public class PrinterInfix extends VisitorAdaptor {

    public void visit(LiteralExpr exp) {
        System.out.print(exp.cte);
    }

    public void visit(String op) {
        System.out.print(op);
    }
    
}