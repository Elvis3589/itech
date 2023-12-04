<%@page import="com.example.dao.impl.DaoPremiumImpl"%>
<%@page import="com.example.dao.DaoPremium"%>
<%@page import="com.example.entidades.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>



<%
    DaoPremium daoPremium = new DaoPremiumImpl();

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrar Evento Universitario</title>
        <link rel="stylesheet" type="text/css" href="styles.css">

        <script type="text/javascript">
            function confirmarRegistro() {
                var formulario = document.getElementById("formularioRegistro");

                if (!formulario.checkValidity()) {
                    return false;
                }

                var respuesta = confirm("¿Estás seguro de que deseas registrar este evento?");
                return respuesta;
            }
        </script>

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
            <form id="formularioRegistro" method="post" action="EventoServlet?accion=REGISTRAR_EVENTO" class="event-form" enctype="multipart/form-data" onsubmit="return confirmarRegistro()">
                <%
                    Usuario usuario = (Usuario) session.getAttribute("usuario");

                    if (usuario != null) {
                %>

                <% String mensajeError = (String) request.getAttribute("mensajeError"); %>
                <% if (mensajeError != null && !mensajeError.isEmpty()) {%>
                <div class="alert alert-danger" role="alert">
                    <%= mensajeError%>
                </div>
                <% } %>

                <% String mensajeExito = (String) request.getAttribute("mensajeExito");
                    if (mensajeExito != null && !mensajeExito.isEmpty()) {%>
                <div class="alert alert-success" role="alert">
                    <%= mensajeExito%>
                </div>
                <% }%>

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
                    <input type="text" name="hora" required maxlength="7">
                </div>

                <div class="form-group">
                    <label for="fecha">Fecha:</label>
                    <input type="date" name="fecha" required>
                </div>

                <div class="form-group">
                    <label for="celular">Nº de Celular:</label>
                    <input type="text" name="celular" required maxlength="9" pattern="\d{9}" title="Ingrese solo números de 9 dígitos">
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
                           <%
                               if (usuario != null) {
                                   boolean tienePremium = daoPremium.tieneSuscripcionPremium(usuario.getIdUsuario());
                                   int maxCantidadAsistentes = tienePremium ? 50 : 30;
                                   out.print("max=\"" + maxCantidadAsistentes + "\"");
                               }
                           %>
                           maxlength="2" pattern="[0-9]{2}" title="Ingrese una cantidad valida">
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
                    <label for="imagen">Imagen del Evento:</label>
                    <input type="file" name="imagen" required>
                </div>

                <div class="form-group">
                    <button type="submit" class="submit-button">Registrar Evento</button>
                </div>
                <% } else { %>
                <p>Usuario no encontrado en la sesión. Inicie sesión para acceder a esta página.</p>
                <% }%>
            </form>
        </div>
    </body>
</html>