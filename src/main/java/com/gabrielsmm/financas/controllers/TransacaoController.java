package com.gabrielsmm.financas.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gabrielsmm.financas.dtos.TransacaoDTO;
import com.gabrielsmm.financas.dtos.TransacoesCategoriaDTO;
import com.gabrielsmm.financas.dtos.TransacoesPeriodoDTO;
import com.gabrielsmm.financas.entities.Transacao;
import com.gabrielsmm.financas.services.TransacaoService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/transacoes")
@AllArgsConstructor
public class TransacaoController {

    private TransacaoService transacaoService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Transacao> find(@PathVariable Integer id) {
        Transacao transacao = transacaoService.find(id);
        return ResponseEntity.ok().body(transacao);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody TransacaoDTO objDto) {
        Transacao obj = transacaoService.insert(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody TransacaoDTO objDto) {
        transacaoService.update(id, objDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        transacaoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/page")
    public ResponseEntity<Page<Transacao>> findPage(
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue="id") String orderBy,
            @RequestParam(value="direction", defaultValue="DESC") String direction,
            @RequestParam(value="tipo", defaultValue = "-1") Integer tipo,
            @RequestParam(value="mes") Integer mes,
            @RequestParam(value="ano") Integer ano
    ) {
        Page<Transacao> list = transacaoService.findPage(page, linesPerPage, orderBy, direction, tipo, mes, ano);
        return ResponseEntity.ok().body(list);
    }
    
    @GetMapping(value = "/soma-valores-por-periodo")
    public ResponseEntity<List<TransacoesPeriodoDTO>> getSomaValoresPorPeriodo(
    		@RequestParam(value = "tipoPeriodo", defaultValue = "1") Integer tipoPeriodo) {
    	List<TransacoesPeriodoDTO> resultado = transacaoService.getSomaValoresPorPeriodo(tipoPeriodo);
    	return ResponseEntity.ok().body(resultado);
    }
    
    @GetMapping(value = "/soma-valores-por-categoria")
    public ResponseEntity<List<TransacoesCategoriaDTO>> getSomaValoresPorCategoria(
    		@RequestParam(value = "tipoCategoria", defaultValue = "-1") Integer tipoCategoria) {
    	List<TransacoesCategoriaDTO> resultado = transacaoService.getSomaValoresPorCategoria(tipoCategoria);
    	return ResponseEntity.ok().body(resultado);
    }

}
