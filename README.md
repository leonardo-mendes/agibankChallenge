# Agibank Challenge [![Build Status](https://travis-ci.org/leonardo-mendes/agibankChallenge.svg?branch=master)](https://travis-ci.org/leonardo-mendes/agibankChallenge)  [![codecov](https://codecov.io/gh/leonardo-mendes/agibankChallenge/branch/master/graph/badge.svg)](https://codecov.io/gh/leonardo-mendes/agibankChallenge)

Uma aplicação para processamento de arquivos, validando e gerando um novo arquivo com o resultado esperado.

## Processamento

1 - A aplicação faz a captura de todos os arquivos no diretório:
``
  src/main/resources/data/in/
``

2 - Aplica as validações referentes a arquivo e conteudo.

3 - Arquivos processados com sucesso são enviados para:
``
  src/main/resources/data/backup/
``

4 - Arquivos processados com erro são enviados para:
``
  src/main/resources/data/failure/
``

5 - Arquivos com os resultados das origens processadas são enviados para:
``
  src/main/resources/data/out/
``

## Formatação de Arquivo

Só será processado arquivo com a extensão **.dat**

## Formatação de linha

Um arquivo contem linhas que podem ser formatadas em 3 tipos de informações:

**Vendedor**
 > 001çCPFçNameçSalary

**Cliente**
 > 002çCNPJçNameçBusiness Area

**Venda**
> 003çSale IDç[Item ID-Item Quantity-Item Price]çSalesman name


Exemplo de Arquivo:

> 001ç1234567891234çPedroç50000 <br>
> 001ç3245678865434çPauloç40000.99 <br>
> 002ç2345675434544345çJose da SilvaçRural <br>
> 002ç2345675433444345çEduardo PereiraçRural <br>
> 003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro <br>
> 003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo <br>

## Execução

``
./gradlew bootRun
``
