package br.ufmg.dcc.pm;

import java.util.List;

public class App {

    @SuppressWarnings("unchecked")
	public static void main(String[] args) {

    		ClienteDAO clienteDao = new ClienteDAO();

            Cliente myCliente1 = new Cliente("Lucia Santos Miranda", "lucimi@email.com");
            Cliente myCliente2 = new Cliente("Carlos Antunes Goncalves", "caango@email.com");

            clienteDao.saveOrUpdate(myCliente1);
            clienteDao.saveOrUpdate(myCliente2);

            List<Cliente> clienteList = (List<Cliente>) clienteDao.findAll();

            for (Cliente Cliente : clienteList) {
                System.out.println("Id: " + Cliente.getId() + " | Name:" + Cliente.getName() + " | Email:" + Cliente.getEmail());
            }

    }
}