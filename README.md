# Agibank Challenge [![Build Status](https://travis-ci.org/leonardo-mendes/agibankChallenge.svg?branch=master)](https://travis-ci.org/leonardo-mendes/agibankChallenge)  [![codecov](https://codecov.io/gh/leonardo-mendes/agibankChallenge/branch/master/graph/badge.svg)](https://codecov.io/gh/leonardo-mendes/agibankChallenge)

Uma aplicação para processamento de arquivos, validando e gerando um novo arquivo com o resultado esperado.

## Processamento

1 - A aplicação faz a captura de todos os arquivos no diretório:
```
  src/main/resources/data/in/
```

2 - Aplica as validações referentes a arquivo e conteudo.

3 - Arquivos processados com sucesso serão movidos para:
```
  src/main/resources/data/backup/
```

4 - Arquivos processados com falha serão movidos para:
```
  src/main/resources/data/failure/
```

5 - Arquivos com os resultados das origens processadas serão gerados em:
```
  src/main/resources/data/out/
```

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

## Resultados

Os resultados dos arquivos processados podem ser encontrados no diretório: 
```
  src/main/resources/data/out/{nome_do_arquivo}.done.dat
```

Indicadores: 

- Quantidade de vendedor no arquivo de entrada <br>
- Quantidade de clientes no arquivo de entrada <br>
- ID da venda mais cara <br>
- O pior vendedor <br>

**Formato do arquivo:**
> Salesmen: 2 - Clients: 2 - Best Sale: 10 - Lower Sale: Paulo

## Tecnologias
* [Gradle](https://gradle.org/): build e gerenciamento de dependências do projeto;
* [Spring Boot](https://projects.spring.io/spring-boot/): criação de aplicações Spring stand-alone com mínima configuração;
* [Java](https://java.com/pt-BR/): linguagem de programação utilizada para desenvolmento do projeto;
* [Junit](http://junit.org/) e  [Mockito](http://site.mockito.org/): testes unitários;

## Executando o Projeto

``
./gradlew bootRun
``

