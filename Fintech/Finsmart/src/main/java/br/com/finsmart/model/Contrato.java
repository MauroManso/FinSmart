package br.com.finsmart.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Contrato {

    private int idContrato;
    private int idEmpresa; // FK - empresa contratante
    private int idProduto; // FK - produto financeiro contratado
    private BigDecimal valorContratado;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private String status; // "ativo", "encerrado", "suspenso"

    // Construtor padrão
    public Contrato() {
    }

    // Construtor com parâmetros
    public Contrato(int idContrato, int idEmpresa, int idProduto,
                    BigDecimal valorContratado, LocalDate dataInicio, LocalDate dataFim) {
        this.idContrato = idContrato;
        this.idEmpresa = idEmpresa;
        this.idProduto = idProduto;
        this.valorContratado = valorContratado;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.status = "ativo";
    }

    // Formaliza a contratação do produto financeiro pela empresa
    public void assinarContrato() {
        System.out.println("Assinando contrato do produto ID " + idProduto
                + " para a empresa ID: " + idEmpresa);
    }

    // Suspende temporariamente o contrato
    public void suspenderContrato() {
        System.out.println("Suspendendo contrato ID: " + idContrato);
    }

    // Encerra definitivamente o contrato
    public void encerrarContrato() {
        System.out.println("Encerrando contrato ID: " + idContrato);
    }

    // Renova o contrato com nova data de fim
    public void renovarContrato(LocalDate novaDataFim) {
        System.out.println("Renovando contrato ID: " + idContrato + " até: " + novaDataFim);
    }

    // Getters e Setters
    public int getIdContrato() { return idContrato; }
    public void setIdContrato(int idContrato) { this.idContrato = idContrato; }

    public int getIdEmpresa() { return idEmpresa; }
    public void setIdEmpresa(int idEmpresa) { this.idEmpresa = idEmpresa; }

    public int getIdProduto() { return idProduto; }
    public void setIdProduto(int idProduto) { this.idProduto = idProduto; }

    public BigDecimal getValorContratado() { return valorContratado; }
    public void setValorContratado(BigDecimal valorContratado) { this.valorContratado = valorContratado; }

    public LocalDate getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }

    public LocalDate getDataFim() { return dataFim; }
    public void setDataFim(LocalDate dataFim) { this.dataFim = dataFim; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
