package biblioteca.dados.repositorio.interfaces;

import java.io.IOException;

import biblioteca.dados.IteratorSala;
import biblioteca.dados.entidades.Sala;
import biblioteca.negocios.exceptions.sala.SalaJaCadastradaException;
import biblioteca.negocios.exceptions.sala.SalaNaoEncontradaException;

public interface RepositorioSala {

	void inserirSala(Sala sala) throws SalaJaCadastradaException, IOException;

	void removerSala(String codigo) throws SalaNaoEncontradaException,
			IOException;

	Sala procurarSala(String codigo) throws SalaNaoEncontradaException,
			IOException;

	void atualizarSala(Sala sala) throws SalaNaoEncontradaException,
			IOException;

	IteratorSala getIterator();

}
