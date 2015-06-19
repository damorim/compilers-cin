package biblioteca.negocios.exceptions.pessoa;

public class AlunoComEmprestimoException extends Exception {

	public AlunoComEmprestimoException(String cpf, int total) {
		super("N�o foi poss�vel remover o aluno [" + cpf
				+ "] pois ele cont�m " + total + " livro(s) emprestado(s).");
	}
}