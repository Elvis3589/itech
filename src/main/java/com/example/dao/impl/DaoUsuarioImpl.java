package com.example.dao.impl;

import com.example.config.AESUtil;
import com.example.config.Conexion;
import com.example.dao.DaoUsuario;
import com.example.entidades.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoUsuarioImpl implements DaoUsuario {

    private final Conexion conexion;
    private String mensaje;

    public DaoUsuarioImpl() {
        conexion = new Conexion();
    }

    @Override
    public String getMensaje() {
        return mensaje;
    }

    @Override
    public boolean registrarUsuario(Usuario usuario) {
        try (Connection connection = conexion.Conectar()) {
            String sql = "INSERT INTO usuarios(nombre, apellidos, email, contraseña) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, usuario.getNombre());
                preparedStatement.setString(2, usuario.getApellidos());
                preparedStatement.setString(3, usuario.getEmail());

                String encryptedContraseña = AESUtil.encriptar(usuario.getContraseña());
                preparedStatement.setString(4, encryptedContraseña);

                int filasAfectadas = preparedStatement.executeUpdate();

                if (filasAfectadas > 0) {
                    mensaje = "Usuario registrado correctamente";
                    return true;
                } else {
                    mensaje = "No se pudo registrar el usuario";
                    return false;
                }
            }
        } catch (SQLException e) {
            mensaje = "Error al registrar el usuario: " + e.getMessage();
            return false;
        }

    }

    @Override
    public Usuario obtenerUsuarioPorEmail(String email) {
        try (Connection connection = conexion.Conectar()) {
            String sql = "SELECT * FROM usuarios WHERE email = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, email);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return new Usuario(
                                resultSet.getInt("id_usuario"),
                                resultSet.getString("nombre"),
                                resultSet.getString("apellidos"),
                                resultSet.getString("email"),
                                resultSet.getString("contraseña")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
