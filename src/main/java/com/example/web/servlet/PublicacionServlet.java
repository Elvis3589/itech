package com.example.web.servlet;

import com.example.dao.DaoPublicacion;
import com.example.dao.impl.DaoPublicacionImpl;
import com.example.entidades.Publicacion;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 *
 * @author User
 */
@MultipartConfig
public class PublicacionServlet extends HttpServlet {

    Publicacion pub = new Publicacion();
    DaoPublicacion dao = new DaoPublicacionImpl();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        String id_usuario = request.getParameter("id");
        String mensaje = null;

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

        switch (accion) {
            case "INS":
                //Captura datos de formulario
                pub.setDescripcion(request.getParameter("texto_descripcion"));
                Part filepart = request.getPart("archivo_imagen");
                pub.setId_usuario(Integer.valueOf(request.getParameter("id_usuario")));
                String imagen = null;
                //Captura fecha local
                LocalDate fechaLocal = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                String fecha = fechaLocal.format(formatter);
                pub.setFecha(fecha);

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

                pub.setContenido(imagen);
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
    }// </editor-fold>

}
