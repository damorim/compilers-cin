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
        // e1 e e2 precisam ser inteiros em "e1 mod e2"
        check(expressionTypes.get(e1), "int");
        check(expressionTypes.get(e2), "int");
        expressionTypes.put(exp, "int");
    }
    
    @Override
    public void visit(ArrayIndexing aiExp){
        super.visit(aiExp);
        Expression e1 = aiExp.getE1();
        Expression e2 = aiExp.getE2();

        // e1 precisa ser array e e2 precisa sem inteiro em e1[e2]
        check(expressionTypes.get(e1), "array ");
        check(expressionTypes.get(e2), "int");

        //TODO: hardcoded update of aiExp
        // correto seria retornar T a partir de expressionTypes.get(e1): array [N] of T
        expressionTypes.put(aiExp, "int");
    }
    
    @Override
    public void visit(Literal exp) {
        expressionTypes.put(exp, "char");
    }

    @Override
    public void visit(FunctionCall exp) {
        super.visit(exp);
        Expression e1 = exp.getE1();
        Expression e2 = exp.getE2();
        String type = expressionTypes.get(e1); // T1 -> T2
        String t1 = type.substring(0, type.indexOf("-")).trim();
        String t2 = type.substring(type.indexOf(">")+1).trim();
        check(expressionTypes.get(e2), t1);
        expressionTypes.put(exp, t2);
    }    
    
    private static void check(String s1, String s2) {
        if (s1 == null) {
            throw new RuntimeException("Could not find type");
        } 
        if (!s1.startsWith(s2)) {
            throw new TypeError();
        }
    }

    private void printExpressionTypes() {
        System.out.println("expression types...");
        for (Map.Entry<Expression, String> entry : expressionTypes.entrySet()) {
            System.out.printf("%s :: %s\n", entry.getKey().toString(), entry.getValue().toString());
        }
    }
    
}
