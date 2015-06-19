package biblioteca.negocios.exceptions.pessoa;

public class TelefoneInvalidoException extends Exception{
	
	private String telefone;
	
	
	public TelefoneInvalidoException (String telefone){
		super("O n�mero de telefone [" + telefone +"] est� inv�lido");
		
	}

}