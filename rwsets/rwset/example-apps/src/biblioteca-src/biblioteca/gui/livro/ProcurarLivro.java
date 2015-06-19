package biblioteca.gui.livro;

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
import biblioteca.negocios.exceptions.livro.LivroNaoEncontradoException;

public class ProcurarLivro extends JFrame {

	private JPanel contentPane;
	private JTextField tf_codigo;
	private static Principal fachada;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProcurarLivro frame = new ProcurarLivro();
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
	public ProcurarLivro() {
		
		fachada = Login.getFachada();
		
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 336, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); 
		this.setResizable(false);
		this.setTitle("Busca Livro");
		
		JLabel lblDigiteOCdigo = new JLabel("Digite o c\u00F3digo do livro: ");
		lblDigiteOCdigo.setBounds(10, 11, 283, 14);
		contentPane.add(lblDigiteOCdigo);
		
		JLabel lblCdigo = new JLabel("C\u00F3digo : ");
		lblCdigo.setBounds(85, 58, 71, 14);
		contentPane.add(lblCdigo);
		
		tf_codigo = new JTextField();
		tf_codigo.setBounds(153, 55, 86, 20);
		contentPane.add(tf_codigo);
		tf_codigo.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					if(tf_codigo.getText().equals("")){
						JOptionPane.showMessageDialog(null,"O campo 'C�digo' � obrigat�rio!");
					}if(fachada.getSistemaLogado() == false){
						JOptionPane.showMessageDialog(null, "Para realizar esta a��o voc� deve est� logado no sistema");
						ProcurarLivro.this.dispose();
						Login frame = new Login();
						frame.setVisible(true);
					}else{
						JTextArea textArea = new JTextArea(6, 25);
			      
			      textArea.setText(fachada.atualizarPrintDadosLivro(tf_codigo.getText()));
			      textArea.setEditable(false);	     
			      JScrollPane scrollPane = new JScrollPane(textArea);    
			      JOptionPane.showMessageDialog(null, scrollPane, "Procurar",JOptionPane.INFORMATION_MESSAGE);
				
				ProcurarLivro.this.dispose();
					}
				} catch (LivroNaoEncontradoException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				} catch (IOException ex) {
					// TODO Auto-generated catch block

				}
				
				
				}
			
		});
		btnOk.setBounds(114, 114, 89, 23);
		contentPane.add(btnOk);
	}

}
