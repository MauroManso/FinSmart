package br.com.finsmart.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class SolicitacaoCredito {

    private int idSolicitacao;
    private int idEmpresa; // FK - empresa solicitante
    private BigDecimal valorSolicitado;
    private BigDecimal taxaJuros;
    private int prazoMeses;
    private String statusAprovacao; // "pendente", "aprovada", "rejeitada", "cancelada"
    private LocalDateTime dataSolicitacao;
    private LocalDateTime dataAnalise;
    private int scoreCredito;

    // Construtor padrão
    public SolicitacaoCredito() {
    }

    // Construtor com parâmetros
    public SolicitacaoCredito(int idSolicitacao, int idEmpresa,
                               BigDecimal valorSolicitado, int prazoMeses) {
        this.idSolicitacao = idSolicitacao;
        this.idEmpresa = idEmpresa;
        this.valorSolicitado = valorSolicitado;
        this.prazoMeses = prazoMeses;
        this.statusAprovacao = "pendente";
        this.dataSolicitacao = LocalDateTime.now();
    }

    // Registra a solicitação de crédito da empresa
    public void registrarSolicitacao() {
        System.out.println("Registrando solicitação de crédito de R$ " + valorSolicitado
                + " para a empresa ID: " + idEmpresa);
    }

    // Aprova a solicitação de crédito
    public void aprovar() {
        System.out.println("Aprovando solicitação de crédito ID: " + idSolicitacao);
    }

    // Rejeita a solicitação de crédito
    public void rejeitar() {
        System.out.println("Rejeitando solicitação de crédito ID: " + idSolicitacao);
    }

    // Cancela a solicitação de crédito
    public void cancelar() {
        System.out.println("Cancelando solicitação de crédito ID: " + idSolicitacao);
    }

    // Calcula o score de crédito da empresa com base nos dados disponíveis
    public int calcularScore() {
        System.out.println("Calculando score de crédito para empresa ID: " + idEmpresa);
        return this.scoreCredito;
    }

    // Getters e Setters
    public int getIdSolicitacao() { return idSolicitacao; }
    public void setIdSolicitacao(int idSolicitacao) { this.idSolicitacao = idSolicitacao; }

    public int getIdEmpresa() { return idEmpresa; }
    public void setIdEmpresa(int idEmpresa) { this.idEmpresa = idEmpresa; }

    public BigDecimal getValorSolicitado() { return valorSolicitado; }
    public void setValorSolicitado(BigDecimal valorSolicitado) { this.valorSolicitado = valorSolicitado; }

    public BigDecimal getTaxaJuros() { return taxaJuros; }
    public void setTaxaJuros(BigDecimal taxaJuros) { this.taxaJuros = taxaJuros; }

    public int getPrazoMeses() { return prazoMeses; }
    public void setPrazoMeses(int prazoMeses) { this.prazoMeses = prazoMeses; }

    public String getStatusAprovacao() { return statusAprovacao; }
    public void setStatusAprovacao(String statusAprovacao) { this.statusAprovacao = statusAprovacao; }

    public LocalDateTime getDataSolicitacao() { return dataSolicitacao; }
    public void setDataSolicitacao(LocalDateTime dataSolicitacao) { this.dataSolicitacao = dataSolicitacao; }

    public LocalDateTime getDataAnalise() { return dataAnalise; }
    public void setDataAnalise(LocalDateTime dataAnalise) { this.dataAnalise = dataAnalise; }

    public int getScoreCredito() { return scoreCredito; }
    public void setScoreCredito(int scoreCredito) { this.scoreCredito = scoreCredito; }
}
