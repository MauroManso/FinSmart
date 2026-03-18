public class Login {

    private String username;
    private String password;

    // Construtor padrão
    public Login() {
    }

    // Construtor com parâmetros
    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Realiza o login do usuário na plataforma
    public void doLogin() {
        System.out.println("Realizando login para o usuário: " + username);
    }

    // Realiza o logout do usuário da plataforma
    public void doLogout() {
        System.out.println("Realizando logout para o usuário: " + username);
    }

    // Valida as credenciais informadas pelo usuário
    public boolean validarCredenciais() {
        System.out.println("Validando credenciais do usuário: " + username);
        return false;
    }

    // Solicita redefinição de senha via e-mail
    public void recuperarSenha(String email) {
        System.out.println("Enviando e-mail de recuperação de senha para: " + email);
    }

    // Getters e Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
