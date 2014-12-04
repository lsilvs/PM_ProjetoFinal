package br.ufmg.dcc.pm.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class Home {

	private JFrame frmEscolhaUmaAo;
	private String mensagem;

	/**
	 * Create the application.
	 */
	/**
	 * @wbp.parser.constructor
	 */
	public Home() {
		initialize();
	}
	
	public Home(String _mensagem) {
		mensagem = _mensagem; 
		initialize();
	}

	public JFrame getFrame() {
		return frmEscolhaUmaAo;
	}

	public void setFrame(JFrame frame) {
		this.frmEscolhaUmaAo = frame;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEscolhaUmaAo = new JFrame();
		frmEscolhaUmaAo.setTitle("Escolha uma ação");
		frmEscolhaUmaAo.setBounds(100, 100, 469, 184);
		frmEscolhaUmaAo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEscolhaUmaAo.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("469px"),},
			new RowSpec[] {
				RowSpec.decode("36px"),
				RowSpec.decode("78px"),}));
		
		JPanel panel_1 = new JPanel();
		frmEscolhaUmaAo.getContentPane().add(panel_1, "1, 1, fill, fill"); 
		panel_1.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("469px"),},
			new RowSpec[] {
				RowSpec.decode("31px"),}));
		
		JLabel lblMensagem = new JLabel("");
		panel_1.add(lblMensagem, "1, 1, center, center");
		if(mensagem != null){
			lblMensagem.setText(mensagem);
		}
		
		JPanel panel = new JPanel();
		frmEscolhaUmaAo.getContentPane().add(panel, "1, 2, fill, fill");
		
		JButton btnCadastrar = new JButton("Cadastrar Consulta");
		panel.add(btnCadastrar);
		
		JButton btnPedidoDeExame = new JButton("Pedido de Exame");
		panel.add(btnPedidoDeExame);
		
		JButton btnCancelarConsulta = new JButton("Cancelar Consulta");
		btnCancelarConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				getFrame().dispose(); 
				CancelaConsulta agenda = new CancelaConsulta();
				agenda.getFrame().setVisible(true);
			}
		});
		panel.add(btnCancelarConsulta);
		
		JButton btnCancelarExame = new JButton("Cancelar Exame");
		panel.add(btnCancelarExame);
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				getFrame().dispose(); 
				AgendaConsulta agenda = new AgendaConsulta();
				agenda.getFrame().setVisible(true);
			}
		});
	}

}
