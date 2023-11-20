package com.example.itech2.web.servlet;

import com.example.itech2.dao.DaoUsuario;
import com.example.itech2.dao.impl.DaoUsuarioImpl;
import com.example.itech2.entidades.Usuario;
import org.mindrot.jbcrypt.BCrypt;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UsuarioServlet", urlPatterns = {"/UsuarioServlet"})
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

                String hashedContraseña = BCrypt.hashpw(contraseña, BCrypt.gensalt());
                nuevoUsuario.setContraseña(hashedContraseña);

                if (daoUsuario.registrarUsuario(nuevoUsuario)) {
                    response.sendRedirect("login.jsp");
                    return;
                } else {
                    request.setAttribute("mensajeError", "Error al registrar el usuario: " + daoUsuario.getMensaje());
                    target = "registro.jsp";
                }
            }

        } else if (accion.equals("ACCEDER")) {
            String email = request.getParameter("email");
            String contraseña = request.getParameter("password");

            Usuario usuario = daoUsuario.obtenerUsuarioPorEmail(email);

            if (usuario != null) {
                System.out.println("Contraseña en la base de datos: " + usuario.getContraseña());
            }

            if (usuario != null && BCrypt.checkpw(contraseña, usuario.getContraseña())) {
                request.getSession().setAttribute("usuario", usuario);
                response.sendRedirect("index.jsp");
                return;
            } else {
                request.setAttribute("mensajeError", "Email o contraseña incorrectos");
                target = "login.jsp";
            }
            if (!target.isEmpty()) {
                request.getRequestDispatcher(target).forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
