package br.com.dados.servico;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.dados.entidade.ItemVenda;
import br.com.dados.entidade.VendaResposta;


@Service
public class VendaServico {
	 public VendaResposta analisar(List<ItemVenda> vendas) {
	        if (vendas == null || vendas.isEmpty())
	            throw new IllegalArgumentException("Lista de vendas está vazia");

	        Map<String, Integer> quantidadePorProduto = new HashMap<>();
	        Map<String, Double> faturamentoPorProduto = new HashMap<>();
	        Map<String, List<Double>> precosPorProduto = new HashMap<>();
	        Map<LocalDate, Double> faturamentoPorDia = new HashMap<>();

	        double faturamentoTotal = 0;
	       

	        for (ItemVenda item : vendas) {
	            String produto = item.produto();
	            double preco = item.precoUnitario();
	            int qtd = item.quantidade();
	            double total = preco * qtd;

	            faturamentoTotal += total;
	            

	            quantidadePorProduto.merge(produto, qtd, Integer::sum);
	            faturamentoPorProduto.merge(produto, total, Double::sum);
	            precosPorProduto.computeIfAbsent(produto, k -> new ArrayList<>()).add(preco);
	            faturamentoPorDia.merge(item.dataVenda(), total, Double::sum);
	        }

	        String produtoMaisVendido = quantidadePorProduto.entrySet().stream()
	                .max(Map.Entry.comparingByValue())
	                .map(Map.Entry::getKey).orElse(null);

	        String produtoMaiorFaturamento = faturamentoPorProduto.entrySet().stream()
	                .max(Map.Entry.comparingByValue())
	                .map(Map.Entry::getKey).orElse(null);

	        Map<String, Integer> ranking = quantidadePorProduto.entrySet().stream()
	                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
	                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> a, LinkedHashMap::new));

	        Map<String, Double> mediaPrecoPorProduto = precosPorProduto.entrySet().stream()
	                .collect(Collectors.toMap(Map.Entry::getKey,
	                        e -> e.getValue().stream().mapToDouble(Double::doubleValue).average().orElse(0.0)));

	        double ticketMedio = faturamentoTotal / vendas.size();

	        return new VendaResposta(faturamentoTotal, ticketMedio, produtoMaisVendido, produtoMaiorFaturamento,
	                ranking, mediaPrecoPorProduto, faturamentoPorDia);
	    }
}
