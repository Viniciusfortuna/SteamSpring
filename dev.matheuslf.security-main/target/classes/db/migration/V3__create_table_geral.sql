CREATE TABLE perfis (
    id SERIAL PRIMARY KEY,
    nickname VARCHAR(255),
    bio TEXT,
    nivel INTEGER,
    imagem_perfil VARCHAR(255),
    user_id INTEGER UNIQUE,
    CONSTRAINT fk_perfil_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE jogos (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    descricao TEXT,
    categoria VARCHAR(255),
    preco NUMERIC(10,2),
    data_lancamento DATE
);

CREATE TABLE bibliotecas (
    id SERIAL PRIMARY KEY,
    data_criacao DATE NOT NULL,
    user_id INTEGER NOT NULL,
    CONSTRAINT fk_biblioteca_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE biblioteca_jogo (
    biblioteca_id INTEGER NOT NULL,
    jogo_id INTEGER NOT NULL,
    PRIMARY KEY (biblioteca_id, jogo_id),
    CONSTRAINT fk_bj_biblioteca FOREIGN KEY (biblioteca_id) REFERENCES bibliotecas(id) ON DELETE CASCADE,
    CONSTRAINT fk_bj_jogo FOREIGN KEY (jogo_id) REFERENCES jogos(id) ON DELETE CASCADE
);

CREATE TABLE inventarios (
    id SERIAL PRIMARY KEY,
    nome_item VARCHAR(255),
    raridade VARCHAR(255),
    tipo_item VARCHAR(255),
    user_id INTEGER NOT NULL,
    CONSTRAINT fk_inventario_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE inventario_jogo (
    inventario_id INTEGER NOT NULL,
    jogo_id INTEGER NOT NULL,
    PRIMARY KEY (inventario_id, jogo_id),
    CONSTRAINT fk_ij_inventario FOREIGN KEY (inventario_id) REFERENCES inventarios(id) ON DELETE CASCADE,
    CONSTRAINT fk_ij_jogo FOREIGN KEY (jogo_id) REFERENCES jogos(id) ON DELETE CASCADE
);


