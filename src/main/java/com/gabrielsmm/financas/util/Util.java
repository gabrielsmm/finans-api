package com.gabrielsmm.financas.util;

public class Util {
	
	public static String obterNomeMes(Integer numeroMes) {
		if (numeroMes < 1 || numeroMes > 12) {
			return "";
        }

        String[] nomesMeses = {
            "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho",
            "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"
        };

        return nomesMeses[numeroMes - 1];
	}
	
}
