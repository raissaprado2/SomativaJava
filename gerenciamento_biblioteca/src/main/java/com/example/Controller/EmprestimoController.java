// EmprestimoController.java
package com.example.Controller;

import com.example.Model.Emprestimo;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmprestimoController {

    private List<Emprestimo> emprestimos = new ArrayList<>();
    private Long idCounter = 1L;

    // Criar um novo empréstimo
    public Emprestimo criarEmprestimo(Emprestimo emprestimo) {
        emprestimo.setId(idCounter++);
        emprestimos.add(emprestimo);
        return emprestimo;
    }

    // Listar todos os empréstimos
    public List<Emprestimo> listarEmprestimos() {
        return emprestimos;
    }

    // Obter um empréstimo por ID
    public Emprestimo obterEmprestimo(Long id) {
        return emprestimos.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Atualizar um empréstimo existente
    public Emprestimo atualizarEmprestimo(Long id, Emprestimo emprestimo) {
        for (int i = 0; i < emprestimos.size(); i++) {
            if (emprestimos.get(i).getId().equals(id)) {
                emprestimo.setId(id);
                emprestimos.set(i, emprestimo);
                return emprestimo;
            }
        }
        return null;
    }

    // Deletar um empréstimo
    public boolean deletarEmprestimo(Long id) {
        return emprestimos.removeIf(e -> e.getId().equals(id));
    }
}
