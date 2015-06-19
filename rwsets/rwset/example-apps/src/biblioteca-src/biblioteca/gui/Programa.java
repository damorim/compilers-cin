package biblioteca.gui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import biblioteca.fachada.Principal;
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

public class Programa {

	public static void main(String[] args) {
		Principal fachada = new Principal();
		String tipoRepositorio;
		boolean first = true;
		try {
			tipoRepositorio = fachada.carregarConfig();

		} catch (FileNotFoundException e) {

			tipoRepositorio = "ARRAY";

		} catch (RepositorioInvalidoException e) {

			tipoRepositorio = "ARRAY";

		}

		try {
			if (fachada.carregarRepositorio(tipoRepositorio)) {

				fachada = realizarLogin(fachada);
			}
		} catch (PessoaJaCadastradaException e) {
			// Poss�vel exce��o que pode ser lan�ada ao iniciar o sistema
			// quem lan�a essa exce��o � o repositorio quando insere
			// o admin
			System.out.println("Erro no reposit�rio");
		} catch (IOException e) {
			// Tratar Exce��o
		}

		fachada = exibirMenuAcao(fachada, first);
	}

	public static Principal realizarLogin(Principal fachada) {
		String cpf = askInfo("Login:");
		String senha = askInfo("Senha:");

		try {
			fachada.realizarLogin(cpf, senha);
		} catch (PessoaNaoEncontradaException e) {
			System.out.println("Login inv�lido. Tente novamente.");
			fachada = realizarLogin(fachada);
		} catch (SenhaInvalidaException e) {
			System.out.println("Senha inv�lida. Tente novamente.");
			fachada = realizarLogin(fachada);
		} catch (PessoaInvalidaException e) {
			System.out.println(e.getMessage());
			fachada = realizarLogin(fachada);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fachada;
	}

	public static Principal exibirMenuAcao(Principal fachada, boolean first) {
		String message = "";
		if (first) {
			message += "\nBem-vindo a Biblioteca do CIn!\n";
			first = false;
		}
		message += "\nescolha uma op��o:\n";
		message += "\t1 - Emprestimo/Devolu��o\n";
		message += "\t2 - Cadastro\n";
		message += "\t3 - Atualiza��o\n";
		message += "\t4 - Remor��o\n";
		message += "\t5 - Relat�rio\n";
		message += "\t6 - Procurar\n";
		message += "\t7 - Sair";

		int menu = askNumber(message);
		while (menu < 1 || menu > 7) {
			menu = askNumber(message);
		}

		switch (menu) {
		case 1: {
			// Emprestimo
			int escolha = opcaoLivroSala();
			switch (escolha) {
			case 1: {
				fachada = opcaoAlunoLivro(fachada);
				break;
			}
			case 2: {
				fachada = opcaoAlunoSala(fachada);
				break;
			}
			case 3: {
				break;
			}
			}
			fachada = exibirMenuAcao(fachada, first);
			break;
		}
		case 2: {
			// Cadastro
			fachada = opcaoCadastro(fachada);
			fachada = exibirMenuAcao(fachada, first);
			break;
		}
		case 3: {
			// Atualiza��o
			fachada = opcaoAtualizar(fachada);
			fachada = exibirMenuAcao(fachada, first);
			break;
		}
		case 4: {
			// Remor��o
			fachada = opcaoRemover(fachada);
			fachada = exibirMenuAcao(fachada, first);
			break;
		}
		case 5: {
			// Relatorio
			// System.out.println(fachada.printRepositorio() + "\n");
			fachada = opcaoRelatorio(fachada);
			fachada = exibirMenuAcao(fachada, first);
			break;
		}
		case 6: {
			// Procurar
			//
			fachada = opcaoProcurar(fachada);
			fachada = exibirMenuAcao(fachada, first);
			break;
		}
		case 7: {
			// Sair
			// Salvar informa��es no repositorio.. caso seja ARQUIVO
			break;
		}
		}
		return fachada;
	}

	// --------------------------------------------------------------

	static String askInfo(String info) {
		Scanner in = new Scanner(System.in);
		System.out.print(info + "  ");
		String aux = in.nextLine();
		return aux;
	}

	static int askNumber(String info) {
		System.out.println(info);
		Scanner in = new Scanner(System.in);
		String a = in.next();

		int num = Integer.parseInt(a);
		return num;
	}

	// --------------------------------------------------------------

	private static int opcaoAlunoLivroSala() {
		String message = "";
		message += "escolha uma op��o:\n";
		message += "\t1 - Aluno\n";
		message += "\t2 - Livro\n";
		message += "\t3 - Sala\n";
		message += "\t4 - Voltar\n";

		int menu = askNumber(message);
		while (menu < 1 || menu > 4) {
			menu = askNumber(message);
		}

		return menu;
	}

	private static int opcaoAlunoLivroSalaFuncionario() {
		String message = "";
		message += "escolha uma op��o:\n";
		message += "\t1 - Aluno\n";
		message += "\t2 - Livro\n";
		message += "\t3 - Funcionario\n";
		message += "\t4 - Sala\n";
		message += "\t5 - Voltar\n";

		int menu = askNumber(message);
		while (menu < 1 || menu > 5) {
			menu = askNumber(message);
		}

		return menu;
	}

	private static int opcaoLivroSala() {
		String message = "";
		message += "escolha uma op��o:\n";
		message += "\t1 - Livro\n";
		message += "\t2 - Sala\n";
		message += "\t3 - Voltar\n";

		int menu = askNumber(message);
		while (menu < 1 || menu > 3) {
			menu = askNumber(message);
		}

		return menu;
	}

	// --------------------------------------------------------------

	private static Principal opcaoAlunoSala(Principal fachada) {
		String message = "";
		message += "escolha uma op��o:\n";
		message += "\t1 - Realizar Empr�stimo\n";
		message += "\t2 - Realizar Devolu��o\n";
		message += "\t3 - Voltar\n";

		int menu = askNumber(message);
		while (menu < 1 || menu > 3) {
			menu = askNumber(message);
		}

		switch (menu) {
		case 1: {
			// emprestimo
			fachada = alunoRealizarLoginEmprestimoSala(fachada);
			break;
		}
		case 2: {
			// devolucao
			fachada = alunoRealizarLoginDevolucaoSala(fachada);
			break;
		}
		case 3: {
			// voltar
			break;
		}
		}

		return fachada;
	}

	private static Principal opcaoAlunoLivro(Principal fachada) {
		String message = "";
		message += "escolha uma op��o:\n";
		message += "\t1 - Realizar Empr�stimo\n";
		message += "\t2 - Realizar Devolu��o\n";
		message += "\t3 - Voltar\n";

		int menu = askNumber(message);
		while (menu < 1 || menu > 3) {
			menu = askNumber(message);
		}

		switch (menu) {
		case 1: {
			// emprestimo
			fachada = alunoRealizarLoginEmprestimoLivro(fachada);
			break;
		}
		case 2: {
			// devolu��o
			fachada = alunoRealizarLoginDevolucaoLivro(fachada);
			break;
		}
		case 3: {
			// voltar
			break;
		}
		}

		return fachada;
	}

	private static Principal opcaoCadastro(Principal fachada) {
		String message = "";
		message += "escolha uma op��o:\n";
		message += "\t1 - Novo Aluno\n";
		message += "\t2 - Novo Livro\n";
		message += "\t3 - Novo Funcion�rio\n";
		message += "\t4 - Nova Sala\n";
		message += "\t5 - Voltar\n";

		int menu = askNumber(message);
		while (menu < 1 || menu > 5) {
			menu = askNumber(message);
		}

		switch (menu) {
		case 1: {
			// Aluno
			String nome = askInfo("Informe um nome:");
			String cpf = askInfo("Informe um CPF:");
			String telefone = askInfo("Informe um telefone:");
			String senha = askInfo("Informe uma senha:");
			String rua = askInfo("[Endereco]Informe a rua:");
			String numero = askInfo("[Endereco]Informe o n�mero:");
			String complemento = askInfo("[Endereco]Informe o complemento:");
			String cep = askInfo("[Endereco]Informe a CEP:");

			try {
				fachada.cadastrarAluno(nome, cpf, telefone, senha, rua, numero,
						complemento, cep);
			} catch (EnderecoInvalidoException e) {
				System.out.println(e.getMessage());
				fachada = opcaoCadastro(fachada);
			} catch (CpfInvalidoException e) {
				System.out.println(e.getMessage());
				fachada = opcaoCadastro(fachada);
			} catch (PessoaJaCadastradaException e) {
				System.out.println(e.getMessage());
				fachada = opcaoCadastro(fachada);
			} catch (IOException e) {
				//
				e.printStackTrace();
			} catch (TelefoneInvalidoException e) {
				System.out.println(e.getMessage());
				fachada = opcaoCadastro(fachada);
			}
			break;
		}
		case 2: {
			// Livro
			String codigo = askInfo("Digite o c�digo de identifica��o:");
			String titulo = askInfo("Digite o t�tulo:");
			String autor = askInfo("Digite o autor:");
			String assunto = askInfo("Digite o assunto:");
			String sinopse = askInfo("Digite o sinopse:");

			try {
				fachada.cadastrarLivro(codigo, titulo, autor, assunto, sinopse);
			} catch (LivroInvalidoException e) {
				System.out.println(e.getMessage());
				fachada = opcaoCadastro(fachada);
			} catch (LivroNaoEncontradoException e) {
				System.out.println(e.getMessage());
				fachada = opcaoCadastro(fachada);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RealizarLoginException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				fachada = opcaoCadastro(fachada);
			} catch (LivroJaCadastradoException e) {
				System.out.println(e.getMessage());
			}
			break;
		}
		case 3: {
			// Funcionario
			String nome = askInfo("Informe um nome:");
			String cpf = askInfo("Informe um CPF:");
			String telefone = askInfo("Informe um telefone:");
			String senha = askInfo("Informe uma senha:");
			String rua = askInfo("[Endereco]Informe a rua:");
			String numero = askInfo("[Endereco]Informe o n�mero:");
			String complemento = askInfo("[Endereco]Informe o complemento:");
			String cep = askInfo("[Endereco]Informe a CEP:");

			try {
				fachada.cadastrarFuncionario(nome, cpf, telefone, senha, rua,
						numero, complemento, cep);

			} catch (EnderecoInvalidoException e) {
				System.out.println(e.getMessage());
				fachada = opcaoCadastro(fachada);
			} catch (CpfInvalidoException e) {
				System.out.println(e.getMessage());
				fachada = opcaoCadastro(fachada);
			} catch (PessoaJaCadastradaException e) {
				System.out.println(e.getMessage());
				fachada = opcaoCadastro(fachada);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TelefoneInvalidoException e) {
				System.out.println(e.getMessage());
				fachada = opcaoCadastro(fachada);
			}
			break;
		}
		case 4: {
			// Sala

			String codigo = askInfo("Informe um c�digo:");
			int capacidade = askNumber("Informe a capacidade de pessoas da sala:");
			try {
				fachada.cadastrarSala(codigo, capacidade);
			} catch (SalaInvalidaException e) {
				System.out.println(e.getMessage());
			} catch (SalaJaCadastradaException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				// erro
			}

			break;
		}
		case 5: {

			break;
		}
		}

		return fachada;
	}

	private static Principal opcaoAtualizar(Principal fachada) {
		int menu = opcaoAlunoLivroSalaFuncionario();
		String message = "";
		switch (menu) {
		case 1: {
			String cpf = askInfo("Informe um CPF:");
			try {
				System.out.println(fachada.autalizarPrintDadosAluno(cpf));
				message = "Deseja atualizar alguma informa��o?";
				if (confirmaAcao(message)) {
					fachada = opcaoAtualizarMenuAluno(fachada);
				}
			} catch (PessoaNaoEncontradaException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				// TODO Auto-generated catch block

			} catch (PessoaInvalidaException e) {

				System.out.println(e.getMessage());
			} catch (RealizarLoginException e) {
				System.out.println(e.getMessage());
			}
			break;
		}
		case 2: {
			// Livro
			String codigo = askInfo("Informe o c�digo do livro:");
			try {
				System.out.println(fachada.atualizarPrintDadosLivro(codigo));
				message = "Deseja atualizar alguma informa��o?";
				if (confirmaAcao(message)) {
					fachada = opcaoAtualizaMenuLivro(fachada);
				}
			} catch (LivroNaoEncontradoException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				// TODO Auto-generated catch block

			}
			break;
		}
		case 3: {
			String cpf = askInfo("Informe um CPF:");
			try {
				System.out.println(fachada.atualizarPrintDadosFuncionario(cpf));
				message = "Deseja atualizar alguma informa��o?";
				if (confirmaAcao(message)) {
					fachada = opcaoAtualizarMenuFuncionario(fachada, cpf);
				}
			} catch (PessoaNaoEncontradaException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				// TODO Auto-generated catch block

			} catch (RealizarLoginException e) {
				System.out.println(e.getMessage());
			} catch (PessoaInvalidaException e) {
				System.out.println(e.getMessage());
			}
			break;
		}

		case 4: {
			String codigo = askInfo("Informe o c�digo da sala:");

			try {
				System.out.println(fachada.atualizarPrintDadosSala(codigo));
				message = "Deseja atualizar alguma informa��o?";
				if (confirmaAcao(message)) {
					fachada = opcaoAtualizarMenuSala(fachada, codigo);
				}
			} catch (SalaNaoEncontradaException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				// TODO Auto-generated catch block

			}
			break;
		}
		}

		return fachada;
	}

	private static Principal opcaoAtualizarMenuSala(Principal fachada,
			String codigo) {
		String message = "";
		message += "escolha uma op��o:\n";
		message += "\t1 - Capacidade\n";
		message += "\t2 - Voltar\n";

		int menu = askNumber(message);
		while (menu < 1 || menu > 2) {
			menu = askNumber(message);
		}

		String senhaFuncionario = "";
		if (menu != 2) {
			senhaFuncionario = askInfo("Digite a sua senha:");
		}

		switch (menu) {
		case 1: {
			int info = askNumber("Informe uma nova capacidade:");
			try {
				fachada.atualizarInfoSala(senhaFuncionario, info, menu);
			} catch (SalaInvalidaException e) {

				System.out.println(e.getMessage());
			} catch (RealizarLoginException e) {
				System.out.println(e.getMessage());
			} catch (SenhaInvalidaException e) {
				System.out.println(e.getMessage());
			} catch (SalaIndisponivelException e) {
				System.out.println(e.getMessage());
			}
		}
		}
		return fachada;
	}

	private static Principal opcaoAtualizarMenuAluno(Principal fachada) {
		String message = "";
		message += "escolha uma op��o:\n";
		message += "\t1 - Nome\n";
		message += "\t2 - Rua\n";
		message += "\t3 - N�mero\n";
		message += "\t4 - Complemento\n";
		message += "\t5 - CEP\n";
		message += "\t6 - telefone\n";
		message += "\t7 - Senha\n";
		message += "\t8 - Voltar\n";

		int menu = askNumber(message);
		while (menu < 1 || menu > 8) {
			menu = askNumber(message);
		}
		String senhaFuncionario = "";

		if (menu != 8) {
			senhaFuncionario = askInfo("Digite a sua senha:");
		}

		switch (menu) {
		case 1: {
			// nome
			String info = askInfo("Informe um novo nome:");
			try {
				fachada.atualizarInfoAluno(senhaFuncionario, info, menu);
			} catch (SenhaInvalidaException e) {
				System.out.println(e.getMessage());
			} catch (EnderecoInvalidoException e) {
				System.out.println(e.getMessage());
			} catch (RealizarLoginException e) {
				System.out.println(e.getMessage());
			} catch (TelefoneInvalidoException e) {
				System.out.println(e.getMessage());
			}
			break;
		}
		case 2: {
			// Rua
			String info = askInfo("Informe uma nova rua:");
			try {
				fachada.atualizarInfoAluno(senhaFuncionario, info, menu);
			} catch (SenhaInvalidaException e) {
				System.out.println(e.getMessage());
			} catch (EnderecoInvalidoException e) {
				System.out.println(e.getMessage());
			} catch (RealizarLoginException e) {
				System.out.println(e.getMessage());
			} catch (TelefoneInvalidoException e) {
				System.out.println(e.getMessage());
			}
			break;
		}
		case 3: {
			// numero
			String info = askInfo("Informe um novo n�mero:");
			try {
				fachada.atualizarInfoAluno(senhaFuncionario, info, menu);
			} catch (SenhaInvalidaException e) {
				System.out.println(e.getMessage());
			} catch (EnderecoInvalidoException e) {
				System.out.println(e.getMessage());
			} catch (RealizarLoginException e) {
				System.out.println(e.getMessage());
			} catch (TelefoneInvalidoException e) {
				System.out.println(e.getMessage());
			}
			break;
		}
		case 4: {
			// complemento
			String info = askInfo("Informe um novo complemento:");
			try {
				fachada.atualizarInfoAluno(senhaFuncionario, info, menu);
			} catch (SenhaInvalidaException e) {
				System.out.println(e.getMessage());
			} catch (EnderecoInvalidoException e) {
				System.out.println(e.getMessage());
			} catch (RealizarLoginException e) {
				System.out.println(e.getMessage());
			} catch (TelefoneInvalidoException e) {
				System.out.println(e.getMessage());
			}
			break;
		}
		case 5: {
			// CEP
			String info = askInfo("Informe um novo CEP:");
			try {
				fachada.atualizarInfoAluno(senhaFuncionario, info, menu);
			} catch (SenhaInvalidaException e) {
				System.out.println(e.getMessage());
			} catch (EnderecoInvalidoException e) {
				System.out.println(e.getMessage());
			} catch (RealizarLoginException e) {
				System.out.println(e.getMessage());
			} catch (TelefoneInvalidoException e) {
				System.out.println(e.getMessage());
			}
			break;
		}
		case 6: {
			// Telefone
			String info = askInfo("Informe um novo Telefone:");
			try {
				fachada.atualizarInfoAluno(senhaFuncionario, info, menu);
			} catch (SenhaInvalidaException e) {
				System.out.println(e.getMessage());
			} catch (EnderecoInvalidoException e) {
				System.out.println(e.getMessage());
			} catch (RealizarLoginException e) {
				System.out.println(e.getMessage());
			} catch (TelefoneInvalidoException e) {
				System.out.println(e.getMessage());
			}
			break;
		}
		case 7: {
			// senha
			String info = askInfo("Informe uma nova senha:");
			try {
				fachada.atualizarInfoAluno(senhaFuncionario, info, menu);
			} catch (SenhaInvalidaException e) {
				System.out.println(e.getMessage());
			} catch (EnderecoInvalidoException e) {
				System.out.println(e.getMessage());
			} catch (RealizarLoginException e) {
				System.out.println(e.getMessage());
			} catch (TelefoneInvalidoException e) {
				System.out.println(e.getMessage());
			}
			break;
		}

		}

		return fachada;
	}

	private static Principal opcaoAtualizarMenuFuncionario(Principal fachada,
			String cpf) {
		String message = "";
		message += "escolha uma op��o:\n";
		message += "\t1 - Nome\n";
		message += "\t2 - Rua\n";
		message += "\t3 - N�mero\n";
		message += "\t4 - Complemento\n";
		message += "\t5 - CEP\n";
		message += "\t6 - telefone\n";
		message += "\t7 - Senha\n";
		message += "\t8 - Voltar\n";

		int menu = askNumber(message);
		while (menu < 1 || menu > 8) {
			menu = askNumber(message);
		}

		String senhaFuncionario = "";

		if (menu != 8) {
			senhaFuncionario = askInfo("Digite a sua senha:");
		}

		switch (menu) {
		case 1: {
			// nome
			String info = askInfo("Informe um novo nome:");
			try {
				fachada.atualizarInfoFuncionario(senhaFuncionario, cpf, info,
						menu);
			} catch (SenhaInvalidaException e) {
				System.out.println(e.getMessage());
			} catch (EnderecoInvalidoException e) {
				System.out.println(e.getMessage());
			} catch (PessoaNaoEncontradaException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (PessoaInvalidaException e) {
				System.out.println(e.getMessage());
			} catch (TelefoneInvalidoException e) {
				System.out.println(e.getMessage());
			}
			break;
		}
		case 2: {
			// Rua
			String info = askInfo("Informe uma nova rua:");
			try {
				fachada.atualizarInfoFuncionario(senhaFuncionario, cpf, info,
						menu);
			} catch (SenhaInvalidaException e) {
				System.out.println(e.getMessage());
			} catch (EnderecoInvalidoException e) {
				System.out.println(e.getMessage());
			} catch (PessoaNaoEncontradaException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				// TODO Auto-generated catch block
			} catch (PessoaInvalidaException e) {

				System.out.println(e.getMessage());
			} catch (TelefoneInvalidoException e) {
				System.out.println(e.getMessage());
			}
			break;
		}
		case 3: {
			// numero
			String info = askInfo("Informe um novo n�mero:");
			try {
				fachada.atualizarInfoFuncionario(senhaFuncionario, cpf, info,
						menu);
			} catch (SenhaInvalidaException e) {
				System.out.println(e.getMessage());
			} catch (EnderecoInvalidoException e) {
				System.out.println(e.getMessage());
			} catch (PessoaNaoEncontradaException e) {
				// TODO Auto-generated catch block
			} catch (IOException e) {
				// TODO Auto-generated catch block

			} catch (PessoaInvalidaException e) {
				System.out.println(e.getMessage());
			} catch (TelefoneInvalidoException e) {
				System.out.println(e.getMessage());
			}
			break;
		}
		case 4: {
			// complemento
			String info = askInfo("Informe um novo complemento:");
			try {
				fachada.atualizarInfoFuncionario(senhaFuncionario, cpf, info,
						menu);
			} catch (SenhaInvalidaException e) {
				System.out.println(e.getMessage());
			} catch (EnderecoInvalidoException e) {
				System.out.println(e.getMessage());
			} catch (PessoaNaoEncontradaException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				// TODO Auto-generated catch block

			} catch (PessoaInvalidaException e) {
				System.out.println(e.getMessage());
			} catch (TelefoneInvalidoException e) {
				System.out.println(e.getMessage());
			}
			break;
		}
		case 5: {
			// CEP
			String info = askInfo("Informe um novo CEP:");
			try {
				fachada.atualizarInfoFuncionario(senhaFuncionario, cpf, info,
						menu);
			} catch (SenhaInvalidaException e) {
				System.out.println(e.getMessage());
			} catch (EnderecoInvalidoException e) {
				System.out.println(e.getMessage());
			} catch (PessoaNaoEncontradaException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				// TODO Auto-generated catch block

			} catch (PessoaInvalidaException e) {
				System.out.println(e.getMessage());
			} catch (TelefoneInvalidoException e) {
				System.out.println(e.getMessage());
			}
			break;
		}
		case 6: {
			// Telefone
			String info = askInfo("Informe um novo Telefone:");
			try {
				fachada.atualizarInfoFuncionario(senhaFuncionario, cpf, info,
						menu);
			} catch (SenhaInvalidaException e) {
				System.out.println(e.getMessage());
			} catch (EnderecoInvalidoException e) {
				System.out.println(e.getMessage());
			} catch (PessoaNaoEncontradaException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				// TODO Auto-generated catch block

			} catch (PessoaInvalidaException e) {
				System.out.println(e.getMessage());
			} catch (TelefoneInvalidoException e) {
				System.out.println(e.getMessage());
			}
			break;
		}
		case 7: {
			// senha
			String info = askInfo("Informe uma nova senha:");
			try {
				fachada.atualizarInfoFuncionario(senhaFuncionario, cpf, info,
						menu);
			} catch (SenhaInvalidaException e) {
				System.out.println(e.getMessage());
			} catch (EnderecoInvalidoException e) {
				System.out.println(e.getMessage());
			} catch (PessoaNaoEncontradaException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				// TODO Auto-generated catch block

			} catch (PessoaInvalidaException e) {
				System.out.println(e.getMessage());
			} catch (TelefoneInvalidoException e) {
				System.out.println(e.getMessage());
			}
			break;
		}

		}

		return fachada;
	}

	private static Principal opcaoAtualizaMenuLivro(Principal fachada) {
		String message = "";
		message += "escolha uma op��o:\n";
		message += "\t1 - T�tulo\n";
		message += "\t2 - Autor\n";
		message += "\t3 - Assunto\n";
		message += "\t4 - Sinopse\n";
		message += "\t5 - Adicionar Livro no Acervo\n";
		message += "\t6 - Voltar\n";

		int menu = askNumber(message);
		while (menu < 1 || menu > 6) {
			menu = askNumber(message);
		}

		String senhaFuncionario = "";
		if (menu != 6) {
			senhaFuncionario = askInfo("Digite a sua senha:");
		}
		switch (menu) {
		case 1: {
			// T�tulo
			String info = askInfo("Informe um novo t�tulo:");
			try {
				fachada.atualizarInfoLivro(senhaFuncionario, info, menu);
			} catch (SenhaInvalidaException e) {
				System.out.println(e.getMessage());
			} catch (RealizarLoginException e) {
				System.out.println(e.getMessage());

			} catch (LivroIndisponivelException e) {
				System.out.println(e.getMessage());
			} catch (LivroInvalidoException e) {
				System.out.println(e.getMessage());
			}
			break;
		}
		case 2: {
			// Autor
			String info = askInfo("Informe um novo autor:");
			try {
				fachada.atualizarInfoLivro(senhaFuncionario, info, menu);
			} catch (SenhaInvalidaException e) {
				System.out.println(e.getMessage());
			} catch (RealizarLoginException e) {
				System.out.println(e.getMessage());
			} catch (LivroIndisponivelException e) {
				System.out.println(e.getMessage());
			} catch (LivroInvalidoException e) {
				System.out.println(e.getMessage());
			}
			break;
		}
		case 3: {
			// Assunto
			String info = askInfo("Informe um novo assunto:");
			try {
				fachada.atualizarInfoLivro(senhaFuncionario, info, menu);
			} catch (SenhaInvalidaException e) {
				System.out.println(e.getMessage());
			} catch (RealizarLoginException e) {
				System.out.println(e.getMessage());
			} catch (LivroIndisponivelException e) {
				System.out.println(e.getMessage());
			} catch (LivroInvalidoException e) {
				System.out.println(e.getMessage());
			}
			break;
		}
		case 4: {
			// Sinopse
			String info = askInfo("Informe uma nova sinopse:");
			try {
				fachada.atualizarInfoLivro(senhaFuncionario, info, menu);
			} catch (SenhaInvalidaException e) {
				System.out.println(e.getMessage());
			} catch (RealizarLoginException e) {
				System.out.println(e.getMessage());
			} catch (LivroIndisponivelException e) {
				System.out.println(e.getMessage());
			} catch (LivroInvalidoException e) {
				System.out.println(e.getMessage());
			}
			break;
		}
		case 5: {
			int info = askNumber("Informe a nova quantidade:");
			try {
				fachada.atualizarInfoLivro(senhaFuncionario, ("" + info), menu);
			} catch (SenhaInvalidaException e) {
				System.out.println(e.getMessage());
			} catch (RealizarLoginException e) {
				System.out.println(e.getMessage());
			} catch (LivroIndisponivelException e) {
				System.out.println(e.getMessage());
			} catch (LivroInvalidoException e) {
				System.out.println(e.getMessage());
			}
			break;
		}
		case 6: {
			// Voltar
			break;
		}

		} // end switch

		return fachada;
	}

	private static Principal opcaoRelatorio(Principal fachada) {
		int menu = opcaoAlunoLivroSala();

		switch (menu) {
		case 1: {
			// Aluno
			// fachada = opcaoRelatorioAluno(fachada);
			System.out.println(fachada.printRepositorioAluno());
			break;
		}
		case 2: {
			// Livro
			// fachada = opcaoRelatorioLivro(fachada);
			System.out.println(fachada.printRepositorioLivro());
			break;
		}
		case 3: {
			// Sala
			// fachada = opcaoRelatorioSala(fachada);
			System.out.println(fachada.printRepositorioSala());

			break;
		}
		}
		return fachada;
	}

	private static Principal opcaoRelatorioAluno(Principal fachada) {
		String message = "";
		message += "escolha uma op��o como par�metro de ordena��o:\n";
		message += "\t1 - Nome\n";
		message += "\t2 - CPF\n";
		message += "\t3 - Quantidade de Empr�stimos\n";
		message += "\t4 - Voltar\n";

		int menu = askNumber(message);
		while (menu < 1 || menu > 4) {
			menu = askNumber(message);
		}

		return fachada;
	}

	private static Principal opcaoRelatorioLivro(Principal fachada) {
		String message = "";
		message += "escolha uma op��o como par�metro de ordena��o:\n";
		message += "\t1 - C�digo\n";
		message += "\t2 - T�tulo\n";
		message += "\t3 - Quantidade no Acervo\n";
		message += "\t4 - Quantidade de Empr�stimos\n";
		message += "\t5 - Voltar\n";

		int menu = askNumber(message);
		while (menu < 1 || menu > 5) {
			menu = askNumber(message);
		}

		return fachada;
	}

	private static Principal opcaoRelatorioSala(Principal fachada) {
		String message = "";
		message += "escolha uma op��o como par�metro de ordena��o:\n";
		message += "\t1 - C�digo\n";
		message += "\t2 - Quantidade Emprestimo(s)\n";
		message += "\t3 - Capacidade\n";
		message += "\t4 - Voltar\n";

		int menu = askNumber(message);
		while (menu < 1 || menu > 4) {
			menu = askNumber(message);
		}

		System.out.println(fachada.getRelatorioSala(menu));

		return fachada;
	}

	private static Principal opcaoProcurar(Principal fachada) {
		int menu = opcaoAlunoLivroSalaFuncionario();

		switch (menu) {
		case 1: {
			// Aluno

			String cpf = askInfo("Informe um CPF:");
			try {
				System.out.println(fachada.autalizarPrintDadosAluno(cpf));
			} catch (PessoaNaoEncontradaException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				// TODO Auto-generated catch block

			} catch (PessoaInvalidaException e) {

				System.out.println(e.getMessage());
			} catch (RealizarLoginException e) {

				System.out.println(e.getMessage());
			}
			break;
		}
		case 2: {
			// Livro
			String codigo = askInfo("Informe um c�digo:");
			try {
				System.out.println(fachada.atualizarPrintDadosLivro(codigo));
			} catch (LivroNaoEncontradoException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				// TODO Auto-generated catch block

			}
			break;
		}
		case 3: {
			// Funcionario
			String cpf = askInfo("Informe um CPF:");
			try {
				System.out.println(fachada.atualizarPrintDadosFuncionario(cpf));
			} catch (PessoaNaoEncontradaException e) {
				System.out.println(e.getMessage());

			} catch (IOException e) {
				// TODO Auto-generated catch block

			} catch (RealizarLoginException e) {
				System.out.println(e.getMessage());
			} catch (PessoaInvalidaException e) {
				System.out.println(e.getMessage());
			}
			break;
		}
		case 4: {
			// Sala
			String codigo = askInfo("Informe o c�digo da sala:");
			try {
				System.out.println(fachada.atualizarPrintDadosSala(codigo));
			} catch (SalaNaoEncontradaException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				// TODO Auto-generated catch block

			}
			break;
		}
		case 5: {
			// voltar
			// do nothing
			break;
		}
		}// end switch
		return fachada;
	}

	// --------------------------------------------------------------

	private static Principal alunoRealizarLoginEmprestimoSala(Principal fachada) {
		String codigoSala = askInfo("Digite o c�digo da sala:");
		String cpfAluno = askInfo("Insira o CPF:");
		String alunoSenha = askInfo("Insira a senha:");

		try {
			fachada.realizarEmprestimoSala(cpfAluno, alunoSenha, codigoSala);
		} catch (PessoaNaoEncontradaException e) {
			System.out.println(e.getMessage());

		} catch (IOException e) {
			// tratar erro

		} catch (PessoaInvalidaException e) {
			System.out.println(e.getMessage());

		} catch (RealizarLoginException e) {
			System.out.println(e.getMessage());

		} catch (SenhaInvalidaException e) {
			System.out.println(e.getMessage());

		} catch (SalaNaoEncontradaException e) {
			System.out.println(e.getMessage());

		} catch (SalaIndisponivelException e) {
			System.out.println(e.getMessage());

		}
		return fachada;
	}

	private static Principal alunoRealizarLoginEmprestimoLivro(Principal fachada) {

		String codigoLivro = askInfo("Digite o c�digo do livro:");
		String cpfAluno = askInfo("Insira o CPF:");
		String alunoSenha = askInfo("Insira a senha:");

		try {
			fachada.realizarEmprestimoLivro(cpfAluno, alunoSenha, codigoLivro);
		} catch (PessoaNaoEncontradaException e) {

			System.out.println(e.getMessage());
			fachada = opcaoAlunoLivro(fachada);

		} catch (LivroNaoEncontradoException e) {

			System.out.println(e.getMessage());
			fachada = opcaoAlunoLivro(fachada);

		} catch (IOException e) {
			// Tratar exce��o
		} catch (LivroIndisponivelException e) {

			System.out.println(e.getMessage());

		} catch (PessoaInvalidaException e) {

			System.out.println(e.getMessage());

		} catch (RealizarLoginException e) {

			System.out.println(e.getMessage());

		} catch (SenhaInvalidaException e) {

			System.out.println(e.getMessage());
			fachada = opcaoAlunoLivro(fachada);
		}

		return fachada;
	}

	private static Principal alunoRealizarLoginDevolucaoLivro(Principal fachada) {
		String cpfAluno = askInfo("Insira o CPF:");
		String codigoLivro = askInfo("Digite o c�digo do livro:");

		try {
			fachada.realizarDevolucaoLivro(cpfAluno, codigoLivro);
		} catch (PessoaNaoEncontradaException e) {
			System.out.println(e.getMessage());

		} catch (IOException e) {
			//

		} catch (PessoaInvalidaException e) {
			System.out.println(e.getMessage());

		} catch (MultaGeradaException e) {
			System.out.println(e.getMessage());
			try {
				String message = "Deseja realizar o pagamento da multa?";
				fachada.confirmarDevolucaoLivro(confirmaAcao(message), cpfAluno);
			} catch (RealizarLoginException e1) {
				System.out.println(e.getMessage());

			}

		} catch (LivroNaoEncontradoException e) {
			System.out.println(e.getMessage());

		} catch (ItemNaoEncontradoException e) {
			System.out.println(e.getMessage());

		} catch (RealizarLoginException e) {

			System.out.println(e.getMessage());

		}
		return fachada;
	}

	private static Principal alunoRealizarLoginDevolucaoSala(Principal fachada) {
		String cpfAluno = askInfo("Insira o CPF:");
		String codigo = askInfo("Digite o c�digo da sala:");

		try {
			fachada.realizarDevolucaoSala(cpfAluno, codigo);
		} catch (PessoaNaoEncontradaException e) {

		} catch (IOException e) {
			// tratar ERRO

		} catch (PessoaInvalidaException e) {
			System.out.println(e.getMessage());
		} catch (SalaNaoEncontradaException e) {
			System.out.println(e.getMessage());
		} catch (MultaGeradaException e) {
			System.out.println(e.getMessage());

			try {
				String message = "Deseja realizar o pagamento da multa?";
				fachada.confirmarDevolucaoSala(confirmaAcao(message));
			} catch (RealizarLoginException e1) {
				System.out.println(e.getMessage());

			}
		} catch (ItemNaoEncontradoException e) {
			System.out.println(e.getMessage());
		} catch (RealizarLoginException e) {
			System.out.println(e.getMessage());
		}

		return fachada;
	}

	private static boolean confirmaAcao(String info) {
		boolean resposta = false;
		String message = info + "\n";
		message += "\t1 - Sim\n";
		message += "\t2 - N�o\n";
		int menu = askNumber(message);

		while (menu < 1 || menu > 2) {
			menu = askNumber(message);
		}

		if (menu == 1) {
			resposta = true;
		}
		return resposta;
	}

	// --------------------------------------------------------------
	private static Principal opcaoRemover(Principal fachada) {
		String message = "";
		message += "escolha uma op��o:\n";
		message += "\t1 - Excluir Aluno\n";
		message += "\t2 - Excluir Livro\n";
		message += "\t3 - Excluir Sala\n";
		message += "\t4 - Excluir Funcion�rio\n";
		message += "\t5 - Voltar\n";

		int menu = askNumber(message);
		while (menu < 1 || menu > 5) {
			menu = askNumber(message);
		}

		switch (menu) {
		case 1: {
			// Aluno
			String cpf = askInfo("Informe o CPF do aluno:");
			String senhaFuncionario = askInfo("Informe a sua senha para confirmar a a��o:");

			try {
				fachada.realizarExclusaoAluno(senhaFuncionario);
			} catch (PessoaNaoEncontradaException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				// TODO Auto-generated catch block

			} catch (SenhaInvalidaException e) {
				System.out.println(e.getMessage());
			} catch (ConfirmarAcaoException e) {
				message = "Deseja confirmar?";
				if (confirmaAcao(message)) {
					try {
						fachada.confirmarExclusaoAluno(cpf);
					} catch (PessoaNaoEncontradaException e1) {
						System.out.println(e1.getMessage());
					} catch (IOException e1) {
						// TODO Auto-generated catch block

					} catch (PessoaInvalidaException e1) {
						System.out.println(e1.getMessage());
					} catch (AlunoComEmprestimoException e1) {
						System.out.println(e1.getMessage());

					}
				}
			}
			break;
		}
		case 2: {
			// Livro
			String codigo = askInfo("Informe o c�digo do livro:");
			String senhaFuncionario = askInfo("Informe a sua senha para confirmar a a��o:");

			try {
				fachada.realizarExclusaoLivro(senhaFuncionario);
			} catch (SenhaInvalidaException e) {
				System.out.println(e.getMessage());
			} catch (ConfirmarAcaoException e) {
				message = "Deseja confirmar?";
				if (confirmaAcao(message)) {
					try {
						fachada.confirmarExclusaoLivro(codigo);
					} catch (LivroNaoEncontradoException e1) {
						System.out.println((e1.getMessage()));
					} catch (IOException e1) {
						// TODO Auto-generated catch block

					} catch (RealizarLoginException e1) {
						// TODO Auto-generated catch block
						System.out.println(e1.getMessage());
					} catch (LivroComEmprestimoException e1) {
						System.out.println(e1.getMessage());
					}
				}
			}
			break;
		}
		case 3: {
			// Sala
			String codigo = askInfo("Informe o c�digo da sala:");
			String senhaFuncionario = askInfo("Informe a sua senha para confirmar a a��o:");

			try {
				fachada.realizarExclusaoSala(codigo, senhaFuncionario);
			} catch (ConfirmarAcaoException e) {
				message = "Deseja confirmar?";
				if (confirmaAcao(message)) {
					try {
						fachada.confirmarExclusaoSala(codigo);
					} catch (SalaNaoEncontradaException e1) {

						System.out.println(e1.getMessage());
					} catch (IOException e1) {
						// TODO Auto-generated catch block

					} catch (SalaComEmprestimoException e1) {
						// TODO Auto-generated catch block
						System.out.println(e1.getMessage());
					}
				}

			} catch (SenhaInvalidaException e) {

				System.out.println(e.getMessage());
			}
			break;
		}
		case 4: {
			// Funcionario
			String cpf = askInfo("Informe o CPF do Funcion�rio:");
			String senhaFuncionario = askInfo("Informe a sua senha para confirmar a a��o:");

			try {
				fachada.realizarExclusaoFuncionario(senhaFuncionario);
			} catch (SenhaInvalidaException e) {
				System.out.println(e.getMessage());
			} catch (ConfirmarAcaoException e) {
				message = "Deseja confirmar?";
				if (confirmaAcao(message)) {
					try {
						fachada.confirmarExclusaoFuncionario(cpf);
					} catch (PessoaNaoEncontradaException e1) {
						System.out.println(e1.getMessage());
					} catch (IOException e1) {
						// TODO Auto-generated catch block

					} catch (PessoaInvalidaException e1) {

						System.out.println(e1.getMessage());
					}
				}
			}
			break;
		}
		case 5: {
			// Sair

			break;
		}
		}
		return fachada;
	}

}