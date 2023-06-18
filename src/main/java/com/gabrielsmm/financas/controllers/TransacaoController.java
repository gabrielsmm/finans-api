package com.gabrielsmm.financas.controllers;

import com.gabrielsmm.financas.dtos.TransacaoDTO;
import com.gabrielsmm.financas.entities.Transacao;
import com.gabrielsmm.financas.services.TransacaoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

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

}
