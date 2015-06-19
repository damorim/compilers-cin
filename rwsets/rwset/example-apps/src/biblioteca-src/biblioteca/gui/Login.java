package biblioteca.gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import biblioteca.fachada.Principal;
import biblioteca.negocios.exceptions.RepositorioInvalidoException;
import biblioteca.negocios.exceptions.pessoa.PessoaInvalidaException;
import biblioteca.negocios.exceptions.pessoa.PessoaJaCadastradaException;
import biblioteca.negocios.exceptions.pessoa.PessoaNaoEncontradaException;
import biblioteca.negocios.exceptions.pessoa.SenhaInvalidaException;
public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private static Principal fachada = new Principal();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 416, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); 
		this.setResizable(false);

		JLabel lblBiblioteca = new JLabel("Biblioteca  CIn");
		lblBiblioteca.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblBiblioteca.setBounds(144, 11, 171, 14);
		contentPane.add(lblBiblioteca);

		JLabel lblLogin = new JLabel("Login :");
		lblLogin.setBounds(127, 97, 46, 14);
		contentPane.add(lblLogin);

		textField = new JTextField();
		textField.setBounds(183, 94, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblSenha = new JLabel("Senha : ");
		lblSenha.setBounds(127, 137, 46, 14);
		contentPane.add(lblSenha);

		passwordField = new JPasswordField();
		passwordField.setBounds(184, 132, 86, 20);
		contentPane.add(passwordField);

		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				String tipoRepositorio;
				try {
					tipoRepositorio = fachada.carregarConfig();

				} catch (FileNotFoundException e) {

					tipoRepositorio = "ARRAY";

				} catch (RepositorioInvalidoException e) {

					tipoRepositorio = "ARRAY";

				}

				try {
					if (fachada.carregarRepositorio(tipoRepositorio)) {

						fachada.realizarLogin(textField.getText(), passwordField.getText());

						Gerenciamento frame = new Gerenciamento(); 
					

						frame.setVisible(true);	 
						Login.this.dispose();

					}
				} catch (PessoaJaCadastradaException e) {
					// Poss�vel exce��o que pode ser lan�ada ao iniciar o sistema
					// quem lan�a essa exce��o � o repositorio quando insere
					// o admin
					JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				} catch (PessoaNaoEncontradaException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);

				} catch (SenhaInvalidaException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);

				} catch (PessoaInvalidaException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				
			}
		});
		btnOk.setBounds(159, 203, 89, 23);
		contentPane.add(btnOk);

		JLabel lblBemVindoAo = new JLabel("Bem-vindo ao sistema de gerenciamento da Biblioteca CIn! ");
		lblBemVindoAo.setBounds(37, 36, 414, 14);
		contentPane.add(lblBemVindoAo);
	}

	public static Principal getFachada() {
		return fachada;
		
	}

}
