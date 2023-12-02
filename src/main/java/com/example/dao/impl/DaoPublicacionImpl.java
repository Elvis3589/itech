package com.example.dao.impl;

import com.example.config.Conexion;
import com.example.dao.DaoPublicacion;
import com.example.entidades.Publicacion;
import com.example.entidades.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class DaoPublicacionImpl implements DaoPublicacion {

    private final Conexion bd;
    private String mensaje;

    public DaoPublicacionImpl() {
        this.bd = new Conexion();
    }

    @Override
    public List<Publicacion> publicacionSel() {
        List<Publicacion> lista = new ArrayList<>();
        String sql = "SELECT p.id_publicacion,p.descripcion,p.contenido,p.fecha,p.id_usuario,u.nombre,u.apellidos "
                + "FROM publicacion p "
                + "INNER JOIN usuarios u ON p.id_usuario = u.id_usuario";

        try (Connection c = bd.Conectar()) {
            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Publicacion pub = new Publicacion();
                pub.setId_publicacion(rs.getInt(1));
                pub.setDescripcion(rs.getString(2));
                pub.setContenidoBase64(Base64.getEncoder().encodeToString(rs.getBytes(3)));
                pub.setFecha(rs.getString(4));
                pub.setId_usuario(rs.getInt(5));

                Usuario us = new Usuario();
                us.setNombre(rs.getString(6));
                us.setApellidos(rs.getString(7));

                pub.setUsuario(us);
                lista.add(pub);
            }
        } catch (Exception e) {
            mensaje = e.getMessage();
        }

        return lista;
    }

    @Override
    public List<Publicacion> publicacionGet(int id) {
        List<Publicacion> lista = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("p.id_publicacion,")
                .append("p.descripcion,")
                .append("p.contenido,")
                .append("p.fecha,")
                .append("p.id_usuario,")
                .append("u.nombre,")
                .append("u.apellidos")
                .append(" FROM publicacion p")
                .append(" INNER JOIN usuarios u ON p.id_usuario = u.id_usuario")
                .append(" WHERE u.id_usuario = ?");
        try (Connection c = bd.Conectar()) {
            PreparedStatement ps = c.prepareStatement(sql.toString());
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Publicacion pub = new Publicacion();
                    pub.setId_publicacion(rs.getInt(1));
                    pub.setDescripcion(rs.getString(2));
                    pub.setContenido(rs.getBytes(3));

                    pub.setFecha(rs.getString(4));
                    pub.setId_usuario(rs.getInt(5));

                    Usuario us = new Usuario();
                    us.setNombre(rs.getString(6));
                    us.setApellidos(rs.getString(7));

                    pub.setUsuario(us);
                    lista.add(pub);
                }
            } catch (SQLException e) {
                mensaje = e.getMessage();
            }
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }

        return lista;
    }

@Override
public String publicacionIns(Publicacion publicacion) {
    StringBuilder sql = new StringBuilder();
    sql.append("INSERT INTO publicacion (")
            .append("descripcion,")
            .append("contenido,")
            .append("fecha,")
            .append("id_usuario")
            .append(") VALUES (?,?,?,?)");
    try (Connection c = bd.Conectar()) {
        PreparedStatement ps = c.prepareStatement(sql.toString());
        ps.setString(1, publicacion.getDescripcion());
        ps.setBytes(2, publicacion.getContenido());
        ps.setString(3, publicacion.getFecha());
        ps.setInt(4, publicacion.getId_usuario());
        int cont = ps.executeUpdate();
        if (cont == 0) {
            mensaje = "0 filas insertadas";
        } else {
            mensaje = "Inserción exitosa";
        }
    } catch (SQLException e) {
        mensaje = e.getMessage();
        e.printStackTrace(); 
    }

    return mensaje;
}


    @Override
    public String publicacionUpd(Publicacion publicacion, boolean actualizarImagen) {
        StringBuilder sql = new StringBuilder();
        if (actualizarImagen == true) {
            sql.append("UPDATE publicacion SET ")
                    .append("descripcion = ?,")
                    .append("contenido = ?,")
                    .append("fecha = ?,")
                    .append(" WHERE id_publicacion = ? ");
        } else {
            sql.append("UPDATE publicacion SET ")
                    .append("descripcion = ?,")
                    .append("fecha = ?,")
                    .append(" WHERE id_publicacion = ? ");
        }

        try (Connection c = bd.Conectar()) {
            PreparedStatement ps = c.prepareStatement(sql.toString());
            if (actualizarImagen) {
                ps.setString(1, publicacion.getDescripcion());
                ps.setBytes(2, publicacion.getContenido());
                ps.setString(3, publicacion.getFecha());
                ps.setInt(4, publicacion.getId_publicacion());
            } else {
                ps.setString(1, publicacion.getDescripcion());
                ps.setString(2, publicacion.getFecha());
                ps.setInt(3, publicacion.getId_publicacion());
            }

            int cont = ps.executeUpdate();
            if (cont == 0) {
                mensaje = "No se pudo actualizar";
            }
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }

        return mensaje;
    }

    @Override
    public String publicacionDel(String id) {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM publicacion")
                .append(" WHERE id_publicacion = ?");
        try (Connection c = bd.Conectar()) {
            PreparedStatement ps = c.prepareStatement(sql.toString());
            ps.setString(1, id);
            int resultado = ps.executeUpdate();
            if (resultado == 0) {
                mensaje = "Cero registros eliminados";
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
