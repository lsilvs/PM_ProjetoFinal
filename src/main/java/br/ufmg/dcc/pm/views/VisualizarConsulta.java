package br.ufmg.dcc.pm.views;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import br.ufmg.dcc.pm.models.Consulta;
import br.ufmg.dcc.pm.modelsDao.ConsultaDAO;
import br.ufmg.dcc.pm.utils.DateUtils;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

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
		frame = new JFrame("Visualização Consulta");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("max(7dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(58dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("439px"),},
			new RowSpec[] {
				RowSpec.decode("max(17dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(17dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("19dlu"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(22dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblMedico = new JLabel("Medico");
		frame.getContentPane().add(lblMedico, "3, 1");
		
		JLabel lblNomeMedico = new JLabel(consultaAtual.getMedico().toString());
		frame.getContentPane().add(lblNomeMedico, "5, 1");
		
		JLabel lblEspecialidade = new JLabel("Especialidade");
		frame.getContentPane().add(lblEspecialidade, "3, 3");
		
		JLabel lblEspecialidadeMedico = new JLabel(consultaAtual.getMedico().getEspecialidade().toString());
		frame.getContentPane().add(lblEspecialidadeMedico, "5, 3");
		
		JLabel lblDataEHora = new JLabel("Data e Hora");
		frame.getContentPane().add(lblDataEHora, "3, 5");
		
		JLabel lblDataEHora_1 = new JLabel(DateUtils.formatData(consultaAtual.getData(), "dd/MM/yyyy HH:MM"));
		frame.getContentPane().add(lblDataEHora_1, "5, 5");
		
		JLabel lblTipoPagamento = new JLabel("Tipo Pagamento");
		frame.getContentPane().add(lblTipoPagamento, "3, 7");
		
		JLabel lblTipoPagamento_1 = new JLabel(consultaAtual.getTipo().toString());
		frame.getContentPane().add(lblTipoPagamento_1, "5, 7");
		
		JButton btnCancelar = new JButton("Cancelar Consulta");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ConsultaDAO().delete(consultaAtual);
				getFrame().dispose();
			}
		});
		frame.getContentPane().add(btnCancelar, "5, 9, left, default");
		 
		 
		
		 
	}
	
	public JFrame getFrame() {
		return frame;
	}

}
