package biblioteca.negocios.exceptions.sala;

public class SalaComEmprestimoException extends Exception {

	public SalaComEmprestimoException(String codigo) {
		super("N�o foi poss�vel excluir a sala [" + codigo
				+ "] porque ela est� emprestada.");
	}
}