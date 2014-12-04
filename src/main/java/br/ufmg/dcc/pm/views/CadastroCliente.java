package br.ufmg.dcc.pm.views;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import br.ufmg.dcc.pm.App;
import br.ufmg.dcc.pm.models.Cliente;
import br.ufmg.dcc.pm.modelsDao.ClienteDAO;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class CadastroCliente{
 
	private JFrame frame;  
	private JTextField txtNome;
	private JTextField txtIdentidade;
	private JTextField txtCPF;
	private JTextField txtDataNascimento;
	private JTextField txtTelefone;
	private JTextField txtEndereco;  

	/**
	 * Create the application.
	 */
	public CadastroCliente(String cpf) { 
		frame = new JFrame("Cadastro Cliente");
		frame.setBounds(100, 100, 442, 258);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		String encodedColumnSpec = "170px";
		String encodedColumnSpec2 = "260px";
		String encodedRowSpec = "40px";
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel); 
		
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode(encodedColumnSpec),
				ColumnSpec.decode(encodedColumnSpec2),},
			new RowSpec[] {
				RowSpec.decode(encodedRowSpec),}));
		
		JLabel lblNome = new JLabel("Nome  ");
		panel.add(lblNome, "1, 1, right, fill");
		
		txtNome = new JTextField();
		panel.add(txtNome, "2, 1, fill, center");
		txtNome.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode(encodedColumnSpec),
				ColumnSpec.decode(encodedColumnSpec2),},
			new RowSpec[] {
				RowSpec.decode(encodedRowSpec),}));
		
		JLabel lblIdentidade = new JLabel("Identidade  ");
		panel_1.add(lblIdentidade, "1, 1, right, fill");
		
		txtIdentidade = new JTextField();
		panel_1.add(txtIdentidade, "2, 1, fill, center");
		txtIdentidade.setColumns(10); 
		
		JPanel panel_2 = new JPanel();
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode(encodedColumnSpec),
				ColumnSpec.decode(encodedColumnSpec2),},
			new RowSpec[] {
				RowSpec.decode(encodedRowSpec),}));
		
		JLabel lblCpf = new JLabel("CPF  ");
		panel_2.add(lblCpf, "1, 1, right, fill");
		
		txtCPF = new JTextField(cpf);
		panel_2.add(txtCPF, "2, 1, fill, center");
		txtCPF.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode(encodedColumnSpec),
				ColumnSpec.decode(encodedColumnSpec2),},
			new RowSpec[] {
				RowSpec.decode(encodedRowSpec),}));
		
		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento  ");
		panel_3.add(lblDataDeNascimento, "1, 1, right, fill");
		
		try {
			txtDataNascimento = new JFormattedTextField(new MaskFormatter("##/##/####"));
		} catch (ParseException e1) { 
			e1.printStackTrace();
		}
		panel_3.add(txtDataNascimento, "2, 1, fill, center");
		txtDataNascimento.setColumns(10);
		
		JPanel panel_4 = new JPanel();
		frame.getContentPane().add(panel_4);
		panel_4.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode(encodedColumnSpec),
				ColumnSpec.decode(encodedColumnSpec2),},
			new RowSpec[] {
				RowSpec.decode(encodedRowSpec),}));
		
		JLabel lblTelefone = new JLabel("Telefone  ");
		panel_4.add(lblTelefone, "1, 1, right, fill");
		
		try {
			txtTelefone = new JFormattedTextField(new MaskFormatter("(##)####-####*"));
		} catch (ParseException e1) { 
			e1.printStackTrace();
		}
		panel_4.add(txtTelefone, "2, 1, fill, center");
		txtTelefone.setColumns(10);
		
		JPanel panel_6 = new JPanel();
		frame.getContentPane().add(panel_6);
		panel_6.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode(encodedColumnSpec),
				ColumnSpec.decode(encodedColumnSpec2),},
			new RowSpec[] {
				RowSpec.decode(encodedRowSpec),}));
		
		JLabel lblEndereco = new JLabel("Endereço  ");
		panel_6.add(lblEndereco, "1, 1, right, fill");
		
		txtEndereco = new JTextField();
		panel_6.add(txtEndereco, "2, 1, fill, center");
		txtEndereco.setColumns(10);
		 
		
		JPanel panel_5 = new JPanel();
		frame.getContentPane().add(panel_5);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvaCliente(); 
			} 
		});
		panel_5.add(btnSalvar);
	}
	
	public void salvaCliente() {
		String nome = txtNome.getText();
		String identidade = txtIdentidade.getText();
		String cpf = txtCPF.getText();
		String dataNascimento = txtDataNascimento.getText();
		String telefone = txtTelefone.getText();
		String endereco = txtEndereco.getText();
		
		ClienteDAO clienteDAO = new ClienteDAO();

		Cliente cliente = new Cliente(nome,identidade,cpf,dataNascimento,telefone,endereco);
		clienteDAO.saveOrUpdate(cliente);
		
		frame.dispose();  
		App.abreHome(cpf);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	
	public void closeWindow(){
		frame.dispose();
	}
}