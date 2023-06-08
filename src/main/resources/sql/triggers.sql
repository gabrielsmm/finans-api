CREATE OR REPLACE FUNCTION atualizar_orcamentos()
RETURNS TRIGGER AS $$
DECLARE
    tipo_transacao INT;
    valor_transacao DECIMAL(10, 2);
    data_transacao DATE;
    usuario_transacao INT;
BEGIN
    SELECT tipo INTO tipo_transacao FROM CATEGORIAS WHERE id = NEW.categoria_id;
    valor_transacao := NEW.valor;
    data_transacao := NEW.data;
    usuario_transacao := NEW.usuario_id;

    IF tipo_transacao = 1 THEN
        UPDATE ORCAMENTOS
        SET valor_receitas = valor_receitas + valor_transacao
        WHERE data_transacao >= data_inicio AND data_transacao <= data_fim
        AND usuario_id = usuario_transacao;
    ELSEIF tipo_transacao = 2 THEN
        UPDATE ORCAMENTOS
        SET valor_despesas = valor_despesas + valor_transacao
        WHERE data_transacao >= data_inicio AND data_transacao <= data_fim
        AND usuario_id = usuario_transacao;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER transacoes_trigger
AFTER INSERT OR UPDATE ON TRANSACOES
FOR EACH ROW
EXECUTE FUNCTION atualizar_orcamentos();