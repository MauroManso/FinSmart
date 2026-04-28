import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MovimentacaoFinanceiraDAO {

    private static final String SQL_INSERT = "INSERT INTO movimentacoes_financeiras "
            + "(id_movimentacao, id_conta, tipo_movimentacao, valor, data_movimentacao, descricao) "
            + "VALUES (seq_movimentacoes_financeiras.NEXTVAL, ?, ?, ?, ?, ?)";

    private static final String SQL_SELECT_ALL = "SELECT id_movimentacao, id_conta, tipo_movimentacao, valor, "
            + "data_movimentacao, descricao FROM movimentacoes_financeiras ORDER BY id_movimentacao";

    public void insert(MovimentacaoFinanceira movimentacao) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT)) {

            statement.setInt(1, movimentacao.getIdConta());
            statement.setString(2, movimentacao.getTipoMovimentacao());
            statement.setBigDecimal(3, movimentacao.getValor());
            statement.setTimestamp(4, toTimestamp(movimentacao.getDataMovimentacao()));
            statement.setString(5, movimentacao.getDescricao());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao inserir movimentacao financeira: " + e.getMessage());
        }
    }

    public List<MovimentacaoFinanceira> getAll() {
        List<MovimentacaoFinanceira> movimentacoes = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                movimentacoes.add(buildMovimentacao(resultSet));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao consultar movimentacoes financeiras: " + e.getMessage());
        }

        return movimentacoes;
    }

    private MovimentacaoFinanceira buildMovimentacao(ResultSet resultSet) throws SQLException {
        MovimentacaoFinanceira movimentacao = new MovimentacaoFinanceira();

        movimentacao.setIdMovimentacao(resultSet.getInt("id_movimentacao"));
        movimentacao.setIdConta(resultSet.getInt("id_conta"));
        movimentacao.setTipoMovimentacao(resultSet.getString("tipo_movimentacao"));
        movimentacao.setValor(resultSet.getBigDecimal("valor"));

        Timestamp data = resultSet.getTimestamp("data_movimentacao");
        if (data != null) {
            movimentacao.setDataMovimentacao(data.toLocalDateTime());
        }

        movimentacao.setDescricao(resultSet.getString("descricao"));

        return movimentacao;
    }

    private Timestamp toTimestamp(LocalDateTime data) {
        return Timestamp.valueOf(data == null ? LocalDateTime.now() : data);
    }
}