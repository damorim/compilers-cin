package biblioteca.dados.repositorio.arquivo;

import java.io.IOException;

import biblioteca.dados.entidades.Aluno;
import biblioteca.dados.entidades.Endereco;
import biblioteca.dados.entidades.Livro;
import biblioteca.dados.entidades.Pessoa;
import biblioteca.dados.entidades.Sala;
import biblioteca.dados.repositorio.interfaces.RepositorioLivro;
import biblioteca.dados.repositorio.interfaces.RepositorioPessoa;
import biblioteca.dados.repositorio.interfaces.RepositorioSala;
import biblioteca.negocios.ManipularLivro;
import biblioteca.negocios.ManipularPessoa;
import biblioteca.negocios.ManipularSala;
import biblioteca.negocios.exceptions.RealizarLoginException;
import biblioteca.negocios.exceptions.livro.LivroJaCadastradoException;
import biblioteca.negocios.exceptions.pessoa.PessoaJaCadastradaException;
import biblioteca.negocios.exceptions.sala.SalaJaCadastradaException;
import biblioteca.negocios.exceptions.sala.SalaNaoEncontradaException;

public class TesteRepArquivo {
	public static void main (String arg[]) {
		
		
		RepositorioSala rep = new RepositorioSalaArquivo();
		
		
		Sala sala = new Sala("21", 13);
		Sala sala2 = new Sala("22", 11);
		Sala sala3 = new Sala("23",17);
		
		sala.setDisponivel(false);
		sala.setTotalEmprestimos(2);
		sala.setCpfResponsavel("07583611490");
		sala.setDataDevolucao("22/07/1994");
		try {
			Sala resposta = rep.procurarSala(sala.getCodigo());
			
		rep.atualizarSala(sala);	
			
			System.out.println(sala.getCapacidade());
			System.out.println(sala.getDataDevolucao());
			System.out.println(sala.getTotalEmprestimos());
			System.out.println(sala.getCpfResponsavel());
		System.out.println(sala.isDisponivel())	;
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SalaNaoEncontradaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		
	}

}
