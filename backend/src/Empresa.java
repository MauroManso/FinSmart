import java.time.LocalDate;
import java.time.LocalDateTime;

public class Empresa {

    private int idEmpresa;
    private String nomeEmpresa;
    private String cnpj;
    private String razaoSocial;
    private String setor;
    private LocalDate dataFundacao;
    private int idUsuario; // FK - proprietário da empresa
    private String status; // "ativa", "inativa"
    private LocalDateTime dataCriacao;

    // Construtor padrão
    public Empresa() {
    }

    // Construtor com parâmetros
    public Empresa(int idEmpresa, String nomeEmpresa, String cnpj,
                   String razaoSocial, String setor, int idUsuario) {
        this.idEmpresa = idEmpresa;
        this.nomeEmpresa = nomeEmpresa;
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.setor = setor;
        this.idUsuario = idUsuario;
        this.status = "ativa";
        this.dataCriacao = LocalDateTime.now();
    }

    // Cadastra uma nova empresa na plataforma
    public void cadastrar() {
        System.out.println("Executando cadastro da empresa: " + nomeEmpresa);
    }

    // Atualiza os dados da empresa
    public void atualizar() {
        System.out.println("Atualizando dados da empresa: " + nomeEmpresa);
    }

    // Inativa a empresa na plataforma
    public void inativar() {
        System.out.println("Inativando empresa: " + nomeEmpresa);
    }

    // Busca empresa pelo CNPJ
    public static Empresa buscarPorCnpj(String cnpj) {
        System.out.println("Buscando empresa pelo CNPJ: " + cnpj);
        return null;
    }

    // Lista todas as contas vinculadas à empresa
    public void listarContas() {
        System.out.println("Listando contas da empresa: " + nomeEmpresa);
    }

    // Getters e Setters
    public int getIdEmpresa() { return idEmpresa; }
    public void setIdEmpresa(int idEmpresa) { this.idEmpresa = idEmpresa; }

    public String getNomeEmpresa() { return nomeEmpresa; }
    public void setNomeEmpresa(String nomeEmpresa) { this.nomeEmpresa = nomeEmpresa; }

    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }

    public String getRazaoSocial() { return razaoSocial; }
    public void setRazaoSocial(String razaoSocial) { this.razaoSocial = razaoSocial; }

    public String getSetor() { return setor; }
    public void setSetor(String setor) { this.setor = setor; }

    public LocalDate getDataFundacao() { return dataFundacao; }
    public void setDataFundacao(LocalDate dataFundacao) { this.dataFundacao = dataFundacao; }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }
}
