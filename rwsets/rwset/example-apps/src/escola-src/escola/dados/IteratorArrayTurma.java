package escola.dados;

import java.util.Iterator;
import java.util.NoSuchElementException;

import escola.classesBase.Pessoa;
import escola.classesBase.Turma;

public class IteratorArrayTurma implements Iterator<Turma> {
	Turma[] array;
	int indiceAtual;
	long contador = 0;

	public IteratorArrayTurma(Turma[] array) {
		this.array = array;
		this.indiceAtual = -1;
		for (int i = 0; this.array[i] != null; i++) {
			this.contador = i+1;
		}

	}

	public boolean hasNext() {
		if(this.contador ==0){
			return false;
		}else{
			return this.indiceAtual < this.contador-1;	
		}
		
		
	}
	public Turma next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		return array[++indiceAtual];

	}

	public void remove() {
		throw new UnsupportedOperationException(
				"Esta operacao nao suportada.");
	}

	public int getIndice() {
		return indiceAtual;
	}
}
