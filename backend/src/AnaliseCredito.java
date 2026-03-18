import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AnaliseCredito {

    private int idAnalise;
    private int idSolicitacao; // FK - solicitação de crédito analisada (1:1)
    private String resultadoIa;
    private String dadosAlternativos; // dados complementares em formato JSON
    private BigDecimal riscoScore; // pontuação de risco de 0 a 100
    private LocalDateTime dataAnalise;
    private int idUsuarioAnalista; // FK - analista responsável

    public AnaliseCredito() {
        this.dataAnalise = LocalDateTime.now();
        this.riscoScore = BigDecimal.ZERO;
    }

    public AnaliseCredito(int idAnalise, int idSolicitacao, int idUsuarioAnalista) {
        this.idAnalise = idAnalise;
        this.idSolicitacao = idSolicitacao;
        this.idUsuarioAnalista = idUsuarioAnalista;
        this.dataAnalise = LocalDateTime.now();
    }

    public void executarAnalise() {
        this.resultadoIa = "analise_em_andamento";
        this.dataAnalise = LocalDateTime.now();
        System.out.println("Executando análise de crédito para a solicitação ID: " + idSolicitacao);
    }

    public BigDecimal calcularRiscoScore() {
        if (this.riscoScore == null) {
            this.riscoScore = BigDecimal.ZERO;
        }
        System.out.println("Calculando risco score para a solicitação ID: " + idSolicitacao);
        return this.riscoScore;
    }

    public void registrarResultadoIa(String resultado) {
        this.resultadoIa = resultado;
        System.out.println("Registrando resultado da IA para análise ID: " + idAnalise);
    }

    public String consultarResultado() {
        System.out.println("Consultando resultado da análise ID: " + idAnalise);
        return this.resultadoIa;
    }

    public int getIdAnalise() { return idAnalise; }
    public void setIdAnalise(int idAnalise) { this.idAnalise = idAnalise; }

    public int getIdSolicitacao() { return idSolicitacao; }
    public void setIdSolicitacao(int idSolicitacao) { this.idSolicitacao = idSolicitacao; }

    public String getResultadoIa() { return resultadoIa; }
    public void setResultadoIa(String resultadoIa) { this.resultadoIa = resultadoIa; }

    public String getDadosAlternativos() { return dadosAlternativos; }
    public void setDadosAlternativos(String dadosAlternativos) { this.dadosAlternativos = dadosAlternativos; }

    public BigDecimal getRiscoScore() { return riscoScore; }
    public void setRiscoScore(BigDecimal riscoScore) { this.riscoScore = riscoScore; }

    public LocalDateTime getDataAnalise() { return dataAnalise; }
    public void setDataAnalise(LocalDateTime dataAnalise) { this.dataAnalise = dataAnalise; }

    public int getIdUsuarioAnalista() { return idUsuarioAnalista; }
    public void setIdUsuarioAnalista(int idUsuarioAnalista) { this.idUsuarioAnalista = idUsuarioAnalista; }
}
