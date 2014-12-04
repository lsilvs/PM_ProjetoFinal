package br.ufmg.dcc.pm.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Proxy;

@Entity
@Proxy(lazy=false)
public class Cliente {
	private Integer id;
	private String nome;
	private String numIdentidade;
	private String numCPF;
	private String endereco;
	private String telefone;
	private String dataNascimento;

	public Cliente() {}

	public Cliente(String name, String cpf) {
		this.nome = name;
		this.numCPF = cpf;
	}

	public Cliente(String nome, String identidade, String cpf, String dataNascimento, String telefone) {
		this.nome = nome;
		this.numIdentidade = identidade;
		this.dataNascimento = dataNascimento;
		this.numCPF = cpf;
		this.telefone = telefone; 
	}
	
	public Cliente(String nome, String identidade, String cpf, String dataNascimento, String telefone,String endereco) {
		this.nome = nome;
		this.numIdentidade = identidade;
		this.dataNascimento = dataNascimento;
		this.numCPF = cpf;
		this.telefone = telefone; 
		this.endereco = endereco;
	}

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

	public String getNumIdentidade() {
		return numIdentidade;
	}

	public void setNumIdentidade(String numIdentidade) {
		this.numIdentidade = numIdentidade;
	}

	@Column(unique = true)
	public String getNumCPF() {
		return numCPF;
	}

	public void setNumCPF(String numCPF) {
		this.numCPF = numCPF;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

}