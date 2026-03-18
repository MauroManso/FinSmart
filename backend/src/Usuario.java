import java.time.LocalDateTime;

public class Usuario extends CadastroBase {

    private int idUsuario;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    private String tipoUsuario; // "proprietario", "analista", "admin"

    public Usuario() {
        super("ativo", LocalDateTime.now());
    }

    public Usuario(int idUsuario, String nome, String email, String telefone,
                   String cpf, String tipoUsuario) {
        super("ativo", LocalDateTime.now());
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.cpf = cpf;
        this.tipoUsuario = tipoUsuario;
    }

    public void cadastrar() {
        System.out.println("Executando cadastro do usuário: " + nome);
    }

    public void atualizar() {
        System.out.println("Atualizando dados do usuário: " + nome);
    }

    public void inativar() {
        setStatus("inativo");
        System.out.println("Inativando usuário: " + nome);
    }

    public Usuario buscarPorEmail(String email) {
        System.out.println("Buscando usuário pelo e-mail: " + email);
        return null;
    }

    public Usuario buscarPorCpf(String cpf) {
        System.out.println("Buscando usuário pelo CPF: " + cpf);
        return null;
    }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getTipoUsuario() { return tipoUsuario; }
    public void setTipoUsuario(String tipoUsuario) { this.tipoUsuario = tipoUsuario; }
}
