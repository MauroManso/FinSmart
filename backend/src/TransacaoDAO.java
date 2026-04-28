import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransacaoDAO {

    private static final String SQL_INSERT = "INSERT INTO transacoes "
            + "(id_transacao, id_conta_origem, id_conta_destino, valor, tipo, data_transacao, descricao, status) "
            + "VALUES (seq_transacoes.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_SELECT_ALL = "SELECT id_transacao, id_conta_origem, id_conta_destino, valor, tipo, "
            + "data_transacao, descricao, status FROM transacoes ORDER BY id_transacao";

    public void insert(Transacao transacao) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT)) {

            statement.setInt(1, transacao.getIdContaOrigem());
            statement.setInt(2, transacao.getIdContaDestino());
            statement.setBigDecimal(3, transacao.getValor());
            statement.setString(4, transacao.getTipo());
            statement.setTimestamp(5, toTimestamp(transacao.getDataTransacao()));
            statement.setString(6, transacao.getDescricao());
            statement.setString(7, transacao.getStatus());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao inserir transacao: " + e.getMessage());
        }
    }

    public List<Transacao> getAll() {
        List<Transacao> transacoes = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                transacoes.add(buildTransacao(resultSet));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao consultar transacoes: " + e.getMessage());
        }

        return transacoes;
    }

    private Transacao buildTransacao(ResultSet resultSet) throws SQLException {
        Transacao transacao = new Transacao();

        transacao.setIdTransacao(resultSet.getInt("id_transacao"));
        transacao.setIdContaOrigem(resultSet.getInt("id_conta_origem"));
        transacao.setIdContaDestino(resultSet.getInt("id_conta_destino"));
        transacao.setValor(resultSet.getBigDecimal("valor"));
        transacao.setTipo(resultSet.getString("tipo"));

        Timestamp data = resultSet.getTimestamp("data_transacao");
        if (data != null) {
            transacao.setDataTransacao(data.toLocalDateTime());
        }

        transacao.setDescricao(resultSet.getString("descricao"));
        transacao.setStatus(resultSet.getString("status"));

        return transacao;
    }

    private Timestamp toTimestamp(LocalDateTime data) {
        return Timestamp.valueOf(data == null ? LocalDateTime.now() : data);
    }
}