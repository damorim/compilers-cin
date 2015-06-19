package escola.gui;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;









import escola.classesBase.Aluno;
import escola.classesBase.Endereco;
import escola.classesBase.Turma;
import escola.dados.Repositorio;
import escola.excecoes.ElementoNaoEncontradoException;
import escola.excecoes.EntradaInvalidaException;
import escola.excecoes.RepositorioException;
import escola.negocio.Controle;
import escola.principal.PaginaPrincipal;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Iterator;

import javax.swing.JComboBox;
import javax.swing.ButtonGroup;

@SuppressWarnings("serial")
public class AtualizarAlunoFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textNome;
	private JTextField textCpf;
	private JTextField textRg;
	private JTextField textDataNasc;
	private JTextField textPai;
	private JTextField textMae;  
	private JTextField textRua;
	private JTextField textNumero;
	private JTextField textCEP;
	private JTextField textBairro;
	private JTextField textCidade;
	private JTextField textEstado;
	private JTextField textPais;
	private JTextField textTelefone;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JComboBox cbxTurma ;
	//private static Aluno aluno;
	JRadioButton rdbtnMasculino;
	JRadioButton rdbtnFeminino;
	private Turma turma;
	private static Aluno aluno;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AtualizarAlunoFrame frame = new AtualizarAlunoFrame(aluno);
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
	public AtualizarAlunoFrame(final Aluno alunoOriginal) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblatualizarAluno = new JLabel("atualizar Aluno");
		lblatualizarAluno.setBounds(21, 18, 144, 16);
		contentPane.add(lblatualizarAluno);
		
		JLabel lblNomeCompleto = new JLabel("Nome Completo:");
		lblNomeCompleto.setBounds(21, 59, 112, 16);
		contentPane.add(lblNomeCompleto);
		
		textNome = new JTextField();
		textNome.setBounds(138, 53, 435, 28);
		contentPane.add(textNome);
		textNome.setColumns(10);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(182, 127, 61, 16);
		contentPane.add(lblCpf);
		
		textCpf = new JTextField();
		textCpf.setEditable(false);
		textCpf.setBounds(214, 121, 141, 28);
		contentPane.add(textCpf);
		textCpf.setColumns(10);
		
		JLabel lblRg = new JLabel("RG:");
		lblRg.setBounds(21, 127, 61, 16);
		contentPane.add(lblRg);
		
		textRg = new JTextField();
		textRg.setBounds(45, 121, 125, 28);
		contentPane.add(textRg);
		textRg.setColumns(10);
		
		textDataNasc = new JTextField();
		textDataNasc.setBounds(245, 87, 125, 28);
		contentPane.add(textDataNasc);
		textDataNasc.setColumns(10);
		
		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento (dd/mm/aaaa)");
		lblDataDeNascimento.setBounds(21, 93, 228, 16);
		contentPane.add(lblDataDeNascimento);
		
		
		rdbtnFeminino = new JRadioButton("Feminino");
		buttonGroup.add(rdbtnFeminino);
		rdbtnFeminino.setBounds(432, 102, 141, 23);
		contentPane.add(rdbtnFeminino);
		
		rdbtnMasculino = new JRadioButton("Masculino");
		buttonGroup.add(rdbtnMasculino);
		rdbtnMasculino.setBounds(432, 126, 141, 23);
		contentPane.add(rdbtnMasculino);
		
		//JRadioButton rdbtnFeminino = new JRadioButton("Feminino");
		rdbtnFeminino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		//JRadioButton rdbtnMasculino = new JRadioButton("Masculino");
		rdbtnMasculino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setBounds(432, 87, 61, 16);
		contentPane.add(lblSexo);
		
		JLabel lblNomeDoPai = new JLabel("Nome do Pai:");
		lblNomeDoPai.setBounds(21, 167, 94, 16);
		contentPane.add(lblNomeDoPai);
		
		textPai = new JTextField();
		textPai.setBounds(112, 161, 461, 28);
		contentPane.add(textPai);
		textPai.setColumns(10);
		
		JLabel lblNomeDaMe = new JLabel("Nome da Mae:");
		lblNomeDaMe.setBounds(21, 200, 94, 16);
		contentPane.add(lblNomeDaMe);
		
		textMae = new JTextField();
		textMae.setBounds(122, 194, 451, 28);
		contentPane.add(textMae);
		textMae.setColumns(10);
		
		JLabel lblEndereo = new JLabel("Endereco:");
		lblEndereo.setBounds(21, 234, 61, 16);
		contentPane.add(lblEndereo);
		
		JLabel lblN = new JLabel("N\u00BA :");
		lblN.setBounds(432, 234, 61, 16);
		contentPane.add(lblN);
		
		textRua = new JTextField();
		textRua.setBounds(90, 228, 320, 28);
		contentPane.add(textRua);
		textRua.setColumns(10);
		
		textNumero = new JTextField();
		textNumero.setBounds(460, 228, 113, 28);
		contentPane.add(textNumero);
		textNumero.setColumns(10);
		
		JLabel lblCep = new JLabel("CEP:");
		lblCep.setBounds(21, 268, 61, 16);
		contentPane.add(lblCep);
		
		textCEP = new JTextField();
		textCEP.setBounds(56, 262, 130, 28);
		contentPane.add(textCEP);
		textCEP.setColumns(10);
		
		JLabel lblBairro = new JLabel("Bairro:");
		lblBairro.setBounds(397, 268, 61, 16);
		contentPane.add(lblBairro);
		
		textBairro = new JTextField();
		textBairro.setBounds(439, 262, 134, 28);
		contentPane.add(textBairro);
		textBairro.setColumns(10);
		
		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setBounds(198, 268, 61, 16);
		contentPane.add(lblCidade);
		
		textCidade = new JTextField();
		textCidade.setBounds(248, 262, 134, 28);
		contentPane.add(textCidade);
		textCidade.setColumns(10);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(21, 302, 61, 16);
		contentPane.add(lblEstado);
		
		textEstado = new JTextField();
		textEstado.setBounds(70, 296, 125, 28);
		contentPane.add(textEstado);
		textEstado.setColumns(10);
		
		JLabel lblPas = new JLabel("Pa\u00EDs:");
		lblPas.setBounds(210, 302, 61, 16);
		contentPane.add(lblPas);
		
		textPais = new JTextField();
		textPais.setBounds(245, 296, 121, 28);
		contentPane.add(textPais);
		textPais.setColumns(10);
		
		JLabel lblTurma = new JLabel("Turma:");
		lblTurma.setBounds(21, 341, 61, 16);
		contentPane.add(lblTurma);
		
		JButton btnatualizar = new JButton("Atualizar");
		btnatualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizar(alunoOriginal);
				}
		});
		btnatualizar.setBounds(461, 365, 112, 42);
		contentPane.add(btnatualizar);
		
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
		
		textTelefone = new JTextField();
		textTelefone.setBounds(439, 296, 134, 28);
		contentPane.add(textTelefone);
		textTelefone.setColumns(10);
		
		cbxTurma = new JComboBox();
		cbxTurma.setBounds(70, 337, 141, 27);
		contentPane.add(cbxTurma);
		
		JButton btnNewButton = new JButton("Adicionar Nova");
		btnNewButton.setBounds(70, 372, 141, 29);
		contentPane.add(btnNewButton);
		

		this.textBairro.setText(alunoOriginal.getEndereco().getBairro());
		this.textCEP.setText(alunoOriginal.getEndereco().getCep());
		this.textCidade.setText(alunoOriginal.getEndereco().getCidade());
		this.textRua.setText(alunoOriginal.getEndereco().getRua());
		this.textBairro.setText(alunoOriginal.getEndereco().getBairro());
		this.textCidade.setText(alunoOriginal.getEndereco().getCidade());
		this.textEstado.setText(alunoOriginal.getEndereco().getEstado());
		this.textNumero.setText(alunoOriginal.getEndereco().getNumero());
		this.textNome.setText(alunoOriginal.getNome());
		this.textRg.setText(alunoOriginal.getIdentidade());
		this.textDataNasc.setText(alunoOriginal.getDataNasc());
		this.textPais.setText(alunoOriginal.getEndereco().getPais());
		this.textCpf.setText(alunoOriginal.getCpf());
		this.textPai.setText(alunoOriginal.getPai());
		this.textMae.setText(alunoOriginal.getMae());
		this.textTelefone.setText(alunoOriginal.getTelefone());
		cbxTurma.setSelectedItem(alunoOriginal.getTurma());
		if(alunoOriginal.getSexo().equals("M")){
			rdbtnMasculino.setSelected(true);
		}else{
			rdbtnFeminino.setSelected(true);
		}
		
		
		//Preenche a comboBox com todos os items e deixa selecionado aquele que esta salvo;
		cbxTurma.removeAllItems();
		Repositorio<Turma> repositorio = PaginaPrincipal.fachada.getTurmas(); 
		Iterator<Turma> it = repositorio.getIterator();
		while (it.hasNext()){
			Turma turmaAux = it.next();
			cbxTurma.addItem(turmaAux);
		}
		
		cbxTurma.setSelectedItem(alunoOriginal.getTurma());
		
		
		
	}
	
	private void atualizar(Aluno alunoOriginal){
		String nome = textNome.getText();
		String cpf = textCpf.getText();
		String dataNasc = textDataNasc.getText();
		String rg = textRg.getText();
		String telefone = textTelefone.getText();
		String rua = textRua.getText();
		String numero = textNumero.getText();
		String cep = textCEP.getText();
		String bairro = textBairro.getText();
		String cidade = textCidade.getText();
		String estado = textEstado.getText();
		String pais = textPais.getText();
		String pai = textPai.getText();
		String mae = textMae.getText();
		String sexo;
		turma = (Turma) cbxTurma.getSelectedItem();
		if (rdbtnMasculino.isSelected()){
			sexo = "M";
		}else{
			sexo = "F";
		}
		if(turma==null){
			JOptionPane.showMessageDialog(this, "Por favor, selecione uma turma!");
		}
		try {
			Controle.controlePessoa(cpf, nome, dataNasc, rg, sexo, telefone, rua, numero, bairro, cep, cidade, estado, pais);
			Endereco endereco = new Endereco(rua, numero, bairro, cep, cidade, estado, pais);
			Aluno alunoAtualizado = new Aluno(cpf, nome, dataNasc, rg, sexo, telefone, endereco, pai, mae, turma);
			PaginaPrincipal.fachada.atualizarAluno(alunoAtualizado);
			JOptionPane.showMessageDialog(this,"Aluno atualizado com sucesso.");
			voltar();
			} catch (EntradaInvalidaException e) {
			JOptionPane.showMessageDialog(this, e.getOndeErrou());
			} catch (ElementoNaoEncontradoException e) {
				JOptionPane.showMessageDialog(this, "Aluno nao encontrado");
			} catch (RepositorioException e) {
				JOptionPane.showMessageDialog(this,"Erro no sistema. Desculpe-nos!");
			}
		
	}

	private void voltar() {
		MenuPrincipal frame1 = new MenuPrincipal();
		frame1.setVisible(true);
		this.setVisible(false);
	}
}

