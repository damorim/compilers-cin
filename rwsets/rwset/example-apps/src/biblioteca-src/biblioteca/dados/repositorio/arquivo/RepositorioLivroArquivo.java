package biblioteca.dados.repositorio.arquivo;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.*; 

import biblioteca.dados.IterableLivro;
import biblioteca.dados.IteratorLivro;
import biblioteca.dados.entidades.Livro;
import biblioteca.dados.repositorio.interfaces.RepositorioLivro;
import biblioteca.negocios.exceptions.livro.LivroJaCadastradoException;
import biblioteca.negocios.exceptions.livro.LivroNaoEncontradoException;
 
public class RepositorioLivroArquivo implements RepositorioLivro, IterableLivro {
        private int contador;
 
        public RepositorioLivroArquivo(){
                this.contador = 0;
                try{
                        inicializarArquivo();
                } catch(Exception e){
                        criarArquivo();
                }
        }
 
        public void inicializarArquivo() throws IOException{
 
                InputStream in = new FileInputStream("GerenciamentoBiblioteca.xls");
                HSSFWorkbook file = new HSSFWorkbook(in);
                HSSFSheet sheet1 = file.getSheet("Livro");
 
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
 
        public InputStream abrirArquivo() throws IOException{
                InputStream in = new FileInputStream("GerenciamentoBiblioteca.xls");
                return in;
        }
 
        public void fecharArquivo(HSSFWorkbook wb) throws IOException{
                FileOutputStream out = new FileOutputStream("GerenciamentoBiblioteca.xls");
                wb.write(out);
                out.flush();
                out.close();
        }
 
        public void criarArquivo(){
                try{
                        HSSFWorkbook file = new HSSFWorkbook();
                        HSSFSheet sheet1 = file.createSheet("Livro");
                        fecharArquivo(file);
 
                }catch(Exception e){
                        e.getStackTrace();
 
                }
        }
 
 
        @SuppressWarnings("deprecation")
        @Override
        public void inserirLivro(Livro livro) throws LivroJaCadastradoException, IOException {
                InputStream in = abrirArquivo();
                HSSFWorkbook file = new HSSFWorkbook(in);
 
                HSSFSheet sheet1 = file.getSheet("Livro");
 
                if(sheet1 == null){
                        sheet1 = file.createSheet("Livro");
                }
 
                int index = this.getIndice(livro.getCodigo());
                if(index == this.contador || index == -1){ // nao existe na planilha... inserir
                        HSSFRow linha = sheet1.createRow(this.contador);
 
                        HSSFCell celula = linha.createCell((short) 0);
                        celula.setCellValue(livro.getCodigo());
 
                        celula = linha.createCell((short) 1);
                        celula.setCellValue(livro.getTitulo());
 
                        celula = linha.createCell((short) 2);
                        celula.setCellValue(livro.getAutor());
 
                        celula = linha.createCell((short) 3);
                        celula.setCellValue(livro.getAssunto());
 
                        celula = linha.createCell((short) 4);
                        celula.setCellValue(livro.getSinopse());
 
                        celula = linha.createCell((short) 5); // qtdDisponivel
                        celula.setCellValue(0);
 
                        celula = linha.createCell((short) 6); // TotalEmprestimo
                        celula.setCellValue(0);
 
                        celula = linha.createCell((short) 7); //TotalAcervo
                        celula.setCellValue(0);
                        
                        celula = linha.createCell((short) 8);
                        celula.setCellValue(livro.getEmprestimos().listaNaplanilha());
                        
                       // celula = linha.createCell((short) 8); // lista de emprestimos
                      // celula.setCellvalue(livro.getEmprestimos().)
                        // lista de empr�stimo...
                }else{
                        throw new LivroJaCadastradoException(livro.getCodigo());
                }
                fecharArquivo(file);
                in.close();
                this.contador++;
 
 
        }
       
       
       
        @SuppressWarnings("deprecation")
        @Override
        public void removerLivro(String codigo) throws LivroNaoEncontradoException, IOException {
                InputStream in = abrirArquivo();
                HSSFWorkbook file = new HSSFWorkbook(in);
                HSSFSheet sheet1 = file.getSheet("Livro");
 
                int index = this.getIndice(codigo);
                if(index == this.contador || index == -1) {// nao existe esse livro
                throw new LivroNaoEncontradoException(codigo);
 
                }else{
                        HSSFRow linha = sheet1.getRow(index);
                        int coluna = 0;
                        while(coluna <= 8){
                                HSSFCell celula = linha.getCell((short) coluna);
                                celula.setCellValue("");
                                coluna ++;
                        }
                }
 
                fecharArquivo(file);
                in.close();
 
        }
 
       
       
        @Override
        public Livro procurarLivro(String codigo)
                        throws LivroNaoEncontradoException, IOException {
 
                InputStream in = abrirArquivo();
                HSSFWorkbook file = new HSSFWorkbook(in);
                HSSFSheet sheet1 = file.getSheet("Livro");
               
                int index = this.getIndice(codigo);
                Livro livro = null;
        if(index == this.contador || index == -1){ //nao encontrou
        throw new LivroNaoEncontradoException(codigo);
                       
 
        }else{
                HSSFRow linha = sheet1.getRow(index);
 
                        HSSFCell celula = linha.getCell((short) 0);
                        String codigoLivro = celula.getStringCellValue();
                        celula = linha.getCell((short) 1);
                        String tituloLivro = celula.getStringCellValue();
                        celula = linha.getCell((short) 2);
                        String autorLivro = celula.getStringCellValue();
                        celula = linha.getCell((short) 3);
                        String assuntoLivro = celula.getStringCellValue();
                        celula = linha.getCell((short) 4);
                        String sinopseLivro =  celula.getStringCellValue();
                        celula = linha.getCell((short) 5);
                        int qtdDisponivel = (int) celula.getNumericCellValue();
                        celula = linha.getCell((short) 6);
                        int totalEmprestimo = (int) celula.getNumericCellValue();
                        celula = linha.getCell((short) 7);
                        int totalAcervo =  (int) celula.getNumericCellValue(); 
                        
                          
 
                        livro = new Livro(codigoLivro,tituloLivro,autorLivro, assuntoLivro, sinopseLivro);
                        livro.setQtdDisponivel(qtdDisponivel);
                        livro.setTotalEmprestimo(totalEmprestimo);
                        livro.setTotalAcervo(totalAcervo);
 
 
 
                }
                return livro;  // retornar� o primeiro objeto dispon�vel
 
        }
       
       
       
       
 
        @Override
        public void atualizarLivro(Livro livro) throws LivroNaoEncontradoException, IOException {
                InputStream in = abrirArquivo();
                HSSFWorkbook file = new HSSFWorkbook(in);
                HSSFSheet sheet1 = file.getSheet("Livro");
 
                int index = this.getIndice(livro.getCodigo());
                if (index == this.contador || index == -1){ // Nao encontrou
                        throw new LivroNaoEncontradoException(livro.getCodigo());
                }else{
                       
                                HSSFRow linha = sheet1.getRow(index);
                                        HSSFCell celula = linha.getCell((short) 1);
                                        celula.setCellValue(livro.getTitulo());
                                        celula = linha.getCell((short) 2);
                                        celula.setCellValue(livro.getAutor());
                                        celula = linha.getCell((short) 3);
                                        celula.setCellValue(livro.getAssunto());
                                        celula = linha.getCell((short) 4);
                                        celula.setCellValue(livro.getSinopse());
                                        celula = linha.getCell((short) 5);
                                        celula.setCellValue(livro.getQtdDisponivel());
                                        celula = linha.getCell((short) 6);
                                        celula.setCellValue(livro.getTotalEmprestimo());
                                        celula = linha.getCell((short) 7);
                                        celula.setCellValue(livro.getTotalAcervo());
 // Atualiza��o dos campos da lista de empr�stimo ...
                                        fecharArquivo(file);
                                        in.close();
 
                                }
                        }
               
 
        private int getIndice(String codigo) throws IOException{
                InputStream in = abrirArquivo();
                HSSFWorkbook wb = new HSSFWorkbook(in);
                HSSFSheet sheet = wb.getSheet("Livro");
 
                int contador = -1;
                boolean achou = false;
                if (sheet != null){
 
 
                        while (contador <this.contador && !achou){
                                contador ++;
                                HSSFRow row = sheet.getRow(contador);
 
                                if(row != null){
                                        HSSFCell cell = row.getCell((short) 0);
                                        if(cell.getStringCellValue().equals(codigo)){
                                                achou = true;
                                        }
                                }
 
                        }
                }
                return contador;       
 
        }
 
 
 
        @Override
        public IteratorLivro getIterator() {
                //Iterator<Livro> iterator = new Iterator<livro>;
                return null;
        }



}
