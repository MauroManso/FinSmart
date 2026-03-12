package br.com.finsmart.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Conta {

    private int idConta;
    private String numeroConta;
    private int idEmpresa; // FK - empresa proprietária da conta
    private String tipoConta; // "corrente", "poupanca"
    private BigDecimal saldo;
    private LocalDateTime dataAbertura;
    private String status; // "ativa", "bloqueada", "encerrada"

    // Construtor padrão
    public Conta() {
    }

    // Construtor com parâmetros
    public Conta(int idConta, String numeroConta, int idEmpresa, String tipoConta) {
        this.idConta = idConta;
        this.numeroConta = numeroConta;
        this.idEmpresa = idEmpresa;
        this.tipoConta = tipoConta;
        this.saldo = BigDecimal.ZERO;
        this.dataAbertura = LocalDateTime.now();
        this.status = "ativa";
    }

    // Abre uma nova conta digital para a empresa
    public void abrirConta() {
        System.out.println("Abrindo conta para a empresa ID: " + idEmpresa);
    }

    // Bloqueia a conta impedindo movimentações
    public void bloquearConta() {
        System.out.println("Bloqueando conta número: " + numeroConta);
    }

    // Encerra a conta definitivamente
    public void encerrarConta() {
        System.out.println("Encerrando conta número: " + numeroConta);
    }

    // Realiza um depósito na conta
    public void depositar(BigDecimal valor) {
        System.out.println("Depositando R$ " + valor + " na conta: " + numeroConta);
    }

    // Realiza um saque na conta
    public void sacar(BigDecimal valor) {
        System.out.println("Realizando saque de R$ " + valor + " da conta: " + numeroConta);
    }

    // Consulta o saldo atual da conta
    public BigDecimal consultarSaldo() {
        System.out.println("Consultando saldo da conta: " + numeroConta);
        return this.saldo;
    }

    // Getters e Setters
    public int getIdConta() { return idConta; }
    public void setIdConta(int idConta) { this.idConta = idConta; }

    public String getNumeroConta() { return numeroConta; }
    public void setNumeroConta(String numeroConta) { this.numeroConta = numeroConta; }

    public int getIdEmpresa() { return idEmpresa; }
    public void setIdEmpresa(int idEmpresa) { this.idEmpresa = idEmpresa; }

    public String getTipoConta() { return tipoConta; }
    public void setTipoConta(String tipoConta) { this.tipoConta = tipoConta; }

    public BigDecimal getSaldo() { return saldo; }
    public void setSaldo(BigDecimal saldo) { this.saldo = saldo; }

    public LocalDateTime getDataAbertura() { return dataAbertura; }
    public void setDataAbertura(LocalDateTime dataAbertura) { this.dataAbertura = dataAbertura; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
