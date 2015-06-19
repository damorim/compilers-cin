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
public class ParametrosRelatorioSala extends JFrame {

	private JPanel contentPane;
	private static Principal fachada;
	private String textoRelatorio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ParametrosRelatorioSala frame = new ParametrosRelatorioSala();
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
	public ParametrosRelatorioSala() {
		fachada = Login.getFachada();
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 357, 239);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); 
		this.setResizable(false);
		
		JLabel lblSelecioneOParmetro = new JLabel("Selecione o par\u00E2metro que deseja ordenar o relat\u00F3rio: ");
		lblSelecioneOParmetro.setBounds(10, 21, 331, 14);
		contentPane.add(lblSelecioneOParmetro);
		
		final JRadioButton rdbtnCodigo = new JRadioButton("C\u00F3digo");
		rdbtnCodigo.setBounds(28, 53, 109, 23);
		contentPane.add(rdbtnCodigo);
		
		final JRadioButton rdbtnTotalReservas = new JRadioButton("Total de reservas");
		rdbtnTotalReservas.setBounds(28, 79, 142, 23);
		contentPane.add(rdbtnTotalReservas);
		
		final JRadioButton rdbtnCapacidade = new JRadioButton("Capacidade");
		rdbtnCapacidade.setBounds(28, 105, 109, 23);
		contentPane.add(rdbtnCapacidade);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnCodigo);
		group.add(rdbtnTotalReservas);
		group.add(rdbtnCapacidade);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(rdbtnCodigo.isSelected()){
					textoRelatorio = fachada.printRepositorioSala();
					Relatorio frame = new Relatorio(textoRelatorio);
					frame.setVisible(true);
				}
				
				if(rdbtnTotalReservas.isSelected()){
					textoRelatorio = fachada.printRepositorioSala();
					Relatorio frame = new Relatorio(textoRelatorio);
					frame.setVisible(true);
				}
				
				if(rdbtnCapacidade.isSelected()){
					textoRelatorio = fachada.printRepositorioSala();
					Relatorio frame = new Relatorio(textoRelatorio);
					frame.setVisible(true);
				}
				
				
				ParametrosRelatorioSala.this.dispose();
			}
		});
		btnOk.setBounds(121, 156, 89, 23);
		contentPane.add(btnOk);
	}
}
