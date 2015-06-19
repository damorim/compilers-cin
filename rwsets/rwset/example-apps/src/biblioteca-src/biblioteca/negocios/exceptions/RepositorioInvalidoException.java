package biblioteca.negocios.exceptions;

public class RepositorioInvalidoException extends Exception {

	private String nome;

	public RepositorioInvalidoException(String nome) {
		super("O reposit�rio " + " n�o existe no sistema.");
		this.nome = nome;
	}

	public String getNome() {
		return this.nome;
	}

}