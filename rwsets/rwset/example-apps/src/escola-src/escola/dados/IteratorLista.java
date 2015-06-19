package escola.dados;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteratorLista<T> implements Iterator<T> {

	private RepositorioLista<T> repositorio;

	public IteratorLista(RepositorioLista<T> repositorio) {
		this.repositorio = new RepositorioLista<T>();
		this.repositorio.setProx(repositorio);
	}

	public boolean hasNext() {
		boolean resposta = true;
		if (this.repositorio.getProx() == null) {
			resposta = false;
		}
		return resposta;
	}
	
	
	
	public T next() throws NoSuchElementException{
		if (hasNext()) {
			this.repositorio = this.repositorio.getProx();
			return this.repositorio.getItem();
		} else {
			throw new NoSuchElementException();
		}
	}

	public void remove() {
		throw new UnsupportedOperationException(
				"Esta operacao nao suportada.");
	}

	public RepositorioLista<T> getRepositorio() {
		return repositorio;
	}

	public void setRepositorio(RepositorioLista<T> repositorio) {
		this.repositorio = repositorio;
	}

}
