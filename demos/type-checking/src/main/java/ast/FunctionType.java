package ast;

public class FunctionType extends Type {

    Type t1, t2;

    FunctionType(Type t1, Type t2) {
        this.t1 = t1;
        this.t2 = t2;
    }

    @Override
    public String toString() {
        return String.format("%s -> %s", t1, t2);
    }    
}
