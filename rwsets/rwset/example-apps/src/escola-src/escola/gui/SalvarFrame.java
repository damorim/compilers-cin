package escola.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SalvarFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textPasta;
	private JTextField textArquivo;
	private static String relatorio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SalvarFrame frame = new SalvarFrame(relatorio);
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
	public SalvarFrame(String relatorio) {
		this.relatorio=relatorio;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSalvandoRelatrios = new JLabel("Salvando Relatorio");
		lblSalvandoRelatrios.setBounds(24, 26, 138, 16);
		contentPane.add(lblSalvandoRelatrios);
		
		JLabel lblDigiteONome = new JLabel("Digite o nome do diretorio em que deseja salvar:");
		lblDigiteONome.setBounds(24, 73, 319, 16);
		contentPane.add(lblDigiteONome);
		
		textPasta = new JTextField();
		textPasta.setBounds(338, 67, 166, 28);
		contentPane.add(textPasta);
		textPasta.setColumns(10);
		
		JLabel lblDigiteONome_1 = new JLabel("Digite o nome do arquivo:");
		lblDigiteONome_1.setBounds(24, 101, 178, 16);
		contentPane.add(lblDigiteONome_1);
		
		textArquivo = new JTextField();
		textArquivo.setBounds(196, 95, 189, 28);
		contentPane.add(textArquivo);
		textArquivo.setColumns(10);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvar();
			}
		});
		btnSalvar.setBounds(458, 360, 123, 50);
		contentPane.add(btnSalvar);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnVoltar.setBounds(329, 360, 123, 50);
		contentPane.add(btnVoltar);
		
		JLabel lbltxt = new JLabel(".txt");
		lbltxt.setBounds(384, 101, 61, 16);
		contentPane.add(lbltxt);
	}
	
	public void salvar(){
		
		String pasta="-";
		File dir = new File(textPasta.getText());  
		if (!dir.mkdir()) {  //se n der certo � pq ja tem
			int i=JOptionPane.showConfirmDialog(null, "Este diretorio ja existe. Deseja salvar nele?");
			switch(i){
			case 0: 
				pasta=textPasta.getText(); //sim
				break;
			case 1: //nao
				break;
			case 2: 
				setVisible(false);
				break;
			}
		} else { //se ainda n tem, cria
			pasta=textPasta.getText();
		}
		
		if(!pasta.equals("-")){
			
			FileWriter arquivo;
			
			System.out.println("\\" +pasta+"\\"+textArquivo.getText()+".txt");
	          
	        try {  
	            arquivo = new FileWriter(new File(pasta+"/"+textArquivo.getText()+".txt"));
	            //arquivo = new FileWriter(new File("\\"+pasta+"\\"+textArquivo.getText()+".txt"));
	            arquivo.write(relatorio);
	            arquivo.close();  
	            JOptionPane.showMessageDialog(null, "Relatorio salvo com sucesso!"); 
	        } catch (IOException e) {  
	          JOptionPane.showMessageDialog(null, "Erro no sistema. Desculpe-nos."); 
	        } catch (Exception e) {  
	        	JOptionPane.showMessageDialog(null, "Erro no sistema. Desculpe-nos."); 
	        }  
			
		}
		
		
	}
	
}
