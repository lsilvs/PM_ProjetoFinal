package br.ufmg.dcc.pm.views;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import br.ufmg.dcc.pm.models.TipoExame;
import br.ufmg.dcc.pm.modelsDao.ExameDAO;
import br.ufmg.dcc.pm.modelsDao.TipoExameDAO;
import br.ufmg.dcc.pm.utils.DateUtils;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class AgendaExame {
	static final long ONE_MINUTE_IN_MILLIS = 60000; //millisecs
	static final long ONE_DAY_IN_MILLIS = ONE_MINUTE_IN_MILLIS * 60 * 24 ; //millisecs

	private JFrame frmAgendarExame;
	JComboBox<TipoExame> cbTipoExame;
	JComboBox<String> cbData;
	JComboBox<String> cbHorario;
	TipoExame exameEscolhido;
	ExameDAO exameDao;

	/**
	 * Create the frame.
	 */
	public AgendaExame() {
		frmAgendarExame = new JFrame("Cadastro Cliente");
		frmAgendarExame.setTitle("Agendar consulta");
		frmAgendarExame.setBounds(100, 100, 442, 258);
		frmAgendarExame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAgendarExame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		exameDao = new ExameDAO();

		JPanel panel_1 = generatePanel();
		frmAgendarExame.getContentPane().add(panel_1);

		JLabel lblExame = new JLabel("Exame  ");
		panel_1.add(lblExame, "1, 1, right, fill");

		cbTipoExame = new JComboBox<TipoExame>();
		panel_1.add(cbTipoExame, "2, 1, fill, default");

		JPanel panel_2 = generatePanel();
		frmAgendarExame.getContentPane().add(panel_2);

		JLabel lblData = new JLabel("Data  ");
		panel_2.add(lblData, "1, 1, right, fill");

		cbData = new JComboBox<String>();
		panel_2.add(cbData, "2, 1, fill, default");

		JPanel panel_3 = generatePanel();
		frmAgendarExame.getContentPane().add(panel_3);

		JLabel lblHorario = new JLabel("Horário  ");
		panel_3.add(lblHorario, "1, 1, right, fill");

		cbHorario = new JComboBox<String>();
		panel_3.add(cbHorario, "2, 1, fill, default");

		JPanel panel_5 = new JPanel();
		frmAgendarExame.getContentPane().add(panel_5);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_5.add(btnSalvar);

		setActions();
		populaExames();
	}

	public JFrame getFrame() {
		return frmAgendarExame;
	}

	private void populaExames() {
		TipoExameDAO tipoExame = new TipoExameDAO();
		for (TipoExame tipo : tipoExame.findAll())
			cbTipoExame.addItem(tipo);
	}

	private void setActions() {
		cbTipoExame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exameEscolhido = (TipoExame) cbTipoExame.getSelectedItem();
				atualizaCampoDataEHora();
			}
		});
		
		cbData.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				int posicaoData = cbData.getSelectedIndex()+1;
				Date data = DateUtils.addDays(DateUtils.getCurrentDate(), posicaoData);
			}
		});
	}

	@SuppressWarnings("deprecation")
	private void atualizaCampoDataEHora() {
		cbData.removeAllItems();
		cbHorario.removeAllItems();
		
		DateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat horaFormat = new SimpleDateFormat("HH:mm");
		DateFormat dbFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		date.setHours(8);
		date.setMinutes(0);
		date.setSeconds(0);
		
		List<Date> datas = exameDao.getScheduleByExame(exameEscolhido);
		
//		String dateString = new SimpleDateFormat("dd/MM/yyy HH:mm").format(consulta.getData());
//		Date data = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse("2014-12-1 08:30");

		for (int i = 0; i < 20; i++) {
			cbData.addItem(dataFormat.format(new Date(date.getTime() + (i * ONE_DAY_IN_MILLIS))));
		}
		
		for (int i = 0; i < 18; i++) {
			Date newData = new Date(date.getTime() + (i * 30 * ONE_MINUTE_IN_MILLIS));
			if(datas.contains(newData)) continue;
			cbHorario.addItem(horaFormat.format(newData));
		}
	}

	private JPanel generatePanel() {
		String encodedColumnSpec = "170px";
		String encodedColumnSpec2 = "260px";
		String encodedRowSpec = "40px";

		JPanel panel = new JPanel();

		panel.setLayout(new FormLayout(new ColumnSpec[] { ColumnSpec.decode(encodedColumnSpec),
				ColumnSpec.decode(encodedColumnSpec2), }, new RowSpec[] { RowSpec.decode(encodedRowSpec), }));

		return panel;
	}
}
