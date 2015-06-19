package biblioteca.dados.entidades;

import biblioteca.negocios.exceptions.sala.SalaIndisponivelException;

public class Sala {
	private String codigo;
	private int capacidade;
	private int totalEmprestimos;
	private boolean disponivel;
	private String dataDevolucao;
	private String cpfResponsavel;

	
	// Historico de utiliza��o

	public Sala(String codigo, int capacidade) {
		this.codigo = codigo;
		this.capacidade = capacidade;
		this.totalEmprestimos = 0;
		this.disponivel = true;
		this.dataDevolucao = null;
		this.cpfResponsavel = null;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(String dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public String getCpfResponsavel() {
		return cpfResponsavel;
	}

	public void setCpfResponsavel(String cpfResponsavel) {
		this.cpfResponsavel = cpfResponsavel;
	}

	public void setTotalEmprestimos(int totalEmprestimos) {
		this.totalEmprestimos = totalEmprestimos;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}

	public int getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}

	public int getTotalEmprestimos() {
		return totalEmprestimos;
	}

	// --------------------------------------------------------

	public boolean isDisponivel() {
		return this.disponivel;
	}

	public void emprestar(String dataDevolucao, String cpfResponsavel)
			throws SalaIndisponivelException {
		if (!isDisponivel()) {
			throw new SalaIndisponivelException(this.codigo);
		} else {
			this.disponivel = false;
			this.dataDevolucao = dataDevolucao;
			this.cpfResponsavel = cpfResponsavel;
			this.totalEmprestimos++;

		}
	}

	public void devolver() {
		this.disponivel = true;
	}

	// --------------------------------------------------------
	public String toString() {
		String message = "";

		message += formatSaida("C�digo:") + codigo + "\n";
		message += formatSaida("Capacidade:") + capacidade + "\n";
		message += formatSaida("Dispon�vel:");
		if (isDisponivel()) {
			message += "SIM\n";
		} else {
			message += "N�O\n";
			message += formatSaida("Data de Devolu��o:") + this.dataDevolucao
					+ "\n";
		}
		message += formatSaida("Total Emprestimos:") + totalEmprestimos;

		return message;
	}

	private static String formatSaida(String info) {
		String aux = info;
		int dif = 18 - info.length();

		if (dif >= 0) {
			for (int i = 0; i <= dif; i++) {
				aux += " ";
			}
		} else {
			aux = aux.substring(0, 17) + ": ";
		}

		return aux;
	}

}
