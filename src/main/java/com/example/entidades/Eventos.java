package com.example.entidades;

import java.sql.Date;

public class Eventos {
    private int idEvento;
    private int idUsuario;  
    private String nombre;
    private String apellidos;
    private String email;
    private String nombreEvento;
    private String lugar;
    private String hora;
    private Date fecha;  
    private String celular;
    private String descripcion;
    private int maxCantidad;
    private byte[] imagenEvento;  // Cambiado a byte[]

    public Eventos(int idEvento, int idUsuario, String nombre, String apellidos, String email, 
                   String nombreEvento, String lugar, String hora, Date fecha, 
                   String celular, String descripcion, int maxCantidad, byte[] imagenEvento) {
        this.idEvento = idEvento;
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.nombreEvento = nombreEvento;
        this.lugar = lugar;
        this.hora = hora;
        this.fecha = fecha;
        this.celular = celular;
        this.descripcion = descripcion;
        this.maxCantidad = maxCantidad;
        this.imagenEvento = imagenEvento;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getMaxCantidad() {
        return maxCantidad;
    }

    public void setMaxCantidad(int maxCantidad) {
        this.maxCantidad = maxCantidad;
    }

    public byte[] getImagenEvento() {
        return imagenEvento;
    }

    public void setImagenEvento(byte[] imagenEvento) {
        this.imagenEvento = imagenEvento;
    }
}