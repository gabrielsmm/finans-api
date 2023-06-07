package com.gabrielsmm.financas.repositories;

import com.gabrielsmm.financas.entities.Transacao;
import com.gabrielsmm.financas.entities.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, Integer> {

    Page<Transacao> findByUsuario(Usuario usuario, Pageable pageable);

}
