package escola.dados;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import escola.classesBase.*;
import escola.excecoes.ElementoNaoEncontradoException;
import escola.excecoes.RepositorioException;

public class RepositorioArquivoDisciplina implements Repositorio<Disciplina> {
	HSSFWorkbook wb;
	HSSFSheet sheet1;
	int cont = 0;
	FileOutputStream stream;
	FileInputStream file;
	boolean abrir = false;

	public int getCont() {
		return this.cont;
	}

	public RepositorioArquivoDisciplina() {
		try {
			file = new FileInputStream(new File("planilha.xls"));
			this.wb = new HSSFWorkbook(file);
			sheet1 = wb.getSheet("Disciplinas");
			stream = new FileOutputStream("planilha.xls");
			System.out.println("achou td disc");
			stream.flush();
			stream.close();
		} catch (FileNotFoundException e1) {
			System.out.println("n achou disc");
			wb = new HSSFWorkbook();
			this.sheet1 = wb.createSheet("Disciplinas");
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
			abrir = true;
		} catch (IOException e) {
			System.out.println("n achou disc2");
			wb = new HSSFWorkbook();
			this.sheet1 = wb.createSheet("Disciplinas");
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
			abrir = true;
		}

		this.lerPlanilha();
	}

	public void lerPlanilha() {
		@SuppressWarnings("unused")
		Disciplina d = null;
		String nome, ementa;
		int i = 0;
		boolean acabou = false;

		while (!acabou) {
			HSSFRow row = null;

			try {
				row = this.sheet1.getRow(i);
			} catch (NullPointerException e) {
				System.out.println("null pointer disc");
			}

			HSSFCell cell = null;
			boolean pulou = false;
			try {
				cell = row.getCell((short) 0);
			} catch (NullPointerException e) {
				acabou = true;
				// System.out.println("acabou");
			}
			if (cell != null) {

				try {
					nome = lerCelula(i, 0);
				} catch (NumberFormatException e) {
					HSSFCell cell1 = row.getCell((short) 0);
					try {
						nome = "" + cell1.getNumericCellValue();
					} catch (NumberFormatException e1) {
						// System.out.print("achou um vazio");
						pulou = true;
					}
				}

				ementa = lerCelula(i, 1);

				if (!pulou) {

					nome = lerCelula(i, 0);
					d = new Disciplina(nome, ementa);
					i++;
					cont++;
				} else {
					acabou = true;
				}
			} else {
				acabou = true;
			}
		}
	}

	@SuppressWarnings("deprecation")
	public String lerCelula(int linha, int coluna) {
		HSSFRow row = this.sheet1.getRow(linha);
		HSSFCell cell = row.getCell((short) coluna);
		return cell.getStringCellValue();
	}

	@SuppressWarnings("deprecation")
	public void gravarDisciplina(Disciplina d) {

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

		sheet1 = wb.getSheet("Disciplinas");

		// System.out.println("entrou no gravar pessoa");

		HSSFRow row = sheet1.createRow(cont);
		row.createCell((short) 0).setCellValue(d.getNome());
		row.createCell((short) 1).setCellValue(d.getEmenta());

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

	public void inserir(Disciplina item) throws RepositorioException {
		gravarDisciplina(item);
		++cont;

	}

	@SuppressWarnings("deprecation")
	public Disciplina procurar(String nome)
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

		sheet1 = wb.getSheet("Disciplinas");

		Disciplina d = null;

		int i = this.getLinha(nome);

		HSSFRow row = sheet1.getRow(i);
		HSSFCell cell = row.getCell((short) 0);
		String nome1 = cell.getStringCellValue();
		String ementa = row.getCell((short) 1).getStringCellValue();

		d = new Disciplina(nome1, ementa);

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

		return d;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void atualizar(Disciplina item)
			throws ElementoNaoEncontradoException, RepositorioException {
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

		sheet1 = wb.getSheet("Disciplinas");

		int aux = this.getLinha(item.getNome());
		// HSSFRow row2 = sheet1.getRow(aux);
		HSSFRow row = sheet1.createRow(aux);
		row.createCell((short) 0).setCellValue(item.getNome());
		row.createCell((short) 1).setCellValue(item.getEmenta());

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

	}

	@SuppressWarnings("deprecation")
	@Override
	public void remover(String nome) throws ElementoNaoEncontradoException,
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

		sheet1 = wb.getSheet("Disciplinas");

		int aux = this.getLinha(nome);
		HSSFRow row2 = sheet1.getRow(aux);
		for (int k = 0; k < 2; k++) {
			row2.getCell((short) k).setCellValue("-");
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

	}

	@Override
	public Iterator<Disciplina> getIterator() {
		Iterator<Disciplina> it = new IteratorArquivoDisciplina("Disciplinas");
		return it;
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

		sheet1 = wb.getSheet("Disciplinas");

		for (int i = 0; i < cont; i++) {
			String nome = lerCelula(i, 0);
			String ementa = lerCelula(i, 1);

			if (!nome.equals("")) {
				retorno += nome + " / " + ementa + "\n";
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

	@SuppressWarnings("deprecation")
	public int getLinha(String nome) throws ElementoNaoEncontradoException {
		int i = 0;
		String str = "";
		boolean achou = false;

		for (; i < this.cont && !achou; i++) {
			HSSFRow row = sheet1.getRow(i);
			HSSFCell cell = row.getCell((short) 0);
			str = cell.getStringCellValue();
			if (str.equals(nome)) {
				achou = true;
			}
		}

		if (!achou) {
			throw new ElementoNaoEncontradoException();
		}

		return --i;
	}

	@SuppressWarnings("deprecation")
	public RepositorioArrayDisciplina procurarNome(String nome)
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

		sheet1 = wb.getSheet("Disciplinas");

		RepositorioArrayDisciplina disc = new RepositorioArrayDisciplina();

		int i = 0;
		String str = "";
		boolean achou = false;

		for (; i < this.cont && !achou; i++) {
			HSSFRow row = sheet1.getRow(i);
			HSSFCell cell = row.getCell((short) 0);
			str = cell.getStringCellValue();
			if (str.toLowerCase().contains(nome.toLowerCase())) {
				try {
					disc.inserir(this.procurar(str));
				} catch (ElementoNaoEncontradoException e) {

				}
				achou = true;
			}
		}

		if (!achou) {
			throw new ElementoNaoEncontradoException();
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
		return disc;
	}
}
