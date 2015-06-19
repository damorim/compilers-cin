package escola.testes;

import escola.classesBase.Aluno;
import escola.classesBase.Endereco;
import escola.classesBase.Pessoa;
import escola.classesBase.Professor;
import escola.classesBase.Turma;
import escola.dados.RepositorioArquivoPessoa;
import escola.excecoes.ElementoNaoEncontradoException;
import escola.excecoes.RepositorioException;

public class Teste {

	public static void main(String[] args) {

		System.out.println("comecou");
		

		RepositorioArquivoPessoa pessoas = new RepositorioArquivoPessoa();

		Endereco end = new Endereco("sddsad", "sdasd", "Sadasd", "sdsad",
				"asd", "", "asdasd");
		Turma turma = new Turma("tumrma1");

		Pessoa bruna = new Aluno("43536787656", "Bruna","23/03/1995", "7727724", "F", "",end,
				"Luiz", "Nancy", turma);
		// Pessoa harry = new Aluno("25365576866", "Harry", "7727724", "F", end,
		// "Luiz", "Nancy", turma);
		Pessoa laryssa = new Professor("47623456456", "Laryssa","", "7727724",
				"F","", end, "ser legal");
		Pessoa geba = new Aluno("83830480504", "Geovane","", "7727724", "F","", end,
				"Luiz", "Nancy", turma);

		Pessoa novo = new Aluno("000000", "Fulano","", "7727724", "F", "",end,
				"Luiz", "Nancy", turma);

		Pessoa prof = new Professor("47623456456", "Prof","", "7727724", "F", "",end,
				"ser legal");
		
		Pessoa item = new Professor("47623456456", "profatualizado","", "7727724", "F", "",end,
				"ser legal");

		// Pessoa novo2 = new Aluno("000000", "Fulano2", "7727724", "F", end,
		// "Luiz", "Nancy", turma);
		
		System.out.println("imprimindo: ");
		System.out.println(pessoas.getPessoas().imprimir());

		try {
			pessoas.inserir(prof);
			pessoas.inserir(geba);
			pessoas.inserir(bruna);
			pessoas.inserir(novo);
			pessoas.inserir(laryssa);
		} catch (RepositorioException e) {
			System.out.println("n deu certo");
		}
		
		System.out.println("imprimindo: ");
		System.out.println(pessoas.getPessoas().imprimir());
		
		try {
			pessoas.remover("43536787656");
			pessoas.atualizar(item);
			System.out.println(pessoas.procurar("83830480504").getNome());
		} catch (RepositorioException e) {
			System.out.println("rep excep");
		} catch (ElementoNaoEncontradoException e) {
			System.out.println("n encontrado");
		}
		
		//System.out.println("imprimindo: ");
		//System.out.println(pessoas.getPessoas().imprimir());
	}

}
