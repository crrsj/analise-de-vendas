package br.com.dados.controle;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.dados.entidade.ItemVenda;
import br.com.dados.entidade.Venda;
import br.com.dados.entidade.VendaResposta;
import br.com.dados.servico.VendaServico;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/vendas")
public class VendaControle {

	@Autowired
	private VendaServico vendaServico;
	
	
	@Operation(summary = "Endpoint responsável por analisar os dados.") 
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })   
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping("/analise")	
	public VendaResposta analisarVendas(@RequestBody Venda venda) {
		return vendaServico.analisar(venda.vendas());
	}
	
	    @Operation(summary = "Endpoint responsável por incluir os dados no arquivo .csv.") 
        @ApiResponse(responseCode = "200",description = " sucesso",content = {
     	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
        })   
	    @ResponseStatus(code = HttpStatus.OK)
	    @PostMapping("/exportar/csv")	   
	    public void exportarCsv(@RequestBody Venda resposta, HttpServletResponse response) throws IOException {
	        response.setContentType("text/csv");
	        response.setHeader("Content-Disposition", "attachment; filename=analise.csv");
	        PrintWriter writer = response.getWriter();
	        writer.println("Produto,Quantidade,Preço Total,Data da Venda");

	        for (ItemVenda item : resposta.vendas()) {
	            writer.printf("%s,%d,%.2f,%s\n", item.produto(), item.quantidade(),
	                    item.precoUnitario() * item.quantidade(), item.dataVenda());
	        }
	        writer.flush();
	    }
	}
