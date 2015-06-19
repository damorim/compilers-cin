package biblioteca.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.io.IOException;

import javax.swing.ButtonGroup;
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
import biblioteca.negocios.exceptions.ItemNaoEncontradoException;
import biblioteca.negocios.exceptions.RealizarLoginException;
import biblioteca.negocios.exceptions.livro.LivroNaoEncontradoException;
import biblioteca.negocios.exceptions.pessoa.MultaGeradaException;
import biblioteca.negocios.exceptions.pessoa.PessoaInvalidaException;
import biblioteca.negocios.exceptions.pessoa.PessoaNaoEncontradaException;

public class DevolucaoLivro extends JFrame {

	private JPanel contentPane;
	private Principal fachada;
	private JTextField tf_codigo;
	private JTextField tf_cpf;
	private JPasswordField passwordField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DevolucaoLivro frame = new DevolucaoLivro();
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
	public DevolucaoLivro() {
		setTitle("Devolu\u00E7\u00E3o Livro");
		fachada = Login.getFachada();

		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(189, 73, 245, 244);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setResizable(false);
		this.setLocation(654,260);

		JLabel lblDigiteOCdigo = new JLabel("Digite o c\u00F3digo do livro : ");
		lblDigiteOCdigo.setBounds(5, 11, 152, 14);
		contentPane.add(lblDigiteOCdigo);

		tf_codigo = new JTextField();
		tf_codigo.setBounds(108, 36, 86, 20);
		contentPane.add(tf_codigo);
		tf_codigo.setColumns(10);

		JLabel lblCdigo = new JLabel("C\u00F3digo : ");
		lblCdigo.setBounds(40, 36, 62, 14);
		contentPane.add(lblCdigo);
		JLabel lblCpfDoAluno = new JLabel("CPF do aluno : ");
		lblCpfDoAluno.setBounds(9, 115, 85, 14);
		contentPane.add(lblCpfDoAluno);

		tf_cpf = new JTextField();
		tf_cpf.setBounds(108, 112, 86, 20);
		contentPane.add(tf_cpf);
		tf_cpf.setColumns(10);

		JLabel lblSenhaDoAluno = new JLabel("Senha do aluno :");
		lblSenhaDoAluno.setBounds(5, 140, 104, 14);
		contentPane.add(lblSenhaDoAluno);

		passwordField = new JPasswordField();
		passwordField.setBounds(108, 137, 86, 20);
		contentPane.add(passwordField);

		JLabel lblAlunoDigiteSeu = new JLabel("Caro aluno, digite seu CPF e sua senha");
		lblAlunoDigiteSeu.setBounds(10, 84, 219, 14);
		contentPane.add(lblAlunoDigiteSeu);



		JButton btnOk_1 = new JButton("OK");
		btnOk_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					if(tf_codigo.getText().equals("") || tf_cpf.getText().equals("") || passwordField.getText().equals("")){
						JOptionPane.showMessageDialog(null, "Todos os campos s�o obrigat�rios!");
						
					}if(fachada.getSistemaLogado() == false){
						JOptionPane.showMessageDialog(null, "Para realizar esta a��o voc� deve est� logado no sistema");
						DevolucaoLivro.this.dispose();
						Login frame = new Login();
						frame.setVisible(true);
					}else{
						fachada.realizarDevolucaoLivro(tf_cpf.getText(), tf_codigo.getText());
						JOptionPane.showMessageDialog(null, "A devolu��o do livro foi realizada com sucesso!");
						DevolucaoLivro.this.dispose();
					}
				}
				catch (PessoaNaoEncontradaException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);

				} catch (IOException ex) {
					//

				} catch (PessoaInvalidaException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);

				} catch (LivroNaoEncontradoException exc) {
					JOptionPane.showMessageDialog(null, exc.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);

				} catch (ItemNaoEncontradoException exc) {
					JOptionPane.showMessageDialog(null, exc.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);

				} catch (RealizarLoginException exc) {

					JOptionPane.showMessageDialog(null, exc.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);	

				} catch (MultaGeradaException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);

					DevolucaoLivro.this.dispose();



					final JInternalFrame internalFrameAux = new JInternalFrame("Multa");
					internalFrameAux.setClosable(true);
					internalFrameAux.setBounds(199, 70, 225, 135);
					contentPane.add(internalFrameAux);
					internalFrameAux.getContentPane().setLayout(null);

					JLabel lblVocDesejaConfirmar = new JLabel("Voc\u00EA deseja realizar o pagamento");
					lblVocDesejaConfirmar.setBounds(10, 11, 199, 14);
					internalFrameAux.getContentPane().add(lblVocDesejaConfirmar);

					JLabel lblMulta = new JLabel(" da multa?");
					lblMulta.setBounds(10, 22, 91, 14);
					internalFrameAux.getContentPane().add(lblMulta);

					JRadioButton rdbtnSim = new JRadioButton("Sim");
					rdbtnSim.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try{
								fachada.confirmarDevolucaoLivro(true, tf_cpf.getText());
								JOptionPane.showMessageDialog(null, "Multa quitada com sucesso!");
								try { // Isto serve para fechar a janela
									internalFrameAux.setClosed(true);
								} catch (PropertyVetoException a) {
									// TODO Auto-generated catch block
									a.printStackTrace();
								}
							} catch (RealizarLoginException exe) {
								JOptionPane.showMessageDialog(null, exe.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);


							}

						}
					});
					rdbtnSim.setBounds(68, 32, 109, 23);
					internalFrameAux.getContentPane().add(rdbtnSim);

					JRadioButton rdbtnNo = new JRadioButton("N\u00E3o");
					rdbtnNo.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							try { // Isto serve para fechar a janela
								internalFrameAux.setClosed(true);
							} catch (PropertyVetoException a) {
								// TODO Auto-generated catch block
								a.printStackTrace();
							}
						}
					});
					rdbtnNo.setBounds(68, 58, 109, 23);
					internalFrameAux.getContentPane().add(rdbtnNo);
					internalFrameAux.setVisible(true);

					ButtonGroup group = new ButtonGroup();
					group.add(rdbtnSim);
					group.add(rdbtnNo);

	
				}


			}
		});
		btnOk_1.setBounds(68, 181, 89, 23);
		contentPane.add(btnOk_1);
	



}

}
