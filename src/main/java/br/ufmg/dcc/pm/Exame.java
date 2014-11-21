package br.ufmg.dcc.pm;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class Exame {

	private Cliente cliente;
	private TipoExame tipoExame;
	private Date data;
	private String tipo;

}
