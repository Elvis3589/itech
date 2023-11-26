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
        String sql = "INSERT INTO usuarios(nombre, apellidos, email, contraseña, rol) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, usuario.getNombre());
            preparedStatement.setString(2, usuario.getApellidos());
            preparedStatement.setString(3, usuario.getEmail());

            String encryptedContraseña = AESUtil.encriptar(usuario.getContraseña());
            preparedStatement.setString(4, encryptedContraseña);

            preparedStatement.setString(5, "usuario");

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
                    int idUsuario = resultSet.getInt("id_usuario");
                    String nombre = resultSet.getString("nombre");
                    String apellidos = resultSet.getString("apellidos");
                    String contraseña = resultSet.getString("contraseña");
                    byte[] imagen = resultSet.getBytes("imagen");
                    String rol = resultSet.getString("rol"); 

                    return new Usuario(idUsuario, nombre, apellidos, email, contraseña, imagen, rol);
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}


        @Override
    public boolean actualizarImagenUsuario(int idUsuario, byte[] imagen) {
        try (Connection connection = conexion.Conectar()) {
            String sql = "UPDATE usuarios SET imagen = ? WHERE id_usuario = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setBytes(1, imagen);
                preparedStatement.setInt(2, idUsuario);

                int filasAfectadas = preparedStatement.executeUpdate();

                return filasAfectadas > 0;
            }
        } catch (SQLException e) {
            mensaje = "Error al actualizar la imagen del usuario: " + e.getMessage();
            return false;
        }
    }
}
