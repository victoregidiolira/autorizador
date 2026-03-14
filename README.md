# Autorizador

API RESTful built with Java and Spring Boot that simulates a card payment terminal authorizer.

## What does it do?

Receives a purchase request, validates if the account exists and has sufficient balance, then approves or rejects the transaction — keeping a full audit log of every attempt.

## Stack

- Java 25
- Spring Boot 4
- Spring Data JPA
- PostgreSQL 15
- Docker

## How to run

Prerequisite: Docker installed.
```bash
docker-compose up
```

That's it. The application runs on port `8080`.

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

API RESTful desenvolvida em Java com Spring Boot que simula o autorizador de transações de uma maquininha de cartão.

## O que faz?

Recebe uma requisição de compra, valida se a conta existe e se há saldo suficiente, e aprova ou rejeita a transação — mantendo auditoria completa de cada tentativa.

## Stack

- Java 25
- Spring Boot 4
- Spring Data JPA
- PostgreSQL 15
- Docker

## Como rodar

Pré-requisito: ter Docker instalado.
```bash
docker-compose up
```

Só isso. A aplicação sobe na porta `8080`.

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
