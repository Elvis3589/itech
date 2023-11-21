package com.example.dao;

import com.example.entidades.Eventos;

import java.util.List;

public interface DaoEvento {

    boolean registrarEvento(Eventos evento);

    List<Eventos> obtenerEventos();
}