import java.math.BigDecimal;
import java.time.LocalDateTime;

public abstract class MovimentoFinanceiroBase {

    private BigDecimal valor;
    private LocalDateTime dataOperacao;
    private String descricao;

    protected MovimentoFinanceiroBase() {
        this(BigDecimal.ZERO, LocalDateTime.now(), "");
    }

    protected MovimentoFinanceiroBase(BigDecimal valor, LocalDateTime dataOperacao, String descricao) {
        this.valor = (valor == null) ? BigDecimal.ZERO : valor;
        this.dataOperacao = (dataOperacao == null) ? LocalDateTime.now() : dataOperacao;
        this.descricao = (descricao == null) ? "" : descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        if (valor != null && valor.compareTo(BigDecimal.ZERO) >= 0) {
            this.valor = valor;
        }
    }

    public LocalDateTime getDataOperacao() {
        return dataOperacao;
    }

    public void setDataOperacao(LocalDateTime dataOperacao) {
        if (dataOperacao != null) {
            this.dataOperacao = dataOperacao;
        }
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        if (descricao != null) {
            this.descricao = descricao;
        }
    }
}