package biblioteca.dados.lista;

import biblioteca.dados.entidades.aluno.Emprestimo;

public class ListaEmprestimo extends Lista {

	public ListaEmprestimo() {
		super();
	}

	public Emprestimo get(int index) {
		Emprestimo emprestimo = null;

		if (!isEmpty()) {
			if (index <= this.index) {
				if (index == 0) {
					// remover o root
					emprestimo = (Emprestimo) this.root.getObjeto();
				} else {
					// remover qualquer posi��o
					int i = 0;
					Node aux = this.root;
					while (aux != null && i != index) {
						i += 1;
						aux = aux.getNext();
					}
					emprestimo = (Emprestimo) aux.getObjeto();
				}
			}
		}
		return emprestimo;
	}

	public int getIndice(String codigo) {
		int indice = -1;

		if (!isEmpty()) {
			Node aux = this.root;
			int i = 0;

			while (aux != null
					&& !((Emprestimo) aux.getObjeto()).getCodigo().equals(
							codigo)) {
				i++;
				aux = aux.getNext();
			}

			if (i <= this.index) {
				indice = i;
			}
		}

		return indice;
	}

	public int getIndiceCpf(String cpf) {
		int indice = -1;

		if (!isEmpty()) {
			Node aux = this.root;
			int i = 0;

			while (aux != null
					&& !((Emprestimo) aux.getObjeto()).getCpfResponsavel()
							.equals(cpf)) {
				i++;
				aux = aux.getNext();
			}

			if (i <= this.index) {
				indice = i;
			}
		}

		return indice;
	}

	public String print() {
		Node aux = this.root;
		String retorno = "";
		if (!isEmpty()) {
			retorno = "\nEMPRESTIMOS\n";
			int i = 0;
			while (aux != null) {
				retorno += "[" + (i + 1) + "]\n";
				retorno += ((Emprestimo) aux.getObjeto()).toString() + "\n";
				i++;
				aux = aux.getNext();
				if (aux != null) {
					retorno += "\n";
				}
			}
		}
		return retorno;
	}

	public String printLivro() {
		Node aux = this.root;
		String retorno = "LIVROS EMPRESTADOS\n";
		int i = 0;
		while (aux != null) {
			retorno += "Exemplar: " + (i + 1) + "\n";
			retorno += ((Emprestimo) aux.getObjeto()).toString() + "\n";
			i++;
			aux = aux.getNext();
		}

		return retorno;
	}

	
	
	public String listaNaplanilha(){
	Node aux = this.root;
	String retorno = "";
	int i = 0;
	while (aux != null) {
		retorno += ((Emprestimo) aux.getObjeto()).colocarPlanilha() + ";";
		i++;
		aux = aux.getNext();
	}

	return retorno;
}
}
