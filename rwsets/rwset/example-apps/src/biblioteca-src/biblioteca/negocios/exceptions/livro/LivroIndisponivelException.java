package biblioteca.negocios.exceptions.livro;

public class LivroIndisponivelException extends Exception {

	public LivroIndisponivelException(String codigo) {
		super("O livro [" + codigo + "] encontra-se indispon�vel no momento.");
	}
}