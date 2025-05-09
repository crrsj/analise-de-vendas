package br.com.dados.entidade;

import java.util.Map;

public record VendaResposta(
		  double faturamentoTotal,
		    double ticketMedio,
		    String produtoMaisVendido,
		    String produtoComMaiorFaturamento,
		    Map<String, Integer> rankingProdutosMaisVendidos,
		    Map<String, Double> mediaPrecoPorProduto,
		    Map<java.time.LocalDate, Double> faturamentoPorDia	
		
		
		                 ) {

}
