package com.example.dao;

import com.example.entidades.Publicacion;
import java.util.List;

public interface DaoPublicacion {
    List<Publicacion> publicacionSel();
    List<Publicacion> publicacionGet(int id);
    String publicacionIns(Publicacion publicacion);
    String publicacionUpd(Publicacion publicacion, boolean actualizarImagen);
    String publicacionDel(String id);
    String getMessage();

}
