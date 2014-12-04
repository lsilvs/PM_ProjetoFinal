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
import br.ufmg.dcc.pm.models.Exame;
import br.ufmg.dcc.pm.modelsDao.ExameDAO;
import br.ufmg.dcc.pm.utils.DateUtils;
import br.ufmg.dcc.pm.utils.FormularioUtils;


public class CancelaExame {

	private JFrame frmAgendarExame; 
	private JTextField txtData;
	private JTextField txtHorario;
	private JLabel lblMensagem;

	/**
	 * Create the application.
	 */
	public CancelaExame() {
		frmAgendarExame = new JFrame("Cancelar exame"); 
		frmAgendarExame.setBounds(100, 100, 442, 258);
		frmAgendarExame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAgendarExame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));

		lblMensagem = new JLabel("");
		frmAgendarExame.getContentPane().add(lblMensagem);
		
		JPanel panel_2 = FormularioUtils.generatePanel();
		frmAgendarExame.getContentPane().add(panel_2);

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
		frmAgendarExame.getContentPane().add(panel_3);

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
		frmAgendarExame.getContentPane().add(panel_5);
		
		JButton btnSalvar = new JButton("Buscar Exame");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelaExame(); 
			} 
		});
		panel_5.add(btnSalvar);

	}

	protected void cancelaExame() {  
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date result = DateUtils.getCurrentDate();
		try {
			result =  df.parse(txtData.getText() + " " + txtHorario.getText());
			System.out.println(result.toString());
		} catch (ParseException e) { 
			e.printStackTrace();
			lblMensagem.setText("A data informada está invalida, por valor digite uma data válida.");
		}   
		
		List<Exame> exames = new ExameDAO().findByDateAndCliente(result, App.getCliente());
		if(exames.isEmpty()){
			lblMensagem.setText("Não foi encontrada sua exame nessa data e hora.");
		}else{
			for(Exame c : exames){
				System.out.println(c.getTipoExame().toString()); 
			}
			VisualizarExame vc = new VisualizarExame(exames.get(0));
			vc.getFrame().setVisible(true);
		} 
	}

	public JFrame getFrame() {
		return frmAgendarExame;
	}
}
