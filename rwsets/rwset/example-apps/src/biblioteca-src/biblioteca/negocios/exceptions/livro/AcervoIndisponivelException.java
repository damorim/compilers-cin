package biblioteca.negocios.exceptions.livro;

public class AcervoIndisponivelException extends Exception {

	public AcervoIndisponivelException(String codigo) {
		super("O livro [" + codigo + "] n�o est� dispon�vel no acervo.");
	}
}