package escola.classesBase;

public abstract class Empregado extends Pessoa {
	private String funcao;
	private double salario;
	//private boolean[][] frequencia;
	public Empregado(String cpf, String nome, String dataNasc, String identidade, String sexo,
			String telefone, Endereco endereco, String funcao) {
		super(cpf, nome, dataNasc, identidade, sexo, telefone, endereco);
		this.funcao = funcao;
		this.salario = 0.0;
		//frequencia = new boolean[12][31];
	}
	public String getFuncao() {
		return funcao;
	}
	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}
public String resumo(){
	String resumo = super.resumo();
	resumo+="\nFuncao: " +this.funcao;
	return resumo;
}
	
}
