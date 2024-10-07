// EmprestimoController.java
package com.example.Controller;

import com.example.Model.Emprestimo;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class EmprestimoController {
    private List<Emprestimo> emprestimos;
    private LivroController livroController; // Supondo que Biblioteca contém métodos para interação com o banco de dados

    public EmprestimoController() {
        this.emprestimos = new ArrayList<>();
        this.livroController = new LivroController();
    }

    public void registrarEmprestimo(String dataEmprestimo, String dataDevolucao, String livro, String usuario) {
        Emprestimo novoEmprestimo = new Emprestimo(dataEmprestimo, dataDevolucao, livro, usuario);
        livroController.registrarEmprestimo(novoEmprestimo);
        emprestimos.add(novoEmprestimo);
        System.out.println("Empréstimo registrado e adicionado à lista.");
    }

    public List<Emprestimo> listarEmprestimos() {
        return emprestimos; // Pode ser aprimorado para buscar do banco de dados
    }

    // Outros métodos como buscarEmprestimo por usuário ou livro podem ser adicionados aqui
}

