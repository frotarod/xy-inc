package br.com.zup.vorest;

import java.io.Serializable;

public class AtributoRest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8055968975932419391L;

	private int id;
	
	private String nome;
	
	private int codigoTipo;
	
	private String nomeModelo;
	
	
	public AtributoRest() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public AtributoRest(String nome, int codigoTipo, String nomeModelo) {
		super();
		this.nome = nome;
		this.codigoTipo = codigoTipo;
		this.nomeModelo = nomeModelo;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public int getCodigoTipo() {
		return codigoTipo;
	}


	public void setCodigoTipo(int codigoTipo) {
		this.codigoTipo = codigoTipo;
	}


	public String getNomeModelo() {
		return nomeModelo;
	}


	public void setNomeModelo(String nomeModelo) {
		this.nomeModelo = nomeModelo;
	}

	
	
}
