import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionFactory {

    private ConnectionFactory() {
    }

    public static Connection getConnection() throws SQLException {
        String url = EnvLoader.get("FINSMART_DB_URL");
        String user = EnvLoader.get("FINSMART_DB_USER");
        String password = EnvLoader.get("FINSMART_DB_PASSWORD");

        if (url == null || user == null || password == null) {
            throw new SQLException(
                    "Credenciais do banco não configuradas. "
                            + "Configure o arquivo .env ou as variáveis de ambiente: "
                            + "FINSMART_DB_URL, FINSMART_DB_USER, FINSMART_DB_PASSWORD"
            );
        }

        return DriverManager.getConnection(url, user, password);
    }
}