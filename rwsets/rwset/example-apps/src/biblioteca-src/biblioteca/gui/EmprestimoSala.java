package biblioteca.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import biblioteca.negocios.exceptions.RealizarLoginException;
import biblioteca.negocios.exceptions.pessoa.PessoaInvalidaException;
import biblioteca.negocios.exceptions.pessoa.PessoaNaoEncontradaException;
import biblioteca.negocios.exceptions.pessoa.SenhaInvalidaException;
import biblioteca.negocios.exceptions.sala.SalaIndisponivelException;
import biblioteca.negocios.exceptions.sala.SalaNaoEncontradaException;

public class EmprestimoSala extends JFrame {

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
					EmprestimoSala frame = new EmprestimoSala();
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
	public EmprestimoSala() {
		setTitle("Empr\u00E9stimo Sala");
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



		JLabel lblDigiteOCdigo = new JLabel("Digite o c\u00F3digo da sala : ");
		lblDigiteOCdigo.setBounds(5, 11, 152, 14);
		contentPane.add(lblDigiteOCdigo);

		tf_codigo = new JTextField();
		tf_codigo.setBounds(108, 36, 86, 20);
		contentPane.add(tf_codigo);
		tf_codigo.setColumns(10);

		JLabel lblCdigo = new JLabel("C\u00F3digo : ");
		lblCdigo.setBounds(40, 36, 62, 14);
		contentPane.add(lblCdigo);

		JButton btnOk_1 = new JButton("OK");
		btnOk_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//...
				try {
					if(tf_codigo.getText().equals("") || tf_cpf.getText().equals("") || passwordField.getText().equals("")){
						JOptionPane.showMessageDialog(null, "Todos os campos s�o obrigat�rios!");
						
					}if(fachada.getSistemaLogado() == false){
						JOptionPane.showMessageDialog(null, "Para realizar esta a��o voc� deve est� logado no sistema");
						EmprestimoSala.this.dispose();
						Login frame = new Login();
						frame.setVisible(true);
						
					}else{
						fachada.realizarEmprestimoSala(tf_cpf.getText(),passwordField.getText(), tf_codigo.getText());
						JOptionPane.showMessageDialog(null, "Sala reservada com sucesso!");
						EmprestimoSala.this.dispose();
					}

				} catch (PessoaNaoEncontradaException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);

				} catch (IOException ex) {
					// tratar erro

				} catch (PessoaInvalidaException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);

				} catch (RealizarLoginException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);

				} catch (SenhaInvalidaException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);

				} catch (SalaNaoEncontradaException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);

				} catch (SalaIndisponivelException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				}





			}
		});
		btnOk_1.setBounds(68, 181, 89, 23);
		contentPane.add(btnOk_1);

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


	
}

}
