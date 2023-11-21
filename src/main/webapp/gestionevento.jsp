<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                <div class="form-group">
                    <label for="nombre">Nombre:</label>
                    <input type="text" name="nombre" required>
                </div>

                <div class="form-group">
                    <label for="apellido">Apellido:</label>
                    <input type="text" name="apellido" required>
                </div>

                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" name="email" required>
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
                    <input type="number" name="maxCantidad" required>
                </div>

                <div class="form-group">
                    <label for="imagen">Imagen del Evento:</label>
                    <input type="file" name="imagen" required>
                </div>

                <div class="form-group">
                    <button type="submit" class="submit-button">Registrar Evento</button>
                </div>
            </form>
        </div>
    </body>
</html>
