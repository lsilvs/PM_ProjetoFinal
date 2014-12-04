package br.ufmg.dcc.pm;

import java.text.ParseException;

import br.ufmg.dcc.pm.models.Cliente;
import br.ufmg.dcc.pm.modelsDao.ClienteDAO;
import br.ufmg.dcc.pm.views.Home;
import br.ufmg.dcc.pm.views.Login;

public class App {

	private static String Cpf;
	private static Cliente cliente;

	public static void main(String[] args) throws ParseException { 
		 Login loginFrame = new Login();
		 loginFrame.getFrame().setVisible(true);
	}

	public static String getCpf() {
		return Cpf;
	}

	public static void setCpf(String cpf) {
		Cpf = cpf;
		ClienteDAO clienteDAO = new ClienteDAO();
		cliente = clienteDAO.findByCpf(cpf);
	}
	
	public static Cliente getCliente(){
		return cliente;
	}

	public static void abreHome(String cpf) {
		App.setCpf(cpf);
		
		Home home = new Home();
		home.getFrame().setVisible(true);
	}

}