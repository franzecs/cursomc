package com.ikytus.mc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ikytus.mc.domain.Cidade;
import com.ikytus.mc.repository.CidadeRepository;

@Service
public class CidadeService {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	public List<Cidade> findByEstado(Long estadoId){
		return cidadeRepository.findCidades(estadoId);
	}

}
