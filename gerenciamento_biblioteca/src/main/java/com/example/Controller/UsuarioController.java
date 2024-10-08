package com.example.Controller;

import com.example.Model.Usuario;
import com.example.DAO.UsuarioDAO;

import java.util.List;

public class UsuarioController {
    private UsuarioDAO usuarioDAO;

    public UsuarioController() {
        this.usuarioDAO = new UsuarioDAO();
    }

    public Usuario criarUsuario(Usuario usuario) {
        // Verifica se já existe um usuário com o mesmo e-mail
        if (usuarioDAO.authenticate(usuario.getEmail(), usuario.getSenha()) != null) {
            return null; // Retorna null se o e-mail já estiver em uso
        }
        usuarioDAO.save(usuario);
        return usuario; // Retorna o usuário criado
    }

    public List<Usuario> listarUsuarios() {
        return usuarioDAO.findAll();
    }

    public Usuario obterUsuario(Long id) {
        return usuarioDAO.findById(id);
    }

    public Usuario atualizarUsuario(Long id, Usuario usuario) {
        usuario.setId(id);
        usuarioDAO.update(usuario);
        return usuario;
    }

    public boolean deletarUsuario(Long id) {
        return usuarioDAO.delete(id);
    }

    public Usuario autenticarUsuario(String email, String senha) {
        return usuarioDAO.authenticate(email, senha);
    }
}
