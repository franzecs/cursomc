package com.ikytus.mc.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Cidade extends AbstractEntity{
	private static final long serialVersionUID = 1L;
	
	private String nome;
	
	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name="estado")
	private Estado estado;
	
	public Cidade() {
	}
	
	public Cidade(Long id, String nome, Estado estado) {
		super();
		super.setId(id);
		this.nome = nome;
		this.estado = estado;
	}



	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
}
