package com.example.DAO;

import com.example.Model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CadastroDAO {
    private Connection connection;

    public CadastroDAO() {
        this.connection = DatabaseConnection.getConnection(); // Método para obter a conexão
    }

    public void save(Usuario usuario) {
        String sql = "INSERT INTO cadastro (usuario, email, senha) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, usuario.getNome()); // Usando nome como "usuario"
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());

            stmt.executeUpdate();

            // Obtendo o ID gerado
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                usuario.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Usuario> findAll() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM cadastro";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getString("usuario"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        false // Você pode ajustar conforme necessário
                );
                usuario.setId(rs.getLong("id"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    public Usuario findById(Long id) {
        String sql = "SELECT * FROM cadastro WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Usuario(
                        rs.getString("usuario"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        false // Ajuste conforme necessário
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Usuario usuario) {
        String sql = "UPDATE cadastro SET usuario = ?, email = ?, senha = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setLong(4, usuario.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM cadastro WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Usuario authenticate(String email, String senha) {
        String sql = "SELECT * FROM cadastro WHERE email = ? AND senha = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Usuario(
                        rs.getString("usuario"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        false // Ajuste conforme necessário
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
