package biblioteca.dados.lista;

import biblioteca.dados.entidades.Pessoa;

public class ListaPessoa extends Lista {
	public ListaPessoa() {
		super();
	}
	
	public Pessoa get(int index){
		Pessoa pessoa = null;
		
		if (!isEmpty()) {
			if (index <= this.index) {
				if (index == 0) {
					
					pessoa = (Pessoa) this.root.getObjeto();
				} else {
					
					int i = 0;
					Node aux = this.root;
					while (aux != null && i != index) {
						i+= 1;
						aux = aux.getNext();
					}
					pessoa = (Pessoa) aux.getObjeto();					
				}
			}
		}		
		return pessoa;
	}

	
	/*Impress�o para verificar a estrutura	
	public String print() {
		Node aux = this.root;
		String retorno = "";
		int i = 0;
		while (aux != null) {			
			retorno += "["+ i+"] Nome: " + ((Pessoa) aux.getObjeto()).getNome() + "\n";
			i++;
			aux = aux.getNext();
		}

		return retorno;
	}
	*/
}
