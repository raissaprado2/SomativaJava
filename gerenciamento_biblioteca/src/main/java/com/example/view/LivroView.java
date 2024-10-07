package com.example.view;

import com.example.Controller.LivroController;
import com.example.Model.Livro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LivroView {
    private LivroController controller;
    private JTextField txtTitulo;
    private JTextField txtAutor;
    private DefaultListModel<Livro> listModel;
    private JList<Livro> listView;

    public LivroView(LivroController controller) {
        this.controller = controller;
        initializeUI();
    }

    private void initializeUI() {
        JFrame frame = new JFrame("Gerenciamento de Livros");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JLabel lblTitulo = new JLabel("Título:");
        txtTitulo = new JTextField(15);
        JLabel lblAutor = new JLabel("Autor:");
        txtAutor = new JTextField(15);

        JButton btnAdicionar = new JButton("Adicionar Livro");
        JButton btnAtualizar = new JButton("Atualizar Livro");
        JButton btnDeletar = new JButton("Deletar Livro");

        listModel = new DefaultListModel<>();
        listView = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(listView);

        // Adiciona um listener para o evento de seleção na JList
        listView.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Livro livroSelecionado = listView.getSelectedValue();
                if (livroSelecionado != null) {
                    txtTitulo.setText(livroSelecionado.getTitulo());
                    txtAutor.setText(livroSelecionado.getAutor());
                }
            }
        });

        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titulo = txtTitulo.getText();
                String autor = txtAutor.getText();
                if (!titulo.isEmpty() && !autor.isEmpty()) {
                    Livro livro = new Livro(titulo, autor);
                    controller.criarLivro(livro);
                    listModel.addElement(livro);
                    txtTitulo.setText("");
                    txtAutor.setText("");
                } else {
                    JOptionPane.showMessageDialog(frame, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = listView.getSelectedIndex();
                if (selectedIndex != -1) {
                    Livro livroSelecionado = listModel.get(selectedIndex);
                    String titulo = txtTitulo.getText();
                    String autor = txtAutor.getText();
                    if (!titulo.isEmpty() && !autor.isEmpty()) {
                        livroSelecionado.setTitulo(titulo);
                        livroSelecionado.setAutor(autor);
                        controller.atualizarLivro(livroSelecionado.getId(), livroSelecionado);
                        listModel.set(selectedIndex, livroSelecionado); // Atualiza o modelo da lista
                        txtTitulo.setText("");
                        txtAutor.setText("");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Selecione um livro para atualizar.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnDeletar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = listView.getSelectedIndex();
                if (selectedIndex != -1) {
                    Livro livroSelecionado = listModel.get(selectedIndex);
                    controller.deletarLivro(livroSelecionado.getId());
                    listModel.remove(selectedIndex);
                    txtTitulo.setText("");
                    txtAutor.setText("");
                } else {
                    JOptionPane.showMessageDialog(frame, "Selecione um livro para deletar.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panel.add(lblTitulo);
        panel.add(txtTitulo);
        panel.add(lblAutor);
        panel.add(txtAutor);
        panel.add(btnAdicionar);
        panel.add(btnAtualizar);
        panel.add(btnDeletar);
        frame.add(panel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        LivroController controller = new LivroController();
        new LivroView(controller);
    }
}
