package biblioteca.dados.repositorio.arquivo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.*;

import biblioteca.dados.IterablePessoa;
import biblioteca.dados.IteratorPessoa;
import biblioteca.dados.entidades.Aluno;
import biblioteca.dados.entidades.Endereco;
import biblioteca.dados.entidades.Funcionario;
import biblioteca.dados.entidades.Pessoa;
import biblioteca.dados.repositorio.interfaces.RepositorioPessoa;
import biblioteca.negocios.exceptions.pessoa.PessoaJaCadastradaException;
import biblioteca.negocios.exceptions.pessoa.PessoaNaoEncontradaException;

public class RepositorioPessoaArquivo implements IterablePessoa,
RepositorioPessoa {
	int contador;

	public RepositorioPessoaArquivo(){
		this.contador = 0;
		try{
			inicializarArquivo();
		}catch (Exception e){
			criarArquivo();
		}
	}


	public void criarArquivo(){
		try {
			HSSFWorkbook wb = new HSSFWorkbook();

			HSSFSheet sheet = wb.createSheet("Pessoa");
			fecharArquivo(wb);
		}catch(Exception e ){
			e.getStackTrace();
		}

	}



	public void fecharArquivo(HSSFWorkbook wb) throws IOException{
		FileOutputStream out = new FileOutputStream("GerenciamentoBiblioteca.xls");
		wb.write(out);
		out.flush();
		out.close();
	}



	public InputStream abrirArquivo() throws FileNotFoundException{
		InputStream in = new FileInputStream("GerenciamentoBiblioteca.xls");
		return in;
	}



	public void inicializarArquivo() throws IOException{
		InputStream in = new FileInputStream("GerenciamentoBiblioteca.xls");
		HSSFWorkbook wb = new HSSFWorkbook(in);
		HSSFSheet sheet = wb.getSheet("Pessoa");

		if(sheet != null){
			int contador = 0;
			HSSFRow linha = sheet.getRow(contador);

			if(linha != null){
				contador ++;
				linha = sheet.getRow(contador);
			}
			this.setContador(contador);

		}
		fecharArquivo(wb);
		in.close();

	}


	public int getContador() {
		return contador;
	}


	public void setContador(int contador) {
		this.contador = contador;
	}


	@Override
	public void inserirPessoa(Pessoa pessoa)
			throws PessoaJaCadastradaException, IOException {

		InputStream in = abrirArquivo();
		HSSFWorkbook wb = new HSSFWorkbook(in);
		HSSFSheet sheet = wb.getSheet("Pessoa");

		if(sheet == null){
			sheet = wb.createSheet("Pessoa");
		}

		int index = this.getIndice(pessoa.getCpf());
		if(index == this.contador || index == -1){ // nao existe na planilha (inserir)
			HSSFRow linha = sheet.createRow(this.contador);

			if(pessoa instanceof Funcionario){
				HSSFCell celula = linha.createCell((short) 0);
				celula.setCellValue(pessoa.getCpf());

				celula = linha.createCell((short) 1);
				celula.setCellValue(pessoa.getNome());

				celula = linha.createCell((short) 2);
				celula.setCellValue(pessoa.getEndereco().getRua());

				celula = linha.createCell((short) 3);
				celula.setCellValue(pessoa.getEndereco().getNumero());

				celula = linha.createCell((short) 4);
				celula.setCellValue(pessoa.getEndereco().getComplemento());

				celula = linha.createCell((short) 5);
				celula.setCellValue(pessoa.getEndereco().getCep());

				celula = linha.createCell ((short) 6);
				celula.setCellValue(pessoa.getTelefone());

				celula = linha.createCell((short) 7);
				celula.setCellValue(pessoa.getSenha());

				celula = linha.createCell ((short) 8);
				celula.setCellValue(-1);  // isso significa que n�o se aplica a funcion�rios!

				celula = linha.createCell((short) 9);
				celula.setCellValue(-1); // ListaEmprestimo 

			}

			if( pessoa instanceof Aluno){

				HSSFCell celula = linha.createCell((short) 0);
				celula.setCellValue(pessoa.getCpf());

				celula = linha.createCell ((short) 1);
				celula.setCellValue(pessoa.getNome());


				celula = linha.createCell((short) 2);
				celula.setCellValue(pessoa.getEndereco().getRua());

				celula = linha.createCell((short) 3);
				celula.setCellValue(pessoa.getEndereco().getNumero());

				celula = linha.createCell((short) 4);
				celula.setCellValue(pessoa.getEndereco().getComplemento());

				celula = linha.createCell((short) 5);
				celula.setCellValue(pessoa.getEndereco().getCep());

				celula = linha.createCell ((short) 6);
				celula.setCellValue(pessoa.getTelefone());

				celula = linha.createCell((short) 7);
				celula.setCellValue(pessoa.getSenha());

				celula = linha.createCell ((short) 8);
				celula.setCellValue(((Aluno) pessoa).totalLivrosEmprestimo());

				celula = linha.createCell((short) 9);
				celula.setCellValue(((Aluno) pessoa).getEmprestimos().listaNaplanilha()); // ListaEmprestimo 

			}

		}else{
			throw new PessoaJaCadastradaException(pessoa.getCpf());
		}


		fecharArquivo(wb);
		in.close();
		this.contador ++;
	}

	@Override
	public void removerPessoa(String cpf) throws PessoaNaoEncontradaException,
	IOException {
		InputStream in = abrirArquivo();
		HSSFWorkbook wb = new HSSFWorkbook(in);
		HSSFSheet sheet = wb.getSheet("Pessoa");

		int index = this.getIndice(cpf);
		if(index == -1  || index == this.contador ){
			throw new PessoaNaoEncontradaException(cpf);
		}else{

			HSSFRow linha = sheet.getRow(index);
			int coluna = 0;

			while(coluna <=9){
				HSSFCell celula = linha.getCell((short) coluna);
				celula.setCellValue("");
				coluna ++;
			}

		}
		fecharArquivo(wb);
		in.close();
	}



	@Override
	public Pessoa procurarPessoa(String cpf)
			throws PessoaNaoEncontradaException, IOException {
		InputStream in = abrirArquivo();
		HSSFWorkbook wb = new HSSFWorkbook(in);
		HSSFSheet sheet = wb.getSheet("Pessoa");
		int index = this.getIndice(cpf);
		Pessoa pessoa = null;
		if(index == this.contador || index == -1){ //nao encontrou
			throw new PessoaNaoEncontradaException(cpf);
		}else{


			HSSFRow linha = sheet.getRow(index);


			HSSFCell celula = linha.getCell((short) 0);
			String cpfPessoa = celula.getStringCellValue();

			celula = linha.getCell((short) 1);
			String nome = celula.getStringCellValue();

			celula = linha.getCell((short ) 2);
			String rua = celula.getStringCellValue();

			celula = linha.getCell((short) 3);
			String numero = celula.getStringCellValue();

			celula = linha.getCell((short) 4);
			String complemento = celula.getStringCellValue();

			celula = linha.getCell((short )5);
			String cep = celula.getStringCellValue();

			celula = linha.getCell((short) 6);
			String telefone = celula.getStringCellValue();

			celula = linha.getCell((short) 7);
			String senha = celula.getStringCellValue();

			celula = linha.getCell((short) 8);
			int totalLivrosEmprestimo = (int) celula.getNumericCellValue();

			celula = linha.getCell((short) 9);
			String listaEmprestimos = celula.getStringCellValue();
			// pegar todos os empr�stimos da lista... (desmembrar), criar objetos emprestimos... e add a lista



			if( totalLivrosEmprestimo == -1){ // � um funcion�rio!

				Endereco endereco = new Endereco (rua,numero,complemento,cep);
				pessoa = new Funcionario(nome,cpf,endereco,telefone,senha);

			}else{ 
				Endereco endereco = new Endereco (rua,numero,complemento,cep);
				pessoa = new Aluno(nome, cpf,endereco,telefone,senha);
				((Aluno)pessoa).setTotalEmprestimos(totalLivrosEmprestimo);
				// ((Aluno)pessoa).setEmprestimos(listaEmprestimos);


			}


			return pessoa;


		}

	}

	@Override
	public void atualizarPessoa(Pessoa pessoa)
			throws PessoaNaoEncontradaException, IOException {
		InputStream in = abrirArquivo();
		HSSFWorkbook wb = new HSSFWorkbook(in);
		HSSFSheet sheet1 = wb.getSheet("Pessoa");

		int index = this.getIndice(pessoa.getCpf());
		if (index == this.contador || index == -1){ // Nao encontrou
			throw new PessoaNaoEncontradaException(pessoa.getCpf());
		}else{
			if (pessoa instanceof Aluno){
				HSSFRow linha = sheet1.getRow(index);
				HSSFCell celula = linha.getCell((short) 1);
				celula.setCellValue(pessoa.getNome());

				celula = linha.getCell((short) 2);
				celula.setCellValue(pessoa.getEndereco().getRua());

				celula = linha.getCell((short) 3);
				celula.setCellValue(pessoa.getEndereco().getNumero());


				celula = linha.getCell((short) 4);
				celula.setCellValue(pessoa.getEndereco().getComplemento());

				celula = linha.getCell((short )5);
				celula.setCellValue(pessoa.getEndereco().getCep());

				celula = linha.getCell((short) 6);
				celula.setCellValue(pessoa.getTelefone());

				celula = linha.getCell((short) 7);
				celula.setCellValue(pessoa.getSenha());

				celula = linha.getCell((short) 8);
				celula.setCellValue(((Aluno)pessoa).getTotalEmprestimos());

				celula = linha.getCell((short) 9);
				celula.setCellValue(((Aluno) pessoa).getEmprestimos().listaNaplanilha());

			}else{ //funcion�rio
				HSSFRow linha = sheet1.getRow(index);
			HSSFCell celula = linha.getCell((short) 1);
			celula.setCellValue(pessoa.getNome());

			celula = linha.getCell((short) 2);
			celula.setCellValue(pessoa.getEndereco().getRua());

			celula = linha.getCell((short) 3);
			celula.setCellValue(pessoa.getEndereco().getNumero());


			celula = linha.getCell((short) 4);
			celula.setCellValue(pessoa.getEndereco().getComplemento());

			celula = linha.getCell((short )5);
			celula.setCellValue(pessoa.getEndereco().getCep());

			celula = linha.getCell((short) 6);
			celula.setCellValue(pessoa.getTelefone());

			celula = linha.getCell((short) 7);
			celula.setCellValue(pessoa.getSenha());
			}


		}

	}

	@Override
	public IteratorPessoa getIterator() {
		// TODO Auto-generated method stub
		return null;
	}



	private int getIndice(String cpf) throws IOException{
		InputStream in = abrirArquivo();
		HSSFWorkbook wb = new HSSFWorkbook(in);
		HSSFSheet sheet = wb.getSheet("Pessoa");

		int contador = -1;
		boolean achou = false;
		if(sheet != null){

			while(contador < this.contador && !achou){
				contador ++;

				HSSFRow linha = sheet.getRow(contador);

				if(linha != null){
					HSSFCell celula = linha.getCell((short) 0);
					if(celula.getStringCellValue().equals(cpf)){
						achou = true;
					}
				}
			}
		}

		return contador;
	}


}
