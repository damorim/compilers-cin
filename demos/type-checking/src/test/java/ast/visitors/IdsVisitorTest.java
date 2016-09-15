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
    public void x() {
        Program p = (Program) ASTSamples.sample1();
        IdsVisitor vis = new IdsVisitor();
        p.accept(vis);
        Set<Id> ids = vis.ids;
        Assert.assertEquals(1, ids.size());
        Assert.assertEquals("x", ids.toArray()[0].toString());
    }

}

