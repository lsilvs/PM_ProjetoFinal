package br.ufmg.dcc.pm.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Proxy;

import br.ufmg.dcc.pm.modelsDao.ConsultaDAO;

@Entity
@Proxy(lazy=false)
public class Consulta extends Atendimento {

	private Medico medico;

	public Consulta() {}
	
	public Consulta(Cliente cliente, Medico medico, Date data, String tipo) {
		super.cliente = cliente;
		super.data = data;
		super.tipo = tipo; 
		this.medico = medico;
		super.aprovado = false; 
		
		solicitarAprovacao(new ConsultaDAO());
	} 
	
	@OneToOne(cascade=CascadeType.DETACH)
	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

}
