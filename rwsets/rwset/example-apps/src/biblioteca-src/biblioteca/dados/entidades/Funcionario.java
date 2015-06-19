package biblioteca.dados.entidades;

import java.io.Serializable;

public class Funcionario extends Pessoa implements Serializable {

	public Funcionario(String nome, String cpf, Endereco endereco,
			String telefone, String senha) {
		super(nome, cpf, endereco, telefone, senha);
	}

	public Funcionario(String nome, String cpf, String senha) {
		super(nome, cpf, senha);
	}

	public String toString() {
		String message = "";
		message += formatSaida("Tipo:") + "FUNCIONARIO\n";
		message += formatSaida("Nome:") + super.getNome() + "\n";
		message += formatSaida("CPF:") + cpfToString(super.getCpf()) + "\n";
		message += super.getEndereco().toString() + "\n";
		message += formatSaida("Telefone:") + super.getTelefone();
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
