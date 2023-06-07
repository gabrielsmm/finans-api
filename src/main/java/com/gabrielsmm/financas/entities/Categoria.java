package com.gabrielsmm.financas.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gabrielsmm.financas.entities.enums.TipoCategoria;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private Integer tipo;

    @JsonIgnore
    @OneToMany(mappedBy = "categoria")
    private List<Transacao> transacoes = new ArrayList<>();

    public Categoria(Integer id, String nome, TipoCategoria tipo) {
        this.id = id;
        this.nome = nome;
        this.tipo = (tipo == null) ? null : tipo.getCodigo();
    }
}
