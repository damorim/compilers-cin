package escola.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GraphicsConfiguration;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import escola.classesBase.*;
import escola.dados.Repositorio;
import escola.dados.RepositorioArrayTurma;
import escola.excecoes.ElementoNaoEncontradoException;
import escola.excecoes.EntradaInvalidaException;
import escola.excecoes.RepositorioException;
import escola.principal.PaginaPrincipal;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Iterator;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class AlunosTurmaFrame extends JFrame {

	private JPanel contentPane;
	private JComboBox comboBox;
	private JTextField textField;
	private JTextArea textArea;
	private static Turma turma;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlunosTurmaFrame frame = new AlunosTurmaFrame(turma);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @param turma
	 */
	public AlunosTurmaFrame(final Turma turma) {
		this.turma = turma;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblSelecioneAOpcao = new JLabel("Selecione a opcao desejada:");
		lblSelecioneAOpcao.setBounds(304, 11, 268, 16);
		contentPane.add(lblSelecioneAOpcao);

		JButton btnRemover = new JButton("RemoverAlunos");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int opc = JOptionPane.showConfirmDialog(null,
						"Tem certeza que quer remover?");
				switch (opc) {
				case 0: // sim
					Iterator<Pessoa> it = turma.getAlunos().getIterator();
					while (it.hasNext()) {
						Pessoa aluno = it.next();
						try {
							PaginaPrincipal.fachada.removerPessoa(aluno
									.getCpf());
						} catch (RepositorioException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (EntradaInvalidaException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ElementoNaoEncontradoException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					JOptionPane.showMessageDialog(null,
							"Alunos deletados com sucesso!");
					voltar();
					break;
				case 1: // nao

					break;
				case 2: // cancelar
					break;
				}
			}
		});

		btnRemover.setBounds(38, 324, 146, 50);
		contentPane.add(btnRemover);

		comboBox = new JComboBox();
		comboBox.removeAllItems();
		Iterator<Turma> it = PaginaPrincipal.fachada.getTurmas().getIterator();
		while (it.hasNext()) {
			comboBox.addItem(it.next());
		}
		if (comboBox.getSelectedItem() != null) {
			textArea.setText(((Turma) comboBox.getSelectedItem()).resumo());
		}
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedItem() != null) {
					textArea.setText(((Turma) comboBox.getSelectedItem())
							.resumo());
				}
			}
		});

		comboBox.setBounds(38, 276, 460, 27);
		contentPane.add(comboBox);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				MenuPrincipal frame = new MenuPrincipal();
				frame.setVisible(true);
				setVisible(false);

			}
		});
		btnVoltar.setBounds(463, 360, 117, 50);
		contentPane.add(btnVoltar);

		JLabel lblSelecioneOTurma = new JLabel(
				"Os seguintes alunos pertenciam a turma excluida");
		lblSelecioneOTurma.setBounds(40, 11, 254, 16);
		contentPane.add(lblSelecioneOTurma);

		textField = new JTextField();
		textField.setBounds(40, 242, 321, 23);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comboBox.removeAll();
				String procura = textField.getText();
				RepositorioArrayTurma resultadoPesquisa = new RepositorioArrayTurma();
				try {
					resultadoPesquisa = PaginaPrincipal.fachada.getArrayTurma()
							.procurarNome(procura);
				} catch (ElementoNaoEncontradoException e1) {
					String aviso = "A pesquisa nao retornou resultados";
					JOptionPane.showInputDialog(this, aviso);
				}
				comboBox.removeAllItems();
				Iterator<Turma> it = resultadoPesquisa.iterator();
				while (it.hasNext()) {
					Turma turma = it.next();
					comboBox.addItem(turma);
				}
			}
		});
		btnPesquisar.setBounds(375, 242, 125, 23);
		contentPane.add(btnPesquisar);

		textArea = new JTextArea();
		textArea.setBounds(40, 35, 460, 191);
		contentPane.add(textArea);
		textArea.setEditable(false);

		JButton btnNewButton = new JButton("Mover para a  turma selecionada");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Turma turmaNova = (Turma) comboBox.getSelectedItem();
				Iterator<Pessoa> it;
				try {
					it = PaginaPrincipal.fachada.getTurmas()
							.procurar(turma.getNome()).getAlunos()
							.getIterator();
					while (it.hasNext()) {
						Aluno aluno = (Aluno) it.next();
						aluno.setTurma(turmaNova);
					}
					JOptionPane.showMessageDialog(null,
							"Alunos movidos com sucesso para a turma "
									+ turmaNova.getNome());
					voltar();
				} catch (ElementoNaoEncontradoException e1) {
					JOptionPane.showMessageDialog(null, "Aluno nao encontrado! ");
				}

			}
		});

		btnNewButton.setBounds(199, 324, 232, 50);
		contentPane.add(btnNewButton);

	}

	protected void voltar() {
		MenuPrincipal frame = new MenuPrincipal();
		frame.setVisible(true);
		setVisible(false);
	}

}
