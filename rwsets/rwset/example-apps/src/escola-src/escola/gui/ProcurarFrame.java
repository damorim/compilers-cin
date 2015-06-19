package escola.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Iterator;
import java.util.NoSuchElementException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;

import escola.classesBase.*;
import escola.dados.Repositorio;
import escola.excecoes.ElementoNaoEncontradoException;
import escola.principal.PaginaPrincipal;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import java.awt.Color;

import javax.swing.DropMode;
import javax.swing.SwingConstants;

public class ProcurarFrame extends JFrame {

	private JPanel contentPane;
	private JComboBox comboBox;
	private static String tipo;
	private JTextPane textPane;
	private JButton btnIr;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProcurarFrame frame = new ProcurarFrame(tipo);
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
	@SuppressWarnings("static-access")
	public ProcurarFrame(String tipo) {

		this.tipo = tipo;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textPane = new JTextPane();
		textPane.setBounds(24, 87, 544, 281);
		contentPane.add(textPane);
		JLabel lblSelecione = null;
		if (tipo.equals("disciplina") || tipo.equals("turma")) {
			lblSelecione = new JLabel("Selecione a " + tipo + "desejado: ");
		} else {
			lblSelecione = new JLabel("Selecione o " + tipo + "desejado: ");
		}
		lblSelecione.setBounds(24, 21, 367, 16);
		contentPane.add(lblSelecione);

		comboBox = new JComboBox();
		comboBox.setBounds(20, 48, 477, 27);
		contentPane.add(comboBox);

		comboBox.removeAllItems();

		if (tipo.equals("aluno")) {
			Repositorio<Pessoa> repositorio = PaginaPrincipal.fachada
					.getPessoas();
			Iterator<Pessoa> it = repositorio.getIterator();
			while (it.hasNext()) {
				Pessoa p = it.next();
				if (p instanceof Aluno) {
					comboBox.addItem(p);
				}
			}

		} else if (tipo.equals("professor")) {
			Repositorio<Pessoa> repositorio = PaginaPrincipal.fachada
					.getPessoas();
			Iterator<Pessoa> it = repositorio.getIterator();
			while (it.hasNext()) {
				Pessoa p = it.next();
				if (p instanceof Professor) {
					comboBox.addItem(p);
				}
			}
		} else if (tipo.equals("administrador")) {
			Repositorio<Pessoa> repositorio = PaginaPrincipal.fachada
					.getPessoas();
			Iterator<Pessoa> it = repositorio.getIterator();
			while (it.hasNext()) {
				Pessoa p = it.next();
				if (p instanceof Administrador) {
					comboBox.addItem(p);
				}
			}
		} else if (tipo.equals("funcionario")) {
			Repositorio<Pessoa> repositorio = PaginaPrincipal.fachada
					.getPessoas();
			Iterator<Pessoa> it = repositorio.getIterator();
			while (it.hasNext()) {
				Pessoa p = it.next();
				if (p instanceof Funcionario) {
					comboBox.addItem(p);
				}
			}
		} else if (tipo.equals("turma")) {
			Repositorio<Turma> repositorio = PaginaPrincipal.fachada
					.getTurmas();
			Iterator<Turma> it = repositorio.getIterator();
			while (it.hasNext()) {
				Turma t = it.next();
				comboBox.addItem(t);
			}
		} else if (tipo.equals("disciplinas")) {
			Repositorio<Disciplina> repositorio = PaginaPrincipal.fachada
					.getDisciplinas();
			Iterator<Disciplina> it2 = repositorio.getIterator();
			while (it2.hasNext()) {
				Disciplina d = it2.next();
				comboBox.addItem(d);
			}
		}

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				MenuPrincipal frame1 = new MenuPrincipal();
				frame1.setVisible(true);
				setVisible(false);

			}
		});
		btnVoltar.setBounds(470, 374, 124, 48);
		contentPane.add(btnVoltar);

		btnIr = new JButton("Ir");
		btnIr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				procurar();
			}
		});
		btnIr.setVerticalAlignment(SwingConstants.TOP);
		btnIr.setBackground(new Color(238, 238, 238));
		btnIr.setToolTipText("Procurar");
		btnIr.setBounds(502, 48, 67, 27);
		contentPane.add(btnIr);

	}

	public void procurar() {
		if (tipo.equals("aluno")) {
			Aluno a = (Aluno) comboBox.getSelectedItem();
			String texto = "Nome: " + a.getNome() + "  Sexo: " + a.getSexo()
					+ "\nCPF: " + a.getCpf() + "  RG: " + a.getIdentidade()
					+ "  Matricula: " + a.getNumeroMatricula() + "\nTelefone: "
					+ a.getTelefone() + "  Rua: " + a.getEndereco().getRua()
					+ ", " + a.getEndereco().getNumero() + "  CEP: "
					+ a.getEndereco().getCep() + "\nBairro: "
					+ a.getEndereco().getBairro() + "  Cidade: "
					+ a.getEndereco().getCidade() + "  Estado: "
					+ a.getEndereco().getEstado() + "  Pais: "
					+ a.getEndereco().getPais() + "\nMae: " + a.getMae()
					+ "   Pai: " + a.getPai() + " \nTurma: "
					+ a.getTurma().getNome();
			textPane.setText(texto);
		} else if (tipo.equals("professor")) {
			Professor a = (Professor) comboBox.getSelectedItem();
			String texto = "Nome: " + a.getNome() + "  Sexo: " + a.getSexo()
					+ "\nCPF: " + a.getCpf() + "  RG: " + a.getIdentidade()
					+ "\nTelefone: " + a.getTelefone() + "  Rua: "
					+ a.getEndereco().getRua() + ", "
					+ a.getEndereco().getNumero() + "  CEP: "
					+ a.getEndereco().getCep() + "\nBairro: "
					+ a.getEndereco().getBairro() + "  Cidade: "
					+ a.getEndereco().getCidade() + "  Estado: "
					+ a.getEndereco().getEstado() + "  Pais: "
					+ a.getEndereco().getPais() + " \nFuncao: " + a.getFuncao()
					+ "\nSalario: " + a.getSalario() + "\nTurmas que leciona: ";
			int i = 0;
			Iterator<Turma> it = a.getTurmas().getIterator();
			while (it.hasNext()) {
				if (i != 0) {
					texto += ", ";
				}
				texto += it.next().getNome();
				i++;
			}

			texto += "\nDisciplinas que leciona: ";

			Iterator<Disciplina> it2 = a.getDisciplinas().getIterator();
			i = 0;
			while (it2.hasNext()) {
				if (i != 0) {
					texto += ", ";
				}
				texto += it2.next().getNome();
				i++;
			}

			textPane.setText(texto);
		} else if (tipo.equals("administrador")) {
			Administrador a = (Administrador) comboBox.getSelectedItem();
			String texto = "Nome: " + a.getNome() + "  Sexo: " + a.getSexo()
					+ "\nCPF: " + a.getCpf() + "  RG: " + a.getIdentidade()
					+ "\nTelefone: " + a.getTelefone() + "  Rua: "
					+ a.getEndereco().getRua() + ", "
					+ a.getEndereco().getNumero() + "  CEP: "
					+ a.getEndereco().getCep() + "\nBairro: "
					+ a.getEndereco().getBairro() + "  Cidade: "
					+ a.getEndereco().getCidade() + "  Estado: "
					+ a.getEndereco().getEstado() + "  Pais: "
					+ a.getEndereco().getPais() + " \nFuncao: " + a.getFuncao()
					+ "\nSalario: " + a.getSalario();
			textPane.setText(texto);
		} else if (tipo.equals("funcionario")) {
			Funcionario a = (Funcionario) comboBox.getSelectedItem();
			String texto = "Nome: " + a.getNome() + "\nCPF: " + a.getCpf()
					+ "  RG: " + a.getIdentidade() + "\nTelefone: "
					+ a.getTelefone() + "  Rua: " + a.getEndereco().getRua()
					+ ", " + a.getEndereco().getNumero() + "  CEP: "
					+ a.getEndereco().getCep() + "\nBairro: "
					+ a.getEndereco().getBairro() + "  Cidade: "
					+ a.getEndereco().getCidade() + "  Estado: "
					+ a.getEndereco().getEstado() + "  Pais: "
					+ a.getEndereco().getPais() + " \nFuncao: " + a.getFuncao()
					+ "\nSalario: " + a.getSalario();
			textPane.setText(texto);
		} else if (tipo.equals("turma")) {
			Turma t = (Turma) comboBox.getSelectedItem();
			String texto = "Nome da turma: " + t.getNome()
					+ "\n\nAlunos dessa turma: ";

			Iterator<Pessoa> it = t.getAlunos().getIterator();
			while (it.hasNext()) {
				Pessoa p = it.next();
				try{
				texto += "\n" + p.getNome() + " / " + p.getCpf();
				}catch(NullPointerException e){
					JOptionPane.showMessageDialog(this, "errito");   //TA DANDO ERRO NESSES DOIS ITERATORS
				}
			}
			texto += "\n\nDisciplinas dessa turma: ";
			Iterator<Disciplina> it2 = t.getDisciplinas().getIterator();
			while (it2.hasNext()) {
				Disciplina d = it2.next();
				texto += "\n" + d.getNome();
			}

			textPane.setText(texto);
		} else if (tipo.equals("disciplinas")) {
			Disciplina d = (Disciplina) comboBox.getSelectedItem();
			String texto = "Nome da disciplina: " + d.getNome()+"\nEmenta: "+d.getEmenta()
					+ "\n\nProfessores que a lecionam: ";
			Iterator<Pessoa> it = PaginaPrincipal.fachada.getPessoas().getIterator();
			while(it.hasNext()){
				Pessoa p =it.next();
				if (p instanceof Professor){
					try{
					((Professor) p).procurarDisciplina(d.getNome());
					texto+="\n"+p.getNome();
					}catch(ElementoNaoEncontradoException e){
						
					}
				}
			}
			
			textPane.setText(texto);
		}
	}
}
