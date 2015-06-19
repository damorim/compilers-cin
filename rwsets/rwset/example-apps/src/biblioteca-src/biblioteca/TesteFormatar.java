package biblioteca;
import biblioteca.dados.entidades.Aluno;
import biblioteca.dados.entidades.Endereco;

public class TesteFormatar {

	public static void main(String[] args) {
		Endereco end = new Endereco("RUA JOSE MARIANO", "372", "", "53140230");
		Aluno aluno = new Aluno("CAIO FELIPE PEREIRA CALADO", "09511766414",
				end, "34314814", "caio123");

		String a = "Total Emprestimo(s):";
		System.out.println("--> " + a);
		System.out.println(a.length());
		String b = "Tipo:";

		System.out.println("\n\n--> " + b + " a");
		System.out.println(b.length());
		System.out.println(12 - b.length());

		/*
		 * int dif = 12 - b.length();
		 * 
		 * for (int i = 0; i <= dif; i++) { b += "."; }
		 */

		String c = "Tipo:";
		String d = "Nome:";
		String e = "CPF:";
		String f = "Telefone:";
		String g = "Complemento123:";
		System.out.println(formatSaida(c));
		System.out.println(formatSaida(d));
		System.out.println(formatSaida(e));
		System.out.println(formatSaida(f));
		System.out.println(formatSaida(g));

	}

	private static String formatSaida(String info) {
		String aux = info;
		int dif = 12 - info.length();

		if (dif >= 0) {
			for (int i = 0; i <= dif; i++) {
				aux += ".";
			}
		} else {
			aux = aux.substring(0, 11) + ":.";
		}

		return aux;
	}
}
