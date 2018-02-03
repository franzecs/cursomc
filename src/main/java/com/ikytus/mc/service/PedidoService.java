package com.ikytus.mc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ikytus.mc.domain.Pedido;
import com.ikytus.mc.repository.PedidoRepository;
import com.ikytus.mc.service.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public Pedido buscar(Long id) {
		
		Pedido pedido = pedidoRepository.findOne(id); 
		
		if(pedido == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id
					+ ", Tipo: " + Pedido.class.getName());
		}
		return pedido; 
	}
}
