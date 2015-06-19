package biblioteca.gui.sala;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import biblioteca.negocios.exceptions.pessoa.SenhaInvalidaException;
import biblioteca.negocios.exceptions.sala.SalaComEmprestimoException;
import biblioteca.negocios.exceptions.sala.SalaNaoEncontradaException;

public class RemoverSala extends JFrame {

	private JPanel contentPane;
	private JTextField tf_codigo;
	private JLabel lblDigiteONmero;
	private static Principal fachada;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RemoverSala frame = new RemoverSala();
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
	public RemoverSala() {
		fachada = Login.getFachada();
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				setBounds(100, 100, 386, 225);
				contentPane = new JPanel();
				contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				setContentPane(contentPane);
				contentPane.setLayout(null);
				setLocationRelativeTo(null); 
				this.setResizable(false);
				this.setTitle("Remover sala");
				
				JLabel lblCodigo = new JLabel("C\u00F3digo : ");
				lblCodigo.setBounds(10, 36, 66, 14);
				contentPane.add(lblCodigo);
				
				tf_codigo = new JTextField();
				tf_codigo.setBounds(61, 33, 86, 20);
				contentPane.add(tf_codigo);
				tf_codigo.setColumns(10);
				
				JButton btnOk = new JButton("OK");
				btnOk.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						try {
							if(tf_codigo.getText().equals("") || passwordField.getText().equals("")){
								JOptionPane.showMessageDialog(null, "Todos os campos s�o obrigat�rios!");
								
							}if(fachada.getSistemaLogado() == false){
								JOptionPane.showMessageDialog(null, "Para realizar esta a��o voc� deve est� logado no sistema");
								RemoverSala.this.dispose();
								Login frame = new Login();
								frame.setVisible(true);
								
							}else{
							fachada.realizarExclusaoSala(tf_codigo.getText(), passwordField.getText());
							RemoverSala.this.dispose();
							}
						} catch (SenhaInvalidaException ex) {

							JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						
						} catch (ConfirmarAcaoException ex) {
					final JInternalFrame internalFrame = new JInternalFrame("Confirma\u00E7\u00E3o");
				internalFrame.setBounds(187, 22, 173, 125);
				contentPane.add(internalFrame);
				internalFrame.getContentPane().setLayout(null);
				
				JLabel lblVocDesejaConfirmar = new JLabel("Voc\u00EA deseja confirmar?");
				lblVocDesejaConfirmar.setBounds(10, 11, 137, 14);
				internalFrame.getContentPane().add(lblVocDesejaConfirmar);
				
				JRadioButton rdbtnSim = new JRadioButton("Sim");
				rdbtnSim.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							fachada.confirmarExclusaoSala(tf_codigo.getText());
							JOptionPane.showMessageDialog(null,"Sala removida com sucesso!");
							RemoverSala.this.dispose();
						} catch (SalaNaoEncontradaException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						} catch (IOException ex) {
							// TODO Auto-generated catch block

						} catch (SalaComEmprestimoException ex) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						
						
						} 
						
					}
				});
				rdbtnSim.setBounds(20, 32, 109, 23);
				internalFrame.getContentPane().add(rdbtnSim);
				
				JRadioButton rdbtnNo = new JRadioButton("N\u00E3o");
				rdbtnNo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						RemoverSala.this.dispose();
					}
				});
				rdbtnNo.setBounds(20, 64, 109, 23);
				internalFrame.getContentPane().add(rdbtnNo);
				internalFrame.setVisible(true);
						}
						
					}
				});
				btnOk.setBounds(46, 142, 89, 23);
				contentPane.add(btnOk);
				
				lblDigiteONmero = new JLabel("Digite o c\u00F3digo da sala : ");
				lblDigiteONmero.setBounds(10, 11, 301, 14);
				contentPane.add(lblDigiteONmero);
				
				JLabel lblFuncionrioDigiteSua = new JLabel("Funcion\u00E1rio, digite sua senha:");
				lblFuncionrioDigiteSua.setBounds(10, 82, 173, 14);
				contentPane.add(lblFuncionrioDigiteSua);
				
				JLabel lblSenha = new JLabel("Senha:");
				lblSenha.setBounds(10, 107, 46, 14);
				contentPane.add(lblSenha);
				
				passwordField = new JPasswordField();
				passwordField.setBounds(61, 104, 86, 20);
				contentPane.add(passwordField);
				
				
			}
		}
