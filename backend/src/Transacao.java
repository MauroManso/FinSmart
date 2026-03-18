import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transacao extends MovimentoFinanceiroBase {

    private int idTransacao;
    private int idContaOrigem; // FK - conta que enviou o recurso
    private int idContaDestino; // FK - conta que recebeu o recurso
    private String tipo; // "transferencia", "deposito", "saque", "pix"
    private String status; // "concluida", "pendente", "rejeitada"

    public Transacao() {
        super();
        this.status = "pendente";
    }

    public Transacao(int idTransacao, int idContaOrigem, int idContaDestino,
                     BigDecimal valor, String tipo, String descricao) {
        super(valor, LocalDateTime.now(), descricao);
        this.idTransacao = idTransacao;
        this.idContaOrigem = idContaOrigem;
        this.idContaDestino = idContaDestino;
        this.tipo = tipo;
        this.status = "pendente";
    }

    public void realizarTransacao() {
        System.out.println("Realizando transação do tipo " + tipo + " no valor de R$ " + getValor());
    }

    public void cancelarTransacao() {
        this.status = "rejeitada";
        System.out.println("Cancelando transação ID: " + idTransacao);
    }

    public void confirmarTransacao() {
        this.status = "concluida";
        System.out.println("Confirmando transação ID: " + idTransacao);
    }

    public String consultarStatus() {
        System.out.println("Consultando status da transação ID: " + idTransacao);
        return this.status;
    }

    public int getIdTransacao() { return idTransacao; }
    public void setIdTransacao(int idTransacao) { this.idTransacao = idTransacao; }

    public int getIdContaOrigem() { return idContaOrigem; }
    public void setIdContaOrigem(int idContaOrigem) { this.idContaOrigem = idContaOrigem; }

    public int getIdContaDestino() { return idContaDestino; }
    public void setIdContaDestino(int idContaDestino) { this.idContaDestino = idContaDestino; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public LocalDateTime getDataTransacao() { return getDataOperacao(); }
    public void setDataTransacao(LocalDateTime dataTransacao) { setDataOperacao(dataTransacao); }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
