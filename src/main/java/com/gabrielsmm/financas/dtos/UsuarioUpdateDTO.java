package com.gabrielsmm.financas.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioUpdateDTO {

    @Size(min = 3, max = 50, message = "Nome precisa estar entre {min} e {max} caracteres")
    private String nome;

    @Size(min = 5, max = 50, message = "Senha precisa estar entre {min} e {max} caracteres")
    private String senha;

}
