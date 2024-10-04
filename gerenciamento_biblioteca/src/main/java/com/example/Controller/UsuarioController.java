// UsuarioController.java
package com.example.Controller;

import com.example.Model.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioController {

    private List<Usuario> usuarios = new ArrayList<>();
    private Long idCounter = 1L;

    public Usuario criarUsuario(Usuario usuario) {
        usuario.setId(idCounter++);
        usuarios.add(usuario);
        return usuario;
    }

    public List<Usuario> listarUsuarios() {
        return usuarios;
    }

    public Usuario obterUsuario(Long id) {
        return usuarios.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

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

    public boolean deletarUsuario(Long id) {
        return usuarios.removeIf(u -> u.getId().equals(id));
    }
}
