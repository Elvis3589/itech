package com.example.dao;

import com.example.entidades.Usuario;
import java.util.List;

public interface DaoUsuario {

    String getMensaje();

    boolean registrarUsuario(Usuario usuario);

    Usuario obtenerUsuarioPorEmail(String email);

    boolean actualizarImagenUsuario(int idUsuario, byte[] imagen);

    List<Usuario> obtenerTodosLosUsuarios();

    boolean actualizarUsuario(Usuario usuario);

    boolean eliminarUsuario(int idUsuario);

    String obtenerContraseñaUsuario(int idUsuario);

    boolean actualizarContraseñaUsuario(int idUsuario, String nuevaContraseña);
}
