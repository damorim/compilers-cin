package escola.excecoes;

@SuppressWarnings("serial")
public class ElementoJaCadastradoException extends Exception {
	public ElementoJaCadastradoException() {
		super("Elemento ja cadastrado");
	}
}
