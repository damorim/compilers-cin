package biblioteca.dados;

import java.util.Iterator;

import biblioteca.dados.entidades.Sala;
import biblioteca.dados.lista.ListaSala;
import biblioteca.dados.lista.Node;

public class IteratorSala implements Iterator<Sala> {

	private ListaSala lista;
	private int index;

	public IteratorSala(ListaSala obj) {
		lista = obj;
		index = -1;
	}

	public IteratorSala(Sala[] obj) {
		ListaSala aux = new ListaSala();

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
	public Sala next() {
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
