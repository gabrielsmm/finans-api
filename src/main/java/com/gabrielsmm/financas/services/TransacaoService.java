package com.gabrielsmm.financas.services;

import com.gabrielsmm.financas.dtos.TransacaoDTO;
import com.gabrielsmm.financas.entities.Transacao;
import com.gabrielsmm.financas.entities.Usuario;
import com.gabrielsmm.financas.repositories.TransacaoRepository;
import com.gabrielsmm.financas.security.UserSS;
import com.gabrielsmm.financas.services.exceptions.AuthorizationException;
import com.gabrielsmm.financas.services.exceptions.DataIntegrityException;
import com.gabrielsmm.financas.services.exceptions.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TransacaoService {

    private TransacaoRepository transacaoRepository;

    private UsuarioService usuarioService;

    private ModelMapper modelMapper;

    public Transacao find(Integer id) {
        Optional<Transacao> obj = transacaoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! " +
                "Id: " + id + ", Tipo: " + Transacao.class.getName()));
    }

    public Transacao insert(TransacaoDTO objDto) {
        Transacao transacao = modelMapper.map(objDto, Transacao.class);
        transacao.setId(-1);
        try {
            return transacaoRepository.save(transacao);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não foi possível inserir, erro de integridade de dados");
        }
    }

    public Transacao update(Integer id, TransacaoDTO objDto) {
        Transacao transacao = find(id);
        modelMapper.map(objDto, transacao);
        return transacaoRepository.save(transacao);
    }

    public void delete(Integer id) {
        find(id);
        try {
            transacaoRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não foi possível excluir, erro de integridade de dados");
        }
    }

    public Page<Transacao> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        UserSS user = UserService.authenticated();
        if (user == null) {
            throw new AuthorizationException("Acesso negado");
        }
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        Usuario usuario = usuarioService.find(user.getId());
        return transacaoRepository.findByUsuario(usuario, pageRequest);
    }

}
