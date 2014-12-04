package br.ufmg.dcc.pm.views;

import java.awt.EventQueue;

import javax.swing.JFrame;

import br.ufmg.dcc.pm.models.Consulta;

public class VisualizarConsulta {

	private JFrame frame;
	private Consulta consultaAtual;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VisualizarConsulta window = new VisualizarConsulta();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VisualizarConsulta() {
		initialize();
	}
	
	/**
	 * Create the application.
	 */
	public VisualizarConsulta(Consulta consulta) {
		consultaAtual = consulta;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public JFrame getFrame() {
		return frame;
	}

}
