package biblioteca.gui.livro;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import biblioteca.fachada.Principal;
import biblioteca.gui.Login;
import biblioteca.negocios.exceptions.ConfirmarAcaoException;
import biblioteca.negocios.exceptions.RealizarLoginException;
import biblioteca.negocios.exceptions.livro.LivroComEmprestimoException;
import biblioteca.negocios.exceptions.livro.LivroNaoEncontradoException;
import biblioteca.negocios.exceptions.pessoa.SenhaInvalidaException;

public class RemoverLivro extends JFrame {

	private JPanel contentPane;
	private JTextField tf_codigo;
	private static Principal fachada;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RemoverLivro frame = new RemoverLivro();
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
	public RemoverLivro() {
		fachada = Login.getFachada();
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 365, 231);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); 
		this.setResizable(false);
		this.setTitle("Remover Livro");
		
		JLabel lblDigiteOCdigo = new JLabel("Digite o c\u00F3digo do livro: ");
		lblDigiteOCdigo.setBounds(10, 11, 283, 14);
		contentPane.add(lblDigiteOCdigo);
		
		JLabel lblCdigo = new JLabel("C\u00F3digo : ");
		lblCdigo.setBounds(10, 47, 71, 14);
		contentPane.add(lblCdigo);
		
		tf_codigo = new JTextField();
		tf_codigo.setBounds(71, 41, 86, 20);
		contentPane.add(tf_codigo);
		tf_codigo.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(tf_codigo.getText().equals("")|| passwordField.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Todos os campos s�o obrigat�rios!");
					
					}if(fachada.getSistemaLogado() == false){
						JOptionPane.showMessageDialog(null, "Para realizar esta a��o voc� deve est� logado no sistema");
						RemoverLivro.this.dispose();
						Login frame = new Login();
						frame.setVisible(true);
						
					}else{
					fachada.realizarExclusaoLivro(passwordField.getText());
					RemoverLivro.this.dispose();
					}
				} catch (SenhaInvalidaException ex) {
					System.out.println(ex.getMessage());
				} catch (ConfirmarAcaoException ex) {
				
			///... implementa�ao do m�todo 
				final JInternalFrame internalFrame = new JInternalFrame("Confirma\u00E7\u00E3o");
		internalFrame.setBounds(186, 25, 163, 131);
		contentPane.add(internalFrame);
		internalFrame.getContentPane().setLayout(null);
		
		JRadioButton rdbtnSim = new JRadioButton("Sim");
		rdbtnSim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					fachada.confirmarExclusaoLivro(tf_codigo.getText());
					JOptionPane.showMessageDialog(null,"Livro removido com sucesso!");
					RemoverLivro.this.dispose();
				} catch (LivroNaoEncontradoException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				} catch (IOException e1) {
					// TODO Auto-generated catch block

				} catch (RealizarLoginException ex) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				} catch (LivroComEmprestimoException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				}
				try { // Isto serve para fechar a janela
					internalFrame.setClosed(true);
				} catch (PropertyVetoException a) {
					// TODO Auto-generated catch block
					a.printStackTrace();
				}
				
			}
		});
		rdbtnSim.setBounds(22, 34, 109, 23);
		internalFrame.getContentPane().add(rdbtnSim);
		
		JRadioButton rdbtnNo = new JRadioButton("N\u00E3o");
		rdbtnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RemoverLivro.this.dispose();
			}
		});
		rdbtnNo.setBounds(22, 60, 109, 23);
		internalFrame.getContentPane().add(rdbtnNo);
		
		JLabel lblVocDesejaConfirmar = new JLabel("Voc\u00EA deseja confirmar? ");
		lblVocDesejaConfirmar.setBounds(10, 11, 147, 14);
		internalFrame.getContentPane().add(lblVocDesejaConfirmar);
		internalFrame.setVisible(true);
					
					
					
					
				}

			}
		});
		btnOk.setBounds(41, 156, 89, 23);
		contentPane.add(btnOk);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(10, 121, 46, 14);
		contentPane.add(lblSenha);
		
		JLabel lblFuncionrioDigiteSua = new JLabel("Funcion\u00E1rio, digite sua senha:");
		lblFuncionrioDigiteSua.setBounds(10, 87, 175, 14);
		contentPane.add(lblFuncionrioDigiteSua);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(71, 118, 86, 20);
		contentPane.add(passwordField);
		
		
		
		
	}

	
			
		
}
