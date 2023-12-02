<%@ page import="com.example.dao.DaoEvento" %>
<%@ page import="com.example.dao.impl.DaoEventoImpl" %>
<%@ page import="java.util.Base64" %>
<%@ page import="com.example.entidades.Eventos" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detalles del evento</title>


        <script type="text/javascript">
            function confirmarReserva() {
                var respuesta = confirm("¿Estás seguro de que deseas reservar este evento?");
                if (respuesta) {
                    document.getElementById("formularioReserva").submit();
                } else {
                }
            }
        </script>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/enlaces.jspf" %>
        <%@include file="WEB-INF/jspf/encabezado.jspf" %>
        <div class="container">
            <div class="custom-header"> 
                <h1>Detalles del Evento</h1>
            </div>

            <%
                int idEvento = Integer.parseInt(request.getParameter("id"));
                DaoEvento daoEvento = new DaoEventoImpl();

                Eventos evento = daoEvento.obtenerDetallesEvento(idEvento);
                String origen = request.getParameter("origen");
                boolean mostrarBotonReserva = !"miseventos1".equals(origen) && !"miseventos2".equals(origen) && !"miseventos3".equals(origen);
                if (evento != null) {
            %>
            <div class="product-details">
                <div class="product-image">
                    <img src="data:image/png;base64,<%= Base64.getEncoder().encodeToString(evento.getImagenEvento())%>" alt="Imagen del Evento">
                </div>

                <div class="product-info">
                    <div class="product-info-item">
                        <label>Nombre del evento:</label>
                        <span><%= evento.getNombreEvento()%></span>
                    </div>
                    <div class="product-info-item">
                        <label>Nombre:</label>
                        <span><%= evento.getNombre()%></span>
                    </div>
                    <div class="product-info-item">
                        <label>Apellido:</label>
                        <span><%= evento.getApellidos()%></span>
                    </div>
                    <div class="product-info-item">
                        <label>Correo:</label>
                        <span><%= evento.getEmail()%></span>
                    </div>
                    <div class="product-info-item">
                        <label>Lugar:</label>
                        <span><%= evento.getLugar()%></span>
                    </div>
                    <div class="product-info-item">
                        <label>Hora:</label>
                        <span><%= evento.getHora()%></span>
                    </div>
                    <div class="product-info-item">
                        <label>Fecha:</label>
                        <span><%= evento.getFecha()%></span>
                    </div>
                    <div class="product-info-item">
                        <label>Número de celular:</label>
                        <span><%= evento.getCelular()%></span>
                    </div>
                    <div class="product-info-item">
                        <label>Descripción del evento:</label>
                        <p><%= evento.getDescripcion()%></p>
                    </div>
                    <div class="product-info-item">
                        <label>Máxima cantidad de asistentes:</label>
                        <span><%= evento.getMaxCantidad()%></span>
                    </div>
                    <div class="product-info-item">
                        <label>Cantidad disponible:</label>
                        <span><%= evento.getMaxCantidad() - daoEvento.obtenerCantidadReservasPorEvento(evento.getIdEvento())%></span>
                    </div>

                </div>
            </div>
            <div class="buttons">
                <% if (mostrarBotonReserva) {%>
                <form id="formularioReserva" action="EventoServlet" method="post">
                    <input type="hidden" name="accion" value="RESERVAR_EVENTO">
                    <input type="hidden" name="idEvento" value="<%= evento.getIdEvento()%>">
                    <button class="contact-button" type="button" onclick="confirmarReserva()">Reservar evento</button>
                </form>
                <% } %>

                <button class="back-button" onclick="window.history.back()">Volver Atrás</button>
            </div>
            <%
            } else {
            %>
            <p>El evento no se encontró o ha sido eliminado.</p>
            <%
                }
            %>
        </div>
    </body>
</html>
<script type="text/javascript">
    function confirmarReserva() {
        var respuesta = confirm("¿Estás seguro de que deseas reservar este evento?");
        if (respuesta) {
            document.getElementById("formularioReserva").submit();
        } else {
        }
    }
</script>