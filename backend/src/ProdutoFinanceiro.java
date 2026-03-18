import java.math.BigDecimal;
import java.util.List;

public class ProdutoFinanceiro {

    private int idProduto;
    private String nomeProduto;
    private String tipo; // "credito", "investimento", "conta"
    private BigDecimal taxaBase;
    private String descricao;
    private boolean ativo;

    public ProdutoFinanceiro() {
        this.ativo = true;
    }

    public ProdutoFinanceiro(int idProduto, String nomeProduto, String tipo,
                              BigDecimal taxaBase, String descricao) {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.tipo = tipo;
        this.taxaBase = taxaBase;
        this.descricao = descricao;
        this.ativo = true;
    }

    public void cadastrarProduto() {
        System.out.println("Cadastrando produto financeiro: " + nomeProduto);
    }

    public void ativarProduto() {
        this.ativo = true;
        System.out.println("Ativando produto financeiro: " + nomeProduto);
    }

    public void desativarProduto() {
        this.ativo = false;
        System.out.println("Desativando produto financeiro: " + nomeProduto);
    }

    public void atualizarTaxaBase(BigDecimal novaTaxa) {
        if (novaTaxa != null && novaTaxa.compareTo(BigDecimal.ZERO) >= 0) {
            this.taxaBase = novaTaxa;
        }
        System.out.println("Atualizando taxa base do produto " + nomeProduto
                + " para: " + novaTaxa + "%");
    }

    public static List<ProdutoFinanceiro> listarPorTipo(String tipo) {
        System.out.println("Listando produtos financeiros do tipo: " + tipo);
        return null;
    }

    public int getIdProduto() { return idProduto; }
    public void setIdProduto(int idProduto) { this.idProduto = idProduto; }

    public String getNomeProduto() { return nomeProduto; }
    public void setNomeProduto(String nomeProduto) { this.nomeProduto = nomeProduto; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public BigDecimal getTaxaBase() { return taxaBase; }
    public void setTaxaBase(BigDecimal taxaBase) { this.taxaBase = taxaBase; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
}
