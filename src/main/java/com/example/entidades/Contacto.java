package com.example.entidades;

import java.sql.Timestamp;

public class Contacto {
    private Integer id_contacto;
    private Integer id_detalles_tienda;
    private String nombre_comprador;
    private String email_comprador;
    private String mensaje;
    private Timestamp fecha_contacto;

    public Contacto() {
    }

    public Contacto(Integer id_contacto, Integer id_detalles_tienda, String nombre_comprador, String email_comprador, String mensaje, Timestamp fecha_contacto) {
        this.id_contacto = id_contacto;
        this.id_detalles_tienda = id_detalles_tienda;
        this.nombre_comprador = nombre_comprador;
        this.email_comprador = email_comprador;
        this.mensaje = mensaje;
        this.fecha_contacto = fecha_contacto;
    }

    public Integer getId_contacto() {
        return id_contacto;
    }

    public void setId_contacto(Integer id_contacto) {
        this.id_contacto = id_contacto;
    }

    public Integer getId_detalles_tienda() {
        return id_detalles_tienda;
    }

    public void setId_detalles_tienda(Integer id_detalles_tienda) {
        this.id_detalles_tienda = id_detalles_tienda;
    }

    public String getNombre_comprador() {
        return nombre_comprador;
    }

    public void setNombre_comprador(String nombre_comprador) {
        this.nombre_comprador = nombre_comprador;
    }

    public String getEmail_comprador() {
        return email_comprador;
    }

    public void setEmail_comprador(String email_comprador) {
        this.email_comprador = email_comprador;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Timestamp getFecha_contacto() {
        return fecha_contacto;
    }

    public void setFecha_contacto(Timestamp fecha_contacto) {
        this.fecha_contacto = fecha_contacto;
    }


    
}
