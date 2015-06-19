package biblioteca.gui.aluno;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import biblioteca.fachada.Principal;
import biblioteca.gui.Login;
import biblioteca.negocios.exceptions.RealizarLoginException;
import biblioteca.negocios.exceptions.pessoa.PessoaInvalidaException;
import biblioteca.negocios.exceptions.pessoa.PessoaNaoEncontradaException;

public class ProcurarAluno extends JFrame {

	private JPanel contentPane;
	private JTextField tf_cpf;
	private static Principal fachada;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProcurarAluno frame = new ProcurarAluno();
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
	public ProcurarAluno() {
		fachada = Login.getFachada();
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 273, 173);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); 
		this.setResizable(false);
		this.setTitle("Busca Aluno");
		
		JLabel lblDigiteOCpf = new JLabel("Digite o CPF do aluno:");
		lblDigiteOCpf.setBounds(10, 11, 267, 14);
		contentPane.add(lblDigiteOCpf);
		
		JLabel lblCpf = new JLabel("CPF : ");
		lblCpf.setBounds(59, 48, 46, 14);
		contentPane.add(lblCpf);
		
		tf_cpf = new JTextField();
		tf_cpf.setBounds(130, 45, 86, 20);
		contentPane.add(tf_cpf);
		tf_cpf.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					
					if(tf_cpf.getText().equals("")){
						JOptionPane.showMessageDialog(null, "O campo 'CPF' � obrigat�rio!");	
						
					}if(fachada.getSistemaLogado() == false){
						JOptionPane.showMessageDialog(null, "Para realizar esta a��o voc� deve est� logado no sistema");
						ProcurarAluno.this.dispose();
						Login frame = new Login();
						frame.setVisible(true);
						
					}else{
				
							JTextArea textArea = new JTextArea(6, 25);
						      
						      textArea.setText(fachada.autalizarPrintDadosAluno(tf_cpf.getText()));
						      textArea.setEditable(false);	     
						      JScrollPane scrollPane = new JScrollPane(textArea);      
			      JOptionPane.showMessageDialog(null, scrollPane, "Procurar",JOptionPane.INFORMATION_MESSAGE);
			      ProcurarAluno.this.dispose();
						
					
					}
						
				} catch (PessoaNaoEncontradaException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				} catch (IOException ex) {
					// TODO Auto-generated catch block

				} catch (PessoaInvalidaException ex) {

					JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				} catch (RealizarLoginException ex) {

					JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				}
				
				
				
				
				
				
			}
		});
		btnOk.setBounds(84, 98, 89, 23);
		
		contentPane.add(btnOk);
		
		
			
		
	}
}
