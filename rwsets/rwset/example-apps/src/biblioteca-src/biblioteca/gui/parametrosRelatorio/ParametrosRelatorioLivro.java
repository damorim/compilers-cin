package biblioteca.gui.parametrosRelatorio;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import biblioteca.fachada.Principal;
import biblioteca.gui.Login;
import biblioteca.gui.Relatorio;

public class ParametrosRelatorioLivro extends JFrame {

	private JPanel contentPane;
	private static Principal fachada;
	
	String textoRelatorio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ParametrosRelatorioLivro frame = new ParametrosRelatorioLivro();
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
	public ParametrosRelatorioLivro() {
		fachada = Login.getFachada();
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 361, 246);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); 
		this.setResizable(false);
		
		
		JLabel lblSelecioneOParmetro = new JLabel("Selecione o par\u00E2metro que deseja ordenar o relat\u00F3rio :");
		lblSelecioneOParmetro.setBounds(10, 22, 325, 14);
		contentPane.add(lblSelecioneOParmetro);
		
		final JRadioButton rdbtnNewRadioButtonCodigo = new JRadioButton("C\u00F3digo");
		rdbtnNewRadioButtonCodigo.setBounds(33, 54, 109, 23);
		contentPane.add(rdbtnNewRadioButtonCodigo);
		
		final JRadioButton rdbtnNewRadioButtonTitulo = new JRadioButton("T\u00EDtulo");
		rdbtnNewRadioButtonTitulo.setBounds(33, 80, 109, 23);
		contentPane.add(rdbtnNewRadioButtonTitulo);
		
		final JRadioButton rdbtnNewRadioButtonqtdAcervo = new JRadioButton("Quantidade no acervo");
		rdbtnNewRadioButtonqtdAcervo.setBounds(33, 106, 195, 23);
		contentPane.add(rdbtnNewRadioButtonqtdAcervo);
		
		final JRadioButton rdbtnTotalEmprestimos = new JRadioButton("Total de empr\u00E9stimos");
		rdbtnTotalEmprestimos.setBounds(33, 132, 195, 23);
		contentPane.add(rdbtnTotalEmprestimos);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnNewRadioButtonCodigo);
		group.add(rdbtnNewRadioButtonTitulo);
		group.add(rdbtnNewRadioButtonqtdAcervo);
		group.add(rdbtnTotalEmprestimos);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rdbtnNewRadioButtonCodigo.isSelected()){
					textoRelatorio = fachada.printRepositorioLivro();
					Relatorio frame = new Relatorio(textoRelatorio);
					frame.setVisible(true);
				}
				
				if(rdbtnNewRadioButtonTitulo.isSelected()){
					textoRelatorio = fachada.printRepositorioLivro();
					Relatorio frame = new Relatorio(textoRelatorio);
					frame.setVisible(true);
				}
				
				if(rdbtnNewRadioButtonqtdAcervo.isSelected()){
					textoRelatorio = fachada.printRepositorioLivro();
					Relatorio frame = new Relatorio(textoRelatorio);
					frame.setVisible(true);
				}
				
				if(rdbtnTotalEmprestimos.isSelected()){
					textoRelatorio = fachada.printRepositorioLivro();
					Relatorio frame = new Relatorio(textoRelatorio);
					frame.setVisible(true);
				}



				
				ParametrosRelatorioLivro.this.dispose();
			}
		});
		btnOk.setBounds(121, 174, 89, 23);
		contentPane.add(btnOk);
		
		
		
		
	}
}
