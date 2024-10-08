package com.example.Controller;

import com.example.DAO.EmprestimoDAO;
import com.example.Model.Emprestimo;

import java.util.List;

public class EmprestimoController {
    private EmprestimoDAO emprestimoDAO;

    public EmprestimoController() {
        this.emprestimoDAO = new EmprestimoDAO();
    }

    public void registrarEmprestimo(String dataEmprestimo, String dataDevolucao, String livro, String usuario) {
        Emprestimo novoEmprestimo = new Emprestimo(dataEmprestimo, dataDevolucao, livro, usuario);
        emprestimoDAO.save(novoEmprestimo); // Salva no banco de dados
        System.out.println("Empréstimo registrado e salvo no banco de dados.");
    }

    public List<Emprestimo> listarEmprestimos() {
        return emprestimoDAO.findAll(); // Busca do banco de dados
    }

    public Emprestimo buscarEmprestimoPorId(int id) {
        return emprestimoDAO.findById(id); // Busca um empréstimo específico
    }

    public void atualizarEmprestimo(Emprestimo emprestimo) {
        emprestimoDAO.update(emprestimo); // Atualiza no banco de dados
    }

    public boolean deletarEmprestimo(int id) {
        return emprestimoDAO.delete(id); // Deleta um empréstimo
    }
}
