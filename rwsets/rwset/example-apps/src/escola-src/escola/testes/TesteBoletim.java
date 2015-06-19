package escola.testes;

import escola.classesBase.*;
import escola.dados.RepositorioArrayDisciplina;

public class TesteBoletim {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		RepositorioArrayDisciplina disc = new RepositorioArrayDisciplina(3);
		Disciplina portugues = new Disciplina("portgues", "oi");
		Disciplina mat = new Disciplina("matematica", "oi");
		Disciplina geo = new Disciplina("geografia", "oi");
		
		disc.inserir(portugues);
		disc.inserir(mat);
		disc.inserir(geo);
		
		Boletim boletim = new Boletim();
		System.out.println(boletim.imprimir());
		
		

	}

}
