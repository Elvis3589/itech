<%@ page import="com.example.dao.DaoPremium" %>
<%@ page import="com.example.dao.impl.DaoPremiumImpl" %>
<%@ page import="com.example.entidades.Usuario" %>

<%@ page contentType="text/html; charset=UTF-8" %>


<%
    // Crear una instancia de DaoPremium
    DaoPremium daoPremium = new DaoPremiumImpl();

    // Resto del código
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrar Evento Universitario</title>
        <link rel="stylesheet" type="text/css" href="styles.css">
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
            <div class="header">
                <h1>Registrar Evento Universitario</h1>
            </div>
            <form method="post" action="EventoServlet?accion=REGISTRAR_EVENTO" class="event-form" enctype="multipart/form-data">
                <%
                    Usuario usuario = (Usuario) session.getAttribute("usuario");

                    if (usuario != null) {
                %>
                <div class="form-group">
                    <label for="nombre">Nombre:</label>
                    <input type="text" name="nombre" value="<%= usuario.getNombre()%>"  required readonly>
                </div>

                <div class="form-group">
                    <label for="apellido">Apellido:</label>
                    <input type="text" name="apellido" value="<%= usuario.getApellidos()%>" required readonly>
                </div>

                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" name="email" value="<%= usuario.getEmail()%>" required readonly>
                </div>

                <div class="form-group">
                    <label for="nombreEvento">Nombre del Evento:</label>
                    <input type="text" name="nombreEvento" required>
                </div>

                <div class="form-group">
                    <label for="lugar">Lugar:</label>
                    <input type="text" name="lugar" required>
                </div>

                <div class="form-group">
                    <label for="hora">Hora:</label>
                    <input type="text" name="hora" required>
                </div>

                <div class="form-group">
                    <label for="fecha">Fecha:</label>
                    <input type="date" name="fecha" required>
                </div>

                <div class="form-group">
                    <label for="celular">Nº de Celular:</label>
                    <input type="text" name="celular" required>
                </div>

                <div class="form-group">
                    <label for="descripcion">Descripción del Evento:</label>
                    <textarea name="descripcion" rows="4" required></textarea>
                </div>

                <div class="form-group">
                    <label for="maxCantidad">Máxima Cantidad de Asistentes:</label>
                    <input type="number" name="maxCantidad" required
                           <% if (request.getAttribute("maxCantidadAsistentes") != null) {
                                   out.print("max=\"" + request.getAttribute("maxCantidadAsistentes") + "\"");
                               } %>
                           >
                </div>

                <div class="form-group">
                    <label for="maxCantidad">Máxima Cantidad de Asistentes:</label>
                    <input type="number" name="maxCantidad" required
                           <% if (request.getAttribute("maxCantidadAsistentes") != null) {
                out.print("max=\"" + request.getAttribute("maxCantidadAsistentes") + "\"");
            } %>
                           >
                    <small class="form-text text-muted">
                        <%
                            if (usuario != null) {
                                boolean tienePremium = daoPremium.tieneSuscripcionPremium(usuario.getIdUsuario());
                                int maxCantidadAsistentes = tienePremium ? 50 : 30;
                                out.print("Puede registrar hasta " + maxCantidadAsistentes + " asistentes.");
                            }
                        %>
                    </small>
                </div>


                <div class="form-group">
                    <button type="submit" class="submit-button">Registrar Evento</button>
                </div>
                <%
                    } else {
                        out.println("Usuario no encontrado en la sesión. Inicie sesión para acceder a esta página.");
                    }
                %>
            </form>
        </div>
    </body>
</html>
