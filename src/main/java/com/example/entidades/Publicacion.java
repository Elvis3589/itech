package com.example.entidades;

import java.util.Base64;

public class Publicacion {

    private Integer id_publicacion;
    private String descripcion;
    private byte[] contenido; 
    private String fecha;
    private Integer id_usuario;
    private Usuario usuario;
    private String contenidoBase64;  

    public Publicacion() {
    }

    public Publicacion(Integer id_publicacion, String descripcion, byte[] contenido, String fecha, Integer id_usuario, Usuario usuario) {
        this.id_publicacion = id_publicacion;
        this.descripcion = descripcion;
        this.contenido = contenido;
        this.fecha = fecha;
        this.id_usuario = id_usuario;
        this.usuario = usuario;
        this.contenidoBase64 = convertirContenidoBase64();  
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

    public byte[] getContenido() {
        return contenido;
    }

    public void setContenido(byte[] contenido) {
        this.contenido = contenido;
        this.contenidoBase64 = convertirContenidoBase64();  
    }

    public String getContenidoBase64() {
        return contenidoBase64;
    }
    public void setContenidoBase64(String contenidoBase64) {
        this.contenidoBase64 = contenidoBase64;
    }
    private String convertirContenidoBase64() {
        return Base64.getEncoder().encodeToString(this.contenido);
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
