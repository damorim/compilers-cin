package escola.dados;
import java.util.Iterator;
import java.util.NoSuchElementException;

import escola.classesBase.Pessoa;


public class IteratorListaPessoa implements Iterator<Pessoa> {

	private RepositorioListaPessoa repositorio;

	public IteratorListaPessoa(RepositorioListaPessoa repositorio) {//Recebe como argumento o primeiro item da lista
		this.repositorio = new RepositorioListaPessoa();
		this.repositorio.setProx(repositorio);//Cria a mesma estrutura presente no repositorio, em q o primeiro item da lista � vazio.
	}

	public boolean hasNext() {
		boolean resposta = true;
		if (this.repositorio.getProx() == null) {
			resposta = false;
		}
		return resposta;
	}
	
	
	
	public Pessoa next() throws NoSuchElementException{
		if (hasNext()) {
			this.repositorio = this.repositorio.getProx();
			return this.repositorio.getPessoa();
		} else {
			throw new NoSuchElementException();
		}
	}

	public void remove() {
		throw new UnsupportedOperationException(
				"Esta operacao nao suportada.");
	}

	public RepositorioListaPessoa getRepositorio() {
		return repositorio;
	}

	public void setRepositorio(RepositorioListaPessoa repositorio) {
		this.repositorio = repositorio;
	}

}
