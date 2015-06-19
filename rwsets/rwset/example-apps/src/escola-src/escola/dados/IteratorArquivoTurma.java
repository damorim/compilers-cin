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

public class IteratorArquivoTurma extends IteratorArquivo<Turma> implements
		Iterator<Turma> {

	public IteratorArquivoTurma(String aba) {
		super(aba);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unused")
	@Override
	public Turma next() {
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

		Turma d = null;

		String nome = "";
		HSSFRow row = null;

		int i = super.indiceAtual;
		
		if (i==-1)
			i++;
		
		row = this.sheet1.getRow(i);

		if (!lerCelula(i, 0).equals("-")) {
			nome = lerCelula(i, 0);
			;
			d = new Turma(nome);
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
		return d;
	}

	@SuppressWarnings("deprecation")
	public String lerCelula(int linha, int coluna) {
		HSSFRow row = this.sheet1.getRow(linha);
		HSSFCell cell = row.getCell((short) coluna);
		return cell.getStringCellValue();

	}
}
