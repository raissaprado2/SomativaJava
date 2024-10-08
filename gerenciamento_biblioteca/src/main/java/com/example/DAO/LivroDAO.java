package com.example.DAO;

import com.example.Model.Livro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {
    private Connection connection;

    public LivroDAO() {
        this.connection = DatabaseConnection.getConnection(); // Método para obter a conexão
    }

    public void save(Livro livro) {
        String sql = "INSERT INTO livros (titulo, autor, genero, disponibilidade) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setString(3, livro.getGenero());
            stmt.setBoolean(4, livro.isDisponivel());

            stmt.executeUpdate();

            // Obtendo o ID gerado
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                livro.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Livro> findAll() {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM livros";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Livro livro = new Livro(
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getString("genero"),
                        rs.getBoolean("disponibilidade")
                );
                livro.setId(rs.getLong("id"));
                livros.add(livro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livros;
    }

    public Livro findById(Long id) {
        String sql = "SELECT * FROM livros WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Livro(
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getString("genero"),
                        rs.getBoolean("disponibilidade")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Livro livro) {
        String sql = "UPDATE livros SET titulo = ?, autor = ?, genero = ?, disponibilidade = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setString(3, livro.getGenero());
            stmt.setBoolean(4, livro.isDisponivel());
            stmt.setLong(5, livro.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM livros WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
