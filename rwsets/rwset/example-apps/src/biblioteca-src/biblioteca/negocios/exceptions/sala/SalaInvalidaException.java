package biblioteca.negocios.exceptions.sala;

public class SalaInvalidaException extends Exception {

	public SalaInvalidaException(String erro) {
		super("O campo [" + erro + "] est� inv�lido.");
	}

}