package biblioteca.fachada;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

import biblioteca.dados.entidades.Aluno;
import biblioteca.dados.entidades.Endereco;
import biblioteca.dados.entidades.Funcionario;
import biblioteca.dados.entidades.Livro;
import biblioteca.dados.entidades.Sala;
import biblioteca.dados.repositorio.array.RepositorioLivroArray;
import biblioteca.dados.repositorio.array.RepositorioPessoaArray;
import biblioteca.dados.repositorio.array.RepositorioSalaArray;
import biblioteca.dados.repositorio.lista.RepositorioLivroLista;
import biblioteca.dados.repositorio.lista.RepositorioPessoaLista;
import biblioteca.dados.repositorio.lista.RepositorioSalaLista;
import biblioteca.negocios.ManipularLivro;
import biblioteca.negocios.ManipularPessoa;
import biblioteca.negocios.ManipularSala;
import biblioteca.negocios.exceptions.ConfirmarAcaoException;
import biblioteca.negocios.exceptions.ItemNaoEncontradoException;
import biblioteca.negocios.exceptions.RealizarLoginException;
import biblioteca.negocios.exceptions.RepositorioInvalidoException;
import biblioteca.negocios.exceptions.endereco.EnderecoInvalidoException;
import biblioteca.negocios.exceptions.livro.LivroComEmprestimoException;
import biblioteca.negocios.exceptions.livro.LivroIndisponivelException;
import biblioteca.negocios.exceptions.livro.LivroInvalidoException;
import biblioteca.negocios.exceptions.livro.LivroJaCadastradoException;
import biblioteca.negocios.exceptions.livro.LivroNaoEncontradoException;
import biblioteca.negocios.exceptions.pessoa.AlunoComEmprestimoException;
import biblioteca.negocios.exceptions.pessoa.CpfInvalidoException;
import biblioteca.negocios.exceptions.pessoa.MultaGeradaException;
import biblioteca.negocios.exceptions.pessoa.PessoaInvalidaException;
import biblioteca.negocios.exceptions.pessoa.PessoaJaCadastradaException;
import biblioteca.negocios.exceptions.pessoa.PessoaNaoEncontradaException;
import biblioteca.negocios.exceptions.pessoa.SenhaInvalidaException;
import biblioteca.negocios.exceptions.pessoa.TelefoneInvalidoException;
import biblioteca.negocios.exceptions.sala.SalaComEmprestimoException;
import biblioteca.negocios.exceptions.sala.SalaIndisponivelException;
import biblioteca.negocios.exceptions.sala.SalaInvalidaException;
import biblioteca.negocios.exceptions.sala.SalaJaCadastradaException;
import biblioteca.negocios.exceptions.sala.SalaNaoEncontradaException;

public class Principal {

	private ManipularPessoa mPessoa;
	private ManipularLivro mLivro;
	private ManipularSala mSala;
	private boolean sistemaLogado;

	public Principal() {
		mPessoa = null;
		mLivro = null;
		sistemaLogado = false;
	}

	public boolean getSistemaLogado() {
		return this.sistemaLogado;
	}

	public String carregarConfig() throws FileNotFoundException,
			RepositorioInvalidoException {
		// Lembrar sempre de alterar o local do diretorio do arquivo..
		String nomeArqEnt = "C:\\Users\\CaioCalado\\Dropbox\\UFPE\\Ci�ncia da Computa��o\\Ci�ncia da Computa��o 2012.2\\if-669\\Projeto - IP\\Biblioteca_2\\CONFIG.txt";
		File arqEnt = new File(nomeArqEnt);
		Scanner ent = new Scanner(arqEnt);

		String repositorio = ent.nextLine();
		ent.close();

		int index = repositorio.indexOf("=");
		repositorio = repositorio.substring((index + 1), repositorio.length());

		// retirando os espa�os vazios que antecedem o tipo do repositorio
		while (repositorio.length() > 0 && repositorio.charAt(0) == ' ') {
			repositorio = repositorio.substring(1, repositorio.length());
		}

		// retirando os espa�os vazios que sucedem o tipo do repositorio
		while (repositorio.length() > 0
				&& repositorio.charAt(repositorio.length() - 1) == ' ') {
			repositorio = repositorio.substring(0, (repositorio.length() - 1));
		}

		repositorio = repositorio.toUpperCase();
		if (!(repositorioValido(repositorio))) {
			throw new RepositorioInvalidoException(repositorio);
		}
		return repositorio;
	}

	private boolean repositorioValido(String repositorio) {
		boolean resposta = false;
		String array = "ARRAY";
		String lista = "LISTA";
		String arquivo = "ARQUIVO";

		if (array.equals(repositorio) || lista.equals(repositorio)
				|| arquivo.equals(repositorio)) {
			resposta = true;
		}
		return resposta;
	}

	public boolean carregarRepositorio(String tipoRepositorio)
			throws PessoaJaCadastradaException, IOException {
		boolean ok = false;

		if (tipoRepositorio.equals("ARRAY")) {
			RepositorioPessoaArray repositorioPessoa = new RepositorioPessoaArray();
			RepositorioLivroArray repositorioLivro = new RepositorioLivroArray();
			RepositorioSalaArray repositorioSala = new RepositorioSalaArray();
			this.mPessoa = new ManipularPessoa(repositorioPessoa);
			this.mLivro = new ManipularLivro(repositorioLivro);
			this.mSala = new ManipularSala(repositorioSala);

			this.mPessoa.iniciarSistema();
			ok = true;
		} else if (tipoRepositorio.equals("LISTA")) {
			RepositorioPessoaLista repositorioPessoa = new RepositorioPessoaLista();
			RepositorioLivroLista repositorioLivro = new RepositorioLivroLista();
			RepositorioSalaLista repositorioSala = new RepositorioSalaLista();
			this.mPessoa = new ManipularPessoa(repositorioPessoa);
			this.mLivro = new ManipularLivro(repositorioLivro);
			this.mSala = new ManipularSala(repositorioSala);
			System.out.println("LISTA!");
			this.mPessoa.iniciarSistema();
			ok = true;
		} else if (tipoRepositorio.equals("ARQUIVO")) {

		}
		return ok;
	}

	public void realizarLogin(String cpf, String senha)
			throws PessoaNaoEncontradaException, SenhaInvalidaException,
			PessoaInvalidaException, IOException {
		if (mPessoa.realizarLoginFuncionario(cpf, senha)) {
			sistemaLogado = true;
		}
	}

	public void cadastrarAluno(String nome, String cpf, String telefone,
			String senha, String rua, String numero, String complemento,
			String cep) throws EnderecoInvalidoException, CpfInvalidoException,
			PessoaJaCadastradaException, IOException, TelefoneInvalidoException {

		Endereco endereco = mPessoa
				.criarEndereco(rua, numero, complemento, cep);

		Aluno aluno = mPessoa.criarAluno(nome, cpf, telefone, senha, endereco);
		mPessoa.cadastrarAluno(aluno);
	}

	public void cadastrarFuncionario(String nome, String cpf, String telefone,
			String senha, String rua, String numero, String complemento,
			String cep) throws EnderecoInvalidoException, CpfInvalidoException,
			PessoaJaCadastradaException, IOException, TelefoneInvalidoException {

		Endereco endereco = mPessoa
				.criarEndereco(rua, numero, complemento, cep);
		Funcionario func = mPessoa.criarFuncionario(nome, cpf, telefone, senha,
				endereco);
		mPessoa.cadastrarFuncionario(func);
	}

	public void cadastrarLivro(String codigo, String titulo, String autor,
			String assunto, String sinopse) throws LivroInvalidoException,
			LivroNaoEncontradoException, IOException, RealizarLoginException,
			LivroJaCadastradoException {
		Livro livro = mLivro
				.criarLivro(codigo, titulo, autor, assunto, sinopse);

		mLivro.cadastrarLivro(livro);
		mLivro.selecionarLivro(codigo);
		mLivro.inserirLivroAcervo();
		mLivro.deselecionarLivro();

	}

	public void cadastrarSala(String codigo, int capacidade)
			throws SalaInvalidaException, SalaJaCadastradaException,
			IOException {
		Sala sala = mSala.criarSala(codigo, capacidade);
		mSala.cadastrarSala(sala);
	}

	// ---------------------------------------------
	public void realizarEmprestimoLivro(String cpf, String senha, String codigo)
			throws PessoaNaoEncontradaException, LivroNaoEncontradoException,
			IOException, LivroIndisponivelException, PessoaInvalidaException,
			RealizarLoginException, SenhaInvalidaException {
		mPessoa.selecionarAluno(cpf);
		mPessoa.confirmarSenhaAluno(senha); // Verifica��o do aluno

		mLivro.selecionarLivro(codigo);
		String[] datas = getDatasLivro();

		mLivro.realizarEmprestimoLivro(datas[1], datas[0], cpf);
		String[] livroDados = mLivro.getCacheDados();

		mPessoa.realizarEmprestimo(livroDados[0], livroDados[1], datas[0],
				datas[1]);
		mLivro.deselecionarLivro();
		mPessoa.deselecionarAluno();
	}

	public void realizarEmprestimoSala(String cpfAluno, String alunoSenha,
			String codigoSala) throws PessoaNaoEncontradaException,
			IOException, PessoaInvalidaException, RealizarLoginException,
			SenhaInvalidaException, SalaNaoEncontradaException,
			SalaIndisponivelException {
		mPessoa.selecionarAluno(cpfAluno);
		mPessoa.confirmarSenhaAluno(alunoSenha);

		mSala.selecionarSala(codigoSala);
		String[] datas = getDatasSala();

		mSala.realizarEmprestimoSala(datas[1], cpfAluno);
		String[] salaDados = mSala.getCacheDados();

		mPessoa.realizarEmprestimo(salaDados[0], salaDados[1], datas[0],
				datas[1]);
		mSala.deselecionarSala();
		mPessoa.deselecionarAluno();
	}

	public void realizarDevolucaoLivro(String cpf, String codigo)
			throws PessoaNaoEncontradaException, IOException,
			PessoaInvalidaException, MultaGeradaException,
			LivroNaoEncontradoException, ItemNaoEncontradoException,
			RealizarLoginException {
		mPessoa.selecionarAluno(cpf);
		mLivro.selecionarLivro(codigo);

		mPessoa.realizarDevolucao(codigo, getDataAtual());
		mLivro.realizarDevolucaoLivro(cpf);

		mPessoa.deselecionarAluno();
		mLivro.deselecionarLivro();
	}

	public void confirmarDevolucaoLivro(boolean confirma, String cpf)
			throws RealizarLoginException {
		if (confirma) {
			mPessoa.confirmarDevolucao();
			mLivro.realizarDevolucaoLivro(cpf);
		}
		mPessoa.deselecionarAluno();
		mLivro.deselecionarLivro();
	}

	public void realizarDevolucaoSala(String cpf, String codigo)
			throws PessoaNaoEncontradaException, IOException,
			PessoaInvalidaException, SalaNaoEncontradaException,
			MultaGeradaException, ItemNaoEncontradoException,
			RealizarLoginException {
		mPessoa.selecionarAluno(cpf);
		mSala.selecionarSala(codigo);

		mPessoa.realizarDevolucao(codigo, getDataAtual());
		mSala.realizarDevolucaoSala();

		mPessoa.deselecionarAluno();
		mSala.deselecionarSala();
	}

	public void confirmarDevolucaoSala(boolean confirma)
			throws RealizarLoginException {
		if (confirma) {
			mPessoa.confirmarDevolucao();
			mSala.realizarDevolucaoSala();
		}
		mPessoa.deselecionarAluno();
		mSala.deselecionarSala();
	}

	// ---------------------------------------------

	public void realizarExclusaoAluno(String senhaFuncionario)
			throws PessoaNaoEncontradaException, IOException,
			SenhaInvalidaException, ConfirmarAcaoException {
		if (mPessoa.confirmarSenhaFuncionario(senhaFuncionario)) {
			throw new ConfirmarAcaoException();
		}

	}

	public void confirmarExclusaoAluno(String cpf)
			throws PessoaNaoEncontradaException, IOException,
			PessoaInvalidaException, AlunoComEmprestimoException {

		mPessoa.removerAluno(cpf);

	}

	public void realizarExclusaoFuncionario(String senhaFuncionario)
			throws SenhaInvalidaException, ConfirmarAcaoException {

		if (mPessoa.confirmarSenhaFuncionario(senhaFuncionario)) {
			throw new ConfirmarAcaoException();
		}
	}

	public void confirmarExclusaoFuncionario(String cpf)
			throws PessoaNaoEncontradaException, IOException,
			PessoaInvalidaException {
		mPessoa.removerFuncionario(cpf);

	}

	public void realizarExclusaoLivro(String senhaFuncionario)
			throws SenhaInvalidaException, ConfirmarAcaoException {
		if (mPessoa.confirmarSenhaFuncionario(senhaFuncionario)) {
			throw new ConfirmarAcaoException();
		}
	}

	public void confirmarExclusaoLivro(String codigo)
			throws LivroNaoEncontradoException, IOException,
			RealizarLoginException, LivroComEmprestimoException {
		mLivro.realizarExclusao(codigo);
	}

	public void realizarExclusaoSala(String codigo, String senhaFuncionario)
			throws ConfirmarAcaoException, SenhaInvalidaException {
		if (mPessoa.confirmarSenhaFuncionario(senhaFuncionario)) {
			throw new ConfirmarAcaoException();
		}
	}

	public void confirmarExclusaoSala(String codigo)
			throws SalaNaoEncontradaException, IOException,
			SalaComEmprestimoException {
		mSala.realizarExclusao(codigo);
	}

	// ---------------------------------------------

	public String autalizarPrintDadosAluno(String cpf)
			throws PessoaNaoEncontradaException, IOException,
			PessoaInvalidaException, RealizarLoginException {
		mPessoa.selecionarAluno(cpf);
		String aux = mPessoa.getDadosPessoa();
		return aux;
	}

	public String atualizarPrintDadosFuncionario(String cpf)
			throws PessoaNaoEncontradaException, IOException,
			RealizarLoginException, PessoaInvalidaException {
		String aux = mPessoa.getDadosFuncionario(cpf);
		return aux;
	}

	public String atualizarPrintDadosSala(String codigo)
			throws SalaNaoEncontradaException, IOException {
		mSala.selecionarSala(codigo);
		String aux = mSala.getDadosSala();
		return aux;
	}

	public String atualizarPrintDadosLivro(String codigo)
			throws LivroNaoEncontradoException, IOException {
		mLivro.selecionarLivro(codigo);
		String aux = mLivro.getDadosLivro();
		return aux;
	}

	public void atualizarInfoSala(String senhaFuncionario, int info,
			int infoTipo) throws SalaInvalidaException, RealizarLoginException,
			SenhaInvalidaException, SalaIndisponivelException {
		if (mPessoa.confirmarSenhaFuncionario(senhaFuncionario)) {
			mSala.confirmarAtualizarSala(info, infoTipo);
			mSala.deselecionarSala();
		}
	}

	public void atualizarInfoAluno(String senhaFuncionario, String info,
			int infoTipo) throws SenhaInvalidaException,
			EnderecoInvalidoException, RealizarLoginException, TelefoneInvalidoException {

		if (mPessoa.confirmarSenhaFuncionario(senhaFuncionario)) {
			mPessoa.confirmaAtualizarAluno(info, infoTipo);
			mPessoa.deselecionarAluno();
		}
	}

	public void atualizarInfoFuncionario(String senhaFuncionario, String cpf,
			String info, int infoTipo) throws SenhaInvalidaException,
			EnderecoInvalidoException, PessoaNaoEncontradaException,
			IOException, PessoaInvalidaException, TelefoneInvalidoException {

		if (mPessoa.confirmarSenhaFuncionario(senhaFuncionario)) {
			mPessoa.confirmaAtualizarFuncionario(cpf, info, infoTipo);
		}

	}

	public void atualizarInfoLivro(String senhaFuncionario, String info,
			int infoTipo) throws SenhaInvalidaException,
			RealizarLoginException, LivroIndisponivelException,
			LivroInvalidoException {
		if (mPessoa.confirmarSenhaFuncionario(senhaFuncionario)) {
			mLivro.confirmarAtualizarSala(info, infoTipo);
			mLivro.deselecionarLivro();
		}

	}

	// ---------------------------------------------

	public String getDataAtual() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String aux = sdf.format(cal.getTime());
		return aux;
	}

	public String[] getDatasLivro() {
		String datas[] = new String[2];
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		datas[0] = sdf.format(cal.getTime());
		cal.add(Calendar.DATE, 7);
		datas[1] = sdf.format(cal.getTime());
		return datas;
	}

	public String[] getDatasSala() {
		String datas[] = new String[2];
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		datas[0] = sdf.format(cal.getTime());
		cal.add(Calendar.DATE, 2);
		datas[1] = sdf.format(cal.getTime());
		return datas;
	}

	public String printRepositorio() {
		String message = "PESSOAS\n";
		message += mPessoa.printRepositorio();
		message += "\n\n##\n\n";
		message += "LIVROS\n";
		message += mLivro.printRepositorio();
		message += "\n\n##\n\n";
		message += "SALAS\n";
		message += mSala.printRepositorio();
		return message;
	}

	public String printRepositorioAluno() {
		String message = "PESSOAS\n";
		message += mPessoa.printRepositorio();
		return message;
	}

	public String printRepositorioLivro() {
		String message = "LIVRO\n";
		message += mLivro.printRepositorio();
		return message;
	}

	public String printRepositorioSala() {
		String message = "SALA\n";
		message += mSala.printRepositorio();
		return message;
	}

	public String getRelatorioSala(int parametro) {
		String message = "";

		message = mSala.gerarRelatorio(parametro);

		return message;
	}

}
