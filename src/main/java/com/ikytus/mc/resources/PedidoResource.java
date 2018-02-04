package com.ikytus.mc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ikytus.mc.domain.Pedido;
import com.ikytus.mc.service.PedidoService;

@RestController
@RequestMapping(value="/pedidos")
public class PedidoResource {
	
	@Autowired
	private PedidoService pedidoService; 
	
	@GetMapping("/{id}")
	public ResponseEntity<Pedido> find(@PathVariable Long id) {
		Pedido pedido = pedidoService.find(id); 
		return ResponseEntity.ok().body(pedido);
	}
}
