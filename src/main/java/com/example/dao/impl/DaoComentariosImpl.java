package com.example.dao.impl;

import com.example.config.Conexion;
import com.example.dao.DaoComentarios;
import com.example.entidades.Comentarios;
import java.util.List;

public class DaoComentariosImpl implements DaoComentarios{
    private Conexion bd;
    private String mensaje;

    public DaoComentariosImpl() {
        bd = new Conexion();
    }
    
    @Override
    public List<Comentarios> comentariosSel() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String comentariosIns(Comentarios comentarios) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String comentariosUpd(Comentarios comentarios) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String comentariosDel(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getMessage() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
