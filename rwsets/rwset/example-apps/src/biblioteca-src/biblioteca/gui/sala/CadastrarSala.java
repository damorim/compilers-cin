package biblioteca.gui.sala;

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
import biblioteca.negocios.exceptions.sala.SalaInvalidaException;
import biblioteca.negocios.exceptions.sala.SalaJaCadastradaException;

public class CadastrarSala extends JFrame {

	private JPanel contentPane;
	private JTextField tf_codigo;
	private JTextField tf_capacidade;
	private static Principal fachada;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastrarSala frame = new CadastrarSala();
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
	public CadastrarSala() {
		fachada = Login.getFachada();
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 328, 182);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); 
		this.setResizable(false);
		this.setTitle("Cadastro sala");

		JLabel lblcodigo = new JLabel("C\u00F3digo :  ");
		lblcodigo.setBounds(61, 49, 72, 14);
		contentPane.add(lblcodigo);

		JLabel lblDigiteOsDados = new JLabel("Digite os dados da sala : ");
		lblDigiteOsDados.setBounds(36, 11, 278, 14);
		contentPane.add(lblDigiteOsDados);

		JLabel lblCapacidade = new JLabel("Capacidade : ");
		lblCapacidade.setBounds(61, 73, 87, 14);
		contentPane.add(lblCapacidade);

		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String codigo = tf_codigo.getText();
					String cap = tf_capacidade.getText();
					if(codigo.equals("")  || cap.equals("")){
						JOptionPane.showMessageDialog(null,"Todos os campos s�o obrigat�rios!");
						
					}if(fachada.getSistemaLogado() == false){
						JOptionPane.showMessageDialog(null, "Para realizar esta a��o voc� deve est� logado no sistema");
						CadastrarSala.this.dispose();
						Login frame = new Login();
						frame.setVisible(true);
						
					}else{
			
					int capacidade = Integer.parseInt(cap);
					
					//System.out.println("codigo:" + codigo + "capacidade:" + capacidade);
					fachada.cadastrarSala(codigo, capacidade);
					JOptionPane.showMessageDialog(null, "Sala cadastrada com sucesso!");
					CadastrarSala.this.dispose();
					}
				} catch (SalaInvalidaException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
					
				} catch (SalaJaCadastradaException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
					
				} catch (IOException ex) {
					ex.printStackTrace();
					
				}
				
			}
		});
		btnOk.setBounds(123, 110, 89, 23);
		contentPane.add(btnOk);

		tf_codigo = new JTextField();
		tf_codigo.setBounds(140, 46, 86, 20);
		contentPane.add(tf_codigo);
		tf_codigo.setColumns(10);

		tf_capacidade = new JTextField();
		tf_capacidade.setBounds(140, 70, 86, 20);
		contentPane.add(tf_capacidade);
		tf_capacidade.setColumns(10);
	}

}
