package br.ufmg.dcc.pm;

import java.text.ParseException;

import javax.swing.JFrame;

import br.ufmg.dcc.pm.utils.ConcludeLogin;
import br.ufmg.dcc.pm.utils.ConcludeScreen;
import br.ufmg.dcc.pm.views.CadastroCliente;
import br.ufmg.dcc.pm.views.Home;
import br.ufmg.dcc.pm.views.Login;

public class App {
 
	private static String Cpf;
	
	public static void main(String[] args) throws ParseException {
		
		Login loginFrame = new Login(new ConcludeLogin() {

			public void clienteCadastrado(JFrame frame, String cpf) {
				frame.dispose();
				Home home = new Home();
				home.getFrame().setVisible(true);
			}

			public void clienteNaoCadastrado(JFrame frame, String cpf) { 
				Cpf = cpf;
				CadastroCliente cadastro = new CadastroCliente(cpf, new ConcludeScreen() { 
					public void concludeScreen(JFrame frame) { 
						frame.dispose();
						Home home = new Home();
						home.getFrame().setVisible(true); 
					}  
				}); 
				cadastro.getFrame().setVisible(true); 
			} 
		});
		loginFrame.getFrame().setVisible(true);
	}
}