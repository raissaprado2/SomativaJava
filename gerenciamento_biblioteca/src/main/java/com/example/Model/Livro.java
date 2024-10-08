package com.example.Model;

import lombok.Data;

@SuppressWarnings("unused")
@Data
public class Livro {
    private Long id;
    private String titulo;
    private String autor;
    private String genero; // Novo atributo
    private boolean disponivel;

    public Livro(String titulo, String autor, String genero, boolean disponivel) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero; // Inicializa o novo atributo
        this.disponivel = disponivel;
    }
}
