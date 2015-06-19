package escola.classesBase;

import java.util.Iterator;

import escola.dados.RepositorioArrayDisciplina;
import escola.dados.RepositorioArrayPessoa;
import escola.excecoes.ElementoNaoEncontradoException;

public class Turma {

	private String nome;
	private RepositorioArrayPessoa alunos;
	private RepositorioArrayDisciplina disciplinas;

	public Turma(String nome) {
		this.nome = nome;
		this.alunos = new RepositorioArrayPessoa();
		this.disciplinas = new RepositorioArrayDisciplina();
	}

	public void inserirDisciplinaNova(String nome, String ementa) {
		Disciplina d = new Disciplina(nome, ementa);
		this.disciplinas.inserir(d);
	}

	public void inserirDisciplina(Disciplina d) {
	this.disciplinas.inserir(d);
	}

	public void inserirAluno(Pessoa p) {
		this.alunos.inserir(p);
	}

	public void removerDisciplina(String nome)
			throws ElementoNaoEncontradoException {
		this.disciplinas.remover(nome);
	}

	public void removerAluno(String cpf) throws ElementoNaoEncontradoException {
		this.alunos.remover(nome);
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public RepositorioArrayPessoa getAlunos() {
		return this.alunos;
	}

	public void setAlunos(RepositorioArrayPessoa alunos) {
		this.alunos = alunos;
	}

	public RepositorioArrayDisciplina getDisciplinas() {
		return this.disciplinas;
	}

	public void setDisciplinas(RepositorioArrayDisciplina disciplinas) {
		this.disciplinas = disciplinas;
	}

	public int qtdDeDisciplinas() {
		return this.disciplinas.getCont();
	}

	public String toString() {
		return this.nome;
	}

	public String resumo() {
		String resumo = "Nome: " + this.nome;
		Iterator<Disciplina> itDisc = this.disciplinas.getIterator();
		if (itDisc.hasNext()) {
			resumo = resumo + "\nDisciplinas:";
			while (itDisc.hasNext()) {
				resumo += "\n" + itDisc.next().getNome();
			}
		}
		Iterator<Pessoa> itAlun = this.alunos.getIterator();
		if (itAlun.hasNext()) {
			resumo = resumo + "\nAlunos:";
			while (itDisc.hasNext()) {
				
				resumo += "\n" + itAlun.next().getNome();
			}
		}
		//System.out.println(resumo);
		return resumo;

	}
}
