package biblioteca.dados.lista;

public abstract class Lista {

	protected Node root;
	protected Node current;
	protected int index;

	public Lista() {
		root = null;
		current = null;
		index = -1;
	}

	public boolean isEmpty() {
		boolean resposta = false;
		
		if (root == null) {
			resposta = true;
		}
		return resposta;
	}

	public int size() {
		return (this.index + 1);
	}

	public void insert(Node no) {
		if (isEmpty()) {
			// Lista vazia. inserindo no primeiro elemento
			this.root = no;
			this.current = no;
			no.setNext(null);
			index += 1;
		} else if (this.root.getNext() == null) {
			// lista com apenas 1 elemento.
			this.root.setNext(no);
			this.current = no;
			no.setNext(null);
			index += 1;
		} else {
			// Lista com mais de 1 elemento.
			this.current.setNext(no);
			this.current = no;
			no.setNext(null);
			index += 1;
		}
	}

	public void remove(int index) {
		if (!isEmpty()) {
			if (index <= this.index) {
				if (index == 0) {
					// remover o root
					this.root = this.root.getNext();
					this.index -= 1;
				} else {
					// remover qualquer posi��o
					int i = 0;
					Node aux = this.root;
					Node auxBefore = null;

					while (aux != null && i != index) {
						i += 1;
						auxBefore = aux;
						aux = aux.getNext();
					}
					auxBefore.setNext(aux.getNext());
					aux.setNext(null);
					this.index -= 1;
				}
			}
		}
	}

	public void set(int index, Node no) {

		if (!isEmpty() && index < this.index) {
			Node aux = this.root;
			int i = 0;
			while (i != index) {
				i += 1;
				aux = aux.getNext();
			}

			aux.setObjeto(no.getObjeto());
		}
	}

	public String print() {
		Node aux = this.root;
		String retorno = "";
		int i = 0;
		while (aux != null) {
			retorno += "[" + i + "] \n" + aux.getObjeto().toString() + "\n";
			i++;
			aux = aux.getNext();
		}

		return retorno;
	}

}
