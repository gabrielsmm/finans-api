CREATE TABLE IF NOT EXISTS categorias (
    id integer NOT NULL,
    tipo integer,
    nome character varying(255),
    PRIMARY KEY (id)
);

CREATE SEQUENCE IF NOT EXISTS categorias_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE categorias_id_seq OWNED BY categorias.id;

ALTER TABLE ONLY categorias ALTER COLUMN id SET DEFAULT nextval('categorias_id_seq'::regclass);

INSERT INTO categorias (id, tipo, nome) VALUES (1, 1, 'Salário');
INSERT INTO categorias (id, tipo, nome) VALUES (2, 1, 'Bonificações');
INSERT INTO categorias (id, tipo, nome) VALUES (3, 1, 'Aluguel');
INSERT INTO categorias (id, tipo, nome) VALUES (4, 1, 'Investimentos');
INSERT INTO categorias (id, tipo, nome) VALUES (5, 1, 'Vendas');
INSERT INTO categorias (id, tipo, nome) VALUES (6, 1, 'Reembolsos');
INSERT INTO categorias (id, tipo, nome) VALUES (7, 1, 'Juros');
INSERT INTO categorias (id, tipo, nome) VALUES (8, 1, 'Empréstimos');
INSERT INTO categorias (id, tipo, nome) VALUES (9, 1, 'Outras Receitas');
INSERT INTO categorias (id, tipo, nome) VALUES (10, 2, 'Moradia');
INSERT INTO categorias (id, tipo, nome) VALUES (11, 2, 'Alimentação');
INSERT INTO categorias (id, tipo, nome) VALUES (12, 2, 'Transporte');
INSERT INTO categorias (id, tipo, nome) VALUES (13, 2, 'Saúde');
INSERT INTO categorias (id, tipo, nome) VALUES (14, 2, 'Educação');
INSERT INTO categorias (id, tipo, nome) VALUES (15, 2, 'Lazer');
INSERT INTO categorias (id, tipo, nome) VALUES (16, 2, 'Vestuário');
INSERT INTO categorias (id, tipo, nome) VALUES (17, 2, 'Serviços Públicos');
INSERT INTO categorias (id, tipo, nome) VALUES (18, 2, 'Seguros');
INSERT INTO categorias (id, tipo, nome) VALUES (19, 2, 'Impostos');
INSERT INTO categorias (id, tipo, nome) VALUES (20, 2, 'Dívidas/Empréstimos');
INSERT INTO categorias (id, tipo, nome) VALUES (21, 2, 'Doações');
INSERT INTO categorias (id, tipo, nome) VALUES (22, 2, 'Manutenção de Veículos');
INSERT INTO categorias (id, tipo, nome) VALUES (23, 2, 'Viagens');
INSERT INTO categorias (id, tipo, nome) VALUES (24, 2, 'Outras Despesas');