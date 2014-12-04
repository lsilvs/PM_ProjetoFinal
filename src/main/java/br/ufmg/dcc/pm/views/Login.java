package br.ufmg.dcc.pm.views;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import br.ufmg.dcc.pm.App;
import br.ufmg.dcc.pm.exceptions.InvalidCpfException;
import br.ufmg.dcc.pm.modelsDao.ClienteDAO;
import br.ufmg.dcc.pm.utils.CpfUtils;

public class Login {

	private JFrame frame;
	private JPanel panel; 
	private JLabel lblCpf;
	private JTextField txtCPF;  

	/**
	 * Create the application.
	 */
	public Login() { 
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Login");
		frame.setBounds(100, 100, 450, 72);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		
		lblCpf = new JLabel("CPF");
		panel.add(lblCpf);
		
		try {
			txtCPF = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
		} catch (ParseException e1) { 
			e1.printStackTrace();
		}
		panel.add(txtCPF);
		txtCPF.setColumns(10);
		
		JButton btnNewButton = new JButton("Entrar");
		panel.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				String cpf = txtCPF.getText();
				try {
					if(validaCpf(cpf)){
						if(!realizaLogin(cpf)){ 
							CadastroCliente cadastro = new CadastroCliente(cpf);
							cadastro.getFrame().setVisible(true); 
						}else{
							App.abreHome(cpf);
						}
						frame.dispose();
					}
				} catch (InvalidCpfException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				
			} 			
		});
	}

	private boolean validaCpf(String cpf) throws InvalidCpfException{
		if(cpf.equals("") || !CpfUtils.validaDigitos(cpf)) throw new InvalidCpfException();
		return true;
	}
	 

	private boolean realizaLogin(String cpf) {
		ClienteDAO cliente = new ClienteDAO(); 
		if(cliente.jaCadastrado(cpf)) return true; 
		return false;
	}
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
