import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoFinanceiroDAO {

    private static final String SQL_INSERT = "INSERT INTO produtos_financeiros "
            + "(id_produto, nome_produto, tipo, taxa_base, descricao, ativo) "
            + "VALUES (seq_produtos_financeiros.NEXTVAL, ?, ?, ?, ?, ?)";

    private static final String SQL_SELECT_ALL = "SELECT id_produto, nome_produto, tipo, taxa_base, descricao, ativo "
            + "FROM produtos_financeiros ORDER BY id_produto";

    public void insert(ProdutoFinanceiro produto) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT)) {

            statement.setString(1, produto.getNomeProduto());
            statement.setString(2, produto.getTipo());
            statement.setBigDecimal(3, produto.getTaxaBase());
            statement.setString(4, produto.getDescricao());
            statement.setString(5, produto.isAtivo() ? "Y" : "N");

            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao inserir produto financeiro: " + e.getMessage());
        }
    }

    public List<ProdutoFinanceiro> getAll() {
        List<ProdutoFinanceiro> produtos = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                produtos.add(buildProduto(resultSet));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao consultar produtos financeiros: " + e.getMessage());
        }

        return produtos;
    }

    private ProdutoFinanceiro buildProduto(ResultSet resultSet) throws SQLException {
        ProdutoFinanceiro produto = new ProdutoFinanceiro();

        produto.setIdProduto(resultSet.getInt("id_produto"));
        produto.setNomeProduto(resultSet.getString("nome_produto"));
        produto.setTipo(resultSet.getString("tipo"));
        produto.setTaxaBase(resultSet.getBigDecimal("taxa_base"));
        produto.setDescricao(resultSet.getString("descricao"));
        produto.setAtivo("Y".equalsIgnoreCase(resultSet.getString("ativo")));

        return produto;
    }
}