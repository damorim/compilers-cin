package biblioteca.gui;

import java.awt.EventQueue;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import biblioteca.fachada.Principal;
public class Relatorio extends JFrame {

	private JPanel contentPane;
	private static Principal fachada;
	private JTextField tf_nomeArquivo;
	private static String textoRelatorio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Relatorio frame = new Relatorio(textoRelatorio);
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
	public Relatorio(final String textoRelatorio) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 476, 345);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setResizable(false);
		setLocationRelativeTo(null); 
        this.setTitle("Relat�rio");
		JLabel lblDigiteONome = new JLabel("Digite o nome do arquivo que deseja salvar o relat\u00F3rio :");
		lblDigiteONome.setBounds(10, 11, 440, 17);
		contentPane.add(lblDigiteONome);

		JLabel lblNome = new JLabel("Nome do arquivo :");
		lblNome.setBounds(20, 39, 103, 14);
		contentPane.add(lblNome);

		tf_nomeArquivo = new JTextField();
		tf_nomeArquivo.setBounds(142, 36, 86, 20);
		contentPane.add(tf_nomeArquivo);
		tf_nomeArquivo.setColumns(10);



		TextArea textArea = new TextArea(textoRelatorio);
		textArea.setEditable(false);
		textArea.setBounds(31, 73, 405, 179);
		contentPane.add(textArea);




		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				

				JFileChooser fc = new JFileChooser();  

				// restringe a amostra a diretorios apenas  
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);  
				fc.setDialogTitle("Salvar");
				fc.setApproveButtonText("Salvar");
				

				int res = fc.showOpenDialog(null);  

				if(res == JFileChooser.APPROVE_OPTION){  
					File diretorio = fc.getSelectedFile();     
					String local = diretorio.getAbsolutePath();
					local = local +  File.separatorChar;

					String directoryName = local;

					String diretorioo = directoryName.replace('/', File.separatorChar);




					try {
						
						


						FileWriter fileR= new FileWriter(diretorioo+  tf_nomeArquivo.getText() +".txt");
						BufferedWriter buff = new BufferedWriter(fileR);
						buff.write(textoRelatorio);
						buff.close();
						JOptionPane.showMessageDialog(null,"Arquivo Salvo com sucesso");
						Relatorio.this.dispose();

					} catch (FileNotFoundException e1) {
						JOptionPane.showMessageDialog(null, "O nome do arquivo n�o pode conter os seguintes caracteres:   *  |  :  <  >  ?  ",  "Erro", JOptionPane.ERROR_MESSAGE);
						

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}




				}  
				else  
					JOptionPane.showMessageDialog(null, "Voce nao selecionou nenhum diretorio.");  






			}	    
		});
		btnOk.setBounds(190, 273, 89, 23);
		contentPane.add(btnOk);
	}
}



