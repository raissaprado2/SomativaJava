package com.example.Model;

import lombok.Data;

@SuppressWarnings("unused")
@Data
public class Emprestimo {
    private int id;
    private String dataEmprestimo;
    private String dataDevolucao;
    private String livro;
    private String usuario;

    // Construtor
    public Emprestimo(String dataEmprestimo, String dataDevolucao, String livro, String usuario) {
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.livro = livro;
        this.usuario = usuario;
    }

    // Método para definir o ID (se necessário)
    public void setId(int id) {
        this.id = id;
    }
}
