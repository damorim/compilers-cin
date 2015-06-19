package escola.classesBase;


public class Disciplina {

	private String ementa;
	private String nome;

	public Disciplina(String nome, String ementa){
		this.nome = nome;
		this.ementa = ementa;
	}

	public String getEmenta() {
		return ementa;
	}

	public void setEmenta(String ementa) {
		this.ementa = ementa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String toString(){
		return this.nome;
	}
	
	public String resumo(){
		String resumo = "Nome: "+this.nome+"\nEmenta: "+this.ementa;
		return resumo;
	}

}
