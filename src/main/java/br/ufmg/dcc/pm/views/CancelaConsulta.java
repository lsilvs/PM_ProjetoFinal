package br.ufmg.dcc.pm.views;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import br.ufmg.dcc.pm.App;
import br.ufmg.dcc.pm.models.Consulta;
import br.ufmg.dcc.pm.modelsDao.ConsultaDAO;
import br.ufmg.dcc.pm.utils.DateUtils;
import br.ufmg.dcc.pm.utils.FormularioUtils;


public class CancelaConsulta {

	private JFrame frmAgendarConsulta; 
	private JTextField txtData;
	private JTextField txtHorario;
	private JLabel lblMensagem;

	/**
	 * Create the application.
	 */
	public CancelaConsulta() {
		frmAgendarConsulta = new JFrame("Cancelar consulta"); 
		frmAgendarConsulta.setBounds(100, 100, 442, 258);
		frmAgendarConsulta.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAgendarConsulta.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));

		lblMensagem = new JLabel("");
		frmAgendarConsulta.getContentPane().add(lblMensagem);
		
		JPanel panel_2 = FormularioUtils.generatePanel();
		frmAgendarConsulta.getContentPane().add(panel_2);

		JLabel lblData = new JLabel("Data  ");
		panel_2.add(lblData, "1, 1, right, fill");

		txtData = new JTextField();
		try {
			txtData = new JFormattedTextField(new MaskFormatter("HH/HH/HHHH"));
		} catch (ParseException e1) { 
			e1.printStackTrace();
		}
		panel_2.add(txtData, "2, 1, fill, default");

		JPanel panel_3 = FormularioUtils.generatePanel();
		frmAgendarConsulta.getContentPane().add(panel_3);

		JLabel lblHorario = new JLabel("Horário  ");
		panel_3.add(lblHorario, "1, 1, right, fill");

		txtHorario = new JTextField(); 
		try {
			txtHorario = new JFormattedTextField(new MaskFormatter("HH:HH"));
		} catch (ParseException e1) { 
			e1.printStackTrace();
		}
		panel_3.add(txtHorario, "2, 1, fill, default"); 
		

		JPanel panel_5 = new JPanel();
		frmAgendarConsulta.getContentPane().add(panel_5);
		
		JButton btnSalvar = new JButton("Buscar Consulta");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelaConsulta(); 
			} 
		});
		panel_5.add(btnSalvar);

	}

	protected void cancelaConsulta() {  
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date result = DateUtils.getCurrentDate();
		try {
			result =  df.parse(txtData.getText() + " " + txtHorario.getText());
			System.out.println(result.toString());
		} catch (ParseException e) { 
			e.printStackTrace();
			lblMensagem.setText("A data informada está invalida, por valor digite uma data válida.");
		}   
		
		List<Consulta> consultas = new ConsultaDAO().findByDateAndCliente(result, App.getCliente());
		if(consultas.isEmpty()){
			lblMensagem.setText("Não foi encontrada sua consulta nessa data e hora.");
		}else{
			for(Consulta c : consultas){
				System.out.println(c.getMedico().toString()); 
			}
			getFrame().dispose();
			VisualizarConsulta vc = new VisualizarConsulta(consultas.get(0));
			vc.getFrame().setVisible(true);
		} 
	}

	public JFrame getFrame() {
		return frmAgendarConsulta;
	}
}
