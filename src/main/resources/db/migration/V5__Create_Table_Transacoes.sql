CREATE TABLE IF NOT EXISTS transacoes (
    categoria_id integer,
    data date,
    id integer NOT NULL,
    orcamento_id integer,
    usuario_id integer,
    valor double precision,
    descricao character varying(255),
    PRIMARY KEY(id)
);

CREATE SEQUENCE IF NOT EXISTS transacoes_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE transacoes_id_seq OWNED BY transacoes.id;

ALTER TABLE ONLY transacoes ALTER COLUMN id SET DEFAULT nextval('transacoes_id_seq'::regclass);

-- INSERT INTO transacoes (categoria_id, data, id, orcamento_id, usuario_id, valor, descricao) VALUES (17, '2024-02-24', 1, 1, 1, 90, 'Teste despesa');
-- INSERT INTO transacoes (categoria_id, data, id, orcamento_id, usuario_id, valor, descricao) VALUES (2, '2024-02-24', 2, 1, 1, 50, 'Teste receita');

ALTER TABLE transacoes DROP CONSTRAINT IF EXISTS fk_usuario;
ALTER TABLE ONLY transacoes ADD CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id);

ALTER TABLE transacoes DROP CONSTRAINT IF EXISTS fk_categoria;
ALTER TABLE ONLY transacoes ADD CONSTRAINT fk_categoria FOREIGN KEY (categoria_id) REFERENCES categorias(id);

ALTER TABLE transacoes DROP CONSTRAINT IF EXISTS fk_orcamento;
ALTER TABLE ONLY transacoes ADD CONSTRAINT fk_orcamento FOREIGN KEY (orcamento_id) REFERENCES orcamentos(id);