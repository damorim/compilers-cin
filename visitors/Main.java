public class Main {
    public static void main(String[] args) {
        // assume this is done by the parser
        Expr expr = new BinaryExpr("+", new LiteralExpr(5), new LiteralExpr(3));
        expr.accept(new PosFixPrinter());
    }
}
