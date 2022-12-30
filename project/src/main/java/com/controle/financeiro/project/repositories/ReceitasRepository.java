package com.controle.financeiro.project.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.controle.financeiro.project.model.Receitas;

@Repository
public interface ReceitasRepository extends JpaRepository<Receitas, Long>{

List<Receitas> findByDescricao(String descricao);
	
	@Query(value = "select obj FROM Receitas obj LEFT JOIN FETCH obj.despesas")
	List<Receitas> getReceitas(Pageable pageable);
	
	@Query(value = "select obj FROM Receitas obj LEFT JOIN FETCH obj.despesas WHERE obj.descricao like %:descricao%")
	List<Receitas> getDescricaoReceitas(@Param("descricao") String descricao, Pageable pageable);
	
	@Query(value = "select obj FROM Receitas obj WHERE YEAR(obj.data) = :ano AND MONTH(obj.data) = :mes")
	List<Receitas> getResumoReceitas(@Param("ano") Integer ano, @Param("mes") Integer mes);
	
	@Query(value = "select obj FROM Receitas obj LEFT JOIN FETCH obj.despesas WHERE obj.id = :id")
	List<Receitas> getDetalhamentoReceitas(@Param("id") Long id, Pageable pageable);
}
