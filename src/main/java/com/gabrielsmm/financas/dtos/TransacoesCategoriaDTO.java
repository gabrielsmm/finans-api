package com.gabrielsmm.financas.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransacoesCategoriaDTO {

	private String nomeCategoria;
	private Double valorTransacoes;
	
}
