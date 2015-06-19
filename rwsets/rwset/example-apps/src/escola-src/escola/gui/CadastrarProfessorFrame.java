package escola.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JPasswordField;

import escola.excecoes.ElementoJaCadastradoException;
import escola.excecoes.EntradaInvalidaException;
import escola.excecoes.RepositorioException;
import escola.negocio.Controle;
import escola.principal.PaginaPrincipal;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ButtonGroup;

public class CadastrarProfessorFrame extends JFrame {

	private JPanel contentPane;
	private JTextField tf_nome;
	private JTextField tf_cpf;
	private JTextField tf_rg;
	private JTextField tf_dataNasc;
	private JTextField tf_rua;
	private JTextField tf_numero;
	private JTextField tf_cep;
	private JTextField tf_bairro;
	private JTextField tf_cidade;
	private JTextField tf_estado;
	private JTextField tf_pais;
	private JTextField tf_telefone;
	private JTextArea ta_funcao;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private String sexo;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastrarProfessorFrame frame = new CadastrarProfessorFrame();
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
	public CadastrarProfessorFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCadastrarAluno = new JLabel("Cadastrar Professor");
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
		
		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento (dd/mm/aaaa)");
		lblDataDeNascimento.setBounds(21, 93, 228, 16);
		contentPane.add(lblDataDeNascimento);
		
		JRadioButton rdbtnFeminino = new JRadioButton("Feminino");
		rdbtnFeminino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sexo="Feminino";
			}
		});
		buttonGroup.add(rdbtnFeminino);
		rdbtnFeminino.setBounds(432, 102, 141, 23);
		contentPane.add(rdbtnFeminino);
		
		JRadioButton rdbtnMasculino = new JRadioButton("Masculino");
		rdbtnMasculino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sexo="Masculino";
			}
		});
		buttonGroup.add(rdbtnMasculino);
		rdbtnMasculino.setBounds(432, 126, 141, 23);
		contentPane.add(rdbtnMasculino);
		
		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setBounds(432, 87, 61, 16);
		contentPane.add(lblSexo);
		
		JLabel lblEndereo = new JLabel("Endere\u00E7o:");
		lblEndereo.setBounds(21, 161, 61, 16);
		contentPane.add(lblEndereo);
		
		JLabel lblN = new JLabel("N\u00BA :");
		lblN.setBounds(432, 161, 61, 16);
		contentPane.add(lblN);
		
		tf_rua = new JTextField();
		tf_rua.setBounds(90, 155, 320, 28);
		contentPane.add(tf_rua);
		tf_rua.setColumns(10);
		
		tf_numero = new JTextField();
		tf_numero.setBounds(460, 155, 113, 28);
		contentPane.add(tf_numero);
		tf_numero.setColumns(10);
		
		JLabel lblCep = new JLabel("CEP:");
		lblCep.setBounds(21, 195, 61, 16);
		contentPane.add(lblCep);
		
		tf_cep = new JTextField();
		tf_cep.setBounds(57, 189, 134, 28);
		contentPane.add(tf_cep);
		tf_cep.setColumns(10);
		
		JLabel lblBairro = new JLabel("Bairro:");
		lblBairro.setBounds(203, 195, 61, 16);
		contentPane.add(lblBairro);
		
		tf_bairro = new JTextField();
		tf_bairro.setBounds(245, 189, 134, 28);
		contentPane.add(tf_bairro);
		tf_bairro.setColumns(10);
		
		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setBounds(21, 229, 61, 16);
		contentPane.add(lblCidade);
		
		tf_cidade = new JTextField();
		tf_cidade.setBounds(77, 223, 134, 28);
		contentPane.add(tf_cidade);
		tf_cidade.setColumns(10);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(228, 229, 61, 16);
		contentPane.add(lblEstado);
		
		tf_estado = new JTextField();
		tf_estado.setBounds(277, 223, 125, 28);
		contentPane.add(tf_estado);
		tf_estado.setColumns(10);
		
		JLabel lblPas = new JLabel("Pa\u00EDs:");
		lblPas.setBounds(417, 229, 61, 16);
		contentPane.add(lblPas);
		
		tf_pais = new JTextField();
		tf_pais.setBounds(452, 223, 121, 28);
		contentPane.add(tf_pais);
		tf_pais.setColumns(10);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastrar();
			}
		});
		btnCadastrar.setBounds(461, 365, 112, 42);
		contentPane.add(btnCadastrar);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				voltar();
			}
		});
		btnVoltar.setBounds(334, 365, 117, 42);
		contentPane.add(btnVoltar);
		
		JLabel lblFuno = new JLabel("Fun\u00E7\u00E3o:");
		lblFuno.setBounds(21, 263, 250, 16);
		contentPane.add(lblFuno);
		
		ta_funcao = new JTextArea();
		ta_funcao.setBounds(21, 285, 250, 96);
		contentPane.add(ta_funcao);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(334, 263, 61, 16);
		contentPane.add(lblTelefone);
		
		tf_telefone = new JTextField();
		tf_telefone.setBounds(399, 257, 134, 28);
		contentPane.add(tf_telefone);
		tf_telefone.setColumns(10);
	}
	private void cadastrar(){
		//System.out.println(sexo);
		try{
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
			String funcao = ta_funcao.getText();
			Controle.controlePessoa(cpf, nome, dataNasc, rg, funcao, telefone, rua, numero, bairro, cep, cidade, estado, pais);
			PaginaPrincipal.fachada.inserirProfessor(cpf, nome, dataNasc, rg, sexo, telefone,
					rua, numero, bairro, cep, cidade, estado, pais, funcao);
			JOptionPane.showMessageDialog(this,"Professor cadastrado com sucesso.");
			voltar();
		} catch (ElementoJaCadastradoException e){
			JOptionPane.showMessageDialog(this,"O professor ja esta cadastrado.");
		} catch (RepositorioException e) {
			JOptionPane.showMessageDialog(this,"Erro no repositorio.");
		} catch (EntradaInvalidaException e) {
			JOptionPane.showMessageDialog(this,e.getOndeErrou());
		}
		
	}

	private void voltar() {
		MenuPrincipal frame1 = new MenuPrincipal();
		frame1.setVisible(true);
		this.setVisible(false);
	}
}

