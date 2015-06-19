package biblioteca.negocios.exceptions.pessoa;



public class MultaGeradaException extends Exception {

	public MultaGeradaException(String valor) {

		super(
				"Atraso! Para realizar a devolu��o deste item � necess�rio o pagamento da quantia ["
						+ valor + "].");
	}
}