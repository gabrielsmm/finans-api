package com.gabrielsmm.financas.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PostLoad;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "orcamentos")
public class Orcamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Double valor;

    @Transient
    private Double valorReceitas;

    @Transient
    private Double valorDespesas;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @JsonIgnore
    @OneToMany(mappedBy = "orcamento")
    private List<Transacao> transacoes = new ArrayList<>();

    public Orcamento(Integer id, String nome, LocalDate dataInicio, LocalDate dataFim, Double valor, Usuario usuario) {
        this.id = id;
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.valor = valor;
        this.usuario = usuario;
    }

    @PostLoad
    public void calcularValoresTransacoes() {
        double receitas = 0d;
        double despesas = 0d;

        for (Transacao transacao : transacoes) {
            if (transacao.getCategoria().getTipo() == 1) {
                receitas += transacao.getValor();
            } else {
                despesas += transacao.getValor();
            }
        }

        this.valorReceitas = receitas;
        this.valorDespesas = despesas;
    }
}
