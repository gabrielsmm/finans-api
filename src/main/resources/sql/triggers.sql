-- TRIGGER QUE ATUALIZA O ORÇAMENTO DE ACORDO COM A INSERÇÃO OU ATUALIZAÇÃO DE TRANSAÇÕES
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

-- TRIGGER QUE ATUALIZA O ORÇAMENTO DE ACORDO COM A DELEÇÃO DE TRANSAÇÕES
CREATE OR REPLACE FUNCTION atualizar_orcamentos_delecao()
RETURNS TRIGGER AS $$
DECLARE
    tipo_transacao INT;
    valor_transacao DECIMAL(10, 2);
    data_transacao DATE;
    usuario_transacao INT;
BEGIN
    SELECT tipo INTO tipo_transacao FROM CATEGORIAS WHERE id = OLD.categoria_id;
    valor_transacao := OLD.valor;
    data_transacao := OLD.data;
    usuario_transacao := OLD.usuario_id;

    IF tipo_transacao = 1 THEN
        UPDATE ORCAMENTOS
        SET valor_receitas = valor_receitas - valor_transacao
        WHERE data_transacao >= data_inicio AND data_transacao <= data_fim
        AND usuario_id = usuario_transacao;
    ELSEIF tipo_transacao = 2 THEN
        UPDATE ORCAMENTOS
        SET valor_despesas = valor_despesas - valor_transacao
        WHERE data_transacao >= data_inicio AND data_transacao <= data_fim
        AND usuario_id = usuario_transacao;
    END IF;

    RETURN OLD;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER transacoes_delecao_trigger
AFTER DELETE ON TRANSACOES
FOR EACH ROW
EXECUTE FUNCTION atualizar_orcamentos_delecao();

-- TRIGGER QUE VERIFICA A EXISTÊNCIA DE TRANSAÇÕES ASSOCIADAS A UM ORÇAMENTO ANTES DE DELETAR
CREATE OR REPLACE FUNCTION check_transactions_on_orcamento_delete()
RETURNS TRIGGER AS $$
DECLARE
    has_transactions INTEGER;
BEGIN
    -- Verifica se existem transações com datas dentro do período do orçamento
    SELECT COUNT(*) INTO has_transactions
    FROM transacoes
    WHERE data >= OLD.data_inicio
        AND data <= OLD.data_fim;

    -- Se houver transações, cancela a deleção do orçamento
    IF has_transactions > 0 THEN
        RAISE EXCEPTION 'Não é possível excluir o orçamento. Existem transações associadas a ele.';
    END IF;

    RETURN OLD;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER before_orcamento_delete
BEFORE DELETE ON orcamentos
FOR EACH ROW
EXECUTE FUNCTION check_transactions_on_orcamento_delete();