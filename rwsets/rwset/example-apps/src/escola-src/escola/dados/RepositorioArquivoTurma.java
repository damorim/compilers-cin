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

public class RepositorioArquivoTurma implements Repositorio<Turma> {

	private RepositorioArrayTurma turmas;
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

	public RepositorioArquivoTurma() {

		try {
			file = new FileInputStream(new File("planilha.xls"));
			this.wb = new HSSFWorkbook(file);
			sheet1 = wb.getSheet("Turmas");
			stream = new FileOutputStream("planilha.xls");
			System.out.println();
			stream.flush();
			stream.close();
		} catch (FileNotFoundException e1) {
			System.out.println("n achou turmas1");
			wb = new HSSFWorkbook();
			this.sheet1 = wb.createSheet("Turmas");
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
			System.out.println("n achou turmasIOEXCEP");
			wb = new HSSFWorkbook();
			this.sheet1 = wb.createSheet("Turmas");
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

		turmas = new RepositorioArrayTurma();
		this.lerPlanilha();

	}

	public void lerPlanilha() {
		
		

		Turma turma = null;

		String nome;
		//RepositorioArquivoDisciplina disciplinas = new RepositorioArquivoDisciplina();
		int i = 0;
		boolean acabou = false;

		while (!acabou) {
			HSSFRow row = null;
			
			this.sheet1=wb.getSheet("Turmas");

			if (i==-1)
				i++;
			
			try {
				row = this.sheet1.getRow(i);
			} catch (NullPointerException e) {
				System.out.println("null pointer turma");
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

				if (!pulou) {

					nome = lerCelula(i, 0);
					turma = new Turma(nome);
					this.turmas.inserir(turma);
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
	public void gravarTurma(Turma turma) {

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

		sheet1 = wb.getSheet("Turmas");

		// System.out.println("entrou no gravar pessoa");

		HSSFRow row = sheet1.createRow(cont);
		
		row.createCell((short) 0).setCellValue(turma.getNome());

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

	public void inserir(Turma item) throws RepositorioException {
		gravarTurma(item);
		cont++;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Turma procurar(String nome) throws ElementoNaoEncontradoException {
		Turma t = null;
		int i = this.getLinha(nome);
		
		HSSFRow row = sheet1.getRow(i);
		String nome1 = row.getCell((short)0).getStringCellValue();
		
		t=new Turma(nome1);
		
		return t;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void atualizar(Turma item) throws ElementoNaoEncontradoException,
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

		sheet1 = wb.getSheet("Turmas");
		
		HSSFRow row2 = sheet1.getRow(this.getLinha(item.getNome()));
		row2.getCell((short) 0).setCellValue(item.getNome());

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

		sheet1 = wb.getSheet("Turmas");
		
		HSSFRow row2 = sheet1.getRow(this.getLinha(nome));
		row2.getCell((short) 0).setCellValue("-");

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
	public Iterator<Turma> getIterator() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String imprimir(){
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

		sheet1 = wb.getSheet("Turmas");

		for (int i = 0; i < cont; i++) {
			String nome = lerCelula(i, 0);
			
			if (!nome.equals("")) {
				retorno += nome + "\n";
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
	public int getLinha(String nome) throws ElementoNaoEncontradoException{
		int i=0;
		String str="";
		boolean achou = false;
		
		for(;i<this.cont && !achou; i++){
			HSSFRow row = sheet1.getRow(i);
			HSSFCell cell = row.getCell((short)0);
			str = cell.getStringCellValue();
			if(str.equals(nome)){
				achou=true;
			}
		}
		
		if(!achou){
			throw new ElementoNaoEncontradoException();
		}
		
		return --i;
	}

	@SuppressWarnings("deprecation")
	public RepositorioArrayTurma procurarNome(String nome) throws ElementoNaoEncontradoException{
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

		sheet1 = wb.getSheet("Turmas");
		
		
		RepositorioArrayTurma turmas = new RepositorioArrayTurma();
		int i=0;
		String str="";
		boolean achou = false;
		
		for(;i<this.cont && !achou; i++){
			HSSFRow row = sheet1.getRow(i);
			HSSFCell cell = row.getCell((short)0);
			str = cell.getStringCellValue();
			if(str.toLowerCase().contains(nome.toLowerCase())){
				turmas.inserir(this.procurar(nome));
				achou=true;
			}
		}
		
		if(!achou){
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
		return turmas;

	}
}
