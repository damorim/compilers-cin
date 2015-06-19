package biblioteca.negocios.exceptions.pessoa;

public class CpfInvalidoException extends Exception {

	private String cpf;

	public CpfInvalidoException(String cpf) {
		super("O CPF [" + cpfToString(cpf) + "] est� inv�lido.");
	}

	private static String cpfToString(String cpf) {

		String message = cpf;
		if (cpf.length() >= 11 && cpf.length() <= 11) {
			message = (cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "."
					+ cpf.substring(6, 9) + "-" + cpf.substring(9, 11));
		}
		return message;
	}
}