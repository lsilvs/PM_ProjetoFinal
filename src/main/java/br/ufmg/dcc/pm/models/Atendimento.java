package br.ufmg.dcc.pm.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;

import br.ufmg.dcc.pm.modelsDao.ConsultaDAO;
import br.ufmg.dcc.pm.modelsDao.ExameDAO;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Atendimento {

	private Integer id;
	protected Cliente cliente;
	protected Date data;
	protected String tipo;
	protected Boolean aprovado;
	
	public Atendimento() {}

	public static String[] TIPOS_PAGAMENTO = { "cortesia", "cheque", "cartao", "convenio","dinheiro" }; 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@OneToOne(cascade=CascadeType.DETACH)
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Boolean getAprovado() {
		return aprovado;
	}

	public void setAprovado(Boolean aprovado) {
		this.aprovado = aprovado;
	}
	
	public void solicitarAprovacao(ConsultaDAO consulta) {
		
		if(this.tipo == "cortesia") {
			this.aprovado = ((consulta.getNumberOf("cortesia") % 5) != 0);
		} else if(this.tipo == "cheque") {
			this.aprovado = (Math.random() < 0.5);
		} else if(this.tipo == "cartao") {
			this.aprovado = true;
		} else if(this.tipo == "convenio") {
			if((consulta.getNumberOf("convenio") % 10) == 0) {
				this.aprovado = (Math.random() < 0.5);
			} else {
				this.aprovado = true;
			}
		} else if(this.tipo == "dinheiro") {
			this.aprovado = true;
		}	
	}	

	public void solicitarAprovacao(ExameDAO consulta) { 
		
		if(this.tipo == "cortesia") {
			this.aprovado = ((consulta.getNumberOf("cortesia") % 5) != 0);
		} else if(this.tipo == "cheque") {
			this.aprovado = (Math.random() < 0.5);
		} else if(this.tipo == "cartao") {
			this.aprovado = true;
		} else if(this.tipo == "convenio") {
			if((consulta.getNumberOf("convenio") % 10) == 0) {
				this.aprovado = (Math.random() < 0.5);
			} else {
				this.aprovado = true;
			}
		} else if(this.tipo == "dinheiro") {
			this.aprovado = true;
		}	
	}

}