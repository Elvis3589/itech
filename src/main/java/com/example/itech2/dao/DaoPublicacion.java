package com.example.itech2.dao;

import com.example.itech2.entidades.Publicacion;
import java.util.List;

public interface DaoPublicacion {
    List<Publicacion> publicacionSel(String contexto, String id_usuario);
    Publicacion publicacionGet(String id);
    String publicacionIns(Publicacion publicacion);
    String publicacionUpd(Publicacion publicacion);
    String publicacionDel(String id);
    String getMessage();

}
