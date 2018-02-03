package com.ikytus.mc.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ikytus.mc.domain.ItemPedido;

@Repository
public interface ItemPedidoRepository extends PagingAndSortingRepository<ItemPedido, Long> {

}
