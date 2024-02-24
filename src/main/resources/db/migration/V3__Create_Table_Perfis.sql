CREATE TABLE IF NOT EXISTS perfis (
    perfis integer,
    usuario_id integer NOT NULL
);

INSERT INTO perfis (perfis, usuario_id) VALUES (1, 1);
INSERT INTO perfis (perfis, usuario_id) VALUES (2, 1);

ALTER TABLE perfis DROP CONSTRAINT IF EXISTS fk_usuario;
ALTER TABLE ONLY perfis ADD CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id);