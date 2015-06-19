package escola.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JButton;

import escola.classesBase.Professor;
import escola.classesBase.Turma;
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

public class EditarProfessorTurmasFrame extends JFrame {

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
					EditarProfessorTurmasFrame frame = new EditarProfessorTurmasFrame(
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
	public EditarProfessorTurmasFrame(final Professor p) {
		this.p = p;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		final JLabel lblTurmasDoProfessor = new JLabel(
				"Selecione a turma a ser removida");
		lblTurmasDoProfessor.setBounds(25, 227, 244, 16);
		contentPane.add(lblTurmasDoProfessor);

		comboBoxRemover = new JComboBox();
		comboBoxRemover.setBounds(316, 222, 244, 27);
		contentPane.add(comboBoxRemover);

		comboBoxAdicionar = new JComboBox();
		comboBoxAdicionar.setBounds(316, 290, 244, 27);
		contentPane.add(comboBoxAdicionar);

		JLabel lblTurmasDaEscola = new JLabel(
				"Selecione a turma a ser adicionada");
		lblTurmasDaEscola.setBounds(25, 301, 244, 16);
		contentPane.add(lblTurmasDaEscola);

		JButton btnAdicionarSelecionada = new JButton("Adicionar Selecionada");
		btnAdicionarSelecionada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					PaginaPrincipal.fachada.adicionarTurmaProfessor(p,
							(Turma) comboBoxAdicionar.getSelectedItem());
					atualizarCampos();
					String aviso = "Turma adicionada com sucesso!";
					JOptionPane.showMessageDialog(null, aviso);
					} catch (ElementoJaCadastradoException e1) {
					JOptionPane.showMessageDialog(null, "Turma ja cadastrada!");
				}
			}
		});

		btnAdicionarSelecionada.setBounds(378, 323, 182, 29);
		contentPane.add(btnAdicionarSelecionada);

		JButton btnAdicionarNova = new JButton("Adicionar Nova");
		btnAdicionarNova.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastrarTurmaFrame frameTurma = new CadastrarTurmaFrame(true);
				frameTurma.setVisible(true);
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
				try {
					PaginaPrincipal.fachada.removerTurmaProfessor(p,
							(Turma) comboBoxRemover.getSelectedItem());
					atualizarCampos();
					String aviso = "Turma removida com sucesso!";
					JOptionPane.showMessageDialog(null, aviso);
					} catch (RepositorioException e1) {
					JOptionPane.showMessageDialog(null,
							"Desculpe-nos, ocorreu um erro no sistema. ):");
				} catch (ElementoNaoEncontradoException e1) {
					JOptionPane
							.showMessageDialog(null, "Turma nao encontrada.");
				}
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

		JLabel lblTurmasAtuais = new JLabel("Turmas Atuais:");
		lblTurmasAtuais.setBounds(34, 7, 128, 14);
		contentPane.add(lblTurmasAtuais);

		atualizarCampos();
	}

	public void atualizarCampos() {
		Iterator<Turma> it = p.getTurmas().getIterator();
		String texto = "";
		while (it.hasNext()) {
			texto = texto + it.next().toString() + "\n";
		}
		textArea.setText(texto);
		comboBoxAdicionar.removeAllItems();
		comboBoxRemover.removeAllItems();
		
		it = p.getTurmas().getIterator();
		while (it.hasNext()) {
		comboBoxRemover.addItem(it.next());
		}
		
		it = PaginaPrincipal.fachada.getTurmas().getIterator();
		while (it.hasNext()) {
		comboBoxAdicionar.addItem(it.next());
		}
	}
	

}
