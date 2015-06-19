package biblioteca.gui.aluno;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import biblioteca.fachada.Principal;
import biblioteca.gui.Login;
import biblioteca.negocios.ManipularPessoa;
import biblioteca.negocios.exceptions.endereco.EnderecoInvalidoException;
import biblioteca.negocios.exceptions.pessoa.CpfInvalidoException;
import biblioteca.negocios.exceptions.pessoa.PessoaJaCadastradaException;
import biblioteca.negocios.exceptions.pessoa.TelefoneInvalidoException;

public class CadastrarAluno extends JFrame {

	private JPanel contentPane;
	private JTextField tf_nome;
	private JTextField tf_cpf;
	private JTextField tf_rua;
	private JTextField tf_numero;
	private JTextField tf_complemento;
	private JTextField tf_cep;
	private JTextField tf_telefone;
	private JLabel lblDigiteAsInformaes;
	private JPasswordField passwordField;
	private static Principal fachada;
	private ManipularPessoa mPessoa;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastrarAluno frame = new CadastrarAluno();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param fachada2 
	 */
	public CadastrarAluno() {
		fachada = Login.getFachada();
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 355, 367);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); 
		this.setResizable(false);
		this.setTitle("Cadastro Aluno");
		
		JLabel lblNome = new JLabel("Nome :  ");
		lblNome.setBounds(61, 38, 58, 14);
		contentPane.add(lblNome);
		
		JLabel lblCpf = new JLabel("CPF :  ");
		lblCpf.setBounds(61, 69, 46, 14);
		contentPane.add(lblCpf);
		
		JLabel lblRua = new JLabel("Rua : ");
		lblRua.setBounds(61, 94, 46, 14);
		contentPane.add(lblRua);
		
		JLabel lblNmero = new JLabel("N\u00FAmero :");
		lblNmero.setBounds(61, 119, 58, 14);
		contentPane.add(lblNmero);
		
		JLabel lblComplemento = new JLabel("Complemento : ");
		lblComplemento.setBounds(61, 151, 89, 14);
		contentPane.add(lblComplemento);
		
		JLabel lblCep = new JLabel("CEP :  ");
		lblCep.setBounds(61, 182, 46, 14);
		contentPane.add(lblCep);
		
		JLabel lblTelefone = new JLabel("Telefone :  ");
		lblTelefone.setBounds(61, 213, 69, 14);
		contentPane.add(lblTelefone);
		
		JLabel lblSenha = new JLabel("Senha : ");
		lblSenha.setBounds(61, 244, 46, 14);
		contentPane.add(lblSenha);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					String nome = tf_nome.getText();
					String cpf = tf_cpf.getText();
					String telefone =tf_telefone.getText();
					String senha = passwordField.getText();
					String rua =  tf_rua.getText();
					String numero = tf_numero.getText();
					String complemento = tf_complemento.getText();
					String cep = tf_cep.getText();
					
					//System.out.println("nome:" + nome + "CPF:" + cpf + "Telefone:" + telefone + "senha:" + senha + "Rua:" + rua + "numero:" + numero + "complemento:" + complemento +"cep:" + cep);
					if(nome.equals("")|| telefone.equals("") || senha.equals("") || rua.equals("") || numero.equals("") || complemento.equals("") ||cep.equals("")){
						JOptionPane.showMessageDialog(null, "Todos os campos s�o obrigat�rios!");
						
					}if(fachada.getSistemaLogado() == false){
						JOptionPane.showMessageDialog(null, "Para realizar esta a��o voc� deve est� logado no sistema");
						CadastrarAluno.this.dispose();
						Login frame = new Login();
						frame.setVisible(true);
						
					}else{
					fachada.cadastrarAluno(nome, cpf ,telefone, senha, rua, numero , complemento, cep);
					JOptionPane.showMessageDialog(null, "O aluno foi cadastrado com sucesso!");
					CadastrarAluno.this.dispose();
					}
				} catch (EnderecoInvalidoException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
					
				} catch (CpfInvalidoException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
					
				} catch (PessoaJaCadastradaException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
					
				} catch (IOException ex) {
					//
					ex.printStackTrace();
				} catch (TelefoneInvalidoException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
		});
		btnOk.setBounds(121, 279, 89, 23);
		contentPane.add(btnOk);
		
		tf_nome = new JTextField();
		tf_nome.setBounds(168, 32, 86, 20);
		contentPane.add(tf_nome);
		tf_nome.setColumns(10);
		
		tf_cpf = new JTextField();
		tf_cpf.setBounds(168, 63, 86, 20);
		contentPane.add(tf_cpf);
		tf_cpf.setColumns(10);
		
		tf_rua = new JTextField();
		tf_rua.setBounds(168, 88, 86, 20);
		contentPane.add(tf_rua);
		tf_rua.setColumns(10);
		
		tf_numero = new JTextField();
		tf_numero.setBounds(168, 119, 86, 20);
		contentPane.add(tf_numero);
		tf_numero.setColumns(10);
		
		tf_complemento = new JTextField();
		tf_complemento.setBounds(168, 148, 86, 20);
		contentPane.add(tf_complemento);
		tf_complemento.setColumns(10);
		
		tf_cep = new JTextField();
		tf_cep.setBounds(168, 179, 86, 20);
		contentPane.add(tf_cep);
		tf_cep.setColumns(10);
		
		tf_telefone = new JTextField();
		tf_telefone.setBounds(168, 210, 86, 20);
		contentPane.add(tf_telefone);
		tf_telefone.setColumns(10);
		
		lblDigiteAsInformaes = new JLabel("Digite as informa\u00E7\u00F5es do aluno: ");
		lblDigiteAsInformaes.setBounds(10, 11, 329, 14);
		contentPane.add(lblDigiteAsInformaes);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(168, 241, 86, 20);
		contentPane.add(passwordField);
	}
}
