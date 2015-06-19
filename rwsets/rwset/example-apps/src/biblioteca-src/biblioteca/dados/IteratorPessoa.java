package biblioteca.dados;

import java.util.Iterator;

import biblioteca.dados.entidades.Pessoa;
import biblioteca.dados.lista.ListaPessoa;
import biblioteca.dados.lista.Node;

public class IteratorPessoa implements Iterator<Pessoa> {

	private ListaPessoa lista;
	private int index;

	public IteratorPessoa(ListaPessoa cadastro) {
		lista = cadastro;
		index = -1;
	}

	public IteratorPessoa(Pessoa[] obj) {
		ListaPessoa aux = new ListaPessoa();
		for (int i = 0; i < obj.length; i++) {
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
	public Pessoa next() {
		return lista.get(index);
	}

	@Override
	public void remove() {
		lista.remove(index);
	}

	public int getIndex() {
		return this.index;
	}

	public int size() {
		return lista.size();
	}
	
}
