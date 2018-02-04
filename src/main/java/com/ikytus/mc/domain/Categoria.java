package com.ikytus.mc.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Categoria extends AbstractEntity{
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min=5, max=80, message="O tamanho deve ser entre 5 e 80 caracteres")
	private String nome;
		
	@ManyToMany(mappedBy="categorias")
	private List<Produto> produtos = new ArrayList<>();
	
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
