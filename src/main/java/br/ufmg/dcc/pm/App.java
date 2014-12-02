package br.ufmg.dcc.pm;

import java.text.ParseException;

import br.ufmg.dcc.pm.views.Home;
import br.ufmg.dcc.pm.views.Login;

public class App {
 
	private static String Cpf;
	
	public static void main(String[] args) throws ParseException {
		
		Login loginFrame = new Login();
		loginFrame.getFrame().setVisible(true);
	}

	public static String getCpf() {
		return Cpf;
	}

	public static void setCpf(String cpf) {
		Cpf = cpf;
	}
	
	public static void abreHome(String cpf){
		App.setCpf(cpf);
		Home home = new Home();
		home.getFrame().setVisible(true);
	}
}