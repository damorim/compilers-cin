package escola.classesBase;

import escola.dados.RepositorioArrayDisciplina;
import escola.excecoes.ElementoNaoEncontradoException;
import escola.excecoes.NumeroMaximoException;


public class Boletim {
	private RepositorioArrayDisciplina disciplinas;
	private double[][] notas;

	public Boletim() {
		notas = new double[15][4];
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 4; j++) {
				notas[i][j] = 0.0;
			}
		}
	}

	public RepositorioArrayDisciplina getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(RepositorioArrayDisciplina disciplinas) {
		this.disciplinas = disciplinas;
	}

	public double[][] getNotas() {
		return notas;
	}

	public void setNotas(double[][] notas) {
		this.notas = notas;
	}

	public String imprimir() {
		String retorno = "";
		double sum = 0;
		for (int i = 0; i < this.disciplinas.getCont(); i++) {
			retorno+=this.disciplinas.getArray()[i].getNome();
			for (int j = 0; j < 4; j++) {
				retorno += " " + notas[i][j] + " ";
				sum += notas[i][j];
			}
			retorno += "Media = " + (sum / 4) + "\n";
		}
		return retorno;
	}
	
	
	public void inserirDisciplina(Disciplina d)throws NumeroMaximoException{
		if(this.disciplinas.getCont()==15){
			throw new NumeroMaximoException("disciplinas", 15);
		}
		this.disciplinas.inserir(d);
	}
	
	public void removerDisciplina(Disciplina d) throws ElementoNaoEncontradoException{
		try{
			this.disciplinas.remover(d.getNome());
		}catch(ElementoNaoEncontradoException e){
			throw e;			
		}
		
	}

}
