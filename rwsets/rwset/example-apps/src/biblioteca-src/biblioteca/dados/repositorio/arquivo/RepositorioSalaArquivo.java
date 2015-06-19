package biblioteca.dados.repositorio.arquivo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.*;

import biblioteca.dados.IterableSala;
import biblioteca.dados.IteratorSala;
import biblioteca.dados.entidades.Sala;
import biblioteca.dados.repositorio.interfaces.RepositorioSala;
import biblioteca.negocios.exceptions.sala.SalaJaCadastradaException;
import biblioteca.negocios.exceptions.sala.SalaNaoEncontradaException;

public class RepositorioSalaArquivo implements RepositorioSala, IterableSala{

	private int contador;

	public RepositorioSalaArquivo(){
		this.contador = 0;
		try{
			inicializarArquivo();
		} catch(Exception e){
			criarArquivo();
		}
	}



	public void criarArquivo(){
		try{
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("Sala");
			fecharArquivo(wb);
		}catch(Exception e){
			e.getStackTrace();
		}

	}


	public void fecharArquivo(HSSFWorkbook wb) throws IOException{
		FileOutputStream out = new FileOutputStream("GerenciamentoBiblioteca.xls");
		wb.write(out);
		out.flush();
		out.close();

	}



	public InputStream abrirArquivo() throws IOException{
		InputStream in = new FileInputStream("GerenciamentoBiblioteca.xls");
		return in;
	}



	public void inicializarArquivo() throws IOException{

		InputStream in = new FileInputStream("GerenciamentoBiblioteca.xls");
		HSSFWorkbook file = new HSSFWorkbook(in);
		HSSFSheet sheet1 = file.getSheet("Sala");

		if(sheet1 != null){
			int contador = 0;
			HSSFRow linha = sheet1.getRow(contador);

			while(linha != null){
				contador++;
				linha = sheet1.getRow(contador);
			}
			this.setContador(contador);    
		}
		fecharArquivo(file);
		in.close();


	}


	public int getContador() {
		return contador;
	}


	public void setContador(int contador) {
		this.contador = contador;
	}


	@SuppressWarnings("deprecation")
	@Override
	public void inserirSala(Sala sala) throws SalaJaCadastradaException,
	IOException {
		InputStream in = abrirArquivo();
		HSSFWorkbook wb = new HSSFWorkbook(in);

		HSSFSheet sheet = wb.getSheet("Sala");
		if( sheet == null){
			wb.createSheet("Sala");
		}
		
		int index = this.getIndice(sala.getCodigo());
		if( index == this.contador || index == -1) { // nao encontrou, posso add!
		
			HSSFRow linha = sheet.createRow(contador);
			
			 HSSFCell celula = linha.createCell((short) 0);
             celula.setCellValue(sala.getCodigo());
             
             celula = linha.createCell((short) 1);
             celula.setCellValue(sala.getCapacidade());
             
             celula = linha.createCell((short) 2);
             celula.setCellValue(sala.getTotalEmprestimos());
             
             celula = linha.createCell((short) 3);
            
             celula.setCellValue("Dispon�vel"); 
             
             celula = linha.createCell ((short) 4);
             celula.setCellValue(""); //data de devolu��o
             
             celula = linha.createCell ((short) 5);
             celula.setCellValue(""); // cpf respons�vel 
             
		}else{
			throw new SalaJaCadastradaException(sala.getCodigo());
		}
		
             fecharArquivo(wb);
             in.close();
             this.contador++;

	}

	@Override
	public void removerSala(String codigo) throws SalaNaoEncontradaException,
	IOException {
		InputStream in = abrirArquivo();
		HSSFWorkbook wb = new HSSFWorkbook(in);
		HSSFSheet sheet = wb.getSheet("Sala");
		
		int index = this.getIndice(codigo);
		
		if(index == -1 || index == this.contador){
			throw new SalaNaoEncontradaException(codigo);
		}else{
			
			HSSFRow linha = sheet.getRow(index);
			int coluna = 0;
			
			
			while(coluna <=5){
				@SuppressWarnings("deprecation")
				HSSFCell celula = linha.getCell((short) coluna);
                celula.setCellValue("");
                coluna ++;
			}
			
			
		}

		fecharArquivo(wb);
		in.close();
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public Sala procurarSala(String codigo) throws SalaNaoEncontradaException,
	IOException {
		
		InputStream in = abrirArquivo();
		HSSFWorkbook wb = new HSSFWorkbook(in);
		HSSFSheet sheet = wb.getSheet("Sala");
		
		int index = this.getIndice(codigo);
		Sala sala = null;
		
		if (index == this.contador || index == -1){
			throw new SalaNaoEncontradaException(codigo);
		}else{
			HSSFRow linha = sheet.getRow(index);
			 
            HSSFCell celula = linha.getCell((short) 0);
            String codigoSala = celula.getStringCellValue();
            
            celula = linha.getCell((short) 1);
           int capacidade = (int) celula.getNumericCellValue();
            
            celula = linha.getCell((short) 2);
            int totalEmprestimos = (int) celula.getNumericCellValue();
            
            celula = linha.getCell((short) 3);
            String  isdisponivel = celula.getStringCellValue();
            boolean disponivel = false;
            if(isdisponivel.equals("Dispon�vel")){
            	disponivel = true;
            }
            
            celula = linha.getCell((short) 4);
            String dataDevolucao = celula.getStringCellValue();
            
            celula = linha.getCell((short) 5);
            String cpfResponsavel = celula.getStringCellValue();
            
            
            sala =  new Sala(codigoSala, capacidade); 
            sala.setCpfResponsavel(cpfResponsavel);
            sala.setDataDevolucao(dataDevolucao);
            sala.setDisponivel(disponivel);
            sala.setTotalEmprestimos(totalEmprestimos);
            

		}
		return sala;
	}

	
	
	@SuppressWarnings("deprecation")
	@Override
	public void atualizarSala(Sala sala) throws SalaNaoEncontradaException,
	IOException {
		InputStream in = abrirArquivo();
		HSSFWorkbook wb = new HSSFWorkbook(in);
		HSSFSheet sheet = wb.getSheet("Sala");
		
		int index = this.getIndice(sala.getCodigo());
		if(index == this.contador || index == -1){
			throw new SalaNaoEncontradaException(sala.getCodigo());
		}else{
			HSSFRow linha = sheet.getRow(index);
			
			HSSFCell celula = linha.getCell((short) 1);
            celula.setCellValue(sala.getCapacidade());
            
            celula = linha.getCell((short) 2);
            celula.setCellValue(sala.getTotalEmprestimos());
            
            celula = linha.getCell((short) 3);
            boolean disponivel = sala.isDisponivel();
            String resposta;
            if(disponivel == false){
            	resposta = "Indispon�vel";
            }else{
            	resposta = "Dispon�vel";
            }
            celula.setCellValue(resposta);
            
            celula = linha.getCell((short) 4);
            celula.setCellValue(sala.getDataDevolucao());
            
            celula = linha.getCell((short) 5);
            celula.setCellValue(sala.getCpfResponsavel());
            
           
		}

	}

	@Override
	public IteratorSala getIterator() {
		// TODO Auto-generated method stub
		return null;
	}


	@SuppressWarnings("deprecation")
	private int getIndice(String codigo) throws IOException{
		InputStream in = abrirArquivo();
		HSSFWorkbook wb = new HSSFWorkbook(in);
		HSSFSheet sheet = wb.getSheet("Sala");

		int contador = -1;
		boolean achou = false;

		if( sheet != null ){

			while(contador < this.contador && !achou){
				contador++;
				HSSFRow linha = sheet.getRow(contador);

				if (linha != null){
					HSSFCell celula = linha.getCell((short) 0);

					if(celula.getStringCellValue().equals(codigo)){
						achou = true;
					}
				}

			}


		}

		return contador;
	}

}
