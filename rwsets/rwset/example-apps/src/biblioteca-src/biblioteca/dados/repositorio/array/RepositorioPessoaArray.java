package biblioteca.dados.repositorio.array;

import biblioteca.dados.IterablePessoa;
import biblioteca.dados.IteratorPessoa;
import biblioteca.dados.entidades.Pessoa;
import biblioteca.dados.repositorio.interfaces.RepositorioPessoa;
import biblioteca.negocios.exceptions.pessoa.PessoaJaCadastradaException;
import biblioteca.negocios.exceptions.pessoa.PessoaNaoEncontradaException;

public class RepositorioPessoaArray implements IterablePessoa,
		RepositorioPessoa {

	private Pessoa[] cadastro;
	private int indice;

	public RepositorioPessoaArray() {
		cadastro = new Pessoa[10];
		indice = 0;
	}

	private void expandir() {
		Pessoa[] aux = new Pessoa[cadastro.length * 2];
		for (int i = 0; i < indice; i += 1) {
			aux[i] = cadastro[i];
		}

		cadastro = aux;
	}

	private int getIndice(String cpf) {
		int indice = -1;
		int i = 0;

		while (i < this.indice && !(cadastro[i].getCpf().equals(cpf))) {
			i += 1;
		}

		if (i < this.indice) {
			indice = i;
		}

		return indice;
	}

	@Override
	public void inserirPessoa(Pessoa pessoa) throws PessoaJaCadastradaException {
		if (this.indice >= cadastro.length) {
			expandir();
		}

		int indice = getIndice(pessoa.getCpf());

		if (indice == -1 || indice >= this.indice) {
			cadastro[this.indice] = pessoa;
			this.indice += 1;
		} else {
			throw new PessoaJaCadastradaException(pessoa.getCpf());
		}
	}

	@Override
	public void removerPessoa(String cpf) throws PessoaNaoEncontradaException {
		int indice = getIndice(cpf);

		if (indice == -1 || indice >= this.indice) {
			throw new PessoaNaoEncontradaException(cpf);
		} else {
			for (int i = indice; i < (this.indice - 1); i += 1) {
				cadastro[i] = cadastro[i + 1];
			}

			this.indice -= 1;
			cadastro[this.indice] = null;
		}

	}

	@Override
	public Pessoa procurarPessoa(String cpf)
			throws PessoaNaoEncontradaException {
		Pessoa pessoa = null;

		int indice = getIndice(cpf);
		if (indice == -1 || indice >= this.indice) {
			throw new PessoaNaoEncontradaException(cpf);
		} else {
			pessoa = cadastro[indice];
		}
		return pessoa;
	}

	@Override
	public void atualizarPessoa(Pessoa pessoa)
			throws PessoaNaoEncontradaException {
		int indice = getIndice(pessoa.getCpf());

		if (indice == -1 || indice >= this.indice) {
			throw new PessoaNaoEncontradaException(pessoa.getCpf());
		} else {
			cadastro[indice] = pessoa;
		}

	}

	@Override
	public IteratorPessoa getIterator() {
		IteratorPessoa aux = new IteratorPessoa(cadastro);
		return aux;
	}
}
