package com.example.Model;

import lombok.Data;

@Data
public class Livro {
    private Long id;
    private String titulo;
    private String autor;
    private boolean disponivel;

    public Livro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
        this.disponivel = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Object object) {
        this.id = (Long) object;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return String.format("Título: %s, Autor: %s", titulo, autor);
    }
}
