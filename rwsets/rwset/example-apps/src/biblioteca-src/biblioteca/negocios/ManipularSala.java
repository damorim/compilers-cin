package biblioteca.negocios;

import java.io.IOException;

import biblioteca.dados.IteratorSala;
import biblioteca.dados.entidades.Sala;
import biblioteca.dados.lista.Node;
import biblioteca.dados.relatorio.MergeSortSala;
import biblioteca.dados.repositorio.interfaces.RepositorioSala;
import biblioteca.negocios.exceptions.RealizarLoginException;
import biblioteca.negocios.exceptions.sala.SalaComEmprestimoException;
import biblioteca.negocios.exceptions.sala.SalaIndisponivelException;
import biblioteca.negocios.exceptions.sala.SalaInvalidaException;
import biblioteca.negocios.exceptions.sala.SalaJaCadastradaException;
import biblioteca.negocios.exceptions.sala.SalaNaoEncontradaException;

public class ManipularSala {

	private RepositorioSala generico;
	private Sala cache;
	private MergeSortSala ordenar;

	public ManipularSala(RepositorioSala generico) {
		this.generico = generico;
		this.cache = null;
		this.ordenar = new MergeSortSala();
	}

	public void cadastrarSala(Sala sala) throws SalaJaCadastradaException,
			IOException {
		generico.inserirSala(sala);
	}

	public void selecionarSala(String codigo)
			throws SalaNaoEncontradaException, IOException {
		cache = generico.procurarSala(codigo);
	}

	public void deselecionarSala() {
		cache = null;
	}

	public Sala criarSala(String codigo, int capacidade)
			throws SalaInvalidaException {
		Sala sala = null;

		boolean erro = false;
		String erroMessage = "";

		if (!campoValidoCodigo(codigo)) {
			erro = true;
			erroMessage += "c�digo";
		}

		if (!campoValidoCapacidade(capacidade)) {
			if (erro) {
				erroMessage += ", capacidade";
			} else {
				erro = true;
				erroMessage += "capacidade";
			}
		}

		if (!erro) {
			sala = new Sala(codigo, capacidade);
		} else {
			throw new SalaInvalidaException(erroMessage);
		}

		return sala;
	}

	private boolean campoValidoCodigo(String codigo) {
		boolean resposta = true;
		if (codigo == null || codigo.length() < 1) {
			resposta = false;
		}

		return resposta;
	}

	private boolean campoValidoCapacidade(int cap) {
		boolean resposta = true;
		if (cap < 4) {
			resposta = false;
		}

		return resposta;
	}

	public void realizarEmprestimoSala(String dataDevolucao,
			String cpfResponsavel) throws SalaIndisponivelException,
			RealizarLoginException {
		if (cache != null) {
			cache.emprestar(dataDevolucao, cpfResponsavel);
		} else {
			throw new RealizarLoginException();
		}
	}

	public void realizarDevolucaoSala() throws RealizarLoginException {

		if (cache != null) {
			cache.devolver();
		} else {
			throw new RealizarLoginException();
		}
	}

	public void realizarExclusao(String codigo)
			throws SalaNaoEncontradaException, IOException,
			SalaComEmprestimoException {

		selecionarSala(codigo);

		if (cache != null) {
			if (cache.isDisponivel()) {
				deselecionarSala();
				generico.removerSala(codigo);
			} else {
				throw new SalaComEmprestimoException(codigo);
			}
		}

	}

	public void confirmarAtualizarSala(int info, int infoTipo)
			throws SalaInvalidaException, RealizarLoginException,
			SalaIndisponivelException {
		if (cache != null) {
			if (cache.isDisponivel()) {
				switch (infoTipo) {
				case 1: {
					// Capacidade
					if (campoValidoCapacidade(info)) {
						cache.setCapacidade(info);
					} else {
						throw new SalaInvalidaException("capacidade");
					}

					break;
				}
				}
			} else {
				throw new SalaIndisponivelException(cache.getCodigo());
			}
		} else {
			throw new RealizarLoginException();
		}
	}

	public String[] getCacheDados() {
		String[] dados = new String[2];
		if (cache != null) {
			dados[0] = cache.getCodigo();
			dados[1] = "SALA";
		}

		return dados;
	}

	public String getDadosSala() {
		String resposta = "";
		if (cache != null) {
			resposta = cache.toString();
		}
		return resposta;
	}

	public String printRepositorio() {
		IteratorSala it = generico.getIterator();
		String message = "";

		while (it.hasNext()) {
			message += it.next().toString() + "\n";
		}

		return message;
	}

	public String gerarRelatorio(int parametro) {
		String message = "";

		IteratorSala it = generico.getIterator();

		if (it.size() > 0) {
			Node[] aux = new Node[it.size()];

			int counter = 0;
			while (it.hasNext()) {
				aux[counter] = new Node(it.next());
				counter++;
			}

			Node[] no = new Node[counter];
			for (int i = 0; i < counter; i++) {
				no[i] = aux[i];
			}

			ordenar.ordenar(no, parametro);

			for (int i = 0; i < no.length; i++) {
				message += ((Sala) no[i].getObjeto()).toString();
				message += "\n";
			}
		}
		return message;
	}
}
