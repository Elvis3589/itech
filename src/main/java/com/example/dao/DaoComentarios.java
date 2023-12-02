package com.example.dao;

import com.example.entidades.Comentarios;
import java.sql.SQLException;
import java.util.List;

public interface DaoComentarios {
    String getMessage();

    void insertarComentario(Comentarios comentario) throws SQLException;
    List<Comentarios> obtenerComentariosPorPublicacion(int idPublicacion) throws SQLException;
}
