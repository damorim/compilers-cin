package ast.visitors;

import ast.visitors.*;
import org.junit.Test;
import org.junit.Assert;
import java.util.Set;
import ast.Program;
import ast.Id;
import ast.ASTSamples;

public class IdsVisitorTest {

    @Test
    public void basicIdVisitorTest() {
        // build AST manually (could be done by a parser)
        Program p = (Program) ASTSamples.sample1();

        // build visitor and call accept
        IdsVisitor vis = new IdsVisitor();
        p.accept(vis);

        
        Set<Id> ids = vis.ids;
        Assert.assertEquals(1, ids.size());
        Assert.assertEquals("x", ids.toArray()[0].toString());
    }

}

