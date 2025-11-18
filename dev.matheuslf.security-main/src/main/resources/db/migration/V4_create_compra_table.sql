CREATE TABLE compras (
    id SERIAL PRIMARY KEY,
    data_compra TIMESTAMP NOT NULL,
    valor_pago NUMERIC(10,2),
    user_id INTEGER NOT NULL,
    jogo_id INTEGER NOT NULL,
    CONSTRAINT fk_compra_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_compra_jogo FOREIGN KEY (jogo_id) REFERENCES jogos(id) ON DELETE CASCADE
);
