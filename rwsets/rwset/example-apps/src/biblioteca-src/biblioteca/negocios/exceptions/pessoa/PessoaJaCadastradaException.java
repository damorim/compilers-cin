package biblioteca.negocios.exceptions.pessoa;

public class PessoaJaCadastradaException extends Exception {

	private String cpf;

	public PessoaJaCadastradaException(String cpf) {
		super("A pessoa " + cpf + " j� est� cadastrada no sistema.");
		this.cpf = cpf;
	}

	public String getCpf() {
		return this.cpf;
	}

}