package com.ikytus.mc.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class Categoria extends AbstractEntity{
	private static final long serialVersionUID = 1L;
	
	@Column(length = 100)
	private String nome;
	
	@ManyToMany(mappedBy="categorias")
	private List<Produto> produtos;
	
	public Categoria() {
	}

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

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
}
