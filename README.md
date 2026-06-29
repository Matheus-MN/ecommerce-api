# 🛒 E-commerce API

API REST para Sistema de Controle de Estoque e Pedidos de um E-commerce, desenvolvida com Java, Spring Boot e PostgreSQL.

## 🚀 Tecnologias

- **Java 21**
- **Spring Boot 3.5**
- **Spring Data JPA**
- **PostgreSQL 16**
- **Docker e Docker Compose**
- **Hibernate Validator**
- **Maven**

## 📐 Arquitetura

Projeto estruturado em camadas:

- **controller** — Endpoints REST
- **service** — Regras de negócio
- **repository** — Acesso ao banco de dados
- **model** — Entidades JPA
- **dto** — Objetos de transferência de dados
- **exception** — Tratamento global de erros

## 📦 Endpoints

### Produtos
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | /api/produtos | Lista todos os produtos |
| GET | /api/produtos/{id} | Busca produto por ID |
| POST | /api/produtos | Cria novo produto |
| PUT | /api/produtos/{id} | Atualiza produto |
| DELETE | /api/produtos/{id} | Remove produto |

### Pedidos
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | /api/pedidos | Lista todos os pedidos |
| GET | /api/pedidos/{id} | Busca pedido por ID |
| POST | /api/pedidos | Cria novo pedido |
| PATCH | /api/pedidos/{id}/cancelar | Cancela pedido |

## ▶️ Como executar

### Pré-requisitos
- Docker
- Docker Compose

### Rodando o projeto

1. Clone o repositório:
   git clone https://github.com/Matheus-MN/ecommerce-api.git

2. Entre na pasta:
   cd ecommerce-api

3. Suba os containers:
   docker-compose up --build

A API estará disponível em http://localhost:8080

## 📋 Exemplos de uso

### Criar produto
POST /api/produtos
{
"nome": "Notebook Dell",
"descricao": "Notebook Dell Inspiron 15, 16GB RAM",
"preco": 3500.00,
"quantidadeEstoque": 10
}

### Criar pedido
POST /api/pedidos
{
"itens": [
{
"produtoId": 1,
"quantidade": 2
}
]
}

## 👨‍💻 Autor

**Matheus Marinho Nascimento**
- GitHub: https://github.com/Matheus-MN
- LinkedIn: https://www.linkedin.com/in/matheus-marinho-n-661813250