package com.ikytus.mc.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ikytus.mc.domain.Pedido;

@Repository
public interface PedidoRepository extends PagingAndSortingRepository<Pedido, Long> {

}
