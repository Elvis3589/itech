<%@page import="java.util.Base64"%>
<%@page import="java.util.List"%>
<%@page import="com.example.entidades.Eventos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Eventos reservados</title>
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
        <div class="menu-container2">
            <h2>Estado del Evento</h2>
            <ul class="menu2">
                <li><a class="menu2-link activos" href="miseventos1.jsp">Activos</a></li>
                <li><a class="menu2-link reservados" href="miseventos2.jsp">Reservados</a></li>
            </ul>
            <ul class="menu2">
                <li><a class="menu2-link finalizados" href="miseventos3.jsp">Finalizados</a></li>
            </ul>
        </div>
        <div class="container">
            <h1>Eventos reservados en espera</h1>
            <div class="eventos-container">
                <% if (request.getAttribute("eventosReservados") != null) {
                List<Eventos> eventosReservados = (List<Eventos>) request.getAttribute("eventosReservados");
                for (Eventos evento : eventosReservados) {%>
                <div class="evento">
                    <div class="imagen-evento">
                        <a href="detallesevento.jsp?id=<%= evento.getIdEvento()%>&origen=miseventos2">
                            <img src="data:image/png;base64,<%= Base64.getEncoder().encodeToString(evento.getImagenEvento())%>" alt="Imagen del Evento">
                        </a>
                    </div>
                    <div class="informacion-evento">
                        <h2><%= evento.getNombreEvento()%></h2>
                        <p><%= evento.getDescripcion()%></p>
                    </div>
                </div>
                <% }
            } else { %>
                <p>No hay eventos reservados.</p>
                <% response.sendRedirect("EventoServlet?accion=MOSTRAR_EVENTOS_RESERVADOS"); %>
                <% }%>
            </div>
        </div>
    </body>
</html>
