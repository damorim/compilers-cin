package escola.dados;

import escola.classesBase.Pessoa;
import escola.excecoes.ElementoNaoEncontradoException;

import java.util.Iterator;

public class RepositorioArrayPessoa extends RepositorioArray<Pessoa> implements Repositorio<Pessoa>{

	public RepositorioArrayPessoa(int n) {// Aqui a gente inicia o array de
		// Pessoas; o n define o tamanho
		super();
		Pessoa[] array = new Pessoa[n];
		for (int a = 0; a < array.length; a++) {
			array[a] = null;
		}
		super.setArray(array);// Aqui e inicializado o array, que e um atributo
		// da calsse pai.
	}

	public RepositorioArrayPessoa() {// Tamanho default = 500000
		super();
		Pessoa[] array = new Pessoa[50000];
		for (int a = 0; a < array.length; a++) {
			array[a] = null;
		}
		super.setArray(array);
	}

	public void inserir(Pessoa pessoa) {
		Pessoa[] array = super.getArray();
		array[super.getCont()] = pessoa;
		super.setArray(array);
		super.setCont(super.getCont() + 1);
	}

	public Pessoa procurar(String cpf) throws ElementoNaoEncontradoException {
		Pessoa p = null;
		boolean achou = false;
		Pessoa[] array = super.getArray();
		for (int i = 0; !achou && i < super.getCont(); i++) {
			p = array[i];
			if (p.getCpf().equals(cpf)) {
				achou = true;
			}
		}
		if (achou == false) {
			throw new ElementoNaoEncontradoException();
		}
		return p;
	}

	public RepositorioArrayPessoa procurarNome(String nome)
			throws ElementoNaoEncontradoException {
		Pessoa p = null;
		boolean achou = false;
		Pessoa[] array = super.getArray();
		RepositorioArrayPessoa resultado = new RepositorioArrayPessoa();
		for (int i = 0; i < super.getCont(); i++) {
			p = array[i];
			if (p.getNome().toLowerCase().contains(nome.toLowerCase())) {// isso
																			// tem
																			// que
																			// ser
																			// revisto,
				achou = true;
				resultado.inserir(p);
			}
		}
		if (achou == false) {
			throw new ElementoNaoEncontradoException();
		}
		return resultado;
	}

	public int procurarIndice(String cpf) throws ElementoNaoEncontradoException {
		Pessoa p = null;
		boolean achou = false;
		Pessoa[] array = super.getArray();
		int i = 0;
		for (i = 0; !achou && i < super.getCont();) {
			p = array[i];
			if (p.getCpf().equals(cpf)) {
				achou = true;
			}
			if (!achou) {
				i++;
			}
		}
		if (achou == false) {
			throw new ElementoNaoEncontradoException();
		}
		return i;
	}

	public void atualizar(Pessoa pessoa) throws ElementoNaoEncontradoException {
		int i = 0;
		try {
			i = procurarIndice(pessoa.getCpf());
		} catch (ElementoNaoEncontradoException e) {
			throw new ElementoNaoEncontradoException();
		}
		Pessoa[] pessoas = super.getArray();
		pessoas[i] = pessoa;
		super.setArray(pessoas);
	}

	public void remover(String cpf) throws ElementoNaoEncontradoException {
		int i = 0;
		try {
			i = procurarIndice(cpf);
		} catch (ElementoNaoEncontradoException e) {
			throw new ElementoNaoEncontradoException();
		}
		// System.out.println(i);//esse e um Teste
		Pessoa[] pessoas = super.getArray();
		if (i == super.getCont()) {
			pessoas[i] = null;
		} else {
			for (int n = i; n < super.getCont(); n++) {
				pessoas[n] = pessoas[n + 1];
			}
		}

		pessoas[pessoas.length - 1] = null;
		super.setArray(pessoas);
		super.setCont(super.getCont() - 1);
	}

	public String imprimir() {
		String retorno = "";
		Pessoa[] pessoas = super.getArray();
		for (int i = 0; i <= super.getCont(); i++) {

			if (pessoas[i] != null) {
				retorno += pessoas[i].getNome() + " / " + pessoas[i].getCpf()
						+ " / " + pessoas[i].getIdentidade();
				retorno += "\n";
			}
		}
		return retorno;

	}

	public Iterator<Pessoa> iterator() {// Ele ja pecorre o array da classe
		// pai.vv
		IteratorArrayPessoa it = new IteratorArrayPessoa(super.getArray());
		return it;
	}

	public Iterator<Pessoa> getIterator() {
		IteratorArrayPessoa it = new IteratorArrayPessoa(super.getArray());
		return it;
	}
}
