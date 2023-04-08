package com.gabrielsmm.financas.entities.enums;

import lombok.Getter;

@Getter
public enum Perfil {

    ADMIN(1, "ROLE_ADMIN"),
    USUARIO(2, "ROLE_USUARIO");

    private int codigo;
    private String descricao;

    private Perfil(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public static Perfil toEnum(Integer codigo) {
        if (codigo == null) {
            return null;
        }

        for (Perfil x : Perfil.values()) {
            if (codigo.equals(x.getCodigo())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + codigo);
    }

}
