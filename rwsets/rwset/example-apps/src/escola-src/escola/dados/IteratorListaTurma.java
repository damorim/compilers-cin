package escola.dados;
import java.util.Iterator;
import java.util.NoSuchElementException;

import escola.classesBase.Turma;


public class IteratorListaTurma implements Iterator<Turma> {

	private RepositorioListaTurma repositorio;

	public IteratorListaTurma(RepositorioListaTurma repositorio) {//Recebe como argumento o primeiro item da lista
		this.repositorio = new RepositorioListaTurma();
		this.repositorio.setProx(repositorio);//Cria a mesma estrutura presente no repositorio, em q o primeiro item da lista � vazio.
	}

	public boolean hasNext() {
		boolean resposta = true;
		if (this.repositorio.getProx() == null) {
			resposta = false;
		}
		return resposta;
	}
	
	
	
	public Turma next() throws NoSuchElementException{
		if (hasNext()) {
			this.repositorio = this.repositorio.getProx();
			return this.repositorio.getTurma();
		} else {
			throw new NoSuchElementException();
		}
	}

	public void remove() {
		throw new UnsupportedOperationException(
				"Esta operacao nao suportada.");
	}

	public RepositorioListaTurma getRepositorio() {
		return repositorio;
	}

	public void setRepositorio(RepositorioListaTurma repositorio) {
		this.repositorio = repositorio;
	}

}
