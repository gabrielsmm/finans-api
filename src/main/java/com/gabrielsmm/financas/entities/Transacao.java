package com.gabrielsmm.financas.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "transacoes")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate data;
    private Double valor;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    private String descricao;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "orcamento_id")
    private Orcamento orcamento;

    @Transient
    private Integer orcamentoId;

    public Transacao(Integer id, LocalDate data, Double valor, Categoria categoria, String descricao, Usuario usuario, Orcamento orcamento) {
        this.id = id;
        this.data = data;
        this.valor = valor;
        this.categoria = categoria;
        this.descricao = descricao;
        this.usuario = usuario;
        this.orcamento = orcamento;
    }

    @PostLoad
    public void obterOrcamentoId() {
        this.orcamentoId = this.orcamento.getId();
    }
}
