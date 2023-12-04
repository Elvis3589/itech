package com.example.dao;

import com.example.entidades.Eventos;
import java.io.InputStream;

import java.util.List;
import net.sf.jasperreports.engine.JasperPrint;

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
    
    JasperPrint reporteEvento(int id_evento, int user, String rutaImg);
    
    boolean haReservadoEvento(int idEvento, int idUsuario);
    
    InputStream imagen(int id);
}
