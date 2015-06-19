package biblioteca.dados.repositorio.array;

import biblioteca.dados.IterableLivro;
import biblioteca.dados.IteratorLivro;
import biblioteca.dados.entidades.Livro;
import biblioteca.dados.repositorio.interfaces.RepositorioLivro;
import biblioteca.negocios.exceptions.livro.LivroJaCadastradoException;
import biblioteca.negocios.exceptions.livro.LivroNaoEncontradoException;

public class RepositorioLivroArray implements IterableLivro, RepositorioLivro {

	private Livro[] cadastro;
	private int indice;

	public RepositorioLivroArray() {
		cadastro = new Livro[10];
		indice = 0;
	}

	private void expandir() {
		Livro[] aux = new Livro[cadastro.length * 2];
		for (int i = 0; i < indice; i += 1) {
			aux[i] = cadastro[i];
		}

		cadastro = aux;
	}

	private int getIndice(String codigo) {
		int indice = -1;
		int i = 0;

		while (i < this.indice && !(cadastro[i].getCodigo().equals(codigo))) {
			i += 1;
		}

		if (i < this.indice) {
			indice = i;
		}

		return indice;
	}

	@Override
	public void inserirLivro(Livro livro) throws LivroJaCadastradoException {

		if (this.indice >= cadastro.length) {
			expandir();
		}

		int indice = getIndice(livro.getCodigo());

		if (indice == -1 || indice >= this.indice) {
			cadastro[this.indice] = livro;
			this.indice += 1;
		} else {
			throw new LivroJaCadastradoException(livro.getCodigo());
		}
	}

	@Override
	public void removerLivro(String codigo) throws LivroNaoEncontradoException {
		int indice = getIndice(codigo);

		if (indice == -1 || indice >= this.indice) {
			throw new LivroNaoEncontradoException(codigo);
		} else {
			for (int i = indice; i < (this.indice - 1); i += 1) {
				cadastro[i] = cadastro[i + 1];
			}

			this.indice -= 1;
			cadastro[this.indice] = null;
		}

	}

	@Override
	public Livro procurarLivro(String codigo)
			throws LivroNaoEncontradoException {
		Livro livro = null;

		int indice = getIndice(codigo);
		if (indice == -1 || indice >= this.indice) {
			throw new LivroNaoEncontradoException(codigo);
		} else {
			livro = cadastro[indice];
		}
		return livro;
	}

	@Override
	public void atualizarLivro(Livro livro) throws LivroNaoEncontradoException {
		int indice = getIndice(livro.getCodigo());

		if (indice == -1 || indice >= this.indice) {
			throw new LivroNaoEncontradoException(livro.getCodigo());
		} else {
			cadastro[indice] = livro;
		}

	}

	@Override
	public IteratorLivro getIterator() {
		IteratorLivro aux = new IteratorLivro(cadastro);
		return aux;
	}

}
