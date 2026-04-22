CREATE TABLE chamado (
                         id BIGSERIAL PRIMARY KEY,
                         titulo VARCHAR(255) NOT NULL,
                         descricao TEXT NOT NULL,
                         status VARCHAR(50) NOT NULL,
                         funcionario_id BIGINT NOT NULL,
                         FOREIGN KEY (funcionario_id) REFERENCES funcionario (id)
);