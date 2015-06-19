package hardware;



public class Estrutura{
	String assembly;
	String binario;
	public Estrutura(String assembly,String binario) {
		this.assembly = assembly;
		this.binario = binario;
	}
	
	public boolean equals(Object ob) {
		boolean retorno = false;
		
		if (ob instanceof Estrutura) {
			retorno = this.assembly.equalsIgnoreCase(((Estrutura) ob).assembly);
		}
		
		return retorno;
	}
}