package biblioteca.dados.repositorio.lista;

import java.io.IOException;

import biblioteca.dados.IterableSala;
import biblioteca.dados.IteratorSala;
import biblioteca.dados.entidades.Sala;
import biblioteca.dados.lista.ListaSala;
import biblioteca.dados.lista.Node;
import biblioteca.dados.repositorio.interfaces.RepositorioSala;
import biblioteca.negocios.exceptions.sala.SalaJaCadastradaException;
import biblioteca.negocios.exceptions.sala.SalaNaoEncontradaException;

public class RepositorioSalaLista implements IterableSala, RepositorioSala {

	private ListaSala cadastro;
	private int indice;

	public RepositorioSalaLista() {
		cadastro = new ListaSala();
		indice = 0;
	}

	private int getIndice(String codigo) {
		int indice = -1;

		if (!cadastro.isEmpty()) {
			int i = 0;

			while (i < this.indice
					&& !(cadastro.get(i).getCodigo().equals(codigo))) {
				i += 1;
			}

			if (i < this.indice) {
				indice = i;
			}
		}

		return indice;
	}

	@Override
	public void inserirSala(Sala sala) throws SalaJaCadastradaException,
			IOException {
		int indice = getIndice(sala.getCodigo());
		if (indice == -1 || indice >= this.indice) {
			Node no = new Node(sala);
			cadastro.insert(no);
			this.indice += 1;
		} else {
			throw new SalaJaCadastradaException(sala.getCodigo());
		}

	}

	@Override
	public void removerSala(String codigo) throws SalaNaoEncontradaException,
			IOException {
		int indice = getIndice(codigo);

		if (indice == -1 || indice >= this.indice) {
			throw new SalaNaoEncontradaException(codigo);
		} else {
			cadastro.remove(indice);
			this.indice -= 1;
		}

	}

	@Override
	public Sala procurarSala(String codigo) throws SalaNaoEncontradaException,
			IOException {
		Sala sala = null;

		int indice = getIndice(codigo);

		if (indice == -1 || indice >= this.indice) {
			throw new SalaNaoEncontradaException(codigo);
		} else {
			sala = cadastro.get(indice);
		}
		return sala;
	}

	@Override
	public void atualizarSala(Sala sala) throws SalaNaoEncontradaException,
			IOException {
		int indice = getIndice(sala.getCodigo());

		if (indice == -1 || indice >= this.indice) {
			throw new SalaNaoEncontradaException(sala.getCodigo());
		} else {
			Node no = new Node(sala);
			cadastro.set(indice, no);
		}

	}

	@Override
	public IteratorSala getIterator() {
		IteratorSala aux = new IteratorSala(cadastro);
		return aux;
	}

}
