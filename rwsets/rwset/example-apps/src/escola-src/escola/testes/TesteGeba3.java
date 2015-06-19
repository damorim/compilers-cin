package escola.testes;
import escola.classesBase.*;
import escola.dados.RepositorioListaPessoa;
import escola.excecoes.ElementoNaoEncontradoException;
public class TesteGeba3 {

	public static void main(String[] args) {

		// FUNCIONOU TUDO DO ARRAY DE PESSOA!

		RepositorioListaPessoa pessoas = new RepositorioListaPessoa();
		Endereco end = new Endereco("sddsad", "sdasd", "Sadasd", "sdsad",
				"asd", "", "asdasd");
		Turma turma = new Turma("tumrma1");

		Pessoa bruna = new Aluno("43536787656", "Bruna","", "7727724", "F","", end,
				"Luiz", "Nancy", turma);
		Pessoa harry = new Aluno("25365576866", "Harry","", "7727724", "F","", end,
				"Luiz", "Nancy", turma);
		Pessoa alana = new Aluno("47623456456", "Laryssa","", "7727724", "F","", end,
				"Luiz", "Nancy", turma);
		Pessoa geba = new Aluno("83830480504", "Geovane","", "7727724", "F","", end,
				"Luiz", "Nancy", turma);

		Pessoa novo = new Aluno("000000", "Fulano","", "7727724", "F","", end,
				"Luiz", "Nancy", turma);

		Pessoa novo2 = new Aluno("000000", "Fulano2","", "7727724", "F","", end,
				"Luiz", "Nancy", turma);

		pessoas.inserir(geba);
		pessoas.inserir(harry);
		pessoas.inserir(bruna);
		pessoas.inserir(novo);
		pessoas.inserir(alana);

		System.out.println("\nImprima:\n");

		System.out.println("\n\n" + pessoas.imprimir());

		
		System.out.println("\nProcurando geba:o primeiro da lista\n");
		try {
			System.out.println(pessoas.procurar("83830480504").getNome());
		} catch (ElementoNaoEncontradoException e) {
			System.out.println("Nao achei");
		}

		
		System.out.println("\nProcurando Alana:a ultima da lista\n");
		try {
			System.out.println(pessoas.procurar("47623456456").getNome());
		} catch (ElementoNaoEncontradoException e) {
			System.out.println("Nao achei");
		}

		
		System.out.println("\nProcurando Harry:la no meioda lista\n");
		try {
			System.out.println(pessoas.procurar("25365576866").getNome());
		} catch (ElementoNaoEncontradoException e) {
			System.out.println("Nao achei");
		}

		
		System.out.println("\n\nTeste aualizar Fulano p Fulano2\n");
		try {
			pessoas.atualizar(novo2);
		} catch (ElementoNaoEncontradoException e) {
			System.out.println("Nao achei");
		}

		
		Pessoa alana2 = new Aluno("47623456456", "Alana","", "7727724", "F","", end,
				"Luiz", "Nancy", turma);
		Pessoa geba2 = new Aluno("83830480504", "Geba","", "7727724", "F", "",end,
				"Luiz", "Nancy", turma);
		System.out.println("\n\nTeste aualizar Laryssa p Alana\n");
		try {
			pessoas.atualizar(alana2);
		} catch (ElementoNaoEncontradoException e) {
			System.out.println("N�o achei");
		}

		
		System.out.println("\n\nTeste aualizar Geovane p Geba\n");
		try {
			pessoas.atualizar(geba2);
		} catch (ElementoNaoEncontradoException e) {
			System.out.println("Nao achei");
		}
		System.out.println("\n\n" + pessoas.imprimir());

		
		
		System.out.println("Apagando alana");
		try {
			pessoas.remover("47623456456");
		} catch (ElementoNaoEncontradoException e) {
			System.out.println("Nao achei");
		}
		System.out.println("\n\n" + pessoas.imprimir());

		
		System.out.println("apagando Harry");
		try {
			pessoas.remover("25365576866");
		} catch (ElementoNaoEncontradoException e) {
			System.out.println("Nao achei");
		}
		System.out.println(pessoas.imprimir());

		
		System.out.println("Apagaremos geba");
		try {
			pessoas.remover("83830480504");
		} catch (ElementoNaoEncontradoException e) {
			System.out.println("Nao achei");
		}
		
		
		System.out.println("\n\n" + pessoas.imprimir());
	}
}
