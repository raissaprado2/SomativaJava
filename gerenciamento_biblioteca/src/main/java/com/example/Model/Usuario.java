package com.example.Model;

public class Usuario {
    private Long id;
    private String nome;
    private String email;
    private String senha; // Armazena a senha
    private boolean ehAdmin; // Indica se o usuário é administrador

    // Construtor
    public Usuario(String nome, String email, String senha, boolean ehAdmin) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.ehAdmin = ehAdmin;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public boolean isEhAdmin() {
        return ehAdmin;
    }

}
