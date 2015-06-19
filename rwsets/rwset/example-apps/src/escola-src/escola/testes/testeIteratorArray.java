package escola.testes;

import java.util.Iterator;

import escola.classesBase.Aluno;
import escola.classesBase.Endereco;
import escola.classesBase.Pessoa;
import escola.classesBase.Turma;
import escola.dados.RepositorioArrayPessoa;

public class testeIteratorArray {

	
	public static void main(String[] args){
		Endereco end = new Endereco("sddsad", "sdasd", "Sadasd", "sdsad",
				"asd", "", "asdasd");
		Turma turma = new Turma("tumrma1");
		Pessoa bruna = new Aluno("43536787656", "Bruna","", "7727724", "F","", end,
				"Luiz", "Nancy", turma);
		
		RepositorioArrayPessoa repositorio = new RepositorioArrayPessoa();
		repositorio.inserir(bruna);
		Iterator<Pessoa> it = repositorio.getIterator();
		while(it.hasNext()){
			System.out.println(it.next().getNome());
		}
	}
	
	
	
}

