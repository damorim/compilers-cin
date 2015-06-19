package escola.dados;
public abstract class RepositorioArray<T> implements Repositorio<T>, Iterable<T>{

	private T[] array;
	private int cont;
	
	public RepositorioArray() {
		this.cont = 0;
		//Lembrar de inicializar array na classe filho;
	}	
	
	public int getCont(){
		return this.cont;
	}
	
	public void setCont(int n){
		this.cont = n;
	}
	
	public void setArray(T[] array){
		this.array = array;
	}
	
	public T[] getArray(){
		return this.array;
	}
	
}
