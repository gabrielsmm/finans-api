package com.gabrielsmm.financas.repositories;

import com.gabrielsmm.financas.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    List<Categoria> findByTipo(Integer tipo);

}
