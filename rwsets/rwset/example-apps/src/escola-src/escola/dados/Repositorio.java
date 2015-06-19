package escola.dados;

import java.util.Iterator;

import escola.excecoes.ElementoJaCadastradoException;
import escola.excecoes.ElementoNaoEncontradoException;
import escola.excecoes.RepositorioException;

public interface Repositorio<T> {

	public void inserir(T item) throws RepositorioException;
	public T procurar(String procura)throws ElementoNaoEncontradoException;
	public void atualizar(T item) throws ElementoNaoEncontradoException, RepositorioException;
	public void remover(String remover) throws ElementoNaoEncontradoException, RepositorioException;	
	public Iterator<T> getIterator();
}
/** Esta Interface serve de basse para qualquer repositorio. 
*/