package com.gabrielsmm.financas.entities;

import com.gabrielsmm.financas.entities.enums.TipoTransacao;
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
    private Integer tipo;
    private Double valor;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    private String descricao;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Transacao(Integer id, LocalDate data, TipoTransacao tipo, Double valor, Categoria categoria, String descricao, Usuario usuario) {
        this.id = id;
        this.data = data;
        this.tipo = (tipo == null) ? null : tipo.getCodigo();
        this.valor = valor;
        this.categoria = categoria;
        this.descricao = descricao;
        this.usuario = usuario;
    }
}
