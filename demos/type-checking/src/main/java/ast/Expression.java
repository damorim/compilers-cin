package ast;

import ast.visitors.Visitor;

public class Expression implements Node {
    
    public void accept(Visitor vis) {
        vis.visit(this);
    }

}
