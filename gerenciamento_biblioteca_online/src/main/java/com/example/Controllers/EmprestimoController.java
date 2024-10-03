package com.biblioteca.controller;

import com.biblioteca.model.Emprestimo;
import com.biblioteca.model.Livro;
import com.biblioteca.model.Usuario;
import com.biblioteca.repository.EmprestimoRepository;
import com.biblioteca.repository.LivroRepository;
import com.biblioteca.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/emprestimos")
public class EmprestimoController {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public String listarEmprestimos(Model model) {
        List<Emprestimo> emprestimos = emprestimoRepository.findAll();
        model.addAttribute("emprestimos", emprestimos);
        return "emprestimos/lista";
    }

    @GetMapping("/novo")
    public String novoEmprestimoForm(Model model) {
        model.addAttribute("emprestimo", new Emprestimo());
        model.addAttribute("livros", livroRepository.findAll());
        model.addAttribute("usuarios", usuarioRepository.findAll());
        return "emprestimos/form";
    }

    @PostMapping
    public String salvarEmprestimo(@ModelAttribute Emprestimo emprestimo) {
        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimoRepository.save(emprestimo);
        return "redirect:/emprestimos";
    }
}
