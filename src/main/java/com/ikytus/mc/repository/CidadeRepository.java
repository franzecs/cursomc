package com.ikytus.mc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ikytus.mc.domain.Cidade;

@Repository
public interface CidadeRepository extends PagingAndSortingRepository<Cidade, Long> {

	@Transactional(readOnly=true)
	@Query("SELECT obj FROM Cidade obj WHERE obj.estado.id = :estado_id ORDER BY obj.nome")
	public List<Cidade> findCidades(@Param("estado_id")Long estado_id);
}
