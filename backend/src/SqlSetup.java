import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlSetup {

    private static final String[] CREATE_TABLES = {
            // Criar sequências
            "CREATE SEQUENCE seq_produtos_financeiros START WITH 1 INCREMENT BY 1",
            "CREATE SEQUENCE seq_transacoes START WITH 1 INCREMENT BY 1",
            "CREATE SEQUENCE seq_movimentacoes_financeiras START WITH 1 INCREMENT BY 1",

            // Criar tabelas
            "CREATE TABLE produtos_financeiros ("
                    + "id_produto NUMBER PRIMARY KEY,"
                    + "nome_produto VARCHAR2(255) NOT NULL,"
                    + "tipo VARCHAR2(20) NOT NULL,"
                    + "taxa_base NUMBER(5,2) DEFAULT 0,"
                    + "descricao VARCHAR2(1000),"
                    + "ativo CHAR(1) DEFAULT 'Y'"
                    + ")",

            "CREATE TABLE contas ("
                    + "id_conta NUMBER PRIMARY KEY,"
                    + "numero_conta VARCHAR2(20) NOT NULL UNIQUE,"
                    + "id_empresa NUMBER NOT NULL,"
                    + "tipo_conta VARCHAR2(20) DEFAULT 'corrente',"
                    + "saldo NUMBER(15,2) DEFAULT 0,"
                    + "data_abertura TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
                    + "status VARCHAR2(20) DEFAULT 'ativa'"
                    + ")",

            "CREATE TABLE transacoes ("
                    + "id_transacao NUMBER PRIMARY KEY,"
                    + "id_conta_origem NUMBER NOT NULL,"
                    + "id_conta_destino NUMBER NOT NULL,"
                    + "valor NUMBER(15,2) NOT NULL,"
                    + "tipo VARCHAR2(20) NOT NULL,"
                    + "data_transacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
                    + "descricao VARCHAR2(500),"
                    + "status VARCHAR2(20) DEFAULT 'concluida'"
                    + ")",

            "CREATE TABLE movimentacoes_financeiras ("
                    + "id_movimentacao NUMBER PRIMARY KEY,"
                    + "id_conta NUMBER NOT NULL,"
                    + "tipo_movimentacao VARCHAR2(20) NOT NULL,"
                    + "valor NUMBER(15,2) NOT NULL,"
                    + "data_movimentacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
                    + "descricao VARCHAR2(500)"
                    + ")"
    };

    public static void main(String[] args) {
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement()) {

            for (String sql : CREATE_TABLES) {
                try {
                    statement.execute(sql);
                    System.out.println("✓ Executado: " + sql.substring(0, Math.min(60, sql.length())) + "...");
                } catch (SQLException e) {
                    if (e.getErrorCode() == 955) {
                        System.out.println("⚠ Objeto já existe: " + sql.substring(0, Math.min(60, sql.length())) + "...");
                    } else {
                        System.err.println("✗ Erro: " + e.getMessage());
                    }
                }
            }

            System.out.println("\n✓ Setup concluído! As tabelas estão prontas para testes.");
        } catch (SQLException e) {
            System.err.println("Falha ao conectar ao banco: " + e.getMessage());
        }
    }
}
