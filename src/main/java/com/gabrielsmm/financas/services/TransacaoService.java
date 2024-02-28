package com.gabrielsmm.financas.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gabrielsmm.financas.dtos.TransacaoDTO;
import com.gabrielsmm.financas.dtos.TransacoesCategoriaDTO;
import com.gabrielsmm.financas.dtos.TransacoesPeriodoDTO;
import com.gabrielsmm.financas.entities.Transacao;
import com.gabrielsmm.financas.entities.Usuario;
import com.gabrielsmm.financas.repositories.TransacaoRepository;
import com.gabrielsmm.financas.security.UserSS;
import com.gabrielsmm.financas.services.exceptions.AuthorizationException;
import com.gabrielsmm.financas.services.exceptions.DataIntegrityException;
import com.gabrielsmm.financas.services.exceptions.ObjectNotFoundException;

import lombok.AllArgsConstructor;

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

    public Page<Transacao> findPage(Integer page, Integer linesPerPage, String orderBy, String direction, Integer tipo, Integer mes, Integer ano) {
    	Usuario usuarioLogado = getUsuarioLogado();
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return transacaoRepository.findByFilter(usuarioLogado, tipo, mes, ano, pageRequest);
    }
    
    public List<TransacoesPeriodoDTO> getSomaValoresPorPeriodo(Integer tipoPeriodo) {
    	Usuario usuarioLogado = getUsuarioLogado();
        
    	List<TransacoesPeriodoDTO> resultado = new ArrayList<>();
    	if (tipoPeriodo.equals(1)) {
    		resultado = transacaoRepository.getSomaValoresPorPeriodo(usuarioLogado);
    	}
    	
    	return resultado;
    }
    
    public List<TransacoesCategoriaDTO> getSomaValoresPorCategoria(Integer tipoCategoria) {
    	Usuario usuarioLogado = getUsuarioLogado();
        Integer mes = LocalDate.now().getMonthValue();
        Integer ano = LocalDate.now().getYear();
        return transacaoRepository.getSomaValoresPorCategoria(usuarioLogado, tipoCategoria, mes, ano);
    }

	private Usuario getUsuarioLogado() {
		UserSS user = UserService.authenticated();
        if (user == null) {
            throw new AuthorizationException("Acesso negado");
        }
        return usuarioService.find(user.getId());
	}

}
