// LivroController.java
package com.example.Controller;

import com.example.Model.Livro;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LivroController {

    private List<Livro> livros = new ArrayList<>();
    private Long idCounter = 1L;

    public Livro criarLivro(Livro livro) {
        livro.setId(idCounter++);
        livros.add(livro);
        return livro;
    }

    public List<Livro> listarLivros() {
        return livros;
    }

    public Livro obterLivro(Long id) {
        return livros.stream()
                .filter(l -> l.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Livro atualizarLivro(Long id, Livro livro) {
        for (int i = 0; i < livros.size(); i++) {
            if (livros.get(i).getId().equals(id)) {
                livro.setId(id);
                livros.set(i, livro);
                return livro;
            }
        }
        return null;
    }

    public boolean deletarLivro(Long id) {
        return livros.removeIf(l -> l.getId().equals(id));
    }
}
