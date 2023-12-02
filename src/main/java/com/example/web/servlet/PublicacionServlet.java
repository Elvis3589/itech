/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.example.web.servlet;

import com.example.dao.DaoPublicacion;
import com.example.dao.impl.DaoPublicacionImpl;
import com.example.entidades.Publicacion;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author User
 */
@MultipartConfig
public class PublicacionServlet extends HttpServlet {

    Publicacion pub = new Publicacion();
    DaoPublicacion dao = new DaoPublicacionImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        String id_usuario = request.getParameter("id");
        String mensaje = null;
        request.setCharacterEncoding("UTF-8");

        switch (accion) {
            case "SEL":
                List<Publicacion> lista = null;
                if (id_usuario != null && !id_usuario.isEmpty()) {
                    try {
                        int id = Integer.parseInt(id_usuario);
                        lista = dao.publicacionGet(id);
                    } catch (NumberFormatException e) {
                        mensaje = e.getMessage();
                    }
                    request.setAttribute("mensaje", mensaje);
                    request.setAttribute("publicaciones", lista);
                    request.getRequestDispatcher("mispublicaciones.jsp").forward(request, response);
                } else {
                    try {
                        lista = dao.publicacionSel();
                    } catch (Exception e) {
                        mensaje = e.getMessage();
                    }

                    request.setAttribute("mensaje", mensaje);
                    request.setAttribute("publicaciones", lista);
                    request.getRequestDispatcher("publicaciones.jsp").forward(request, response);
                }

                break;
            default:
                throw new AssertionError();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        String mensaje = null;
        request.setCharacterEncoding("UTF-8");

        switch (accion) {
            case "INS":
                pub.setDescripcion(new String(request.getParameter("texto_descripcion").getBytes("ISO-8859-1"), "UTF-8"));
                Part filepart = request.getPart("archivo_imagen");
                pub.setId_usuario(Integer.valueOf(request.getParameter("id_usuario")));
                LocalDate fechaLocal = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                String fecha = fechaLocal.format(formatter);
                pub.setFecha(fecha);

                byte[] imagenBytes = null;
                if (filepart != null && filepart.getSize() > 0) {
                    try (InputStream input = filepart.getInputStream()) {
                        imagenBytes = input.readAllBytes();
                    } catch (IOException e) {
                        System.out.println("Error al procesar la imagen: " + e);
                    }
                }

                pub.setContenido(imagenBytes);

                List<Publicacion> lista = null;

                dao.publicacionIns(pub);
                lista = dao.publicacionSel();

                request.setAttribute("mensaje", mensaje);
                request.setAttribute("publicaciones", lista);
                request.getRequestDispatcher("publicaciones.jsp").forward(request, response);
                break;

            default:
                throw new AssertionError();
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
