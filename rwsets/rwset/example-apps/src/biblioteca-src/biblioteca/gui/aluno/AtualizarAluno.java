package biblioteca.gui.aluno;

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

public class AtualizarAluno extends JFrame {

	private JPanel contentPane;
	private static Principal fachada;
	private JLabel lblDigiteOCpf;
	private JTextField tf_cpf;
	private JRadioButton rdbtnNome;
	private JRadioButton rdbtnRua;
	private JRadioButton rdbtnNmero;
	private JRadioButton rdbtnComplemento;
	private JRadioButton rdbtnCep;
	private JRadioButton rdbtnTelefone;
	private JRadioButton rdbtnSenha;
	private JLabel lblFuncionrioDigiteSua;
	private JLabel lblSenha;
	private JPasswordField passwordField;
	private JLabel lblDigiteONome;
	private JLabel lblNome;
	private JTextField tf_nome;
	private JButton btnOK;
	private JInternalFrame internalFrame;
	private JLabel lblFuncionrioDigiteSua_1;
	private JLabel lblSenha_1;
	private JPasswordField passwordField_1;
	private JLabel lblDigiteARua;
	private JLabel lblRua;
	private JTextField tf_rua;
	private JButton btnNewButton;
	private JLabel lblDigiteONmero;
	private JLabel lblNmero;
	private JTextField tf_numero;
	private JButton btnNewButton_1;
	private JLabel lblDigiteOComplemento;
	private JLabel lblComplemento;
	private JTextField tf_complemento;
	private JButton btnOk_1;
	private JLabel lblDigiteOCep;
	private JLabel lblCep;
	private JTextField tf_cep;
	private JButton btnOk_2;
	private JLabel lblDigiteOTelefone;
	private JLabel lblTelefone;
	private JTextField tf_telefone;
	private JButton btnOk_3;
	private JLabel lblDigiteANova;
	private JLabel lblSenha_2;
	private JButton btnOk_4;
	private JTextField tf_senha;
	private JLabel lblOQueDeseja;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AtualizarAluno frame = new AtualizarAluno();
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
	public AtualizarAluno() {
		fachada = Login.getFachada();
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 382, 370);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); 
		setLocationRelativeTo(null); 
		this.setResizable(false);
		this.setTitle("Atualiza��o Aluno");

		lblDigiteOCpf = new JLabel("Digite o CPF do aluno:");
		lblDigiteOCpf.setBounds(45, 14, 127, 14);
		contentPane.add(lblDigiteOCpf);

		tf_cpf = new JTextField();
		tf_cpf.setBounds(182, 11, 86, 20);
		contentPane.add(tf_cpf);
		tf_cpf.setColumns(10);

		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(tf_cpf.getText().equals("")){
						JOptionPane.showMessageDialog(null, "O campo 'CPF' � obrigat�rio!");
					
					}if(fachada.getSistemaLogado() == false){
							JOptionPane.showMessageDialog(null, "Para realizar esta a��o voc� deve est� logado no sistema");
							AtualizarAluno.this.dispose();
							Login frame = new Login();
							frame.setVisible(true);
						}else{
							JTextArea textArea = new JTextArea(6, 25);

							textArea.setText(fachada.autalizarPrintDadosAluno(tf_cpf.getText()));
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

				} catch (PessoaInvalidaException ex) {

					JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				} catch (RealizarLoginException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnOk.setBounds(125, 40, 89, 23);
		contentPane.add(btnOk);

		rdbtnNome = new JRadioButton("Nome");
		rdbtnNome.setEnabled(false);
		rdbtnNome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				internalFrame = new JInternalFrame("Atualiza\u00E7\u00E3o");
				internalFrame.setClosable(true);
				internalFrame.setBounds(137, 87, 219, 212);
				contentPane.add(internalFrame);
				internalFrame.getContentPane().setLayout(null);

				lblFuncionrioDigiteSua = new JLabel("Funcion\u00E1rio, digite sua senha:");
				lblFuncionrioDigiteSua.setBounds(10, 11, 183, 14);
				internalFrame.getContentPane().add(lblFuncionrioDigiteSua);

				lblSenha = new JLabel("Senha:");
				lblSenha.setBounds(32, 36, 46, 14);
				internalFrame.getContentPane().add(lblSenha);

				passwordField = new JPasswordField();
				passwordField.setBounds(81, 33, 95, 20);
				internalFrame.getContentPane().add(passwordField);

				lblDigiteONome = new JLabel("Digite o nome do aluno:");
				lblDigiteONome.setBounds(10, 71, 166, 14);
				internalFrame.getContentPane().add(lblDigiteONome);

				lblNome = new JLabel("Nome:");
				lblNome.setBounds(32, 107, 46, 14);
				internalFrame.getContentPane().add(lblNome);

				tf_nome = new JTextField();
				tf_nome.setBounds(81, 104, 95, 20);
				internalFrame.getContentPane().add(tf_nome);
				tf_nome.setColumns(10);

				btnOK = new JButton("OK");
				btnOK.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							if(tf_nome.getText().equals("") || passwordField.getText().equals("")){
								JOptionPane.showMessageDialog(null, "Todos os campos s�o obrigat�rios!");
							}else{
								fachada.atualizarInfoAluno(passwordField.getText(), tf_nome.getText(), 1);
								JOptionPane.showMessageDialog(null, "O nome do aluno foi atualizado com sucesso!");
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
						} catch (RealizarLoginException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						} catch (TelefoneInvalidoException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				btnOK.setBounds(57, 149, 89, 23);
				internalFrame.getContentPane().add(btnOK);
				internalFrame.setVisible(true);
			}
		});
		rdbtnNome.setBounds(6, 115, 109, 23);
		contentPane.add(rdbtnNome);


		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnNome);

		rdbtnRua = new JRadioButton("Rua");
		rdbtnRua.setEnabled(false);
		rdbtnRua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				internalFrame = new JInternalFrame("Atualiza\u00E7\u00E3o");
				internalFrame.setClosable(true);
				internalFrame.setBounds(125, 88, 231, 217);
				contentPane.add(internalFrame);
				internalFrame.getContentPane().setLayout(null);

				lblFuncionrioDigiteSua_1 = new JLabel("Funcion\u00E1rio, digite sua senha:");
				lblFuncionrioDigiteSua_1.setBounds(10, 11, 179, 14);
				internalFrame.getContentPane().add(lblFuncionrioDigiteSua_1);

				lblSenha_1 = new JLabel("Senha:");
				lblSenha_1.setBounds(20, 32, 46, 14);
				internalFrame.getContentPane().add(lblSenha_1);

				passwordField_1 = new JPasswordField();
				passwordField_1.setBounds(78, 29, 86, 20);
				internalFrame.getContentPane().add(passwordField_1);

				lblDigiteARua = new JLabel("Digite a rua do aluno:");
				lblDigiteARua.setBounds(10, 69, 168, 14);
				internalFrame.getContentPane().add(lblDigiteARua);

				lblRua = new JLabel("Rua:");
				lblRua.setBounds(20, 102, 46, 14);
				internalFrame.getContentPane().add(lblRua);

				tf_rua = new JTextField();
				tf_rua.setBounds(78, 99, 86, 20);
				internalFrame.getContentPane().add(tf_rua);
				tf_rua.setColumns(10);

				btnNewButton = new JButton("OK");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							if(tf_rua.getText().equals("") ||passwordField.getText().equals("")){
								JOptionPane.showMessageDialog(null, "Todos os campos s�o obrigat�rios!");
							}else{
								fachada.atualizarInfoAluno(passwordField.getText(),tf_rua.getText(), 2);
								JOptionPane.showMessageDialog(null, "Rua atualizada com sucesso!");
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
						} catch (RealizarLoginException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						} catch (TelefoneInvalidoException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						}

					}
				});
				btnNewButton.setBounds(66, 154, 89, 23);
				internalFrame.getContentPane().add(btnNewButton);
				internalFrame.setVisible(true);
			}
		});
		rdbtnRua.setBounds(6, 141, 109, 23);
		contentPane.add(rdbtnRua);
		group.add(rdbtnRua);

		rdbtnNmero = new JRadioButton("N\u00FAmero");
		rdbtnNmero.setEnabled(false);
		rdbtnNmero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


				internalFrame = new JInternalFrame("Atualiza\u00E7\u00E3o");
				internalFrame.setClosable(true);
				internalFrame.setBounds(116, 88, 250, 217);
				contentPane.add(internalFrame);
				internalFrame.getContentPane().setLayout(null);

				lblFuncionrioDigiteSua_1 = new JLabel("Funcion\u00E1rio, digite sua senha:");
				lblFuncionrioDigiteSua_1.setBounds(10, 11, 179, 14);
				internalFrame.getContentPane().add(lblFuncionrioDigiteSua_1);

				lblSenha_1 = new JLabel("Senha:");
				lblSenha_1.setBounds(36, 32, 46, 14);
				internalFrame.getContentPane().add(lblSenha_1);

				passwordField_1 = new JPasswordField();
				passwordField_1.setBounds(103, 29, 86, 20);
				internalFrame.getContentPane().add(passwordField_1);

				lblDigiteONmero = new JLabel("Digite o n\u00FAmero da resid\u00EAncia do aluno:");
				lblDigiteONmero.setBounds(10, 71, 224, 14);
				internalFrame.getContentPane().add(lblDigiteONmero);

				lblNmero = new JLabel("N\u00FAmero:");
				lblNmero.setBounds(36, 104, 57, 14);
				internalFrame.getContentPane().add(lblNmero);

				tf_numero = new JTextField();
				tf_numero.setBounds(103, 101, 86, 20);
				internalFrame.getContentPane().add(tf_numero);
				tf_numero.setColumns(10);

				btnNewButton_1 = new JButton("OK");
				btnNewButton_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						try {
							if(tf_numero.getText().equals("")|| passwordField.getText().equals("")){
								JOptionPane.showMessageDialog(null,"Todos os campos s�o obrigat�rios!");
							}else{
								fachada.atualizarInfoAluno(passwordField.getText(), tf_numero.getText(), 3);
								JOptionPane.showMessageDialog(null,"O N�mero da resid�ncia do aluno foi atualizado com sucesso!");
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
						} catch (RealizarLoginException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						} catch (TelefoneInvalidoException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						}

					}
				});
				btnNewButton_1.setBounds(75, 154, 89, 23);
				internalFrame.getContentPane().add(btnNewButton_1);
				internalFrame.setVisible(true);
			}
		});
		rdbtnNmero.setBounds(6, 167, 109, 23);
		contentPane.add(rdbtnNmero);
		group.add(rdbtnNmero);


		rdbtnComplemento = new JRadioButton("Complemento");
		rdbtnComplemento.setEnabled(false);
		rdbtnComplemento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				internalFrame = new JInternalFrame("Atualiza\u00E7\u00E3o");
				internalFrame.setClosable(true);
				internalFrame.setBounds(116, 88, 250, 217);
				contentPane.add(internalFrame);
				internalFrame.getContentPane().setLayout(null);

				lblFuncionrioDigiteSua_1 = new JLabel("Funcion\u00E1rio, digite sua senha:");
				lblFuncionrioDigiteSua_1.setBounds(10, 11, 179, 14);
				internalFrame.getContentPane().add(lblFuncionrioDigiteSua_1);

				lblSenha_1 = new JLabel("Senha:");
				lblSenha_1.setBounds(20, 36, 57, 14);
				internalFrame.getContentPane().add(lblSenha_1);

				passwordField_1 = new JPasswordField();
				passwordField_1.setBounds(111, 36, 86, 20);
				internalFrame.getContentPane().add(passwordField_1);

				lblDigiteOComplemento = new JLabel("Digite o complemento do aluno:");
				lblDigiteOComplemento.setBounds(10, 73, 202, 14);
				internalFrame.getContentPane().add(lblDigiteOComplemento);

				lblComplemento = new JLabel("Complemento:");
				lblComplemento.setBounds(20, 108, 94, 14);
				internalFrame.getContentPane().add(lblComplemento);

				tf_complemento = new JTextField();
				tf_complemento.setBounds(111, 105, 86, 20);
				internalFrame.getContentPane().add(tf_complemento);
				tf_complemento.setColumns(10);

				btnOk_1 = new JButton("OK");
				btnOk_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							if (tf_complemento.getText().equals("")|| passwordField.getText().equals("")){
								JOptionPane.showMessageDialog(null,"Todos os campos s�o obrigat�rios!");
							}else{
								fachada.atualizarInfoAluno(passwordField.getText(), tf_complemento.getText(), 4);
								JOptionPane.showMessageDialog(null,"O complemento do endere�o do aluno foi atualizado com sucesso!");

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
						} catch (RealizarLoginException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						} catch (TelefoneInvalidoException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				btnOk_1.setBounds(69, 154, 89, 23);
				internalFrame.getContentPane().add(btnOk_1);


				internalFrame.setVisible(true);
			}
		});
		rdbtnComplemento.setBounds(6, 193, 109, 23);
		contentPane.add(rdbtnComplemento);
		group.add(rdbtnComplemento);
		group.add(rdbtnComplemento);

		rdbtnCep = new JRadioButton("Cep");
		rdbtnCep.setEnabled(false);
		rdbtnCep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				internalFrame = new JInternalFrame("Atualiza\u00E7\u00E3o");
				internalFrame.setClosable(true);
				internalFrame.setBounds(116, 88, 250, 217);
				contentPane.add(internalFrame);
				internalFrame.getContentPane().setLayout(null);

				lblFuncionrioDigiteSua_1 = new JLabel("Funcion\u00E1rio, digite sua senha:");
				lblFuncionrioDigiteSua_1.setBounds(10, 11, 179, 14);
				internalFrame.getContentPane().add(lblFuncionrioDigiteSua_1);

				lblSenha_1 = new JLabel("Senha:");
				lblSenha_1.setBounds(44, 36, 57, 14);
				internalFrame.getContentPane().add(lblSenha_1);

				passwordField_1 = new JPasswordField();
				passwordField_1.setBounds(111, 36, 86, 20);
				internalFrame.getContentPane().add(passwordField_1);

				lblDigiteOCep = new JLabel("Digite o CEP do aluno:");
				lblDigiteOCep.setBounds(10, 77, 187, 14);
				internalFrame.getContentPane().add(lblDigiteOCep);

				lblCep = new JLabel("CEP:");
				lblCep.setBounds(55, 117, 46, 14);
				internalFrame.getContentPane().add(lblCep);

				tf_cep = new JTextField();
				tf_cep.setBounds(111, 114, 86, 20);
				internalFrame.getContentPane().add(tf_cep);
				tf_cep.setColumns(10);

				btnOk_2 = new JButton("OK");
				btnOk_2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						try {
							if(passwordField.getText().equals("")|| tf_cep.getText().equals("")){
								JOptionPane.showMessageDialog(null, "Todos os campos s�o obrigat�rios!");
							}else{
								fachada.atualizarInfoAluno(passwordField.getText(), tf_cep.getText(), 5);
								JOptionPane.showMessageDialog(null, "O cep do aluno foi atualizado com sucesso!");
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
						} catch (RealizarLoginException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						} catch (TelefoneInvalidoException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				btnOk_2.setBounds(81, 154, 89, 23);
				internalFrame.getContentPane().add(btnOk_2);



				internalFrame.setVisible(true);

			}
		});
		rdbtnCep.setBounds(6, 219, 109, 23);
		contentPane.add(rdbtnCep);
		group.add(rdbtnCep);

		rdbtnTelefone = new JRadioButton("Telefone");
		rdbtnTelefone.setEnabled(false);
		rdbtnTelefone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				internalFrame = new JInternalFrame("Atualiza\u00E7\u00E3o");
				internalFrame.setClosable(true);
				internalFrame.setBounds(116, 88, 250, 217);
				contentPane.add(internalFrame);
				internalFrame.getContentPane().setLayout(null);

				lblFuncionrioDigiteSua_1 = new JLabel("Funcion\u00E1rio, digite sua senha:");
				lblFuncionrioDigiteSua_1.setBounds(10, 11, 179, 14);
				internalFrame.getContentPane().add(lblFuncionrioDigiteSua_1);

				lblSenha_1 = new JLabel("Senha:");
				lblSenha_1.setBounds(44, 36, 57, 14);
				internalFrame.getContentPane().add(lblSenha_1);

				passwordField_1 = new JPasswordField();
				passwordField_1.setBounds(111, 36, 86, 20);
				internalFrame.getContentPane().add(passwordField_1);

				lblDigiteOTelefone = new JLabel("Digite o telefone do aluno:");
				lblDigiteOTelefone.setBounds(10, 75, 179, 14);
				internalFrame.getContentPane().add(lblDigiteOTelefone);

				lblTelefone = new JLabel("Telefone:");
				lblTelefone.setBounds(44, 109, 57, 14);
				internalFrame.getContentPane().add(lblTelefone);

				tf_telefone = new JTextField();
				tf_telefone.setBounds(111, 106, 86, 20);
				internalFrame.getContentPane().add(tf_telefone);
				tf_telefone.setColumns(10);

				btnOk_3 = new JButton("OK");
				btnOk_3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							if(tf_telefone.getText().equals("") || passwordField.getText().equals("")){
								JOptionPane.showMessageDialog(null, "Todos os campos s�o obrigat�rios!");
							}else{
								fachada.atualizarInfoAluno(passwordField.getText(), tf_telefone.getText(), 6);
								JOptionPane.showMessageDialog(null, "O telefone do aluno foi atualizado com sucesso!");
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
						} catch (RealizarLoginException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						} catch (TelefoneInvalidoException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				btnOk_3.setBounds(75, 154, 89, 23);
				internalFrame.getContentPane().add(btnOk_3);


				internalFrame.setVisible(true);

			}
		});
		rdbtnTelefone.setBounds(6, 245, 109, 23);
		contentPane.add(rdbtnTelefone);
		group.add(rdbtnTelefone);

		rdbtnSenha = new JRadioButton("Senha");
		rdbtnSenha.setEnabled(false);
		rdbtnSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				internalFrame = new JInternalFrame("Atualiza\u00E7\u00E3o");
				internalFrame.setClosable(true);
				internalFrame.setBounds(116, 88, 250, 217);
				contentPane.add(internalFrame);
				internalFrame.getContentPane().setLayout(null);

				lblFuncionrioDigiteSua_1 = new JLabel("Funcion\u00E1rio, digite sua senha:");
				lblFuncionrioDigiteSua_1.setBounds(10, 11, 179, 14);
				internalFrame.getContentPane().add(lblFuncionrioDigiteSua_1);

				lblSenha_1 = new JLabel("Senha:");
				lblSenha_1.setBounds(44, 36, 57, 14);
				internalFrame.getContentPane().add(lblSenha_1);

				passwordField_1 = new JPasswordField();
				passwordField_1.setBounds(111, 36, 86, 20);
				internalFrame.getContentPane().add(passwordField_1);

				lblDigiteANova = new JLabel("Digite a nova senha do aluno:");
				lblDigiteANova.setBounds(10, 75, 179, 14);
				internalFrame.getContentPane().add(lblDigiteANova);

				lblSenha_2 = new JLabel("Senha");
				lblSenha_2.setBounds(44, 114, 46, 14);
				internalFrame.getContentPane().add(lblSenha_2);

				btnOk_4 = new JButton("OK");
				btnOk_4.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							if(passwordField.getText().equals("") || tf_senha.getText().equals("")){
								JOptionPane.showMessageDialog(null, "Todos os campos s�o obrigat�rios!");
							}else{
								fachada.atualizarInfoAluno(passwordField.getText(), tf_senha.getText(), 7);
								JOptionPane.showMessageDialog(null, " A senha do aluno foi redefinida com sucesso!");
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
						} catch (RealizarLoginException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						} catch (TelefoneInvalidoException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				btnOk_4.setBounds(70, 154, 89, 23);
				internalFrame.getContentPane().add(btnOk_4);

				tf_senha = new JTextField();
				tf_senha.setBounds(111, 111, 86, 20);
				internalFrame.getContentPane().add(tf_senha);
				tf_senha.setColumns(10);


				internalFrame.setVisible(true);
			}
		});
		rdbtnSenha.setBounds(6, 271, 109, 23);
		contentPane.add(rdbtnSenha);
		group.add(rdbtnSenha);

		lblOQueDeseja = new JLabel("O que deseja atualizar?");
		lblOQueDeseja.setBounds(6, 94, 142, 14);
		contentPane.add(lblOQueDeseja);






	}
}
