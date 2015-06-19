package escola.dados;

import java.util.Iterator;

import escola.classesBase.*;
import escola.excecoes.ElementoNaoEncontradoException;

public class RepositorioListaDisciplina implements Repositorio<Disciplina> {

	private Disciplina disciplina;
	private RepositorioListaDisciplina prox;

	public RepositorioListaDisciplina() {
		this.disciplina = null;
		this.prox = null;
	}

	public Disciplina getDisciplina() {
		return this.disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public RepositorioListaDisciplina getProx() {
		return this.prox;
	}

	public void setProx(RepositorioListaDisciplina prox) {
		this.prox = prox;
	}

	public void inserir(Disciplina disciplina) {

		if (this.prox == null) {
			RepositorioListaDisciplina novoRepositorio = new RepositorioListaDisciplina();
			novoRepositorio.setDisciplina(disciplina);
			setProx(novoRepositorio);
		} else {
			getProx().inserir(disciplina);
		}
	}

	public String imprimir() {
		RepositorioListaDisciplina repositorioAtual = this.prox;
		String resposta = "";
		while (repositorioAtual != null) {
			resposta = resposta + repositorioAtual.getDisciplina().getNome();
			repositorioAtual = repositorioAtual.getProx();
		}
		return resposta;
	}

	public Disciplina procurar(String nome)
			throws ElementoNaoEncontradoException {
		Disciplina d = null;
		boolean achou = false;
		RepositorioListaDisciplina repositorioAtual = this.prox;
		while (repositorioAtual != null && achou == false) {
			d = repositorioAtual.getDisciplina();
			if (d.getNome().equals(nome)) {
				achou = true;
			}
			repositorioAtual = repositorioAtual.getProx();
		}
		if (achou == false) {
			throw new ElementoNaoEncontradoException();
		}
		return d;
	}

	public RepositorioArrayDisciplina procurarNome(String nome)
			throws ElementoNaoEncontradoException {
		Disciplina d = null;
		boolean achou = false;
		RepositorioArrayDisciplina resultado = new RepositorioArrayDisciplina();
		RepositorioListaDisciplina repositorioAtual = this.prox;
		while (repositorioAtual != null) {
			d = repositorioAtual.getDisciplina();
			if (d.getNome().toLowerCase().contains(nome.toLowerCase())) {
				achou = true;
				resultado.inserir(d);
			}
			repositorioAtual = repositorioAtual.getProx();
		}
		if (achou == false) {
			throw new ElementoNaoEncontradoException();
		}
		return resultado;
	}

	public void atualizar(Disciplina disciplina)
			throws ElementoNaoEncontradoException {

		boolean achou = false;
		Disciplina d = null;
		RepositorioListaDisciplina repositorioAtual = this.prox;
		while ((repositorioAtual != null) && (achou == false)) {
			d = repositorioAtual.getDisciplina();
			if (d.getNome().equals(disciplina.getNome())) {
				repositorioAtual.setDisciplina(disciplina);
				achou = true;
			} else {
				repositorioAtual = repositorioAtual.getProx();
			}
		}
		if (achou == false) {
			throw new ElementoNaoEncontradoException();
		}
		/**
		 * boolean achou = false; Iterator<RepositorioLista<Disciplina>> it =
		 * iterator(); Disciplina p = null; RepositorioLista<Disciplina>
		 * repositorio = null; while (it.hasNext() && !achou) { try {
		 * repositorio = it.next(); p = repositorio.getDisciplina(); } catch
		 * (NoSuchElementException e) { throw new
		 * ElementoNaoEncontradoException(); } if (p != null &&
		 * p.getNome().equals(disciplina.getNome())) { achou = true;
		 * repositorio.setDisciplina(disciplina); // substitui a disciplina
		 * atual pelo // parametro passado. } } if (achou == false) { throw new
		 * ElementoNaoEncontradoException(); }
		 */
	}

	public void remover(String nome) throws ElementoNaoEncontradoException {
		boolean achou = false;
		RepositorioListaDisciplina repositorioAtual = null;
		RepositorioListaDisciplina repositorioProximo = this.prox;
		if (this.prox != null) {
			if (this.prox.getDisciplina().getNome().equals(nome)) {// Verifica
																	// se o
				// primeiro da lista
				// e aquele que eu
				// quero remover.
				this.prox = this.prox.getProx();
				achou = true;
			} else {
				repositorioAtual = this.prox;
				repositorioProximo = repositorioAtual.getProx();
				while (achou != true && repositorioProximo != null) {
					if (repositorioProximo.getDisciplina().getNome()
							.equals(nome)) {
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
		 * Disciplina p = null; boolean achou = false;
		 * Iterator<RepositorioLista<Disciplina>> it = iterator();
		 * RepositorioLista<Disciplina> repositorioAtual = null;
		 * RepositorioLista<Disciplina> repositorioProximo = null; if
		 * (getProx().getDisciplina().getNome().equals(nome)) { p =
		 * getProx().getDisciplina(); setProx(getProx().getProx()); achou =
		 * true; } else { while (it.hasNext() && !achou) { try {
		 * repositorioAtual = ((IteratorLista<Disciplina>) it)
		 * .getRepositorio(); repositorioProximo = it.next(); p =
		 * repositorioProximo.getDisciplina(); } catch (NoSuchElementException
		 * e) { throw new ElementoNaoEncontradoException(); } if (p != null &&
		 * p.getNome().equals(nome)) { achou = true; System.out.println("achei "
		 * + p.getNome() + "\n\n");
		 * repositorioAtual.setProx(repositorioProximo.getProx()); } }
		 */

		/**
		 * if (achou == false) { throw new ElementoNaoEncontradoException(); }
		 */
	}

	public Iterator<Disciplina> iterator() {
		IteratorListaDisciplina it = new IteratorListaDisciplina(this.prox);// Passa
		// como
		// argumento
		// o
		// primeiro
		// item da
		// lista.
		return it;

	}

	@Override
	public Iterator<Disciplina> getIterator() {
		IteratorListaDisciplina it = new IteratorListaDisciplina(this.prox);// Passa
		// como
		// argumento
		// o
		// primeiro
		// item da
		// lista.
		return it;
	}

}
