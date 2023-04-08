package com.gabrielsmm.financas.repositories;

import com.gabrielsmm.financas.entities.Orcamento;
import com.gabrielsmm.financas.entities.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrcamentoRepository extends JpaRepository<Orcamento, Integer> {

    Page<Orcamento> findByUsuario(Usuario usuario, Pageable pageable);

}
