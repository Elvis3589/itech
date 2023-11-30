package com.example.entidades;

public class Tienda {
    private Integer id_tienda;
    private String titulo;
    private String imagen;
    private Float precio;
    private Integer id_usuario;
    private DetallesTienda detallesTienda;

    public Tienda() {
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
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
