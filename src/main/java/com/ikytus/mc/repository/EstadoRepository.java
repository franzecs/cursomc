package com.ikytus.mc.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ikytus.mc.domain.Estado;

@Repository
public interface EstadoRepository extends PagingAndSortingRepository<Estado, Long> {
	
	@Transactional(readOnly=true)
	public List<Estado>findAllByOrderByNome();
}
