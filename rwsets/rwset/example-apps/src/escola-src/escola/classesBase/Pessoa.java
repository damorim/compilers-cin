package escola.classesBase;

public abstract class Pessoa {
	private String cpf;
	private String nome;
	private String telefone;
	private String dataNasc;
	private String rg;
	private String sexo;
	private Endereco endereco;

	public Pessoa(String cpf, String nome, String dataNasc, String rg,
			String sexo, String telefone, Endereco endereco) {
		this.cpf = cpf;
		this.nome = nome;
		this.telefone = telefone;
		this.dataNasc = dataNasc;
		this.rg = rg;
		this.sexo = sexo;
		this.endereco = endereco;
	}

	public String getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(String dataNasc) {
		this.dataNasc = dataNasc;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIdentidade() {
		return rg;
	}

	public void setIdentidade(String identidade) {
		this.rg = identidade;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String toString() {
		return this.nome + "  CPF: " + this.cpf;
	}

	public String resumo() {
		String resumo = "Nome: " + nome + "\nCPF: " + cpf + "\nRG: " + rg +"\nSexo: "+sexo+ "\nTelefone: "+telefone;
		return resumo;
	}
}
