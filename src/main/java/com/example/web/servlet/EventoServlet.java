package com.example.web.servlet;

import com.example.dao.DaoEvento;
import com.example.dao.impl.DaoEventoImpl;
import com.example.entidades.Eventos;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;

@WebServlet(name = "EventoServlet", urlPatterns = {"/EventoServlet"})
@MultipartConfig
public class EventoServlet extends HttpServlet {

    private final DaoEvento daoEvento = new DaoEventoImpl();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String accion = request.getParameter("accion");
        accion = (accion == null) ? "" : accion;
        String target = "";

        if (accion.equals("REGISTRAR_EVENTO")) {
            String nombre = request.getParameter("nombre");
            String apellidos = request.getParameter("apellido");
            String email = request.getParameter("email");
            String nombreEvento = request.getParameter("nombreEvento");
            String lugar = request.getParameter("lugar");
            String hora = request.getParameter("hora");
            Date fecha = Date.valueOf(request.getParameter("fecha"));
            String celular = request.getParameter("celular");
            String descripcion = request.getParameter("descripcion");
            int maxCantidad = Integer.parseInt(request.getParameter("maxCantidad"));

            Part imagenPart = request.getPart("imagen");
            byte[] imagenBytes = obtenerBytesImagen(imagenPart);

            Eventos nuevoEvento = new Eventos(
                    0,
                    1,
                    nombre,
                    apellidos,
                    email,
                    nombreEvento,
                    lugar,
                    hora,
                    fecha,
                    celular,
                    descripcion,
                    maxCantidad,
                    imagenBytes
            );

            if (daoEvento.registrarEvento(nuevoEvento)) {
                response.sendRedirect("index.jsp");
                return;
            } else {
                request.setAttribute("mensajeError", "Error al registrar el evento");
                target = "gestionevento.jsp";
            }

        }

        request.getRequestDispatcher(target).forward(request, response);
    }

    private byte[] obtenerBytesImagen(Part imagenPart) throws IOException {
        try (InputStream inputStream = imagenPart.getInputStream()) {
            return inputStream.readAllBytes();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
