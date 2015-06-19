package biblioteca.negocios.exceptions.livro;

public class LivroJaCadastradoException extends Exception {

	private String codigo;

	public LivroJaCadastradoException(String codigo) {
		super("O livro " + codigo + " j� est� cadastrado no sistema");
		this.codigo = codigo;
	}

	public String getCodigo() {
		return this.codigo;
	}

}