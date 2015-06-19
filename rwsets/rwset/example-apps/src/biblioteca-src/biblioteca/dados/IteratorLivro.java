package biblioteca.dados;

import java.util.Iterator;

import biblioteca.dados.entidades.Livro;
import biblioteca.dados.lista.ListaLivro;
import biblioteca.dados.lista.Node;

public class IteratorLivro implements Iterator<Livro> {

	private ListaLivro lista;
	private int index;

	public IteratorLivro(ListaLivro cadastro) {
		lista = cadastro;
		index = -1;
	}

	public IteratorLivro(Livro[] obj) {
		ListaLivro aux = new ListaLivro();
		for (int i = 0; i < obj.length; i += 1) {
			Node no = new Node(obj[i]);
			aux.insert(no);
		}
		lista = aux;
		index = -1;
	}

	@Override
	public boolean hasNext() {
		boolean resposta = false;
		index += 1;

		if (index < lista.size() && lista.get(index) != null) {
			resposta = true;
		}
		return resposta;
	}

	@Override
	public Livro next() {
		return lista.get(index);
	}

	@Override
	public void remove() {
		lista.remove(index);
	}

	public int getIndex() {
		return index;
	}

	public int size() {
		return lista.size();
	}
	
}
