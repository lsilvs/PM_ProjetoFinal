package br.ufmg.dcc.pm.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Proxy;

@Entity
@Proxy(lazy=false)
public class Medico extends Funcionario {

	private Especialidade especialidade;
	
	public Medico() {}

	public Medico(String nome, Especialidade especialidade) {
		super.nome = nome;
		this.especialidade = especialidade;
	}
	
	@OneToOne(cascade=CascadeType.ALL)
	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}
	
	public String toString(){
		return this.nome;
	}

}
