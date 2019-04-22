package ast.visitors;

import java.util.*;
import ast.*;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class TypeCheckerVisitor extends VisitorAdaptor { 

    // This data structure associates types to expressions
    private Map<Expression, String> symbolTable = new HashMap<Expression, String>();

    @Override
    public void visit(Declaration decl) { // id : integer
        super.visit(decl);
        symbolTable.put(decl.getID(), decl.getType().toString());
    }

    @Override
    public void visit(Id exp) { //TODO: check if needed
        symbolTable.put(exp, symbolTable.get(exp));
    }
    
    @Override
    public void visit(Num exp) { 
        symbolTable.put(exp, "int"); // should add Num(5) -> "int" to symbol table
    }
    
    @Override
    public void visit(Mod exp) {
        /**
         * em "e1 mod e2", e1 e e2 precisam ser inteiros
         **/
        super.visit(exp);
        // checagem de tipos
        Expression e1 = exp.getE1();
        Expression e2 = exp.getE2();
        check(symbolTable.get(e1), "int");
        check(symbolTable.get(e2), "int");
        // atualizacao tabela de simbolos
        symbolTable.put(exp, "int");
    }
    
    @Override
    public void visit(ArrayIndexing aiExp){ // e1[e2]
        super.visit(aiExp);
        Expression e1 = aiExp.getE1();
        Expression e2 = aiExp.getE2();

        // checagem de tipo
        // em e1[e2]:
        //   e1 precisa ser array e
        //   e2 precisa ser inteiro
        //   se e2 eh uma constante entao val(e2) deve estar no range de type(e1)

        String arType = symbolTable.get(e1);
        check(arType, "array ");
        check(symbolTable.get(e2), "int");

        // identificando partes relevantes a partir da string do array
        String regex = "array \\[(.*?)\\] of (.*)";
        Pattern pattern = Pattern.compile(regex);        
        Matcher matcher = pattern.matcher(arType);
        int val1 = -1;
        String arrayType = "";
        if (matcher.matches()) {
            val1 = Integer.parseInt(matcher.group(1));
            arrayType = matcher.group(2);
        } else {
            throw new RuntimeException("Could not identify type");
        }

        if (e2 instanceof Num) {
            int val = ((Num) e2).getVal();
            if (val < 0 || val >= val1) {
                throw new TypeError();
            }
        }

        // atualizando tabela de simbolos
        symbolTable.put(aiExp, arrayType);
    }
    
    @Override
    public void visit(Literal exp) { // '5' -> "char"
        symbolTable.put(exp, "char");
    }

    @Override
    public void visit(FunctionCall exp) { // E1(E2)
        super.visit(exp);
        Expression e1 = exp.getE1();
        Expression e2 = exp.getE2();

        // checagem de tipos
        // em e1(e2): 
        //   e1 precisa ser do tipo ->(t1,t2)
        //   e2 precisa ser do tipo t1
        String type = symbolTable.get(e1);
        check(type, "->"); // verifica se e1 eh funcao
        String t1 = type.substring(type.indexOf("(")+1, type.indexOf(",")).trim();
        String t2 = type.substring(type.indexOf(",")+1, type.indexOf(")")).trim();
        check(symbolTable.get(e2), t1); // verifica se e2 tem tipo consistente com e1

        // atualizacao da tabela de simbolos
        symbolTable.put(exp, t2);
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
        for (Map.Entry<Expression, String> entry : symbolTable.entrySet()) {
            System.out.printf("%s :: %s\n", entry.getKey().toString(), entry.getValue().toString());
        }
    }
    
}
