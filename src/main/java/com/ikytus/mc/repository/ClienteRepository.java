package com.ikytus.mc.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ikytus.mc.domain.Cliente;

@Repository
public interface ClienteRepository extends PagingAndSortingRepository<Cliente, Long> {
	
	@Transactional(readOnly = true)
	Cliente findByEmail(String email);
}
