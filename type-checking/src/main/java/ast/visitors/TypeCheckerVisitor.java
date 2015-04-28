package ast.visitors;

import java.util.*;
import ast.*;

public class TypeCheckerVisitor extends VisitorAdaptor {

    private Map<Id, String> symbolTable = new HashMap<Id, String>();
    private Map<Expression, String> expressionTypes = new HashMap<Expression, String>();

    @Override
    public void visit(Declaration decl) {
        super.visit(decl);
        symbolTable.put(decl.getID(), decl.getType().toString());
    }

    @Override
    public void visit(Id exp) {
        expressionTypes.put(exp, symbolTable.get(exp));
    }
    
    @Override
    public void visit(Num exp) {
        expressionTypes.put(exp, "int");
    }
    
    @Override
    public void visit(Mod exp) {
        super.visit(exp);
        Expression e1 = exp.getE1();
        Expression e2 = exp.getE2();
        check(expressionTypes.get(e1), "int");
        check(expressionTypes.get(e2), "int");
    }
    
    @Override
    public void visit(ArrayIndexing aiExp){
        super.visit(aiExp);
        Expression e1 = aiExp.getE1();
        Expression e2 = aiExp.getE2();

        check(expressionTypes.get(e1), "array ");
        check(expressionTypes.get(e2), "int");

        // hardcoded
        expressionTypes.put(aiExp, "int");
    }
    
    @Override
    public void visit(Literal exp) {
        expressionTypes.put(exp, "char");
    }
    
    private static void check(String s1, String s2) {
        if (s1 == null) {
            throw new RuntimeException("Could not find type");
        } 
        if (!s1.startsWith(s2)) {
            throw new TypeError();
        }
    }
    
}