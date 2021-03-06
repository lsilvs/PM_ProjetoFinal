package br.ufmg.dcc.pm.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import br.ufmg.dcc.pm.modelsDao.ExameDAO;

@Entity
public class Exame extends Atendimento {

	private TipoExame tipoExame;
	
	public Exame() {}
	
	public Exame(Cliente cliente, TipoExame tipoExame, Date data, String tipo) {
		super.cliente = cliente;
		super.data = data;
		super.tipo = tipo;
		super.aprovado = false;
		this.tipoExame = tipoExame;
		
		solicitarAprovacao(new ExameDAO());
	}
	
	@OneToOne(cascade=CascadeType.DETACH)
	public TipoExame getTipoExame() {
		return tipoExame;
	}

	public void setTipoExame(TipoExame tipoExame) {
		this.tipoExame = tipoExame;
	}

}
