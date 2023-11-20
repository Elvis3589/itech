package com.example.itech2.dao;

import com.example.itech2.entidades.Usuario;

public interface DaoUsuario {

    String getMensaje();

    boolean registrarUsuario(Usuario usuario);

    Usuario obtenerUsuarioPorEmail(String email);
}
