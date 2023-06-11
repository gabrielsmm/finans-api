package com.gabrielsmm.financas.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransacaoDTO {

    @NotNull(message = "Informe o id do usuário")
    @Positive(message = "O Id do usuário precisa ser positivo")
    private Integer usuarioId;

    @NotNull(message = "Informe o id do orçamento")
    @Positive(message = "O Id do orçamento precisa ser positivo")
    private Integer orcamentoId;

    @NotNull(message = "Preencha o campo data corretamente")
    private LocalDate data;

    @NotNull(message = "Preencha o campo valor corretamente")
    @Positive(message = "O valor deve ser maior que zero")
    private Double valor;

    @NotNull(message = "Informe o id da categoria")
    @Positive(message = "O Id da categoria precisa ser positivo")
    private Integer categoriaId;

    @NotBlank(message = "Preencha o campo descrição corretamente")
    private String descricao;

}
