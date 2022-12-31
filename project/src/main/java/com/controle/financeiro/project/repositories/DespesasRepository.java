package com.controle.financeiro.project.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.controle.financeiro.project.model.Despesas;

@Repository
public interface DespesasRepository extends JpaRepository<Despesas, Long>{

List<Despesas> findByDescricao(String descricao);
	
	@Query(value = "select d FROM db_controle_financeiro.Despesas d where d.descricao like %:descricao%")
	List<Despesas> getDescricaoDespesas(String descricao);
	
	@Query(value = "SELECT receitas_id, d.id, d.categoria, d.descricao, d.valor, d.data FROM db_controle_financeiro.DESPESAS d WHERE MONTH(d.data) = :mes AND YEAR(d.data) = :ano", nativeQuery = true)
	Page<Despesas> getFindByAnoMesDespesas(@Param(value = "mes") Integer mes, @Param(value = "ano") Integer ano, Pageable pageable);
	
	@Query(value = "select obj FROM db_controle_financeiro.Despesas obj WHERE YEAR(obj.data) = :ano AND MONTH(obj.data) = :mes")
	List<Despesas> getResumoDespesas(@Param("ano") Integer ano, @Param("mes") Integer mes);
}
