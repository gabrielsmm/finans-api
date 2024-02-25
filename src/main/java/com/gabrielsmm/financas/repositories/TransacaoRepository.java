package com.gabrielsmm.financas.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gabrielsmm.financas.dtos.TransacoesPeriodoDTO;
import com.gabrielsmm.financas.entities.Transacao;
import com.gabrielsmm.financas.entities.Usuario;

public interface TransacaoRepository extends JpaRepository<Transacao, Integer> {

    @Query(value = "SELECT t FROM Transacao t JOIN t.categoria c WHERE t.usuario = :usuario "
            + " AND (:tipo < 0 OR c.tipo = :tipo) "
            + " AND MONTH(t.data) = :mes "
            + " AND YEAR(t.data) = :ano ",
            countQuery = "SELECT COUNT(t) FROM Transacao t JOIN t.categoria c WHERE t.usuario = :usuario "
                    + " AND (:tipo < 0 OR c.tipo = :tipo) "
                    + " AND MONTH(t.data) = :mes "
                    + " AND YEAR(t.data) = :ano ")
    Page<Transacao> findByFilter(Usuario usuario, Integer tipo, Integer mes, Integer ano, Pageable pageable);
    
    @Query(value = " SELECT new com.gabrielsmm.financas.dtos.TransacoesPeriodoDTO("
    		+ "            MONTH(t.data), "
    		+ "            SUM(CASE WHEN c.tipo = 1 THEN t.valor ELSE 0 END), "
    		+ "            SUM(CASE WHEN c.tipo = 2 THEN t.valor ELSE 0 END)) "
    		+ " FROM Transacao t "
    		+ " JOIN t.categoria c "
    		+ " WHERE t.usuario = :usuario "
    		+ " GROUP BY MONTH(t.data) ")
    List<TransacoesPeriodoDTO> getSomaValoresMensais(Usuario usuario);

}
