package com.example.DAO;

import com.example.Model.Emprestimo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoDAO {
    private Connection connection;

    public EmprestimoDAO() {
        this.connection = DatabaseConnection.getConnection();
        if (this.connection == null) {
            throw new RuntimeException("Falha ao conectar ao banco de dados.");
        }
    }

    public void save(Emprestimo emprestimo) {
        String sql = "INSERT INTO emprestimo (dataemprestimo, datadevolucao, livro, usuario) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, emprestimo.getDataEmprestimo());
            stmt.setString(2, emprestimo.getDataDevolucao());
            stmt.setString(3, emprestimo.getLivro());
            stmt.setString(4, emprestimo.getUsuario());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    emprestimo.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao salvar empréstimo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Emprestimo> findAll() {
        List<Emprestimo> emprestimos = new ArrayList<>();
        String sql = "SELECT * FROM emprestimo";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Emprestimo emprestimo = new Emprestimo(
                        rs.getString("dataemprestimo"),
                        rs.getString("datadevolucao"),
                        rs.getString("livro"),
                        rs.getString("usuario")
                );
                emprestimo.setId(rs.getInt("id"));
                emprestimos.add(emprestimo);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar empréstimos: " + e.getMessage());
            e.printStackTrace();
        }
        return emprestimos;
    }

    public Emprestimo findById(int id) {
        String sql = "SELECT * FROM emprestimo WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Emprestimo(
                        rs.getString("dataemprestimo"),
                        rs.getString("datadevolucao"),
                        rs.getString("livro"),
                        rs.getString("usuario")
                );
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar empréstimo: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public void update(Emprestimo emprestimo) {
        String sql = "UPDATE emprestimo SET dataemprestimo = ?, datadevolucao = ?, livro = ?, usuario = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, emprestimo.getDataEmprestimo());
            stmt.setString(2, emprestimo.getDataDevolucao());
            stmt.setString(3, emprestimo.getLivro());
            stmt.setString(4, emprestimo.getUsuario());
            stmt.setInt(5, emprestimo.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar empréstimo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM emprestimo WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao deletar empréstimo: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
