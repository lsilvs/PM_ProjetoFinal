package br.ufmg.dcc.pm.views;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import br.ufmg.dcc.pm.App;
import br.ufmg.dcc.pm.models.Atendimento;
import br.ufmg.dcc.pm.models.Consulta;
import br.ufmg.dcc.pm.models.Especialidade;
import br.ufmg.dcc.pm.models.Medico;
import br.ufmg.dcc.pm.modelsDao.ConsultaDAO;
import br.ufmg.dcc.pm.modelsDao.EspecialidadeDAO;
import br.ufmg.dcc.pm.modelsDao.MedicoDAO;
import br.ufmg.dcc.pm.utils.DateUtils;
import br.ufmg.dcc.pm.utils.FormularioUtils;

public class AgendaConsulta {

	private JFrame frmAgendarConsulta;
	JComboBox<Especialidade> cbEspecialidade;
	JComboBox<Medico> cbMedico;
	JComboBox<String> cbData;
	JComboBox<String> cbHorario;
	JComboBox<String> cbTiposPagamento;
	JButton btnSalvar;
	Medico medicoEscolhido;
	Date dataSelecionada;

	/**
	 * Create the frame.
	 */
	public AgendaConsulta() {
		frmAgendarConsulta = new JFrame("Agendar consulta"); 
		frmAgendarConsulta.setBounds(100, 100, 442, 258);
		frmAgendarConsulta.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAgendarConsulta.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel = FormularioUtils.generatePanel();
		frmAgendarConsulta.getContentPane().add(panel);

		JLabel lblEspecialidade = new JLabel("Especialidade  ");
		panel.add(lblEspecialidade, "1, 1, right, fill");

		cbEspecialidade = new JComboBox<Especialidade>();
		panel.add(cbEspecialidade, "2, 1, fill, default");

		JPanel panel_1 = FormularioUtils.generatePanel();
		frmAgendarConsulta.getContentPane().add(panel_1);

		JLabel lblMedico = new JLabel("Médico  ");
		panel_1.add(lblMedico, "1, 1, right, fill");

		cbMedico = new JComboBox<Medico>();
		panel_1.add(cbMedico, "2, 1, fill, default");

		JPanel panel_2 = FormularioUtils.generatePanel();
		frmAgendarConsulta.getContentPane().add(panel_2);

		JLabel lblData = new JLabel("Data  ");
		panel_2.add(lblData, "1, 1, right, fill");

		cbData = new JComboBox<String>();
		panel_2.add(cbData, "2, 1, fill, default");

		JPanel panel_3 = FormularioUtils.generatePanel();
		frmAgendarConsulta.getContentPane().add(panel_3);

		JLabel lblHorario = new JLabel("Horário  ");
		panel_3.add(lblHorario, "1, 1, right, fill");

		cbHorario = new JComboBox<String>();
		panel_3.add(cbHorario, "2, 1, fill, default");
		
		JPanel panelTipo = FormularioUtils.generatePanel();
		frmAgendarConsulta.getContentPane().add(panelTipo);

		JLabel lblTipoPagamento = new JLabel("Tipo de Pagamento  ");
		panelTipo.add(lblTipoPagamento, "1, 1, right, fill");

		cbTiposPagamento = new JComboBox<String>(Atendimento.TIPOS_PAGAMENTO);
		panelTipo.add(cbTiposPagamento, "2, 1, fill, default");

		JPanel panel_5 = new JPanel();
		frmAgendarConsulta.getContentPane().add(panel_5);

		btnSalvar = new JButton("Salvar");
		panel_5.add(btnSalvar);

		// Ações do Formulário
		setActions();
		populaEspecialidades();
	}

	public JFrame getFrame() {
		return frmAgendarConsulta;
	}

	private void populaEspecialidades() { 
		for (Especialidade s : new EspecialidadeDAO().findAllHaveMedico())
			cbEspecialidade.addItem(s);
	}

	private void setActions() {
		cbEspecialidade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				cbMedico.removeAllItems();
				Especialidade choosed = (Especialidade) cbEspecialidade.getSelectedItem();
				List<Medico> medicoPorEspecialidade = new MedicoDAO().findAllByEspecialidade(choosed);
				for (Medico medico : medicoPorEspecialidade) cbMedico.addItem(medico);
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

				cbHorario.removeAllItems();

				int posicaoData = cbData.getSelectedIndex() + 1; 
				dataSelecionada = DateUtils.addDays(DateUtils.getCurrentDate(), posicaoData);

				if(medicoEscolhido != null){
					List<Consulta> consultas = new ConsultaDAO().findAllByDateAndMedico(dataSelecionada, medicoEscolhido);
					List<Date> disponiveis = criaRangeHorarios(dataSelecionada);
					List<Date> horariosJaMarcados = new ArrayList<Date>();
	
					for (Consulta co : consultas)
						for (Date d : disponiveis)
							if (co.getData().compareTo(d) == 0)
								horariosJaMarcados.add(d);
	
					disponiveis.removeAll(horariosJaMarcados);
	
					for (Date d : disponiveis)
						cbHorario.addItem(DateUtils.formatData(d, "HH:mm"));
				}

			}
		});

		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				realizarAgendamento();
			}
		});
	}

	private List<Date> criaRangeHorarios(Date data) {
		List<Date> rangeHorarios = new ArrayList<Date>();
		for (int i = 8; i < 17; i++) {
			rangeHorarios.add(DateUtils.setHourMinuteSecond(data, i, 0, 0));
			rangeHorarios.add(DateUtils.setHourMinuteSecond(data, i, 30, 0));
		}
		return rangeHorarios;

	}

	private void atualizaCampoDataEHora() {
		cbData.removeAllItems();
		cbHorario.removeAllItems();
		
		// Pega os próximos 20 dias para marcar consulta
		for (int i = 1; i < 20; i++)
			cbData.addItem(DateUtils.formatData(DateUtils.addDays(DateUtils.getCurrentDate(), i), "dd/MM/yyyy"));
	}

	private void realizarAgendamento() {
		Date data = dataSelecionada;
		String hora = (String) cbHorario.getSelectedItem();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date result = data;
		try {
			result =  df.parse(DateUtils.formatData(data, "dd/MM/yyyy") + " " + hora);
		} catch (ParseException e) { 
			e.printStackTrace();
		}    
		Consulta consulta = new Consulta(App.getCliente(), medicoEscolhido, result, cbTiposPagamento.getSelectedItem().toString());
		ConsultaDAO consultaDao = new ConsultaDAO();
		consultaDao.create(consulta);  
		
		getFrame().dispose();
		String msg_resultado = "";
		
		if(consulta.getAprovado()){
			msg_resultado = "Seu agendamento foi realizado com sucesso!";
		}else{
			msg_resultado = "Infelizmente não pudemos realizar seu agendamento\n";
			if( consulta.getTipo().equals("convenio"))
				msg_resultado += "O Convenio não aprovou a consulta";
			else if(consulta.getTipo().equals("cortesia") )
				msg_resultado += "A diretoria não aprovou a consulta";
			else if(consulta.getTipo().equals("cheque"))
				msg_resultado += "Seu cheque não foi aprovado a consulta";
		}
		JOptionPane.showMessageDialog(null, msg_resultado); 
	}
}
