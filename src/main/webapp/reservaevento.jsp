<%@page import="java.util.Base64"%>
<%@page import="java.util.List"%>
<%@page import="com.example.entidades.Eventos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Eventos Disponibles</title>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/enlaces.jspf" %>
        <%@include file="WEB-INF/jspf/encabezado.jspf" %>
        <div class="menu-container">
            <h2>Interacción de Eventos</h2>
            <ul class="menu">
                <li><a class="menu-link gestion-evento" href="gestionevento.jsp">Gestionar evento</a></li>
                <li><a class="menu-link reservar-eventos" href="reservaevento.jsp">Reservar eventos</a></li>
                <li><a class="menu-link mis-eventos" href="miseventos1.jsp">Mis eventos</a></li>
            </ul>
        </div>
        <div class="container">
            <h1>Reserva algún evento de tu preferencia</h1>
            <div class="eventos-container">
                <%
                    List<Eventos> eventos = (List<Eventos>) request.getAttribute("eventos");
                    if (eventos == null) {
                        response.sendRedirect("EventoServlet?accion=MOSTRAR_EVENTOS");
                    } else {
                        for (Eventos evento : eventos) {
                %>
                <div class="evento">
                    <div class="imagen-evento">
                        <img src="data:image/png;base64,<%= Base64.getEncoder().encodeToString(evento.getImagenEvento())%>" alt="Imagen del Evento">
                    </div>
                    <div class="informacion-evento">
                        <h2><%= evento.getNombreEvento()%></h2>
                        <p><%= evento.getDescripcion()%></p>
                    </div>
                </div>
                <%
                        }
                    }
                %>
            </div>
        </div>
    </body>
</html>
