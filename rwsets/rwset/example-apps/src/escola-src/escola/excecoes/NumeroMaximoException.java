package escola.excecoes;

@SuppressWarnings("serial")
public class NumeroMaximoException extends Exception {

	
	private String items;
	private int numMax; 
	public NumeroMaximoException(String items, int numMax){
		super("So nao possivel adicionar "+numMax+" "+items+".");
		this.items = items;
		this.setNumMax(numMax);
	}
	
	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

	public int getNumMax() {
		return numMax;
	}
	public void setNumMax(int numMax) {
		this.numMax = numMax;
	}
	
}
