package escola.dados;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteratorArray<T> implements Iterator<T> {
	T[] array;
	int indiceAtual;

	public IteratorArray(T[] array) {
		this.array = array;
		indiceAtual = 0;
	}

	public boolean hasNext() {
		return indiceAtual < array.length;
	}

	public T next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		return array[indiceAtual++];

	}

	public void remove() {
		throw new UnsupportedOperationException(
				"Esta operacao nao suportada.");
	}

	public int getIndice() {
		return indiceAtual;
	}
}
