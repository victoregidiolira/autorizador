# Autorizador

API RESTful built with Java and Spring Boot that simulates a card payment terminal authorizer. Includes a minimal frontend for manual testing.

## What does it do?

Receives a purchase request, validates if the account exists and has sufficient balance, then approves or rejects the transaction — keeping a full audit log of every attempt.

## Stack

- Java 25
- Spring Boot 4
- Spring Data JPA
- PostgreSQL 15
- Docker
- HTML / CSS / JavaScript

## How to run

Prerequisite: Docker installed.

**1. Start the backend and database:**
```bash
docker-compose up
```

The API runs on port `8080`.

**2. Open the frontend:**

Open `frontend/index.html` in your browser.

## Endpoints

### Authorize transaction
```
POST /transactions
```

**Body:**
```json
{
  "accountId": 1,
  "amount": 150.00,
  "merchant": "Store Name"
}
```

**Responses:**
- `200 OK` → `APPROVED`
- `422` → `REJECTED: INSUFFICIENT FUNDS.`
- `422` → `REJECTED: ACCOUNT NOT FOUND.`

---

# Autorizador (Português)

API RESTful desenvolvida em Java com Spring Boot que simula o autorizador de transações de uma maquininha de cartão. Inclui um frontend minimalista para testes manuais.

## O que faz?

Recebe uma requisição de compra, valida se a conta existe e se há saldo suficiente, e aprova ou rejeita a transação — mantendo auditoria completa de cada tentativa.

## Stack

- Java 25
- Spring Boot 4
- Spring Data JPA
- PostgreSQL 15
- Docker
- HTML / CSS / JavaScript

## Como rodar

Pré-requisito: ter Docker instalado.

**1. Sobe o backend e o banco:**
```bash
docker-compose up
```

A API sobe na porta `8080`.

**2. Abre o frontend:**

Abre o arquivo `frontend/index.html` no navegador.

## Endpoints

### Autorizar transação
```
POST /transactions
```

**Body:**
```json
{
  "accountId": 1,
  "amount": 150.00,
  "merchant": "Mercadão da Vila"
}
```

**Respostas:**
- `200 OK` → `APPROVED`
- `422` → `REJECTED: INSUFFICIENT FUNDS.`
- `422` → `REJECTED: ACCOUNT NOT FOUND.`