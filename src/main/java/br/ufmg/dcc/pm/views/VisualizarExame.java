package br.ufmg.dcc.pm.views;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import br.ufmg.dcc.pm.models.Exame;
import br.ufmg.dcc.pm.modelsDao.ExameDAO;
import br.ufmg.dcc.pm.utils.DateUtils;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class VisualizarExame {

	private JFrame frame;
	private Exame exameAtual;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VisualizarExame window = new VisualizarExame();
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
	public VisualizarExame() {
		initialize();
	}
	
	/**
	 * Create the application.
	 */
	public VisualizarExame(Exame exame) {
		exameAtual = exame;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Visualização Exame");
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
		
		JLabel lblTipoExame = new JLabel("TipoExame");
		frame.getContentPane().add(lblTipoExame, "3, 1");
		
		JLabel lblNomeTipoExame = new JLabel(exameAtual.getTipoExame().toString());
		frame.getContentPane().add(lblNomeTipoExame, "5, 1");
		
		JLabel lblDataEHora = new JLabel("Data e Hora");
		frame.getContentPane().add(lblDataEHora, "3, 5");
		
		JLabel lblDataEHora_1 = new JLabel(DateUtils.formatData(exameAtual.getData(), "dd/MM/yyyy HH:MM"));
		frame.getContentPane().add(lblDataEHora_1, "5, 5");
		
		JLabel lblTipoPagamento = new JLabel("Tipo Pagamento");
		frame.getContentPane().add(lblTipoPagamento, "3, 7");
		
		JLabel lblTipoPagamento_1 = new JLabel(exameAtual.getTipo().toString());
		frame.getContentPane().add(lblTipoPagamento_1, "5, 7");
		
		JButton btnCancelar = new JButton("Cancelar Exame");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ExameDAO().delete(exameAtual);
				JOptionPane.showMessageDialog(null, "Exame cancelado com sucesso!");
				getFrame().dispose();
			}
		});
		frame.getContentPane().add(btnCancelar, "5, 9, left, default"); 
	}
	
	public JFrame getFrame() {
		return frame;
	}

}
