package br.com.zup.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import br.com.zup.enuns.EnumTipos;

//TODO @JsonIdentityInfo ajusta o erro de --Infinite recursion -- 

@Entity
@Table(name = "Atributo")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id")
public class Atributo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8936162692535059534L;

	@Id
    @GeneratedValue
	private int id;
	
    @NotNull
    @Column
	private String name;
    
    @ManyToOne
    private Modelo modelo;
    
    @Enumerated(javax.persistence.EnumType.ORDINAL)
    private EnumTipos enumTipos;

	
	public Atributo(String name, Modelo modelo, EnumTipos enumTipos) {
		super();
		this.name = name;
		this.modelo = modelo;
		this.enumTipos = enumTipos;
	}

	public Atributo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public EnumTipos getEnumTipos() {
		return enumTipos;
	}

	public void setEnumTipos(EnumTipos enumTipos) {
		this.enumTipos = enumTipos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((enumTipos == null) ? 0 : enumTipos.hashCode());
		result = prime * result + id;
		result = prime * result + ((modelo == null) ? 0 : modelo.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atributo other = (Atributo) obj;
		if (enumTipos != other.enumTipos)
			return false;
		if (id != other.id)
			return false;
		if (modelo == null) {
			if (other.modelo != null)
				return false;
		} else if (!modelo.equals(other.modelo))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
