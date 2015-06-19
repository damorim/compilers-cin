package escola.dados;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.util.Iterator;

//import modificacoes.RepositorioArrayPessoa2;



import org.apache.poi.hssf.usermodel.*;

import escola.classesBase.*;
import escola.excecoes.ElementoNaoEncontradoException;
import escola.excecoes.RepositorioException;

public class RepositorioArquivoPessoa implements Repositorio<Pessoa> {

	private RepositorioArrayPessoa pessoas;
	HSSFWorkbook wb;
	HSSFSheet sheet1;
	int cont = 0;
	FileOutputStream stream;
	FileInputStream file;
	boolean abrir = false;

	// boolean primeiravez = true;

	public int getCont() {
		return this.cont;
	}

	public RepositorioArquivoPessoa() {

		try {
			file = new FileInputStream(new File("planilha.xls"));
			this.wb = new HSSFWorkbook(file);
			sheet1 = wb.getSheet("Pessoas");
			stream = new FileOutputStream("planilha.xls");
			stream.flush();
			stream.close();
		} catch (FileNotFoundException e1) {
			//System.out.println("n achou");
			wb = new HSSFWorkbook();
			this.sheet1 = wb.createSheet("Pessoas");
			wb.createSheet("Turmas");
			wb.createSheet("Disciplinas");
			try {
				stream = new FileOutputStream("planilha.xls");
				wb.write(stream);
				stream.flush();
				stream.close();
			} catch (FileNotFoundException e) {
				System.out.println("erro dentro");
			} catch (IOException e) {
				System.out.println("erro dentro");
			}
			// abrir = true;
		} catch (IOException e) {
			//System.out.println("n achou");
			wb = new HSSFWorkbook();
			wb.createSheet("Turmas");
			wb.createSheet("Disciplinas");
			this.sheet1 = wb.createSheet("Pessoas");
			try {
				stream = new FileOutputStream("planilha.xls");
				wb.write(stream);
				stream.flush();
				stream.close();
			} catch (FileNotFoundException g) {
				System.out.println("erro dentro");
			} catch (IOException f) {
				System.out.println("erro dentro");
			}
			// abrir = true;
		}

		pessoas = new RepositorioArrayPessoa();
		this.lerPlanilha();

	}

	public void lerPlanilha() {

		Pessoa pessoa = null;
		String telefone = "", nomeTurma, cpf, nome, rg, sexo, pai, mae, rua, numero, bairro, cep, cidade, estado, pais, numeroMatricula, funcao;
		double salario = 0.0;
		double tipoPessoa = 0.0;
		int i = 0;
		boolean acabou = false;

		while (!acabou) {
			HSSFRow row = null;

			try {
				row = this.sheet1.getRow(i);
			} catch (NullPointerException e) {
				System.out.println("null pointer oi");
			}

			HSSFCell cell = null;
			boolean pulou = false;
			try {
				cell = row.getCell((short) 0);
			} catch (NullPointerException e) {
				acabou = true;
				//System.out.println("acabou");
			}
			if (cell != null) {

				try {
					tipoPessoa = Integer.parseInt(lerCelula(i, 0));
				} catch (NumberFormatException e) {
					HSSFCell cell1 = row.getCell((short) 0);
					try {
						tipoPessoa = cell1.getNumericCellValue();
					} catch (NumberFormatException e1) {
						//System.out.print("achou um vazio");
						pulou = true;
					}
				}

				if (!pulou) {

					try {
						cpf = lerCelula(i, 1);
					} catch (NumberFormatException e) {
						HSSFCell cell2 = row.getCell((short) 1);
						cpf = "" + (int) cell2.getNumericCellValue();
					}

					nome = lerCelula(i, 2);
					String dataNasc = lerCelula(i, 3);
					try {
						rg = lerCelula(i, 4);
					} catch (NumberFormatException e) {
						HSSFCell cell4 = row.getCell((short) 4);
						rg = "" + (int) cell4.getNumericCellValue();
					}
					sexo = lerCelula(i, 5);
					rua = lerCelula(i, 6);
					numero = lerCelula(i, 7);
					bairro = lerCelula(i, 8);
					try {
						cep = lerCelula(i, 9);
					} catch (NumberFormatException e) {
						HSSFCell cell5 = row.getCell((short) 9);
						cep = "" + (int) cell5.getNumericCellValue();
					}
					cidade = lerCelula(i, 10);
					estado = lerCelula(i, 11);
					pais = lerCelula(i, 12);
					pai = lerCelula(i, 13);
					mae = lerCelula(i, 14);
					numeroMatricula = lerCelula(i, 15);
					nomeTurma = lerCelula(i, 16);
					funcao = lerCelula(i, 17);
					try {
						String sal = lerCelula(i, 18);
						if (!sal.equals("")) {
							salario = Double.parseDouble(lerCelula(i, 18));
						} else {
							salario = 0.0;
						}
					} catch (NumberFormatException e) {
						HSSFCell cell7 = row.getCell((short) 18);
						salario = cell7.getNumericCellValue();
					}
					try {
						String tel = lerCelula(i, 19);
						if (!tel.equals("")) {
							telefone = lerCelula(i, 19);
						}
					} catch (NumberFormatException e) {
						HSSFCell cell7 = row.getCell((short) 19);
						telefone = "" + cell7.getNumericCellValue();
					}

					Endereco end = new Endereco(rua, numero, bairro, cep,
							cidade, estado, pais);
					Turma turma = new Turma(nomeTurma);

					switch ((int) tipoPessoa) {
					case 1:
						pessoa = new Aluno(cpf, nome, dataNasc, rg, sexo,
								telefone, end, pai, mae, turma);
						((Aluno) pessoa).setNumeroMatricula(numeroMatricula);
						// System.out.println("criou um aluno");
						break;
					case 2:
						pessoa = new Administrador(cpf, nome, dataNasc, rg,
								sexo, telefone, end, funcao);
						((Administrador) pessoa).setSalario(salario);

						// System.out.println("criou um adm");
						break;
					case 3:
						pessoa = new Professor(cpf, nome, dataNasc, rg, sexo,
								telefone, end, funcao);
						((Professor) pessoa).setSalario(salario);
						// System.out.println("criou um prof");
						break;
					case 4:
						pessoa = new Funcionario(cpf, nome, dataNasc, rg, sexo,
								telefone, end, funcao);
						((Funcionario) pessoa).setSalario(salario);
					}
					pessoas.inserir(pessoa);
					i++;
					cont++;
				} else {
					i++;
				}
			} else {
				acabou = true;
			}
		}

	}

	public RepositorioArrayPessoa getPessoas() {
		return pessoas;
	}

	@SuppressWarnings("deprecation")
	public void gravarPessoa(Pessoa p) {
		/**
		 * try { file = new FileInputStream(new File("planilha.xls")); this.wb =
		 * new HSSFWorkbook(file); stream = new
		 * FileOutputStream("planilha.xls"); } catch (FileNotFoundException e1)
		 * { System.out.println("erro1"); } catch (IOException e) {
		 * System.out.println("erro2"); }
		 * 
		 * sheet1 = wb.getSheet("Pessoas");
		 */

		try {
			file = new FileInputStream(new File("planilha.xls"));
		} catch (FileNotFoundException e1) {
			// System.out.println("erro1");
		}

		try {
			this.wb = new HSSFWorkbook(file);
		} catch (IOException e1) {
			// System.out.println("erro2");
			// System.out.println(e1.getMessage());
		}

		try {
			stream = new FileOutputStream("planilha.xls");
		} catch (FileNotFoundException e1) {
			// System.out.println("erro3");
		}

		sheet1 = wb.getSheet("Pessoas");

		// System.out.println("entrou no gravar pessoa");

		if (p instanceof Aluno) {
			HSSFRow row = sheet1.createRow(cont);
			// System.out.println("entrou no gravar pessoa aluno >" + cont);

			row.createCell((short) 0).setCellValue("1");
			row.createCell((short) 1).setCellValue(p.getCpf());
			row.createCell((short) 2).setCellValue(p.getNome());
			row.createCell((short) 3).setCellValue(p.getDataNasc());
			row.createCell((short) 4).setCellValue(p.getIdentidade());
			row.createCell((short) 5).setCellValue(p.getSexo());
			row.createCell((short) 6).setCellValue(p.getEndereco().getRua());
			row.createCell((short) 7).setCellValue(p.getEndereco().getNumero());
			row.createCell((short) 8).setCellValue(p.getEndereco().getBairro());
			row.createCell((short) 9).setCellValue(p.getEndereco().getCep());
			row.createCell((short) 10)
					.setCellValue(p.getEndereco().getCidade());
			row.createCell((short) 11)
					.setCellValue(p.getEndereco().getEstado());
			row.createCell((short) 12).setCellValue(p.getEndereco().getPais());
			row.createCell((short) 13).setCellValue(((Aluno) p).getPai());
			row.createCell((short) 14).setCellValue(((Aluno) p).getMae());
			row.createCell((short) 15).setCellValue(
					((Aluno) p).getNumeroMatricula());
			row.createCell((short) 16).setCellValue(
					((Aluno) p).getTurma().getNome());
			row.createCell((short) 17).setCellValue("");
			row.createCell((short) 18).setCellValue("");
			row.createCell((short) 19).setCellValue(p.getTelefone());
		} else if (p instanceof Administrador) {
			HSSFRow row = sheet1.createRow(cont);
			// System.out.println("entrou no gravar pessoa admo >" + cont);
			row.createCell((short) 0).setCellValue("2");
			row.createCell((short) 1).setCellValue(p.getCpf());
			row.createCell((short) 2).setCellValue(p.getNome());
			row.createCell((short) 3).setCellValue(p.getDataNasc());
			row.createCell((short) 4).setCellValue(p.getIdentidade());
			row.createCell((short) 5).setCellValue(p.getSexo());
			row.createCell((short) 6).setCellValue(p.getEndereco().getRua());
			row.createCell((short) 7).setCellValue(p.getEndereco().getNumero());
			row.createCell((short) 8).setCellValue(p.getEndereco().getBairro());
			row.createCell((short) 9).setCellValue(p.getEndereco().getCep());
			row.createCell((short) 10)
					.setCellValue(p.getEndereco().getCidade());
			row.createCell((short) 11)
					.setCellValue(p.getEndereco().getEstado());
			row.createCell((short) 12).setCellValue(p.getEndereco().getPais());
			row.createCell((short) 13).setCellValue("");
			row.createCell((short) 14).setCellValue("");
			row.createCell((short) 15).setCellValue("");
			row.createCell((short) 16).setCellValue("");
			row.createCell((short) 17).setCellValue(
					((Administrador) p).getFuncao());
			row.createCell((short) 18).setCellValue(
					((Administrador) p).getSalario());
			row.createCell((short) 19).setCellValue(p.getTelefone());

		} else if (p instanceof Professor) {
			HSSFRow row2 = sheet1.createRow(cont);
			// System.out.println("entrou no gravar pessoa profo >" + cont);
			row2.createCell((short) 0).setCellValue("3");
			row2.createCell((short) 1).setCellValue(p.getCpf());
			row2.createCell((short) 2).setCellValue(p.getNome());
			row2.createCell((short) 3).setCellValue(p.getDataNasc());
			row2.createCell((short) 4).setCellValue(p.getIdentidade());
			row2.createCell((short) 5).setCellValue(p.getSexo());
			row2.createCell((short) 6).setCellValue(p.getEndereco().getRua());
			row2.createCell((short) 7)
					.setCellValue(p.getEndereco().getNumero());
			row2.createCell((short) 8)
					.setCellValue(p.getEndereco().getBairro());
			row2.createCell((short) 9).setCellValue(p.getEndereco().getCep());
			row2.createCell((short) 10).setCellValue(
					p.getEndereco().getCidade());
			row2.createCell((short) 11).setCellValue(
					p.getEndereco().getEstado());
			row2.createCell((short) 12).setCellValue(p.getEndereco().getPais());
			row2.createCell((short) 13).setCellValue("");
			row2.createCell((short) 14).setCellValue("");
			row2.createCell((short) 15).setCellValue("");
			row2.createCell((short) 16).setCellValue("");
			row2.createCell((short) 17).setCellValue(
					((Professor) p).getFuncao());
			row2.createCell((short) 18).setCellValue(
					((Professor) p).getSalario());
			row2.createCell((short) 19).setCellValue(p.getTelefone());
		} else if (p instanceof Funcionario) {
			HSSFRow row = sheet1.createRow(cont);
			// System.out.println("entrou no gravar pessoa funco >" + cont);
			row.createCell((short) 0).setCellValue("4");
			row.createCell((short) 1).setCellValue(p.getCpf());
			row.createCell((short) 2).setCellValue(p.getNome());
			row.createCell((short) 3).setCellValue(p.getDataNasc());
			row.createCell((short) 4).setCellValue(p.getIdentidade());
			row.createCell((short) 5).setCellValue(p.getSexo());
			row.createCell((short) 6).setCellValue(p.getEndereco().getRua());
			row.createCell((short) 7).setCellValue(p.getEndereco().getNumero());
			row.createCell((short) 8).setCellValue(p.getEndereco().getBairro());
			row.createCell((short) 9).setCellValue(p.getEndereco().getCep());
			row.createCell((short) 10)
					.setCellValue(p.getEndereco().getCidade());
			row.createCell((short) 11)
					.setCellValue(p.getEndereco().getEstado());
			row.createCell((short) 12).setCellValue(p.getEndereco().getPais());
			row.createCell((short) 13).setCellValue("");
			row.createCell((short) 14).setCellValue("");
			row.createCell((short) 15).setCellValue("");
			row.createCell((short) 16).setCellValue("");
			row.createCell((short) 17).setCellValue(
					((Funcionario) p).getFuncao());
			row.createCell((short) 18).setCellValue(
					((Funcionario) p).getSalario());
			row.createCell((short) 19).setCellValue(p.getTelefone());
		}
		try {
			wb.write(stream);
		} catch (IOException e) {
			System.out.println("erro no stream");
		}
		try {
			stream.flush();
		} catch (IOException e) {
			System.out.println("erro flush");
		}
		try {
			stream.close();
		} catch (IOException e) {
			System.out.println("erro close");
		}
	}

	public void inserir(Pessoa item) throws RepositorioException {
		pessoas.inserir(item);
		gravarPessoa(item);
		++cont;
	}

	@SuppressWarnings("deprecation")
	public String lerCelula(int linha, int coluna) {
		HSSFRow row = this.sheet1.getRow(linha);
		HSSFCell cell = row.getCell((short) coluna);
		return cell.getStringCellValue();

	}

	// @SuppressWarnings({ "deprecation", "unused" })
	public Pessoa procurar(String cpf) throws ElementoNaoEncontradoException {
		return pessoas.procurar(cpf);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void atualizar(Pessoa p) throws ElementoNaoEncontradoException,
			RepositorioException {
		pessoas.atualizar(p);
		try {
			file = new FileInputStream(new File("planilha.xls"));
		} catch (FileNotFoundException e1) {
			// System.out.println("erro1");
		}

		try {
			this.wb = new HSSFWorkbook(file);
		} catch (IOException e1) {
			// System.out.println("erro2");
			// System.out.println(e1.getMessage());
		}

		try {
			stream = new FileOutputStream("planilha.xls");
		} catch (FileNotFoundException e1) {
			// System.out.println("erro3");
		}

		sheet1 = wb.getSheet("Pessoas");
		int i = pessoas.procurarIndice(p.getCpf());
		HSSFRow row = sheet1.createRow(i);
		if (p instanceof Aluno) {

			row.createCell((short) 0).setCellValue("1");
			row.createCell((short) 1).setCellValue(p.getCpf());
			row.createCell((short) 2).setCellValue(p.getNome());
			row.createCell((short) 3).setCellValue(p.getDataNasc());
			row.createCell((short) 4).setCellValue(p.getIdentidade());
			row.createCell((short) 5).setCellValue(p.getSexo());
			row.createCell((short) 6).setCellValue(p.getEndereco().getRua());
			row.createCell((short) 7).setCellValue(p.getEndereco().getNumero());
			row.createCell((short) 8).setCellValue(p.getEndereco().getBairro());
			row.createCell((short) 9).setCellValue(p.getEndereco().getCep());
			row.createCell((short) 10)
					.setCellValue(p.getEndereco().getCidade());
			row.createCell((short) 11)
					.setCellValue(p.getEndereco().getEstado());
			row.createCell((short) 12).setCellValue(p.getEndereco().getPais());
			row.createCell((short) 13).setCellValue(((Aluno) p).getPai());
			row.createCell((short) 14).setCellValue(((Aluno) p).getMae());
			row.createCell((short) 15).setCellValue(
					((Aluno) p).getNumeroMatricula());
			row.createCell((short) 16).setCellValue(
					((Aluno) p).getTurma().getNome());
			row.createCell((short) 17).setCellValue("");
			row.createCell((short) 18).setCellValue("");
			row.createCell((short) 19).setCellValue(p.getTelefone());
		} else if (p instanceof Administrador) {

			row.createCell((short) 0).setCellValue("2");
			row.createCell((short) 1).setCellValue(p.getCpf());
			row.createCell((short) 2).setCellValue(p.getNome());
			row.createCell((short) 3).setCellValue(p.getDataNasc());
			row.createCell((short) 4).setCellValue(p.getIdentidade());
			row.createCell((short) 5).setCellValue(p.getSexo());
			row.createCell((short) 6).setCellValue(p.getEndereco().getRua());
			row.createCell((short) 7).setCellValue(p.getEndereco().getNumero());
			row.createCell((short) 8).setCellValue(p.getEndereco().getBairro());
			row.createCell((short) 9).setCellValue(p.getEndereco().getCep());
			row.createCell((short) 10)
					.setCellValue(p.getEndereco().getCidade());
			row.createCell((short) 11)
					.setCellValue(p.getEndereco().getEstado());
			row.createCell((short) 12).setCellValue(p.getEndereco().getPais());
			row.createCell((short) 13).setCellValue("");
			row.createCell((short) 14).setCellValue("");
			row.createCell((short) 15).setCellValue("");
			row.createCell((short) 16).setCellValue("");
			row.createCell((short) 17).setCellValue(
					((Administrador) p).getFuncao());
			row.createCell((short) 18).setCellValue(
					((Administrador) p).getSalario());
			row.createCell((short) 19).setCellValue(p.getTelefone());

		} else if (p instanceof Professor) {
			HSSFRow row2 = sheet1.createRow(i);
			// System.out.println("entrou no gravar pessoa profo >" + cont);
			row2.createCell((short) 0).setCellValue("3");
			row2.createCell((short) 1).setCellValue(p.getCpf());
			row2.createCell((short) 2).setCellValue(p.getNome());
			row2.createCell((short) 3).setCellValue(p.getDataNasc());
			row2.createCell((short) 4).setCellValue(p.getIdentidade());
			row2.createCell((short) 5).setCellValue(p.getSexo());
			row2.createCell((short) 6).setCellValue(p.getEndereco().getRua());
			row2.createCell((short) 7)
					.setCellValue(p.getEndereco().getNumero());
			row2.createCell((short) 8)
					.setCellValue(p.getEndereco().getBairro());
			row2.createCell((short) 9).setCellValue(p.getEndereco().getCep());
			row2.createCell((short) 10).setCellValue(
					p.getEndereco().getCidade());
			row2.createCell((short) 11).setCellValue(
					p.getEndereco().getEstado());
			row2.createCell((short) 12).setCellValue(p.getEndereco().getPais());
			row2.createCell((short) 13).setCellValue("");
			row2.createCell((short) 14).setCellValue("");
			row2.createCell((short) 15).setCellValue("");
			row2.createCell((short) 16).setCellValue("");
			row2.createCell((short) 17).setCellValue(
					((Professor) p).getFuncao());
			row2.createCell((short) 18).setCellValue(
					((Professor) p).getSalario());
			row2.createCell((short) 19).setCellValue(p.getTelefone());
		} else if (p instanceof Funcionario) {
			// HSSFRow row = sheet1.createRow(cont);
			// System.out.println("entrou no gravar pessoa funco >" + cont);
			row.createCell((short) 0).setCellValue("4");
			row.createCell((short) 1).setCellValue(p.getCpf());
			row.createCell((short) 2).setCellValue(p.getNome());
			row.createCell((short) 3).setCellValue(p.getDataNasc());
			row.createCell((short) 4).setCellValue(p.getIdentidade());
			row.createCell((short) 5).setCellValue(p.getSexo());
			row.createCell((short) 6).setCellValue(p.getEndereco().getRua());
			row.createCell((short) 7).setCellValue(p.getEndereco().getNumero());
			row.createCell((short) 8).setCellValue(p.getEndereco().getBairro());
			row.createCell((short) 9).setCellValue(p.getEndereco().getCep());
			row.createCell((short) 10)
					.setCellValue(p.getEndereco().getCidade());
			row.createCell((short) 11)
					.setCellValue(p.getEndereco().getEstado());
			row.createCell((short) 12).setCellValue(p.getEndereco().getPais());
			row.createCell((short) 13).setCellValue("");
			row.createCell((short) 14).setCellValue("");
			row.createCell((short) 15).setCellValue("");
			row.createCell((short) 16).setCellValue("");
			row.createCell((short) 17).setCellValue(
					((Funcionario) p).getFuncao());
			row.createCell((short) 18).setCellValue(
					((Funcionario) p).getSalario());
			row.createCell((short) 19).setCellValue(p.getTelefone());
		}
		try {
			wb.write(stream);
		} catch (IOException e) {
			System.out.println("erro no stream");
		}
		try {
			stream.flush();
		} catch (IOException e) {
			System.out.println("erro flush");
		}
		try {
			stream.close();
		} catch (IOException e) {
			System.out.println("erro close");
		}

	}

	@SuppressWarnings("deprecation")
	@Override
	public void remover(String cpf) throws ElementoNaoEncontradoException,
			RepositorioException {

		try {
			file = new FileInputStream(new File("planilha.xls"));
		} catch (FileNotFoundException e1) {
			// System.out.println("erro1");
		}

		try {
			this.wb = new HSSFWorkbook(file);
		} catch (IOException e1) {
			// System.out.println("erro2");
			// System.out.println(e1.getMessage());
		}

		try {
			stream = new FileOutputStream("planilha.xls");
		} catch (FileNotFoundException e1) {
			// System.out.println("erro3");
		}

		sheet1 = wb.getSheet("Pessoas");

		int i = pessoas.procurarIndice(cpf);

		// System.out.println(i);

		HSSFRow row = sheet1.getRow(i);
		for (int k = 0; k < 19; k++) {
			row.getCell((short) k).setCellValue("-");
		}

		try {
			wb.write(stream);
		} catch (IOException e1) {
			System.out.println("erro no stream");
		}
		try {
			stream.flush();
		} catch (IOException e2) {
			System.out.println("erro flush");
		}
		try {
			stream.close();
		} catch (IOException e3) {
			System.out.println("erro close");
		}

		pessoas.remover(cpf);
	}

	public String imprimir() {
		String retorno = "";

		try {
			file = new FileInputStream(new File("planilha.xls"));
		} catch (FileNotFoundException e1) {
			// System.out.println("erro1");
		}

		try {
			this.wb = new HSSFWorkbook(file);
		} catch (IOException e1) {
			// System.out.println("erro2");
			// System.out.println(e1.getMessage());
		}

		try {
			stream = new FileOutputStream("planilha.xls");
		} catch (FileNotFoundException e1) {
			// System.out.println("erro3");
		}

		sheet1 = wb.getSheet("Pessoas");

		for (int i = 0; i < cont; i++) {
			HSSFRow row = this.sheet1.getRow(i);
			String cpf, rg;
			try {
				cpf = lerCelula(i, 1);
			} catch (NumberFormatException e) {
				HSSFCell cell2 = row.getCell((short) 1);
				cpf = "" + (int) cell2.getNumericCellValue();
			}

			String nome = lerCelula(i, 2);
			String dataNasc = lerCelula(i, 3);
			try {
				rg = lerCelula(i, 4);
			} catch (NumberFormatException e) {
				HSSFCell cell4 = row.getCell((short) 4);
				rg = "" + (int) cell4.getNumericCellValue();
			}
			if (!nome.equals("-")) {
				retorno += nome + " / " + cpf + " / " + rg + " / " + dataNasc
						+ "\n";
			}
		}

		try {
			wb.write(stream);
		} catch (IOException e) {
			System.out.println("erro no stream");
		}
		try {
			stream.flush();
		} catch (IOException e) {
			System.out.println("erro flush");
		}
		try {
			stream.close();
		} catch (IOException e) {
			System.out.println("erro close");
		}
		return retorno;
	}

	@Override
	public Iterator<Pessoa> getIterator() {
		Iterator<Pessoa> it = new IteratorArquivoPessoa("Pessoas");
		return it;
	}

	@SuppressWarnings("deprecation")
	public int getLinha(String cpf) throws ElementoNaoEncontradoException {
		
		int i = 0;
		String str = "";
		boolean achou = false;

		for (; i < this.cont && !achou; i++) {
			HSSFRow row = sheet1.getRow(i);
			HSSFCell cell = row.getCell((short) 1);
			try {
				str = cell.getStringCellValue();
				// System.out.println("entrou no 1 str: "+str+" cpf: "+cpf);
			} catch (NumberFormatException e) {
				str = "" + cell.getNumericCellValue();
				// System.out.println("entrou no 2");
			}

			if (str.equals(cpf)) {
				achou = true;
			}
		}

		if (!achou) {
			throw new ElementoNaoEncontradoException();
		}

		
		try {
			wb.write(stream);
		} catch (IOException e) {
			System.out.println("erro no stream");
		}
		try {
			stream.flush();
		} catch (IOException e) {
			System.out.println("erro flush");
		}
		try {
			stream.close();
		} catch (IOException e) {
			System.out.println("erro close");
		}
		
		return --i;
	}

	@SuppressWarnings("deprecation")
	public RepositorioArrayPessoa procurarNome(String nome)
			throws ElementoNaoEncontradoException {
		
		try {
			file = new FileInputStream(new File("planilha.xls"));
		} catch (FileNotFoundException e1) {
			// System.out.println("erro1");
		}

		try {
			this.wb = new HSSFWorkbook(file);
		} catch (IOException e1) {
			// System.out.println("erro2");
			// System.out.println(e1.getMessage());
		}

		try {
			stream = new FileOutputStream("planilha.xls");
		} catch (FileNotFoundException e1) {
			// System.out.println("erro3");
		}

		sheet1 = wb.getSheet("Pessoas");
		//Pessoa p = null;
		boolean achou = false;
		RepositorioArrayPessoa resultado = new RepositorioArrayPessoa();
		int i = 0;
		String str = "", cpf = "";
		for (; i < this.cont; i++) {
			HSSFRow row = sheet1.getRow(i);
			HSSFCell cell = row.getCell((short) 2);
			try {
				str = cell.getStringCellValue();
				// System.out.println("entrou no 1 str: "+str+" cpf: "+cpf);
			} catch (NumberFormatException e) {
				str = "" + cell.getNumericCellValue();
				// System.out.println("entrou no 2");
			}
			if (str.toLowerCase().contains(nome.toLowerCase())) {
				HSSFCell cell2 = row.getCell((short) 1);
				try {
					str = cell2.getStringCellValue();
					// System.out.println("entrou no 1 str: "+str+" cpf: "+cpf);
				} catch (NumberFormatException e) {
					str = "" + cell2.getNumericCellValue();
					// System.out.println("entrou no 2");
				}
				resultado.inserir(this.procurar(cpf));
				achou=true;
			}
		}

		if (achou == false) {
			throw new ElementoNaoEncontradoException();
		}
		return resultado;
	}

}