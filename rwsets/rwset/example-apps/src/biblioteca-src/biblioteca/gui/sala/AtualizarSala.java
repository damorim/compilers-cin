package biblioteca.gui.sala;

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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import biblioteca.fachada.Principal;
import biblioteca.gui.Login;
import biblioteca.negocios.exceptions.RealizarLoginException;
import biblioteca.negocios.exceptions.pessoa.SenhaInvalidaException;
import biblioteca.negocios.exceptions.sala.SalaIndisponivelException;
import biblioteca.negocios.exceptions.sala.SalaInvalidaException;
import biblioteca.negocios.exceptions.sala.SalaNaoEncontradaException;

public class AtualizarSala extends JFrame {

	private JPanel contentPane;
	private JTextField tf_codigo;
	private static Principal fachada;
	private JRadioButton rdbtnSim;
	private JRadioButton rdbtnNo;
	private JTextField tf_capacidade;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AtualizarSala frame = new AtualizarSala();
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
	public AtualizarSala() {
		fachada = Login.getFachada();
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 413, 252);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); 
		this.setResizable(false);
		this.setTitle("Atualizar sala");

		JLabel lblcodigo = new JLabel("C\u00F3digo :  ");
		lblcodigo.setBounds(10, 36, 72, 14);
		contentPane.add(lblcodigo);

		JLabel lblDigiteOsDados = new JLabel("Digite o c\u00F3digo da sala:");
		lblDigiteOsDados.setBounds(24, 11, 150, 14);
		contentPane.add(lblDigiteOsDados);

		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					if(tf_codigo.equals("")){
						JOptionPane.showMessageDialog(null,"Todos os campos s�o obrigat�rios!");
						
					}if(fachada.getSistemaLogado() == false){
						JOptionPane.showMessageDialog(null, "Para realizar esta a��o voc� deve est� logado no sistema");
						AtualizarSala.this.dispose();
						Login frame = new Login();
						frame.setVisible(true);
						
					}else{
						JTextArea textArea = new JTextArea(6, 25);
					      
					      textArea.setText(fachada.atualizarPrintDadosSala(tf_codigo.getText()));
					      textArea.setEditable(false);	     
					      JScrollPane scrollPane = new JScrollPane(textArea);    
					      JOptionPane.showMessageDialog(null, scrollPane, "Atualiza��o",JOptionPane.INFORMATION_MESSAGE);
					      
					      
					      
						rdbtnSim.setEnabled(true);
						rdbtnNo.setEnabled(true);
					}
				}catch (SalaNaoEncontradaException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				} catch (IOException ex) {
					// TODO Auto-generated catch block




				}
			}
		});
		btnOk.setBounds(42, 61, 89, 23);
		contentPane.add(btnOk);

		tf_codigo = new JTextField();
		tf_codigo.setBounds(61, 33, 86, 20);
		contentPane.add(tf_codigo);
		tf_codigo.setColumns(10);

		JLabel lblVocDesejaAlterar = new JLabel("Voc\u00EA deseja alterar ");
		lblVocDesejaAlterar.setBounds(10, 101, 262, 14);
		contentPane.add(lblVocDesejaAlterar);

		rdbtnSim = new JRadioButton("Sim");
		rdbtnSim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				final JInternalFrame internalFrame = new JInternalFrame("Atualiza\u00E7\u00E3o");
				internalFrame.setBounds(184, 11, 203, 192);
				contentPane.add(internalFrame);
				internalFrame.getContentPane().setLayout(null);

				JLabel lblCapacidade = new JLabel("Capacidade:");
				lblCapacidade.setBounds(10, 101, 76, 14);
				internalFrame.getContentPane().add(lblCapacidade);

				tf_capacidade = new JTextField();
				tf_capacidade.setBounds(82, 98, 76, 20);
				internalFrame.getContentPane().add(tf_capacidade);
				tf_capacidade.setColumns(10);
				
				JLabel lblFuncionrioDigiteSua = new JLabel("Funcion\u00E1rio, digite sua senha:");
				lblFuncionrioDigiteSua.setBounds(10, 11, 177, 14);
				internalFrame.getContentPane().add(lblFuncionrioDigiteSua);
				
				JLabel lblSenha = new JLabel("Senha:");
				lblSenha.setBounds(10, 36, 46, 14);
				internalFrame.getContentPane().add(lblSenha);
				
				passwordField = new JPasswordField();
				passwordField.setBounds(80, 33, 78, 17);
				internalFrame.getContentPane().add(passwordField);
				
				JLabel lblDigiteACapacidade = new JLabel("Digite a capacidade da sala:");
				lblDigiteACapacidade.setBounds(10, 73, 167, 14);
				internalFrame.getContentPane().add(lblDigiteACapacidade);
				
				JButton btnOk_2 = new JButton("OK");
				btnOk_2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int capacity =  Integer.parseInt(tf_capacidade.getText());
						try {
							if(passwordField.getText().equals("") || tf_capacidade.getText().equals("")){
								JOptionPane.showMessageDialog(null, "Todos os campos s�o obrigat�rios!");
							}else{
							fachada.atualizarInfoSala(passwordField.getText(),capacity, 1);
							JOptionPane.showMessageDialog(null, "A capacidade da sala foi alterada com sucesso!");
							try { // Isto serve para fechar a janela
								internalFrame.setClosed(true);
							} catch (PropertyVetoException a) {
								// TODO Auto-generated catch block
								a.printStackTrace();
							}
							}
						} catch (SalaInvalidaException ex) {

							JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						} catch (RealizarLoginException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						} catch (SenhaInvalidaException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						} catch (SalaIndisponivelException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				btnOk_2.setBounds(54, 129, 89, 23);
				internalFrame.getContentPane().add(btnOk_2);
				
				
		internalFrame.setVisible(true);
		
		}
	});
		rdbtnSim.setEnabled(false);
		rdbtnSim.setBounds(10, 135, 109, 23);
		contentPane.add(rdbtnSim);

		rdbtnNo = new JRadioButton("N\u00E3o");
		rdbtnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AtualizarSala.this.dispose();
			}
		});
		rdbtnNo.setEnabled(false);
		rdbtnNo.setBounds(10, 161, 109, 23);
		contentPane.add(rdbtnNo);
		
JLabel lblACapacidadeDa = new JLabel("a capacidade da sala?");
				lblACapacidadeDa.setBounds(10, 114, 137, 14);
				contentPane.add(lblACapacidadeDa);

}

}
