package biblioteca.negocios.exceptions.pessoa;

public class PessoaNaoEncontradaException extends Exception {

	private String cpf;

	public PessoaNaoEncontradaException(String cpf) {
		super("A pessoa " + cpf + " n�o est� cadastrada no sistema.");
		this.cpf = cpf;
	}

	public String getCpf() {
		return this.cpf;
	}

}