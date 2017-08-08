public class LiteralExpr implements Expr {
    int cte;
    
    public LiteralExpr(int cte) {
        this.cte = cte;
    }

    public void accept(Visitor vis) {
        vis.visit(this);
    }    

}
