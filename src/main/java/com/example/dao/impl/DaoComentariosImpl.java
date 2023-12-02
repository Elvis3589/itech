// En DaoComentariosImpl.java
package com.example.dao.impl;

import com.example.config.Conexion;
import com.example.dao.DaoComentarios;
import com.example.entidades.Comentarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DaoComentariosImpl implements DaoComentarios {
    private Conexion bd;
    private String mensaje;

    public DaoComentariosImpl() {
        bd = new Conexion();
    }



    @Override
    public String agregarComentario(Comentarios comentario) {
        String sql = "INSERT INTO comentarios (contenido, id_usuario, id_publicacion) VALUES (?, ?, ?)";

        try (Connection c = bd.Conectar();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, comentario.getContenido());
            ps.setInt(2, comentario.getId_usuario());
            ps.setInt(3, comentario.getId_publicacion());

            int cont = ps.executeUpdate();
            if (cont == 0) {
                mensaje = "Error al insertar el comentario";
            } else {
                mensaje = "Comentario agregado exitosamente";
            }
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }

        return mensaje;
    }

    @Override
    public String getMessage() {
        return mensaje;
    }
}
