package biblioteca.dados.repositorio.array;

import java.io.IOException;

import biblioteca.dados.IterableSala;
import biblioteca.dados.IteratorSala;
import biblioteca.dados.entidades.Sala;
import biblioteca.dados.repositorio.interfaces.RepositorioSala;
import biblioteca.negocios.exceptions.sala.SalaJaCadastradaException;
import biblioteca.negocios.exceptions.sala.SalaNaoEncontradaException;

public class RepositorioSalaArray implements IterableSala, RepositorioSala {

	private Sala[] cadastro;
	private int indice;

	public RepositorioSalaArray() {
		cadastro = new Sala[10];
		indice = 0;
	}

	private void expandir() {
		Sala[] aux = new Sala[cadastro.length * 2];
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
	public void inserirSala(Sala sala) throws SalaJaCadastradaException,
			IOException {
		if (this.indice >= cadastro.length) {
			expandir();
		}

		int indice = getIndice(sala.getCodigo());

		if (indice == -1 || indice >= this.indice) {
			cadastro[this.indice] = sala;
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
			for (int i = indice; i < (this.indice - 1); i += 1) {
				cadastro[i] = cadastro[i + 1];
			}

			this.indice -= 1;
			cadastro[this.indice] = null;
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
			sala = cadastro[indice];
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
			cadastro[indice] = sala;
		}

	}

	@Override
	public IteratorSala getIterator() {
		IteratorSala aux = new IteratorSala(cadastro);
		return aux;
	}

}
