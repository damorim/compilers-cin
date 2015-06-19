package escola.gui;

import java.awt.BorderLayout;
import java.lang.Object;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;

import escola.classesBase.Turma;
import escola.dados.Repositorio;
import escola.dados.RepositorioArrayTurma;
import escola.excecoes.ElementoJaCadastradoException;
import escola.excecoes.EntradaInvalidaException;
import escola.excecoes.RepositorioException;
import escola.negocio.Controle;
import escola.principal.PaginaPrincipal;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Iterator;

import javax.swing.JComboBox;
import javax.swing.ButtonGroup;

public class CadastrarAlunoFrame extends JFrame {

	private JPanel contentPane;
	private JTextField tf_nome;
	private JTextField tf_cpf;
	private JTextField tf_rg;
	private JTextField tf_dataNasc;
	private JTextField tf_pai;
	private JTextField tf_mae; // FALTA RESOLVER O COMBOBOX DA TURMA!!!!
	private JTextField tf_rua;
	private JTextField tf_numero;
	private JTextField tf_cep;
	private JTextField tf_bairro;
	private JTextField tf_cidade;
	private JTextField tf_estado;
	private JTextField tf_pais;
	private JTextField tf_telefone;
	private String sexo;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JComboBox comboBoxTurma;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastrarAlunoFrame frame = new CadastrarAlunoFrame();
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
	public CadastrarAlunoFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCadastrarAluno = new JLabel("Cadastrar Aluno");
		lblCadastrarAluno.setBounds(21, 18, 144, 16);
		contentPane.add(lblCadastrarAluno);

		JLabel lblNomeCompleto = new JLabel("Nome Completo:");
		lblNomeCompleto.setBounds(21, 59, 112, 16);
		contentPane.add(lblNomeCompleto);

		tf_nome = new JTextField();
		tf_nome.setBounds(138, 53, 435, 28);
		contentPane.add(tf_nome);
		tf_nome.setColumns(10);

		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(182, 127, 61, 16);
		contentPane.add(lblCpf);

		tf_cpf = new JTextField();
		tf_cpf.setBounds(214, 121, 141, 28);
		contentPane.add(tf_cpf);
		tf_cpf.setColumns(10);

		JLabel lblRg = new JLabel("RG:");
		lblRg.setBounds(21, 127, 61, 16);
		contentPane.add(lblRg);

		tf_rg = new JTextField();
		tf_rg.setBounds(45, 121, 125, 28);
		contentPane.add(tf_rg);
		tf_rg.setColumns(10);

		tf_dataNasc = new JTextField();
		tf_dataNasc.setBounds(245, 87, 125, 28);
		contentPane.add(tf_dataNasc);
		tf_dataNasc.setColumns(10);

		JLabel lblDataDeNascimento = new JLabel(
				"Data de Nascimento (dd/mm/aaaa)");
		lblDataDeNascimento.setBounds(21, 93, 228, 16);
		contentPane.add(lblDataDeNascimento);

		JRadioButton rdbtnFeminino = new JRadioButton("Feminino");
		buttonGroup.add(rdbtnFeminino);
		rdbtnFeminino.setBounds(432, 102, 141, 23);
		contentPane.add(rdbtnFeminino);

		JRadioButton rdbtnMasculino = new JRadioButton("Masculino");
		buttonGroup.add(rdbtnMasculino);
		rdbtnMasculino.setBounds(432, 126, 141, 23);
		contentPane.add(rdbtnMasculino);
		// JRadioButton rdbtnFeminino = new JRadioButton("Feminino");
		rdbtnFeminino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sexo = "Feminino";
			}
		});

		// JRadioButton rdbtnMasculino = new JRadioButton("Masculino");
		rdbtnMasculino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sexo = "Masculino";
			}
		});

		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setBounds(432, 87, 61, 16);
		contentPane.add(lblSexo);

		JLabel lblNomeDoPai = new JLabel("Nome do Pai:");
		lblNomeDoPai.setBounds(21, 167, 94, 16);
		contentPane.add(lblNomeDoPai);

		tf_pai = new JTextField();
		tf_pai.setBounds(112, 161, 461, 28);
		contentPane.add(tf_pai);
		tf_pai.setColumns(10);

		JLabel lblNomeDaMe = new JLabel("Nome da Mae:");
		lblNomeDaMe.setBounds(21, 200, 94, 16);
		contentPane.add(lblNomeDaMe);

		tf_mae = new JTextField();
		tf_mae.setBounds(122, 194, 451, 28);
		contentPane.add(tf_mae);
		tf_mae.setColumns(10);

		JLabel lblEndereo = new JLabel("Endereco:");
		lblEndereo.setBounds(21, 234, 61, 16);
		contentPane.add(lblEndereo);

		JLabel lblN = new JLabel("No :");
		lblN.setBounds(432, 234, 61, 16);
		contentPane.add(lblN);

		tf_rua = new JTextField();
		tf_rua.setBounds(90, 228, 320, 28);
		contentPane.add(tf_rua);
		tf_rua.setColumns(10);

		tf_numero = new JTextField();
		tf_numero.setBounds(460, 228, 113, 28);
		contentPane.add(tf_numero);
		tf_numero.setColumns(10);

		JLabel lblCep = new JLabel("CEP:");
		lblCep.setBounds(21, 268, 61, 16);
		contentPane.add(lblCep);

		tf_cep = new JTextField();
		tf_cep.setBounds(56, 262, 130, 28);
		contentPane.add(tf_cep);
		tf_cep.setColumns(10);

		JLabel lblBairro = new JLabel("Bairro:");
		lblBairro.setBounds(397, 268, 61, 16);
		contentPane.add(lblBairro);

		tf_bairro = new JTextField();
		tf_bairro.setBounds(439, 262, 134, 28);
		contentPane.add(tf_bairro);
		tf_bairro.setColumns(10);

		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setBounds(198, 268, 61, 16);
		contentPane.add(lblCidade);

		tf_cidade = new JTextField();
		tf_cidade.setBounds(248, 262, 134, 28);
		contentPane.add(tf_cidade);
		tf_cidade.setColumns(10);

		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(21, 302, 61, 16);
		contentPane.add(lblEstado);

		tf_estado = new JTextField();
		tf_estado.setBounds(70, 296, 125, 28);
		contentPane.add(tf_estado);
		tf_estado.setColumns(10);

		JLabel lblPas = new JLabel("Pais:");
		lblPas.setBounds(210, 302, 61, 16);
		contentPane.add(lblPas);

		tf_pais = new JTextField();
		tf_pais.setBounds(245, 296, 121, 28);
		contentPane.add(tf_pais);
		tf_pais.setColumns(10);

		JLabel lblTurma = new JLabel("Turma:");
		lblTurma.setBounds(21, 341, 61, 16);
		contentPane.add(lblTurma);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				voltar();
			}
		});
		btnVoltar.setBounds(334, 365, 117, 42);
		contentPane.add(btnVoltar);

		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(378, 302, 61, 16);
		contentPane.add(lblTelefone);

		tf_telefone = new JTextField();
		tf_telefone.setBounds(439, 296, 134, 28);
		contentPane.add(tf_telefone);
		tf_telefone.setColumns(10);

		comboBoxTurma = new JComboBox();
		comboBoxTurma.setBounds(70, 337, 141, 27);
		contentPane.add(comboBoxTurma);

		comboBoxTurma.removeAllItems();
		Repositorio<Turma> repositorio = PaginaPrincipal.fachada.getTurmas();
		Iterator<Turma> it = repositorio.getIterator();
		while (it.hasNext()) {
			Turma turmaAux = it.next();
			comboBoxTurma.addItem(turmaAux);
		}

		JButton btnNewButton = new JButton("Adicionar Nova");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastrarTurmaFrame frame = new CadastrarTurmaFrame(true);
				frame.setVisible(true);

			}
		});
		btnNewButton.setBounds(70, 368, 141, 29);
		contentPane.add(btnNewButton);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastrar();
			}
		});
		btnCadastrar.setBounds(461, 365, 112, 42);
		contentPane.add(btnCadastrar);

		JButton btnAtualizarTurmas = new JButton("Atualizar Turmas");
		btnAtualizarTurmas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				comboBoxTurma.removeAllItems();
				Repositorio<Turma> repositorio = PaginaPrincipal.fachada
						.getTurmas();
				Iterator<Turma> it = repositorio.getIterator();
				while (it.hasNext()) {
					Turma turmaAux = it.next();
					comboBoxTurma.addItem(turmaAux);
				}

			}
		});
		btnAtualizarTurmas.setBounds(214, 335, 141, 29);
		contentPane.add(btnAtualizarTurmas);
	}

	private void cadastrar() {
		Turma turma = null;
		try {
			String nome = tf_nome.getText();
			String cpf = tf_cpf.getText();
			String dataNasc = tf_dataNasc.getText();
			String rg = tf_rg.getText();
			String telefone = tf_telefone.getText();
			String rua = tf_rua.getText();
			String numero = tf_numero.getText();
			String cep = tf_cep.getText();
			String bairro = tf_bairro.getText();
			String cidade = tf_cidade.getText();
			String estado = tf_estado.getText();
			String pais = tf_pais.getText();
			String pai = tf_pai.getText();
			String mae = tf_mae.getText();
			if (comboBoxTurma.getSelectedItem() != null) {
				turma = (Turma) comboBoxTurma.getSelectedItem();
				Controle.controlePessoa(cpf, nome, dataNasc, rg, mae, telefone,
						rua, numero, bairro, cep, cidade, estado, pais);
				PaginaPrincipal.fachada.inserirAluno(cpf, nome, dataNasc, rg,
						sexo, telefone, rua, numero, bairro, cep, cidade,
						estado, pais, pai, mae, turma); // <<<<<<
				JOptionPane.showMessageDialog(this,
						"Aluno cadastrado com sucesso.");
			} else {
				JOptionPane.showMessageDialog(null, "Insira uma turma.");
			}
		} catch (ElementoJaCadastradoException e) {
			JOptionPane.showMessageDialog(this, "O aluno ja esta cadastrado.");
		} catch (EntradaInvalidaException e) {
			JOptionPane.showMessageDialog(this, e.getOndeErrou());
		} catch (RepositorioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void voltar() {
		MenuPrincipal frame1 = new MenuPrincipal();
		frame1.setVisible(true);
		this.setVisible(false);
	}
}