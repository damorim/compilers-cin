package escola.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JScrollBar;

import escola.principal.PaginaPrincipal;

public class RelatorioFrame extends JFrame {

	private JPanel contentPane;
	private static String tipo;
	private JTextArea  textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RelatorioFrame frame = new RelatorioFrame(tipo);
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
	@SuppressWarnings("deprecation")
	public RelatorioFrame(String tipo) {
		this.tipo = tipo;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 48, 560, 326);
		contentPane.add(scrollPane);

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);
	
		if (tipo.equalsIgnoreCase("aluno")) {
			textArea.setText(PaginaPrincipal.fachada.relatorioAlunos());
		} else if (tipo.equalsIgnoreCase("professor")) {
			textArea.setText(PaginaPrincipal.fachada.relatorioProfessor());
		} else if (tipo.equalsIgnoreCase("administrador")) {
			textArea.setText(PaginaPrincipal.fachada.relatorioAdm());
		} else if (tipo.equalsIgnoreCase("funcionario")) {
			textArea.setText(PaginaPrincipal.fachada.relatorioFuncionario());
		} else if (tipo.equalsIgnoreCase("turma")) {
			textArea.setText(PaginaPrincipal.fachada.relatorioTurmas());
		} else if (tipo.equalsIgnoreCase("disciplina")) {
			textArea.setText(PaginaPrincipal.fachada.relatorioDisc());
		}

		String palavra = tipo.substring(0, 1).toUpperCase()
				+ tipo.substring(1, tipo.length()).toLowerCase();

		JLabel lblRelatorio = new JLabel("Relatorio " + palavra);
		lblRelatorio.setBounds(20, 20, 186, 16);
		contentPane.add(lblRelatorio);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MenuPrincipal frame = new MenuPrincipal();
				frame.setVisible(true);
				setVisible(false);

			}
		});
		btnVoltar.setBounds(463, 379, 117, 43);
		contentPane.add(btnVoltar);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				salvar();
			}
		});
		btnSalvar.setBounds(342, 379, 117, 43);
		contentPane.add(btnSalvar);
		
	
	}
	public void salvar(){
		SalvarFrame frame =new SalvarFrame(textArea.getText());
		frame.setVisible(true);
		
	}
}
