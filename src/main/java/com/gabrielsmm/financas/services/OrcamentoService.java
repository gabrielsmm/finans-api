package com.gabrielsmm.financas.services;

import com.gabrielsmm.financas.dtos.OrcamentoNewDTO;
import com.gabrielsmm.financas.dtos.OrcamentoUpdateDTO;
import com.gabrielsmm.financas.entities.Orcamento;
import com.gabrielsmm.financas.entities.Usuario;
import com.gabrielsmm.financas.repositories.OrcamentoRepository;
import com.gabrielsmm.financas.security.UserSS;
import com.gabrielsmm.financas.services.exceptions.AuthorizationException;
import com.gabrielsmm.financas.services.exceptions.DataIntegrityException;
import com.gabrielsmm.financas.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrcamentoService {

    @Autowired
    private OrcamentoRepository orcamentoRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ModelMapper modelMapper;

    public Orcamento find(Integer id) {
        Optional<Orcamento> obj = orcamentoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! " +
                "Id: " + id + ", Tipo: " + Orcamento.class.getName()));
    }

    public Orcamento insert(OrcamentoNewDTO objDto) {
        Orcamento orcamento = modelMapper.map(objDto, Orcamento.class);
        orcamento.setId(null);
        try {
            return orcamentoRepository.save(orcamento);
        } catch (Exception e) {
            throw new DataIntegrityException("Não foi possível inserir, erro de integridade de dados");
        }
    }

    public Orcamento update(Integer id, OrcamentoUpdateDTO objDto) {
        Orcamento orcamento = find(id);
        modelMapper.map(objDto, orcamento);
        return orcamentoRepository.save(orcamento);
    }

    public void delete(Integer id) {
        find(id);
        try {
            orcamentoRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não foi possível excluir, erro de integridade de dados");
        }
    }

    public Page<Orcamento> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        UserSS user = UserService.authenticated();
        if (user == null) {
            throw new AuthorizationException("Acesso negado");
        }
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        Usuario usuario = usuarioService.find(user.getId());
        return orcamentoRepository.findByUsuario(usuario, pageRequest);
    }

}