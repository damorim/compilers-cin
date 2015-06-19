package biblioteca.dados.lista;

import biblioteca.dados.entidades.Livro;

public class ListaLivro extends Lista {

	public ListaLivro() {
		super();
	}

	public Livro get(int index) {
		Livro livro = null;
		
		if (!isEmpty()) {
			if (index <= this.index) {
				if (index == 0) {
					// remover o root
					livro = (Livro) this.root.getObjeto();
				} else {
					//remover qualquer posi��o
					int i = 0;
					Node aux = this.root;
					while (aux != null && i != index) {
						i+= 1;
						aux = aux.getNext();
					}
					livro = (Livro) aux.getObjeto();					
				}
			}
		}		
		return livro;
	}
}
