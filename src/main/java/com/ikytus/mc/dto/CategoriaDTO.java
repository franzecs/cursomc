package com.ikytus.mc.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.ikytus.mc.domain.AbstractEntity;
import com.ikytus.mc.domain.Categoria;

public class CategoriaDTO extends AbstractEntity{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min=5, max=80, message="O tamanho deve ser entre 5 e 80 caracteres")
	private String nome;
		
		
	public CategoriaDTO() {
	}
	
	public CategoriaDTO(Categoria categoria) {
		id = categoria.getId();
		nome = categoria.getNome();
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}
}
