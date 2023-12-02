/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.example.web.servlet;

import com.example.dao.DaoDetallesTienda;
import com.example.dao.DaoTienda;
import com.example.dao.impl.DaoDetallesTiendaImpl;
import com.example.dao.impl.DaoTiendaImpl;
import com.example.entidades.DetallesTienda;
import com.example.entidades.Tienda;
import jakarta.servlet.annotation.MultipartConfig;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.util.ArrayList;

/**
 *
 * @author User
 */
@MultipartConfig
public class TiendaServlet extends HttpServlet {

    Tienda tie = new Tienda();
    DetallesTienda dt = new DetallesTienda();
    DaoTienda dao = new DaoTiendaImpl();
    DaoDetallesTienda daodt = new DaoDetallesTiendaImpl();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String accion = request.getParameter("accion");
        accion = (accion == null) ? "" : accion;
        String target = "";
        String mensaje = null;

        if (accion.equals("SEL")) {
            List<Tienda> lista = null;
            try {
                lista = dao.tiendaSel();
            } catch (Exception e) {
                mensaje = e.getMessage();
            }

            request.setAttribute("productos", lista);
            request.setAttribute("mensaje", mensaje);
            request.getRequestDispatcher("tienda.jsp").forward(request, response);

        } else if (accion.equals("REGISTRAR_TIENDA")) {
            String titulo = request.getParameter("texto_titulo");
            tie.setTitulo(titulo);
            dt.setProducto(titulo);
            dt.setTipo_venta(request.getParameter("texto_tipo"));
            dt.setCategoria(request.getParameter("texto_categoria"));
            Part filepart = request.getPart("archivo_imagen");
            dt.setCantidad(Integer.valueOf(request.getParameter("texto_cantidad")));
            dt.setDescripcion(request.getParameter("texto_descripcion"));
            tie.setPrecio(Float.valueOf(request.getParameter("texto_precio")));
            dt.setEstado(request.getParameter("texto_estado"));
            dt.setContacto(request.getParameter("texto_contacto"));
            tie.setId_usuario(Integer.valueOf(request.getParameter("id_usuario")));
            dt.setId_usuario(Integer.valueOf(request.getParameter("id_usuario")));
            byte[] imagenBytes = null;

            if (filepart != null && filepart.getSize() > 0) {
                try (InputStream input = filepart.getInputStream()) {
                    imagenBytes = input.readAllBytes();
                } catch (IOException e) {
                    System.out.println("Error al procesar la imagen: " + e);
                }
            }

            tie.setImagen(imagenBytes);

            List<Tienda> lista = new ArrayList<>();

            try {
                dao.tiendaIns(tie);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            try {
                Tienda producto = new Tienda();
                producto = dao.tiendaGet(titulo);
                dt.setId_tienda(producto.getId_tienda());
                daodt.detallesIns(dt);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            lista = dao.tiendaSel();

            request.setAttribute("mensaje", mensaje);
            request.setAttribute("productos", lista);
            request.getRequestDispatcher("tienda.jsp").forward(request, response);

        }
        request.getRequestDispatcher(target).forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
