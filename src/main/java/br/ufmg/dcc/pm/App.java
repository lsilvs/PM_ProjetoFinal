package br.ufmg.dcc.pm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import br.ufmg.dcc.pm.models.Cliente;
import br.ufmg.dcc.pm.models.Consulta;
import br.ufmg.dcc.pm.models.Medico;
import br.ufmg.dcc.pm.modelsDao.ClienteDAO;
import br.ufmg.dcc.pm.modelsDao.ConsultaDAO;
import br.ufmg.dcc.pm.modelsDao.MedicoDAO;

public class App {

	public static void main(String[] args) throws ParseException {

        MedicoDAO medicoDao = new MedicoDAO();
        Medico medico = medicoDao.find(2);
        
        List<Medico> medicoList = (List<Medico>) medicoDao.findAll();

        System.out.println("\nMEDICOS\n");

        for (Medico medico1 : medicoList) {
            System.out.println("ID: " + medico1.getId() + " | Nome:" + medico1.getNome() + " | Especialidade:" + medico1.getEspecialidade().getNome());
        }
        
        ClienteDAO clienteDao = new ClienteDAO();
        Cliente cliente = clienteDao.find(1);

        Date data = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse("2014-12-1 08:30");

        ConsultaDAO consultaDao = new ConsultaDAO();
        Consulta consulta = new Consulta(cliente, medico, data, "cartao");
        consultaDao.create(consulta);
        consulta.solicitarAprovacao();
        
        System.out.println("\nCONSULTA\n");
        String dateString = new SimpleDateFormat("dd/MM/yyy HH:mm").format(consulta.getData());

        System.out.println("ID: " + consulta.getId() + 
        		" | Cliente: " + consulta.getCliente().getNome() + 
        		" | Medico: " + consulta.getMedico().getNome() + 
        		" | Especialidade: " + consulta.getMedico().getEspecialidade().getNome() +
        		" | Data: " + dateString +
        		" | Aprovado?: " + consulta.getAprovado()
        		);

    }
}