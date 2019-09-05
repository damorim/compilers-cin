import java.util.Stack;

public class CalculatorVisitor extends VisitorAdaptor {

    Stack<Integer> st = new Stack<Integer>();

    public void visit(BinaryExpr exp) {
        this.visit(exp.arg1);
        this.visit(exp.arg2);
        
        String op = exp.op;
        int a = st.pop();
        int b = st.pop();
        int c;
        if (op.equals("+")) {
            c = a + b;
        } else if (op.equals("*")) {
            c = a * b;
        } else {
            throw new RuntimeException("check this case, please.");
        }
        
        st.push(c);
    }

    public void visit(LiteralExpr exp) {
        st.push(exp.cte);
    }    

    
}
