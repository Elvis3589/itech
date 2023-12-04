package com.example.dao.impl;

import com.example.config.AESUtil;
import com.example.config.Conexion;
import com.example.dao.DaoUsuario;
import com.example.entidades.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        if (existeUsuarioConDNI(connection, usuario.getDni())) {
            mensaje = "Ya existe un usuario con el mismo DNI";
            return false;
        }

        String sql = "INSERT INTO usuarios(nombre, apellidos, email, contraseña, rol, dni) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, usuario.getNombre());
            preparedStatement.setString(2, usuario.getApellidos());
            preparedStatement.setString(3, usuario.getEmail());
            preparedStatement.setString(4, AESUtil.encriptar(usuario.getContraseña()));
            preparedStatement.setString(5, "usuario");
            preparedStatement.setString(6, usuario.getDni()); 

            int filasAfectadas = preparedStatement.executeUpdate();

            if (filasAfectadas > 0) {
                mensaje = "Usuario registrado correctamente ";
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
private boolean existeUsuarioConDNI(Connection connection, String dni) throws SQLException {
    String sql = "SELECT COUNT(*) FROM usuarios WHERE dni = ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
        preparedStatement.setString(1, dni);
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        }
    }
    return false;
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
                    String dni = resultSet.getString("dni");  

                    return new Usuario(idUsuario, nombre, apellidos, email, contraseña, imagen, rol, dni);  
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

@Override
public List<Usuario> obtenerTodosLosUsuarios() {
    List<Usuario> usuarios = new ArrayList<>();
    try (Connection connection = conexion.Conectar()) {
        String sql = "SELECT id_usuario, nombre, apellidos, email, rol, dni FROM usuarios";  
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int idUsuario = resultSet.getInt("id_usuario");
                    String nombre = resultSet.getString("nombre");
                    String apellidos = resultSet.getString("apellidos");
                    String email = resultSet.getString("email");
                    String rol = resultSet.getString("rol");
                    String dni = resultSet.getString("dni");  

                    usuarios.add(new Usuario(idUsuario, nombre, apellidos, email, null, null, rol, dni));  
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return usuarios;
}


@Override
public boolean actualizarUsuario(Usuario usuario) {
    try (Connection connection = conexion.Conectar()) {
        String sql = "UPDATE usuarios SET nombre = ?, apellidos = ?, email = ?, rol = ?, dni = ? WHERE id_usuario = ?"; 
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, usuario.getNombre());
            preparedStatement.setString(2, usuario.getApellidos());
            preparedStatement.setString(3, usuario.getEmail());
            preparedStatement.setString(4, usuario.getRol());
            preparedStatement.setString(5, usuario.getDni());  
            preparedStatement.setInt(6, usuario.getIdUsuario());

            int filasAfectadas = preparedStatement.executeUpdate();

            return filasAfectadas > 0;
        }
    } catch (SQLException e) {
        mensaje = "Error al actualizar el usuario: " + e.getMessage();
        return false;
    }
}


    @Override
    public boolean eliminarUsuario(int idUsuario) {
        try (Connection connection = conexion.Conectar()) {
            String sql = "DELETE FROM usuarios WHERE id_usuario = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, idUsuario);

                int filasAfectadas = preparedStatement.executeUpdate();

                return filasAfectadas > 0;
            }
        } catch (SQLException e) {
            mensaje = "Error al eliminar el usuario: " + e.getMessage();
            return false;
        }
    }

    @Override
    public String obtenerContraseñaUsuario(int idUsuario) {
        try (Connection connection = conexion.Conectar()) {
            String sql = "SELECT contraseña FROM usuarios WHERE id_usuario = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, idUsuario);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getString("contraseña");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean actualizarContraseñaUsuario(int idUsuario, String nuevaContraseña) {
        try (Connection connection = conexion.Conectar()) {
            String sql = "UPDATE usuarios SET contraseña = ? WHERE id_usuario = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                String encryptedContraseña = AESUtil.encriptar(nuevaContraseña);
                preparedStatement.setString(1, encryptedContraseña);
                preparedStatement.setInt(2, idUsuario);

                int filasAfectadas = preparedStatement.executeUpdate();

                return filasAfectadas > 0;
            }
        } catch (SQLException e) {
            mensaje = "Error al actualizar la contraseña del usuario: " + e.getMessage();
            return false;
        }
    }

}
