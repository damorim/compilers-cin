package escola.dados;

import java.util.Iterator;
import java.util.NoSuchElementException;

import escola.classesBase.Pessoa;

public class IteratorArrayPessoa implements Iterator<Pessoa> {
	Pessoa[] array;
	int indiceAtual;
	long contador = 0;

	public IteratorArrayPessoa(Pessoa[] array) {
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

	public Pessoa next() {
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
