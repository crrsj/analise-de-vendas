package br.com.dados.entidade;

import java.time.LocalDate;

public record ItemVenda(String produto,double precoUnitario,int quantidade, LocalDate dataVenda) {

}
