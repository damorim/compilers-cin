package escola.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import escola.classesBase.*;
import escola.dados.RepositorioArrayDisciplina;
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
public class RemoverDisciplinaFrame extends JFrame {

	private JPanel contentPane;
	private JComboBox comboBox;
	private JTextField textField;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RemoverDisciplinaFrame frame = new RemoverDisciplinaFrame();
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
	public RemoverDisciplinaFrame() {
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 114, 462, 148);
		contentPane.add(scrollPane);

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);

		JLabel lblSelecioneAOpcao = new JLabel("Selecione a opcao desejada:");
		lblSelecioneAOpcao.setBounds(40, 271, 268, 16);
		contentPane.add(lblSelecioneAOpcao);

		JButton btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int opc = JOptionPane.showConfirmDialog(null, "Tem certeza que quer remover?");
				switch(opc){
				case 0: //sim
					try {
						Disciplina d= (Disciplina) comboBox.getSelectedItem();
						try {
							PaginaPrincipal.fachada.removerDisciplina(d.getNome());
						} catch (RepositorioException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (EntradaInvalidaException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						JOptionPane.showMessageDialog(RemoverDisciplinaFrame.this,"Disciplina removida com sucesso.");
					} catch (ElementoNaoEncontradoException e1) {
					}
					voltar();		
					break;
				case 1: //nao
					
					break;
				case 2: //cancelar
					break;
				}
			}
		});
		btnRemover.setBounds(40, 299, 146, 50);
		contentPane.add(btnRemover);

		comboBox = new JComboBox();
		comboBox.removeAllItems();
		Iterator<Disciplina> it = PaginaPrincipal.fachada.getArrayDisciplina().getIterator();
		while(it.hasNext()){
			comboBox.addItem(it.next());
		}
		if (comboBox.getSelectedItem() != null) {
			textArea.setText(((Disciplina) comboBox.getSelectedItem())
					.resumo());
		}
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedItem() != null) {
					textArea.setText(((Disciplina) comboBox.getSelectedItem())
							.resumo());
				}
			}
		});

		comboBox.setBounds(40, 76, 462, 27);
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

		JLabel lblSelecioneODisciplina = new JLabel(
				"Selecione o disciplina a ser editado:");
		lblSelecioneODisciplina.setBounds(40, 30, 254, 16);
		contentPane.add(lblSelecioneODisciplina);

		textField = new JTextField();
		textField.setBounds(40, 50, 321, 23);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String procura = textField.getText();
				RepositorioArrayDisciplina resultadoPesquisa = new RepositorioArrayDisciplina();
				try {
					resultadoPesquisa =PaginaPrincipal.fachada.getArrayDisciplina().procurarNome(procura);
				} catch (ElementoNaoEncontradoException e1) {
					String aviso = "A pesquisa nao retornou resultados";
					JOptionPane.showMessageDialog(null, aviso);
				}
				comboBox.removeAllItems();
				Iterator<Disciplina> it = resultadoPesquisa.iterator();
				while (it.hasNext()) {
					Disciplina disciplina = it.next();
					comboBox.addItem(disciplina);
				}
			}
		});
		btnPesquisar.setBounds(377, 50, 125, 23);
		contentPane.add(btnPesquisar);

	}
	protected void voltar() {
		MenuPrincipal frame = new MenuPrincipal();
		frame.setVisible(true);
		setVisible(false);
	}
}
