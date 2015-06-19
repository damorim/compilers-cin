package biblioteca.dados.repositorio.interfaces;

import java.io.IOException;

import biblioteca.dados.IteratorLivro;
import biblioteca.dados.entidades.Livro;
import biblioteca.negocios.exceptions.livro.LivroJaCadastradoException;
import biblioteca.negocios.exceptions.livro.LivroNaoEncontradoException;

public interface RepositorioLivro {

	void inserirLivro(Livro livro) throws LivroJaCadastradoException,
			IOException;

	void removerLivro(String codigo) throws LivroNaoEncontradoException,
			IOException;

	Livro procurarLivro(String codigo) throws LivroNaoEncontradoException,
			IOException;

	void atualizarLivro(Livro livro) throws LivroNaoEncontradoException,
			IOException;

	IteratorLivro getIterator();

}
