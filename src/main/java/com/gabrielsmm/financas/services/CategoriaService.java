package com.gabrielsmm.financas.services;

import com.gabrielsmm.financas.entities.Categoria;
import com.gabrielsmm.financas.repositories.CategoriaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoriaService {

    private CategoriaRepository categoriaRepository;

    public List<Categoria> findByTipo(Integer tipo) {
        return this.categoriaRepository.findByTipo(tipo);
    }

}
