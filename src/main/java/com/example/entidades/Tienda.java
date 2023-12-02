package com.example.entidades;

import java.util.Base64;

public class Tienda {
    private Integer id_tienda;
    private String titulo;
    private byte[] imagen; 
    private String imagenBase64;
    private Float precio;
    private Integer id_usuario;
    private DetallesTienda detallesTienda;

    public Tienda() {
    }
    public Tienda(int id_tienda, String titulo, byte[] imagen, float precio, int id_usuario) {
        this.id_tienda = id_tienda;
        this.titulo = titulo;
        this.imagen = imagen;
        this.precio = precio;
        this.id_usuario = id_usuario;
    }
    public Integer getId_tienda() {
        return id_tienda;
    }

    public void setId_tienda(Integer id_tienda) {
        this.id_tienda = id_tienda;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getImagenBase64() {
        return imagenBase64;
    }

    public void setImagenBase64(String imagenBase64) {
        this.imagenBase64 = imagenBase64;
    }

    public void convertirImagenBase64() {
        this.imagenBase64 = Base64.getEncoder().encodeToString(this.imagen);
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public DetallesTienda getDetallesTienda() {
        return detallesTienda;
    }

    public void setDetallesTienda(DetallesTienda detallesTienda) {
        this.detallesTienda = detallesTienda;
    }
}
