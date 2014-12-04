package br.ufmg.dcc.pm.exceptions;

public class InvalidCpfException extends Exception {
	
	public InvalidCpfException(){
		super("Seu cpf é inválido!");
	}

}
