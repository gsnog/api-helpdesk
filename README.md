# 🎫 API de Helpdesk Corporativo (Sistema de Chamados)

Esta API foi desenvolvida para simular um sistema interno de gerenciamento de chamados de TI (Helpdesk). O foco arquitetural deste projeto é o domínio de **Bancos de Dados Relacionais (1:N)**, versionamento de SGBD e a simulação de tráfego de identificadores via Cabeçalhos HTTP (preparação para tokens de segurança).

---

## 🛠️ Tecnologias Utilizadas

* **Java 21+**
* **Spring Boot 3.x**
* **Spring Data JPA** (Com mapeamentos `@ManyToOne`)
* **PostgreSQL** (Banco de dados relacional robusto)
* **Docker & Docker Compose** (Infraestrutura via código)
* **Flyway** (Database Migrations com chaves estrangeiras)
* **Lombok** (Para manter as entidades e DTOs limpos e sem código verboso)

---

## 🏗️ Arquitetura e Diferenciais Técnicos

* **Modelagem Relacional (1:N):** Implementação de relacionamento entre `Funcionario` e `Chamado`, garantindo a integridade referencial com `FOREIGN KEY` diretamente no banco de dados via scripts do Flyway (`V1` e `V2`).
* **Clean Code com Lombok:** Redução drástica de *boilerplate code* (getters, setters e construtores) utilizando as anotações do Projeto Lombok (`@Getter`, `@Setter`, `@NoArgsConstructor`).
* **Segurança e Boas Práticas HTTP:** Substituição do envio de IDs sensíveis no corpo da requisição (JSON) pela extração direta dos cabeçalhos HTTP utilizando a anotação `@RequestHeader`.
* **Proteção de Entidades:** Uso rigoroso de DTOs (`records`) customizados. As rotas de listagem devolvem dados mesclados de duas tabelas diferentes de forma segura, sem expor as Entidades do JPA.

---

## 🚀 Endpoints da API

### 👤 Funcionários
* `POST /funcionarios` - Cadastra um novo funcionário (Valida e-mail único no SGBD).
* `GET /funcionarios` - Lista todos os funcionários.

### 🎫 Chamados
* `POST /chamados` - Abre um novo chamado de TI. 
  *(Requer o cabeçalho `X-Funcionario-Id` contendo o ID do solicitante).*
* `GET /chamados` - Lista todos os chamados, cruzando os dados e exibindo o nome do funcionário solicitante.
* `PUT /chamados/{id}/fechar` - Altera o status do chamado para "FECHADO" (Endpoint sem Body).

---

## ⚙️ Como executar localmente

1. Clone o repositório:
   ```bash
   git clone [https://github.com/gsnog/api-helpdesk.git](https://github.com/gsnog/api-helpdesk.git)

2. Acesse a pasta do projeto e suba o banco de dados via Docker:

```bash
docker-compose up -d

3. Execute a aplicação Spring Boot:

```bash
./mvnw spring-boot:run
(Nota: O Flyway criará as tabelas e as chaves estrangeiras automaticamente na ordem correta).
