package com.ikytus.mc.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ikytus.mc.domain.Pagamento;

@Repository
public interface PagamentoRepository extends PagingAndSortingRepository<Pagamento, Long> {

}
