  COMANDOS SQL

-- BANCO DE DADOS: InovaLocal
-- Estrutura das tabelas: usuario e occurrences


-- Cria o banco de dados (opcional)
CREATE DATABASE IF NOT EXISTS inovalocal;
USE inovalocal;


-- Tabela: usuario

CREATE TABLE usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    role VARCHAR(50) NOT NULL, -- ADMIN ou USER
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);


-- Tabela: occurrences

CREATE TABLE occurrences (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(2000),
    category VARCHAR(255),
    status VARCHAR(50) DEFAULT 'OPEN',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    user_id BIGINT,
    CONSTRAINT fk_occurrence_user FOREIGN KEY (user_id) REFERENCES usuario(id) ON DELETE SET NULL
);


-- Inserts

INSERT INTO usuario (name, email, role) VALUES
('Administrador', 'admin@inovalocal.com', 'ADMIN'),
('Usuário Padrão', 'user@inovalocal.com', 'USER');

INSERT INTO occurrences (title, description, category, status, user_id) VALUES
('Falha de energia', 'Queda de energia no setor 3.', 'Infraestrutura', 'OPEN', 1),
('Computador travando', 'O PC do escritório não inicia corretamente.', 'Suporte Técnico', 'IN_PROGRESS', 2);

-- Ver todos os usuários
SELECT * FROM usuario;

-- Ver todas as ocorrências (com nome do usuário)
SELECT o.id, o.title, o.status, o.category, u.name AS reporter, o.created_at
FROM occurrences o
LEFT JOIN usuario u ON o.user_id = u.id
ORDER BY o.created_at DESC;
