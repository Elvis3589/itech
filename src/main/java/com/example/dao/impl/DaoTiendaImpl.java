package com.example.dao.impl;

import com.example.config.Conexion;
import com.example.dao.DaoTienda;
import com.example.entidades.Tienda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class DaoTiendaImpl implements DaoTienda {

    private Conexion bd;
    private String mensaje;

    public DaoTiendaImpl() {
        bd = new Conexion();
    }

    @Override
    public List<Tienda> tiendaSel() {
        List<Tienda> lista = null;

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("id_tienda,")
                .append("titulo,")
                .append("imagen,")
                .append("precio,")
                .append("id_usuario")
                .append(" FROM tienda");

        try (Connection c = bd.Conectar()) {
            PreparedStatement ps = c.prepareStatement(sql.toString());
            ResultSet rs = ps.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                Tienda tie = new Tienda();
                tie.setId_tienda(rs.getInt(1));
                tie.setTitulo(rs.getString(2));
                tie.setImagenBase64(Base64.getEncoder().encodeToString(rs.getBytes(3))); 
                tie.setPrecio(rs.getFloat(4));
                tie.setId_usuario(rs.getInt(5));
                lista.add(tie);
            }
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }

        return lista;
    }

    @Override
    public Tienda tiendaGet(String dato) {
        Tienda tie = new Tienda();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("id_tienda,")
                .append("titulo,")
                .append("imagen,")
                .append("precio,")
                .append("id_usuario")
                .append(" FROM tienda")
                .append(" WHERE titulo = ?");

        try (Connection c = bd.Conectar()) {
            PreparedStatement ps = c.prepareStatement(sql.toString());
            ps.setString(1, dato);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    tie.setId_tienda(rs.getInt(1));
                    tie.setTitulo(rs.getString(2));
                    tie.setImagen(rs.getBytes(3));
                    tie.setPrecio(rs.getFloat(4));
                    tie.setId_usuario(rs.getInt(5));
                }
            } catch (SQLException e) {
                mensaje = e.getMessage();
            }
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }

        return tie;
    }

    @Override
    public String tiendaIns(Tienda tienda) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO tienda( ")
                .append("titulo,")
                .append("imagen,")
                .append("precio,")
                .append("id_usuario")
                .append(") VALUES (?,?,?,?)");
        try (Connection c = bd.Conectar()) {
            PreparedStatement ps = c.prepareStatement(sql.toString());
            ps.setString(1, tienda.getTitulo());
            ps.setBytes(2, tienda.getImagen());
            ps.setFloat(3, tienda.getPrecio());
            ps.setInt(4, tienda.getId_usuario());
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
    public String tiendaUpd(Tienda tienda) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String tiendaDel(String id) {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM tienda")
                .append(" WHERE id_tienda = ?");
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
@Override
public List<Tienda> obtenerMaterialesDestacadosPremium() {
    List<Tienda> materialesPremium = new ArrayList<>();

    try (Connection connection = bd.Conectar()) {
        String sql = "SELECT t.* FROM tienda t "
                + "INNER JOIN suscripciones_premium sp ON t.id_usuario = sp.id_usuario "
                + "WHERE sp.fecha_fin >= CURRENT_DATE "
                + "ORDER BY RAND() LIMIT 4";

        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Tienda material = new Tienda(
                        resultSet.getInt("id_tienda"),
                        resultSet.getString("titulo"),
                        resultSet.getBytes("imagen"),
                        resultSet.getFloat("precio"),
                        resultSet.getInt("id_usuario")
                );

                // Asegúrate de tener el método setImagenBase64 en la clase Tienda
                material.convertirImagenBase64();

                materialesPremium.add(material);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return materialesPremium;
}

}
