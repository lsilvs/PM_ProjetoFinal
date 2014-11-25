package br.ufmg.dcc.pm.models;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Medico extends Funcionario {

	private Especialidade especialidade;
	
	public Medico() {

	}

	public Medico(String nome, Especialidade especialidade) {
		super.nome = nome;
		this.especialidade = especialidade;
	}
	
	@OneToOne
	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

}
