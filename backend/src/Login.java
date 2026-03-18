public class Login {

    private String username;
    private String password;
    private boolean autenticado;

    public Login() {
        this.autenticado = false;
    }

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
        this.autenticado = false;
    }

    public void doLogin() {
        this.autenticado = validarCredenciais();
        System.out.println("Realizando login para o usuário: " + username);
    }

    public void doLogout() {
        this.autenticado = false;
        System.out.println("Realizando logout para o usuário: " + username);
    }

    public boolean validarCredenciais() {
        System.out.println("Validando credenciais do usuário: " + username);
        return username != null && !username.isBlank()
                && password != null && !password.isBlank();
    }

    public void recuperarSenha(String email) {
        System.out.println("Enviando e-mail de recuperação de senha para: " + email);
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public boolean isAutenticado() { return autenticado; }
}
