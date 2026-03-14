# Autorizador

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