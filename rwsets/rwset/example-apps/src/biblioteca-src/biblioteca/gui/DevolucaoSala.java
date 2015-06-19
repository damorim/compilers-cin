package biblioteca.gui;

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
import biblioteca.negocios.exceptions.ItemNaoEncontradoException;
import biblioteca.negocios.exceptions.RealizarLoginException;
import biblioteca.negocios.exceptions.pessoa.MultaGeradaException;
import biblioteca.negocios.exceptions.pessoa.PessoaInvalidaException;
import biblioteca.negocios.exceptions.pessoa.PessoaNaoEncontradaException;
import biblioteca.negocios.exceptions.sala.SalaNaoEncontradaException;

public class DevolucaoSala extends JFrame {

	private JPanel contentPane;
    private Principal fachada;
    private JTextField tf_codigo;
	private JTextField tf_cpf;
	private JPasswordField passwordField;
	/**
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DevolucaoSala frame = new DevolucaoSala();
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
	public DevolucaoSala() {
		setTitle("Devolu\u00E7\u00E3o Sala");
fachada = Login.getFachada();
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(189, 73, 245, 227);
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



		JLabel lblAlunoDigiteSeu = new JLabel("Caro aluno, digite seu CPF:");
		lblAlunoDigiteSeu.setBounds(10, 84, 219, 14);
		contentPane.add(lblAlunoDigiteSeu);

		JButton btnOk_2 = new JButton("OK");
		btnOk_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try{
					if(tf_codigo.getText().equals("") || tf_cpf.getText().equals("")){
						JOptionPane.showMessageDialog(null, "Todos os campos s�o obrigat�rios!");
						
					}if(fachada.getSistemaLogado() == false){
						JOptionPane.showMessageDialog(null, "Para realizar esta a��o voc� deve est� logado no sistema");
						DevolucaoSala.this.dispose();
						Login frame = new Login();
						frame.setVisible(true);
						
					}else{
						fachada.realizarDevolucaoSala(tf_cpf.getText(), tf_codigo.getText());
						JOptionPane.showMessageDialog(null,"A devolu��o da sala foi realizada com sucesso!");
						DevolucaoSala.this.dispose();

					}

				} catch (PessoaNaoEncontradaException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				} catch (IOException ex) {
					// tratar ERRO

				} catch (PessoaInvalidaException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				} catch (SalaNaoEncontradaException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				} catch (ItemNaoEncontradoException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				} catch (RealizarLoginException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				} catch (MultaGeradaException ex) {

DevolucaoSala.this.dispose();

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

					JRadioButton rdbtnSim_1 = new JRadioButton("Sim");
					rdbtnSim_1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {

							try {

								fachada.confirmarDevolucaoSala(true);
								JOptionPane.showMessageDialog(null, "Multa quitada com sucesso!");
								try { // Isto serve para fechar a janela
									internalFrameAux.setClosed(true);
								} catch (PropertyVetoException a) {
									// TODO Auto-generated catch block
									a.printStackTrace();
								}
							} catch (RealizarLoginException ex) {
								JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);


							}
						}
					});
					rdbtnSim_1.setBounds(76, 32, 109, 23);
					internalFrameAux.getContentPane().add(rdbtnSim_1);

					JRadioButton rdbtnNo_1 = new JRadioButton("N\u00E3o");
					rdbtnNo_1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try { // Isto serve para fechar a janela
								internalFrameAux.setClosed(true);
							} catch (PropertyVetoException a) {
								// TODO Auto-generated catch block
								a.printStackTrace();
							}
						}
					});
					rdbtnNo_1.setBounds(76, 58, 109, 23);
					internalFrameAux.getContentPane().add(rdbtnNo_1);




				}

			}
		});
		btnOk_2.setBounds(78, 158, 89, 23);
		contentPane.add(btnOk_2);
		

		
		
	}

}
