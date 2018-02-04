package com.ikytus.mc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ikytus.mc.domain.Cliente;
import com.ikytus.mc.repository.ClienteRepository;
import com.ikytus.mc.service.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente find(Long id) {
		
		Cliente cliente = clienteRepository.findOne(id); 
		
		if(cliente == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id
					+ ", Tipo: " + Cliente.class.getName());
		}
		return cliente; 
	}
}
