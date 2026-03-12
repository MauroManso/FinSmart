package br.com.finsmart.model;

import java.time.LocalDateTime;

public class Usuario {

    private int idUsuario;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    private String tipoUsuario; // "proprietario", "analista", "admin"
    private LocalDateTime dataCriacao;
    private String status; // "ativo", "inativo"

    // Construtor padrão
    public Usuario() {
    }

    // Construtor com parâmetros
    public Usuario(int idUsuario, String nome, String email, String telefone,
                   String cpf, String tipoUsuario) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.cpf = cpf;
        this.tipoUsuario = tipoUsuario;
        this.dataCriacao = LocalDateTime.now();
        this.status = "ativo";
    }

    // Cadastra um novo usuário no sistema
    public void cadastrar() {
        System.out.println("Executando cadastro do usuário: " + nome);
    }

    // Atualiza os dados do usuário
    public void atualizar() {
        System.out.println("Atualizando dados do usuário: " + nome);
    }

    // Inativa o usuário no sistema
    public void inativar() {
        System.out.println("Inativando usuário: " + nome);
    }

    // Busca usuário pelo e-mail
    public Usuario buscarPorEmail(String email) {
        System.out.println("Buscando usuário pelo e-mail: " + email);
        return null;
    }

    // Busca usuário pelo CPF
    public Usuario buscarPorCpf(String cpf) {
        System.out.println("Buscando usuário pelo CPF: " + cpf);
        return null;
    }

    // Getters e Setters
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

    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
