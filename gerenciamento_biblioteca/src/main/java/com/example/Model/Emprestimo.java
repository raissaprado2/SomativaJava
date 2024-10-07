// Emprestimo.java
package com.example.Model;

import lombok.Data;

import java.time.LocalDate;

@SuppressWarnings("unused")
@Data
public class Emprestimo {
    private int id;
    private String dataEmprestimo;
    private String dataDevolucao;
    private String livro;
    private String usuario;

    // Construtor, getters e setters
    public Emprestimo(String dataEmprestimo, String dataDevolucao, String livro, String usuario) {
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.livro = livro;
        this.usuario = usuario;
    }

    // Getters e setters...
}
