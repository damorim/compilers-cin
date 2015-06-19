package escola.fachadaEscola;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import javax.swing.JOptionPane;

import escola.classesBase.Administrador;
import escola.classesBase.Aluno;
import escola.classesBase.Disciplina;
import escola.classesBase.Endereco;
import escola.classesBase.Funcionario;
import escola.classesBase.Pessoa;
import escola.classesBase.Professor;
import escola.classesBase.Turma;
import escola.dados.*;
import escola.excecoes.ElementoJaCadastradoException;
import escola.excecoes.ElementoNaoEncontradoException;
import escola.excecoes.EntradaInvalidaException;
import escola.excecoes.RepositorioException;
import escola.negocio.Controle;

public class Escola {

	Repositorio<Pessoa> pessoas;
	Repositorio<Turma> turmas; // AINDA TEM QUE TRATAR OS ERROS NO CATCH
	Repositorio<Disciplina> disciplinas; // INSERIR, PROCURAR, REMOVER E
											// ATUALIZAR
											// DONE

	public Escola() {

		FileReader arq;
		String repositorio = "";
		try {
			arq = new FileReader("config.txt");
			@SuppressWarnings("resource")
			BufferedReader lerArq = new BufferedReader(arq);
			repositorio = lerArq.readLine();
		} catch (FileNotFoundException e) {
			System.out.println("erro1");
		} catch (IOException e) {
			System.out.println("erro2");
		}

		if (repositorio.equalsIgnoreCase("array")) {
			pessoas = new RepositorioArrayPessoa();
			turmas = new RepositorioArrayTurma();
			disciplinas = new RepositorioArrayDisciplina();
		} else if (repositorio.equalsIgnoreCase("lista")) {
			pessoas = new RepositorioListaPessoa();
			turmas = new RepositorioListaTurma();
			disciplinas = new RepositorioListaDisciplina();
		} else if (repositorio.equalsIgnoreCase("arquivo")) {
			pessoas = new RepositorioArquivoPessoa();
			turmas = new RepositorioArrayTurma();
			disciplinas = new RepositorioArrayDisciplina();
		} else {
			JOptionPane.showMessageDialog(null,
					"Insira um tipo de repositorio no config.txt");
		}
		
		teste();

	}

	public Repositorio<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(Repositorio<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public Repositorio<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(Repositorio<Turma> turmas) {
		this.turmas = turmas;
	}

	public Repositorio<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(Repositorio<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public void inserirAluno(String cpf, String nome, String dataNasc,
			String rg, String sexo, String telefone, String rua, String numero,
			String bairro, String cep, String cidade, String estado,
			String pais, String pai, String mae, Turma turma)
			throws EntradaInvalidaException, ElementoJaCadastradoException,
			RepositorioException {
		try {
			Pessoa p = pessoas.procurar(cpf);// verifica se a pessoa ja foi
												// cadastrada. Se sim, e jogado
												// o erro.

			throw new ElementoJaCadastradoException();

		} catch (ElementoNaoEncontradoException e) {
			Endereco endereco = new Endereco(rua, numero, bairro, cep, cidade,
					estado, pais);
			Aluno aluno = new Aluno(cpf, nome, dataNasc, rg, sexo, telefone,
					endereco, pai, mae, turma);
			pessoas.inserir(aluno);
			try {
				turmas.procurar(turma.getNome()).inserirAluno(aluno);
				
			} catch (ElementoNaoEncontradoException e1) {
				
			}
		}

	}

	public void inserirProfessor(String cpf, String nome, String dataNasc,
			String rg, String sexo, String telefone, String rua, String numero,
			String bairro, String cep, String cidade, String estado,
			String pais, String funcao) throws RepositorioException,
			EntradaInvalidaException, ElementoJaCadastradoException {
		try {
			Pessoa p = pessoas.procurar(cpf);
			throw new ElementoJaCadastradoException();
		} catch (ElementoNaoEncontradoException e) {
			Endereco endereco = new Endereco(rua, numero, bairro, cep, cidade,
					estado, pais);
			Professor professor = new Professor(cpf, nome, dataNasc, rg, sexo,
					telefone, endereco, funcao);
			pessoas.inserir(professor);
		}
	}

	public void inserirAdministrador(String cpf, String nome, String dataNasc,
			String rg, String sexo, String telefone, String rua, String numero,
			String bairro, String cep, String cidade, String estado,
			String pais, String funcao) throws RepositorioException,
			EntradaInvalidaException, ElementoJaCadastradoException {
		try {
			Pessoa p = pessoas.procurar(cpf);
			throw new ElementoJaCadastradoException();
		} catch (ElementoNaoEncontradoException e) {
			Endereco endereco = new Endereco(rua, numero, bairro, cep, cidade,
					estado, pais);
			Administrador administrador = new Administrador(cpf, nome,
					dataNasc, rg, sexo, telefone, endereco, funcao);
			pessoas.inserir(administrador);
		}
	}

	public void inserirFuncionario(String cpf, String nome, String dataNasc,
			String rg, String sexo, String telefone, String rua, String numero,
			String bairro, String cep, String cidade, String estado,
			String pais, String funcao) throws RepositorioException,
			EntradaInvalidaException, ElementoJaCadastradoException {
		try {
			Controle.controlePessoa(cpf, nome, dataNasc, rg, sexo, telefone,
					rua, numero, bairro, cep, cidade, estado, pais);
		} catch (EntradaInvalidaException e) {
			throw e;
		}
		try {
			Pessoa p = pessoas.procurar(cpf);
			throw new ElementoJaCadastradoException();
		} catch (ElementoNaoEncontradoException e) {
			Endereco endereco = new Endereco(rua, numero, bairro, cep, cidade,
					estado, pais);
			Funcionario funcionario = new Funcionario(cpf, nome, dataNasc, rg,
					sexo, telefone, endereco, funcao);
			pessoas.inserir(funcionario);
		}
	}

	public void inserirTurma(String nome) throws EntradaInvalidaException,
			ElementoJaCadastradoException, RepositorioException {

		try {
			Turma t = turmas.procurar(nome);
			throw new ElementoJaCadastradoException();
		} catch (ElementoNaoEncontradoException e) {
			Turma turma = new Turma(nome);
			turmas.inserir(turma);
		}
	}

	public void inserirDisciplina(String nome, String ementa)
			throws ElementoJaCadastradoException, RepositorioException,
			EntradaInvalidaException {
		try {
			Disciplina d = disciplinas.procurar(nome);
			throw new ElementoJaCadastradoException();
		} catch (ElementoNaoEncontradoException e) {
			if (Controle.nomeValido(nome)) {
				Disciplina d = new Disciplina(nome, ementa);
				disciplinas.inserir(d);
			}
		}

	}

	public Pessoa procurarPessoa(String cpf)
			throws ElementoNaoEncontradoException, EntradaInvalidaException {
		Pessoa p = null;
		if (Controle.cpfValido(cpf)) {
			try {
				p = pessoas.procurar(cpf);
			} catch (ElementoNaoEncontradoException e) {
				throw e;
			}
		}
		return p;
	}

	public Disciplina procurarDisciplina(String nome)
			throws ElementoNaoEncontradoException, EntradaInvalidaException {
		Disciplina d = null;
		if (Controle.nomeValido(nome)) {
			try {
				d = disciplinas.procurar(nome);
			} catch (ElementoNaoEncontradoException e) {
				throw e;
			}
		}
		return d;
	}

	public Turma procurarTurma(String nome)
			throws ElementoNaoEncontradoException, EntradaInvalidaException {
		Turma t = null;

		if (Controle.nomeValido(nome)) {
			try {
				t = turmas.procurar(nome);
			} catch (ElementoNaoEncontradoException e) {
				throw e;
			}
		}
		return t;
	}

	public void removerPessoa(String cpf) throws RepositorioException,
			EntradaInvalidaException, ElementoNaoEncontradoException {
		Controle.cpfValido(cpf);
		pessoas.remover(cpf);
	}

	public void removerDisciplina(String nome) throws RepositorioException,
			EntradaInvalidaException, ElementoNaoEncontradoException {
		Controle.nomeValido(nome);

		disciplinas.remover(nome);
		Iterator<Turma> it = turmas.getIterator();
		while (it.hasNext()) {
			Turma turmaAux = it.next();
			Iterator<Disciplina> itD = turmaAux.getDisciplinas().getIterator();
			while (itD.hasNext()) {
				Disciplina disciplinaAux = itD.next();
				if (disciplinaAux.getNome().equals(nome)) {
					turmaAux.removerDisciplina(nome);
				}

			}
		}
	}

	public void removerTurma(String nome) throws RepositorioException,
			EntradaInvalidaException, ElementoNaoEncontradoException {
		Controle.nomeValido(nome);
		turmas.remover(nome);
	}

	public void atualizarPessoa(Pessoa item)
			throws ElementoNaoEncontradoException, RepositorioException {
		pessoas.atualizar(item);
	}

	public void atualizarDisciplina(Disciplina disc) throws ElementoNaoEncontradoException, RepositorioException{
			disciplinas.atualizar(disc);
	}

	public void atualizarTurma(Turma item)
			throws ElementoNaoEncontradoException, RepositorioException {
		turmas.atualizar(item);
	}

	public String relatorioAlunos() { // tem que fazer os metodos relatorio
		String retorno = "Relatorio de Alunos\n\n";

		Iterator<Pessoa> it = pessoas.getIterator();
		while (it.hasNext()) {
			Pessoa p = (Pessoa) it.next();
			if (p instanceof Aluno) {
				retorno += p.resumo() + "\n\n";
			}
		}

		return retorno;
	}

	public String relatorioProfessor() { // tem que fazer os metodos relatorio
		String retorno = "Relatorio de Professores\n\n";

		Iterator<Pessoa> it = pessoas.getIterator();
		while (it.hasNext()) {
			Pessoa p = (Pessoa) it.next();
			if (p instanceof Professor) {
				retorno += p.resumo() + "\n\n";
			}
		}

		return retorno;
	}

	public String relatorioAdm() { // tem que fazer os metodos relatorio
		String retorno = "Relatorio de Administradores\n\n";

		Iterator<Pessoa> it = pessoas.getIterator();
		while (it.hasNext()) {
			Pessoa p = (Pessoa) it.next();
			if (p instanceof Administrador) {
				retorno += p.resumo() + "\n\n";
			}
		}

		return retorno;
	}

	public String relatorioFuncionario() { 
		String retorno = "Relatorio de Funcionarios\n\n";

		Iterator<Pessoa> it = pessoas.getIterator();
		while (it.hasNext()) {
			Pessoa p = (Pessoa) it.next();
			if (p instanceof Funcionario) {
				retorno += p.resumo() + "\n\n";
			}
		}

		return retorno;
	}

	public String relatorioTurmas() { 
		String retorno = "Relatorio de Turmas\n\n";

		Iterator<Turma> it = turmas.getIterator();
		while (it.hasNext()) {
			Turma p = it.next();
			retorno += p.resumo() + "\n\n";
		}

		return retorno;
	}

	public String relatorioDisc() { 
		String retorno = "Relatorio de Disciplinas\n\n";

		Iterator<Disciplina> it = disciplinas.getIterator();
		while (it.hasNext()) {
			Disciplina p = it.next();
			retorno += p.resumo() + "\n\n";
		}

		return retorno;
	}

	public void atualizarAluno(Pessoa aluno) throws ElementoNaoEncontradoException, RepositorioException {
		pessoas.atualizar(aluno);
	}

	public void atualizarProfessor(Pessoa professor) throws ElementoNaoEncontradoException, RepositorioException {
		pessoas.atualizar(professor);
	}

	public RepositorioArrayPessoa getAlunos() {
		RepositorioArrayPessoa alunos = new RepositorioArrayPessoa();
		Iterator<Pessoa> it = pessoas.getIterator();
		while (it.hasNext()) {
			Pessoa p = it.next();
			if (p instanceof Aluno) {
				alunos.inserir(p);
			}
		}
		return alunos;
	}

	public RepositorioArrayPessoa getProfessores() {
		RepositorioArrayPessoa professores = new RepositorioArrayPessoa();
		Iterator<Pessoa> it = pessoas.getIterator();
		while (it.hasNext()) {
			Pessoa p = it.next();
			if (p instanceof Professor) {
				professores.inserir(p);
			}
		}
		return professores;
	}

	public RepositorioArrayPessoa getAdministradores() {
		RepositorioArrayPessoa administradores = new RepositorioArrayPessoa();
		Iterator<Pessoa> it = pessoas.getIterator();
		while (it.hasNext()) {
			Pessoa p = it.next();
			if (p instanceof Administrador) {
				administradores.inserir(p);
			}
		}
		return administradores;
	}

	public RepositorioArrayPessoa getFuncionarios() {
		RepositorioArrayPessoa funcionarios = new RepositorioArrayPessoa();
		Iterator<Pessoa> it = pessoas.getIterator();
		while (it.hasNext()) {
			Pessoa p = it.next();
			if (p instanceof Professor) {
				funcionarios.inserir(p);
			}
		}
		return funcionarios;
	}

	public RepositorioArrayTurma getArrayTurma() {
		RepositorioArrayTurma turmasArray = new RepositorioArrayTurma();
		Iterator<Turma> it = this.turmas.getIterator();
		while (it.hasNext()) {
			Turma turma = it.next();
			turmasArray.inserir(turma);
		}
		return turmasArray;

	}

	public RepositorioArrayDisciplina getArrayDisciplina() {
		RepositorioArrayDisciplina disciplinasArray = new RepositorioArrayDisciplina();
		Iterator<Disciplina> it = this.disciplinas.getIterator();
		while (it.hasNext()) {
			Disciplina disciplina = it.next();
			disciplinasArray.inserir(disciplina);
		}
		return disciplinasArray;

	}

	public void removerTurmaProfessor(Professor professor, Turma turma)
			throws RepositorioException, ElementoNaoEncontradoException {
		Professor professorAux = professor;
		try {
			professorAux = (Professor) getProfessores().procurar(
					professor.getCpf());
		} catch (ElementoNaoEncontradoException e) {
		}
		professorAux.removerTurma(turma);
	}

	public void adicionarTurmaProfessor(Professor professor, Turma turma)
			throws ElementoJaCadastradoException {
		Professor professorAux = professor;
		try {
			professorAux = (Professor) getProfessores().procurar(
					professor.getCpf());
		} catch (ElementoNaoEncontradoException e) {
		}
		professorAux.inserirTurma(turma);

	}

	public void removerDisciplinaProfessor(Professor professor,
			Disciplina disciplina) {
		Professor professorAux = professor;
		try {
			professorAux = (Professor) getProfessores().procurar(
					professor.getCpf());
		} catch (ElementoNaoEncontradoException e) {
		}
		try {
			professorAux.removerDisciplina(disciplina);
		} catch (RepositorioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ElementoNaoEncontradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void adicionarDisciplinaProfessor(Professor professor,
			Disciplina disciplina) throws ElementoJaCadastradoException {
		Professor professorAux = professor;
		try {
			professorAux = (Professor) getProfessores().procurar(
					professor.getCpf());
		} catch (ElementoNaoEncontradoException e) {
		}
		professorAux.inserirDisciplina(disciplina);
	}

	public RepositorioArrayPessoa getAlunosTurma(String nomedaTurma){
		Iterator<Pessoa> it = getAlunos().getIterator();
		RepositorioArrayPessoa alunos = new RepositorioArrayPessoa();
		while (it.hasNext()){
			Aluno aluno = (Aluno) it.next();
			if(aluno.getTurma().getNome().equals(nomedaTurma)){
				alunos.inserir(aluno);
			}
		}
		return alunos;
	}
	
	public void teste() {
		try {
			

			Endereco end = new Endereco("Rua", "123", "Bairro", "51030630",
					"Cidade", "Estado", "Pais");
			Turma turma = new Turma("tumrma1");
			Pessoa bruna = new Aluno("00000000001", "Bruna", "", "7727724", "F",
					"", end, "Luiz", "Nancy", turma);
			Pessoa harry = new Aluno("00000000002", "Harry", "", "7727724", "F",
					"", end, "Luiz", "Nancy", turma);
			Pessoa alana = new Aluno("00000000003", "Laryssa", "", "7727724", "F",
					"", end, "Luiz", "Nancy", turma);
			Pessoa geovane = new Aluno("00000000004", "Geovane Silva Pereira",
					"08/11/1992", "83830480504", "M", "34626209", end, "Gilvan",
					"Marcia", turma);

			Pessoa geovane2 = new Aluno("00000000005", "Geovane2", "", "7727724",
					"F", "", end, "Luiz", "Nancy", turma);

			Pessoa novo = new Aluno("00000000006", "Fulano", "", "7727724", "F",
					"", end, "Luiz", "Nancy", turma);

			Pessoa timmy = new Aluno("00000000007", "Timmy Turner", "", "34655843",
					"F", "", end, "Pai", "Mae", turma);
			Pessoa bob = new Aluno("00000000008", "BobSponge SquarePants", "",
					"12321", "A", "", end, "Pai", "Mae", turma);
			Pessoa frango = new Aluno("00000000009", "Frango", "", "1245789", "F",
					"", end, "Pai", "Mae", turma);
			Pessoa vaca = new Aluno("00000000010", "vaca", "", "1245789", "F", "",
					end, "Pai", "Mae", turma);
			Pessoa jerry = new Aluno("00000000012", "Jerry", "", "12235521", "F",
					"", end, "Pai", "Mae", turma);
			Pessoa tom = new Aluno("00000000013", "Tom", "", "12235521", "F", "",
					end, "Pai", "Mae", turma);
			Pessoa johny = new Aluno("00000000014", "Johny", "", "12233421", "F",
					"", end, "Pai", "Mae", turma);
			Pessoa lindinha = new Aluno("00000000015", "Lindinha", "", "122321",
					"F", "", end, "Pai", "Mae", turma);
			Pessoa docinho = new Aluno("00000000011", "Docinho", "", "122321", "F",
					"", end, "Pai", "Mae", turma);
			Pessoa flor = new Aluno("00000000016", "Florzinha", "", "122321", "F",
					"", end, "Pai", "Mae", turma);
			Pessoa untonio = new Professor("00000000017", "Untonio", "",
					"123454433", "F", "333333333", end, "ser pai");
			Pessoa prof = new Professor("00000000018", "Profinho", "",
					"1234588833", "F", "333333333", end, "ser pai");
			Pessoa untonio2 = new Professor("00000000019", "Untonio", "",
					"123454433", "F", "333333333", end, "ser pai");
			Pessoa untonio3 = new Professor("00000000020", "Untonio", "",
					"123454433", "F", "333333333", end, "ser pai");
			Pessoa untonio4 = new Professor("00000000021", "Untonio", "",
					"123454433", "F", "333333333", end, "ser pai");

			Disciplina d1 = new Disciplina("disc", "ser legal");
			Disciplina d2 = new Disciplina("disc2", "ser legal");
			Disciplina d3 = new Disciplina("disc3", "ser legal");
			turma.inserirDisciplina(d1);
			turma.inserirDisciplina(d2);
			turma.inserirDisciplina(d3);
			try {
				pessoas.inserir(untonio4);
				pessoas.inserir(untonio3);
				pessoas.inserir(untonio2);
				pessoas.inserir(geovane);
				pessoas.inserir(geovane2);
				pessoas.inserir(harry);
				pessoas.inserir(bruna);
				pessoas.inserir(novo);
				pessoas.inserir(alana);
				pessoas.inserir(timmy);
				pessoas.inserir(bob);
				pessoas.inserir(vaca);
				pessoas.inserir(frango);
				pessoas.inserir(jerry);
				pessoas.inserir(johny);
				pessoas.inserir(tom);
				pessoas.inserir(flor);
				pessoas.inserir(docinho);
				pessoas.inserir(lindinha);
				pessoas.inserir(untonio);
				pessoas.inserir(prof);
				disciplinas.inserir(d1);
				disciplinas.inserir(d2);
				disciplinas.inserir(d3);
				turmas.inserir(turma);
				((Professor) untonio).inserirDisciplina(d1);
				((Professor) untonio).inserirDisciplina(d2);
				((Professor) untonio).inserirDisciplina(d3);
				((Professor) untonio2).inserirTurma(turma);
				((Professor) untonio2).inserirDisciplina(d1);
				((Professor) untonio2).inserirDisciplina(d2);
				((Professor) untonio2).inserirDisciplina(d3);
				((Professor) untonio2).inserirTurma(turma);
				((Professor) untonio4).inserirDisciplina(d1);
				((Professor) untonio4).inserirDisciplina(d2);
				((Professor) untonio4).inserirDisciplina(d3);
				((Professor) untonio4).inserirTurma(turma);
				((Professor) untonio3).inserirDisciplina(d1);
				((Professor) untonio3).inserirDisciplina(d2);
				((Professor) untonio3).inserirDisciplina(d3);
				((Professor) untonio3).inserirTurma(turma);
				((Professor) prof).inserirDisciplina(d1);
				((Professor) prof).inserirDisciplina(d2);
				((Professor) prof).inserirDisciplina(d3);
				((Professor) prof).inserirTurma(turma);
			} catch (RepositorioException e) {
				System.out.println("erro");
			}	
			this.inserirDisciplina("d232", "Ser legal!");
			this.inserirDisciplina("d332", "Ser legal!");
			this.inserirTurma("Turma1");
			this.inserirDisciplina("d123", "Funcionar");
			//System.out.println(this.procurarTurma("Turma1").getNome());
			//System.out.println(this.procurarDisciplina("d123"));
			this.procurarTurma("Turma1").inserirDisciplina(this.procurarDisciplina("d123"));
			this.inserirAluno("000000000101", "Wandergleison", "01/01/1111", "8349638",
					"M", "34626209", "rua", "1234", "bairro", "51030630",
					"cidade", "estado", "pais", "pai", "mae", this.getTurmas()
							.procurar("Turma1"));
			this.inserirAluno("000000000022", "Maria do Carmo", "01/01/1111",
					"8349638", "M", "34626209", "rua", "1234", "bairro",
					"51030630", "cidade", "estado", "pais", "pai", "mae", this
							.getTurmas().procurar("Turma1"));
			this.inserirAluno("000000000303", "Maria Aparecida", "01/01/1111",
					"8349638", "M", "34626209", "rua", "1234", "bairro",
					"51030630", "cidade", "estado", "pais", "pai", "mae", this
							.getTurmas().procurar("Turma1"));
			this.inserirAluno("000000000204", "Izinalda", "01/01/1111",
					"8349638", "M", "34626209", "rua", "1234", "bairro",
					"51030630", "cidade", "estado", "pais", "pai", "mae", this
							.getTurmas().procurar("Turma1"));
			this.inserirAluno("000000000205", "Yslan", "01/01/1111",
					"8349638", "M", "34626209", "rua", "1234", "bairro",
					"51030630", "cidade", "estado", "pais", "pai", "mae", this
							.getTurmas().procurar("Turma1"));
			this.inserirAluno("000000000026", "Creuza", "01/01/1111",
					"8349638", "M", "34626209", "rua", "1234", "bairro",
					"51030630", "cidade", "estado", "pais", "pai", "mae", this
							.getTurmas().procurar("Turma1"));
			this.inserirProfessor("002000000008", "Dorgival", "01/01/2001",
					"2234234123", "Feminino", "44444444", "tua", "numero",
					"bairro", "cep", "cidade", "estado", "pais", "funcionar");
		this.	inserirProfessor("000010000009", "Edvaldo", "01/01/2001",
					"2234234123", "Feminino", "44444444", "tua", "numero",
					"bairro", "cep", "cidade", "estado", "pais", "funcionar");
			this.inserirProfessor("002000000010", "Socorro", "01/01/2001",
					"2234234123", "Feminino", "44444444", "tua", "numero",
					"bairro", "cep", "cidade", "estado", "pais", "funcionar");
		} catch (EntradaInvalidaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ElementoJaCadastradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RepositorioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ElementoNaoEncontradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
	}

}
