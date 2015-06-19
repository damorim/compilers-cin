package biblioteca.dados.entidades;

import java.io.Serializable;

public class Endereco implements Serializable {
	private String rua;
	private String numero;
	private String complemento;
	private String cep;

	public Endereco(String rua, String numero, String complemento, String cep) {
		this.rua = rua;
		this.numero = numero;
		this.complemento = complemento;
		this.cep = cep;
	}
	
	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String toString() {
		String message = "";

		message += "ENDERE�O\n";
		message += formatSaida("Rua:") + this.rua + "\n";
		message += formatSaida("Numero:") + this.numero + "\n";
		message += formatSaida("Complemento:") + this.complemento + "\n";
		message += formatSaida("CEP:") + cepToString(this.cep);

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

	private static String cepToString(String cep) {
		String message = "";
		if (cep.length() == 8) {
			message = cep.substring(0, 5) + "-"
					+ cep.substring(5, cep.length());
		}

		return message;
	}

}
