package escola.testes;

import escola.classesBase.Aluno;
import escola.classesBase.Endereco;
import escola.classesBase.Pessoa;
import escola.classesBase.Professor;
import escola.classesBase.Turma;
import escola.dados.RepositorioArrayPessoa;
import escola.dados.RepositorioListaPessoa;
import escola.excecoes.ElementoNaoEncontradoException;
import escola.gui.EditarProfessorFrame;

public class EscolaTeste {

	private RepositorioArrayPessoa pessoas = new RepositorioArrayPessoa();
	
	public EscolaTeste(){
		
		Endereco end = new Endereco("sddsad", "sdasd", "Sadasd", "sdsad",
				"asd", "", "asdasd");
		Turma turma = new Turma("tumrma1");
		Pessoa bruna = new Aluno("43536787656", "Bruna","", "7727724", "F","", end,
				"Luiz", "Nancy", turma);
		Pessoa harry = new Aluno("25365576866", "Harry","", "7727724", "F","", end,
				"Luiz", "Nancy", turma);
		Pessoa alana = new Aluno("47623456456", "Laryssa","", "7727724", "F","", end,
				"Luiz", "Nancy", turma);
		Pessoa geovane = new Aluno("83830480504", "Geovane","", "7727724", "F","", end,
				"Luiz", "Nancy", turma);

		Pessoa geovane2 = new Aluno("838304802504", "Geovane2","", "7727724", "F","", end,
				"Luiz", "Nancy", turma);

		Pessoa novo = new Aluno("000000", "Fulano","", "7727724", "F","", end,
				"Luiz", "Nancy", turma);

		Pessoa timmy = new Aluno("123456789", "Timmy Turner","", "34655843", "F",
				"",end, "Pai", "Mae", turma);
		Pessoa bob = new Aluno("123456000","BobSponge SquarePants", "", "12321", "A", "", end, "Pai", "Mae", turma);
		Pessoa frango = new Aluno("123456001","Frango", "", "1245789", "F", "", end, "Pai", "Mae", turma);
		Pessoa vaca = new Aluno("123456002","vaca", "", "1245789", "F", "", end, "Pai", "Mae", turma);
		Pessoa jerry = new Aluno("123456005","Jerry", "", "12235521", "F", "", end, "Pai", "Mae", turma);
		Pessoa tom = new Aluno("123456004","Tom", "", "12235521", "F", "", end, "Pai", "Mae", turma);
		Pessoa johny = new Aluno("123456006","Johny", "", "12233421", "F", "", end, "Pai", "Mae", turma);
		Pessoa lindinha = new Aluno("123456007","Lindinha", "", "122321", "F", "", end, "Pai", "Mae", turma);
		Pessoa docinho = new Aluno("123456008","Docinho", "", "122321", "F", "", end, "Pai", "Mae", turma);
		Pessoa flor = new Aluno("123456009","Florzinha", "", "122321", "F", "", end, "Pai", "Mae", turma);
		Pessoa untonio = new Professor("123454321", "Untonio", "","123454433","F", "333333333", end,"se pai");
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
		
		
	}
	RepositorioArrayPessoa getRepositorio(){
		return this.pessoas;
	}
	public Professor getProfessor() {
		Professor untonio = null; 
		try {
			untonio = (Professor) this.pessoas.procurar("123454321"); 
			 
		} catch (ElementoNaoEncontradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return untonio;
		}
	
	public static void main(String[] args) {	
	EditarProfessorFrame frame = new EditarProfessorFrame();
	
	}
	
	
	
}
