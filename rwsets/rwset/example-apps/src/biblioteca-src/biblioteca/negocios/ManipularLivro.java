package biblioteca.negocios;

import java.io.IOException;

import biblioteca.dados.IteratorLivro;
import biblioteca.dados.entidades.Livro;
import biblioteca.dados.repositorio.interfaces.RepositorioLivro;
import biblioteca.negocios.exceptions.RealizarLoginException;
import biblioteca.negocios.exceptions.livro.*;
import biblioteca.negocios.exceptions.pessoa.SenhaInvalidaException;

public class ManipularLivro {

	private RepositorioLivro generico;
	private Livro cache; // vai ser utilizado como uma cache do sistema

	public ManipularLivro(RepositorioLivro generico) {
		this.generico = generico;
		this.cache = null;
	}

	public void cadastrarLivro(Livro livro) throws LivroJaCadastradoException,
			IOException {
		generico.inserirLivro(livro);
	}

	public void selecionarLivro(String codigo)
			throws LivroNaoEncontradoException, IOException {
		cache = generico.procurarLivro(codigo);
	}
	
	
	public void deselecionarLivro() {
		cache = null;
	}

	public Livro criarLivro(String codigo, String titulo, String autor,
			String assunto, String sinopse) throws LivroInvalidoException {
		Livro livro = null;
		boolean erro = false;
		String erroMessage = "";
		if (!campoValido(codigo)) {
			erro = true;
			erroMessage += "codigo";
		}

		if (!campoValido(titulo)) {
			if (erro) {
				erroMessage += ", t�tulo";
			} else {
				erro = true;
				erroMessage += "t�tulo";
			}
		}

		if (!campoValido(assunto)) {
			if (erro) {
				erroMessage += ", assunto";
			} else {
				erro = true;
				erroMessage += "assunto";
			}
		}

		if (!erro) {
			livro = new Livro(codigo, titulo, autor, assunto, sinopse);
		} else {
			throw new LivroInvalidoException(erroMessage);
		}
		return livro;
	}

	private boolean campoValido(String info) {
		/*
		 * Essa valida��o apenas verifica se algum campo est� vazio. Caso
		 * esteja, lan�ar� uma exce��o pois todos os campos que s�o validados
		 * s�o obrigat�rios Para livro, os campos s�o: -Titulo -Autor -Assunto
		 */
		boolean resposta = true;
		if (info == null || info.length() <= 0) {
			resposta = false;
		}

		return resposta;
	}

	private boolean quantidadeValida(int qtd) {
		boolean resposta = false;

		if (qtd > 0) {
			resposta = true;
		}

		return resposta;
	}

	// ---------------------------------------------

	public void realizarEmprestimoLivro(String dataDevolucao,
			String dataEmprestimo, String cpf)
			throws LivroIndisponivelException, RealizarLoginException {
		if (cache != null) {
			cache.emprestar(dataDevolucao, dataEmprestimo, cpf);
		} else {
			throw new RealizarLoginException();
		}
	}

	public void realizarDevolucaoLivro(String cpf)
			throws RealizarLoginException {

		if (cache != null) {
			cache.devolver(cpf);
		} else {
			throw new RealizarLoginException();
		}
	}

	// ---------------------------------------------

	public void inserirLivroAcervo() throws RealizarLoginException {
		if (cache != null) {
			cache.inserirLivroAcervo();
		} else {
			throw new RealizarLoginException();
		}
	}

	public void removerLivroAcervo() throws AcervoIndisponivelException,
			RealizarLoginException {
		if (cache != null) {
			if (cache.getTotalAcervo() > 0) {
				cache.retirarLivroAcervo();
			} else {
				throw new AcervoIndisponivelException(cache.getCodigo());
			}
		} else {
			throw new RealizarLoginException();
		}
	}

	// ---------------------------------------------

	public void confirmarAtualizarSala(String info, int infoTipo)
			throws RealizarLoginException, LivroIndisponivelException,
			LivroInvalidoException {
		// TODO Auto-generated method stub

		if (cache != null) {
			if (!cache.hasEmprestimo()) {
				switch (infoTipo) {
				case 1: {
					// t�tulo
					if (campoValido(info)) {
						cache.setTitulo(info);
					} else {
						throw new LivroInvalidoException("t�tulo");
					}

					break;
				}
				case 2: {
					// Autor
					if (campoValido(info)) {
						cache.setAutor(info);
					} else {
						throw new LivroInvalidoException("autor");
					}
					break;
				}
				case 3: {
					// Assunto
					if (campoValido(info)) {
						cache.setAssunto(info);
					} else {
						throw new LivroInvalidoException("assunto");
					}
					break;
				}
				case 4: {
					// Sinopse
					cache.setTitulo(info);
					break;
				}
				case 5: {
					// Adicionar Livro no Acervo
					if (quantidadeValida(Integer.parseInt(info))) {
						int dif = cache.getTotalAcervo()
								- Integer.parseInt(info);
						for (int i = 0; i < dif; i++) {
							cache.inserirLivroAcervo();
						}
					} else {
						throw new LivroInvalidoException("quantidade");
					}
					break;
				}
				}
			} else {
				/*
				 * Caso o livro esteja emprestado n�o ser� poss�vel realizar uma
				 * altera��o de dados, com a exce��o de inserir livros no
				 * acervo.
				 */
				if (infoTipo == 5) {
					/*
					 * Caso a a��o seja inserir um livro.. essa a��o ser�
					 * poss�vel, pois n�o ira atrapalhar no funcionamento da
					 * bilbioteca.
					 */
					if (cache.getTotalAcervo() > Integer.parseInt(info)) {
						throw new LivroInvalidoException("quantidade");
					} else {
						int dif = Integer.parseInt(info) - cache.getTotalAcervo();
						for (int i = 0; i < dif; i++) {
							cache.inserirLivroAcervo();
						}
					}
				} else {
					throw new LivroIndisponivelException(cache.getCodigo());
				}
			}
		} else {
			throw new RealizarLoginException();
		}

	}

	// ---------------------------------------------

	public String getDadosLivro() throws LivroNaoEncontradoException {
		String resposta = "";

		if (cache != null) {
			resposta = cache.toString();
		} else {
			throw new LivroNaoEncontradoException(cache.getCodigo());
		}

		return resposta;
	}

	// ----------------------------------------------

	public String[] getCacheDados() {
		String[] dados = new String[2];
		if (cache != null) {
			dados[0] = cache.getCodigo();
			dados[1] = "LIVRO";
		}

		return dados;
	}

	public void realizarExclusao(String codigo)
			throws LivroNaoEncontradoException, IOException,
			RealizarLoginException, LivroComEmprestimoException {
		selecionarLivro(codigo);
		if (cache != null) {
			if (cache.hasEmprestimo()) {
				deselecionarLivro();
				throw new LivroComEmprestimoException(codigo);
			} else {
				deselecionarLivro();
				generico.removerLivro(codigo);
			}
		} else {
			throw new RealizarLoginException();
		}

	}

	public String printRepositorio() {
		IteratorLivro it = generico.getIterator();
		String message = "";

		while (it.hasNext()) {
			message += it.next().toString() + "\n";
		}
		return message;
	}

}
