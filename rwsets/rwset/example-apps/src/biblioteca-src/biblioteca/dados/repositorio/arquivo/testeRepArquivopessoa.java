package biblioteca.dados.repositorio.arquivo;

import java.io.IOException;

import biblioteca.dados.entidades.Aluno;
import biblioteca.dados.entidades.Endereco;
import biblioteca.dados.entidades.Funcionario;
import biblioteca.dados.entidades.Pessoa;
import biblioteca.dados.repositorio.interfaces.RepositorioPessoa;
import biblioteca.negocios.exceptions.pessoa.PessoaNaoEncontradaException;

public class testeRepArquivopessoa {

	
	public static void main (String arg[]) {
		
		RepositorioPessoa rep = new RepositorioPessoaArquivo();
		
		
		Endereco endereco = new Endereco("Rua das cal�adas", "30", "102", "84627222");
		
		Pessoa pessoa = new Aluno("Daniela", " 07583611490",endereco, " 35677567",
				"111");
		
		Endereco endereco2 = new Endereco("Rua das tormentas", "22", "205", "934857731");
		
		Pessoa pessoa2 = new Funcionario("Roberto", "2191386482", endereco2,"83666382", "123" );
		
		//try {
		//	rep.inserirPessoa(pessoa2);
		//} catch (PessoaJaCadastradaException | IOException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
	//	}
		
		
		//try {
		//	rep.inserirPessoa(pessoa);
	//	} catch (PessoaJaCadastradaException | IOException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
	//	}
		
		
		try {
			rep.removerPessoa(pessoa.getCpf());
		} catch (PessoaNaoEncontradaException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
