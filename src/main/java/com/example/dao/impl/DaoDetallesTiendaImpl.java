package com.example.dao.impl;

import com.example.config.Conexion;
import com.example.dao.DaoDetallesTienda;
import com.example.entidades.DetallesTienda;
import com.example.entidades.Tienda;
import com.example.entidades.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class DaoDetallesTiendaImpl implements DaoDetallesTienda {

    private Conexion bd;
    private String mensaje;

    public DaoDetallesTiendaImpl() {
        bd = new Conexion();
    }

    @Override
    public List<DetallesTienda> detallesGet(int id_tienda) {
        StringBuilder sql = new StringBuilder();
        DetallesTienda dt = new DetallesTienda();
        Tienda tie = new Tienda();
        Usuario us = new Usuario();
        List<DetallesTienda> lista = new ArrayList<>();

        sql.append("SELECT ")
                .append("dt.id_detalles_tienda,")
                .append("dt.producto,")
                .append("dt.categoria,")
                .append("dt.tipo_venta,")
                .append("dt.descripcion,")
                .append("dt.estado,")
                .append("dt.contacto,")
                .append("dt.id_tienda,")
                .append("dt.id_usuario,")
                .append("t.precio,")
                .append("t.imagen,")
                .append("u.nombre,")
                .append("u.apellidos")
                .append(" FROM detalles_tienda dt")
                .append(" INNER JOIN tienda t")
                .append(" INNER JOIN usuarios u")
                .append(" ON dt.id_tienda = t.id_tienda AND dt.id_usuario = u.id_usuario")
                .append(" WHERE t.id_tienda = ?");

        try (Connection c = bd.Conectar()) {
            PreparedStatement ps = c.prepareStatement(sql.toString());
            ps.setInt(1, id_tienda);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    dt.setId_detalles_tienda(rs.getInt(1));
                    dt.setProducto(rs.getString(2));
                    dt.setCategoria(rs.getString(3));
                    dt.setTipo_venta(rs.getString(4));
                    dt.setDescripcion(rs.getString(5));
                    dt.setEstado(rs.getString(6));
                    dt.setContacto(rs.getString(7));
                    dt.setId_tienda(rs.getInt(8));
                    dt.setId_usuario(rs.getInt(9));

                    tie.setPrecio(rs.getFloat(10));
                    tie.setImagenBase64(Base64.getEncoder().encodeToString(rs.getBytes(11))); // Convierte a Base64
                    dt.setTienda(tie);

                    us.setNombre(rs.getString(12));
                    us.setApellidos(rs.getString(13));
                    dt.setUsuario(us);
                    lista.add(dt);
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
    public String detallesIns(DetallesTienda detalles) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO detalles_tienda( ")
                .append("producto,")
                .append("categoria,")
                .append("tipo_venta,")
                .append("descripcion,")
                .append("estado,")
                .append("contacto,")
                .append("id_tienda,")
                .append("id_usuario")
                .append(") VALUES (?,?,?,?,?,?,?,?)");
        try (Connection c = bd.Conectar()) {
            PreparedStatement ps = c.prepareStatement(sql.toString());
            ps.setString(1, detalles.getProducto());
            ps.setString(2, detalles.getCategoria());
            ps.setString(3, detalles.getTipo_venta());
            ps.setString(4, detalles.getDescripcion());
            ps.setString(5, detalles.getEstado());
            ps.setString(6, detalles.getContacto());
            ps.setInt(7, detalles.getId_tienda());
            ps.setInt(8, detalles.getId_usuario());
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
