public class PrinterPosfix extends PrinterInfix {

    public void visit(BinaryExpr exp) {
        this.visit(exp.arg1);
        this.visit(exp.arg2);
        System.out.print(exp.op);
    }
    
}
