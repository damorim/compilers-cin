package biblioteca.gui.livro;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import biblioteca.fachada.Principal;
import biblioteca.gui.Login;
import biblioteca.negocios.exceptions.RealizarLoginException;
import biblioteca.negocios.exceptions.livro.LivroInvalidoException;
import biblioteca.negocios.exceptions.livro.LivroJaCadastradoException;
import biblioteca.negocios.exceptions.livro.LivroNaoEncontradoException;

public class CadastrarLivro extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	private static Principal fachada;
	private JTextField tf_sinopse;
	private JTextField tf_codigo;
	private JTextField tf_titulo;
	private JTextField tf_autor;
	private JTextField tf_assunto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastrarLivro frame = new CadastrarLivro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param fachada2 
	 */
	public CadastrarLivro() {
		fachada = Login.getFachada();
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 354, 258);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); 
		this.setResizable(false);
		this.setTitle("Cadastro Livro");
		
		JLabel lblCdigo = new JLabel("C\u00F3digo :");
		lblCdigo.setBounds(77, 39, 65, 14);
		contentPane.add(lblCdigo);
		
		JLabel lblTtulo = new JLabel("T\u00EDtulo :");
		lblTtulo.setBounds(77, 64, 65, 14);
		contentPane.add(lblTtulo);
		
		JLabel lblAutor = new JLabel("Autor :");
		lblAutor.setBounds(77, 89, 65, 14);
		contentPane.add(lblAutor);
		
		JLabel lblAssunto = new JLabel("Assunto : ");
		lblAssunto.setBounds(77, 114, 65, 14);
		contentPane.add(lblAssunto);
		
		tf_autor = new JTextField();
		tf_autor.setBounds(159, 86, 86, 20);
		contentPane.add(tf_autor);
		tf_autor.setColumns(10);
		
		tf_titulo = new JTextField();
		tf_titulo.setBounds(159, 61, 86, 20);
		contentPane.add(tf_titulo);
		tf_titulo.setColumns(10);
		
		 tf_assunto = new JTextField();
		tf_assunto.setBounds(159, 111, 86, 20);
		contentPane.add(tf_assunto);
		tf_assunto.setColumns(10);
		
		
		 tf_codigo = new JTextField();
		tf_codigo.setBounds(159, 36, 86, 20);
		contentPane.add(tf_codigo);
		tf_codigo.setColumns(10);
		
		JButton btnOk = new JButton("OK ");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					String codigo = tf_codigo.getText();
					String titulo = tf_titulo.getText();
					String autor = tf_autor.getText();
					String assunto = tf_assunto.getText();
					String sinopse = tf_sinopse.getText();
					
				
				if( codigo.equals("") || titulo.equals("") || autor.equals("") || assunto.equals("") || sinopse.equals("")){
					JOptionPane.showMessageDialog(null,"Todos os campos s�o obrigat�rios!");
					
				}if(fachada.getSistemaLogado() == false){
					JOptionPane.showMessageDialog(null, "Para realizar esta a��o voc� deve est� logado no sistema");
					CadastrarLivro.this.dispose();
					Login frame = new Login();
					frame.setVisible(true);
					
				}else{
					
					fachada.cadastrarLivro(codigo, titulo, autor, assunto, sinopse);
					JOptionPane.showMessageDialog(null, "O livro foi cadastrado com sucesso!");
					CadastrarLivro.this.dispose();
				}
				} catch (LivroInvalidoException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
					
					
				} catch (LivroNaoEncontradoException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (RealizarLoginException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
					
				} catch (LivroJaCadastradoException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				}
				
				
					
				
			}
		});
			

	
		btnOk.setBounds(119, 186, 89, 23);
		contentPane.add(btnOk);
		
		JLabel lblDigiteAsInformaes = new JLabel("Digite as informa\u00E7\u00F5es do livro: ");
		lblDigiteAsInformaes.setBounds(10, 11, 304, 14);
		contentPane.add(lblDigiteAsInformaes);
		
		JLabel lblSinopse = new JLabel("Sinopse : ");
		lblSinopse.setBounds(77, 139, 65, 14);
		contentPane.add(lblSinopse);
		
		tf_sinopse = new JTextField();
		tf_sinopse.setBounds(159, 136, 86, 20);
		contentPane.add(tf_sinopse);
		tf_sinopse.setColumns(10);
		
		
		
			
	}
}
