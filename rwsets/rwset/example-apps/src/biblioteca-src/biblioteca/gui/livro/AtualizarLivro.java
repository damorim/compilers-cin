package biblioteca.gui.livro;

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
import biblioteca.negocios.exceptions.livro.LivroIndisponivelException;
import biblioteca.negocios.exceptions.livro.LivroInvalidoException;
import biblioteca.negocios.exceptions.livro.LivroNaoEncontradoException;
import biblioteca.negocios.exceptions.pessoa.SenhaInvalidaException;

public class AtualizarLivro extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	private static Principal fachada;
	private JRadioButton rdbtnTtulo;
    private JRadioButton rdbtnAssunto;
    private JRadioButton rdbtnAutor;
    private JRadioButton rdbtnSinopse;
    private JInternalFrame internalFrame;
    private JLabel lblFuncionrioDigiteSua;
    private JLabel lblSenha;
    private JPasswordField passwordField;
    private JLabel lblDigiteOTtulo;
    private JLabel lblTtulo;
    private JTextField tf_titulo;
    private JButton btnOk_1;
    private JLabel lblDigiteONome;
    private JLabel lblAutor;
    private JTextField tf_autor;
    private JButton btnNewButton;
    private JLabel lblDigiteOAssunto;
    private JLabel lblAssunto;
    private JTextField tf_assunto;
    private JButton btnOk_2;
    private JLabel lblDigiteASinopse;
    private JLabel lblSinopse;
    private JTextField tf_sinopse;
    private JButton btnOk_3;
    private JRadioButton rdbtnAdicionarLivroNo;
    private JLabel lblDigi;
    private JLabel lblQuantidade;
    private JTextField tf_quantidade;
    private JButton btnOk_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AtualizarLivro frame = new AtualizarLivro();
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
	public AtualizarLivro() {
		fachada = Login.getFachada();
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 429, 308);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		this.setResizable(false);
		this.setTitle("Atualiza��o Livro");

		JLabel lblCdigo = new JLabel("Digite o c\u00F3digo do livro:");
		lblCdigo.setBounds(41, 11, 132, 14);
		contentPane.add(lblCdigo);


		final JTextField tf_codigo = new JTextField();
		tf_codigo.setBounds(183, 8, 86, 20);
		contentPane.add(tf_codigo);
		tf_codigo.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(tf_codigo.getText().equals("")){
						JOptionPane.showMessageDialog(null, "O campo c�digo � obrigat�rio!");
					}if(fachada.getSistemaLogado() == false){
						JOptionPane.showMessageDialog(null, "Para realizar esta a��o voc� deve est� logado no sistema");
						AtualizarLivro.this.dispose();
						Login frame = new Login();
						frame.setVisible(true);
					}else{
						
						JTextArea textArea = new JTextArea(6, 25);	
						textArea.setText(fachada.atualizarPrintDadosLivro(tf_codigo.getText()));
						textArea.setEditable(false);	     
					      JScrollPane scrollPane = new JScrollPane(textArea);    
					      JOptionPane.showMessageDialog(null, scrollPane, "Atualiza��o",JOptionPane.INFORMATION_MESSAGE);
					      
						rdbtnTtulo.setEnabled(true);
						rdbtnAutor.setEnabled(true);
						rdbtnAssunto.setEnabled(true);
						rdbtnSinopse.setEnabled(true);
						rdbtnAdicionarLivroNo.setEnabled(true);
					}
				
					
				} catch (LivroNaoEncontradoException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				} catch (IOException e) {
					// TODO Auto-generated catch block

				}
				
				
				
			}
		});
		btnOk.setBounds(115, 36, 89, 23);
		contentPane.add(btnOk);
		
		JLabel lblOQueVoc = new JLabel("O que voc\u00EA deseja alterar?");
		lblOQueVoc.setBounds(10, 75, 163, 14);
		contentPane.add(lblOQueVoc);
		
		 rdbtnTtulo = new JRadioButton("T\u00EDtulo");
		 rdbtnTtulo.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		internalFrame = new JInternalFrame("Atualiza\u00E7\u00E3o");
				internalFrame.setClosable(true);
				internalFrame.setBounds(179, 70, 243, 189); 
				contentPane.add(internalFrame);
				internalFrame.getContentPane().setLayout(null);
				
				lblFuncionrioDigiteSua = new JLabel("Funcion\u00E1rio, digite sua senha:");
				lblFuncionrioDigiteSua.setBounds(10, 11, 207, 14);
				internalFrame.getContentPane().add(lblFuncionrioDigiteSua);
				
				lblSenha = new JLabel("Senha:");
				lblSenha.setBounds(20, 36, 46, 14);
				internalFrame.getContentPane().add(lblSenha);
				
				passwordField = new JPasswordField();
				passwordField.setBounds(86, 36, 81, 20);
				internalFrame.getContentPane().add(passwordField);
				
				lblDigiteOTtulo = new JLabel("Digite o t\u00EDtulo do livro:");
				lblDigiteOTtulo.setBounds(10, 72, 124, 14);
				internalFrame.getContentPane().add(lblDigiteOTtulo);
				
				lblTtulo = new JLabel("T\u00EDtulo:");
				lblTtulo.setBounds(20, 103, 46, 14);
				internalFrame.getContentPane().add(lblTtulo);
				
				tf_titulo = new JTextField();
				tf_titulo.setBounds(86, 97, 81, 20);
				internalFrame.getContentPane().add(tf_titulo);
				tf_titulo.setColumns(10);
				
				btnOk_1 = new JButton("OK");
				btnOk_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							if(passwordField.getText().equals("") || tf_titulo.getText().equals("")){
								JOptionPane.showMessageDialog(null, "Todos os campos s�o obrigat�rios!");
							}else{
							fachada.atualizarInfoLivro(passwordField.getText(), tf_titulo.getText(),1);
							JOptionPane.showMessageDialog(null,"O t�tulo do livro foi atualizado com sucesso!");
							try { // Isto serve para fechar a janela
								internalFrame.setClosed(true);
							} catch (PropertyVetoException a) {
								// TODO Auto-generated catch block
								a.printStackTrace();
							}
							
							}
						} catch (SenhaInvalidaException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						} catch (RealizarLoginException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);

						} catch (LivroIndisponivelException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						} catch (LivroInvalidoException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						}
						
					}
				});
				btnOk_1.setBounds(78, 126, 89, 23);
				internalFrame.getContentPane().add(btnOk_1);
				internalFrame.setVisible(true);
		 	}
		 });
		rdbtnTtulo.setEnabled(false);
		rdbtnTtulo.setBounds(6, 96, 109, 23);
		contentPane.add(rdbtnTtulo);
		
		rdbtnAutor = new JRadioButton("Autor");
		rdbtnAutor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 internalFrame = new JInternalFrame("Atualiza\u00E7\u00E3o");
					internalFrame.setClosable(true);
					internalFrame.setBounds(179, 70, 243, 189);
					contentPane.add(internalFrame);
					internalFrame.getContentPane().setLayout(null);
					
					lblFuncionrioDigiteSua = new JLabel("Funcion\u00E1rio, digite sua senha:");
					lblFuncionrioDigiteSua.setBounds(10, 11, 207, 14);
					internalFrame.getContentPane().add(lblFuncionrioDigiteSua);
					
					lblSenha = new JLabel("Senha:");
					lblSenha.setBounds(48, 36, 46, 14);
					internalFrame.getContentPane().add(lblSenha);
					
					passwordField = new JPasswordField();
					passwordField.setBounds(120, 33, 81, 20);
					internalFrame.getContentPane().add(passwordField);
					
					lblDigiteONome = new JLabel("Digite o nome do autor do livro:");
					lblDigiteONome.setBounds(10, 70, 207, 14);
					internalFrame.getContentPane().add(lblDigiteONome);
					
					lblAutor = new JLabel("Autor:");
					lblAutor.setBounds(48, 98, 46, 14);
					internalFrame.getContentPane().add(lblAutor);
					
					tf_autor = new JTextField();
					tf_autor.setBounds(115, 95, 86, 20);
					internalFrame.getContentPane().add(tf_autor);
					tf_autor.setColumns(10);
					
					btnNewButton = new JButton("OK");
					btnNewButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try {
								if(passwordField.getText().equals("") || tf_autor.getText().equals("")){
									JOptionPane.showMessageDialog(null, "Todos os campos s�o obrigat�rios!");
								}else{
								fachada.atualizarInfoLivro(passwordField.getText(), tf_autor.getText(), 2);
								JOptionPane.showMessageDialog(null, "O autor do livro foi atualizado com sucesso!");
								try { // Isto serve para fechar a janela
									internalFrame.setClosed(true);
								} catch (PropertyVetoException a) {
									// TODO Auto-generated catch block
									a.printStackTrace();
								}
								}
							} catch (SenhaInvalidaException ex) {
								JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
							} catch (RealizarLoginException ex) {
								JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
							} catch (LivroIndisponivelException ex) {
								JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
							} catch (LivroInvalidoException ex) {
								JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
							}
						}
					});
					btnNewButton.setBounds(78, 126, 89, 23);
					internalFrame.getContentPane().add(btnNewButton);
					
					
					internalFrame.setVisible(true);
			}
		});
		rdbtnAutor.setEnabled(false);
		rdbtnAutor.setBounds(6, 122, 109, 23);
		contentPane.add(rdbtnAutor);
		
		 rdbtnAssunto = new JRadioButton("Assunto");
		 rdbtnAssunto.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		internalFrame = new JInternalFrame("Atualiza\u00E7\u00E3o");
				internalFrame.setClosable(true);
				internalFrame.setBounds(179, 70, 243, 189);
				contentPane.add(internalFrame);
				internalFrame.getContentPane().setLayout(null);
				
				lblFuncionrioDigiteSua = new JLabel("Funcion\u00E1rio, digite sua senha:");
				lblFuncionrioDigiteSua.setBounds(10, 11, 207, 14);
				internalFrame.getContentPane().add(lblFuncionrioDigiteSua);
				
				lblSenha = new JLabel("Senha:");
				lblSenha.setBounds(48, 36, 46, 14);
				internalFrame.getContentPane().add(lblSenha);
				
				passwordField = new JPasswordField();
				passwordField.setBounds(120, 33, 81, 20);
				internalFrame.getContentPane().add(passwordField);
				
				lblDigiteOAssunto = new JLabel("Digite o assunto do livro:");
				lblDigiteOAssunto.setBounds(10, 71, 154, 14);
				internalFrame.getContentPane().add(lblDigiteOAssunto);
				
				lblAssunto = new JLabel("Assunto:");
				lblAssunto.setBounds(48, 96, 59, 14);
				internalFrame.getContentPane().add(lblAssunto);
				
				tf_assunto = new JTextField();
				tf_assunto.setBounds(120, 96, 81, 20);
				internalFrame.getContentPane().add(tf_assunto);
				tf_assunto.setColumns(10);
				
				btnOk_2 = new JButton("OK");
				btnOk_2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							if(passwordField.getText().equals("") || tf_assunto.getText().equals("")){
								JOptionPane.showMessageDialog(null, "Todos os campos s�o obrigat�rios!");
							}else{
							fachada.atualizarInfoLivro(passwordField.getText(), tf_assunto.getText(), 3);
							JOptionPane.showMessageDialog(null, "Assunto do livro alterado com sucesso!");
							try { // Isto serve para fechar a janela
								internalFrame.setClosed(true);
							} catch (PropertyVetoException a) {
								// TODO Auto-generated catch block
								a.printStackTrace();
							}
							}
						} catch (SenhaInvalidaException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						} catch (RealizarLoginException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						} catch (LivroIndisponivelException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						} catch (LivroInvalidoException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				btnOk_2.setBounds(75, 126, 89, 23);
				internalFrame.getContentPane().add(btnOk_2);
				
				
				
				internalFrame.setVisible(true);
		 	}
		 });
		rdbtnAssunto.setEnabled(false);
		rdbtnAssunto.setBounds(6, 148, 109, 23);
		contentPane.add(rdbtnAssunto);
		
		rdbtnSinopse = new JRadioButton("Sinopse");
		rdbtnSinopse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				internalFrame = new JInternalFrame("Atualiza\u00E7\u00E3o");
					internalFrame.setClosable(true);
					internalFrame.setBounds(179, 70, 235, 189);
					contentPane.add(internalFrame);
					internalFrame.getContentPane().setLayout(null);
					
					lblFuncionrioDigiteSua = new JLabel("Funcion\u00E1rio, digite sua senha:");
					lblFuncionrioDigiteSua.setBounds(10, 11, 207, 14);
					internalFrame.getContentPane().add(lblFuncionrioDigiteSua);
					
					lblSenha = new JLabel("Senha:");
					lblSenha.setBounds(48, 36, 62, 14);
					internalFrame.getContentPane().add(lblSenha);
					
					passwordField = new JPasswordField();
					passwordField.setBounds(120, 33, 81, 20);
					internalFrame.getContentPane().add(passwordField);
					
					lblDigiteASinopse = new JLabel("Digite a sinopse do livro:");
					lblDigiteASinopse.setBounds(10, 70, 174, 14);
					internalFrame.getContentPane().add(lblDigiteASinopse);
					
					lblSinopse = new JLabel("Sinopse:");
					lblSinopse.setBounds(48, 95, 62, 14);
					internalFrame.getContentPane().add(lblSinopse);
					
					tf_sinopse = new JTextField();
					tf_sinopse.setBounds(120, 95, 81, 20);
					internalFrame.getContentPane().add(tf_sinopse);
					tf_sinopse.setColumns(10);
					
					btnOk_3 = new JButton("OK");
					btnOk_3.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
							try {
								if(passwordField.getText().equals("") || tf_sinopse.getText().equals("")){
									JOptionPane.showMessageDialog(null, "Todos os campos s�o obrigat�rios!");
								}else{
								fachada.atualizarInfoLivro(passwordField.getText(),  tf_sinopse.getText(), 4);
								JOptionPane.showMessageDialog(null, "Sinopse alterada com sucesso!");
								try { // Isto serve para fechar a janela
									internalFrame.setClosed(true);
								} catch (PropertyVetoException a) {
									// TODO Auto-generated catch block
									a.printStackTrace();
								}
								}
							} catch (SenhaInvalidaException ex) {
								JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
							} catch (RealizarLoginException ex) {
								JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
							} catch (LivroIndisponivelException ex) {
								JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
							} catch (LivroInvalidoException ex) {
								JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
							}
						}
					});
					btnOk_3.setBounds(77, 126, 89, 23);
					internalFrame.getContentPane().add(btnOk_3);
					
					
					
					
					
					internalFrame.setVisible(true);
			}
		});
		rdbtnSinopse.setEnabled(false);
		rdbtnSinopse.setBounds(6, 172, 109, 23);
		contentPane.add(rdbtnSinopse);

		rdbtnAdicionarLivroNo = new JRadioButton("Adicionar livro no acervo");
		rdbtnAdicionarLivroNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				internalFrame = new JInternalFrame("Atualiza\u00E7\u00E3o");
		internalFrame.setClosable(true);
		internalFrame.setBounds(179, 70, 230, 189);
		contentPane.add(internalFrame);
		internalFrame.getContentPane().setLayout(null);
		
		lblFuncionrioDigiteSua = new JLabel("Funcion\u00E1rio, digite sua senha:");
		lblFuncionrioDigiteSua.setBounds(10, 11, 207, 14);
		internalFrame.getContentPane().add(lblFuncionrioDigiteSua);
		
		lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(43, 36, 46, 14);
		internalFrame.getContentPane().add(lblSenha);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(120, 33, 81, 20);
		internalFrame.getContentPane().add(passwordField);
		
		lblDigi = new JLabel("Digite a nova quantidade :");
		lblDigi.setBounds(10, 61, 191, 14);
		internalFrame.getContentPane().add(lblDigi);
		
		lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setBounds(20, 96, 89, 14);
		internalFrame.getContentPane().add(lblQuantidade);
		
		tf_quantidade = new JTextField();
		tf_quantidade.setBounds(115, 93, 86, 20);
		internalFrame.getContentPane().add(tf_quantidade);
		tf_quantidade.setColumns(10);
		
		btnOk_4 = new JButton("OK");
		btnOk_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(passwordField.getText().equals("") || tf_quantidade.getText().equals("")){
						JOptionPane.showMessageDialog(null, "Todos os campos s�o obrigat�rios!");
					}else{
					fachada.atualizarInfoLivro(passwordField.getText(), ("" + tf_quantidade.getText()), 5);
					JOptionPane.showMessageDialog(null, "A quantidade de livros foi atualizada no acervo!");
					try { // Isto serve para fechar a janela
						internalFrame.setClosed(true);
					} catch (PropertyVetoException a) {
						// TODO Auto-generated catch block
						a.printStackTrace();
					}
					}
				} catch (SenhaInvalidaException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				} catch (RealizarLoginException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				} catch (LivroIndisponivelException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				} catch (LivroInvalidoException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnOk_4.setBounds(68, 126, 89, 23);
		internalFrame.getContentPane().add(btnOk_4);
		
		
		
		
		
		
		
		internalFrame.setVisible(true);
				
			}
		});
		rdbtnAdicionarLivroNo.setEnabled(false);
	    rdbtnAdicionarLivroNo.setBounds(6, 198, 167, 23);
	    contentPane.add(rdbtnAdicionarLivroNo);
	    
	    
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnTtulo);
		group.add(rdbtnAutor);
		group.add(rdbtnAssunto);
		group.add(rdbtnSinopse);
		group.add(rdbtnAdicionarLivroNo);
	    
	    
		
 

		}
	}
