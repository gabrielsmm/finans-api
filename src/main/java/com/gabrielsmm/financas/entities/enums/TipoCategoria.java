package com.gabrielsmm.financas.entities.enums;

import lombok.Getter;

@Getter
public enum TipoCategoria {

    RECEITA(1, "Receita"),
    DESPESA(2, "Despesa");

    private int codigo;
    private String descricao;

    private TipoCategoria(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public static TipoCategoria toEnum(Integer codigo) {
        if (codigo == null) return null;

        for (TipoCategoria x : TipoCategoria.values()) {
            if (codigo.equals(x.getCodigo())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + codigo);
    }
}
