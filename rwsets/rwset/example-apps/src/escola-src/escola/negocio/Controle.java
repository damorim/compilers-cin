package escola.negocio;

import escola.classesBase.Turma;
import escola.excecoes.EntradaInvalidaException;

public class Controle {

	public Controle() {

	}

	public static boolean controlePessoa(String cpf, String nome,
			String dataNasc, String rg, String sexo, String telefone,
			String rua, String numero, String bairro, String cep,
			String cidade, String estado, String pais)
			throws EntradaInvalidaException {
		boolean pode = true;
		String ondeErrou = "";
		boolean preencheu = true;
		boolean entradaInvalida = false;
		boolean errouData = false;
		if (cpf == null || cpf == "") {
			preencheu = false;
			ondeErrou = "Por favor, preencha os campos:";
			ondeErrou = ondeErrou + "\nCPF";
			pode = false;
		}
		if (nome == null || nome == "") {
			if (preencheu = false) {
				ondeErrou = "Por favor, preencha os campos:";
			}
			ondeErrou = ondeErrou + "\nNome";
			preencheu = false;
			pode = false;
		}
		if (rg == null || rg == "") {
			if (preencheu = false) {
				ondeErrou = "Por favor, preencha os campos:";
			}
			ondeErrou = ondeErrou + "\nRG";
			preencheu = false;
			pode = false;
		}
		if (cpf.length() < 11) {
			entradaInvalida = true;
			ondeErrou = ondeErrou + "\n" + "O que ainda esta errado:";
			ondeErrou = ondeErrou
					+ "\nO CPF nao contem caracteres suficiente, o minimo sao 11";
			pode = false;
		}
		if (rg.length() < 4) {
			if (entradaInvalida = false) {
				ondeErrou = ondeErrou + "\n" + "O que ainda esta errado:";
			}
			ondeErrou = ondeErrou
					+ "\nO RG nao contem caracteres suficiente, o minimo sao 4";
			entradaInvalida = true;
			pode = false;
		}
		if (nome.length() < 5) {
			if (entradaInvalida = false) {
				ondeErrou = ondeErrou + "\n" + "O que ainda esta errado:";
			}
			ondeErrou = ondeErrou
					+ "\nO Nome nao contem caracteres suficiente, o minimo sao 5";
			entradaInvalida = true;
			pode = false;
			pode = false;
		}
		if (dataNasc.length() != 10) {
			if (entradaInvalida = false) {
				ondeErrou = ondeErrou + "\n" + "O que ainda esta errado:";
			}
			ondeErrou = ondeErrou + "\nDigite a data no formato dd/mm/aaaa";
			entradaInvalida = true;
			pode = false;
			errouData = true;
		}else{
		if (dataNasc.charAt(2) != '/' || dataNasc.charAt(5) != '/') {
			if (entradaInvalida = false) {
				ondeErrou = ondeErrou + "\n" + "O que ainda esta errado:";
			}
			if (errouData = false) {
				ondeErrou = ondeErrou + "\nDigite a data no formato dd/mm/aaaa";
			}
			entradaInvalida = true;
			pode = false;
		}
		if ((Integer.parseInt(dataNasc.substring(0, 2))) > 31) {
			if (entradaInvalida = false) {
				ondeErrou = ondeErrou + "\n" + "O que ainda esta errado:";
			}
			ondeErrou = ondeErrou + "\nO dia selecionado nao existe";
			entradaInvalida = true;
			pode = false;
		}
		if ((Integer.parseInt(dataNasc.substring(3, 5))) > 12) {
			if (entradaInvalida = false) {
				ondeErrou = ondeErrou + "\n" + "O que ainda esta errado:";
			}
			ondeErrou = ondeErrou + "\nO mes selecionado nao existe";
			entradaInvalida = true;
			pode = false;
		}
		}
		if (telefone.length() < 8) {
			if (entradaInvalida = false) {
				ondeErrou = ondeErrou + "\n" + "O que ainda esta errado:";
			}
			ondeErrou = ondeErrou
					+ "\nO telefone nao contem caracteres suficiente, o minimo e 8";
			entradaInvalida = true;
			pode = false;
		}
		if (!pode) {
			throw new EntradaInvalidaException(ondeErrou);
		}
		return pode;
	}

	
	public static boolean cpfValido(String cpf) throws EntradaInvalidaException {
		boolean pode = true;
		double cpf2;

		try {
			cpf2 = Double.parseDouble(cpf);
		} catch (NumberFormatException e) {
			pode = false;
			cpf2 = 0.0;
		}

		if (cpf.length() < 11) {
			pode = false;
		}
		if (cpf2 == 0.0) {
			pode = false;
		}
		if (!pode) {
			String ondeErrou = "O CPF deve te no minimo 11 digitos";
			throw new EntradaInvalidaException(ondeErrou);
		}
		return pode;
	}

	public static boolean nomeValido(String nome)
			throws EntradaInvalidaException {
		boolean pode = true;
		if (nome.length() < 3 || nome == null) {
			pode = false;
		}
		if (!pode) {
			String ondeErrou = "O Nome deve te no minimo 3 caracteres";
			throw new EntradaInvalidaException(ondeErrou);
		}
		return pode;

	}

}
