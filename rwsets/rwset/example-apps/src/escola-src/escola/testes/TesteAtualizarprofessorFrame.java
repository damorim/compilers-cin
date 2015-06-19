package escola.testes;

import escola.classesBase.Professor;
import escola.gui.AtualizarProfessorFrame;

public class TesteAtualizarprofessorFrame {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EscolaTeste escola = new EscolaTeste();
		Professor untonio = escola.getProfessor();
		
AtualizarProfessorFrame f = new AtualizarProfessorFrame(untonio);
f.setVisible(true);
	}

}
