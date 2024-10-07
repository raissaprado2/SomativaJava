package com.example.Controller;

import com.example.Model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioController {
    private List<Usuario> usuarios = new ArrayList<>();
    private Long idCounter = 1L;

    // Método para criar um usuário
    public Usuario criarUsuario(Usuario usuario) {
        // Verifica se já existe um usuário com o mesmo e-mail
        for (Usuario u : usuarios) {
            if (u.getEmail().equals(usuario.getEmail())) {
                return null; // Retorna null se o e-mail já estiver em uso
            }
        }
        usuario.setId(idCounter++); // Atribuir o ID ao usuário
        usuarios.add(usuario);
        return usuario; // Retorna o usuário criado
    }

    // Método para listar todos os usuários
    public List<Usuario> listarUsuarios() {
        return usuarios;
    }

    // Método para obter um usuário por ID
    public Usuario obterUsuario(Long id) {
        return usuarios.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Método para atualizar um usuário
    public Usuario atualizarUsuario(Long id, Usuario usuario) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getId().equals(id)) {
                usuario.setId(id);
                usuarios.set(i, usuario);
                return usuario;
            }
        }
        return null;
    }

    // Método para deletar um usuário
    public boolean deletarUsuario(Long id) {
        return usuarios.removeIf(u -> u.getId().equals(id));
    }

    // Método para autenticar um usuário
    public Usuario autenticar(String email, String senha) {
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)) {
                return usuario; // Retorna o usuário autenticado
            }
        }
        return null; // Retorna null se não encontrar
    }

    public Usuario autenticarUsuario(String email, String senha) {
        return usuarios.stream()
                .filter(u -> u.getEmail().equals(email) && u.getSenha().equals(senha))
                .findFirst()
                .orElse(null);
    }
    
}
