package com.ikytus.mc.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ikytus.mc.domain.Cidade;

@Repository
public interface CidadeRepository extends PagingAndSortingRepository<Cidade, Long> {

}
