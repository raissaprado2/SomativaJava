package com.example.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.example.Controller.UsuarioController;
import com.example.Model.Usuario;

public class LoginView {
    private JFrame frame;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLoginUser;
    private JButton btnLoginAdmin;
    private JButton btnCadastro;
    private UsuarioController usuarioController;

    public LoginView(UsuarioController usuarioController) {
        this.usuarioController = usuarioController;
        frame = new JFrame("Página de Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 250);
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Rótulos e Campos de texto
        JLabel lblUsername = new JLabel("E-mail:");
        gbc.gridx = 0; gbc.gridy = 0; frame.add(lblUsername, gbc);

        txtUsername = new JTextField(15);
        gbc.gridx = 1; gbc.gridy = 0; frame.add(txtUsername, gbc);

        JLabel lblPassword = new JLabel("Senha:");
        gbc.gridx = 0; gbc.gridy = 1; frame.add(lblPassword, gbc);

        txtPassword = new JPasswordField(15);
        gbc.gridx = 1; gbc.gridy = 1; frame.add(txtPassword, gbc);

        // Botões
        btnLoginUser = new JButton("Login como Usuário");
        gbc.gridx = 0; gbc.gridy = 2; frame.add(btnLoginUser, gbc);

        btnLoginAdmin = new JButton("Login como Administrador");
        gbc.gridx = 1; gbc.gridy = 2; frame.add(btnLoginAdmin, gbc);

        btnCadastro = new JButton("Cadastrar Novo Usuário");
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2; frame.add(btnCadastro, gbc);

        // Ações dos botões
        btnLoginUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = txtUsername.getText();
                String password = new String(txtPassword.getPassword());
                
                // Implementar lógica de autenticação de usuário
                Usuario usuario = usuarioController.autenticarUsuario(email, password);
                if (usuario != null) {
                    JOptionPane.showMessageDialog(frame, "Login realizado com sucesso!");
                    new MainApp(usuario); // Redireciona para MainApp
                    frame.dispose(); // Fecha a janela de login
                } else {
                    JOptionPane.showMessageDialog(frame, "Credenciais inválidas!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnLoginAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = txtUsername.getText();
                String password = new String(txtPassword.getPassword());
                // Aqui você pode validar se é realmente um administrador
                Usuario usuario = usuarioController.autenticarUsuario(email, password);
                if (usuario != null && usuario.isEhAdmin()) {
                    new LivroView(usuario); // Abre a página de gerenciamento de livros
                    frame.dispose(); // Fecha a janela de login
                } else {
                    JOptionPane.showMessageDialog(frame, "Credenciais de Administrador inválidas!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnCadastro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openCadastroView(); // Abre a tela de cadastro
            }
        });

        frame.setVisible(true);
    }

    private void openCadastroView() {
        JFrame cadastroFrame = new JFrame("Cadastro de Usuário");
        cadastroFrame.setSize(300, 300);
        cadastroFrame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Campos de cadastro
        JLabel lblNome = new JLabel("Nome:");
        gbc.gridx = 0; gbc.gridy = 0; cadastroFrame.add(lblNome, gbc);
        JTextField txtNome = new JTextField(15);
        gbc.gridx = 1; gbc.gridy = 0; cadastroFrame.add(txtNome, gbc);

        JLabel lblEmail = new JLabel("E-mail:");
        gbc.gridx = 0; gbc.gridy = 1; cadastroFrame.add(lblEmail, gbc);
        JTextField txtEmail = new JTextField(15);
        gbc.gridx = 1; gbc.gridy = 1; cadastroFrame.add(txtEmail, gbc);

        JLabel lblSenha = new JLabel("Senha:");
        gbc.gridx = 0; gbc.gridy = 2; cadastroFrame.add(lblSenha, gbc);
        JPasswordField txtSenha = new JPasswordField(15);
        gbc.gridx = 1; gbc.gridy = 2; cadastroFrame.add(txtSenha, gbc);

        JButton btnCadastrar = new JButton("Cadastrar");
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2; cadastroFrame.add(btnCadastrar, gbc);

        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = txtNome.getText();
                String email = txtEmail.getText();
                String senha = new String(txtSenha.getPassword());
                
                // Criação do usuário
                Usuario usuario = new Usuario(nome, email, senha, false); // ehAdmin padrão como false
                if (usuarioController.criarUsuario(usuario) != null) {
                    JOptionPane.showMessageDialog(cadastroFrame, "Cadastro realizado com sucesso!");
                    cadastroFrame.dispose(); // Fecha a tela de cadastro
                } else {
                    JOptionPane.showMessageDialog(cadastroFrame, "E-mail já em uso.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }  
        });

        cadastroFrame.setVisible(true);
    }

    public static void main(String[] args) {
        UsuarioController controller = new UsuarioController(); // Inicialize seu controller
        SwingUtilities.invokeLater(() -> new LoginView(controller));
    }
}
