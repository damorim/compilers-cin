package ast.visitors;

import java.util.*;
import ast.*;

public class IdsVisitor extends VisitorAdaptor {

    public Set<Id> ids = new HashSet<Id>();
    
    @Override
    public void visit(Id id) {
        ids.add(id);
    }
    
}