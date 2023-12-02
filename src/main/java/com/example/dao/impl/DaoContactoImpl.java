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
        try (Connection c = bd.Conectar();
             PreparedStatement ps = c.prepareStatement(sql)) {
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
}
