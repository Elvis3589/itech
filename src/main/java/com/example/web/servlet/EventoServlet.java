package com.example.web.servlet;

import com.example.dao.DaoEvento;
import com.example.dao.DaoPremium;
import com.example.dao.DaoTienda;
import com.example.dao.impl.DaoEventoImpl;
import com.example.dao.impl.DaoPremiumImpl;
import com.example.dao.impl.DaoTiendaImpl;
import com.example.entidades.Eventos;
import com.example.entidades.Tienda;
import com.example.entidades.Usuario;

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
import java.util.List;

@WebServlet(name = "EventoServlet", urlPatterns = {"/EventoServlet"})
@MultipartConfig
public class EventoServlet extends HttpServlet {

    private final DaoEvento daoEvento = new DaoEventoImpl();
    private final DaoPremium daoPremium = new DaoPremiumImpl();
    private final DaoTienda daoTienda = new DaoTiendaImpl();

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
            Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

            boolean tienePremium = false;
            if (usuario != null) {
                int idUsuario = usuario.getIdUsuario();
                tienePremium = daoPremium.tieneSuscripcionPremium(idUsuario);
            }

            int maxCantidadAsistentes = tienePremium ? 50 : 30;

            int cantidadAsistentes = Integer.parseInt(request.getParameter("maxCantidad"));
            if (cantidadAsistentes > maxCantidadAsistentes) {
                request.setAttribute("mensajeError", "La cantidad de asistentes excede el límite permitido.");
                target = "gestionevento.jsp";
            } else {
                if (usuario != null) {
                    tienePremium = daoPremium.tieneSuscripcionPremium(usuario.getIdUsuario());
                    maxCantidadAsistentes = tienePremium ? 50 : 30;

                    if (cantidadAsistentes > maxCantidadAsistentes) {
                        request.setAttribute("mensajeError", "La cantidad de asistentes excede el límite permitido.");
                        request.getRequestDispatcher("error.jsp").forward(request, response);
                        return;
                    }
                }
                int idUsuario = usuario.getIdUsuario();

                Eventos nuevoEvento = new Eventos(
                        0,
                        idUsuario,
                        nombre,
                        apellidos,
                        email,
                        nombreEvento,
                        lugar,
                        hora,
                        fecha,
                        celular,
                        descripcion,
                        cantidadAsistentes,
                        imagenBytes
                );

                if (daoEvento.registrarEvento(nuevoEvento)) {
                    request.setAttribute("mensajeExito", "Evento registrado exitosamente.");
                    request.getRequestDispatcher("gestionevento.jsp").forward(request, response);
                    return;
                } else {
                    request.setAttribute("mensajeError", "Error al registrar el evento");
                    target = "gestionevento.jsp";
                }

            }

        } else if (accion.equals("MOSTRAR_EVENTOS")) {
            List<Eventos> eventos = daoEvento.obtenerEventos();
            request.setAttribute("eventos", eventos);
            target = "reservaevento.jsp";

        } else if (accion.equals("MOSTRAR_DATOS_PRINCIPALES")) {
            List<Eventos> eventosP = daoEvento.obtenerEventosPremium();
            List<Tienda> materialesDestacados = daoTienda.obtenerMaterialesDestacadosPremium();

            request.setAttribute("eventosP", eventosP);
            request.setAttribute("materialesDestacados", materialesDestacados);

            target = "index.jsp";
        } else if (accion.equals("MOSTRAR_EVENTOS_ACTIVOS")) {
            Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

            if (usuario != null) {
                int idUsuario = usuario.getIdUsuario();

                List<Eventos> eventosActivos = daoEvento.obtenerEventosActivosPorUsuario(idUsuario);

                request.setAttribute("eventosActivos", eventosActivos);

                target = "miseventos1.jsp";
            } else {
                request.setAttribute("mensajeError", "Usuario no encontrado en la sesión");
            }
        } else if (accion.equals("RESERVAR_EVENTO")) {
            int idEvento = Integer.parseInt(request.getParameter("idEvento"));
            Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

            if (usuario != null) {
                int idUsuario = usuario.getIdUsuario();

                if (daoEvento.haReservadoEvento(idEvento, idUsuario)) {
                    request.setAttribute("mensajeError", "Ya has reservado este evento antes.");
                    target = "detallesevento.jsp?id=" + idEvento;
                } else {
                    if (daoEvento.registrarReserva(idEvento, idUsuario)) {
                        request.setAttribute("mensajeExito", "Evento reservado exitosamente.");
                        request.getRequestDispatcher("detallesevento.jsp?id=" + idEvento).forward(request, response);
                        return;
                    } else {
                        request.setAttribute("mensajeError", "Error al registrar la reserva del evento");
                        target = "detallesevento.jsp?id=" + idEvento;
                    }
                }
            } else {
                request.setAttribute("mensajeError", "Usuario no encontrado en la sesión");
                target = "detallesevento.jsp?id=" + idEvento;
            }
        } else if (accion.equals("MOSTRAR_EVENTOS_RESERVADOS")) {
            Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

            if (usuario != null) {
                int idUsuario = usuario.getIdUsuario();

                List<Eventos> eventosReservados = daoEvento.obtenerEventosReservadosPorUsuario(idUsuario);

                request.setAttribute("eventosReservados", eventosReservados);

                target = "miseventos2.jsp";
            } else {
                request.setAttribute("mensajeError", "Usuario no encontrado en la sesión");
            }
        } else if (accion.equals("MOSTRAR_EVENTOS_FINALIZADOS")) {
            Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

            if (usuario != null) {
                int idUsuario = usuario.getIdUsuario();

                List<Eventos> eventosFinalizados = daoEvento.obtenerEventosFinalizadosPorUsuario(idUsuario);

                request.setAttribute("eventosFinalizados", eventosFinalizados);

                target = "miseventos3.jsp";
            } else {
                request.setAttribute("mensajeError", "Usuario no encontrado en la sesión");
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
