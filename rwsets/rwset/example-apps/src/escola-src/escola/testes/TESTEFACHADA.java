package escola.testes;

import java.util.Iterator;

import javax.swing.JOptionPane;

import escola.classesBase.*;
import escola.dados.*;
import escola.excecoes.ElementoJaCadastradoException;
import escola.excecoes.ElementoNaoEncontradoException;
import escola.excecoes.EntradaInvalidaException;
import escola.excecoes.RepositorioException;
import escola.fachadaEscola.Escola;
import escola.principal.PaginaPrincipal;

public class TESTEFACHADA {

	/**
	 * @param args
	 * @throws RepositorioException
	 * @throws ElementoJaCadastradoException
	 * @throws EntradaInvalidaException
	 * @throws ElementoNaoEncontradoException
	 */
	public static void main(String[] args) throws EntradaInvalidaException,
			ElementoJaCadastradoException, RepositorioException,
			ElementoNaoEncontradoException {
		Escola fachada = new Escola();

		System.out.println("Pegando os alunoss: \n");

		fachada.inserirTurma("Turmaz1");

		/**
		 * fachada.inserirAluno("344000134342", "heey", "01/01/1111", "8349638",
		 * "M", "34626209", "rua", "1234", "bairro", "51030630", "cidade",
		 * "estado", "pais", "pai", "mae",
		 * fachada.getTurmas().procurar("Turma1"));
		 * fachada.inserirAluno("2342300044300134342", "heey", "01/01/1111",
		 * "8349638", "M", "34626209", "rua", "1234", "bairro", "51030630",
		 * "cidade", "estado", "pais", "pai", "mae",
		 * fachada.getTurmas().procurar("Turma1"));
		 * fachada.inserirAluno("32423413234324342", "heey", "01/01/1111",
		 * "8349638", "M", "34626209", "rua", "1234", "bairro", "51030630",
		 * "cidade", "estado", "pais", "pai", "mae",
		 * fachada.getTurmas().procurar("Turma1"));
		 * fachada.inserirAluno("234234233242334234234", "heey", "01/01/1111",
		 * "8349638", "M", "34626209", "rua", "1234", "bairro", "51030630",
		 * "cidade", "estado", "pais", "pai", "mae",
		 * fachada.getTurmas().procurar("Turma1"));
		 * fachada.inserirAluno("0003423333343232", "heey", "01/01/1111",
		 * "8349638", "M", "34626209", "rua", "1234", "bairro", "51030630",
		 * "cidade", "estado", "pais", "pai", "mae",
		 * fachada.getTurmas().procurar("Turma1"));
		 * fachada.inserirAluno("000324333432000134342", "heey", "01/01/1111",
		 * "8349638", "M", "34626209", "rua", "1234", "bairro", "51030630",
		 * "cidade", "estado", "pais", "pai", "mae",
		 * fachada.getTurmas().procurar("Turma1"));
		 */
		Iterator<Pessoa> it1 = fachada.getAlunos().getIterator();
		while (it1.hasNext()) {
			System.out.println(it1.next().resumo());
		}

		String procura = "lin";
		RepositorioArrayPessoa resultadoPesquisa = new RepositorioArrayPessoa();

		System.out
				.println(fachada.getAlunos().procurarNome(procura).imprimir()); // AQUI
																				// O
																				// METODO
																				// PROCURAR
																				// NOME
																				// RETORNA
																				// DOIS (E UM TB)
																				// ELEMENTOS
																				// E
																				// ELE
																				// IMPRIME
																				// NORMAL,
																				// SEM
																				// O USO
																				// DO
																				// ITERATOR

		try {
			resultadoPesquisa = PaginaPrincipal.fachada.getAlunos()
					.procurarNome(procura);
		} catch (ElementoNaoEncontradoException e1) {
			// String aviso = "A pesquisa nao retornou resultados";

		}

		Iterator<Pessoa> it = resultadoPesquisa.iterator();
		while (it.hasNext()) {
			Pessoa pessoa = it.next();
			System.out.println(pessoa.resumo());
		}

	}
}
