package com.ikytus.mc.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Categoria extends AbstractEntity{
	private static final long serialVersionUID = 1L;
	
	@Column(length = 100)
	private String nome;

	public Categoria(Long id, String nome) {
		super();
	    super.setId(id);
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
