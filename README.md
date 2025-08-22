# Sistema de Venda de Ingressos

Esse é um projeto prático desenvolvido na disciplina **CSI607 - Sistemas Web II, ministrada pelo Prof. Fernando Bernardes de Oliveira (UFOP - DECSI)**. É um sistema de venda de ingressos online, construído com uma arquitetura de microsserviços, usando o Spring Boot e Docker.

## Arquitetura

O sistema é dividido nos seguintes microsserviços:

* **Gateway:** Ponto de entrada único para todas as requisições da API, responsável pelo roteamento para os outros serviços.
* **Name Server (Eureka):** Servidor de nomes para registro e descoberta de serviços.
* **User Service:** Gerencia informações de usuários.
* **Sales Service:** Responsável pela lógica de negócios de vendas e eventos.
* **Notifications Service:** Gerencia o envio de notificações para os usuários.
* **Frontend:** A interface de usuário da aplicação, construída com React.

## Tecnologias Utilizadas

### Backend
* **Java:** Versões 17 e 24
* **Spring Boot:** Versões 3.4.5 a 3.5.4
* **Spring Cloud:** Versões 2024.0.1 e 2025.0.0
    * **Spring Cloud Gateway:** Para o serviço de API Gateway.
    * **Spring Cloud Netflix Eureka:** Para registro e descoberta de serviços.
    * **Spring Cloud OpenFeign:** Para comunicação declarativa entre os serviços.
* **Spring Data JPA:** Para persistência de dados.
* **Lombok:** Para reduzir o código boilerplate.
* **PostgreSQL:** Banco de dados relacional.
* **Maven:** Gerenciador de dependências e build.

### Frontend
* **React**
* **TypeScript**
* **Vite**
* **Axios** 
* **React Router DOM**

### Infraestrutura
* **Docker:** Para containerização da aplicação.

## Como Rodar o Projeto

### Pré-requisitos

* Docker e Docker Compose instalados.
* Java (JDK) 17 ou superior.
* Node.js e npm (ou yarn).
* Maven.

### Passo a passo

#### 1. Iniciar os Bancos de Dados com Docker

Primeiro, vamos iniciar os contêineres do PostgreSQL que servem como banco de dados para os microsserviços. Na raiz do projeto, execute:

```bash
docker compose -f .\compose.yaml up  
```

#### 2. Iniciar os Microsserviços do Backend

Agora, inicie os microsserviços na sua IDE (utilizando o Spring Boot Dashboard, por exemplo) na seguinte ordem para garantir que as dependências entre eles sejam resolvidas corretamente:

1. nameserver

2. user

3. sales

4. notifications

5. gateway

Aguarde cada serviço iniciar completamente antes de iniciar o próximo.

#### 3. Iniciar a Aplicação Frontend

Com o backend rodando, vamos iniciar a interface do usuário. Abra um novo terminal e execute os seguintes comandos:

```bash
# 1. Navegue até a pasta do frontend
cd ticket/frontend

# 2. Instale as dependências (só é necessário na primeira vez)
npm install

# 3. Inicie o servidor de desenvolvimento
npm run dev
```

#### 4. Acessar a Aplicação

Após seguir todos os passos, a aplicação estará disponível no seu navegador. Acesse o seguinte endereço: http://localhost:5174/
