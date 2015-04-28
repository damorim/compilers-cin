package ast.visitors;

import org.junit.Test;
import org.junit.Assert;
import ast.visitors.TypeCheckerVisitor;
import ast.visitors.TypeError;
import ast.Program;
import ast.ASTSamples;

public class TypeCheckerVisitorTest {

    @Test
    public void arrayIndexing() {
        Program p = (Program) ASTSamples.sample2();
        TypeCheckerVisitor vis = new TypeCheckerVisitor();
        p.accept(vis);
    }

    @Test(expected=TypeError.class)
    public void arrayIndexingExceptional() {
        Program p = (Program) ASTSamples.sample3();
        TypeCheckerVisitor vis = new TypeCheckerVisitor();
        p.accept(vis);
    }
    
    @Test
    public void mod() {
        Program p = (Program) ASTSamples.sample4();
        TypeCheckerVisitor vis = new TypeCheckerVisitor();
        p.accept(vis);
    }
    
    @Test(expected=TypeError.class)
    public void modExceptional() {
        Program p = (Program) ASTSamples.sample5();
        TypeCheckerVisitor vis = new TypeCheckerVisitor();
        p.accept(vis);
    }

    @Test(expected=TypeError.class)
    public void nonIntIndex() {
        Program p = (Program) ASTSamples.sample6();
        TypeCheckerVisitor vis = new TypeCheckerVisitor();
        p.accept(vis);
    }
}