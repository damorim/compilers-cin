package biblioteca.negocios;

import java.io.IOException;

import biblioteca.dados.IteratorPessoa;
import biblioteca.dados.entidades.Aluno;
import biblioteca.dados.entidades.Endereco;
import biblioteca.dados.entidades.Funcionario;
import biblioteca.dados.entidades.Pessoa;
import biblioteca.dados.repositorio.interfaces.RepositorioPessoa;
import biblioteca.negocios.exceptions.ItemNaoEncontradoException;
import biblioteca.negocios.exceptions.RealizarLoginException;
import biblioteca.negocios.exceptions.endereco.EnderecoInvalidoException;
import biblioteca.negocios.exceptions.pessoa.AlunoComEmprestimoException;
import biblioteca.negocios.exceptions.pessoa.CpfInvalidoException;
import biblioteca.negocios.exceptions.pessoa.MultaGeradaException;
import biblioteca.negocios.exceptions.pessoa.PessoaInvalidaException;
import biblioteca.negocios.exceptions.pessoa.PessoaJaCadastradaException;
import biblioteca.negocios.exceptions.pessoa.PessoaNaoEncontradaException;
import biblioteca.negocios.exceptions.pessoa.SenhaInvalidaException;
import biblioteca.negocios.exceptions.pessoa.TelefoneInvalidoException;

public class ManipularPessoa {

	private RepositorioPessoa generico;
	private Pessoa funcionario;
	private Pessoa cache; // atributo que vai ser usado como cache do sistema

	public ManipularPessoa(RepositorioPessoa generico) {
		this.generico = generico;
		this.funcionario = null;
		this.cache = null;
	}

	public void iniciarSistema() throws PessoaJaCadastradaException,
	IOException {
		/*
		 * Este m�todo vai ser usado quando o sistema n�o for inicializado por
		 * um repositorio do tipo ARQUIVO, ou seja, ele vai carregar sempre pelo
		 * menos um usu�rio admin.
		 */
		Funcionario user = new Funcionario("Caio", "09511766414", "caio123");
		generico.inserirPessoa(user);
	}

	public boolean realizarLoginFuncionario(String cpf, String senha)
			throws PessoaNaoEncontradaException, SenhaInvalidaException,
			PessoaInvalidaException, IOException {
		boolean resposta = true;
		funcionario = generico.procurarPessoa(cpf);
		if (!(funcionario.getSenha().equals(senha))) {
			funcionario = null;
			resposta = false;
			throw new SenhaInvalidaException();
		}

		if (funcionario instanceof Aluno) {
			throw new PessoaInvalidaException(cpf);
		}
		return resposta;
	}

	public void cadastrarAluno(Aluno aluno) throws PessoaJaCadastradaException,
	IOException {
		generico.inserirPessoa(aluno);
	}

	public void cadastrarFuncionario(Funcionario func)
			throws PessoaJaCadastradaException, IOException {
		generico.inserirPessoa(func);
	}

	public Endereco criarEndereco(String rua, String numero,
			String complemento, String cep) throws EnderecoInvalidoException {

		boolean erro = false;
		String erroMessage = "";
		Endereco endereco = null;
		rua = tiraEspaco(rua);
		numero = tiraEspaco(numero);
		complemento = tiraEspaco(complemento);
		cep = tiraEspaco(cep);

		if (rua.length() < 1) {
			erro = true;
			erroMessage += "rua";
		}

		if (numero.length() < 1) {
			if (erro) {
				erroMessage += ", n�mero";
			} else {
				erro = true;
				erroMessage += "n�mero";
			}
		}

		if (cep.length() != 8 || !telefoneCepValido(cep)) {
			if (erro) {
				erroMessage += ", CEP";
			} else {
				erro = true;
				erroMessage += "CEP";
			}
		}

		if (erro) {
			throw new EnderecoInvalidoException(erroMessage);
		} else {
			endereco = new Endereco(rua, numero, complemento, cep);
		}

		return endereco;
	}

	public Funcionario criarFuncionario(String nome, String cpf,
			String telefone, String senha, Endereco endereco)
					throws CpfInvalidoException, TelefoneInvalidoException {
		Funcionario funcionario = null;
	
		if(!cpfValido(cpf)){
			throw new CpfInvalidoException(cpf);
		}
			
			if(!telefoneCepValido(telefone)){
			throw new TelefoneInvalidoException(telefone);
		
			
		}else{
			funcionario = new Funcionario(nome, cpf, endereco, telefone, senha);
		}
		return funcionario;
	}

	
	public Aluno criarAluno(String nome, String cpf, String telefone,
			String senha, Endereco endereco) throws CpfInvalidoException, TelefoneInvalidoException {
		Aluno aluno = null;
		if (!cpfValido(cpf)) {
			throw new CpfInvalidoException(cpf);
			
		}
		
		if(!telefoneCepValido(telefone)){
			throw new TelefoneInvalidoException(telefone);
			
		} else {
			aluno = new Aluno(nome, cpf, endereco, telefone, senha);
		}
		return aluno;
	}

	private boolean telefoneCepValido (String telefone){
		boolean tamanhoValido = false;
		boolean caracValido = false;
		if(telefone.length() != 8){
			tamanhoValido = false;
		}else{
			tamanhoValido = true;
		}

		boolean aux = true;
		if(tamanhoValido && aux){
			for (int i = 0; i<=7;i++){
				char a = telefone.charAt(i);
				if( a == '0' || a == '1' || a== '2' || a== '3' || a== '4' || a== '5' || a=='6' || a=='7' || a=='8' || a=='9'){
					caracValido = true;
				}else{
					caracValido = false;
					aux = false;
					i++;
				}
			}
		}
		
		return caracValido;
	}
	
	



	private boolean cpfValido(String cpf) {
		boolean valido = false;
		boolean formatoValido = true;

		if (cpf.equals("00000000000") || cpf.equals("11111111111")
				|| cpf.equals("22222222222") || cpf.equals("33333333333")
				|| cpf.equals("44444444444") || cpf.equals("55555555555")
				|| cpf.equals("66666666666") || cpf.equals("77777777777")
				|| cpf.equals("88888888888") || cpf.equals("99999999999")
				|| !(cpf.length() >= 11 && cpf.length() <= 11)) {
			valido = false;
			formatoValido = false;
		}
		if (formatoValido) {
			char penultimoDigito;
			char ultimoDigito;
			int sum = 0;
			int peso = 10;

			for (int i = 0; i < 9; i++) {
				int n = (int) (cpf.charAt(i) - 48);
				sum += n * peso;
				peso--;
			}

			int p = 11 - (sum % 11); // penultimo

			if ((p == 10) || (p == 11)) {
				penultimoDigito = '0';
			} else {
				penultimoDigito = (char) (p + 48);
			}

			sum = 0;
			peso = 11;

			for (int i = 0; i < 10; i++) {
				int n = (int) (cpf.charAt(i) - 48);
				sum += n * peso;
				peso--;
			}

			int u = 11 - (sum % 11); // ultimo
			if ((u == 10) || (u == 11)) {
				ultimoDigito = '0';
			} else {
				ultimoDigito = (char) (u + 48);
			}

			if (penultimoDigito != cpf.charAt(9)
					|| ultimoDigito != cpf.charAt(10)) {
				valido = false;
			} else {
				valido = true;
			}
		}
		return valido;
	}

	private String tiraEspaco(String aux) {
		// retirando os espa�os vazios que antecedem
		while (aux.length() > 0 && aux.charAt(0) == ' ') {
			aux = aux.substring(1, aux.length());
		}

		// retirando os espa�os vazios que sucedem
		while (aux.length() > 0 && aux.charAt(aux.length() - 1) == ' ') {
			aux = aux.substring(0, (aux.length() - 1));
		}
		return aux;
	}

	public String getDadosPessoa() throws RealizarLoginException {
		String resposta = "";
		if (cache != null) {
			resposta = cache.toString();
		} else {
			throw new RealizarLoginException();
		}
		return resposta;
	}

	public String getDadosFuncionario(String cpf)
			throws PessoaNaoEncontradaException, IOException,
			RealizarLoginException, PessoaInvalidaException {
		String message = "";
		cache = generico.procurarPessoa(cpf);
		if (cache != null) {
			message += cache.toString();
			cache = null;
		} else if (cache instanceof Aluno) {
			throw new PessoaInvalidaException(cpf);
		} else {
			throw new RealizarLoginException();
		}

		return message;
	}

	// ---------------------------------------

	public boolean confirmarSenhaFuncionario(String senha)
			throws SenhaInvalidaException {
		boolean resposta = false;
		if (funcionario != null) {
			if (!funcionario.getSenha().equals(senha)) {
				throw new SenhaInvalidaException();
			} else {
				resposta = true;
			}
		}
		return resposta;
	}

	// ---------------------------------------

	public void selecionarAluno(String cpf)
			throws PessoaNaoEncontradaException, IOException,
			PessoaInvalidaException {
		cache = generico.procurarPessoa(cpf);
		if (cache instanceof Funcionario) {
			throw new PessoaInvalidaException(cache.getCpf());
		}
	}

	public void confirmarSenhaAluno(String senha)
			throws RealizarLoginException, SenhaInvalidaException {
		if (cache != null) {
			if (!((Aluno) cache).getSenha().equals(senha)) {
				throw new SenhaInvalidaException();
			}
		} else {
			throw new RealizarLoginException();
		}
	}

	public void deselecionarAluno() {
		cache = null;
	}

	public void realizarEmprestimo(String codigo, String tipo,
			String dataEmprestimo, String dataDevolucao)
					throws RealizarLoginException {

		if (cache != null) {
			((Aluno) cache).novoEmprestimo(codigo, tipo, dataDevolucao,
					dataEmprestimo);
		} else {
			throw new RealizarLoginException();
		}
	}

	public void realizarDevolucao(String codigo, String dataDevolucao)
			throws MultaGeradaException, ItemNaoEncontradoException,
			RealizarLoginException {
		if (cache != null) {
			((Aluno) cache).novaDevolucao(codigo, dataDevolucao);
		} else {
			throw new RealizarLoginException();
		}
	}

	public void confirmarDevolucao() throws RealizarLoginException {
		if (cache != null) {
			((Aluno) cache).confirmarDevolucao();
		} else {
			throw new RealizarLoginException();
		}
	}

	// ---------------------------------------

	public void removerAluno(String cpf) throws PessoaNaoEncontradaException,
	IOException, PessoaInvalidaException, AlunoComEmprestimoException {
		selecionarAluno(cpf);

		if (cache instanceof Funcionario) {
			deselecionarAluno();
			throw new PessoaInvalidaException(cpf, '1');
		} else if (cache instanceof Aluno) {
			if (((Aluno) cache).hasEmprestimo()) {
				int total = ((Aluno) cache).totalLivrosEmprestimo();
				throw new AlunoComEmprestimoException(cpf, total);
			} else {
				generico.removerPessoa(cpf);
			}
		}

	}

	public void removerFuncionario(String cpf)
			throws PessoaNaoEncontradaException, IOException,
			PessoaInvalidaException {

		cache = generico.procurarPessoa(cpf);

		if (cache instanceof Aluno || cache.getCpf().equals(cpf)) {
			cache = null;
			throw new PessoaInvalidaException(cpf, '1');
		}
	}

	// ---------------------------------------

	public void confirmaAtualizarAluno(String info, int infoTipo)
			throws EnderecoInvalidoException, RealizarLoginException, TelefoneInvalidoException{
		if (cache != null) {
			switch (infoTipo) {
			case 1: {
				// nome
				((Aluno) cache).setNome(info);
				break;
			}
			case 2: {
				// Rua
				if (info.length() < 1) {
					throw new EnderecoInvalidoException("rua");
				}
				((Aluno) cache).getEndereco().setRua(info);
				break;
			}
			case 3: {
				// numero
				if (info.length() < 1) {
					throw new EnderecoInvalidoException("n�mero");
				}
				((Aluno) cache).getEndereco().setNumero(info);
				break;
			}
			case 4: {
				// complemento
				((Aluno) cache).getEndereco().setComplemento(info);
				break;
			}
			case 5: {
				// CEP
				if (info.length() != 8 || !telefoneCepValido(info)) {
					throw new EnderecoInvalidoException("CEP");
				}
				((Aluno) cache).getEndereco().setCep(info);
				break;
			}
			case 6: {
				// Telefone
				if(!telefoneCepValido(info)){
					throw new TelefoneInvalidoException(info);
				}
				((Aluno) cache).setTelefone(info);
				break;
			}
			case 7: {
				// senha
				((Aluno) cache).setSenha(info);
				break;
			}

			} // end switch

		} else {
			throw new RealizarLoginException();
		}
	}

	public void confirmaAtualizarFuncionario(String cpf, String info,
			int infoTipo) throws EnderecoInvalidoException,
			PessoaNaoEncontradaException, IOException, PessoaInvalidaException, TelefoneInvalidoException {
		cache = generico.procurarPessoa(cpf);

		if (cache instanceof Aluno) {
			throw new PessoaInvalidaException(cache.getCpf());
		} else {
			if (cache != null) {
				switch (infoTipo) {
				case 1: {
					// nome
					((Funcionario) cache).setNome(info);
					break;
				}
				case 2: {
					// Rua
					if (info.length() < 1) {
						throw new EnderecoInvalidoException("rua");
					}
					((Funcionario) cache).getEndereco().setRua(info);
					break;
				}
				case 3: {
					// numero
					if (info.length() < 1) {
						throw new EnderecoInvalidoException("n�mero");
					}
					((Funcionario) cache).getEndereco().setNumero(info);
					break;
				}
				case 4: {
					// complemento
					((Funcionario) cache).getEndereco().setComplemento(info);
					break;
				}
				case 5: {
					// CEP
					if (info.length() != 8 || !telefoneCepValido(info)) {
						throw new EnderecoInvalidoException("CEP");
					}else{
					((Funcionario) cache).getEndereco().setCep(info);
					}
					break;
				}
				case 6: {
					// Telefone
					if(!telefoneCepValido(info)){
						throw new TelefoneInvalidoException(info);
					}
					((Funcionario) cache).setTelefone(info);
					break;
				}
				case 7: {
					// senha
					((Funcionario) cache).setSenha(info);
					break;
				}

				} // end switch

			}// end if
		}
	}

	// ---------------------------------------

	public String printRepositorio() {
		IteratorPessoa it = generico.getIterator();
		String message = "";

		while (it.hasNext()) {
			Pessoa aux = it.next();
			if (aux instanceof Aluno) {
				message += aux.toString() + "\n";
			}
		}

		return message;
	}

}
