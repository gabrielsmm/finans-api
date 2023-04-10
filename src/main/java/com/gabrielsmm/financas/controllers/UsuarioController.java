package com.gabrielsmm.financas.controllers;

import com.gabrielsmm.financas.dtos.UsuarioNewDTO;
import com.gabrielsmm.financas.dtos.UsuarioUpdateDTO;
import com.gabrielsmm.financas.entities.Usuario;
import com.gabrielsmm.financas.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Usuario> find(@PathVariable Integer id) {
        Usuario obj = usuarioService.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/usuario-logado")
    public ResponseEntity<Usuario> findLoggedUser() {
        Usuario obj = usuarioService.findLoggedUser();
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/nome")
    public ResponseEntity<Usuario> findByNome(@RequestParam(value = "value") String nome) {
        Usuario obj = usuarioService.findByNome(nome);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/email")
    public ResponseEntity<Usuario> findByEmail(@RequestParam(value = "value") String email) {
        Usuario obj = usuarioService.findByEmail(email);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody UsuarioNewDTO objDto) {
        Usuario obj = usuarioService.insert(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody UsuarioUpdateDTO objDto) {
        usuarioService.update(id, objDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
