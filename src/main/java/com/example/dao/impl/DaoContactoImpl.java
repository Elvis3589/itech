package com.example.dao.impl;

import com.example.config.Conexion;
import com.example.dao.DaoContacto;
import com.example.entidades.Contacto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoContactoImpl implements DaoContacto {

    private Conexion bd;
    private String mensaje;

    public DaoContactoImpl() {
        bd = new Conexion();
    }

    @Override
    public List<Contacto> obtenerContactos(int id_detalles_tienda) {

        return new ArrayList<>();
    }

    @Override
    public String registrarContacto(Contacto contacto) {
        String sql = "INSERT INTO contacto (id_detalles_tienda, nombre_comprador, email_comprador, mensaje) VALUES (?, ?, ?, ?)";
        try (Connection c = bd.Conectar(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, contacto.getId_detalles_tienda());
            ps.setString(2, contacto.getNombre_comprador());
            ps.setString(3, contacto.getEmail_comprador());
            ps.setString(4, contacto.getMensaje());

            int cont = ps.executeUpdate();
            if (cont == 0) {
                mensaje = "0 filas insertadas";
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

    @Override
    public List<Contacto> obtenerContactos() {
        List<Contacto> lista = new ArrayList<>();
        String sql = "SELECT * FROM contacto";

        try (Connection c = bd.Conectar(); PreparedStatement ps = c.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Contacto contacto = new Contacto();
                contacto.setId_contacto(rs.getInt("id_contacto"));
                contacto.setId_detalles_tienda(rs.getInt("id_detalles_tienda"));
                contacto.setNombre_comprador(rs.getString("nombre_comprador"));
                contacto.setEmail_comprador(rs.getString("email_comprador"));
                contacto.setMensaje(rs.getString("mensaje"));
                contacto.setFecha_contacto(rs.getTimestamp("fecha_contacto"));

                lista.add(contacto);
            }

        } catch (SQLException e) {
            mensaje = e.getMessage();
        }

        return lista;
    }

    @Override
    public List<Contacto> obtenerContactosPorUsuario(int idUsuario) {
        List<Contacto> lista = new ArrayList<>();
        String sql = "SELECT c.* FROM contacto c "
                + "INNER JOIN detalles_tienda dt ON c.id_detalles_tienda = dt.id_detalles_tienda "
                + "WHERE dt.id_usuario = ?";

        try (Connection c = bd.Conectar(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Contacto contacto = new Contacto();
                    contacto.setId_contacto(rs.getInt("id_contacto"));
                    contacto.setId_detalles_tienda(rs.getInt("id_detalles_tienda"));
                    contacto.setNombre_comprador(rs.getString("nombre_comprador"));
                    contacto.setEmail_comprador(rs.getString("email_comprador"));
                    contacto.setMensaje(rs.getString("mensaje"));
                    contacto.setFecha_contacto(rs.getTimestamp("fecha_contacto"));

                    lista.add(contacto);
                }
            }

        } catch (SQLException e) {
            mensaje = e.getMessage();
        }

        return lista;
    }

    @Override
    public void eliminarContacto(int idContacto) {
        String sql = "DELETE FROM contacto WHERE id_contacto = ?";
        try (Connection c = bd.Conectar(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, idContacto);

            int cont = ps.executeUpdate();
            if (cont == 0) {
                mensaje = "0 filas eliminadas";
            }
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
    }
}
