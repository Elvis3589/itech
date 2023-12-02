package com.example.web.servlet;

import com.example.dao.DaoComentarios;
import com.example.dao.impl.DaoComentariosImpl;
import com.example.entidades.Comentarios;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;

import java.io.IOException;
import java.sql.SQLException;

@MultipartConfig

public class ComentarioServlet extends HttpServlet {

    protected void processRequest(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response)
            throws jakarta.servlet.ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String accion = request.getParameter("accion");
        String id_usuario = request.getParameter("id");

        accion = (accion == null) ? "" : accion;
        String target = "";
        String mensaje = null;

        if (accion.equals("REGISTRAR_COMENTARIO")) {
            String contenido = request.getParameter("texto_comentario");
            int idUsuario = Integer.parseInt(request.getParameter("id_usuario"));
            int idPublicacion = Integer.parseInt(request.getParameter("id_publicacion"));

            Comentarios comentario = new Comentarios();
            comentario.setContenido(contenido);
            comentario.setId_usuario(idUsuario);
            comentario.setId_publicacion(idPublicacion);

            DaoComentarios daoComentarios = new DaoComentariosImpl();
            try {
                daoComentarios.insertarComentario(comentario);
                response.sendRedirect("Publicacion?accion=SEL");
                return;
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendRedirect("error.jsp");
            }
        } else if (accion.equals("REGISTRAR_COMENTARIO2")) {
            String contenido = request.getParameter("texto_comentario");
            int idUsuario = Integer.parseInt(request.getParameter("id_usuario"));
            int idPublicacion = Integer.parseInt(request.getParameter("id_publicacion"));

            Comentarios comentario = new Comentarios();
            comentario.setContenido(contenido);
            comentario.setId_usuario(idUsuario);
            comentario.setId_publicacion(idPublicacion);

            DaoComentarios daoComentarios = new DaoComentariosImpl();
            try {
                daoComentarios.insertarComentario(comentario);
                response.sendRedirect("Publicacion?accion=SEL&id=" + idUsuario);
                return;
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendRedirect("error.jsp");
            }
        }

    }

    @Override
    protected void doPost(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response)
            throws jakarta.servlet.ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response)
            throws jakarta.servlet.ServletException, IOException {
        processRequest(request, response);

    }
}
