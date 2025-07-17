# API de Transações - Desafio Itaú

Esta é a API REST desenvolvida como solução para o Desafio de Programação do Itaú Unibanco. A aplicação gerencia transações financeiras em memória e calcula estatísticas em tempo real sobre elas.

## 📜 Sumário

* [Tecnologias Utilizadas](#-tecnologias-utilizadas)
* [Funcionalidades Implementadas](#-funcionalidades-implementadas)
* [Como Executar o Projeto](#-como-executar-o-projeto)
    * [Pré-requisitos](#-pré-requisitos)
    * [Via Docker (Método Recomendado)](#-via-docker-método-recomendado)
    * [Localmente via Maven](#-localmente-via-maven)
* [Endpoints da API](#-endpoints-da-api)
* [Autor](#-autor)

## ✨ Tecnologias Utilizadas

Este projeto foi construído utilizando as seguintes tecnologias:

* **Java 17**: Versão da linguagem Java.
* **Spring Boot 3**: Framework principal para a construção da API.
* **Maven**: Gerenciador de dependências e build do projeto.
* **Docker**: Para containerização da aplicação.
* **Spring Boot Actuator**: Para o endpoint de Health Check.
* **Springdoc OpenAPI (Swagger)**: Para documentação interativa da API.
* **JUnit 5 & Mockito**: Para os testes unitários e de integração.
* **Lombok**: Para redução de código boilerplate.

## 🚀 Funcionalidades Implementadas

* **POST /transacao**: Recebe, valida e armazena uma nova transação.
* **DELETE /transacao**: Apaga todos os registros de transações.
* **GET /estatistica**: Retorna as estatísticas das transações ocorridas na janela de tempo configurada (padrão: 60 segundos).
* **Tratamento de Erro Aprimorado**: Respostas com status `422` e corpo detalhado para erros de validação.
* **Logs**: A aplicação registra suas operações em pontos-chave.
* **Health Check**: Endpoint `GET /actuator/health` para monitoramento.
* **Configurabilidade**: A janela de tempo das estatísticas pode ser alterada no `application.properties`.
* **Testes Automatizados**: Cobertura de testes para as principais regras de negócio.
* **Documentação Interativa**: Interface Swagger UI para explorar e testar a API.
* **Containerização**: `Dockerfile` otimizado com multi-stage build.

## ⚙️ Como Executar o Projeto

### Pré-requisitos

Para o método via Docker, você precisa ter o **Docker Desktop** instalado e em execução.
Para o método local, você precisa do **Java 17** e do **Maven 3.8+** instalados e configurados no seu sistema.

### Via Docker (Método Recomendado)

Este é o método mais simples, pois não requer instalação de Java ou Maven na sua máquina.

1.  **Clone o repositório:**
    ```bash
    git clone <URL_DO_SEU_REPOSITORIO>
    cd <NOME_DA_PASTA_DO_PROJETO>
    ```

2.  **Construa a imagem Docker:**
    ```bash
    docker build -t desafio-itau/api-transacoes .
    ```

3.  **Execute o container:**
    ```bash
    docker run --rm -p 8080:8080 -d desafio-itau/api-transacoes
    ```
A aplicação estará disponível em `http://localhost:8080`.

### Localmente via Maven

1.  **Clone o repositório** (se ainda não o fez).

2.  **Navegue até a raiz do projeto** e execute o seguinte comando Maven:
    ```bash
    mvn spring-boot:run
    ```
A aplicação iniciará e estará disponível em `http://localhost:8080`.

## Endpoints da API

Após iniciar a aplicação, a documentação completa e interativa da API pode ser acessada em:

➡️ **[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)**

Os principais endpoints são:

| Verbo  | Rota                  | Descrição                                 |
| :----- | :-------------------- | :---------------------------------------- |
| `POST` | `/transacao`          | Registra uma nova transação.              |
| `DELETE`| `/transacao`          | Apaga todas as transações.               |
| `GET`  | `/estatistica`        | Retorna as estatísticas.                  |
| `GET`  | `/actuator/health`    | Verifica a saúde da aplicação.           |

## 👤 Autor

**JOÃO LUCAS BERNARDES**

* **LinkedIn**:https://www.linkedin.com/in/joaolucasbernardes/
