package com.example.web.servlet;

import com.example.config.AESUtil;
import com.example.dao.DaoUsuario;
import com.example.dao.impl.DaoUsuarioImpl;
import com.example.entidades.Usuario;
import jakarta.servlet.annotation.MultipartConfig;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;

@WebServlet(name = "UsuarioServlet", urlPatterns = {"/UsuarioServlet"})
@MultipartConfig

public class UsuarioServlet extends HttpServlet {

    private final DaoUsuario daoUsuario = new DaoUsuarioImpl();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String accion = request.getParameter("accion");
        accion = (accion == null) ? "" : accion;
        String mensaje = null;
        String target = "";

        if (accion.equals("INS")) {
            String nombre = request.getParameter("nombre");
            String apellidos = request.getParameter("apellidos");
            String email = request.getParameter("email");
            String contraseña = request.getParameter("contrasenia");
            String confirmContraseña = request.getParameter("confirmContrasenia");

            if (contraseña == null || confirmContraseña == null || !contraseña.equals(confirmContraseña)) {
                request.setAttribute("mensajeError", "Las contraseñas no coinciden");
                target = "registro.jsp";
            } else {
                Usuario nuevoUsuario = new Usuario();
                nuevoUsuario.setNombre(nombre);
                nuevoUsuario.setApellidos(apellidos);
                nuevoUsuario.setEmail(email);

                String encryptedContraseña = AESUtil.encriptar(contraseña);
                nuevoUsuario.setContraseña(encryptedContraseña);

                if (daoUsuario.registrarUsuario(nuevoUsuario)) {
                    response.sendRedirect("login.jsp");
                    return;
                } else {
                    request.setAttribute("mensajeError", "Error al registrar el usuario: " + daoUsuario.getMensaje());
                    target = "registro.jsp";
                }
            }
        } else if (accion.equals("GUARDAR_CAMBIOS")) {
            Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
            int idUsuario = usuario.getIdUsuario();

            Part filePart = request.getPart("imagen");
            InputStream inputStream = filePart.getInputStream();
            byte[] imagenBytes = inputStream.readAllBytes();

            if (daoUsuario.actualizarImagenUsuario(idUsuario, imagenBytes)) {
                usuario.setImagen(imagenBytes);
                request.getSession().setAttribute("usuario", usuario);

                response.sendRedirect("index.jsp");
                return;
            } else {
                request.setAttribute("mensajeError", "Error al actualizar la imagen: " + daoUsuario.getMensaje());
                target = "perfil.jsp";
            }

        } else if (accion.equals("ACCEDER")) {
            String email = request.getParameter("email");
            String contraseña = request.getParameter("contrasenia");

            Usuario usuario = daoUsuario.obtenerUsuarioPorEmail(email);
            if (usuario != null) {
                String decryptedContraseña = AESUtil.desencriptar(usuario.getContraseña());

                String encryptedContraseña = AESUtil.encriptar(contraseña);

                if (decryptedContraseña != null && decryptedContraseña.equals(encryptedContraseña)) {
                    request.getSession().setAttribute("usuario", usuario);
                    request.getSession().setAttribute("nombreUsuario", usuario.getNombre());

                    target = "index.jsp";
                } else {
                    request.setAttribute("mensajeError", "Contraseña incorrecta");
                }
            } else {
                System.out.println("Usuario no encontrado. Permaneciendo en login.jsp.");
            }

            request.getRequestDispatcher(target).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
