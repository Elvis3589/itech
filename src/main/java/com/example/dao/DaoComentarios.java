package com.example.dao;

import com.example.entidades.Comentarios;
import java.util.List;

public interface DaoComentarios {
    List<Comentarios> comentariosSel();
    String comentariosIns(Comentarios comentarios);
    String comentariosUpd(Comentarios comentarios);
    String comentariosDel(String id);
    String getMessage();

}
