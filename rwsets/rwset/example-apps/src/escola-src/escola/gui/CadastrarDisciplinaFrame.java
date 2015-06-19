package escola.gui;

//import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

//import javax.swing.SwingConstants;


import escola.excecoes.ElementoJaCadastradoException;
import escola.excecoes.EntradaInvalidaException;
import escola.excecoes.RepositorioException;
import escola.negocio.Controle;
import escola.principal.PaginaPrincipal;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class CadastrarDisciplinaFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textNome;
	private JTextArea textEmenta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastrarDisciplinaFrame frame = new CadastrarDisciplinaFrame();
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
	public CadastrarDisciplinaFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCadastrarDisciplina = new JLabel("Cadastrar Disciplina");
		lblCadastrarDisciplina.setBounds(28, 31, 137, 16);
		contentPane.add(lblCadastrarDisciplina);
		
		JLabel lblDigiteONome = new JLabel("Digite o nome da disciplina:");
		lblDigiteONome.setBounds(28, 71, 223, 16);
		contentPane.add(lblDigiteONome);
		
		textNome = new JTextField();
		textNome.setBounds(213, 65, 144, 28);
		contentPane.add(textNome);
		textNome.setColumns(10);
		
		JLabel lblDigiteAEmenta = new JLabel("Digite a ementa:");
		lblDigiteAEmenta.setBounds(28, 102, 137, 16);
		contentPane.add(lblDigiteAEmenta);
		
	 textEmenta = new JTextArea();
		textEmenta.setBounds(138, 102, 230, 77);
		contentPane.add(textEmenta);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cadastrar();
			}
		});
		btnCadastrar.setBounds(462, 357, 117, 48);
		contentPane.add(btnCadastrar);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				voltar();
			}
		});
		btnVoltar.setBounds(333, 357, 117, 48);
		contentPane.add(btnVoltar);
		
		JLabel lblOsProfessoresQue = new JLabel("Os professores que lecionam esta disciplina");
		lblOsProfessoresQue.setBounds(28, 357, 298, 33);
		contentPane.add(lblOsProfessoresQue);
		
		JLabel lblPoderoSerEditados = new JLabel("poderao ser editados no menu Editar Professor");
		lblPoderoSerEditados.setBounds(28, 386, 298, 16);
		contentPane.add(lblPoderoSerEditados);
		
	}
	public void voltar(){
		MenuPrincipal frame = new MenuPrincipal();
		frame.setVisible(true);
		setVisible(false);
	}
	
	public void cadastrar(){
		String nome = textNome.getText();
		String ementa = textEmenta.getText();
		try {
			Controle.nomeValido(nome);
			PaginaPrincipal.fachada.inserirDisciplina(nome, ementa);
			JOptionPane.showMessageDialog(this, "Disciplina cadastrada com sucesso!");
			voltar();
		} catch (EntradaInvalidaException e) {
			JOptionPane.showMessageDialog(this, "Entrada invalida. Tente novamente.\n"+e.getOndeErrou());
		} catch (ElementoJaCadastradoException e) {
			JOptionPane.showMessageDialog(this, "Disciplina ja cadastrada. Tente novamente.");
		} catch (RepositorioException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}
}
