package ast;

public class ArrayType extends Type { 

    private int size;

    private Type type;

    public ArrayType(int i, IntType intType) { // int[5] => new ArrayType(5, new IntType())
        this.size = i;
        this.type = intType;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return String.format("array [%d] of %s", size, type.toString());
    }

}
