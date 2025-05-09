API de Análise de Vendas
Esta API foi desenvolvida para realizar a análise de dados de vendas e gerar relatórios baseados em informações fornecidas pelo usuário. 
Ela calcula coisas importantes como o faturamento total, o ticket médio, o produto mais vendido, dentre outras coisas.

Funcionalidades

A API oferece os seguintes recursos:

Análise de Vendas: Recebe uma lista de vendas e gera um relatório com dados como:

Faturamento total

Total de itens vendidos

Ticket médio

Produto mais vendido

Exportação de Relatórios: Geração de relatórios no formato CSV com as análises solicitadas.

Endpoints
1. POST /api/vendas/exportar/csv
Este endpoint gera um relatório CSV com os dados das vendas e as análises solicitadas.

Request
URL: /api/vendas/exportar/csv

Método: POST

Body: Um JSON contendo as vendas

A resposta será um arquivo CSV gerado, contendo os dados das vendas, o faturamento total, o ticket médio, o produto mais vendido, entre outros dados de análise.

Exemplo de resposta:

Status Code: 200 OK

Content-Type: text/csv

Content-Disposition: attachment; filename=relatorio.csv

Tecnologias
Java: Para o desenvolvimento da API.

Spring Boot: Para estruturar a API.

Postman: Para testar a API de forma interativa.
