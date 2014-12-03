package br.ufmg.dcc.pm.views;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import br.ufmg.dcc.pm.models.Especialidade;
import br.ufmg.dcc.pm.models.Medico;
import br.ufmg.dcc.pm.modelsDao.EspecialidadeDAO;
import br.ufmg.dcc.pm.modelsDao.MedicoDAO;
import br.ufmg.dcc.pm.utils.DateUtils;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class AgendaConsulta {

	private JFrame frmAgendarConsulta;
	JComboBox<Especialidade> cbEspecialidade;
	JComboBox<Medico> cbMedico;
	JComboBox<String> cbData;
	JComboBox cbHorario;
	Medico medicoEscolhido;

	/**
	 * Create the frame.
	 */
	public AgendaConsulta() {
		frmAgendarConsulta = new JFrame("Cadastro Cliente");
		frmAgendarConsulta.setTitle("Agendar consulta");
		frmAgendarConsulta.setBounds(100, 100, 442, 258);
		frmAgendarConsulta.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAgendarConsulta.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel = generatePanel();
		frmAgendarConsulta.getContentPane().add(panel);

		JLabel lblEspecialidade = new JLabel("Especialidade  ");
		panel.add(lblEspecialidade, "1, 1, right, fill");

		cbEspecialidade = new JComboBox();
		panel.add(cbEspecialidade, "2, 1, fill, default");

		JPanel panel_1 = generatePanel();
		frmAgendarConsulta.getContentPane().add(panel_1);

		JLabel lblMedico = new JLabel("Médico  ");
		panel_1.add(lblMedico, "1, 1, right, fill");

		cbMedico = new JComboBox<Medico>();
		panel_1.add(cbMedico, "2, 1, fill, default");

		JPanel panel_2 = generatePanel();
		frmAgendarConsulta.getContentPane().add(panel_2);

		JLabel lblData = new JLabel("Data  ");
		panel_2.add(lblData, "1, 1, right, fill");

		cbData = new JComboBox();
		panel_2.add(cbData, "2, 1, fill, default");

		JPanel panel_3 = generatePanel();
		frmAgendarConsulta.getContentPane().add(panel_3);

		JLabel lblHorario = new JLabel("Horário  ");
		panel_3.add(lblHorario, "1, 1, right, fill");

		cbHorario = new JComboBox();
		panel_3.add(cbHorario, "2, 1, fill, default");

		JPanel panel_5 = new JPanel();
		frmAgendarConsulta.getContentPane().add(panel_5);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_5.add(btnSalvar);

		// Ações do Formulário 
		setActions();
		populaEspecialidades();
	}

	public JFrame getFrame() {
		return frmAgendarConsulta;
	}

	private void populaEspecialidades() {
		EspecialidadeDAO especialidade = new EspecialidadeDAO();
		for (Especialidade s : especialidade.findAllWithMedico())
			cbEspecialidade.addItem(s);
	}

	private void setActions() {
		cbEspecialidade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				cbMedico.removeAllItems();

				JComboBox<Especialidade> cb = (JComboBox<Especialidade>) e.getSource();
				Especialidade choosed = (Especialidade) cb.getSelectedItem();

				List<Medico> medicoPorEspecialidade = new MedicoDAO().findAllByEspecialidade(choosed);
				for (Medico medico : medicoPorEspecialidade)
					cbMedico.addItem(medico);
			}
		});

		cbMedico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				medicoEscolhido = (Medico) cbMedico.getSelectedItem();
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

	private void atualizaCampoDataEHora() {
		cbData.removeAllItems();
		cbHorario.removeAllItems();

		for (int i = 1; i < 20; i++)
			cbData.addItem(DateUtils.formatData(DateUtils.addDays(DateUtils.getCurrentDate(), i), "dd/MM/yyyy"));
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
