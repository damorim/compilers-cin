package biblioteca.negocios.exceptions.livro;

public class LivroComEmprestimoException extends Exception {

	public LivroComEmprestimoException(String codigo) {
		super("N�o foi poss�vel excluir o livro [" + codigo
				+ "] porqu� ele est� emprestado.");
	}
}