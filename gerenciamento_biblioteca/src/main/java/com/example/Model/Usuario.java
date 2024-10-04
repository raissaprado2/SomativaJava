package com.example.Model;

import lombok.Data;

public class Usuario {
    private Long id;
    private String nome;
    private String email;

    public String getNome() {
        return nome;
    }

    public Usuario(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public void setId(Long long1) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setId'");
    }

    public Object getId() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getId'");
    }
}
