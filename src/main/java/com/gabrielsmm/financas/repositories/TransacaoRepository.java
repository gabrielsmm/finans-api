package com.gabrielsmm.financas.repositories;

import com.gabrielsmm.financas.entities.Transacao;
import com.gabrielsmm.financas.entities.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransacaoRepository extends JpaRepository<Transacao, Integer> {

    @Query(value = "SELECT t FROM Transacao t JOIN t.categoria c WHERE t.usuario = :usuario "
            + "AND (:tipo < 0 OR c.tipo = :tipo)",
            countQuery = "SELECT COUNT(t) FROM Transacao t JOIN t.categoria c WHERE t.usuario = :usuario "
                    + "AND (:tipo < 0 OR c.tipo = :tipo)")
    Page<Transacao> findByFilter(Usuario usuario, Integer tipo, Pageable pageable);

}
