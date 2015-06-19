package biblioteca.dados.entidades;

import java.io.Serializable;

import biblioteca.dados.entidades.aluno.Emprestimo;
import biblioteca.dados.lista.ListaEmprestimo;
import biblioteca.dados.lista.Node;
import biblioteca.negocios.exceptions.livro.LivroIndisponivelException;

public class Livro implements Serializable {
	private String codigo;
	private String titulo;
	private String autor;
	private String assunto;
	private String sinopse;
	private int qtdDisponivel;
	private int totalEmprestimo;
	public ListaEmprestimo getEmprestimos() {
		return emprestimos;
	}

	public void setEmprestimos(ListaEmprestimo emprestimos) {
		this.emprestimos = emprestimos;
	}

	private int totalAcervo;

	private ListaEmprestimo emprestimos;

	// lista exemplar;
	// Hist�rico do livro

	public Livro(String codigo, String titulo, String autor, String assunto,
			String sinopse) {
		this.codigo = codigo;
		this.titulo = titulo;
		this.autor = autor;
		this.assunto = assunto;
		this.sinopse = sinopse;
		this.qtdDisponivel = 0;
		this.totalEmprestimo = 0;
		this.totalAcervo = 0;
		this.emprestimos = new ListaEmprestimo();
	}

	public String getSinopse() {
		return sinopse;
	}

	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public int getQtdDisponivel() {
		return qtdDisponivel;
	}

	public void setQtdDisponivel(int qtdDisponivel) {
		this.qtdDisponivel = qtdDisponivel;
	}

	public int getTotalEmprestimo() {
		return totalEmprestimo;
	}

	public void setTotalEmprestimo(int totalEmprestimo) {
		this.totalEmprestimo = totalEmprestimo;
	}

	public int getTotalAcervo() {
		return totalAcervo;
	}

	public void setTotalAcervo(int totalAcervo) {
		this.totalAcervo = totalAcervo;
	}

	public String toString() {
		String message = "";

		message += "LIVRO\n";
		message += formatSaida("Codigo:") + this.codigo + "\n";
		message += formatSaida("T�tulo:") + this.titulo + "\n";
		message += formatSaida("Autor:") + this.autor + "\n";
		message += formatSaida("Assunto:") + this.assunto + "\n";
		message += formatSaida("Sinopse:") + this.sinopse + "\n";
		message += formatSaida("Total Acervo:") + this.totalAcervo + "\n";
		message += formatSaida("Total Emprestimo(s):") + this.totalEmprestimo+"\n";
		if (!emprestimos.isEmpty()) {
			message += emprestimos.printLivro();
		}
		return message;
	}

	private static String formatSaida(String info) {
		String aux = info;
		int dif = 20 - info.length();

		if (dif >= 0) {
			for (int i = 0; i <= dif; i++) {
				aux += " ";
			}
		} else {
			aux = aux.substring(0, 19) + ": ";
		}

		return aux;
	}

	// -------------------------

	public void emprestar(String dataDevolucao, String dataEmprestimo,
			String cpfResponsavel) throws LivroIndisponivelException {
		if (qtdDisponivel > 0) {
			Emprestimo aux = new Emprestimo(this.codigo, dataDevolucao,
					dataEmprestimo);
			aux.setCpfResponsavel(cpfResponsavel);
			Node no = new Node(aux);
			emprestimos.insert(no);

			qtdDisponivel--;
			totalEmprestimo++;
		} else {
			throw new LivroIndisponivelException(this.codigo);
		}
	}

	public void devolver(String cpf) {
		int indice = emprestimos.getIndiceCpf(cpf);
		emprestimos.remove(indice);
		qtdDisponivel++;
	}

	public void inserirLivroAcervo() {
		totalAcervo++;
		qtdDisponivel++;
	}

	public void retirarLivroAcervo() {
		if (totalAcervo > 0) {
			totalAcervo--;
		}
	}

	public boolean hasEmprestimo() {
		boolean resposta = false;
		if (!emprestimos.isEmpty()) {
			resposta = true;
		}
		return resposta;
	}
}
