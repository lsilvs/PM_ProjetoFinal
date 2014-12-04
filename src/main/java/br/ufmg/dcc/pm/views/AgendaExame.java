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
import br.ufmg.dcc.pm.models.Exame;
import br.ufmg.dcc.pm.models.TipoExame;
import br.ufmg.dcc.pm.modelsDao.ExameDAO;
import br.ufmg.dcc.pm.modelsDao.TipoExameDAO;
import br.ufmg.dcc.pm.utils.DateUtils;
import br.ufmg.dcc.pm.utils.FormularioUtils;

public class AgendaExame {

	private JFrame frmAgendarExame;
	JComboBox<TipoExame> cbTipoExame;
	JComboBox<String> cbData;
	JComboBox<String> cbHorario;
	JComboBox<String> cbTiposPagamento;
	JButton btnSalvar;
	TipoExame tipoExameEscolhido;
	Date dataSelecionada;

	/**
	 * Create the frame.
	 */
	public AgendaExame() {
		frmAgendarExame = new JFrame("Cadastro Cliente");
		frmAgendarExame.setTitle("Agendar exame");
		frmAgendarExame.setBounds(100, 100, 442, 258);
		frmAgendarExame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAgendarExame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel_1 = FormularioUtils.generatePanel();
		frmAgendarExame.getContentPane().add(panel_1);

		JLabel lblTipoExame = new JLabel("Exame  ");
		panel_1.add(lblTipoExame, "1, 1, right, fill");

		cbTipoExame = new JComboBox<TipoExame>();
		panel_1.add(cbTipoExame, "2, 1, fill, default");

		JPanel panel_2 = FormularioUtils.generatePanel();
		frmAgendarExame.getContentPane().add(panel_2);

		JLabel lblData = new JLabel("Data  ");
		panel_2.add(lblData, "1, 1, right, fill");

		cbData = new JComboBox<String>();
		panel_2.add(cbData, "2, 1, fill, default");

		JPanel panel_3 = FormularioUtils.generatePanel();
		frmAgendarExame.getContentPane().add(panel_3);

		JLabel lblHorario = new JLabel("Horário  ");
		panel_3.add(lblHorario, "1, 1, right, fill");

		cbHorario = new JComboBox<String>();
		panel_3.add(cbHorario, "2, 1, fill, default");
		
		JPanel panelTipo = FormularioUtils.generatePanel();
		frmAgendarExame.getContentPane().add(panelTipo);

		JLabel lblTipoPagamento = new JLabel("Tipo de Pagamento  ");
		panelTipo.add(lblTipoPagamento, "1, 1, right, fill");

		cbTiposPagamento = new JComboBox<String>(Atendimento.TIPOS_PAGAMENTO);
		panelTipo.add(cbTiposPagamento, "2, 1, fill, default");

		JPanel panel_5 = new JPanel();
		frmAgendarExame.getContentPane().add(panel_5);

		btnSalvar = new JButton("Salvar");
		panel_5.add(btnSalvar);

		// Ações do Formulário
		setActions();
		populaTipoExame();
	}

	public JFrame getFrame() {
		return frmAgendarExame;
	}

	private void populaTipoExame() { 
		for (TipoExame tipo : new TipoExameDAO().findAll())
			cbTipoExame.addItem(tipo);
	}

	private void setActions() {
		cbTipoExame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tipoExameEscolhido = (TipoExame) cbTipoExame.getSelectedItem();
				atualizaCampoDataEHora();
			}
		});

		cbData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				cbHorario.removeAllItems();

				int posicaoData = cbData.getSelectedIndex() + 1; 
				dataSelecionada = DateUtils.addDays(DateUtils.getCurrentDate(), posicaoData);

				List<Exame> consultas = new ExameDAO().findAllByDateAndTipoExame(dataSelecionada, tipoExameEscolhido);
				List<Date> disponiveis = criaRangeHorarios(dataSelecionada);
				List<Date> horariosJaMarcados = new ArrayList<Date>();

				for (Exame co : consultas)
					for (Date d : disponiveis)
						if (co.getData().compareTo(d) == 0)
							horariosJaMarcados.add(d);

				disponiveis.removeAll(horariosJaMarcados);

				for (Date d : disponiveis)
					cbHorario.addItem(DateUtils.formatData(d, "HH:mm"));

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
		Exame consulta = new Exame(App.getCliente(), tipoExameEscolhido, result, cbTiposPagamento.getSelectedItem().toString());
		ExameDAO consultaDao = new ExameDAO();
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
