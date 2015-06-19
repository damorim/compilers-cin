package biblioteca.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import biblioteca.fachada.Principal;
import biblioteca.gui.opcoesGerenciamento.OpcaoAtualizar;
import biblioteca.gui.opcoesGerenciamento.OpcaoCadastro;
import biblioteca.gui.opcoesGerenciamento.OpcaoProcurar;
import biblioteca.gui.opcoesGerenciamento.OpcaoRemover;
import biblioteca.gui.parametrosRelatorio.ParametrosRelatorioAluno;
import biblioteca.gui.parametrosRelatorio.ParametrosRelatorioLivro;
import biblioteca.gui.parametrosRelatorio.ParametrosRelatorioSala;

public class Gerenciamento extends JFrame{

	private JPanel contentPane;
	private JTextField tf_codigo;
	private JTextField tf_cpf;
	private JPasswordField passwordField;
	private Principal fachada;
	private JTextField textField_2;
	private JInternalFrame internalFrame4;
	private JInternalFrame internalFrame2;
	private JInternalFrame internalFrame3;

	private JInternalFrame internalFrame5;
	private JInternalFrame internalFrame6;
	private JInternalFrame internalFrame7;
	private JInternalFrame internalFrame8;
	private JInternalFrame internalFrame9;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gerenciamento frame = new Gerenciamento();
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
	public Gerenciamento() {
		fachada = Login.getFachada();
		


		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 395);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setResizable(false);

		this.setTitle("Gerenciamento");
		setLocationRelativeTo(null);  // Colocar o JFrame no meio da tela
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//fechar todos ou outros internalFrames para eles nao se sobreporem...


				OpcaoCadastro frame = new OpcaoCadastro();
				frame.setVisible(true);

			}
		});

		btnCadastrar.setBounds(20, 31, 159, 23);
		contentPane.add(btnCadastrar);

		JButton btnNewButton = new JButton("Empr\u00E9stimo de livro");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				EmprestimoLivro frame = new EmprestimoLivro();
				frame.setVisible(true);


			}
		});
		btnNewButton.setBounds(20, 167, 159, 23);
		contentPane.add(btnNewButton);

		JButton btnDevoluoDeLivro = new JButton("Devolu\u00E7\u00E3o de livro");
		btnDevoluoDeLivro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DevolucaoLivro frame = new DevolucaoLivro();
				frame.setVisible(true);

			}
		});
		btnDevoluoDeLivro.setBounds(20, 201, 159, 23);
		contentPane.add(btnDevoluoDeLivro);
		
		
		
		
		

		JButton btnReservarSala = new JButton("Reservar sala");
		btnReservarSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				EmprestimoSala frame = new EmprestimoSala();
				frame.setVisible(true);


			}
		});

		btnReservarSala.setBounds(20, 235, 159, 23);
		contentPane.add(btnReservarSala);

		JButton btnGerarRelatrio = new JButton("Gerar relat\u00F3rio");
		btnGerarRelatrio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JInternalFrame internalFrame5 = new JInternalFrame("Salvar relat\u00F3rio");
				internalFrame5.setClosable(true);
				internalFrame5.setBounds(189, 84, 235, 210);
				contentPane.add(internalFrame5);
				internalFrame5.getContentPane().setLayout(null);
				internalFrame5.setOpaque(true);

				final JRadioButton rdbtnLivro = new JRadioButton("Livro ");
				rdbtnLivro.setBounds(55, 29, 109, 23);
				internalFrame5.getContentPane().add(rdbtnLivro);

				final JRadioButton rdbtnAluno = new JRadioButton("Aluno");
				rdbtnAluno.setBounds(55, 55, 109, 23);
				internalFrame5.getContentPane().add(rdbtnAluno);

				final JRadioButton rdbtnSala = new JRadioButton("Sala");
				rdbtnSala.setBounds(55, 81, 109, 23);
				internalFrame5.getContentPane().add(rdbtnSala);



				ButtonGroup group = new ButtonGroup();
				group.add(rdbtnLivro);
				group.add(rdbtnAluno);

				group.add(rdbtnSala);

				JLabel lblQualORelatrio = new JLabel("Selecione o relat\u00F3rio desejado: ");
				lblQualORelatrio.setBounds(10, 8, 188, 14);
				internalFrame5.getContentPane().add(lblQualORelatrio);

				JButton btnOk_3 = new JButton("OK");
				btnOk_3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (rdbtnLivro.isSelected()) {
							ParametrosRelatorioLivro frame = new ParametrosRelatorioLivro();
							frame.setVisible(true);
						}

						if (rdbtnAluno.isSelected()){
							ParametrosRelatorioAluno frame = new ParametrosRelatorioAluno();
							frame.setVisible(true);
						}

						if(rdbtnSala.isSelected()){
							ParametrosRelatorioSala frame = new ParametrosRelatorioSala();
							frame.setVisible(true);
						}

						try { // Isto serve para fechar a janela
							internalFrame5.setClosed(true);
						} catch (PropertyVetoException a) {
							// TODO Auto-generated catch block
							a.printStackTrace();
						}

					}
				});
				btnOk_3.setBounds(75,120, 89, 23);
				internalFrame5.getContentPane().add(btnOk_3);
				internalFrame5.setVisible(true);
			}
		});
		btnGerarRelatrio.setBounds(20, 303, 159, 23);
		contentPane.add(btnGerarRelatrio);

		JButton btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				OpcaoRemover frame = new OpcaoRemover();
				frame.setVisible(true);


			}
		});
		btnRemover.setBounds(20, 99, 159, 23);
		contentPane.add(btnRemover);

		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OpcaoAtualizar frame = new OpcaoAtualizar();
				frame.setVisible(true);
			}
		});
		btnAtualizar.setBounds(20, 133, 159, 23);
		contentPane.add(btnAtualizar);




		JButton btnProcurar = new JButton("Procurar");
		btnProcurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				OpcaoProcurar frame = new OpcaoProcurar();
				frame.setVisible(true);
			}
		});
		btnProcurar.setBounds(20, 65, 159, 23);
		contentPane.add(btnProcurar);




		JButton btnDevoluoDeSala = new JButton("Devolu\u00E7\u00E3o de sala");
		btnDevoluoDeSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				DevolucaoSala frame = new DevolucaoSala();
				frame.setVisible(true);
					

			}
		});
		btnDevoluoDeSala.setBounds(20, 269, 159, 23);
		contentPane.add(btnDevoluoDeSala);
		////////////////////










	}
}
