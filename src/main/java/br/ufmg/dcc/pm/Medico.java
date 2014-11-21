package br.ufmg.dcc.pm;

import javax.persistence.Entity;

@Entity
public class Medico extends Funcionario {
	
	Especialidade especialidade;
	Agenda agenda;
	
	public Medico() {

	}

	public Medico(String nome) {
		super.nome = nome;
	}

}
