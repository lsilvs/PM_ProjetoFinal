package br.ufmg.dcc.pm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import br.ufmg.dcc.pm.models.Cliente;
import br.ufmg.dcc.pm.models.Consulta;
import br.ufmg.dcc.pm.models.Especialidade;
import br.ufmg.dcc.pm.models.Medico;
import br.ufmg.dcc.pm.modelsDao.ClienteDAO;
import br.ufmg.dcc.pm.modelsDao.ConsultaDAO;
import br.ufmg.dcc.pm.modelsDao.EspecialidadeDAO;
import br.ufmg.dcc.pm.modelsDao.MedicoDAO;

public class App {

    public static void main(String[] args) throws ParseException {

    	ClienteDAO clienteDao = new ClienteDAO();
        Cliente myCliente1 = new Cliente("Lucia Santos Miranda", "06212332144");
        Cliente myCliente2 = new Cliente("Carlos Antunes Goncalves", "22156798722");
        try{
        	clienteDao.create(myCliente1);
            clienteDao.create(myCliente2);
        }catch(Exception e){
        	System.out.println(e);
        }

//        List<Cliente> clienteList = (List<Cliente>) clienteDao.findAll();
//
//        for (Cliente cliente : clienteList) {
//            System.out.println("ID: " + cliente.getId() + " | Nome:" + cliente.getNome() + " | CPF:" + cliente.getNumCPF());
//        }
        
        EspecialidadeDAO especialidadeDao = new EspecialidadeDAO();
        Especialidade especialidade1 = new Especialidade("ALERGOLOGIA");
        Especialidade especialidade2 = new Especialidade("CARDIOLOGIA");
        especialidadeDao.create(especialidade1);
        especialidadeDao.create(especialidade2);


        MedicoDAO medicoDao = new MedicoDAO();
        Medico myMedico1 = new Medico("Dr. Atauffo Ribeiro", especialidade1);
        Medico myMedico2 = new Medico("Dra. Cristina Almeida", especialidade2);
        Medico myMedico3 = new Medico("Dr. Carlos Amaral", especialidade1);

        try{
        	medicoDao.saveOrUpdate(myMedico1);
        	medicoDao.saveOrUpdate(myMedico2);
        	medicoDao.saveOrUpdate(myMedico3);
        }catch(Exception e){
        	
        }


        List<Medico> medicoList = (List<Medico>) medicoDao.findAllByEspecialidade(especialidade1);
        for (Medico medico : medicoList) {
            System.out.println("ID: " + medico.getId() + " | Nome:" + medico.getNome());
        }
        
        Date data1 = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse("2014-12-09 11:30");
        
        ConsultaDAO consultaDao = new ConsultaDAO();
        Consulta cosulta1 = new Consulta(myCliente1, myMedico1, data1, "particular");
        Consulta consulta2 = new Consulta(myCliente2, myMedico2, data1, "convenio");
        consultaDao.create(cosulta1);
        consultaDao.create(consulta2);

    }
}