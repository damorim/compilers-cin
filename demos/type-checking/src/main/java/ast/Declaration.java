package ast;

import ast.visitors.Visitor;

public class Declaration implements Node {

    private Id id;
    private Type type;

    public Declaration(Id x, Type arg) {
        this.id = x;
        this.type = arg;
    }
    
    public void accept(Visitor vis) {
        vis.visit(this);
    }

    public Id getID() {
        return id;
    }

    public Type getType() {
        return type;
    }
}