import japa.parser.ASTHelper;
import japa.parser.JavaParser;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.PackageDeclaration;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.ModifierSet;
import japa.parser.ast.body.Parameter;
import japa.parser.ast.expr.FieldAccessExpr;
import japa.parser.ast.expr.MethodCallExpr;
import japa.parser.ast.expr.NameExpr;
import japa.parser.ast.expr.StringLiteralExpr;
import japa.parser.ast.stmt.BlockStmt;
import japa.parser.ast.visitor.VoidVisitorAdapter;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;


public class ParserExample {

    public void printCompilationUnit() throws Exception{
	FileInputStream in = new FileInputStream("src/examples/VisitorExample.java");
	CompilationUnit compUnit;
	try {
	    compUnit = JavaParser.parse(in);
		} finally {
	    in.close();
	}
	
	System.out.println(compUnit.toString());
    }
    
    public void listMethods() throws Exception {
	class MethodVisitor extends VoidVisitorAdapter<List<String>> {
	    @Override
		public void visit(MethodDeclaration n, List<String> names) {
		names.add(n.getName());
	    }
	}
	
	FileInputStream in = new FileInputStream("src/examples/VisitorExample.java");
	CompilationUnit compUnit;
	List<String> names = new ArrayList<String>();
	try {
	    compUnit = JavaParser.parse(in);
	    new MethodVisitor().visit(compUnit, names);
	} finally {
	    in.close();
	}
	
	System.out.println(names.toString());
	
    }
    
    public void buildUnit() {
	CompilationUnit compUnit = new CompilationUnit();
	
	compUnit.setPackage(new PackageDeclaration(ASTHelper
						   .createNameExpr("compiladores.aula")));
	
	// create the type declaration
	ClassOrInterfaceDeclaration type = new ClassOrInterfaceDeclaration(
									   ModifierSet.PUBLIC, false, "AulaJavaParser");
	ASTHelper.addTypeDeclaration(compUnit, type);
	
	// create a method
	MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC,
							 ASTHelper.VOID_TYPE, "main");
	method.setModifiers(ModifierSet.addModifier(method.getModifiers(),
						    ModifierSet.STATIC));
	ASTHelper.addMember(type, method);
	
	// add a parameter to the method
	Parameter param = ASTHelper.createParameter(
						    ASTHelper.createReferenceType("String", 0), "args");
	param.setVarArgs(true); //makes the parameter a ",,," array
	ASTHelper.addParameter(method, param);
	
	// add a body to the method
	BlockStmt block = new BlockStmt();
	method.setBody(block);
	
	// add a statement do the method body
	NameExpr clazz = new NameExpr("System");
	FieldAccessExpr field = new FieldAccessExpr(clazz, "out");
	MethodCallExpr call = new MethodCallExpr(field, "println");
	ASTHelper.addArgument(call, new StringLiteralExpr("Hello World!"));
	ASTHelper.addStmt(block, call);
	
	System.out.println(compUnit.toString());
    }
}
