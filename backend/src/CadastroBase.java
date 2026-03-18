import java.time.LocalDateTime;

public abstract class CadastroBase {

    private String status;
    private LocalDateTime dataCriacao;

    protected CadastroBase() {
        this("ativo", LocalDateTime.now());
    }

    protected CadastroBase(String status, LocalDateTime dataCriacao) {
        this.status = (status == null || status.isBlank()) ? "ativo" : status;
        this.dataCriacao = (dataCriacao == null) ? LocalDateTime.now() : dataCriacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (status != null && !status.isBlank()) {
            this.status = status;
        }
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        if (dataCriacao != null) {
            this.dataCriacao = dataCriacao;
        }
    }
}