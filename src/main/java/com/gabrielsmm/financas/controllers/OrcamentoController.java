package com.gabrielsmm.financas.controllers;

import com.gabrielsmm.financas.dtos.OrcamentoNewDTO;
import com.gabrielsmm.financas.dtos.OrcamentoUpdateDTO;
import com.gabrielsmm.financas.entities.Orcamento;
import com.gabrielsmm.financas.services.OrcamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/orcamentos")
public class OrcamentoController {

    @Autowired
    private OrcamentoService orcamentoService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Orcamento> find(@PathVariable Integer id) {
        Orcamento orcamento = orcamentoService.find(id);
        return ResponseEntity.ok().body(orcamento);
    }

    @GetMapping(value = "/orcamento-vigente")
    public ResponseEntity<Orcamento> getOrcamentoVigente() {
        Orcamento orcamento = orcamentoService.getOrcamentoVigente();
        return ResponseEntity.ok().body(orcamento);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody OrcamentoNewDTO objDto) {
        Orcamento obj = orcamentoService.insert(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody OrcamentoUpdateDTO objDto) {
        orcamentoService.update(id, objDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        orcamentoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/page")
    public ResponseEntity<Page<Orcamento>> findPage(
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue="id") String orderBy,
            @RequestParam(value="direction", defaultValue="DESC") String direction
    ) {
        Page<Orcamento> list = orcamentoService.findPage(page, linesPerPage, orderBy, direction);
        return ResponseEntity.ok().body(list);
    }

}
