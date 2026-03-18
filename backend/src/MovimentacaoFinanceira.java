import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class MovimentacaoFinanceira extends MovimentoFinanceiroBase {

    private int idMovimentacao;
    private int idConta; // FK - conta em que ocorreu a movimentação
    private String tipoMovimentacao; // "entrada", "saida", "juros", "tarifa"

    public MovimentacaoFinanceira() {
        super();
    }

    public MovimentacaoFinanceira(int idMovimentacao, int idConta,
                                   String tipoMovimentacao, BigDecimal valor, String descricao) {
        super(valor, LocalDateTime.now(), descricao);
        this.idMovimentacao = idMovimentacao;
        this.idConta = idConta;
        this.tipoMovimentacao = tipoMovimentacao;
    }

    public void registrarMovimentacao() {
        System.out.println("Registrando movimentação do tipo '" + tipoMovimentacao
                + "' no valor de R$ " + getValor() + " na conta ID: " + idConta);
    }

    public static List<MovimentacaoFinanceira> consultarExtrato(int idConta) {
        System.out.println("Consultando extrato de movimentações da conta ID: " + idConta);
        return null;
    }

    public static List<MovimentacaoFinanceira> filtrarMovimentacoes(int idConta,
            LocalDateTime dataInicio, LocalDateTime dataFim, String tipo) {
        System.out.println("Filtrando movimentações da conta ID: " + idConta
                + " entre " + dataInicio + " e " + dataFim);
        return null;
    }

    public int getIdMovimentacao() { return idMovimentacao; }
    public void setIdMovimentacao(int idMovimentacao) { this.idMovimentacao = idMovimentacao; }

    public int getIdConta() { return idConta; }
    public void setIdConta(int idConta) { this.idConta = idConta; }

    public String getTipoMovimentacao() { return tipoMovimentacao; }
    public void setTipoMovimentacao(String tipoMovimentacao) { this.tipoMovimentacao = tipoMovimentacao; }

    public LocalDateTime getDataMovimentacao() { return getDataOperacao(); }
    public void setDataMovimentacao(LocalDateTime dataMovimentacao) { setDataOperacao(dataMovimentacao); }

}
