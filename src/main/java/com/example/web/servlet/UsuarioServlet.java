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
import java.util.List;

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
            String dni = request.getParameter("dni");

            if (contraseña == null || confirmContraseña == null || !contraseña.equals(confirmContraseña)) {
                request.setAttribute("mensajeError", "Las contraseñas no coinciden");
                target = "registro.jsp";
            } else {
                Usuario nuevoUsuario = new Usuario();
                nuevoUsuario.setNombre(nombre);
                nuevoUsuario.setApellidos(apellidos);
                nuevoUsuario.setEmail(email);
                nuevoUsuario.setDni(dni);

                String encryptedContraseña = AESUtil.encriptar(contraseña);
                nuevoUsuario.setContraseña(encryptedContraseña);

                if (daoUsuario.registrarUsuario(nuevoUsuario)) {
                    response.sendRedirect("login.jsp");
                    return;
                } else {
                    if (daoUsuario.getMensaje().equals("Ya existe un usuario con el mismo DNI")) {
                        request.setAttribute("mensajeError", "Ya existe un usuario con el mismo DNI");
                    } else {
                        request.setAttribute("mensajeError", "Error al registrar el usuario: " + daoUsuario.getMensaje());
                    }
                    target = "registro.jsp";
                }
            }
        } else if (accion.equals("GUARDAR_CAMBIOS")) {
            Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
            int idUsuario = usuario.getIdUsuario();

            String contraseniaActual = request.getParameter("contrasenia_actual");
            String nuevaContrasenia = request.getParameter("nueva_contrasenia");
            String confirmarNuevaContrasenia = request.getParameter("confirmar_nueva_contrasenia");

            String contraseniaGuardada = daoUsuario.obtenerContraseñaUsuario(idUsuario);

            if (nuevaContrasenia != null && !nuevaContrasenia.isEmpty() && nuevaContrasenia.equals(confirmarNuevaContrasenia)) {
                if (contraseniaActual != null && !contraseniaActual.isEmpty() && contraseniaGuardada != null && AESUtil.desencriptar(contraseniaGuardada).equals(AESUtil.encriptar(contraseniaActual))) {
                    String nuevaContraseniaEncriptada = AESUtil.encriptar(nuevaContrasenia);
                    if (daoUsuario.actualizarContraseñaUsuario(idUsuario, nuevaContraseniaEncriptada)) {
                        response.sendRedirect("EventoServlet?accion=MOSTRAR_DATOS_PRINCIPALES");
                        return;

                    } else {
                        request.setAttribute("mensajeError", "Error al actualizar la contraseña: " + daoUsuario.getMensaje());
                        request.getRequestDispatcher("perfil.jsp").forward(request, response);
                        return;

                    }

                } else {
                    request.setAttribute("mensajeError", "La contraseña actual es incorrecta");
                    request.getRequestDispatcher("perfil.jsp").forward(request, response);
                    return;

                }
            } else {
                request.setAttribute("mensajeError", "La nueva contraseña y la confirmación de la nueva contraseña no coinciden");
                request.getRequestDispatcher("perfil.jsp").forward(request, response);

            }

            Part filePart = request.getPart("imagen");
            InputStream inputStream = filePart.getInputStream();
            byte[] imagenBytes = inputStream.readAllBytes();

            if (imagenBytes.length > 0) {
                if (daoUsuario.actualizarImagenUsuario(idUsuario, imagenBytes)) {
                    usuario.setImagen(imagenBytes);
                    request.getSession().setAttribute("usuario", usuario);
                    response.sendRedirect("EventoServlet?accion=MOSTRAR_DATOS_PRINCIPALES");
                    return;
                } else {
                    request.setAttribute("mensajeError", "Error al actualizar la imagen: " + daoUsuario.getMensaje());
                    request.getRequestDispatcher("perfil.jsp").forward(request, response);
                    return;
                }
            } else {
                response.sendRedirect("EventoServlet?accion=MOSTRAR_DATOS_PRINCIPALES");
                return;
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
                    request.getSession().setAttribute("rol", usuario.getRol());
                    request.getSession().setAttribute("id_usuario", usuario.getIdUsuario());

                    boolean esAdmin = "admin".equals(usuario.getRol());
                    request.getSession().setAttribute("esAdmin", esAdmin);
                    target = "EventoServlet?accion=MOSTRAR_DATOS_PRINCIPALES";
                } else {
                    request.setAttribute("mensajeError", "Contraseña incorrecta");
                }
            } else {
                request.setAttribute("mensajeError", "Email no registrado");
            }
        } else if (accion.equals("ADMINISTRAR")) {
            List<Usuario> listaUsuarios = daoUsuario.obtenerTodosLosUsuarios();
            request.setAttribute("listaUsuarios", listaUsuarios);
            target = "administrar.jsp";
        } else if (accion.equals("EDITAR")) {
            int idUsuario = Integer.parseInt(request.getParameter("editarId"));
            String nuevoNombre = request.getParameter("editarNombre");
            String nuevoApellidos = request.getParameter("editarApellidos");
            String nuevoEmail = request.getParameter("editarEmail");
            String nuevoRol = request.getParameter("editarRol");
            String nuevoDni = request.getParameter("editarDni");

            Usuario usuarioActualizado = new Usuario();
            usuarioActualizado.setIdUsuario(idUsuario);
            usuarioActualizado.setNombre(nuevoNombre);
            usuarioActualizado.setApellidos(nuevoApellidos);
            usuarioActualizado.setEmail(nuevoEmail);
            usuarioActualizado.setRol(nuevoRol);
            usuarioActualizado.setDni(nuevoDni);

            if (daoUsuario.actualizarUsuario(usuarioActualizado)) {
                response.sendRedirect("UsuarioServlet?accion=ADMINISTRAR");
                return;
            } else {
                request.setAttribute("mensajeError", "Error al actualizar el usuario: " + daoUsuario.getMensaje());
                target = "administrar.jsp";
            }
        } else if (accion.equals("ELIMINAR")) {
            int idUsuarioEliminar = Integer.parseInt(request.getParameter("id"));

            if (daoUsuario.eliminarUsuario(idUsuarioEliminar)) {
                response.sendRedirect("UsuarioServlet?accion=ADMINISTRAR");
                return;
            } else {
                request.setAttribute("mensajeError", "Error al eliminar el usuario: " + daoUsuario.getMensaje());
                target = "administrar.jsp";
            }
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
