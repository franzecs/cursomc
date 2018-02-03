package com.ikytus.mc.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Pedido extends AbstractEntity{
	private static final long serialVersionUID = 1L;
	
	@Column
	private Date instante;
	
	@OneToOne(cascade=CascadeType.ALL, mappedBy="pedido")
	private Pagamento pagamento;
	
	@ManyToOne
	@JoinColumn(name="cliente")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name="enderecoDeEntrega")
	private Endereco enderecoDeEntrega;
			
	public Pedido() {
	}

	public Pedido(Long id,Date instante, Cliente cliente, Endereco enderecoDeEntrega) {
		super();
		super.setId(id);
		this.instante = instante;
		this.cliente = cliente;
		this.enderecoDeEntrega = enderecoDeEntrega;
	}

	public Date getInstante() {
		return instante;
	}

	public void setInstante(Date instante) {
		this.instante = instante;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Endereco getEnderecoDeEntrega() {
		return enderecoDeEntrega;
	}

	public void setEnderecoDeEntrega(Endereco enderecoDeEntrega) {
		this.enderecoDeEntrega = enderecoDeEntrega;
	}
}
