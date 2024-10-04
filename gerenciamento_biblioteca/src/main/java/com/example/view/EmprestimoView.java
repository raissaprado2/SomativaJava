package com.example.view;

import com.example.Model.Emprestimo;
import com.example.Model.Usuario;

import java.time.LocalDate;

public class EmprestimoView {

    private Long id;
    private String usuarioNome; // Supondo que Usuario tenha um método getNome()
    private String livroTitulo; // Supondo que Livro tenha um método getTitulo()
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    public EmprestimoView(Emprestimo emprestimo) {
        this.id = emprestimo.getId();
        this.usuarioNome = emprestimo.getUsuario() != null ? ((Usuario) emprestimo.getUsuario()).getNome() : "Usuário não informado";
        this.livroTitulo = emprestimo.getLivro() != null ? emprestimo.getLivro().getTitulo() : "Livro não informado";
        this.dataEmprestimo = emprestimo.getDataEmprestimo();
        this.dataDevolucao = emprestimo.getDataDevolucao();
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public String getUsuarioNome() {
        return usuarioNome;
    }

    public String getLivroTitulo() {
        return livroTitulo;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }
}
