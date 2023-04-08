package com.gabrielsmm.financas.services;

import com.gabrielsmm.financas.entities.Usuario;
import com.gabrielsmm.financas.repositories.UsuarioRepository;
import com.gabrielsmm.financas.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String nome) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByNome(nome);
        if (usuario == null) {
            throw new UsernameNotFoundException(nome);
        }
        return new UserSS(usuario.getId(), usuario.getNome(), usuario.getSenha(), usuario.getPerfis());
    }

}
