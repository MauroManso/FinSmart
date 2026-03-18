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

    public Conta() {
        this.saldo = BigDecimal.ZERO;
        this.dataAbertura = LocalDateTime.now();
        this.status = "ativa";
    }

    public Conta(int idConta, String numeroConta, int idEmpresa, String tipoConta) {
        this.idConta = idConta;
        this.numeroConta = numeroConta;
        this.idEmpresa = idEmpresa;
        this.tipoConta = tipoConta;
        this.saldo = BigDecimal.ZERO;
        this.dataAbertura = LocalDateTime.now();
        this.status = "ativa";
    }

    public void abrirConta() {
        this.status = "ativa";
        System.out.println("Abrindo conta para a empresa ID: " + idEmpresa);
    }

    public void bloquearConta() {
        this.status = "bloqueada";
        System.out.println("Bloqueando conta número: " + numeroConta);
    }

    public void encerrarConta() {
        this.status = "encerrada";
        System.out.println("Encerrando conta número: " + numeroConta);
    }

    public void depositar(BigDecimal valor) {
        if (valor != null && valor.compareTo(BigDecimal.ZERO) > 0) {
            this.saldo = this.saldo.add(valor);
        }
        System.out.println("Depositando R$ " + valor + " na conta: " + numeroConta);
    }

    public void sacar(BigDecimal valor) {
        if (valor != null && valor.compareTo(BigDecimal.ZERO) > 0 && this.saldo.compareTo(valor) >= 0) {
            this.saldo = this.saldo.subtract(valor);
        }
        System.out.println("Realizando saque de R$ " + valor + " da conta: " + numeroConta);
    }

    public BigDecimal consultarSaldo() {
        System.out.println("Consultando saldo da conta: " + numeroConta);
        return this.saldo;
    }

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
    public void setStatus(String status) {
        if (status != null && !status.isBlank()) {
            this.status = status;
        }
    }
}
