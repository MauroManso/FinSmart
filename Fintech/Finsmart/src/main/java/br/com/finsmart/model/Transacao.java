package br.com.finsmart.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transacao {

    private int idTransacao;
    private int idContaOrigem; // FK - conta que enviou o recurso
    private int idContaDestino; // FK - conta que recebeu o recurso
    private BigDecimal valor;
    private String tipo; // "transferencia", "deposito", "saque", "pix"
    private LocalDateTime dataTransacao;
    private String descricao;
    private String status; // "concluida", "pendente", "rejeitada"

    // Construtor padrão
    public Transacao() {
    }

    // Construtor com parâmetros
    public Transacao(int idTransacao, int idContaOrigem, int idContaDestino,
                     BigDecimal valor, String tipo, String descricao) {
        this.idTransacao = idTransacao;
        this.idContaOrigem = idContaOrigem;
        this.idContaDestino = idContaDestino;
        this.valor = valor;
        this.tipo = tipo;
        this.descricao = descricao;
        this.dataTransacao = LocalDateTime.now();
        this.status = "pendente";
    }

    // Realiza a transação financeira entre contas
    public void realizarTransacao() {
        System.out.println("Realizando transação do tipo " + tipo + " no valor de R$ " + valor);
    }

    // Cancela/rejeita a transação antes de ser concluída
    public void cancelarTransacao() {
        System.out.println("Cancelando transação ID: " + idTransacao);
    }

    // Confirma e conclui a transação
    public void confirmarTransacao() {
        System.out.println("Confirmando transação ID: " + idTransacao);
    }

    // Consulta o status atual da transação
    public String consultarStatus() {
        System.out.println("Consultando status da transação ID: " + idTransacao);
        return this.status;
    }

    // Getters e Setters
    public int getIdTransacao() { return idTransacao; }
    public void setIdTransacao(int idTransacao) { this.idTransacao = idTransacao; }

    public int getIdContaOrigem() { return idContaOrigem; }
    public void setIdContaOrigem(int idContaOrigem) { this.idContaOrigem = idContaOrigem; }

    public int getIdContaDestino() { return idContaDestino; }
    public void setIdContaDestino(int idContaDestino) { this.idContaDestino = idContaDestino; }

    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public LocalDateTime getDataTransacao() { return dataTransacao; }
    public void setDataTransacao(LocalDateTime dataTransacao) { this.dataTransacao = dataTransacao; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
