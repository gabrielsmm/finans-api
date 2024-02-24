CREATE TABLE IF NOT EXISTS usuarios (
    id integer NOT NULL,
    email character varying(255),
    nome character varying(255),
    senha character varying(255),
    PRIMARY KEY(id)
);

CREATE SEQUENCE IF NOT EXISTS usuarios_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE usuarios_id_seq OWNED BY usuarios.id;

ALTER TABLE ONLY usuarios ALTER COLUMN id SET DEFAULT nextval('usuarios_id_seq'::regclass);

ALTER TABLE usuarios DROP CONSTRAINT IF EXISTS email_unique;
ALTER TABLE ONLY usuarios ADD CONSTRAINT email_unique UNIQUE (email);

INSERT INTO usuarios (id, email, nome, senha) VALUES (1, 'gabriel@teste.com', 'Gabriel Mendes', '$2a$10$0Y7oV.wzcvyFa1y98KnvAO4caywHSUzJUgsBWod3E4Ghc8Uqx1aoe') ON CONFLICT DO NOTHING;