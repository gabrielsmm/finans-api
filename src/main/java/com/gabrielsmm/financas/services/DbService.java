package com.gabrielsmm.financas.services;

import com.gabrielsmm.financas.entities.Usuario;
import com.gabrielsmm.financas.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DbService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void instanciaBaseDeDados() {
        Usuario usuario = new Usuario(null, "gabrielsmm", "gabriel@teste.com", bCryptPasswordEncoder.encode("12345"));
        usuarioRepository.save(usuario);
    }

}
