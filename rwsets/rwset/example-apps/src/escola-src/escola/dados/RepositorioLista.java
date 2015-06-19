package escola.dados;
public  class RepositorioLista<T> {
	private T item;
	private RepositorioLista<T> prox;

	public RepositorioLista(){
		this.item = null;
		this.prox = null;
		
	}
	public T getItem(){
		return item;
	}
	
	public void setItem(T item) {
		this.item = item;
	}
	
	public RepositorioLista<T> getProx(){
		return this.prox;
	}

	public void setProx(RepositorioLista<T> prox) {
		this.prox = prox;
	}

	
	
	
}
