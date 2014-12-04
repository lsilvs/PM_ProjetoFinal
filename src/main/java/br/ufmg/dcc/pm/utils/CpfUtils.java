package br.ufmg.dcc.pm.utils;

public class CpfUtils {
	public static String geraDigitos(String cpf){
		int somaX,somaY,X,Y,restoX,restoY,i ;

		i = 0;
		somaX = 0;
		somaY = 0;
		int value;

		while(i < 9){
		  value = ((int)cpf.charAt(i))-48 ;
		  somaX += (value * (10-i));
		  somaY += (value * (11-i));
		  i++;
		}

		restoX = somaX % 11;

		if(restoX == 0 || restoX == 1) X = 0;
		else X = 11-restoX;

		somaY += (X * 2);
		restoY = somaY % 11;

		if(restoY == 0 || restoY == 1) Y = 0;
		else Y = 11-restoY;
		
		return String.valueOf(X)+String.valueOf(Y); 
	}
	
	public static boolean validaDigitos(String cpf){ 
		
//		Limpa CPF
		cpf = cpf.replace(".", "");
		cpf = cpf.replace("-", "");
		
		String numerico = "";
		String digito = "";

		numerico = cpf.substring(0,9);
		digito = cpf.substring(9,11); 

		String cpf_parcial = geraDigitos(numerico.toString());
		if( digito.toString().equals(cpf_parcial) ) return true;
		return false;
	} 
}
