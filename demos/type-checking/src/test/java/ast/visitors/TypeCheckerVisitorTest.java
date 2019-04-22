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
    public void arrayIndexing2() {
        Program p = (Program) ASTSamples.sample10();
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

    @Test
    public void functionTest() {
        Program p = (Program) ASTSamples.sample7();
        TypeCheckerVisitor vis = new TypeCheckerVisitor();
        p.accept(vis);
    }

    @Test(expected=TypeError.class)
    public void functionBadParam() {
        Program p = (Program) ASTSamples.sample8();
        TypeCheckerVisitor vis = new TypeCheckerVisitor();
        p.accept(vis);
    }

    @Test(expected=TypeError.class)
    public void badIndex() {
        Program p = (Program) ASTSamples.sample9();
        TypeCheckerVisitor vis = new TypeCheckerVisitor();
        p.accept(vis);
    }        
    
}
