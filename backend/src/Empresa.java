import java.time.LocalDate;
import java.time.LocalDateTime;

public class Empresa extends CadastroBase {

    private int idEmpresa;
    private String nomeEmpresa;
    private String cnpj;
    private String razaoSocial;
    private String setor;
    private LocalDate dataFundacao;
    private int idUsuario; // FK - proprietário da empresa

    public Empresa() {
        super("ativa", LocalDateTime.now());
    }

    public Empresa(int idEmpresa, String nomeEmpresa, String cnpj,
                   String razaoSocial, String setor, int idUsuario) {
        super("ativa", LocalDateTime.now());
        this.idEmpresa = idEmpresa;
        this.nomeEmpresa = nomeEmpresa;
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.setor = setor;
        this.idUsuario = idUsuario;
    }

    public void cadastrar() {
        System.out.println("Executando cadastro da empresa: " + nomeEmpresa);
    }

    public void atualizar() {
        System.out.println("Atualizando dados da empresa: " + nomeEmpresa);
    }

    public void inativar() {
        setStatus("inativa");
        System.out.println("Inativando empresa: " + nomeEmpresa);
    }

    public static Empresa buscarPorCnpj(String cnpj) {
        System.out.println("Buscando empresa pelo CNPJ: " + cnpj);
        return null;
    }

    public void listarContas() {
        System.out.println("Listando contas da empresa: " + nomeEmpresa);
    }

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
}
