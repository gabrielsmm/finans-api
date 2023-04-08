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

    @NotBlank(message = "Preencha o campo corretamente")
    @Size(min = 3, max = 50, message = "Nome precisa estar entre {min} e {max} caracteres")
    private String nome;

    @NotBlank(message = "Preencha o campo corretamente")
    @Size(min = 5, max = 50, message = "Senha precisa estar entre {min} e {max} caracteres")
    private String senha;

}
