package biblioteca.dados.entidades;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Calendar;

import biblioteca.dados.entidades.aluno.Emprestimo;
import biblioteca.dados.lista.ListaEmprestimo;
import biblioteca.dados.lista.Node;
import biblioteca.negocios.exceptions.ItemNaoEncontradoException;
import biblioteca.negocios.exceptions.pessoa.MultaGeradaException;

public class Aluno extends Pessoa implements Serializable {

	private int totalEmprestimos;
	
	
	public int getTotalEmprestimos() {
		return totalEmprestimos;
	}

	public void setTotalEmprestimos(int totalEmprestimos) {
		this.totalEmprestimos = totalEmprestimos;
	}

	public ListaEmprestimo getEmprestimos() {
		return emprestimos;
	}

	public void setEmprestimos(ListaEmprestimo emprestimos) {
		this.emprestimos = emprestimos;
	}

	public int getListaIndexCache() {
		return listaIndexCache;
	}

	public void setListaIndexCache(int listaIndexCache) {
		this.listaIndexCache = listaIndexCache;
	}

	private ListaEmprestimo emprestimos;
	private int listaIndexCache; // cache da lista

	public Aluno(String nome, String cpf, Endereco endereco, String telefone,
			String senha) {
		super(nome, cpf, endereco, telefone, senha);
		totalEmprestimos = 0;
		emprestimos = new ListaEmprestimo();
	}

	// --------------------------------------------
	public void novoEmprestimo(String codigo, String tipo,
			String dataDevolucao, String dataEmprestimo) {
		Emprestimo emprestimo = new Emprestimo(codigo, tipo, dataDevolucao,
				dataEmprestimo);
		Node no = new Node(emprestimo);
		emprestimos.insert(no);
		totalEmprestimos++;
	}

	public void novaDevolucao(String codigo, String dataDevolucao)
			throws MultaGeradaException, ItemNaoEncontradoException {
		listaIndexCache = 0;// variavel vai servir como cache do sistema

		listaIndexCache = emprestimos.getIndice(codigo);
		if (listaIndexCache == -1) {
			throw new ItemNaoEncontradoException(codigo);
		} else {
			Emprestimo aux = emprestimos.get(listaIndexCache);
			double multa = geraMulta(aux.getDataDevolucao());

			if (multa > 0) {
				NumberFormat nf = NumberFormat.getCurrencyInstance();
				throw new MultaGeradaException(nf.format(multa));
			} else {
				emprestimos.remove(listaIndexCache);
			}

		}
	}

	public void confirmarDevolucao() {
		emprestimos.remove(listaIndexCache);
		listaIndexCache = 0;
	}

	// --------------------------------------------

	public boolean hasEmprestimo() {
		boolean resposta = false;

		if (!emprestimos.isEmpty()) {
			resposta = true;
		}

		return resposta;
	}

	public int totalLivrosEmprestimo() {
		// retorna a quantidade de livros que o aluno est� portanto.
		int total = emprestimos.size();
		return total;

	}

	private static double geraMulta(String dataDevolucao) {
		double valor = 0.0;
		Calendar dev = Calendar.getInstance();
		String[] aux = dataDevolucao.split("-");

		dev.set(Integer.parseInt(aux[0]), (Integer.parseInt(aux[1]) - 1),
				Integer.parseInt(aux[2]));
		int dif = calculaDiferencaDatas(dev);

		if (dif < 0) {
			valor = (dif * 0.75) * (-1);
		}
		return valor;
	}

	private static int calculaDiferencaDatas(Calendar dev) {
		Calendar atual = Calendar.getInstance();
		long ini = atual.getTimeInMillis();
		long fim = dev.getTimeInMillis();
		long dif = fim - ini;

		long difDays = dif / (24 * 60 * 60 * 1000);

		int days = (int) difDays;
		return days;
	}

	// --------------------------------------------

	public String toString() {
		String message = "";
		message += formatSaida("Tipo:") + "ALUNO\n";
		message += formatSaida("Nome:") + super.getNome() + "\n";
		message += formatSaida("CPF:") + cpfToString(super.getCpf()) + "\n";
		message += super.getEndereco().toString() + "\n";
		message += formatSaida("Telefone:") + super.getTelefone();
		message += emprestimos.print();
		return message;
	}

	private static String formatSaida(String info) {
		String aux = info;
		int dif = 12 - info.length();

		if (dif >= 0) {
			for (int i = 0; i <= dif; i++) {
				aux += " ";
			}
		} else {
			aux = aux.substring(0, 11) + ": ";
		}

		return aux;
	}

	private static String cpfToString(String cpf) {

		String message = cpf;
		if (cpf.length() >= 11 && cpf.length() <= 11) {
			message = (cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "."
					+ cpf.substring(6, 9) + "-" + cpf.substring(9, 11));
		}
		return message;
	}

	

}
