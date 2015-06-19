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
public class ParametrosRelatorioAluno extends JFrame {

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
					ParametrosRelatorioAluno frame = new ParametrosRelatorioAluno();
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
	public ParametrosRelatorioAluno() {
		fachada = Login.getFachada();
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 356, 234);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		this.setResizable(false);
		
		JLabel lblSelecioneOParmetro = new JLabel("Selecione o par\u00E2metro que deseja ordenar o relat\u00F3rio : ");
		lblSelecioneOParmetro.setBounds(10, 26, 332, 14);
		contentPane.add(lblSelecioneOParmetro);
		
		final JRadioButton rdbtnCpf = new JRadioButton("CPF");
		rdbtnCpf.setBounds(22, 83, 109, 23);
		contentPane.add(rdbtnCpf);
		
		final JRadioButton rdbtnNome = new JRadioButton("Nome");
		rdbtnNome.setBounds(22, 57, 109, 23);
		contentPane.add(rdbtnNome);
		
		
		final JRadioButton rdbtnQuantidadeEmprestimos = new JRadioButton("Quantidade de empr\u00E9stimos");
		rdbtnQuantidadeEmprestimos.setBounds(22, 109, 201, 23);
		contentPane.add(rdbtnQuantidadeEmprestimos);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnCpf);
		group.add(rdbtnNome);
		group.add(rdbtnQuantidadeEmprestimos);
		
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnCpf.isSelected()){
					 
					textoRelatorio = fachada.printRepositorioAluno();
					Relatorio frame = new Relatorio(textoRelatorio);
					frame.setVisible(true);
					
				}if(rdbtnNome.isSelected()){
					textoRelatorio = fachada.printRepositorioAluno();
						Relatorio frame = new Relatorio(textoRelatorio );
						frame.setVisible(true);
						
				}if(rdbtnQuantidadeEmprestimos.isSelected()){
					textoRelatorio = fachada.printRepositorioAluno();
					Relatorio frame = new Relatorio(textoRelatorio );
					frame.setVisible(true);
					
				}
				//
				ParametrosRelatorioAluno.this.dispose();
			}
		});
		btnOk.setBounds(117, 150, 89, 23);
		contentPane.add(btnOk);
	}

}
