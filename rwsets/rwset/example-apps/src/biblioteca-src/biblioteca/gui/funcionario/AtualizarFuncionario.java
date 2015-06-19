package biblioteca.gui.funcionario;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import biblioteca.fachada.Principal;
import biblioteca.gui.Login;
import biblioteca.negocios.exceptions.RealizarLoginException;
import biblioteca.negocios.exceptions.endereco.EnderecoInvalidoException;
import biblioteca.negocios.exceptions.pessoa.PessoaInvalidaException;
import biblioteca.negocios.exceptions.pessoa.PessoaNaoEncontradaException;
import biblioteca.negocios.exceptions.pessoa.SenhaInvalidaException;
import biblioteca.negocios.exceptions.pessoa.TelefoneInvalidoException;

public class AtualizarFuncionario extends JFrame {

	private JPanel contentPane;
	private static Principal fachada;
	private JLabel lblDigiteOCpf;
	private JTextField tf_cpf;
	private JLabel lblOQueDeseja;
	private JRadioButton rdbtnNome;
	private JRadioButton rdbtnRua;
	private JRadioButton rdbtnNmero;
	private JRadioButton rdbtnComplemento;
	private JRadioButton rdbtnCep;
	private JRadioButton rdbtnTelefone;
	private JRadioButton rdbtnSenha;
	private JInternalFrame internalFrame;
	private JLabel lblFuncionrioDigiteA;
	private JLabel lblSenha;
	private JPasswordField passwordField;
	private JLabel lblDigiteONome;
	private JLabel lblNome;
	private JTextField tf_nome;
	private JButton btnOk_1;
	private JLabel lblDigiteARua;
	private JLabel lblRua;
	private JTextField tf_rua;
	private JButton btnOk_2;
	private JLabel lblDigiteONmero;
	private JLabel lblFuncionrio;
	private JLabel lblNmero;
	private JTextField tf_numero;
	private JButton btnNewButton;
	private JLabel lblDigiteOComplemento;
	private JLabel lblComplemento;
	private JTextField tf_complemento;
	private JButton btnOk_3;
	private JLabel lblDigiteOCep;
	private JLabel lblCep;
	private JTextField tf_cep;
	private JButton btnOk_4;
	private JLabel lblDigiteOTelefone;
	private JLabel lblTelefone;
	private JTextField tf_telefone;
	private JButton btnOk_5;
	private JLabel lblDigiteANova;
	private JLabel lblSenha_1;
	private JTextField tf_senha;
	private JButton btnOk_6;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AtualizarFuncionario frame = new AtualizarFuncionario();
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
	public AtualizarFuncionario() {
		fachada = Login.getFachada();
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 382, 370);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); 
		this.setResizable(false);
		this.setTitle("Atualiza��o Funcion�rio");
		
		lblDigiteOCpf = new JLabel("Digite o CPF do funcion�rio:");
		lblDigiteOCpf.setBounds(34, 14, 169, 14);
		contentPane.add(lblDigiteOCpf);
		
		tf_cpf = new JTextField();
		tf_cpf.setBounds(210, 11, 86, 20);
		contentPane.add(tf_cpf);
		tf_cpf.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(tf_cpf.getText().equals("")){
						JOptionPane.showMessageDialog(null, "O campo CPF � obrigat�rio!");
						
					}if(fachada.getSistemaLogado() == false){
						JOptionPane.showMessageDialog(null, "Para realizar esta a��o voc� deve est� logado no sistema");
						AtualizarFuncionario.this.dispose();
						Login frame = new Login();
						frame.setVisible(true);
						
					}else{
						JTextArea textArea = new JTextArea(6, 25);			
					      textArea.setText(fachada.atualizarPrintDadosFuncionario(tf_cpf.getText()));
					      textArea.setEditable(false);	     
					      JScrollPane scrollPane = new JScrollPane(textArea);    
					      JOptionPane.showMessageDialog(null, scrollPane, "Atualiza��o",JOptionPane.INFORMATION_MESSAGE);
					      
					      
					rdbtnNome.setEnabled(true);
					rdbtnRua.setEnabled(true);
					rdbtnNmero.setEnabled(true);
					rdbtnComplemento.setEnabled(true);
					rdbtnCep.setEnabled(true);
					rdbtnTelefone.setEnabled(true);
					rdbtnSenha.setEnabled(true);
				}
				}catch (PessoaNaoEncontradaException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				} catch (IOException ex) {
					// TODO Auto-generated catch block

				} catch (RealizarLoginException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				} catch (PessoaInvalidaException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnOk.setBounds(125, 40, 89, 23);
		contentPane.add(btnOk);
		
		lblOQueDeseja = new JLabel("O que deseja atualizar?");
		lblOQueDeseja.setBounds(10, 89, 135, 14);
		contentPane.add(lblOQueDeseja);
		
		rdbtnNome = new JRadioButton("Nome");
		rdbtnNome.setEnabled(false);
		rdbtnNome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				internalFrame = new JInternalFrame("Atualiza\u00E7\u00E3o");
				internalFrame.setClosable(true);
				internalFrame.setBounds(131, 108, 225, 213);
				contentPane.add(internalFrame);
				internalFrame.getContentPane().setLayout(null);
				
				lblFuncionrioDigiteA = new JLabel("Funcion\u00E1rio, digite a sua senha:");
				lblFuncionrioDigiteA.setBounds(10, 11, 189, 14);
				internalFrame.getContentPane().add(lblFuncionrioDigiteA);
				
				lblSenha = new JLabel("Senha:");
				lblSenha.setBounds(20, 36, 46, 14);
				internalFrame.getContentPane().add(lblSenha);
				
				passwordField = new JPasswordField();
				passwordField.setBounds(83, 33, 92, 20);
				internalFrame.getContentPane().add(passwordField);
				
				lblDigiteONome = new JLabel("Digite o nome do funcion\u00E1rio:");
				lblDigiteONome.setBounds(10, 74, 189, 14);
				internalFrame.getContentPane().add(lblDigiteONome);
				
				lblNome = new JLabel("Nome:");
				lblNome.setBounds(20, 106, 46, 14);
				internalFrame.getContentPane().add(lblNome);
				
				tf_nome = new JTextField();
				tf_nome.setBounds(83, 103, 92, 20);
				internalFrame.getContentPane().add(tf_nome);
				tf_nome.setColumns(10);
				
				btnOk_1 = new JButton("OK");
				btnOk_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							if(tf_nome.getText().equals("") || passwordField.getText().equals("")){
								JOptionPane.showMessageDialog(null, "Todos os campos s�o obrigat�rios!");
							}else{
							fachada.atualizarInfoFuncionario(passwordField.getText(), tf_cpf.getText(), tf_nome.getText(),
									1);
							JOptionPane.showMessageDialog(null, "O nome do funcion�rio foi atualizado com sucesso!");
							try { // Isto serve para fechar a janela
								internalFrame.setClosed(true);
							} catch (PropertyVetoException a) {
								// TODO Auto-generated catch block
								a.printStackTrace();
							}
							}
						} catch (SenhaInvalidaException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						} catch (EnderecoInvalidoException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						} catch (PessoaNaoEncontradaException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						} catch (IOException ex) {
							// TODO Auto-generated catch block
							ex.printStackTrace();
						} catch (PessoaInvalidaException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						} catch (TelefoneInvalidoException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				btnOk_1.setBounds(62, 150, 89, 23);
				internalFrame.getContentPane().add(btnOk_1);
				internalFrame.setVisible(true);
				
			}
		});
		rdbtnNome.setBounds(10, 118, 109, 23);
		contentPane.add(rdbtnNome);
		
		rdbtnRua = new JRadioButton("Rua");
		rdbtnRua.setEnabled(false);
		rdbtnRua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				internalFrame = new JInternalFrame("Atualiza\u00E7\u00E3o");
				internalFrame.setClosable(true);
				internalFrame.setBounds(131, 108, 225, 213);
				contentPane.add(internalFrame);
				internalFrame.getContentPane().setLayout(null);
				
				lblFuncionrioDigiteA = new JLabel("Funcion\u00E1rio, digite a sua senha:");
				lblFuncionrioDigiteA.setBounds(10, 11, 189, 14);
				internalFrame.getContentPane().add(lblFuncionrioDigiteA);
				
				lblSenha = new JLabel("Senha:");
				lblSenha.setBounds(20, 36, 46, 14);
				internalFrame.getContentPane().add(lblSenha);
				
				passwordField = new JPasswordField();
				passwordField.setBounds(83, 33, 92, 20);
				internalFrame.getContentPane().add(passwordField);
				
				lblDigiteARua = new JLabel("Digite a rua do funcion\u00E1rio:");
				lblDigiteARua.setBounds(10, 72, 165, 14);
				internalFrame.getContentPane().add(lblDigiteARua);
				
				lblRua = new JLabel("Rua:");
				lblRua.setBounds(20, 100, 46, 14);
				internalFrame.getContentPane().add(lblRua);
				
				tf_rua = new JTextField();
				tf_rua.setBounds(83, 97, 92, 20);
				internalFrame.getContentPane().add(tf_rua);
				tf_rua.setColumns(10);
				
				btnOk_2 = new JButton("OK");
				btnOk_2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							if(tf_rua.getText().equals("") ||passwordField.getText().equals("")){
								JOptionPane.showMessageDialog(null, "Todos os campos s�o obrigat�rios!");
							}else{
							fachada.atualizarInfoFuncionario(passwordField.getText(), tf_cpf.getText(), tf_rua.getText(),
									2);
							JOptionPane.showMessageDialog(null,"A rua do funcion�rio foi atualizada com sucesso!");
							try { // Isto serve para fechar a janela
								internalFrame.setClosed(true);
							} catch (PropertyVetoException a) {
								// TODO Auto-generated catch block
								a.printStackTrace();
							}
							}
						} catch (SenhaInvalidaException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						} catch (EnderecoInvalidoException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						} catch (PessoaNaoEncontradaException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						} catch (IOException ex) {
							// TODO Auto-generated catch block
						} catch (PessoaInvalidaException ex) {

							JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						} catch (TelefoneInvalidoException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				btnOk_2.setBounds(58, 137, 89, 23);
				internalFrame.getContentPane().add(btnOk_2);
			}
		});
		rdbtnRua.setBounds(10, 144, 109, 23);
		contentPane.add(rdbtnRua);
		
		rdbtnNmero = new JRadioButton("N\u00FAmero");
		rdbtnNmero.setEnabled(false);
		rdbtnNmero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				internalFrame = new JInternalFrame("Atualiza\u00E7\u00E3o");
				internalFrame.setClosable(true);
				internalFrame.setBounds(131, 108, 225, 201);
				contentPane.add(internalFrame);
				internalFrame.getContentPane().setLayout(null);
				
				lblFuncionrioDigiteA = new JLabel("Funcion\u00E1rio, digite a sua senha:");
				lblFuncionrioDigiteA.setBounds(10, 11, 189, 14);
				internalFrame.getContentPane().add(lblFuncionrioDigiteA);
				
				lblSenha = new JLabel("Senha:");
				lblSenha.setBounds(20, 36, 46, 14);
				internalFrame.getContentPane().add(lblSenha);
				
				passwordField = new JPasswordField();
				passwordField.setBounds(83, 33, 92, 20);
				internalFrame.getContentPane().add(passwordField);
				
				lblDigiteONmero = new JLabel("Digite o n\u00FAmero da resid\u00EAncia do\r\n");
				lblDigiteONmero.setBounds(10, 60, 199, 14);
				internalFrame.getContentPane().add(lblDigiteONmero);
				
				lblFuncionrio = new JLabel("funcion\u00E1rio:");
				lblFuncionrio.setBounds(10, 76, 71, 14);
				internalFrame.getContentPane().add(lblFuncionrio);
				
				lblNmero = new JLabel("N\u00FAmero:");
				lblNmero.setBounds(20, 104, 61, 14);
				internalFrame.getContentPane().add(lblNmero);
				
				tf_numero = new JTextField();
				tf_numero.setBounds(83, 101, 92, 20);
				internalFrame.getContentPane().add(tf_numero);
				tf_numero.setColumns(10);
				
				btnNewButton = new JButton("OK");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							if(tf_numero.getText().equals("")|| passwordField.getText().equals("")){
								JOptionPane.showMessageDialog(null,"Todos os campos s�o obrigat�rios!");
							}else{
							fachada.atualizarInfoFuncionario(passwordField.getText(), tf_cpf.getText(), tf_numero.getText(),
									3);
							JOptionPane.showMessageDialog(null,"O N�mero da resid�ncia do funcion�rio foi atualizado com sucesso!");
							try { // Isto serve para fechar a janela
								internalFrame.setClosed(true);
							} catch (PropertyVetoException a) {
								// TODO Auto-generated catch block
								a.printStackTrace();
							}
							}
						} catch (SenhaInvalidaException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						} catch (EnderecoInvalidoException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						} catch (PessoaNaoEncontradaException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						} catch (IOException ex) {
							// TODO Auto-generated catch block

						} catch (PessoaInvalidaException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						} catch (TelefoneInvalidoException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				btnNewButton.setBounds(66, 132, 89, 23);
				internalFrame.getContentPane().add(btnNewButton);
				
				
				
				internalFrame.setVisible(true);
			}
		});
		rdbtnNmero.setBounds(10, 172, 109, 23);
		contentPane.add(rdbtnNmero);
		
		rdbtnComplemento = new JRadioButton("Complemento");
		rdbtnComplemento.setEnabled(false);
		rdbtnComplemento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				internalFrame = new JInternalFrame("Atualiza\u00E7\u00E3o");
				internalFrame.setClosable(true);
				internalFrame.setBounds(131, 108, 235, 201);
				contentPane.add(internalFrame);
				internalFrame.getContentPane().setLayout(null);
				
				lblFuncionrioDigiteA = new JLabel("Funcion\u00E1rio, digite a sua senha:");
				lblFuncionrioDigiteA.setBounds(10, 11, 189, 14);
				internalFrame.getContentPane().add(lblFuncionrioDigiteA);
				
				lblSenha = new JLabel("Senha:");
				lblSenha.setBounds(33, 39, 46, 14);
				internalFrame.getContentPane().add(lblSenha);
				
				passwordField = new JPasswordField();
				passwordField.setBounds(102, 36, 86, 20);
				internalFrame.getContentPane().add(passwordField);
				
				lblDigiteOComplemento = new JLabel("Digite o complemento do funcion\u00E1rio:");
				lblDigiteOComplemento.setBounds(10, 69, 210, 14);
				internalFrame.getContentPane().add(lblDigiteOComplemento);
				
				lblComplemento = new JLabel("Complemento:");
				lblComplemento.setBounds(10, 100, 92, 14);
				internalFrame.getContentPane().add(lblComplemento);
				
				tf_complemento = new JTextField();
				tf_complemento.setBounds(102, 97, 86, 20);
				internalFrame.getContentPane().add(tf_complemento);
				tf_complemento.setColumns(10);
				
				btnOk_3 = new JButton("OK");
				btnOk_3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						try {
							if (tf_complemento.getText().equals("")|| passwordField.getText().equals("")){
								JOptionPane.showMessageDialog(null,"Todos os campos s�o obrigat�rios!");
							}else{
							fachada.atualizarInfoFuncionario(passwordField.getText(), tf_cpf.getText(), tf_complemento.getText(),
									4);
							JOptionPane.showMessageDialog(null,"O complemento do endere�o do Funcion�rio foi atualizado com sucesso!");
							
							try { // Isto serve para fechar a janela
								internalFrame.setClosed(true);
							} catch (PropertyVetoException a) {
								// TODO Auto-generated catch block
								a.printStackTrace();
							}
							}
						} catch (SenhaInvalidaException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						} catch (EnderecoInvalidoException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						} catch (PessoaNaoEncontradaException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						} catch (IOException ex) {
							// TODO Auto-generated catch block

						} catch (PessoaInvalidaException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						} catch (TelefoneInvalidoException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				btnOk_3.setBounds(63, 138, 89, 23);
				internalFrame.getContentPane().add(btnOk_3);
				
				
				
				
				internalFrame.setVisible(true);
			}
		});
		rdbtnComplemento.setBounds(10, 198, 109, 23);
		contentPane.add(rdbtnComplemento);
		
		rdbtnCep = new JRadioButton("CEP");
		rdbtnCep.setEnabled(false);
		rdbtnCep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				internalFrame = new JInternalFrame("Atualiza\u00E7\u00E3o");
				internalFrame.setClosable(true);
				internalFrame.setBounds(131, 108, 235, 201);
				contentPane.add(internalFrame);
				internalFrame.getContentPane().setLayout(null);
				
				lblFuncionrioDigiteA = new JLabel("Funcion\u00E1rio, digite a sua senha:");
				lblFuncionrioDigiteA.setBounds(10, 11, 189, 14);
				internalFrame.getContentPane().add(lblFuncionrioDigiteA);
				
				lblSenha = new JLabel("Senha:");
				lblSenha.setBounds(33, 39, 46, 14);
				internalFrame.getContentPane().add(lblSenha);
				
				passwordField = new JPasswordField();
				passwordField.setBounds(102, 36, 86, 20);
				internalFrame.getContentPane().add(passwordField);
				
				lblDigiteOCep = new JLabel("Digite o CEP do funcion\u00E1rio:");
				lblDigiteOCep.setBounds(10, 75, 155, 14);
				internalFrame.getContentPane().add(lblDigiteOCep);
				
				lblCep = new JLabel("CEP:");
				lblCep.setBounds(33, 105, 46, 14);
				internalFrame.getContentPane().add(lblCep);
				
				tf_cep = new JTextField();
				tf_cep.setBounds(102, 102, 86, 20);
				internalFrame.getContentPane().add(tf_cep);
				tf_cep.setColumns(10);
				
				btnOk_4 = new JButton("OK");
				btnOk_4.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							if(passwordField.getText().equals("")|| tf_cep.getText().equals("")){
								JOptionPane.showMessageDialog(null, "Todos os campos s�o obrigat�rios!");
							}else{
							fachada.atualizarInfoFuncionario(passwordField.getText(), tf_cpf.getText(), tf_cep.getText(),
									5);
							JOptionPane.showMessageDialog(null, "O cep do funcion�rio foi atualizado com sucesso!");
							try { // Isto serve para fechar a janela
								internalFrame.setClosed(true);
							} catch (PropertyVetoException a) {
								// TODO Auto-generated catch block
								a.printStackTrace();
							}
							}
						} catch (SenhaInvalidaException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						} catch (EnderecoInvalidoException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						} catch (PessoaNaoEncontradaException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						} catch (IOException ex) {
							// TODO Auto-generated catch block

						} catch (PessoaInvalidaException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						} catch (TelefoneInvalidoException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				btnOk_4.setBounds(63, 138, 89, 23);
				internalFrame.getContentPane().add(btnOk_4);
				
				
				
				
				
				internalFrame.setVisible(true);
			}
		});
		rdbtnCep.setBounds(10, 224, 109, 23);
		contentPane.add(rdbtnCep);
		
		rdbtnTelefone = new JRadioButton("Telefone");
		rdbtnTelefone.setEnabled(false);
		rdbtnTelefone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				internalFrame = new JInternalFrame("Atualiza\u00E7\u00E3o");
				internalFrame.setClosable(true);
				internalFrame.setBounds(131, 108, 235, 201);
				contentPane.add(internalFrame);
				internalFrame.getContentPane().setLayout(null);
				
				lblFuncionrioDigiteA = new JLabel("Funcion\u00E1rio, digite a sua senha:");
				lblFuncionrioDigiteA.setBounds(10, 11, 189, 14);
				internalFrame.getContentPane().add(lblFuncionrioDigiteA);
				
				lblSenha = new JLabel("Senha:");
				lblSenha.setBounds(33, 39, 46, 14);
				internalFrame.getContentPane().add(lblSenha);
				
				passwordField = new JPasswordField();
				passwordField.setBounds(102, 36, 86, 20);
				internalFrame.getContentPane().add(passwordField);
				
				lblDigiteOTelefone = new JLabel("Digite o telefone do funcion\u00E1rio:");
				lblDigiteOTelefone.setBounds(10, 76, 178, 14);
				internalFrame.getContentPane().add(lblDigiteOTelefone);
				
				lblTelefone = new JLabel("Telefone:");
				lblTelefone.setBounds(23, 108, 56, 14);
				internalFrame.getContentPane().add(lblTelefone);
				
				tf_telefone = new JTextField();
				tf_telefone.setBounds(102, 105, 86, 20);
				internalFrame.getContentPane().add(tf_telefone);
				tf_telefone.setColumns(10);
				
				btnOk_5 = new JButton("OK");
				btnOk_5.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							if(tf_telefone.getText().equals("") || passwordField.getText().equals("")){
								JOptionPane.showMessageDialog(null, "Todos os campos s�o obrigat�rios!");
							}else{
							fachada.atualizarInfoFuncionario(passwordField.getText(), tf_cpf.getText(), tf_telefone.getText(),
									6);
							JOptionPane.showMessageDialog(null, "O telefone do funcion�rio foi atualizado com sucesso!");
							try { // Isto serve para fechar a janela
								internalFrame.setClosed(true);
							} catch (PropertyVetoException a) {
								// TODO Auto-generated catch block
								a.printStackTrace();
							}
							}
						} catch (SenhaInvalidaException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						} catch (EnderecoInvalidoException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						} catch (PessoaNaoEncontradaException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						} catch (IOException ex) {
							// TODO Auto-generated catch block

						} catch (PessoaInvalidaException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						} catch (TelefoneInvalidoException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				btnOk_5.setBounds(61, 138, 89, 23);
				internalFrame.getContentPane().add(btnOk_5);
				
				
				
				
				
				
				internalFrame.setVisible(true);
			}
		});
		rdbtnTelefone.setBounds(10, 250, 109, 23);
		contentPane.add(rdbtnTelefone);
		
		rdbtnSenha = new JRadioButton("Senha");
		rdbtnSenha.setEnabled(false);
		rdbtnSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				internalFrame = new JInternalFrame("Atualiza\u00E7\u00E3o");
				internalFrame.setClosable(true);
				internalFrame.setBounds(131, 108, 235, 201);
				contentPane.add(internalFrame);
				internalFrame.getContentPane().setLayout(null);
				
				lblFuncionrioDigiteA = new JLabel("Funcion\u00E1rio, digite a sua senha:");
				lblFuncionrioDigiteA.setBounds(10, 11, 189, 14);
				internalFrame.getContentPane().add(lblFuncionrioDigiteA);
				
				lblSenha = new JLabel("Senha:");
				lblSenha.setBounds(33, 39, 46, 14);
				internalFrame.getContentPane().add(lblSenha);
				
				passwordField = new JPasswordField();
				passwordField.setBounds(102, 36, 86, 20);
				internalFrame.getContentPane().add(passwordField);
				
				lblDigiteANova = new JLabel("Digite a nova senha do funcion\u00E1rio:");
				lblDigiteANova.setBounds(10, 72, 199, 14);
				internalFrame.getContentPane().add(lblDigiteANova);
				
				lblSenha_1 = new JLabel("Senha:");
				lblSenha_1.setBounds(33, 109, 46, 14);
				internalFrame.getContentPane().add(lblSenha_1);
				
				tf_senha = new JTextField();
				tf_senha.setBounds(102, 106, 86, 20);
				internalFrame.getContentPane().add(tf_senha);
				tf_senha.setColumns(10);
				
				btnOk_6 = new JButton("OK");
				btnOk_6.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							if(passwordField.getText().equals("") || tf_senha.getText().equals("")){
								JOptionPane.showMessageDialog(null, "Todos os campos s�o obrigat�rios!");
							}else{
							fachada.atualizarInfoFuncionario(passwordField.getText(), tf_cpf.getText(), tf_senha.getText(),
									7);
							JOptionPane.showMessageDialog(null, " A senha do funcion�rio foi redefinida com sucesso!");
							try { // Isto serve para fechar a janela
								internalFrame.setClosed(true);
							} catch (PropertyVetoException a) {
								// TODO Auto-generated catch block
								a.printStackTrace();
							}
							} 
							
						} catch (SenhaInvalidaException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						} catch (EnderecoInvalidoException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						} catch (PessoaNaoEncontradaException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						} catch (IOException ex) {
							// TODO Auto-generated catch block

						} catch (PessoaInvalidaException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);;
						} catch (TelefoneInvalidoException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				btnOk_6.setBounds(65, 138, 89, 23);
				internalFrame.getContentPane().add(btnOk_6);
				
				
				
				
				
				
				
				
				internalFrame.setVisible(true);
			}
		});
		rdbtnSenha.setBounds(10, 277, 109, 23);
		contentPane.add(rdbtnSenha);
		
		
		
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnNome);
		group.add(rdbtnRua);
		group.add(rdbtnNmero);
		group.add(rdbtnComplemento);
		group.add(rdbtnCep);
		group.add(rdbtnTelefone);
		group.add(rdbtnSenha);
		
		
	}
}
