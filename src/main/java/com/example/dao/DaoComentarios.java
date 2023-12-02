// En DaoComentarios.java
package com.example.dao;

import com.example.entidades.Comentarios;
import java.util.List;

public interface DaoComentarios {
    String agregarComentario(Comentarios comentario);
    String getMessage();
}
