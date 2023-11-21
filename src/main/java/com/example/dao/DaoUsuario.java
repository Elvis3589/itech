package com.example.dao;

import com.example.entidades.Usuario;

public interface DaoUsuario {

    String getMensaje();

    boolean registrarUsuario(Usuario usuario);

    Usuario obtenerUsuarioPorEmail(String email);
}
