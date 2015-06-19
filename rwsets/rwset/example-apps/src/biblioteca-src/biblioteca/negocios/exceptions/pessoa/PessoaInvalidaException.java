package biblioteca.negocios.exceptions.pessoa;

public class PessoaInvalidaException extends Exception {

	public PessoaInvalidaException(String cpf) {
		super("A pessoa [" + cpf
				+ "] n�o tem permiss�o para realizar esta a��o.");
	}

	public PessoaInvalidaException(String cpf, char tipoExcecao) {
		super("A pessoa [" + cpf + "] n�o pode ser excluida.");
	}

}