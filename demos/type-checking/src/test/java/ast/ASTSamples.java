package ast;

import ast.visitors.*;

public class ASTSamples {

    //   x : integer ; x mod 10
    public static Node sample1() {
        Declaration d = new Declaration(new Id("x"), new IntType());
        Expression e = new Mod(new Id("x"), new Num(10));
        return new Program(d, e);
    }

    //   x : array [1] of integer ; x[0]
    public static Node sample2() {
        Declaration d = new Declaration(new Id("x"), new ArrayType(1, new IntType()));
        Expression e = new ArrayIndexing(new Id("x"), new Num(0));
        return new Program(d, e);
    }
 
    //   x : integer ; x[0]
    public static Node sample3() {
        Declaration d = new Declaration(new Id("x"), new IntType());
        Expression e = new ArrayIndexing(new Id("x"), new Num(0));
        return new Program(d, e);
    }
   
    //   x : integer ; x mod 10
    public static Node sample4() {
        Declaration d = new Declaration(new Id("x"), new IntType());
        Expression e = new Mod(new Id("x"), new Num(10));
        return new Program(d, e);
    }
    
    //   x : integer ; x mod "a"
    public static Node sample5() {
        Declaration d = new Declaration(new Id("x"), new IntType());
        Expression e = new Mod(new Id("x"), new Literal('a'));
        return new Program(d, e);
    }

   //   x : array [1] of integer ; x["a"]
    public static Node sample6() {
        Declaration d = new Declaration(new Id("x"), new ArrayType(1, new IntType()));
        Expression e = new ArrayIndexing(new Id("x"), new Literal('a'));
        return new Program(d, e);
    }

    //   x : int -> char ; x(5)
    public static Node sample7() {
        Declaration d = new Declaration(new Id("x"), new FunctionType(new IntType(), new CharType()));
        Expression e = new FunctionCall(new Id("x"), new Num(5));
        return new Program(d, e);
    }

    //   x : char - int ; x(5)
    public static Node sample8() {
        Declaration d = new Declaration(new Id("x"), new FunctionType(new CharType(),new IntType()));
        Expression e = new FunctionCall(new Id("x"), new Num(5));
        return new Program(d, e);
    }


}
