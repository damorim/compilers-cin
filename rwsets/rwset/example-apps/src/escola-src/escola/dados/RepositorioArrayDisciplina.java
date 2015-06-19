package escola.dados;

import escola.classesBase.Disciplina;
import escola.excecoes.ElementoNaoEncontradoException;

import java.util.Iterator;

public class RepositorioArrayDisciplina extends RepositorioArray<Disciplina> implements Repositorio<Disciplina> {

	public RepositorioArrayDisciplina(int n) {// Aqui a gente inicia o array de
											// Disciplinas; o n define o tamanho
		//testandoooo
		super();
		Disciplina[] array = new Disciplina[n];
		for (int a = 0; a < array.length; a++) {
			array[a] = null;
		}
		super.setArray(array);// Aqui e inicializado o array, que e um atributo
								// da calsse pai.
	}

	public RepositorioArrayDisciplina() {// Tamanho default = 500000
		super();
		Disciplina[] array = new Disciplina[50000];
		for (int a = 0; a < array.length; a++) {
			array[a] = null;
		}
		super.setArray(array);
	}

	public void inserir(Disciplina disciplina) {
		Disciplina[] array = super.getArray();
		array[super.getCont()] = disciplina;
		super.setArray(array);
		super.setCont(super.getCont() + 1);
	}

	public Disciplina procurar(String nome) throws ElementoNaoEncontradoException {
		Disciplina d = null;
		boolean achou = false;
		Disciplina[] array = super.getArray();
		for (int i = 0; !achou && i < super.getCont(); i++) {
			d = array[i];
			if (d.getNome().equals(nome)) {
				achou = true;
			}
		}
		if (achou == false) {
			throw new ElementoNaoEncontradoException();
		}
		return d;
	}
	
	public RepositorioArrayDisciplina procurarNome(String nome)
			throws ElementoNaoEncontradoException {
		Disciplina d = null;
		boolean achou = false;
		Disciplina[] array = super.getArray();
		RepositorioArrayDisciplina resultado = new RepositorioArrayDisciplina();
		for (int i = 0; i < super.getCont(); i++) {
			d = array[i];
			if (d.getNome().toLowerCase().contains(nome.toLowerCase())) {// isso tem que ser revisto,
				achou = true;
				resultado.inserir(d);
			}
		}
		if (achou == false) {
			throw new ElementoNaoEncontradoException();
		}
		return resultado;
	}

	private int procurarIndice(String nome) throws ElementoNaoEncontradoException {
		Disciplina d = null;
		boolean achou = false;
		Disciplina[] array = super.getArray();
		int i = 0;
		for (i = 0; !achou && i < super.getCont();) {
			d = array[i];
			if (d.getNome().equals(nome)) {
				achou = true;
			}
			if(!achou){
				i++;
			}
		}
		if (achou == false) {
			throw new ElementoNaoEncontradoException();
		}
		return i;
	}

	public void atualizar(Disciplina disciplina) throws ElementoNaoEncontradoException {
		int i = 0;
		try {
			i = procurarIndice(disciplina.getNome());
		} catch (ElementoNaoEncontradoException e) {
			throw new ElementoNaoEncontradoException();
		}
		Disciplina[] disciplinas = super.getArray();
		disciplinas[i] = disciplina;
		super.setArray(disciplinas);
	}

	public void remover(String nome) throws ElementoNaoEncontradoException {
		int i = 0;
		try {
			i = procurarIndice(nome);
		} catch (ElementoNaoEncontradoException e) {
			throw new ElementoNaoEncontradoException();
		}
		Disciplina[] disciplinas = super.getArray();
		if (i == super.getCont()) {
			disciplinas[i] = null;
		} else {
			for (int n = i; n < super.getCont(); n++) {
				disciplinas[n] = disciplinas[n + 1];
			}
		}

		disciplinas[disciplinas.length - 1] = null;
		super.setArray(disciplinas);
		super.setCont(super.getCont() - 1);
	}

	public String imprimir() {
		String retorno = "";
		Disciplina[] disciplinas = super.getArray();
		for (int i = 0; i <= super.getCont(); i++) {

			if (disciplinas[i] != null) {
				retorno += disciplinas[i].getNome();
				retorno += "\n";
			}
		}
		return retorno;

	}

	public Iterator<Disciplina> iterator() {// Ele ja pecorre o array da classe
										// pai.vv
		IteratorArrayDisciplina it = new IteratorArrayDisciplina(super.getArray());
		return it;
	}

	public Iterator<Disciplina> getIterator(){
		IteratorArrayDisciplina it = new IteratorArrayDisciplina(super.getArray());
		return it;
	}	
}
