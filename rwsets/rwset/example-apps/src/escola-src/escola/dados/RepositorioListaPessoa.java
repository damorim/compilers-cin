package escola.dados;

import java.util.Iterator;

import escola.classesBase.*;
import escola.excecoes.ElementoNaoEncontradoException;

public class RepositorioListaPessoa implements Repositorio<Pessoa> {

	private Pessoa pessoa;
	private RepositorioListaPessoa prox;

	public RepositorioListaPessoa() {
		this.pessoa = null;
		this.prox = null;
	}

	public Pessoa getPessoa() {
		return this.pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public RepositorioListaPessoa getProx() {
		return this.prox;
	}

	public void setProx(RepositorioListaPessoa prox) {
		this.prox = prox;
	}

	public void inserir(Pessoa pessoa) {

		if (this.prox == null) {
			RepositorioListaPessoa novoRepositorio = new RepositorioListaPessoa();
			novoRepositorio.setPessoa(pessoa);
			setProx(novoRepositorio);
		} else {
			getProx().inserir(pessoa);
		}
	}

	public String imprimir() {
		RepositorioListaPessoa repositorioAtual = this.prox;
		String resposta = "";
		while (repositorioAtual != null) {
			resposta = resposta + repositorioAtual.getPessoa().getNome() + "\n";
			repositorioAtual = repositorioAtual.getProx();
		}
		return resposta;
	}

	public Pessoa procurar(String cpf) throws ElementoNaoEncontradoException {
		Pessoa p = null;
		boolean achou = false;
		// Iterator<RepositorioLista<Pessoa>> it = iterator();
		RepositorioListaPessoa repositorioAtual = this.prox;
		while (repositorioAtual != null && achou == false) {
			p = repositorioAtual.getPessoa();
			if (p.getCpf().equals(cpf)) {
				achou = true;
			}
			repositorioAtual = repositorioAtual.getProx();
		}
		if (achou == false) {
			throw new ElementoNaoEncontradoException();
		}
		return p;
		/**
		 * while (it.hasNext() && !achou) { try { p = it.next().getPessoa(); }
		 * catch (NoSuchElementException e) { throw new
		 * ElementoNaoEncontradoException(); } if (p != null &&
		 * p.getCpf().equals(cpf)) { achou = true; } } if (achou == false) {
		 * throw new ElementoNaoEncontradoException(); } return p;
		 */
	}

	public RepositorioArrayPessoa procurarNome(String nome)
			throws ElementoNaoEncontradoException {
		Pessoa p = null;
		boolean achou = false;
		RepositorioArrayPessoa resultado = new RepositorioArrayPessoa();
		RepositorioListaPessoa repositorioAtual = this.prox;
		while (repositorioAtual != null) {
			p = repositorioAtual.getPessoa();
			if (p.getNome().toLowerCase().contains(nome.toLowerCase())) {
				achou = true;
				resultado.inserir(p);
			}
			repositorioAtual = repositorioAtual.getProx();
		}
		if (achou == false) {
			throw new ElementoNaoEncontradoException();
		}
		return resultado;
	}

	public void atualizar(Pessoa pessoa) throws ElementoNaoEncontradoException {

		boolean achou = false;
		Pessoa p = null;
		RepositorioListaPessoa repositorioAtual = this.prox;
		while ((repositorioAtual != null) && (achou == false)) {
			p = repositorioAtual.getPessoa();
			if (p.getCpf().equals(pessoa.getCpf())) {
				repositorioAtual.setPessoa(pessoa);
				achou = true;
			} else {
				repositorioAtual = repositorioAtual.getProx();
			}
		}
		if (achou == false) {
			throw new ElementoNaoEncontradoException();
		}
		/**
		 * boolean achou = false; Iterator<RepositorioLista<Pessoa>> it =
		 * iterator(); Pessoa p = null; RepositorioLista<Pessoa> repositorio =
		 * null; while (it.hasNext() && !achou) { try { repositorio = it.next();
		 * p = repositorio.getPessoa(); } catch (NoSuchElementException e) {
		 * throw new ElementoNaoEncontradoException(); } if (p != null &&
		 * p.getCpf().equals(pessoa.getCpf())) { achou = true;
		 * repositorio.setPessoa(pessoa); // substitui a pessoa atual pelo //
		 * parametro passado. } } if (achou == false) { throw new
		 * ElementoNaoEncontradoException(); }
		 */
	}

	public void remover(String cpf) throws ElementoNaoEncontradoException {
		boolean achou = false;
		RepositorioListaPessoa repositorioAtual = null;
		RepositorioListaPessoa repositorioProximo = this.prox;
		if (this.prox != null) {
			if (this.prox.getPessoa().getCpf().equals(cpf)) {// Verifica se o
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
					if (repositorioProximo.getPessoa().getCpf().equals(cpf)) {
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
		 * Pessoa p = null; boolean achou = false;
		 * Iterator<RepositorioLista<Pessoa>> it = iterator();
		 * RepositorioLista<Pessoa> repositorioAtual = null;
		 * RepositorioLista<Pessoa> repositorioProximo = null; if
		 * (getProx().getPessoa().getCpf().equals(cpf)) { p =
		 * getProx().getPessoa(); setProx(getProx().getProx()); achou = true; }
		 * else { while (it.hasNext() && !achou) { try { repositorioAtual =
		 * ((IteratorLista<Pessoa>) it) .getRepositorio(); repositorioProximo =
		 * it.next(); p = repositorioProximo.getPessoa(); } catch
		 * (NoSuchElementException e) { throw new
		 * ElementoNaoEncontradoException(); } if (p != null &&
		 * p.getCpf().equals(cpf)) { achou = true; System.out.println("achei " +
		 * p.getNome() + "\n\n");
		 * repositorioAtual.setProx(repositorioProximo.getProx()); } }
		 */

		/**
		 * if (achou == false) { throw new ElementoNaoEncontradoException(); }
		 */
	}

	public Iterator<Pessoa> iterator() {
		IteratorListaPessoa it = new IteratorListaPessoa(this.prox);// Passa
																	// como
																	// argumento
																	// o
																	// primeiro
																	// item da
																	// lista.
		return it;

	}

	@Override
	public Iterator<Pessoa> getIterator() {
		IteratorListaPessoa it = new IteratorListaPessoa(this.prox);// Passa
		// como
		// argumento
		// o
		// primeiro
		// item da
		// lista.
return it;
	}

}
