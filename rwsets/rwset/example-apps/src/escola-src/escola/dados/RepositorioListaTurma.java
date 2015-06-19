package escola.dados;

import java.util.Iterator;

import escola.classesBase.*;
import escola.excecoes.ElementoNaoEncontradoException;

public class RepositorioListaTurma implements Repositorio<Turma> {

	private Turma turma;
	private RepositorioListaTurma prox;

	public RepositorioListaTurma() {
		this.turma = null;
		this.prox = null;
	}

	public Turma getTurma() {
		return this.turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public RepositorioListaTurma getProx() {
		return this.prox;
	}

	public void setProx(RepositorioListaTurma prox) {
		this.prox = prox;
	}

	public void inserir(Turma turma) {

		if (this.prox == null) {
			RepositorioListaTurma novoRepositorio = new RepositorioListaTurma();
			novoRepositorio.setTurma(turma);
			setProx(novoRepositorio);
		} else {
			getProx().inserir(turma);
		}
	}

	public String imprimir() {
		RepositorioListaTurma repositorioAtual = this.prox;
		String resposta = "";
		while (repositorioAtual != null) {
			resposta = resposta + repositorioAtual.getTurma().getNome();
			repositorioAtual = repositorioAtual.getProx();
		}
		return resposta;
	}

	public Turma procurar(String nome) throws ElementoNaoEncontradoException {
		Turma t = null;
		boolean achou = false;
		// Iterator<RepositorioLista<Turma>> it = iterator();
		RepositorioListaTurma repositorioAtual = this.prox;
		while (repositorioAtual != null && achou == false) {
			t = repositorioAtual.getTurma();
			if (t.getNome().equals(nome)) {
				achou = true;
			}
			repositorioAtual = repositorioAtual.getProx();
		}
		if (achou == false) {
			throw new ElementoNaoEncontradoException();
		}
		return t;
	}

	public RepositorioArrayTurma procurarNome(String nome)
			throws ElementoNaoEncontradoException {
		Turma t = null;
		boolean achou = false;
		RepositorioArrayTurma resultado = new RepositorioArrayTurma();
		RepositorioListaTurma repositorioAtual = this.prox;
		while (repositorioAtual != null) {
			t = repositorioAtual.getTurma();
			if (t.getNome().toLowerCase().contains(nome.toLowerCase())) {
				achou = true;
				resultado.inserir(t);
			}
			repositorioAtual = repositorioAtual.getProx();
		}
		if (achou == false) {
			throw new ElementoNaoEncontradoException();
		}
		return resultado;
	}

	public void atualizar(Turma turma) throws ElementoNaoEncontradoException {

		boolean achou = false;
		Turma t = null;
		RepositorioListaTurma repositorioAtual = this.prox;
		while ((repositorioAtual != null) && (achou == false)) {
			t = repositorioAtual.getTurma();
			if (t.getNome().equals(turma.getNome())) {
				repositorioAtual.setTurma(turma);
				achou = true;
			} else {
				repositorioAtual = repositorioAtual.getProx();
			}
		}
		if (achou == false) {
			throw new ElementoNaoEncontradoException();
		}
		/**
		 * boolean achou = false; Iterator<RepositorioLista<Turma>> it =
		 * iterator(); Turma p = null; RepositorioLista<Turma> repositorio =
		 * null; while (it.hasNext() && !achou) { try { repositorio = it.next();
		 * p = repositorio.getTurma(); } catch (NoSuchElementException e) {
		 * throw new ElementoNaoEncontradoException(); } if (p != null &&
		 * p.getNome().equals(turma.getNome())) { achou = true;
		 * repositorio.setTurma(turma); // substitui a turma atual pelo //
		 * parametro passado. } } if (achou == false) { throw new
		 * ElementoNaoEncontradoException(); }
		 */
	}

	public void remover(String nome) throws ElementoNaoEncontradoException {
		boolean achou = false;
		RepositorioListaTurma repositorioAtual = null;
		RepositorioListaTurma repositorioProximo = this.prox;
		if (this.prox != null) {
			if (this.prox.getTurma().getNome().equals(nome)) {// Verifica se o
																// primeiro da
																// lista
																// e aquele que
																// eu
																// quero
																// remover.
				this.prox = this.prox.getProx();
				achou = true;
			} else {
				repositorioAtual = this.prox;
				repositorioProximo = repositorioAtual.getProx();
				while (achou != true && repositorioProximo != null) {
					if (repositorioProximo.getTurma().getNome().equals(nome)) {
						repositorioAtual.setProx(repositorioProximo.getProx());
						;
						achou = true;
					} else {
						repositorioAtual = repositorioProximo;
						repositorioProximo = repositorioProximo.getProx();
					}
				}
			}
		}
		if (achou == false) {
			throw new ElementoNaoEncontradoException();
		}
		/**
		 * Turma p = null; boolean achou = false;
		 * Iterator<RepositorioLista<Turma>> it = iterator();
		 * RepositorioLista<Turma> repositorioAtual = null;
		 * RepositorioLista<Turma> repositorioProximo = null; if
		 * (getProx().getTurma().getNome().equals(nome)) { p =
		 * getProx().getTurma(); setProx(getProx().getProx()); achou = true; }
		 * else { while (it.hasNext() && !achou) { try { repositorioAtual =
		 * ((IteratorLista<Turma>) it) .getRepositorio(); repositorioProximo =
		 * it.next(); p = repositorioProximo.getTurma(); } catch
		 * (NoSuchElementException e) { throw new
		 * ElementoNaoEncontradoException(); } if (p != null &&
		 * p.getNome().equals(nome)) { achou = true; System.out.println("achei "
		 * + p.getNome() + "\n\n");
		 * repositorioAtual.setProx(repositorioProximo.getProx()); } }
		 */

		/**
		 * if (achou == false) { throw new ElementoNaoEncontradoException(); }
		 */
	}

	public Iterator<Turma> iterator() {
		IteratorListaTurma it = new IteratorListaTurma(this.prox);// Passa
																	// como
																	// argumento
																	// o
																	// primeiro
																	// item da
																	// lista.
		return it;

	}

	@Override
	public Iterator<Turma> getIterator() {
		IteratorListaTurma it = new IteratorListaTurma(this.prox);// Passa
		// como
		// argumento
		// o
		// primeiro
		// item da
		// lista.
return it;
	}

}
