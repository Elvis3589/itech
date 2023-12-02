package com.example.web.servlet;

import com.example.dao.DaoPremium;
import com.example.dao.impl.DaoPremiumImpl;
import com.example.entidades.Premium;
import com.example.entidades.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "PremiumServlet", urlPatterns = {"/PremiumServlet"})
public class PremiumServlet extends HttpServlet {

    private final DaoPremium daoPremium = new DaoPremiumImpl();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String accion = request.getParameter("accion");
        accion = (accion == null) ? "" : accion;

        if (accion.equals("INS")) {
            String nombreTarjeta = request.getParameter("nombre");
            String numeroTarjeta = request.getParameter("numeroTarjeta");
            String mesVencimiento = request.getParameter("mesVencimiento");
            String anoVencimiento = request.getParameter("anoVencimiento");
            String codigoSeguridad = request.getParameter("codigoSeguridad");

            Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

            if (daoPremium.tieneSuscripcionPremium(usuario.getIdUsuario())) {
                request.setAttribute("mensajeError", "Usted ya cuenta con una suscripción premium");
                request.getRequestDispatcher("premium.jsp").forward(request, response);
            } else {
                LocalDateTime fechaInicio = LocalDateTime.now();
                LocalDateTime fechaFin = fechaInicio.plusMonths(1);

                Premium premium = new Premium();
                premium.setIdUsuario(usuario.getIdUsuario());
                premium.setFechaInicio(fechaInicio);
                premium.setFechaFin(fechaFin);

                if (daoPremium.registrarSuscripcionPremium(premium)) {
                    response.sendRedirect("EventoServlet?accion=MOSTRAR_DATOS_PRINCIPALES");
                } else {
                    request.setAttribute("mensajeError", "Error al obtener la suscripción premium: " + daoPremium.getMensaje());
                    request.getRequestDispatcher("premium.jsp").forward(request, response);
                }
            }
        } else {

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
