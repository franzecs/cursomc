package com.ikytus.mc.domain;

public class Categoria extends AbstractEntity{
	private static final long serialVersionUID = 1L;
	
	private String nome;

	public Categoria(Long codigo, String nome) {
		super();
	    super.setCodigo(codigo);
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
