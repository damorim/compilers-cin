package ast.visitors;

import ast.*;

public class VisitorAdaptor implements Visitor {

    @Override
    public void visit(Program program) {
        program.getDeclaration().accept(this);
        program.getExpression().accept(this);
    }

    @Override
    public void visit(Declaration declaration) {
        declaration.getID().accept(this);
        declaration.getType().accept(this);
    }

    // subtypes of Type
    @Override
    public void visit(Type type) { }

    @Override
    public void visit(ArrayType type) {}

    @Override
    public void visit(CharType type) {}

    @Override
    public void visit(IntType type){}

    @Override
    public void visit(FunctionType type){}

    // subtypes of Expression    
    @Override
    public void visit(Expression expression) { 
        if (expression instanceof Id) {
            visit((Id) expression);
        } else if (expression instanceof Mod) {
            visit((Mod) expression);
        } else if (expression instanceof Num) {
            visit((Num) expression);
        } else if (expression instanceof ArrayIndexing) {
            visit((ArrayIndexing) expression);
        } else if (expression instanceof Literal) {
            visit((Literal) expression);
        } else if (expression instanceof FunctionCall) {
            visit((FunctionCall) expression);
        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public void visit(Id expression) {}

    @Override
    public void visit(Mod expression) {
        expression.getE1().accept(this);
        expression.getE2().accept(this);
    }

    @Override
    public void visit(Num expression) {}

    @Override
    public void visit(ArrayIndexing expression) {
        expression.getE1().accept(this);
        expression.getE2().accept(this);
    }

    @Override
    public void visit(Literal expression){}

    @Override
    public void visit(FunctionCall expression){
        expression.getE1().accept(this);
        expression.getE2().accept(this);        
    }    
    
}
