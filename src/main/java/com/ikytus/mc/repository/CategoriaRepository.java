package com.ikytus.mc.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ikytus.mc.domain.Categoria;

@Repository
public interface CategoriaRepository extends PagingAndSortingRepository<Categoria, Long> {

}
