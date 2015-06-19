package escola.dados;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import escola.classesBase.*;

public class IteratorArquivoDisciplina extends IteratorArquivo<Disciplina>
		implements Iterator<Disciplina> {

	public IteratorArquivoDisciplina(String aba) {
		super(aba);
		// fechar();
	}

	public Disciplina next() {
		// super.abrir();
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

		sheet1 = wb.getSheet(aba);

		Disciplina d = null;

		String nome = "", ementa = "";
		HSSFRow row = null;

		int i = super.indiceAtual;
		row = this.sheet1.getRow(i);

		if (!lerCelula(i, 0).equals("-")) {
			nome = lerCelula(i, 0);
			ementa = lerCelula(i, 1);
			d = new Disciplina(nome, ementa);
		}

		// pessoas.inserir(pessoa);

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

		// super.fechar();
		return d;
	}

	@SuppressWarnings("deprecation")
	public String lerCelula(int linha, int coluna) {
		HSSFRow row = this.sheet1.getRow(linha);
		HSSFCell cell = row.getCell((short) coluna);
		return cell.getStringCellValue();

	}

}
