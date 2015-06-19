package escola.testes;

import java.io.*;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class TesteLerPlanilha {

	/**
	 * @param args
	 */
	@SuppressWarnings({ "resource", "deprecation" })
	public static void main(String[] args) {

		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet1 = wb.createSheet("Teste");
		FileOutputStream stream;
		FileInputStream file;
		try {
			file = new FileInputStream(new File("planilha.xls"));
			wb = new HSSFWorkbook(file);
			sheet1 = wb.getSheet("Pessoas");
			stream = new FileOutputStream("planilha.xls");
			stream.flush();
			stream.close();
			for (int i=0; i<5; i++){
				HSSFRow row = sheet1.createRow(i);
				row.createCell((short)0).setCellValue("1");
				row.createCell((short)1).setCellValue("o de baixo e vazio");
				row.createCell((short)3).setCellValue("o de cima nao tem nada, o de baixo e espaco");
				row.createCell((short)4).setCellValue(" ");
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("erro dentro1");
		} catch (IOException e) {
			System.out.println("erro dentro2");
			
		}
		boolean acabou = false;

		HSSFCell cell = null;
		boolean pulou = false;
		double tipoPessoa = 0;
		int i = 0;
		HSSFCell cell1 = null;

		while (!acabou) {
			HSSFRow row = null;

			try {
				row = sheet1.getRow(i);
			} catch (NullPointerException e) {
				System.out.println("null pointer");
			}

			try {
				cell = row.getCell((short) 0);
			} catch (NullPointerException e) {
				acabou = true;
				System.out.println("acabou");
			}
			if (cell != null) {

				try {
					cell1 = row.getCell((short) 0);
					tipoPessoa = Integer.parseInt(cell1.getStringCellValue());
				} catch (NumberFormatException e) {
					cell1 = row.getCell((short) 0);
					try {
						tipoPessoa = cell1.getNumericCellValue();
					} catch (NumberFormatException e1) {
						System.out.print("achou um vazio");
						pulou = true;
					}
				}

			}
			System.out.println(cell1);
		}
	}
}
