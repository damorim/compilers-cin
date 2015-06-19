package escola.dados;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public abstract class IteratorArquivo<T> implements Iterator<T> {
	int indiceAtual;

	HSSFWorkbook wb;
	HSSFSheet sheet1;
	//int cont = 0;
	FileOutputStream stream;
	FileInputStream file;
	String arq="planilha.xls";
	String aba="";
	

	public IteratorArquivo(String aba) {
		this.aba=aba;
		try {
			file = new FileInputStream(new File(arq));
			this.wb = new HSSFWorkbook(file);
			sheet1 = wb.getSheet(aba);
			stream = new FileOutputStream(arq);
			stream.flush();
			stream.close();
		} catch (FileNotFoundException e1) {
			// System.out.println("n achou");
			wb = new HSSFWorkbook();
			this.sheet1 = wb.createSheet(aba);
			try {
				stream = new FileOutputStream(arq);
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
			// System.out.println("n achou");
			wb = new HSSFWorkbook();
			this.sheet1 = wb.createSheet(aba);
			try {
				stream = new FileOutputStream(arq);
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
		indiceAtual=-1;

	}

	public boolean hasNext() {
		
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
		
		//abrir();
		boolean retorno = true;
		HSSFRow row = null;
		//indiceAtual++;
		try {
			row = this.sheet1.getRow(++indiceAtual);
			
		} catch (NullPointerException e) {
			System.out.println("null pointer");
		}
		//indiceAtual--;

		HSSFCell cell = null;
		// boolean pulou = false;
		try {
			cell = row.getCell((short) 2);
			//System.out.println(cell.toString() +"<<<<cell");
		} catch (NullPointerException e) {
			retorno = false;
			//System.out.println("acabou iterator");
		}
		if (cell == null) {
			retorno = false;
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
		//fechar();
		
		return retorno;
	}

	public abstract T next();

	public void remove() {
		throw new UnsupportedOperationException(
				"Esta operacao nao eh suportada.");
	}

	public int getIndice() {
		return indiceAtual;
	}
	
}
