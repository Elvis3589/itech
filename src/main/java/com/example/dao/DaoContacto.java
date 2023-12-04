package com.example.dao;

import com.example.entidades.Contacto;
import java.util.List;

public interface DaoContacto {
    List<Contacto> obtenerContactos(int id_detalles_tienda);
    String registrarContacto(Contacto contacto);
    String getMessage();
    List<Contacto> obtenerContactos();
    void eliminarContacto(int idContacto);
    List<Contacto> obtenerContactosPorUsuario(int idUsuario);
}