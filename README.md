# Lucius KeyStore

Projeto de software desenvolvido para a Disciplina de Desenvolvimento de Software III da Universidade do Vale do Rio dos Sinos - Unisinos.
* O Sistema Lucius KeyStore é um e-commerce para venda de chaves de ativação para jogos.<br />
* O sistema possui integração com as API’s da Steam e Origin, exibine automaticamente os jogos na vitrine já realizando o cálculo de valores, permite cadastro de usuários, gerenciamento de carrinho e finalização de compra. 
* Os pagamentos são realizados através da utilização da API do Paypal.

Professor:
* Raphael Leite Campos
Alunos: 
* Bruno Klein;
* Fábio Castilhos;
* Leonardo Carmona;
* Paulo Lara


# Linguagem/Frameworks Utilizada no Projeto

Foi Utilizada estrutura de micro-serviços para desenvolver o back-end

## Back-end
* Java 8 (Spring Boot 2.0.1.RELEASE);

## Front-End
* Bootstrap 4 + JQuery 1.11.1 + Javascript;
	
## Banco de Dados
* PostgreSQL 10;

# Requisitos para iniciar o projeto

* Possuir o Java 1.8 ou superior instalado;
* Possuir o banco de dados Postgree SQL 10 instalado;

## Clonar o projeto

No seu terminal, digite: 

```
git clone https://github.com/LeoCarmona/unisinos-desenvolvimento-de-software-iii.git
```

E pressione Enter.

## Para iniciar o projeto
	
## Banco de dados

Antes de iniciar pela primeira vez os serviços, execute os seguintes arquivos na pasta /database/:
* schema-postgresql.sql
* load-postgresql.sql

## Back-end

Dentro da pasta /microservice/, execute o seguinte arquivo:
* run.sh (Linux)
* run.bat (Windows)

## Front-End

Após o back-end estar apto a receber chamadas, abra o arquivo index.html dentro da pasta /ecommerce/.

# Contatos

* brunoklein@outlook.com
* fabiocastilhoss@gmail.com
* pauloalara@gmail.com
* lcdesenv@gmail.com
