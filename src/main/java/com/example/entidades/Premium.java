package com.example.entidades;

import java.time.LocalDateTime;

public class Premium {
    private int idSuscripcion;
    private int idUsuario;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;

    public Premium() {
    }

    public Premium(int idSuscripcion, int idUsuario, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        this.idSuscripcion = idSuscripcion;
        this.idUsuario = idUsuario;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public int getIdSuscripcion() {
        return idSuscripcion;
    }

    public void setIdSuscripcion(int idSuscripcion) {
        this.idSuscripcion = idSuscripcion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }
    
}
