package ast;

import ast.visitors.Visitor;

public class Type {
    
    public void accept(Visitor vis) {
        vis.visit(this);
    }

}
