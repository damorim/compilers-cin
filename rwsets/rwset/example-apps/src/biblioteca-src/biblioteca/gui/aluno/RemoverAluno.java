package biblioteca.gui.aluno;

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
import biblioteca.negocios.exceptions.pessoa.AlunoComEmprestimoException;
import biblioteca.negocios.exceptions.pessoa.PessoaInvalidaException;
import biblioteca.negocios.exceptions.pessoa.PessoaNaoEncontradaException;
import biblioteca.negocios.exceptions.pessoa.SenhaInvalidaException;

public class RemoverAluno extends JFrame {

	private JPanel contentPane;
	private JTextField tf_cpf;
	private static Principal fachada;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RemoverAluno frame = new RemoverAluno();
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
	public RemoverAluno() {
		fachada = Login.getFachada();
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 359, 221);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); 
		this.setResizable(false);
		this.setTitle("Remor��o Aluno");
		
		JLabel lblDigiteOCpf = new JLabel("Digite o CPF do aluno:");
		lblDigiteOCpf.setBounds(10, 21, 267, 14);
		contentPane.add(lblDigiteOCpf);
		
		JLabel lblCpf = new JLabel("CPF : ");
		lblCpf.setBounds(10, 53, 46, 14);
		contentPane.add(lblCpf);
		
		tf_cpf = new JTextField();
		tf_cpf.setBounds(54, 50, 86, 20);
		contentPane.add(tf_cpf);
		tf_cpf.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					if(tf_cpf.getText().equals("") || passwordField.getText().equals("")){
				
						JOptionPane.showMessageDialog(null, "Todos os campos s�o obrigat�rios!");
						
					}if(fachada.getSistemaLogado() == false){
						JOptionPane.showMessageDialog(null, "Para realizar esta a��o voc� deve est� logado no sistema");
						RemoverAluno.this.dispose();
						Login frame = new Login();
						frame.setVisible(true);
					}else{
					fachada.realizarExclusaoAluno(passwordField.getText());
					RemoverAluno.this.dispose();
					}
					
				} catch (PessoaNaoEncontradaException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				} catch (IOException ex) {
					// TODO Auto-generated catch block

				} catch (SenhaInvalidaException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				} catch (ConfirmarAcaoException ex) {
					
					final JInternalFrame internalFrame = new JInternalFrame("Confirma\u00E7\u00E3o");
		internalFrame.setBounds(182, 26, 161, 121);
		contentPane.add(internalFrame);
		internalFrame.getContentPane().setLayout(null);
		
		JLabel lblVocDeseja = new JLabel("Voc\u00EA deseja confirmar?");
		lblVocDeseja.setBounds(10, 11, 145, 14);
		internalFrame.getContentPane().add(lblVocDeseja);
		
		JRadioButton rdbtnSim = new JRadioButton("Sim");
		rdbtnSim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					fachada.confirmarExclusaoAluno(tf_cpf.getText());
					JOptionPane.showMessageDialog(null,"Aluno removido com sucesso!");
					RemoverAluno.this.dispose();
					
				} catch (PessoaNaoEncontradaException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				} catch (IOException e1) {
					// TODO Auto-generated catch block

				} catch (PessoaInvalidaException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
					
				} catch (AlunoComEmprestimoException ex) {
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
		rdbtnSim.setBounds(10, 32, 109, 23);
		internalFrame.getContentPane().add(rdbtnSim);
		
		JRadioButton rdbtnNao = new JRadioButton("Nao");
		rdbtnNao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RemoverAluno.this.dispose();
			}
		});
		rdbtnNao.setBounds(10, 61, 109, 23);
		internalFrame.getContentPane().add(rdbtnNao);
		internalFrame.setVisible(true);
					}
				
				
			}
		});
		btnOk.setBounds(41, 149, 89, 23);
		contentPane.add(btnOk);
		
		
		
		JLabel lblSenhoDoFuncionrio = new JLabel("Funcion\u00E1rio, digite sua senha:");
		lblSenhoDoFuncionrio.setBounds(10, 90, 172, 14);
		contentPane.add(lblSenhoDoFuncionrio);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(10, 118, 52, 14);
		contentPane.add(lblSenha);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(54, 112, 86, 20);
		contentPane.add(passwordField);
		
	}
}
