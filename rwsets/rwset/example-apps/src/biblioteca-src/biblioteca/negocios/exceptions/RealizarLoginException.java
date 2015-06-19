package biblioteca.negocios.exceptions;

public class RealizarLoginException extends Exception {

	public RealizarLoginException(){
		super("Para realizar esta a��o voc� deve est� logado no sistema");
	}
}