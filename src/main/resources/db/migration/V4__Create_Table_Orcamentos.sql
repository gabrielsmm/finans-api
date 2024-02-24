CREATE TABLE IF NOT EXISTS orcamentos (
    data_fim date,
    data_inicio date,
    id integer NOT NULL,
    usuario_id integer,
    valor double precision,
    nome character varying(255),
    PRIMARY KEY(id)
);

CREATE SEQUENCE IF NOT EXISTS orcamentos_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE orcamentos_id_seq OWNED BY orcamentos.id;

ALTER TABLE ONLY orcamentos ALTER COLUMN id SET DEFAULT nextval('orcamentos_id_seq'::regclass);

-- INSERT INTO orcamentos (data_fim, data_inicio, id, usuario_id, valor, nome) VALUES ('2023-06-30', '2023-06-01', 1, 1, 2000, 'Orçamento Junho');

ALTER TABLE orcamentos DROP CONSTRAINT IF EXISTS fk_usuario;
ALTER TABLE ONLY orcamentos ADD CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id);