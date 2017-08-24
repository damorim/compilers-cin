import java.util.*;

public class Compiladores1EE2013 {
    
    static interface Expression {
	void accept(Visitor vis);
    }

    static enum Type {_Bool, _Int, _Real};

    static class VarDecl {
	Type t;
	List<Id> ids;
	VarDecl(Type t, List<Id> ids) {
	    this.t = t; this.ids = ids;
	}
	public void accept(Visitor vis) {
	    vis.visit(this);
	}
    }

    static class Id implements Expression {
	String name;
	Id(String name) {
	    this.name = name;
	}
	public void accept(Visitor vis) {
	    vis.visit(this);
	}
    }

    static interface Stmt {
	void accept(Visitor vis);
    }

    static class IfStmt implements Stmt {
	Expression bExp;
	List<Stmt> stmts;
	IfStmt(Expression bExp, List<Stmt> stmts) {
	    this.bExp = bExp; this.stmts = stmts;
	}
	public void accept(Visitor vis) {
	    vis.visit(this);
	}
    }

    static class Assign implements Stmt {
	Id id;
	Expression exp;
	Assign(Id id, Expression exp) {
	    this.id = id; this.exp = exp;
	}
	public void accept(Visitor vis) {
	    vis.visit(this);
	}
    }

    static class BinaryExpression implements Expression {
	Expression exp1, exp2;
	String op;
	BinaryExpression(Expression exp1, String op, Expression exp2) {
	    this.exp1 = exp1; this.exp2 = exp2; this.op = op;
	}
	public void accept(Visitor vis) {
	    vis.visit(this);
	}
    }

    static class Literal implements Expression {
	String val;
	Literal(String val) {
	    this.val = val;
	}
	public void accept(Visitor vis) {
	    vis.visit(this);
	}
    }
    
    static class Program {
	List<VarDecl> decls;
	List<Stmt> stmts;
	Program(List<VarDecl> decls, List<Stmt> stmts) {
	    this.decls = decls; this.stmts = stmts;
	}
	public void accept(Visitor vis) {
	    vis.visit(this);
	}
    }

    static interface Visitor {
	void visit(Program p);
	void visit(Expression e);
	void visit(Stmt stmt);
	void visit(VarDecl stmt);
    }

    static class TypeCheckerVisitor implements Visitor {
	Map<Id, Type> typeIds = new HashMap<Id, Type>();
	Map<Expression, Type> typeExps = new HashMap<Expression, Type>();
	public void visit(Program p) {
	    // p.decls
	    for (VarDecl decl : p.decls) {
		decl.accept(this);
	    }
	    // p.stmts
	    for (Stmt stmt : p.stmts) {
		stmt.accept(this);
	    }
	}
	public void visit(Expression e) {
	    if (e instanceof BinaryExpression) {
		BinaryExpression bExp = (BinaryExpression) e;
		bExp.exp1.accept(this);
		bExp.exp2.accept(this);
		Type t1 = typeExps.get(bExp.exp1);
		if (t1 != typeExps.get(bExp.exp2)) {
		    throw new RuntimeException("typeError");
		}
		String op = bExp.op;
		if (op.equals(">")) {
		    typeExps.put(e, Type._Bool);
		} else if (op.equals("+")) {
		    typeExps.put(e, t1);
		} else {
		    throw new UnsupportedOperationException();
		}
	    } else if (e instanceof Literal) {
		String lit = ((Literal) e).val;
		Type t = null;
		try {
		    Integer.parseInt(lit);
		    t = Type._Int;
		} catch (Exception e1) {
		    try {
			Double.parseDouble(lit);
			t = Type._Real;
		    } catch (Exception e2) {
			try {
			    Boolean.parseBoolean(lit);
			    t = Type._Bool;
			} catch (Exception e3) {
			}
		    }
		}
		if (t == null) {
		    throw new RuntimeException("invalid literal");
		}
		typeExps.put(e, t);
	    } else if (e instanceof Id) {
		typeExps.put(e, typeIds.get((Id)e));
	    } else {
		throw new UnsupportedOperationException();
	    }
	}
	public void visit(Stmt stmt) {
	    if (stmt instanceof Assign) {
		Assign assign = (Assign) stmt;
		//TODO: check if assign.id is declared
		assign.id.accept(this);
		assign.exp.accept(this);
		Type t1 = typeIds.get(assign.id);
		Type t2 = typeExps.get(assign.exp);
		if (t1 != t2) {
		    throw new RuntimeException("typeError");
		}
	    } else if (stmt instanceof IfStmt) {
		IfStmt ifStmt = (IfStmt) stmt;
		ifStmt.bExp.accept(this);
		// type checking
		if (typeExps.get(ifStmt.bExp) != Type._Bool) {
		    throw new RuntimeException("typeError");
		}
		for (Stmt st : ifStmt.stmts) {
		    st.accept(this);
		}
	    } else {
		throw new UnsupportedOperationException();
	    }
	}
	public void visit(VarDecl decl) {
	    Type t = decl.t;
	    for (Id id : decl.ids) {
		typeIds.put(id, t);
	    }
	}

	void dump() {
	    for (Map.Entry<Id, Type> entry : typeIds.entrySet()) {
		System.out.printf("id %s is of type %s\n", entry.getKey().name, entry.getValue());
	    }
	    System.out.println("Your program type checks!");
	}
    }


    public static void main(String[] args) {
	// construir um program valido 
        // para a gramatica (implicita) acima.
 
        // Exemplo:
        // int x, y;
        // 1: x = 10; 
        // 2: y = 20;
        // 3: if (x > 5) then x = x + y;

	List<VarDecl> decls = new ArrayList<VarDecl>();
	Type t = Type._Int;
	List<Id> ids = new ArrayList<Id>();
	Id id_x =new Id("x");
	ids.add(id_x);
	Id id_y = new Id("y");
	ids.add(id_y);
	VarDecl vardecl = new VarDecl(t, ids);
	decls.add(vardecl);
	List<Stmt> stmts = new ArrayList<Stmt>();
	Stmt stmt1 = new Assign(id_x, new Literal("10"));
	Stmt stmt2 = new Assign(id_y, new Literal("20"));
	Expression bExp = new BinaryExpression(id_x, ">", new Literal("5"));
	List<Stmt> block = new ArrayList<Stmt>();
	block.add(new Assign(id_x, new BinaryExpression(id_x, "+", id_y)));
	Stmt stmt3 = new IfStmt(bExp, block);
	stmts.add(stmt1);
	stmts.add(stmt2);
	stmts.add(stmt3);
	Program p = new Program(decls, stmts);

	// type-checking p...
	TypeCheckerVisitor typeChecker = new TypeCheckerVisitor();
	p.accept(typeChecker);
	typeChecker.dump();
    }
}