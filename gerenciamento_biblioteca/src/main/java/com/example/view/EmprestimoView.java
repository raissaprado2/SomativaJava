package com.example.view;

import com.example.Controller.EmprestimoController;
import com.example.Model.Emprestimo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EmprestimoView {
    private EmprestimoController controller;

    private JSpinner spinnerDataEmprestimo;
    private JSpinner spinnerDataDevolucao;
    private JTextField txtLivro;
    private JTextField txtUsuario;
    private DefaultListModel<String> listModel;
    private JList<String> listView;

    public EmprestimoView(EmprestimoController controller) {
        this.controller = controller;
        initializeUI();
    }

    private void initializeUI() {
        JFrame frame = new JFrame("Sistema de Empréstimos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400);
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Rótulos e Campos de texto
        JLabel lblDataEmprestimo = new JLabel("Data do Empréstimo:");
        gbc.gridx = 0; gbc.gridy = 0; frame.add(lblDataEmprestimo, gbc);

        spinnerDataEmprestimo = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor editorEmprestimo = new JSpinner.DateEditor(spinnerDataEmprestimo, "yyyy-MM-dd");
        spinnerDataEmprestimo.setEditor(editorEmprestimo);
        spinnerDataEmprestimo.setValue(new Date());
        gbc.gridx = 1; gbc.gridy = 0; frame.add(spinnerDataEmprestimo, gbc);

        JLabel lblDataDevolucao = new JLabel("Data de Devolução:");
        gbc.gridx = 0; gbc.gridy = 1; frame.add(lblDataDevolucao, gbc);

        spinnerDataDevolucao = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor editorDevolucao = new JSpinner.DateEditor(spinnerDataDevolucao, "yyyy-MM-dd");
        spinnerDataDevolucao.setEditor(editorDevolucao);
        spinnerDataDevolucao.setValue(new Date());
        gbc.gridx = 1; gbc.gridy = 1; frame.add(spinnerDataDevolucao, gbc);

        JLabel lblLivro = new JLabel("Título do Livro:");
        gbc.gridx = 0; gbc.gridy = 2; frame.add(lblLivro, gbc);

        txtLivro = new JTextField(10);
        gbc.gridx = 1; gbc.gridy = 2; frame.add(txtLivro, gbc);

        JLabel lblUsuario = new JLabel("Nome do Usuário:");
        gbc.gridx = 0; gbc.gridy = 3; frame.add(lblUsuario, gbc);

        txtUsuario = new JTextField(10);
        gbc.gridx = 1; gbc.gridy = 3; frame.add(txtUsuario, gbc);

        // Botões
        JButton btnRegistrar = new JButton("Registrar");
        gbc.gridx = 0; gbc.gridy = 4; frame.add(btnRegistrar, gbc);

        JButton btnListar = new JButton("Listar");
        gbc.gridx = 1; gbc.gridy = 4; frame.add(btnListar, gbc);

        // Lista para mostrar os empréstimos
        listModel = new DefaultListModel<>();
        listView = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(listView);
        scrollPane.setPreferredSize(new Dimension(250, 100));
        gbc.gridwidth = 2;
        gbc.gridx = 0; gbc.gridy = 5; frame.add(scrollPane, gbc);

        // Ações dos botões
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarEmprestimo();
            }
        });

        btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarEmprestimos();
            }
        });

        frame.setVisible(true);
    }

    private void registrarEmprestimo() {
        SimpleDateFormat formatoData = new SimpleDateFormat("yyyy-MM-dd");

        String dataEmprestimo = formatoData.format((Date) spinnerDataEmprestimo.getValue());
        String dataDevolucao = formatoData.format((Date) spinnerDataDevolucao.getValue());
        String livro = txtLivro.getText();
        String usuario = txtUsuario.getText();

        controller.registrarEmprestimo(dataEmprestimo, dataDevolucao, livro, usuario);

        // Limpar os campos
        spinnerDataEmprestimo.setValue(new Date());
        spinnerDataDevolucao.setValue(new Date());
        txtLivro.setText("");
        txtUsuario.setText("");

        // Mostrar mensagem de sucesso
        JOptionPane.showMessageDialog(null, "Empréstimo registrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    private void listarEmprestimos() {
        @SuppressWarnings("unused")
        SimpleDateFormat formatoData = new SimpleDateFormat("yyyy-MM-dd");
        List<Emprestimo> emprestimos = controller.listarEmprestimos();
        listModel.clear(); // Limpa a lista antes de adicionar novos itens
        for (Emprestimo emprestimo : emprestimos) {
            String item = String.format("Livro: %s, Usuário: %s, Data de Empréstimo: %s, Data de Devolução: %s",
                    emprestimo.getLivro(), emprestimo.getUsuario(), emprestimo.getDataEmprestimo(), emprestimo.getDataDevolucao());
            listModel.addElement(item);
        }
    }

    public static void main(String[] args) {
        EmprestimoController controller = new EmprestimoController();
        new EmprestimoView(controller);
    }
}
