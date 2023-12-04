package com.example.dao;

import com.example.entidades.DetallesTienda;
import java.util.List;

public interface DaoDetallesTienda {
    List<DetallesTienda> detallesGet(int id_tienda);
    String detallesIns(DetallesTienda detalles);
    String getMessage();
    DetallesTienda obtenerDetallesPorId(int id_detalles_tienda);
}
