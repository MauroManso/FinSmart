package br.com.finsmart.model;

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

    // Construtor padrão
    public AnaliseCredito() {
    }

    // Construtor com parâmetros
    public AnaliseCredito(int idAnalise, int idSolicitacao, int idUsuarioAnalista) {
        this.idAnalise = idAnalise;
        this.idSolicitacao = idSolicitacao;
        this.idUsuarioAnalista = idUsuarioAnalista;
        this.dataAnalise = LocalDateTime.now();
    }

    // Executa a análise de crédito usando IA
    public void executarAnalise() {
        System.out.println("Executando análise de crédito para a solicitação ID: " + idSolicitacao);
    }

    // Calcula o score de risco com base nos dados alternativos
    public BigDecimal calcularRiscoScore() {
        System.out.println("Calculando risco score para a solicitação ID: " + idSolicitacao);
        return this.riscoScore;
    }

    // Registra o resultado da análise feita pela IA
    public void registrarResultadoIa(String resultado) {
        System.out.println("Registrando resultado da IA para análise ID: " + idAnalise);
    }

    // Consulta o resultado da análise
    public String consultarResultado() {
        System.out.println("Consultando resultado da análise ID: " + idAnalise);
        return this.resultadoIa;
    }

    // Getters e Setters
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
