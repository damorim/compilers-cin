package escola.testes;

import java.io.IOException;
import java.util.Iterator;

import escola.classesBase.*;
import escola.dados.*;
import escola.excecoes.RepositorioException;

public class testeArquivoTurmaEDisciplina {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Repositorio<Pessoa> pessoas = new RepositorioArquivoPessoa();
		Repositorio<Turma> turmas = new RepositorioArquivoTurma();
		Repositorio<Disciplina> disc = new RepositorioArrayDisciplina();

		Disciplina d = new Disciplina("turma", "ementz");
		Disciplina d2 = new Disciplina("portugues", "ementa");
		Disciplina d3 = new Disciplina("matematica", "ementa");
		Disciplina d4 = new Disciplina("ingles", "ementa");

		Turma t = new Turma("p5");
		Turma t1 = new Turma("turma p");
		Turma t2 = new Turma("turminha");
		Turma t3 = new Turma("nomeee");

		try {
			disc.inserir(d);
			disc.inserir(d2);
			disc.inserir(d3);
			disc.inserir(d4);
			turmas.inserir(t);
			turmas.inserir(t1);
			turmas.inserir(t2);
			turmas.inserir(t3);

		} catch (RepositorioException e) {
			System.out.println("rep excep");
		}

		System.out.println("\niterator:\n");

		Iterator<Turma> itt = new IteratorArquivoTurma("Turmas");
		System.out.println(itt.hasNext());
		while (itt.hasNext()) {
			System.out.println("entrou no hasnext");
			System.out.println(itt.next().resumo());
		}

		System.out.println("\niterator:\n");

		Iterator<Disciplina> itd = disc.getIterator();
		while (itd.hasNext()) {
			System.out.println(itd.next().resumo());
		}

	}

}
