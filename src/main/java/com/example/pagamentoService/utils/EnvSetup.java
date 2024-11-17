package com.example.pagamentoService.utils;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.logging.Logger;

public class EnvSetup {
    private static EnvSetup instance;
    private static final Logger LOGGER = Logger.getLogger(EnvSetup.class.getName());
    private final JdbcTemplate jdbcTemplate;

    private EnvSetup() {
        Dotenv dotenv = Dotenv.load();
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        dataSource.setUsername(dotenv.get("DB_USERNAME"));
        dataSource.setPassword(dotenv.get("DB_PASSWORD"));
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public static EnvSetup getInstance() {
        if (instance == null) {
            instance = new EnvSetup();
        }
        return instance;
    }

    public void dataSources() {
        LOGGER.info("[-] CRIANDO USUÁRIO NO BANCO DE DADOS");
        Dotenv dotenv = Dotenv.load();
        System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
        System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
        criarTabelas();
    }

    public void criarTabelas() {
        LOGGER.info("[-] CRIANDO TABELAS NO BANCO DE DADOS");
        String sqlCreateUser = "CREATE USER API_PGTOS IDENTIFIED BY 123";
        String sqlCreateTableClientes = "CREATE TABLE \"API_PGTOS\".\"CLIENTES\" (\"NUM_SEQ_CLIENTE\" NUMBER(5,0), \"NOME\" VARCHAR2(100), \"CPF\" NUMBER(11,0), CONSTRAINT \"CLIENTES_PK\" PRIMARY KEY (\"NUM_SEQ_CLIENTE\") USING INDEX INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS TABLESPACE \"USERS\" ENABLE ) SEGMENT CREATION DEFERRED INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING TABLESPACE \"USERS\"";
        String sqlCreateIndexClientesPK = "CREATE UNIQUE INDEX \"API_PGTOS\".\"CLIENTES_PK\" ON \"API_PGTOS\".\"CLIENTES\" (\"NUM_SEQ_CLIENTE\") INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS TABLESPACE \"USERS\"";
        String sqlCommentClientesNumSeqCliente = "COMMENT ON COLUMN API_PGTOS.CLIENTES.NUM_SEQ_CLIENTE IS 'Sequencial'";
        String sqlCommentClientesNome = "COMMENT ON COLUMN API_PGTOS.CLIENTES.NOME IS 'Nome do cliente'";
        String sqlCommentClientesCpf = "COMMENT ON COLUMN API_PGTOS.CLIENTES.CPF IS 'Cpf do cliente'";
        String sqlCreateTablePedidos = "CREATE TABLE \"API_PGTOS\".\"PEDIDOS\" (\"NUM_SEQ_PEDIDO\" NUMBER(5,0) NOT NULL ENABLE, \"DESCR_PEDIDO\" VARCHAR2(100), \"NUM_ITEM\" NUMBER(5,0), \"VALOR_ITEM\" NUMBER(6,2), CONSTRAINT \"PEDIDOS_PK\" PRIMARY KEY (\"NUM_SEQ_PEDIDO\") USING INDEX INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS TABLESPACE \"USERS\" ENABLE ) SEGMENT CREATION DEFERRED INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING TABLESPACE \"USERS\"";
        String sqlCreateIndexPedidosPK = "CREATE UNIQUE INDEX \"API_PGTOS\".\"PEDIDOS_PK\" ON \"API_PGTOS\".\"PEDIDOS\" (\"NUM_SEQ_PEDIDO\") INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS TABLESPACE \"USERS\"";
        String sqlCommentPedidosNumSeqPedido = "COMMENT ON COLUMN API_PGTOS.PEDIDOS.NUM_SEQ_PEDIDO IS 'Sequencial'";
        String sqlCommentPedidosDescrPedido = "COMMENT ON COLUMN API_PGTOS.PEDIDOS.DESCR_PEDIDO IS 'Descricao do pedido'";
        String sqlCommentPedidosNumItem = "COMMENT ON COLUMN API_PGTOS.PEDIDOS.NUM_ITEM IS 'Codigo do item do pedido'";
        String sqlCommentPedidosValorItem = "COMMENT ON COLUMN API_PGTOS.PEDIDOS.VALOR_ITEM IS 'Valor do item do pedido'";
        String sqlCreateTableRegistroPgtoMensal = "CREATE TABLE \"API_PGTOS\".\"REGISTRO_PGTO_MENSAL\" (\"NUM_SEQ_RGPGTO\" NUMBER(5,0) NOT NULL ENABLE, \"DESCR_RGPGTO\" VARCHAR2(100), \"NUM_CLIENTE\" NUMBER(5,0), \"NUM_TIPO_PGTO\" NUMBER(5,0), \"DATAHORA_RGPGTO\" TIMESTAMP (6), \"NUM_PEDIDO\" NUMBER(5,0), CONSTRAINT \"REGISTRO_PGTO_MENSAL_PK\" PRIMARY KEY (\"NUM_SEQ_RGPGTO\") USING INDEX INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS TABLESPACE \"USERS\" ENABLE ) SEGMENT CREATION DEFERRED INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING TABLESPACE \"USERS\"";
        String sqlAddConstraintRegistroPgtoMensalTipoPgtoFK = "ALTER TABLE \"API_PGTOS\".\"REGISTRO_PGTO_MENSAL\" ADD CONSTRAINT \"REGISTRO_PGTO_MENSAL_TIPO_PGTO_FK\" FOREIGN KEY (\"NUM_TIPO_PGTO\") REFERENCES \"API_PGTOS\".\"TIPO_PGTO\" (\"NUM_SEQ_PGTO\") ENABLE";
        String sqlAddConstraintRegistroPgtoMensalClientesFK = "ALTER TABLE \"API_PGTOS\".\"REGISTRO_PGTO_MENSAL\" ADD CONSTRAINT \"REGISTRO_PGTO_MENSAL_CLIENTES_FK\" FOREIGN KEY (\"NUM_CLIENTE\") REFERENCES \"API_PGTOS\".\"CLIENTES\" (\"NUM_SEQ_CLIENTE\") ENABLE";
        String sqlAddConstraintRegistroPgtoMensalPedidosFK = "ALTER TABLE \"API_PGTOS\".\"REGISTRO_PGTO_MENSAL\" ADD CONSTRAINT \"REGISTRO_PGTO_MENSAL_PEDIDOS_FK\" FOREIGN KEY (\"NUM_PEDIDO\") REFERENCES \"API_PGTOS\".\"PEDIDOS\" (\"NUM_SEQ_PEDIDO\") ENABLE";
        String sqlCreateIndexRegistroPgtoMensalPK = "CREATE UNIQUE INDEX \"API_PGTOS\".\"REGISTRO_PGTO_MENSAL_PK\" ON \"API_PGTOS\".\"REGISTRO_PGTO_MENSAL\" (\"NUM_SEQ_RGPGTO\") INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS TABLESPACE \"USERS\"";
        String sqlCommentRegistroPgtoMensalNumSeqRgpgto = "COMMENT ON COLUMN API_PGTOS.REGISTRO_PGTO_MENSAL.NUM_SEQ_RGPGTO IS 'Sequencial'";
        String sqlCommentRegistroPgtoMensalDescrRgpgto = "COMMENT ON COLUMN API_PGTOS.REGISTRO_PGTO_MENSAL.DESCR_RGPGTO IS 'Descricao do pagamento'";
        String sqlCommentRegistroPgtoMensalNumCliente = "COMMENT ON COLUMN API_PGTOS.REGISTRO_PGTO_MENSAL.NUM_CLIENTE IS 'Cliente que realizou o pagamento'";
        String sqlCommentRegistroPgtoMensalNumTipoPgto = "COMMENT ON COLUMN API_PGTOS.REGISTRO_PGTO_MENSAL.NUM_TIPO_PGTO IS 'Tipo de pagamento utilizado na compra'";
        String sqlCommentRegistroPgtoMensalDatahoraRgpgto = "COMMENT ON COLUMN API_PGTOS.REGISTRO_PGTO_MENSAL.DATAHORA_RGPGTO IS 'Timestamp do pagamento'";
        String sqlCommentRegistroPgtoMensalNumPedido = "COMMENT ON COLUMN API_PGTOS.REGISTRO_PGTO_MENSAL.NUM_PEDIDO IS 'Pedido oriundo do pagamento'";
        String sqlCreateTableTipoPgto = "CREATE TABLE \"API_PGTOS\".\"TIPO_PGTO\" (\"NUM_SEQ_PGTO\" NUMBER(5,0) NOT NULL ENABLE, \"DESCR_PGTO\" VARCHAR2(10), CONSTRAINT \"TIPO_PGTO_PK\" PRIMARY KEY (\"NUM_SEQ_PGTO\") USING INDEX INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS TABLESPACE \"USERS\" ENABLE ) SEGMENT CREATION DEFERRED INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING TABLESPACE \"USERS\"";
        String sqlCreateIndexTipoPgtoPK = "CREATE UNIQUE INDEX \"API_PGTOS\".\"TIPO_PGTO_PK\" ON \"API_PGTOS\".\"TIPO_PGTO\" (\"NUM_SEQ_PGTO\") INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS TABLESPACE \"USERS\"";
        String sqlCommentTipoPgtoNumSeqPgto = "COMMENT ON COLUMN API_PGTOS.TIPO_PGTO.NUM_SEQ_PGTO IS 'Sequencial'";
        String sqlCommentTipoPgtoDescrPgto = "COMMENT ON COLUMN API_PGTOS.TIPO_PGTO.DESCR_PGTO IS 'Descricao do tipo de pagamento'";

        try {
            jdbcTemplate.execute(sqlCreateTableClientes);
        } catch (Exception e) {
            LOGGER.info("[ERRO] FALHA AO CRIAR TABELAS");
            throw new RuntimeException(e);
        }
    }
}