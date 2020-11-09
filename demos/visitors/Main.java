public class Main {
    public static void main(String[] args) {
        // assume this is done by the parser
        Expr childExpr = new BinaryExpr("+", new LiteralExpr(5), new LiteralExpr(3)); // 5 + 3
        Expr rootExpr = new BinaryExpr("*", childExpr, new LiteralExpr(2)); // (5 + 3) * 2
        example_infix(rootExpr);
        System.out.println();
        example_prefix(rootExpr);
        System.out.println();        
        example_postfix(rootExpr);
        System.out.println();        
        example_calculator(rootExpr);
    }

    // use case 1: calculator
    private static void example_calculator(Expr rootExpr) {
        CalculatorVisitor vis = new CalculatorVisitor();
        rootExpr.accept(vis);
        System.out.println("--> "+vis.st.pop());
    }
    

    // use case 2: postfix notation
    private static void example_postfix(Expr rootExpr) {
        Visitor vis = new PrinterPosfix();
        rootExpr.accept(vis);
    }

    // use case 3: prefix notation
    private static void example_prefix(Expr rootExpr) {
        Visitor vis = new PrinterPrefix();
        rootExpr.accept(vis);
    }

    // use case 4: infix notation
    private static void example_infix(Expr rootExpr) {
        Visitor vis = new PrinterInfix();
        rootExpr.accept(vis);
    }

    
    
}
