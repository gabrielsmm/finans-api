package com.gabrielsmm.financas.entities.enums;

import lombok.Getter;

@Getter
public enum TipoTransacao {

    RECEITA(1, "Receita"),
    DESPESA(2, "Despesa");

    private int codigo;
    private String descricao;

    private TipoTransacao(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public static TipoTransacao toEnum(Integer codigo) {
        if (codigo == null) return null;

        for (TipoTransacao x : TipoTransacao.values()) {
            if (codigo.equals(x.getCodigo())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + codigo);
    }
}
