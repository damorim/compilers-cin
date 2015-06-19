package escola.classesBase;

public class Aluno extends Pessoa {
	private String pai;
	private String mae;
	private String numeroMatricula;
	private Turma turma;
	private Boletim boletim;

	public Boletim getBoletim() {
		return boletim;
	}

	public void setBoletim(Boletim boletim) {
		this.boletim = boletim;
	}


	public Aluno(String cpf, String nome, String dataNasc, String rg, String sexo,String telefone,
			Endereco endereco, String pai, String mae, Turma turma) {
		super(cpf, nome, dataNasc, rg, sexo, telefone, endereco);
		this.pai = pai;
		this.mae = mae;
		this.turma = turma;

		// gerando um numero aleatorio pro numeroMatricula pra nao precisar
		// pegar do usuario esse valor
		String x = (cpf.substring(0, 4));
		x += (rg.substring(0, 3));
		this.numeroMatricula = x;

		this.boletim = new Boletim(); // desse jeito passa pro
														// boletim do cara as
														// disciplinas que estao
														// definidas na turma

	}

	public String getPai() {
		return pai;
	}

	public void setPai(String pai) {
		this.pai = pai;
	}

	public String getMae() {
		return mae;
	}

	public void setMae(String mae) {
		this.mae = mae;
	}

	public String getNumeroMatricula() {
		return numeroMatricula;
	}

	public void setNumeroMatricula(String numeroMatricula) {
		this.numeroMatricula = numeroMatricula;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public String imprimirBoletim() {
		return boletim.imprimir();
	}
	public String resumo(){
		String resumo = super.resumo();
		resumo+="\nNome do Pai: "+ pai+"\nNome do Mae: "+ mae+"\nTurma :"+turma;
		return resumo;
	}

}
