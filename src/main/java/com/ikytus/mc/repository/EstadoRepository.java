package com.ikytus.mc.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ikytus.mc.domain.Estado;

@Repository
public interface EstadoRepository extends PagingAndSortingRepository<Estado, Long> {

}
