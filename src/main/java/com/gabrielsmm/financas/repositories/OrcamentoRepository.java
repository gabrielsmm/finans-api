package com.gabrielsmm.financas.repositories;

import com.gabrielsmm.financas.entities.Orcamento;
import com.gabrielsmm.financas.entities.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface OrcamentoRepository extends JpaRepository<Orcamento, Integer> {

    Page<Orcamento> findByUsuario(Usuario usuario, Pageable pageable);

    @Query("SELECT CASE WHEN COUNT(o) > 0 THEN true ELSE false END " +
            "FROM Orcamento o " +
            "WHERE o.usuario = :usuario " +
            "AND o.id <> :id " +
            "AND (" +
            " (o.dataInicio <= :dataFim AND o.dataFim >= :dataInicio) " +
            " OR (o.dataInicio >= :dataInicio AND o.dataInicio <= :dataFim) " +
            " OR (o.dataFim >= :dataInicio AND o.dataFim <= :dataFim)" +
            ")")
    boolean existeDataConflitante(Usuario usuario, Integer id, LocalDate dataInicio, LocalDate dataFim);

    @Query("SELECT o FROM Orcamento o " +
           "WHERE o.usuario = :usuario " +
           "AND :dataAtual BETWEEN o.dataInicio AND o.dataFim")
    Orcamento getOrcamentoVigente(Usuario usuario, LocalDate dataAtual);

}
