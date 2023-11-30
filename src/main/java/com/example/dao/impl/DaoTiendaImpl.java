package com.example.dao.impl;

import com.example.config.Conexion;
import com.example.dao.DaoTienda;
import com.example.entidades.Tienda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoTiendaImpl implements DaoTienda{
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
                tie.setImagen(rs.getString(3));
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
                    tie.setImagen(rs.getString(3));
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
        try(Connection c = bd.Conectar()) {
            PreparedStatement ps = c.prepareStatement(sql.toString());
            ps.setString(1, tienda.getTitulo());
            ps.setString(2, tienda.getImagen());
            ps.setFloat(3, tienda.getPrecio());
            ps.setInt(4, tienda.getId_usuario());
            int cont = ps.executeUpdate();
            if(cont == 0){
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
        try(Connection c = bd.Conectar()) {
            PreparedStatement ps = c.prepareStatement(sql.toString());
            ps.setString(1, id);
            int resultado = ps.executeUpdate();
            if(resultado == 0){
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
