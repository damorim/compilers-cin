package ast;

public class Id extends Expression {

    private String name;

    public Id(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }


    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj instanceof Id) {
            result = name.equals(((Id)obj).name);
        }
        return result;
    }

    public String getName() {
        return name;
    }
}
