package com.example.view;

import com.example.Controller.LivroController;
import com.example.Model.Livro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LivroView extends JFrame {
    private JTextField tituloField;
    private JTextField autorField;
    private JTextField generoField;
    private JCheckBox disponivelCheckBox;
    private LivroController livroController;

    public LivroView() {
        livroController = new LivroController();
        
        setTitle("Cadastro de Livro");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        add(new JLabel("Título:"));
        tituloField = new JTextField();
        add(tituloField);

        add(new JLabel("Autor:"));
        autorField = new JTextField();
        add(autorField);

        add(new JLabel("Gênero:"));
        generoField = new JTextField();
        add(generoField);

        add(new JLabel("Disponível:"));
        disponivelCheckBox = new JCheckBox();
        add(disponivelCheckBox);

        JButton cadastrarButton = new JButton("Cadastrar");
        add(cadastrarButton);

        JButton listarButton = new JButton("Listar Livros");
        add(listarButton);

        // Ação do botão Cadastrar
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titulo = tituloField.getText();
                String autor = autorField.getText();
                String genero = generoField.getText();
                boolean disponivel = disponivelCheckBox.isSelected();

                Livro livro = new Livro(titulo, autor, genero, disponivel);
                livroController.criarLivro(livro);

                JOptionPane.showMessageDialog(LivroView.this, "Livro cadastrado com sucesso!");
                limparCampos();
            }
        });

        // Ação do botão Listar Livros
        listarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Livro> livros = livroController.listarLivros();
                StringBuilder sb = new StringBuilder("Livros cadastrados:\n");
                for (Livro livro : livros) {
                    sb.append(livro.toString()).append("\n");
                }
                JOptionPane.showMessageDialog(LivroView.this, sb.toString());
            }
        });
    }

    public LivroView(Object object) {
        //TODO Auto-generated constructor stub
    }

    private void limparCampos() {
        tituloField.setText("");
        autorField.setText("");
        generoField.setText("");
        disponivelCheckBox.setSelected(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LivroView view = new LivroView();
            view.setVisible(true);
        });
    }
}
