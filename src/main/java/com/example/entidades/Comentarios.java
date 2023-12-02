package com.example.entidades;

import java.sql.Timestamp;

public class Comentarios {

    private int id_comentario;
    private String contenido;
    private int id_usuario;
    private int id_publicacion;
    private Usuario usuario; 
    private Timestamp fechaComentario;

    public Comentarios() {
    }

    public Comentarios(int id_comentario, String contenido, int id_usuario, int id_publicacion, Usuario usuario, Timestamp fechaComentario) {
        this.id_comentario = id_comentario;
        this.contenido = contenido;
        this.id_usuario = id_usuario;
        this.id_publicacion = id_publicacion;
        this.usuario = usuario;
        this.fechaComentario = fechaComentario;
    }

    public int getId_comentario() {
        return id_comentario;
    }

    public void setId_comentario(int id_comentario) {
        this.id_comentario = id_comentario;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_publicacion() {
        return id_publicacion;
    }

    public void setId_publicacion(int id_publicacion) {
        this.id_publicacion = id_publicacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Timestamp getFechaComentario() {
        return fechaComentario;
    }

    public void setFechaComentario(Timestamp fechaComentario) {
        this.fechaComentario = fechaComentario;
    }

}
