package biblioteca.dados.relatorio;

import biblioteca.dados.entidades.Sala;
import biblioteca.dados.lista.Node;

public class MergeSortSala {

	public Node[] ordenar(Node[] no, int tipo) {
		switch (tipo) {
		case 1: {
			// c�digo
			mergeSortCodigo(no, 0, (no.length - 1));
			break;
		}
		case 2: {
			// total emprestimo
			mergeSortEmprestimo(no, 0, (no.length - 1));
			break;
		}
		case 3: {
			// capacidade
			mergeSortCapacidade(no, 0, (no.length - 1));
			break;
		}
		}
		return no;
	}

	// ------------------------------------------------------------------

	private static Node[] mergeSortCodigo(Node[] data, int ini, int fim) {

		if ((fim - ini) >= 1) {
			int middle1 = (fim + ini) / 2;
			int middle2 = middle1 + 1;

			data = mergeSortCodigo(data, ini, middle1);
			data = mergeSortCodigo(data, middle2, fim);

			data = mergeCodigo(data, ini, middle1, middle2, fim);
		}

		return data;
	}

	private static Node[] mergeCodigo(Node[] data, int left, int middle1,
			int middle2, int right) {
		int leftIndex = left;
		int rightIndex = middle2;
		int combinedIndex = left;

		Node[] combined = new Node[data.length];
		// realizando o merge
		while (leftIndex <= middle1 && rightIndex <= right) {
			if (((Sala) data[leftIndex].getObjeto()).getCodigo().compareTo(
					((Sala) data[left].getObjeto()).getCodigo()) >= 0) {
				// Caso seja maior ou igual..
				combined[combinedIndex++] = data[rightIndex++];
			} else {
				combined[combinedIndex++] = data[leftIndex++];
			}
		}

		if (leftIndex == middle2) {
			// Caso tenha elementos no merge a esquerda, inserir no array
			// combinado
			while (rightIndex <= right) {
				combined[combinedIndex++] = data[rightIndex++];
			}
		} else {
			while (leftIndex <= middle1) {
				combined[combinedIndex++] = data[leftIndex++];
			}
		}

		for (int i = left; i <= right; i++) {
			data[i] = combined[i];
			System.out.println(((Sala) data[i].getObjeto()).toString());
		}
		return data;
	}

	// ------------------------------------------------------------------

	private static void mergeSortEmprestimo(Node[] data, int ini, int fim) {
		if ((fim - ini) >= 1) {
			int middle1 = (fim + ini) / 2;
			int middle2 = middle1 + 1;

			mergeSortEmprestimo(data, ini, middle1);
			mergeSortEmprestimo(data, middle2, fim);

			mergeEmprestimo(data, ini, middle1, middle2, fim);
		}
	}

	private static void mergeEmprestimo(Node[] data, int left, int middle1,
			int middle2, int right) {

		int leftIndex = left;
		int rightIndex = middle2;
		int combinedIndex = left;

		Node[] combined = new Node[data.length];
		// realizando o merge
		while (leftIndex <= middle1 && rightIndex <= right) {

			if (((Sala) data[leftIndex].getObjeto()).getTotalEmprestimos() >= ((Sala) data[rightIndex]
					.getObjeto()).getTotalEmprestimos()) {
				// Caso seja maior ou igual..
				combined[combinedIndex++] = data[rightIndex++];
			} else {
				combined[combinedIndex++] = data[leftIndex++];
			}
		}

		if (leftIndex == middle2) {
			// Caso tenha elementos no merge a esquerda, inserir no array
			// combinado
			while (rightIndex <= right) {
				combined[combinedIndex++] = data[rightIndex++];
			}
		} else {
			while (leftIndex <= middle1) {
				combined[combinedIndex++] = data[leftIndex++];
			}
		}

		for (int i = left; i <= right; i++) {
			data[i] = combined[i];
		}

	}

	// ------------------------------------------------------------------

	private static void mergeSortCapacidade(Node[] data, int ini, int fim) {
		if ((fim - ini) >= 1) {
			int middle1 = (fim + ini) / 2;
			int middle2 = middle1 + 1;

			mergeSortCapacidade(data, ini, middle1);
			mergeSortCapacidade(data, middle2, fim);

			mergeCapacidade(data, ini, middle1, middle2, fim);
		}
	}

	private static void mergeCapacidade(Node[] data, int left, int middle1,
			int middle2, int right) {

		int leftIndex = left;
		int rightIndex = middle2;
		int combinedIndex = left;

		Node[] combined = new Node[data.length];
		// realizando o merge
		while (leftIndex <= middle1 && rightIndex <= right) {

			if (((Sala) data[leftIndex].getObjeto()).getCapacidade() >= ((Sala) data[rightIndex]
					.getObjeto()).getCapacidade()) {
				// Caso seja maior ou igual..
				combined[combinedIndex++] = data[rightIndex++];
			} else {
				combined[combinedIndex++] = data[leftIndex++];
			}
		}

		if (leftIndex == middle2) {
			// Caso tenha elementos no merge a esquerda, inserir no array
			// combinado
			while (rightIndex <= right) {
				combined[combinedIndex++] = data[rightIndex++];
			}
		} else {
			while (leftIndex <= middle1) {
				combined[combinedIndex++] = data[leftIndex++];
			}
		}

		for (int i = left; i <= right; i++) {
			data[i] = combined[i];
		}

	}

}
