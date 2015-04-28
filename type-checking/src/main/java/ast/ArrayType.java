package ast;

public class ArrayType extends Type {

    private int size;

    private Type type;

    public ArrayType(int i, IntType intType) {
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
