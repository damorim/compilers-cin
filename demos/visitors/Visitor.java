public interface Visitor {
    void visit(Expr exp);    
    void visit(BinaryExpr exp);
    void visit(LiteralExpr exp);
    void visit(String op); // operation
}
