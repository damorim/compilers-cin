/**
 * Exemplo de como um visitor funciona
 * @author mateus
 */

public class VisitorExample {
	
	interface ComputingVisitor {
		int visit(Expression e);
		int visit(PlusExpression e);
		int visit(MinusExpression e);
		int visit(IntegerTerminal e);
	}
	
	static abstract class Expression {
		abstract void accept(ComputingVisitor v);
	}
	
	static class PlusExpression extends Expression {
		Expression a;
		Expression b;
		
		public PlusExpression(Expression a, Expression b) {
			this.a = a; this.b = b;
		}
		
		void accept(ComputingVisitor v) {
			v.visit(this);
		}
	}
	
	static class MinusExpression extends Expression {
		Expression a;
		Expression b;
		
		public MinusExpression(Expression a, Expression b) {
			this.a = a; this.b = b;
		}
		
		void accept(ComputingVisitor v) {
			v.visit(this);
		}
	}
	
	static class IntegerTerminal extends Expression {
		int value;
		
		public IntegerTerminal(int value) {
			this.value = value;
		}
		
		void accept(ComputingVisitor v) {
			v.visit(this);
		}
	}

	static class ConcreteComputingVisitor implements ComputingVisitor {

		@Override
		public int visit(Expression e) {
			int ret;
			if(e instanceof PlusExpression) {
				ret = this.visit((PlusExpression) e);
			} else if (e instanceof MinusExpression) {
				ret = this.visit((MinusExpression) e);
			} else if (e instanceof IntegerTerminal) {
				ret = this.visit((IntegerTerminal) e);
			} else {
				throw new UnsupportedOperationException(e.getClass().toString());
			}
			
			return ret;
		}

		@Override
		public int visit(PlusExpression e) {
			int resultA = this.visit(e.a);
			int resultB = this.visit(e.b);
			return resultA + resultB;
		}

		@Override
		public int visit(MinusExpression e) {
			int resultA = this.visit(e.a);
			int resultB = this.visit(e.b);
			return resultA - resultB;
		}

		@Override
		public int visit(IntegerTerminal e) {
			return e.value;
		}
		
	}
	
	public static void main(String[] args) {
		//15 + 30
		Expression sum = new PlusExpression(new IntegerTerminal(15),
				new IntegerTerminal(30));
		//10 - 90
		Expression minus = new MinusExpression(new IntegerTerminal(10),
				new IntegerTerminal(90));
		//(15 + 30) + (10 - 90)
		Expression total = new PlusExpression(sum, minus);
		
		ComputingVisitor visitor = new ConcreteComputingVisitor();
				
		System.out.println(visitor.visit(total));
	}
	
	public int paraNoooooooooooooooooooosaAlegria() {
		return 0; 
	}
}
