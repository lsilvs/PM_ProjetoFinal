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

public class Home implements ActionListener {

	private JFrame frmEscolhaUmaAo;
	private String mensagem;
	private JButton btnCancelarExame;
	private JButton btnCadastrar;
	private JButton btnPedidoDeExame;
	private JButton btnCancelarConsulta;

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
		
		btnCadastrar = new JButton("Cadastrar Consulta");
		btnCadastrar.addActionListener(this);
		panel.add(btnCadastrar);
		
		btnPedidoDeExame = new JButton("Pedido de Exame");
		btnPedidoDeExame.addActionListener(this);
		panel.add(btnPedidoDeExame);
		
		btnCancelarConsulta = new JButton("Cancelar Consulta");
		btnCancelarConsulta.addActionListener(this);
		panel.add(btnCancelarConsulta);
		
		btnCancelarExame = new JButton("Cancelar Exame");
		btnCancelarExame.addActionListener(this);
		panel.add(btnCancelarExame);
	}

	public void actionPerformed(ActionEvent e) {
		if(btnCancelarExame == e.getSource()){
			CancelaExame exame = new CancelaExame();
			exame.getFrame().setVisible(true);
		} 
		else if(btnCancelarConsulta == e.getSource()){
			CancelaConsulta agenda = new CancelaConsulta();
			agenda.getFrame().setVisible(true);
		}
		else if(btnPedidoDeExame == e.getSource()){
			AgendaExame agendaExame = new AgendaExame();
			agendaExame.getFrame().setVisible(true);
		}else if(btnCadastrar == e.getSource()){
			AgendaConsulta agenda = new AgendaConsulta();
			agenda.getFrame().setVisible(true);
		}
				
	} 

}
