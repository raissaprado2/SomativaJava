package com.example.Controller;

import com.example.DAO.LivroDAO;
import com.example.Model.Livro;

import java.util.List;

public class LivroController {
    private LivroDAO livroDAO;

    public LivroController() {
        this.livroDAO = new LivroDAO();
    }

    public Livro criarLivro(Livro livro) {
        livroDAO.save(livro); // Salva no banco de dados
        return livro; // Retorna o livro criado com ID
    }

    public List<Livro> listarLivros() {
        return livroDAO.findAll(); // Busca do banco de dados
    }

    public Livro obterLivro(Long id) {
        return livroDAO.findById(id); // Busca do banco de dados
    }

    public Livro atualizarLivro(Long id, Livro livro) {
        livro.setId(id);
        livroDAO.update(livro); // Atualiza no banco de dados
        return livro; // Retorna o livro atualizado
    }

    public boolean deletarLivro(Long id) {
        return livroDAO.delete(id); // Remove o livro do banco de dados
    }
}
