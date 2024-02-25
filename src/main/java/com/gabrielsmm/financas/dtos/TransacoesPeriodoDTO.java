package com.gabrielsmm.financas.dtos;

import com.gabrielsmm.financas.util.Util;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TransacoesPeriodoDTO {
	
	private String periodo;
	private Double valorReceitas;
	private Double valorDespesas;
	
	public TransacoesPeriodoDTO(Integer numeroMes, Double valorReceitas, Double valorDespesas) {
		this.periodo = Util.obterNomeMes(numeroMes);
		this.valorReceitas = valorReceitas;
		this.valorDespesas = valorDespesas;
	}
	
}
