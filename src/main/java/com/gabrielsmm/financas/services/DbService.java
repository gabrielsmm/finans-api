package com.gabrielsmm.financas.services;

import com.gabrielsmm.financas.entities.Orcamento;
import com.gabrielsmm.financas.entities.Usuario;
import com.gabrielsmm.financas.entities.enums.Perfil;
import com.gabrielsmm.financas.repositories.OrcamentoRepository;
import com.gabrielsmm.financas.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DbService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private OrcamentoRepository orcamentoRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void instanciaBaseDeDados() {
        Usuario usuario = new Usuario(null, "Gabriel Mendes", "gabriel@teste.com", bCryptPasswordEncoder.encode("12345"));
        usuario.addPerfil(Perfil.ADMIN);

        Orcamento orcamento = new Orcamento(null, "Orçamento Abril", LocalDate.of(2023, 4, 1), LocalDate.of(2023, 4, 30), 2000.0, 2000.0, 0.0, usuario);

        usuario.getOrcamentos().add(orcamento);

        usuarioRepository.save(usuario);
        orcamentoRepository.save(orcamento);
    }

}
