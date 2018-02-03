package com.ikytus.mc.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ikytus.mc.domain.Endereco;

@Repository
public interface EnderecoRepository extends PagingAndSortingRepository<Endereco, Long> {

}
