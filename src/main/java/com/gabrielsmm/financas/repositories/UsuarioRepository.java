package com.gabrielsmm.financas.repositories;

import com.gabrielsmm.financas.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Usuario findByEmail(String email);

    Usuario findByNome(String nome);

}
