package br.ufmg.dcc.pm.views;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Home {

	private JFrame frame;

	/**
	 * Create the application.
	 */
	public Home() {
		initialize();
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 77);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		
		JButton btnCadastrar = new JButton("Cadastrar Consulta");
		panel.add(btnCadastrar);
		
		JButton btnPedidoDeExame = new JButton("Pedido de Exame");
		panel.add(btnPedidoDeExame);
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
	}

}
