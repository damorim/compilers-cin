package escola.excecoes;


@SuppressWarnings("serial")
public class ElementoNaoEncontradoException extends Exception {
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	private String cpf;

	public ElementoNaoEncontradoException(String cpf) {
		super("Elemento nao encontrado.");
		this.cpf = cpf;
	}

	public ElementoNaoEncontradoException() {
		super("Elemento nao encontrado.");
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}
