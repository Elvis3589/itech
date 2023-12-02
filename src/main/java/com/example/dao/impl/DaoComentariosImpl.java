package com.example.dao.impl;

import com.example.config.Conexion;
import com.example.dao.DaoComentarios;
import com.example.entidades.Comentarios;
import com.example.entidades.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoComentariosImpl implements DaoComentarios {

    private Conexion bd;
    private String mensaje;

    public DaoComentariosImpl() {
        bd = new Conexion();
    }

    @Override
    public String getMessage() {
        return mensaje;
    }

    @Override
    public void insertarComentario(Comentarios comentario) throws SQLException {
        try (Connection conn = bd.Conectar(); PreparedStatement pstmt = conn.prepareStatement("INSERT INTO comentarios (contenido, id_usuario, id_publicacion) VALUES (?, ?, ?)")) {
            pstmt.setString(1, comentario.getContenido());
            pstmt.setInt(2, comentario.getId_usuario());
            pstmt.setInt(3, comentario.getId_publicacion());
            pstmt.executeUpdate();
        }
    }

    @Override
    public List<Comentarios> obtenerComentariosPorPublicacion(int idPublicacion) throws SQLException {
        List<Comentarios> comentarios = new ArrayList<>();
        try (Connection conn = bd.Conectar(); PreparedStatement pstmt = conn.prepareStatement("SELECT c.*, u.nombre, u.apellidos FROM comentarios c JOIN usuarios u ON c.id_usuario = u.id_usuario WHERE c.id_publicacion = ?")) {
            pstmt.setInt(1, idPublicacion);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Comentarios comentario = new Comentarios();
                    comentario.setId_comentario(rs.getInt("id_comentario"));
                    comentario.setContenido(rs.getString("contenido"));
                    comentario.setId_usuario(rs.getInt("id_usuario"));
                    comentario.setId_publicacion(rs.getInt("id_publicacion"));
                    comentario.setFechaComentario(rs.getTimestamp("fecha_comentario"));

                    Usuario usuario = new Usuario();
                    usuario.setIdUsuario(rs.getInt("id_usuario"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setApellidos(rs.getString("apellidos"));

                    comentario.setUsuario(usuario);

                    comentarios.add(comentario);
                }
            }
        }
        return comentarios;
    }
}
