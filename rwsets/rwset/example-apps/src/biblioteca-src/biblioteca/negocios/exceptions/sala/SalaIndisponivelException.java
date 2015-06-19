package biblioteca.negocios.exceptions.sala;

public class SalaIndisponivelException extends Exception {
	
	public SalaIndisponivelException(String codigo){
		super("A sala [" + codigo + "] encontra-se indispon�vel no momento.");
	}
}