package escola.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JButton;

import escola.classesBase.Disciplina;
import escola.classesBase.Professor;
import escola.dados.Repositorio;
import escola.excecoes.ElementoJaCadastradoException;
import escola.excecoes.ElementoNaoEncontradoException;
import escola.excecoes.RepositorioException;
import escola.principal.PaginaPrincipal;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Iterator;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class EditarProfessorDisciplinasFrame extends JFrame {

	private JPanel contentPane;
	private static Professor p;
	JTextArea textArea;
	final JComboBox comboBoxRemover;
	final JComboBox comboBoxAdicionar;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditarProfessorDisciplinasFrame frame = new EditarProfessorDisciplinasFrame(
							p);
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
	public EditarProfessorDisciplinasFrame(final Professor p) {
		this.p = p;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		final JLabel lblDisciplinasDoProfessor = new JLabel(
				"Selecione a disciplina a ser removida");
		lblDisciplinasDoProfessor.setBounds(25, 227, 244, 16);
		contentPane.add(lblDisciplinasDoProfessor);

		comboBoxRemover = new JComboBox();
		comboBoxRemover.setBounds(316, 222, 244, 27);
		contentPane.add(comboBoxRemover);

		comboBoxAdicionar = new JComboBox();
		comboBoxAdicionar.setBounds(316, 290, 244, 27);
		contentPane.add(comboBoxAdicionar);

		JLabel lblDisciplinasDaEscola = new JLabel(
				"Selecione a disciplina a ser adicionada");
		lblDisciplinasDaEscola.setBounds(25, 301, 244, 16);
		contentPane.add(lblDisciplinasDaEscola);

		JButton btnAdicionarSelecionada = new JButton("Adicionar Selecionada");
		btnAdicionarSelecionada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					PaginaPrincipal.fachada.adicionarDisciplinaProfessor(p,
							(Disciplina) comboBoxAdicionar.getSelectedItem());
					atualizarCampos();
					String aviso = "Disciplina adicionada com sucesso!";
					JOptionPane.showMessageDialog(null, aviso);
					} catch (ElementoJaCadastradoException e1) {
					JOptionPane.showMessageDialog(null, "Disciplina ja cadastrada!");
				}
			}
		});

		btnAdicionarSelecionada.setBounds(378, 323, 182, 29);
		contentPane.add(btnAdicionarSelecionada);

		JButton btnAdicionarNova = new JButton("Adicionar Nova");
		btnAdicionarNova.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastrarDisciplinaFrame frameDisciplina = new CadastrarDisciplinaFrame();
				frameDisciplina.setVisible(true);
				setVisible(false);
				atualizarCampos();
			}
		});
		btnAdicionarNova.setBounds(182, 323, 182, 29);
		contentPane.add(btnAdicionarNova);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditarProfessorFrame frameProfessor = new EditarProfessorFrame();
				frameProfessor.setVisible(true);
				setVisible(false);
			}
		});
		btnVoltar.setBounds(461, 363, 118, 44);
		contentPane.add(btnVoltar);

		final JButton btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PaginaPrincipal.fachada.removerDisciplinaProfessor(p,
						(Disciplina) comboBoxRemover.getSelectedItem());
				atualizarCampos();
				String aviso = "Disciplina removida com sucesso!";
				JOptionPane.showMessageDialog(null, aviso);
			}
		});
		btnRemover.setBounds(378, 250, 182, 29);
		contentPane.add(btnRemover);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 32, 526, 180);
		contentPane.add(scrollPane);

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);
		atualizarCampos();

		JLabel lblDisciplinasAtuais = new JLabel("Disciplinas Atuais:");
		lblDisciplinasAtuais.setBounds(34, 7, 128, 14);
		contentPane.add(lblDisciplinasAtuais);

		atualizarCampos();
	}

	public void atualizarCampos() {
		Iterator<Disciplina> it = p.getDisciplinas().getIterator();
		String texto = "";
		while (it.hasNext()) {
			texto = texto + it.next().toString() + "\n";
		}
		textArea.setText(texto);
		comboBoxAdicionar.removeAllItems();
		comboBoxRemover.removeAllItems();
		
		it = p.getDisciplinas().getIterator();
		while (it.hasNext()) {
		comboBoxRemover.addItem(it.next());
		}
		
		it = PaginaPrincipal.fachada.getDisciplinas().getIterator();
		while (it.hasNext()) {
		comboBoxAdicionar.addItem(it.next());
		}
	}
	

}
