package com.example.entidades;

public class Comentarios {
    private String id_comentarios;
    private String contenido;
    private String id_usuario;
    private String id_publicacion;

    public Comentarios() {
    }

    public String getId_comentarios() {
        return id_comentarios;
    }

    public void setId_comentarios(String id_comentarios) {
        this.id_comentarios = id_comentarios;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getId_publicacion() {
        return id_publicacion;
    }

    public void setId_publicacion(String id_publicacion) {
        this.id_publicacion = id_publicacion;
    }
    

}
