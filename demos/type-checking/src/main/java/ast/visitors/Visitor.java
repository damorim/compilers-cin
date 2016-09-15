package ast.visitors;

import ast.*;


public interface Visitor {

    public void visit(Program program);
    public void visit(Declaration declaration);

    // subtypes of Type
    public void visit(Type type);
    public void visit(ArrayType type);
    public void visit(CharType type);
    public void visit(IntType type);
    public void visit(FunctionType type);    

    // subtypes of Expression
    public void visit(Expression expression);
    public void visit(Id expression);
    public void visit(Mod expression);
    public void visit(Num expression);
    public void visit(ArrayIndexing expression);
    public void visit(Literal expression);
    public void visit(FunctionCall expression);    
}

