package com.example.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SomeDAO {
    public void someDatabaseOperation() {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            pstmt = conn.prepareStatement("SUA CONSULTA SQL AQUI");
            // Execute a operação
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Feche os recursos
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            DatabaseConnection.closeConnection();
        }
    }
}
