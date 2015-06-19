package biblioteca.gui.opcoesGerenciamento;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import biblioteca.fachada.Principal;
import biblioteca.gui.Login;
import biblioteca.gui.aluno.AtualizarAluno;
import biblioteca.gui.funcionario.AtualizarFuncionario;
import biblioteca.gui.livro.AtualizarLivro;
import biblioteca.gui.sala.AtualizarSala;

public class OpcaoAtualizar extends JFrame {

	private JPanel contentPane;
private Principal fachada;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OpcaoAtualizar frame = new OpcaoAtualizar();
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
	public OpcaoAtualizar() {
		setTitle("Op\u00E7\u00E3o Atualizar");
fachada = Login.getFachada();
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(200, 42, 224, 209);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocation(666,260);
		
		JLabel lblOQueVoc = new JLabel("O que voc\u00EA deseja atualizar?");
		lblOQueVoc.setBounds(25, 24, 284, 14);
		contentPane.add(lblOQueVoc);
		
		JRadioButton rdbtnLivro = new JRadioButton("Livro");
		rdbtnLivro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AtualizarLivro frame = new AtualizarLivro();

				frame.setVisible(true);
				OpcaoAtualizar.this.dispose();
			}
		});
		rdbtnLivro.setBounds(35, 50, 109, 23);
		contentPane.add(rdbtnLivro);
		
		JRadioButton rdbtnAluno = new JRadioButton("Aluno");
		rdbtnAluno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AtualizarAluno frame = new AtualizarAluno();
				
				frame.setVisible(true);
				OpcaoAtualizar.this.dispose();
			}
		});
		rdbtnAluno.setBounds(35, 73, 109, 23);
		contentPane.add(rdbtnAluno);
		
		JRadioButton rdbtnFuncionrio = new JRadioButton("Funcion\u00E1rio");
		rdbtnFuncionrio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AtualizarFuncionario frame = new AtualizarFuncionario();
				
				frame.setVisible(true);
				OpcaoAtualizar.this.dispose();
			}
		});
		rdbtnFuncionrio.setBounds(35, 99, 109, 23);
		contentPane.add(rdbtnFuncionrio);
		
		JRadioButton rdbtnSala = new JRadioButton("Sala");
		rdbtnSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AtualizarSala frame = new AtualizarSala();
				frame.setVisible(true);
				OpcaoAtualizar.this.dispose();
			}
		});
		rdbtnSala.setBounds(35, 125, 109, 23);
		contentPane.add(rdbtnSala);
		this.setResizable(false);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnLivro);
		group.add(rdbtnAluno);
		group.add(rdbtnFuncionrio);
		group.add(rdbtnSala);
	}

}
