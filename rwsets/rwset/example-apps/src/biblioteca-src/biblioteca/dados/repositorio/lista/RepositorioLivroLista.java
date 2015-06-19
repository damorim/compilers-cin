package biblioteca.dados.repositorio.lista;

import biblioteca.dados.IterableLivro;
import biblioteca.dados.IteratorLivro;
import biblioteca.dados.entidades.Livro;
import biblioteca.dados.lista.ListaLivro;
import biblioteca.dados.lista.Node;
import biblioteca.dados.repositorio.interfaces.RepositorioLivro;
import biblioteca.negocios.exceptions.livro.LivroJaCadastradoException;
import biblioteca.negocios.exceptions.livro.LivroNaoEncontradoException;

public class RepositorioLivroLista implements IterableLivro, RepositorioLivro {

	private ListaLivro cadastro;
	private int indice;

	public RepositorioLivroLista() {
		cadastro = new ListaLivro();
		indice = 0;
	}

	private int getIndice(String codigo) {
		int indice = -1;
		if (!cadastro.isEmpty()) {
			int i = 0;

			while (i < this.indice
					&& !(((Livro) cadastro.get(i)).getCodigo()
							.equals(codigo))) {
				i += 1;
			}

			if (i < this.indice) {
				indice = i;
			}
		}
		return indice;
	}

	@Override
	public void inserirLivro(Livro livro) throws LivroJaCadastradoException {
		int indice = getIndice(livro.getCodigo());

		if (indice == -1 || indice >= this.indice) {
			Node no = new Node(livro);
			cadastro.insert(no);
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
			cadastro.remove(indice);
			this.indice -= 1;
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
			livro = cadastro.get(indice);
		}

		return livro;
	}

	@Override
	public void atualizarLivro(Livro livro) throws LivroNaoEncontradoException {

		int indice = getIndice(livro.getCodigo());

		if (indice == -1 || indice >= this.indice) {
			throw new LivroNaoEncontradoException(livro.getCodigo());
		} else {
			Node no = new Node(livro);
			cadastro.set(indice, no);
		}

	}

	@Override
	public IteratorLivro getIterator() {
		IteratorLivro aux = new IteratorLivro(cadastro);
		return aux;
	}

}
