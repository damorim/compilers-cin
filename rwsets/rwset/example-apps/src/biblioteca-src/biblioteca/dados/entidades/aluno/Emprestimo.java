package biblioteca.dados.entidades.aluno;


public class Emprestimo {

	private String codigo;
	private String tipo;
	private String dataDevolucao;
	private String dataEmprestimo;
	private String cpfResponsavel; // atributo usado para a lista dos livros

	public Emprestimo(String codigo, String tipo, String dataDevolucao,
			String dataEmprestimo) {
		this.codigo = codigo;
		this.tipo = tipo;
		this.dataDevolucao = dataDevolucao;
		this.dataEmprestimo = dataEmprestimo;
		this.cpfResponsavel = null;
	}

	public Emprestimo(String codigo, String dataDevolucao, String dataEmprestimo) {
		this.codigo = codigo;
		this.tipo = null;
		this.dataDevolucao = dataDevolucao;
		this.dataEmprestimo = dataEmprestimo;
		this.cpfResponsavel = null;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public String getTipo() {
		return this.tipo;
	}

	public String getDataDevolucao() {
		return dataDevolucao;
	}

	public String getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setCpfResponsavel(String cpfResponsavel) {
		this.cpfResponsavel = cpfResponsavel;
	}

	public String getCpfResponsavel() {
		return this.cpfResponsavel;
	}

	// ----------------------------------

	// ----------------------------------

	public String toString() {
		String message = "";

		message += formatSaida("C�digo:") + this.codigo + "\n";
		if (tipo != null) {
			message += formatSaida("Tipo:") + this.tipo + "\n";
		}
		message += formatSaida("Data Emprestimo:") + this.dataEmprestimo + "\n";
		message += formatSaida("Data Devolucao:") + this.dataDevolucao + "\n";

		return message;
	}
	
	
	
	public String colocarPlanilha(){
		String resposta = this.codigo +"/"+this.tipo+"/"+this.dataEmprestimo+"/"+this.dataDevolucao;
		return resposta;
	}
	
	
	

	private static String formatSaida(String info) {
		String aux = info;
		int dif = 16 - info.length();

		if (dif >= 0) {
			for (int i = 0; i <= dif; i++) {
				aux += " ";
			}
		} else {
			aux = aux.substring(0, 15) + ": ";
		}

		return aux;
	}
}
