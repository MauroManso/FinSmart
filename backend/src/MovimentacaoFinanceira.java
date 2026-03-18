import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class MovimentacaoFinanceira {

    private int idMovimentacao;
    private int idConta; // FK - conta em que ocorreu a movimentação
    private String tipoMovimentacao; // "entrada", "saida", "juros", "tarifa"
    private BigDecimal valor;
    private LocalDateTime dataMovimentacao;
    private String descricao;

    // Construtor padrão
    public MovimentacaoFinanceira() {
    }

    // Construtor com parâmetros
    public MovimentacaoFinanceira(int idMovimentacao, int idConta,
                                   String tipoMovimentacao, BigDecimal valor, String descricao) {
        this.idMovimentacao = idMovimentacao;
        this.idConta = idConta;
        this.tipoMovimentacao = tipoMovimentacao;
        this.valor = valor;
        this.descricao = descricao;
        this.dataMovimentacao = LocalDateTime.now();
    }

    // Registra uma nova movimentação financeira na conta
    public void registrarMovimentacao() {
        System.out.println("Registrando movimentação do tipo '" + tipoMovimentacao
                + "' no valor de R$ " + valor + " na conta ID: " + idConta);
    }

    // Lista o extrato de movimentações de uma conta
    public static List<MovimentacaoFinanceira> consultarExtrato(int idConta) {
        System.out.println("Consultando extrato de movimentações da conta ID: " + idConta);
        return null;
    }

    // Filtra movimentações por período e tipo
    public static List<MovimentacaoFinanceira> filtrarMovimentacoes(int idConta,
            LocalDateTime dataInicio, LocalDateTime dataFim, String tipo) {
        System.out.println("Filtrando movimentações da conta ID: " + idConta
                + " entre " + dataInicio + " e " + dataFim);
        return null;
    }

    // Getters e Setters
    public int getIdMovimentacao() { return idMovimentacao; }
    public void setIdMovimentacao(int idMovimentacao) { this.idMovimentacao = idMovimentacao; }

    public int getIdConta() { return idConta; }
    public void setIdConta(int idConta) { this.idConta = idConta; }

    public String getTipoMovimentacao() { return tipoMovimentacao; }
    public void setTipoMovimentacao(String tipoMovimentacao) { this.tipoMovimentacao = tipoMovimentacao; }

    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }

    public LocalDateTime getDataMovimentacao() { return dataMovimentacao; }
    public void setDataMovimentacao(LocalDateTime dataMovimentacao) { this.dataMovimentacao = dataMovimentacao; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}
