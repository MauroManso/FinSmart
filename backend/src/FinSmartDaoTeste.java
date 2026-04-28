import java.math.BigDecimal;
import java.util.List;

public class FinSmartDaoTeste {

    public static void main(String[] args) {
        ProdutoFinanceiroDAO produtoFinanceiroDAO = new ProdutoFinanceiroDAO();

        try {
            List<ProdutoFinanceiro> novosProdutos = List.of(
                    new ProdutoFinanceiro(0, "Conta Digital Basica", "conta", new BigDecimal("0.00"),
                            "Conta PJ sem tarifa mensal"),
                    new ProdutoFinanceiro(0, "Conta Digital Premium", "conta", new BigDecimal("19.90"),
                            "Conta PJ com pacote de beneficios"),
                    new ProdutoFinanceiro(0, "Credito Capital de Giro", "credito", new BigDecimal("1.85"),
                            "Linha de credito para capital de giro"),
                    new ProdutoFinanceiro(0, "Antecipacao de Recebiveis", "credito", new BigDecimal("1.40"),
                            "Antecipacao de fluxo de caixa"),
                    new ProdutoFinanceiro(0, "Investimento CDB PJ", "investimento", new BigDecimal("0.95"),
                            "Aplicacao de curto prazo para empresas")
            );

            for (ProdutoFinanceiro produto : novosProdutos) {
                produtoFinanceiroDAO.insert(produto);
            }

            List<ProdutoFinanceiro> produtosCadastrados = produtoFinanceiroDAO.getAll();
            System.out.println("Produtos financeiros encontrados: " + produtosCadastrados.size());

            for (ProdutoFinanceiro produto : produtosCadastrados) {
                System.out.println(
                        "ID=" + produto.getIdProduto()
                                + " | Nome=" + produto.getNomeProduto()
                                + " | Tipo=" + produto.getTipo()
                                + " | Taxa=" + produto.getTaxaBase()
                                + " | Ativo=" + produto.isAtivo()
                );
            }

            // Adaptacao do padrao DAO para outras entidades do sistema.
            TransacaoDAO transacaoDAO = new TransacaoDAO();
            MovimentacaoFinanceiraDAO movimentacaoDAO = new MovimentacaoFinanceiraDAO();

            System.out.println("Transacoes encontradas: " + transacaoDAO.getAll().size());
            System.out.println("Movimentacoes encontradas: " + movimentacaoDAO.getAll().size());
        } catch (Exception e) {
            System.err.println("Falha ao executar testes DAO: " + e.getMessage());
        }
    }
}