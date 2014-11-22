package br.ufmg.dcc.pm;

import java.util.List;

public class App {

    public static void main(String[] args) {

    	ClienteDAO clienteDao = new ClienteDAO();

        Cliente myCliente1 = new Cliente("Lucia Santos Miranda", "06212332144");
        Cliente myCliente2 = new Cliente("Carlos Antunes Goncalves", "22156798722");

        clienteDao.saveOrUpdate(myCliente1);
        clienteDao.saveOrUpdate(myCliente2);

        List<Cliente> clienteList = (List<Cliente>) clienteDao.findAll();

        for (Cliente cliente : clienteList) {
            System.out.println("ID: " + cliente.getId() + " | Nome:" + cliente.getNome() + " | CPF:" + cliente.getNumCPF());
        }
        
        MedicoDAO medicoDao = new MedicoDAO();

        Medico myMedico1 = new Medico("Dr. Atauffo Ribeiro");
        Medico myMedico2 = new Medico("Dra. Cristina Almeida");

        medicoDao.saveOrUpdate(myMedico1);
        medicoDao.saveOrUpdate(myMedico2);

        List<Medico> medicoList = (List<Medico>) medicoDao.findAll();

        for (Medico medico : medicoList) {
            System.out.println("ID: " + medico.getId() + " | Nome:" + medico.getNome());
        }

    }
}