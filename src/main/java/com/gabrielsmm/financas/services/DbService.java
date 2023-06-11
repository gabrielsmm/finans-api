package com.gabrielsmm.financas.services;

import com.gabrielsmm.financas.entities.Categoria;
import com.gabrielsmm.financas.entities.Orcamento;
import com.gabrielsmm.financas.entities.Transacao;
import com.gabrielsmm.financas.entities.Usuario;
import com.gabrielsmm.financas.entities.enums.Perfil;
import com.gabrielsmm.financas.entities.enums.TipoCategoria;
import com.gabrielsmm.financas.repositories.CategoriaRepository;
import com.gabrielsmm.financas.repositories.OrcamentoRepository;
import com.gabrielsmm.financas.repositories.TransacaoRepository;
import com.gabrielsmm.financas.repositories.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class DbService {

    private UsuarioRepository usuarioRepository;

    private OrcamentoRepository orcamentoRepository;

    private CategoriaRepository categoriaRepository;

    private TransacaoRepository transacaoRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void instanciaBaseDeDados() {
        Usuario usuario = new Usuario(null, "Gabriel Mendes", "gabriel@teste.com", bCryptPasswordEncoder.encode("12345"));
        usuario.addPerfil(Perfil.ADMIN);

        usuarioRepository.save(usuario);

        Orcamento orcamento = new Orcamento(null, "Orçamento Junho", LocalDate.of(2023, 6, 1), LocalDate.of(2023, 6, 30), 2000.0, usuario);

        orcamentoRepository.save(orcamento);

        Categoria categoria1 = new Categoria(null, "Salário", TipoCategoria.RECEITA);
        Categoria categoria2 = new Categoria(null, "Bonificações", TipoCategoria.RECEITA);
        Categoria categoria3 = new Categoria(null, "Aluguel", TipoCategoria.RECEITA);
        Categoria categoria4 = new Categoria(null, "Investimentos", TipoCategoria.RECEITA);
        Categoria categoria5 = new Categoria(null, "Vendas", TipoCategoria.RECEITA);
        Categoria categoria6 = new Categoria(null, "Reembolsos", TipoCategoria.RECEITA);
        Categoria categoria7 = new Categoria(null, "Juros", TipoCategoria.RECEITA);
        Categoria categoria8 = new Categoria(null, "Outras Receitas", TipoCategoria.RECEITA);
        Categoria categoria9 = new Categoria(null, "Moradia", TipoCategoria.DESPESA);
        Categoria categoria10 = new Categoria(null, "Alimentação", TipoCategoria.DESPESA);
        Categoria categoria11 = new Categoria(null, "Transporte", TipoCategoria.DESPESA);
        Categoria categoria12 = new Categoria(null, "Saúde", TipoCategoria.DESPESA);
        Categoria categoria13 = new Categoria(null, "Educação", TipoCategoria.DESPESA);
        Categoria categoria14 = new Categoria(null, "Lazer", TipoCategoria.DESPESA);
        Categoria categoria15 = new Categoria(null, "Vestuário", TipoCategoria.DESPESA);
        Categoria categoria16 = new Categoria(null, "Serviços Públicos", TipoCategoria.DESPESA);
        Categoria categoria17 = new Categoria(null, "Seguros", TipoCategoria.DESPESA);
        Categoria categoria18 = new Categoria(null, "Impostos", TipoCategoria.DESPESA);
        Categoria categoria19 = new Categoria(null, "Dívidas/Empréstimos", TipoCategoria.DESPESA);
        Categoria categoria20 = new Categoria(null, "Doações", TipoCategoria.DESPESA);
        Categoria categoria21 = new Categoria(null, "Manutenção de Veículos", TipoCategoria.DESPESA);
        Categoria categoria22 = new Categoria(null, "Viagens", TipoCategoria.DESPESA);
        Categoria categoria23 = new Categoria(null, "Outras Despesas", TipoCategoria.DESPESA);

        List<Categoria> categorias = Arrays.asList(
                categoria1, categoria2, categoria3, categoria4, categoria5,
                categoria6, categoria7, categoria8, categoria9, categoria10,
                categoria11, categoria12, categoria13, categoria14, categoria15,
                categoria16, categoria17, categoria18, categoria19, categoria20,
                categoria21, categoria22, categoria23
        );

        categoriaRepository.saveAll(categorias);

        Transacao transacao1 = new Transacao(null, LocalDate.now(), 90.0, categoria17, "Teste despesa", usuario, orcamento);
        Transacao transacao2 = new Transacao(null, LocalDate.now(), 50.0, categoria2, "Teste receita", usuario, orcamento);

        transacaoRepository.saveAll(Arrays.asList(transacao1, transacao2));
    }

}
