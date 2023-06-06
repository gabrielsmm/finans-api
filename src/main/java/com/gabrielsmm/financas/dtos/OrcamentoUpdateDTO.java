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
public class OrcamentoUpdateDTO {

    @NotBlank(message = "Preencha o campo nome corretamente")
    private String nome;

    @NotNull(message = "Preencha o campo data de início corretamente")
    private LocalDate dataInicio;

    @NotNull(message = "Preencha o campo data de fim corretamente")
    private LocalDate dataFim;

    @NotNull(message = "Preencha o campo valor corretamente")
    @Positive(message = "O valor deve ser maior que zero")
    private Double valor;

}
