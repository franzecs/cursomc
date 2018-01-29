package com.ikytus.mc.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Estado extends AbstractEntity{
	private static final long serialVersionUID = 1L;
	
	private String nome;
	
	@OneToMany(mappedBy="estado")
	private List<Cidade> cidades;

	public Estado() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

}
