package biblioteca.negocios.exceptions.sala;

public class SalaJaCadastradaException extends Exception {

	private String codigo;

	public SalaJaCadastradaException(String codigo) {
		super("A sala [" + codigo + "] j� est� cadastrada no sistema.");
		this.codigo = codigo;
	}

	public String getCodigo() {
		return this.codigo;
	}
}