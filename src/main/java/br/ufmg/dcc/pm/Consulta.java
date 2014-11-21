package br.ufmg.dcc.pm;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class Consulta {

	private Cliente cliente;
	private Medico medico;
	private Date data;
	private String tipo;

}
