package escola.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;

import escola.excecoes.ElementoJaCadastradoException;
import escola.excecoes.EntradaInvalidaException;
import escola.excecoes.RepositorioException;
import escola.negocio.Controle;
import escola.principal.PaginaPrincipal;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadastrarTurmaFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textNome;
	private static boolean fechar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastrarTurmaFrame frame = new CadastrarTurmaFrame(fechar);
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
	public CadastrarTurmaFrame(final boolean fechar) {
		this.fechar = fechar;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCadastrarTurma = new JLabel("Cadastrar Turma");
		lblCadastrarTurma.setBounds(31, 33, 104, 16);
		contentPane.add(lblCadastrarTurma);
		
		JLabel lblDigiteONome = new JLabel("Digite o nome da turma:");
		lblDigiteONome.setBounds(31, 75, 166, 16);
		contentPane.add(lblDigiteONome);
		
		textNome = new JTextField();
		textNome.setBounds(190, 69, 134, 28);
		contentPane.add(textNome);
		textNome.setColumns(10);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cadastrar();
			}
		});
		btnCadastrar.setBounds(455, 356, 117, 48);
		contentPane.add(btnCadastrar);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				voltar();
			}
		});
		btnVoltar.setBounds(326, 356, 117, 48);
		contentPane.add(btnVoltar);
		
		JLabel lblOsAlunosQue = new JLabel("Os alunos que compoem esta turma poderao");
		lblOsAlunosQue.setBounds(26, 356, 288, 16);
		contentPane.add(lblOsAlunosQue);
		
		JLabel lblPoderaoSerEditados = new JLabel("ser editados no menu Editar Aluno");
		lblPoderaoSerEditados.setBounds(26, 373, 288, 16);
		contentPane.add(lblPoderaoSerEditados);
	}
	
	public void voltar(){
		if (!fechar){
		MenuPrincipal frame = new MenuPrincipal();
		frame.setVisible(true);
		System.out.println(fechar);
		}
		setVisible(false);
	}
	
	public void cadastrar(){
		String nome = textNome.getText();
		try {
			Controle.nomeValido(nome);
			PaginaPrincipal.fachada.inserirTurma(nome);
			JOptionPane.showMessageDialog(this, "Turma cadastrada com sucesso!");
		} catch (EntradaInvalidaException e) {
			JOptionPane.showMessageDialog(this, e.getOndeErrou());
		} catch (ElementoJaCadastradoException e) {
			JOptionPane.showMessageDialog(this, "Turma ja cadastrada. Tente novamente.");
		} catch (RepositorioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}