package escola.excecoes;

@SuppressWarnings("serial")
public class EntradaInvalidaException extends Exception {
	private String OndeErrou;
	public EntradaInvalidaException(String ondeErrou){
		super("Entrada invalida\n"+ondeErrou);
		this.setOndeErrou(ondeErrou);
	}
	public String getOndeErrou() {
		return OndeErrou;
	}
	public void setOndeErrou(String oQNaoPode) {
		OndeErrou = oQNaoPode;
	}
}
