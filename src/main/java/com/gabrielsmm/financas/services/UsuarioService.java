package com.gabrielsmm.financas.services;

import com.gabrielsmm.financas.dtos.UsuarioNewDTO;
import com.gabrielsmm.financas.dtos.UsuarioUpdateDTO;
import com.gabrielsmm.financas.entities.Usuario;
import com.gabrielsmm.financas.entities.enums.Perfil;
import com.gabrielsmm.financas.repositories.UsuarioRepository;
import com.gabrielsmm.financas.security.UserSS;
import com.gabrielsmm.financas.services.exceptions.AuthorizationException;
import com.gabrielsmm.financas.services.exceptions.DataIntegrityException;
import com.gabrielsmm.financas.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Usuario find(Integer id) {
        UserSS user = UserService.authenticated();
        if (user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
            throw new AuthorizationException("Acesso negado");
        }
        Optional<Usuario> obj = usuarioRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! " +
                "Id: " + id + ", Tipo: " + Usuario.class.getName()));
    }

    public Usuario findLoggedUser() {
        UserSS user = UserService.authenticated();
        if (user == null) {
            throw new AuthorizationException("Acesso negado");
        }
        Optional<Usuario> obj = usuarioRepository.findById(user.getId());
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! " +
                "Id: " + user.getId() + ", Tipo: " + Usuario.class.getName()));
    }

    public Usuario findByEmail(String email) {
        Usuario obj = usuarioRepository.findByEmail(email);
        if (obj == null) {
            throw new ObjectNotFoundException("Objeto não encontrado! " +
                    "Email: " + email + ", Tipo: " + Usuario.class.getName());
        }
        return obj;
    }

    public Usuario findByNome(String nome) {
        Usuario obj = usuarioRepository.findByNome(nome);
        if (obj == null) {
            throw new ObjectNotFoundException("Objeto não encontrado! " +
                    "Nome: " + nome + ", Tipo: " + Usuario.class.getName());
        }
        return obj;
    }

    public Usuario insert(UsuarioNewDTO objDto) {
        Usuario usuario = modelMapper.map(objDto, Usuario.class);
        usuario.setId(null);
        usuario.setSenha(bCryptPasswordEncoder.encode(objDto.getSenha()));
        try {
            return usuarioRepository.save(usuario);
        } catch (Exception e) {
            throw new DataIntegrityException("Não foi possível inserir, erro de integridade de dados");
        }
    }

    public Usuario update(Integer id, UsuarioUpdateDTO objDto) {
        Usuario usuario = find(id);
        modelMapper.map(objDto, usuario);
        usuario.setSenha(bCryptPasswordEncoder.encode(objDto.getSenha()));
        return usuarioRepository.save(usuario);
    }

    public void delete(Integer id) {
        find(id);
        try {
            usuarioRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não foi possível excluir, erro de integridade de dados");
        }
    }

}
