package com.ikytus.mc.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ikytus.mc.domain.Produto;

@Repository
public interface ProdutoRepository extends PagingAndSortingRepository<Produto, Long> {

}
