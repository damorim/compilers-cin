package escola.testes;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class TesteRelatorioTxt {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileWriter arq=null;
		try {
			arq = new FileWriter("relatoriosip/relatorio.txt");
		} catch (IOException e1) {
			System.out.println("erro1");
		}
	    PrintWriter gravarArq = new PrintWriter(arq);
	    String str="";
	    for (int i=1; i<=50; i++) {
	      str+="arroz arroz arroz \n";
	    }
	    gravarArq.printf(str);
	 
	    try {
			arq.close();
		} catch (IOException e) {
			System.out.println("erro");
		}


	}

}
