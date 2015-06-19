package biblioteca.dados.lista;

public class Node {

	private Node next;
	private Object objeto;

	public Node(Object objeto) {
		this.objeto = objeto;
	}
	
	public void setObjeto(Object objeto){
		this.objeto = objeto;
	}

	public Object getObjeto() {
		return this.objeto;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public Node getNext() {
		return this.next;
	}
}
