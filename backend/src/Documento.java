import java.time.LocalDateTime;

public class Documento {

    private int idDocumento;
    private int idEmpresa; // FK - empresa proprietária do documento
    private String tipoDocumento; // ex: "contrato_social", "balanco_patrimonial", "declaracao_ir"
    private String caminhoArquivo;
    private LocalDateTime dataEnvio;
    private String statusValidacao; // "pendente", "validado", "rejeitado"
    private String observacoes;

    // Construtor padrão
    public Documento() {
    }

    // Construtor com parâmetros
    public Documento(int idDocumento, int idEmpresa,
                     String tipoDocumento, String caminhoArquivo) {
        this.idDocumento = idDocumento;
        this.idEmpresa = idEmpresa;
        this.tipoDocumento = tipoDocumento;
        this.caminhoArquivo = caminhoArquivo;
        this.dataEnvio = LocalDateTime.now();
        this.statusValidacao = "pendente";
    }

    // Realiza o envio e registro do documento no sistema
    public void enviarDocumento() {
        System.out.println("Enviando documento do tipo '" + tipoDocumento
                + "' para a empresa ID: " + idEmpresa);
    }

    // Valida o documento enviado pela empresa
    public void validarDocumento() {
        System.out.println("Validando documento ID: " + idDocumento);
    }

    // Rejeita o documento e registra observação
    public void rejeitarDocumento(String motivo) {
        System.out.println("Rejeitando documento ID: " + idDocumento + " - Motivo: " + motivo);
    }

    // Consulta o status de validação do documento
    public String consultarStatus() {
        System.out.println("Consultando status do documento ID: " + idDocumento);
        return this.statusValidacao;
    }

    // Getters e Setters
    public int getIdDocumento() { return idDocumento; }
    public void setIdDocumento(int idDocumento) { this.idDocumento = idDocumento; }

    public int getIdEmpresa() { return idEmpresa; }
    public void setIdEmpresa(int idEmpresa) { this.idEmpresa = idEmpresa; }

    public String getTipoDocumento() { return tipoDocumento; }
    public void setTipoDocumento(String tipoDocumento) { this.tipoDocumento = tipoDocumento; }

    public String getCaminhoArquivo() { return caminhoArquivo; }
    public void setCaminhoArquivo(String caminhoArquivo) { this.caminhoArquivo = caminhoArquivo; }

    public LocalDateTime getDataEnvio() { return dataEnvio; }
    public void setDataEnvio(LocalDateTime dataEnvio) { this.dataEnvio = dataEnvio; }

    public String getStatusValidacao() { return statusValidacao; }
    public void setStatusValidacao(String statusValidacao) { this.statusValidacao = statusValidacao; }

    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
}
