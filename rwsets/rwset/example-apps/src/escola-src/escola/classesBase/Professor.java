package escola.classesBase;

import java.util.Iterator;

import escola.dados.*;
import escola.excecoes.ElementoJaCadastradoException;
import escola.excecoes.ElementoNaoEncontradoException;
import escola.excecoes.RepositorioException;

public class Professor extends Empregado {
	private Repositorio<Turma> turmas;
	private Repositorio<Disciplina> disciplinas;

	public Professor(String cpf, String nome, String dataNasc,
			String rg, String sexo, String telefone, Endereco endereco,
			String funcao) {
		super(cpf, nome, dataNasc, rg, sexo, telefone, endereco, funcao);
		this.turmas = new RepositorioArrayTurma();
		this.disciplinas = new RepositorioArrayDisciplina();

	}

	public Repositorio<Turma> getTurmas() {
		return this.turmas;
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
	
	public void inserirDisciplina(Disciplina d) throws ElementoJaCadastradoException{
		try {
			this.disciplinas.inserir(d);
		} catch (RepositorioException e) {
			System.out.println("erro no inserir disciplinas");
		}
	}
	
	public void removerDisciplina(Disciplina d) throws RepositorioException, ElementoNaoEncontradoException{
		try{
			this.disciplinas.remover(d.getNome());
		}catch(ElementoNaoEncontradoException e){
			throw e;			
		}catch(RepositorioException a){
			throw a;
		}
		
	}
	
	public void inserirTurma(Turma t) throws ElementoJaCadastradoException{
		try {
			this.turmas.inserir(t);
		} catch (RepositorioException e) {
			System.out.println("erro no inserir turmas");
		}
	}
	
	public void removerTurma(Turma t) throws RepositorioException, ElementoNaoEncontradoException{
		try{
			this.turmas.remover(t.getNome());
		}catch(ElementoNaoEncontradoException e){
			throw e;			
		}catch(RepositorioException a){
			throw a;
		}
		
	}
	
	public Turma procurarTurma(String nome) throws ElementoNaoEncontradoException{
		return this.turmas.procurar(nome);
	}
	public Disciplina procurarDisciplina(String nome) throws ElementoNaoEncontradoException{
		return this.disciplinas.procurar(nome);
	}

	public String resumo(){
		String resumo = super.resumo();
		
		Iterator<Turma> itTurmas = this.turmas.getIterator();
		Iterator<Disciplina> itDisciplinas = this.disciplinas.getIterator();
		resumo+="\nTurmas:";
		while (itTurmas.hasNext()){
			Turma turma = itTurmas.next();
			resumo+="\n"+turma.getNome();
		}
		resumo+="\nDisciplinas:";
		while (itDisciplinas.hasNext()){
			Disciplina disciplina = itDisciplinas.next();
			resumo+="\n"+disciplina.getNome();
		}
		return resumo;
	}


}
