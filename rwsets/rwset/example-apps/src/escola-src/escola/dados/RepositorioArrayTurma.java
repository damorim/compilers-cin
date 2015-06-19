package escola.dados;

import escola.classesBase.Turma;
import escola.excecoes.ElementoNaoEncontradoException;

import java.util.Iterator;

public class RepositorioArrayTurma extends RepositorioArray<Turma> implements Repositorio<Turma> {

	public RepositorioArrayTurma(int n) {// Aqui a gente inicia o array de
											// Turmas; o n define o tamanho
		super();
		Turma[] array = new Turma[n];
		for (int a = 0; a < array.length; a++) {
			array[a] = null;
		}
		super.setArray(array);// Aqui e inicializado o array, que e um atributo
								// da calsse pai.
	}

	public RepositorioArrayTurma() {// Tamanho default = 500000
		super();
		Turma[] array = new Turma[50000];
		for (int a = 0; a < array.length; a++) {
			array[a] = null;
		}
		super.setArray(array);
	}

	public void inserir(Turma turma) {
		Turma[] array = super.getArray();
		array[super.getCont()] = turma;
		super.setArray(array);
		super.setCont(super.getCont() + 1);
	}


	public Turma procurar(String nome) throws ElementoNaoEncontradoException {
		Turma t = null;
		boolean achou = false;
		Turma[] array = super.getArray();
		for (int i = 0; !achou && i < super.getCont(); i++) {
			t = array[i];
			if (t.getNome().equals(nome)) {
				achou = true;
			}
		}
		if (achou == false) {
			throw new ElementoNaoEncontradoException();
		}
		return t;
	}
	
	public RepositorioArrayTurma procurarNome(String nome)
			throws ElementoNaoEncontradoException {
		Turma t = null;
		boolean achou = false;
		Turma[] array = super.getArray();
		RepositorioArrayTurma resultado = new RepositorioArrayTurma();
		for (int i = 0; i < super.getCont(); i++) {
			t = array[i];
			if (t.getNome().toLowerCase().contains(nome.toLowerCase())) {// isso tem que ser revisto,
				achou = true;
				resultado.inserir(t);
			}
		}
		if (achou == false) {
			throw new ElementoNaoEncontradoException();
		}
		return resultado;
	}
	
	private int procurarIndice(String nome) throws ElementoNaoEncontradoException {
		Turma t = null;
		boolean achou = false;
		Turma[] array = super.getArray();
		int i = 0;
		for (i = 0; !achou && i < super.getCont();) {
			t = array[i];
			if (t.getNome().equals(nome)) {
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

	public void atualizar(Turma turma) throws ElementoNaoEncontradoException {
		int i = 0;
		try {
			i = procurarIndice(turma.getNome());
		} catch (ElementoNaoEncontradoException e) {
			throw new ElementoNaoEncontradoException();
		}
		Turma[] turmas = super.getArray();
		turmas[i] = turma;
		super.setArray(turmas);
	}

	public void remover(String nome) throws ElementoNaoEncontradoException {
		int i = 0;
		try {
			i = procurarIndice(nome);
		} catch (ElementoNaoEncontradoException e) {
			throw new ElementoNaoEncontradoException();
		}
		// System.out.println(i);//esse e um Teste
		Turma[] turmas = super.getArray();
		if (i == super.getCont()) {
			turmas[i] = null;
		} else {
			for (int n = i; n < super.getCont(); n++) {
				turmas[n] = turmas[n + 1];
			}
		}

		turmas[turmas.length - 1] = null;
		super.setArray(turmas);
		super.setCont(super.getCont() - 1);
	}

	public String imprimir() {
		String retorno = "";
		Turma[] turmas = super.getArray();
		for (int i = 0; i <= super.getCont(); i++) {

			if (turmas[i] != null) {
				retorno += turmas[i].getNome();
				retorno += "\n";
			}
		}
		return retorno;

	}

	public Iterator<Turma> iterator() {// Ele ja pecorre o array da classe
										// pai.vv
		IteratorArrayTurma it = new IteratorArrayTurma(super.getArray());
		return it;
	}

	public Iterator<Turma> getIterator(){
		IteratorArrayTurma it = new IteratorArrayTurma(super.getArray());
		return it;
	}
}
