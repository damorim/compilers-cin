public class Main {
    public static void main(String[] args) {
        // assume this is done by the parser
        Expr childExpr = new BinaryExpr("+", new LiteralExpr(5), new LiteralExpr(3));
        Expr rootExpr = new BinaryExpr("*", childExpr, new LiteralExpr(2));
        rootExpr.accept(new PosFixPrinter());
    }
}
