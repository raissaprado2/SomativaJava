package com.example.view;

import com.example.Controller.EmprestimoController;
import com.example.Controller.LivroController;
import com.example.Controller.UsuarioController;
import com.example.Model.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("unused")
public class MainApp {
    private EmprestimoController emprestimoController;
    private LivroController livroController;
    private UsuarioController usuarioController;

    public MainApp() {
        emprestimoController = new EmprestimoController();
        livroController = new LivroController();
        usuarioController = new UsuarioController();
        initializeUI();
    }

    public MainApp(Usuario usuario) {
        //TODO Auto-generated constructor stub
    }

    private void initializeUI() {
        JFrame frame = new JFrame("Sistema de Biblioteca");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(0, 1)); // Usar GridLayout para cards

        // Criar um card para Gerenciar Livros
        JPanel panelLivros = createCard("Gerenciar Livros", e -> new LivroView(livroController));
        frame.add(panelLivros);

        // Criar um card para Gerenciar Usuários
        JPanel panelUsuarios = createCard("Gerenciar Usuários", e -> new LoginView(usuarioController));
        frame.add(panelUsuarios);

        // Criar um card para Gerenciar Empréstimos
        JPanel panelEmprestimos = createCard("Gerenciar Empréstimos", e -> new EmprestimoView(emprestimoController));
        frame.add(panelEmprestimos);

        frame.setVisible(true);
    }

    private JPanel createCard(String title, ActionListener actionListener) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBorder(BorderFactory.createTitledBorder(title));

        JButton button = new JButton("Abrir");
        button.addActionListener(actionListener);
        card.add(button, BorderLayout.CENTER);

        return card;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainApp::new);
    }
}
