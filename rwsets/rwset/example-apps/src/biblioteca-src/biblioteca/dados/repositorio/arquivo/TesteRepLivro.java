package biblioteca.dados.repositorio.arquivo;

import java.io.IOException;

import biblioteca.dados.entidades.Livro;
import biblioteca.dados.repositorio.interfaces.RepositorioLivro;
import biblioteca.negocios.exceptions.livro.LivroJaCadastradoException;

public class TesteRepLivro {
	
	public static void main (String []args){
		
		
		RepositorioLivro rep = new RepositorioLivroArquivo();
		
		Livro livro1 = new Livro("222", "O poema", "David B.", "poemas", "Colet�nea com mais de 100 poemas");
		Livro livro2 = new Livro("231", "A viagem", "Nathan R.", "Aventura", "A vida de Nathan, o viajante");
		
		
	
		try {
			rep.inserirLivro(livro1);
		} catch (LivroJaCadastradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
