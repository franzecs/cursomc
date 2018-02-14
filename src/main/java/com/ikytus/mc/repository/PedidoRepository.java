package com.ikytus.mc.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ikytus.mc.domain.Cliente;
import com.ikytus.mc.domain.Pedido;

@Repository
public interface PedidoRepository extends PagingAndSortingRepository<Pedido, Long> {
	
	@Transactional(readOnly=true)
	Page<Pedido> findByCliente (Cliente cliente, Pageable pageRequest);
}
