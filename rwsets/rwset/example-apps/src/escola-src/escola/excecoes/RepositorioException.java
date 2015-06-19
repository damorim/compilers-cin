package escola.excecoes;

@SuppressWarnings("serial")
public class RepositorioException extends Exception {

	private Throwable exception;

	public RepositorioException(Throwable exception) {
		this.exception = exception;
	}

	public String getMessage() {
		return exception.getMessage();
	}

}