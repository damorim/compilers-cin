package biblioteca.dados.lista;

import biblioteca.dados.entidades.Sala;

public class ListaSala extends Lista {

	public ListaSala() {
		super();
	}

	public Sala get(int index) {
		Sala sala = null;

		if (!isEmpty()) {
			if (index <= this.index) {
				if (index == 0) {
					// remover o root
					sala = (Sala) this.root.getObjeto();
				} else {
					// remover qualquer posicao
					int i = 0;
					Node aux = this.root;
					while (aux != null && i != index) {
						i += 1;
						aux = aux.getNext();
					}
					sala = (Sala) aux.getObjeto();
				}
			}
		}
		return sala;

	}

}
