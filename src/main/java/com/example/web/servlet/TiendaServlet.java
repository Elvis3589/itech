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
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.UUID;

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

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        String mensaje = null;

        switch (accion) {
            case "SEL":
                List<Tienda> lista = null;
                try {
                    lista = dao.tiendaSel();
                } catch (Exception e) {
                    mensaje = e.getMessage();
                }

                request.setAttribute("productos", lista);
                request.setAttribute("mensaje", mensaje);
                request.getRequestDispatcher("tienda.jsp").forward(request, response);
                break;
            default:
                throw new AssertionError();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String mensaje = null;
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
        String imagen = null;

        // Procesar imagen o multimedia
        if (filepart != null && filepart.getSize() > 0) {
            try {
                String fileName = Paths.get(filepart.getSubmittedFileName()).getFileName().toString();
                String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);

                String uniqueFileName = UUID.randomUUID().toString() + "." + fileExtension;

                // Especifica la ruta completa donde deseas guardar las imágenes
                String uploadPath = getServletContext().getRealPath("/") + "uploads";

                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                imagen = "uploads" + File.separator + uniqueFileName; // Guardar dirección en variable imagen
                // Guardar la imagen en la carpeta uploads
                String filePath = uploadPath + File.separator + uniqueFileName;
                try (InputStream input = filepart.getInputStream(); OutputStream output = new FileOutputStream(filePath)) {
                    int bytesRead;
                    byte[] buffer = new byte[4096];
                    while ((bytesRead = input.read(buffer)) != -1) {
                        output.write(buffer, 0, bytesRead);
                    }
                }
            } catch (IOException e) {
                System.out.println("Error publicacionServ" + e);
            }
        }
        
        tie.setImagen(imagen);
        
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

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
