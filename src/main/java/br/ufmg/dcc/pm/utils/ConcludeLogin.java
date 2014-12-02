package br.ufmg.dcc.pm.utils;

import javax.swing.JFrame;

public interface ConcludeLogin {
	void clienteCadastrado(JFrame frame,String cpf);
	void clienteNaoCadastrado(JFrame frame,String cpf);
}
