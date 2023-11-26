package com.example.dao;

import com.example.entidades.Eventos;

import java.util.List;

public interface DaoEvento {

    boolean registrarEvento(Eventos evento);

    List<Eventos> obtenerEventos();

    List<Eventos> obtenerEventosActivosPorUsuario(int idUsuario);

    Eventos obtenerDetallesEvento(int idEvento);

    boolean registrarReserva(int idEvento, int idUsuario);

    List<Eventos> obtenerEventosReservadosPorUsuario(int idUsuario);

    List<Eventos> obtenerEventosFinalizadosPorUsuario(int idUsuario);

    List<Eventos> obtenerEventosPremium();
    
    int obtenerCantidadReservasPorEvento(int idEvento);
}
