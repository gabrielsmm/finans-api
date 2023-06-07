package com.gabrielsmm.financas.controllers;

import com.gabrielsmm.financas.entities.Categoria;
import com.gabrielsmm.financas.services.CategoriaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categorias")
@AllArgsConstructor
public class CategoriaController {

    private CategoriaService categoriaService;

    @GetMapping(value = "/{tipo}")
    public ResponseEntity<List<Categoria>> getAllByTipo(@PathVariable Integer tipo) {
        List<Categoria> categorias = categoriaService.findByTipo(tipo);
        return ResponseEntity.ok().body(categorias);
    }

}
