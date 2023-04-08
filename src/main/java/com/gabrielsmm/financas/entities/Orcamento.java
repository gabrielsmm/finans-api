package com.gabrielsmm.financas.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orcamentos")
public class Orcamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private Date dataInicio;
    private Date dataFim;
    private Double valor;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

}
