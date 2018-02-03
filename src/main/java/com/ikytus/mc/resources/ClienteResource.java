package com.ikytus.mc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ikytus.mc.domain.Cliente;
import com.ikytus.mc.service.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService clienteService; 
	
	@GetMapping("/{id}")
	public ResponseEntity<?> find(@PathVariable Long id) {
		Cliente cliente = clienteService.buscar(id); 
		return ResponseEntity.ok().body(cliente);
	}
}
