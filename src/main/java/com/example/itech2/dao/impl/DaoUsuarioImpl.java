package com.example.itech2.dao.impl;

import com.example.itech2.config.Conexion;
import com.example.itech2.dao.DaoUsuario;
import com.example.itech2.entidades.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoUsuarioImpl implements DaoUsuario{
    private Conexion bd;
    private String mensaje;

    public DaoUsuarioImpl() {
        bd = new Conexion();
    }

    @Override
    public List<Usuario> usuarioSel() {
        List<Usuario> lista = null;

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("id_usuario,")
                .append("nombre,")
                .append("apellidos,")
                .append("email,")
                .append("contraseña,")
                .append("foto")
                .append(" FROM usuario");

        try (Connection c = bd.Conectar()) {
            PreparedStatement ps = c.prepareStatement(sql.toString());
            ResultSet rs = ps.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                Usuario us = new Usuario();
                us.setId_usuario(rs.getString(1));
                us.setNombre(rs.getString(2));
                us.setApellidos(rs.getString(3));
                us.setEmail(rs.getString(4));
                us.setContraseña(rs.getString(5));
                us.setFoto(rs.getString(6));
                lista.add(us);
            }
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }

        return lista;
    }

    @Override
    public Usuario usuarioGet(String email, String contrasena) {
        Usuario us = new Usuario();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("id_usuario,")
                .append("nombre,")
                .append("apellidos,")
                .append("email,")
                .append("contraseña,")
                .append("foto")
                .append(" FROM usuario WHERE email = ? AND contraseña = ?");
        try (Connection c = bd.Conectar()) {
            PreparedStatement ps = c.prepareStatement(sql.toString());
            ps.setString(1, email);
            ps.setString(2, contrasena);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    us.setId_usuario(rs.getString(1));
                    us.setNombre(rs.getString(2));
                    us.setApellidos(rs.getString(3));
                    us.setEmail(rs.getString(4));
                    us.setContraseña(rs.getString(5));
                    us.setFoto(rs.getString(6));
                } 
            } catch (SQLException e) {
                mensaje = e.getMessage();
            }
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }

        return us;
    }

    @Override
    public String usuarioIns(Usuario usuario) {
         StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO usuario( ")
                .append("id_usuario,")
                .append("nombre,")
                .append("apellidos,")
                .append("email,")
                .append("contraseña,")
                .append("foto")
                .append(") VALUES (?,?,?,?,?,?)");
        try(Connection c = bd.Conectar()) {
            PreparedStatement ps = c.prepareStatement(sql.toString());
            ps.setString(1, usuario.getId_usuario());
            ps.setString(2, usuario.getNombre());
            ps.setString(3, usuario.getApellidos());
            ps.setString(4, usuario.getEmail());
            ps.setString(5, usuario.getContraseña());
            ps.setString(6, usuario.getFoto());
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
    public String usuarioUpd(Usuario usuario) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE usuario SET ")
                .append("nombre = ?,")
                .append("apellidos = ?,")
                .append("email = ?,")
                .append("contraseña = ?,")
                .append("foto = ?")
                .append(" WHERE id_usuario = ? ");
        try (Connection c = bd.Conectar()) {
            PreparedStatement ps = c.prepareStatement(sql.toString());
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellidos());
            ps.setString(3, usuario.getEmail());
            ps.setString(4, usuario.getContraseña());
            ps.setString(5, usuario.getFoto());
            ps.setString(6, usuario.getId_usuario());
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
    public String usuarioDel(String id) {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM usuario")
                .append(" WHERE id_usuario = ?");
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
