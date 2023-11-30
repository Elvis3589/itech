package com.example.entidades;

import java.sql.Date;

public class Publicacion {
    private Integer id_publicacion;
    private String descripcion;
    private String contenido;
    private String fecha;
    private Integer id_usuario;
    private Usuario usuario;

    public Publicacion() {
    }

    public Publicacion(Integer id_publicacion, String descripcion, String contenido, String fecha, Integer id_usuario, Usuario usuario) {
        this.id_publicacion = id_publicacion;
        this.descripcion = descripcion;
        this.contenido = contenido;
        this.fecha = fecha;
        this.id_usuario = id_usuario;
        this.usuario = usuario;
    }

    public Integer getId_publicacion() {
        return id_publicacion;
    }

    public void setId_publicacion(Integer id_publicacion) {
        this.id_publicacion = id_publicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
