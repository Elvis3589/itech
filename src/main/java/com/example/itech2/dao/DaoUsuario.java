package com.example.itech2.dao;

import com.example.itech2.entidades.Usuario;
import java.util.List;

public interface DaoUsuario {
    List<Usuario> usuarioSel();
    Usuario usuarioGet(String email, String contrasena);
    String usuarioIns(Usuario usuario);
    String usuarioUpd(Usuario usuario);
    String usuarioDel(String id);
    String getMessage();

}
