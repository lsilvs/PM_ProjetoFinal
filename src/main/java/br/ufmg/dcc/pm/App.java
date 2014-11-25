package br.ufmg.dcc.pm;

import java.util.List;

import br.ufmg.dcc.pm.models.Cliente;
import br.ufmg.dcc.pm.models.Especialidade;
import br.ufmg.dcc.pm.models.Medico;
import br.ufmg.dcc.pm.modelsDao.ClienteDAO;
import br.ufmg.dcc.pm.modelsDao.EspecialidadeDAO;
import br.ufmg.dcc.pm.modelsDao.MedicoDAO;

public class App {

    public static void main(String[] args) {

    	ClienteDAO clienteDao = new ClienteDAO();

        Cliente myCliente1 = new Cliente("Lucia Santos Miranda", "06212332144");
        Cliente myCliente2 = new Cliente("Carlos Antunes Goncalves", "22156798722");

        try{
        	clienteDao.saveOrUpdate(myCliente1);
            clienteDao.saveOrUpdate(myCliente2);
        }catch(Exception e){
        	
        }
        
        
//        System.out.println(String.valueOf(clienteDao.jaCadastrado("06212332144")));
//        System.out.println(clienteDao.jaCadastrado("06212332140"));

        List<Cliente> clienteList = (List<Cliente>) clienteDao.findAll();

        for (Cliente cliente : clienteList) {
            System.out.println("ID: " + cliente.getId() + " | Nome:" + cliente.getNome() + " | CPF:" + cliente.getNumCPF());
        }
        
        MedicoDAO medicoDao = new MedicoDAO();
        
        EspecialidadeDAO especialidadeDao = new EspecialidadeDAO();
        
        Especialidade especialidade1 = new Especialidade("ALERGOLOGIA");
        Especialidade especialidade2 = new Especialidade("CARDIOLOGIA");
        
        especialidadeDao.create(especialidade1);
        especialidadeDao.create(especialidade2);

        Medico myMedico1 = new Medico("Dr. Atauffo Ribeiro", especialidade1);
        Medico myMedico2 = new Medico("Dra. Cristina Almeida", especialidade2);
        Medico myMedico3 = new Medico("Dr. Carlos Amaral", especialidade1);

        try{
        	medicoDao.saveOrUpdate(myMedico1);
        	medicoDao.saveOrUpdate(myMedico2);
        	medicoDao.saveOrUpdate(myMedico3);
        }catch(Exception e){
        	
        }

        // List<Medico> medicoList = (List<Medico>) medicoDao.findAll();
        List<Medico> medicoList = (List<Medico>) medicoDao.findAllByEspecialidade(especialidade1);

        for (Medico medico : medicoList) {
            System.out.println("ID: " + medico.getId() + " | Nome:" + medico.getNome());
        }

    }
}