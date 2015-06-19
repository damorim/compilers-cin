package biblioteca.dados.repositorio.interfaces;

import java.io.IOException;

import biblioteca.dados.IteratorPessoa;
import biblioteca.dados.entidades.Pessoa;
import biblioteca.negocios.exceptions.pessoa.PessoaJaCadastradaException;
import biblioteca.negocios.exceptions.pessoa.PessoaNaoEncontradaException;

public interface RepositorioPessoa {

	void inserirPessoa(Pessoa pessoa) throws PessoaJaCadastradaException,
			IOException;

	void removerPessoa(String cpf) throws PessoaNaoEncontradaException,
			IOException;

	Pessoa procurarPessoa(String cpf) throws PessoaNaoEncontradaException,
			IOException;

	void atualizarPessoa(Pessoa pessoa) throws PessoaNaoEncontradaException,
			IOException;

	IteratorPessoa getIterator();

}
