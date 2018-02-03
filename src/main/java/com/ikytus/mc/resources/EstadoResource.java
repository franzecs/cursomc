package com.ikytus.mc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ikytus.mc.domain.Categoria;
import com.ikytus.mc.service.CategoriaService;

@RestController
@RequestMapping(value="/estados")
public class EstadoResource {
	
	@Autowired
	private CategoriaService categoriaService; 
	
	@GetMapping("/{id}")
	public ResponseEntity<?> find(@PathVariable Long id) {
		Categoria categoria = categoriaService.buscar(id); 
		return ResponseEntity.ok().body(categoria);
	}
}