public class Main {
    public static void main(String[] args) {
        // assume this is done by the parser
        Expr childExpr = new BinaryExpr("+", new LiteralExpr(5), new LiteralExpr(3));
        Expr rootExpr = new BinaryExpr("*", childExpr, new LiteralExpr(2));
        //        example_postfix(rootExpr);
        example_calculator(rootExpr);        
    }

    private static void example_postfix(Expr rootExpr) {
        Visitor vis = new PrinterPosfix();
        rootExpr.accept(vis);
    }

    private static void example_calculator(Expr rootExpr) {
        CalculatorVisitor vis = new CalculatorVisitor();
        rootExpr.accept(vis);
        System.out.println("--> "+vis.st.pop());
    }
    
}
