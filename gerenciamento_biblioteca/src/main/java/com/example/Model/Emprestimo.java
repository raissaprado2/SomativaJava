// Emprestimo.java
package com.example.Model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Emprestimo {
    private Long id;
    private Usuario usuario;
    private Livro livro;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    public Emprestimo(Usuario usuario, Livro livro) {
        this.usuario = usuario;
        this.livro = livro;
        this.dataEmprestimo = LocalDate.now();
    }
}
