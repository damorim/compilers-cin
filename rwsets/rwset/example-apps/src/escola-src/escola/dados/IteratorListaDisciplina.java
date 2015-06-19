package escola.dados;
import java.util.Iterator;
import java.util.NoSuchElementException;

import escola.classesBase.Disciplina;

public class IteratorListaDisciplina implements Iterator<Disciplina> {

	private RepositorioListaDisciplina repositorio;

	public IteratorListaDisciplina(RepositorioListaDisciplina repositorio) {//Recebe como argumento o primeiro item da lista
		this.repositorio = new RepositorioListaDisciplina();
		this.repositorio.setProx(repositorio);//Cria a mesma estrutura presente no repositorio, em q o primeiro item da lista � vazio.
	}

	public boolean hasNext() {
		boolean resposta = true;
		if (this.repositorio.getProx() == null) {
			resposta = false;
		}
		return resposta;
	}
	
	
	
	public Disciplina next() throws NoSuchElementException{
		if (hasNext()) {
			this.repositorio = this.repositorio.getProx();
			return this.repositorio.getDisciplina();
		} else {
			throw new NoSuchElementException();
		}
	}

	public void remove() {
		throw new UnsupportedOperationException(
				"Esta operacao nao suportada.");
	}

	public RepositorioListaDisciplina getRepositorio() {
		return repositorio;
	}

	public void setRepositorio(RepositorioListaDisciplina repositorio) {
		this.repositorio = repositorio;
	}

}
