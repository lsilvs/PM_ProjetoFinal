package br.ufmg.dcc.pm.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TipoExame {

	private Integer id;
	private String nome;
	
	public TipoExame() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String toString(){
		return this.nome;
	}

}
