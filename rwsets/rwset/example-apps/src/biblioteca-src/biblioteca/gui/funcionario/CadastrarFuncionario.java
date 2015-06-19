package biblioteca.gui.funcionario;

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
import biblioteca.negocios.exceptions.endereco.EnderecoInvalidoException;
import biblioteca.negocios.exceptions.pessoa.CpfInvalidoException;
import biblioteca.negocios.exceptions.pessoa.PessoaJaCadastradaException;
import biblioteca.negocios.exceptions.pessoa.TelefoneInvalidoException;

public class CadastrarFuncionario extends JFrame {

	private JPanel contentPane;
	private JTextField tf_nome;
	private JTextField tf_cpf;
	private JTextField tf_rua;
	private JTextField tf_numero;
	private JTextField tf_complemento;
	private JTextField tf_cep;
	private JTextField tf_telefone;
	private JLabel lblDigiteOsDados;
	private static Principal fachada;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastrarFuncionario frame = new CadastrarFuncionario();
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
	public CadastrarFuncionario() {
		fachada = Login.getFachada();
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 355, 342);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); 
		this.setResizable(false);
		this.setTitle("Cadastro Funcion�rio");
		
		JLabel lblNome = new JLabel("Nome :  ");
		lblNome.setBounds(50, 38, 58, 14);
		contentPane.add(lblNome);
		
		JLabel lblCpf = new JLabel("CPF :  ");
		lblCpf.setBounds(50, 66, 46, 14);
		contentPane.add(lblCpf);
		
		JLabel lblRua = new JLabel("Rua : ");
		lblRua.setBounds(50, 94, 46, 14);
		contentPane.add(lblRua);
		
		JLabel lblNmero = new JLabel("N\u00FAmero :");
		lblNmero.setBounds(50, 122, 58, 14);
		contentPane.add(lblNmero);
		
		JLabel lblComplemento = new JLabel("Complemento : ");
		lblComplemento.setBounds(50, 147, 89, 14);
		contentPane.add(lblComplemento);
		
		JLabel lblCep = new JLabel("CEP :  ");
		lblCep.setBounds(50, 172, 46, 14);
		contentPane.add(lblCep);
		
		JLabel lblTelefone = new JLabel("Telefone :  ");
		lblTelefone.setBounds(50, 200, 69, 14);
		contentPane.add(lblTelefone);
		
		JLabel lblSenha = new JLabel("Senha : ");
		lblSenha.setBounds(50, 225, 46, 14);
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
						CadastrarFuncionario.this.dispose();
						Login frame = new Login();
						frame.setVisible(true);
						
					}else{
					
					
					fachada.cadastrarFuncionario(nome, cpf, telefone, senha, rua,
							numero, complemento, cep);
					JOptionPane.showMessageDialog(null, "O funcion�rio foi cadastrado com sucesso!");
					CadastrarFuncionario.this.dispose();
					}
				} catch (EnderecoInvalidoException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
					
				} catch (CpfInvalidoException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
					
				} catch (PessoaJaCadastradaException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
					
				} catch (IOException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				} catch (TelefoneInvalidoException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnOk.setBounds(122, 262, 89, 23);
		contentPane.add(btnOk);
		
		tf_nome = new JTextField();
		tf_nome.setBounds(168, 32, 86, 20);
		contentPane.add(tf_nome);
		tf_nome.setColumns(10);
		
		tf_cpf = new JTextField();
		tf_cpf.setBounds(168, 60, 86, 20);
		contentPane.add(tf_cpf);
		tf_cpf.setColumns(10);
		
		tf_rua = new JTextField();
		tf_rua.setBounds(168, 88, 86, 20);
		contentPane.add(tf_rua);
		tf_rua.setColumns(10);
		
		tf_numero = new JTextField();
		tf_numero.setBounds(168, 116, 86, 20);
		contentPane.add(tf_numero);
		tf_numero.setColumns(10);
		
		tf_complemento = new JTextField();
		tf_complemento.setBounds(168, 144, 86, 20);
		contentPane.add(tf_complemento);
		tf_complemento.setColumns(10);
		
		tf_cep = new JTextField();
		tf_cep.setBounds(168, 172, 86, 20);
		contentPane.add(tf_cep);
		tf_cep.setColumns(10);
		
		tf_telefone = new JTextField();
		tf_telefone.setBounds(168, 197, 86, 20);
		contentPane.add(tf_telefone);
		tf_telefone.setColumns(10);
		
		lblDigiteOsDados = new JLabel("Digite os dados do funcion\u00E1rio : ");
		lblDigiteOsDados.setBounds(10, 11, 329, 14);
		contentPane.add(lblDigiteOsDados);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(168, 222, 86, 20);
		contentPane.add(passwordField);
	}
}
