package com.example.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private final String url = "jdbc:mysql://localhost:3306/red_social";
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String user = "root";
    private final String pass = "";

    public Connection Conectar() throws SQLException {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("Error al conectar a la base de datos: " + e.getMessage());
        }
    }
}
