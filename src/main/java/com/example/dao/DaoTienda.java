package com.example.dao;

import com.example.entidades.Tienda;
import java.util.List;

public interface DaoTienda {
    List<Tienda> tiendaSel();
    Tienda tiendaGet(String dato);
    String tiendaIns(Tienda tienda);
    String tiendaUpd(Tienda tienda);
    String tiendaDel(String id);
    String getMessage();

}
