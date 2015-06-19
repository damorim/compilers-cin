package biblioteca.negocios.exceptions;

public class ItemNaoEncontradoException extends Exception {

	public ItemNaoEncontradoException(String codigo) {
		super("O item [" + codigo + "] n�o foi encontrado.");
	}

}