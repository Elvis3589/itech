<%@page import="java.util.List"%>
<%@page import="com.example.entidades.Eventos"%>
<%@page import="java.util.Base64"%>
<%@page import="com.example.entidades.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Página de Inicio</title>

    </head>
    <body>
        <%@include file="WEB-INF/jspf/enlaces.jspf" %>
        <%@include file="WEB-INF/jspf/encabezado.jspf" %>
        <section class="eventos-esperados section-right">
            <h2>Eventos más Esperados</h2>

            <div class="eventos-container">
                <%
                    List<Eventos> eventosP = (List<Eventos>) request.getAttribute("eventosP");
                    if (eventosP == null) {
                        response.sendRedirect("EventoServlet?accion=MOSTRAR_EVENTOS_PREMIUM");
                    } else {
                        for (Eventos evento : eventosP) {
                %>
                <div class="evento">
                    <div class="imagen-evento">
                        <a href="detallesevento.jsp?id=<%= evento.getIdEvento()%>">
                            <img src="data:image/png;base64,<%= Base64.getEncoder().encodeToString(evento.getImagenEvento())%>" alt="Imagen del Evento">
                            <p>   <%= evento.getNombreEvento()%></p>

                        </a>
                    </div>
                </div>
                <%
                        }
                    }
                %>
            </div>
        </section>
        <div class="profile-info1">
            <div class="card">
                <%
                    Usuario usuario = (Usuario) session.getAttribute("usuario");
                    byte[] imagen = (usuario != null && usuario.getImagen() != null) ? usuario.getImagen() : null;
                %>

                <% if (imagen != null) {%>
                <img src="data:image/jpeg;base64,<%= Base64.getEncoder().encodeToString(imagen)%>" class="rounded-image" alt="Profile Image">
                <% } %>

                <h5 style="text-align: center; font-size: 20px">
                    <% if (usuario != null) {%>
                    <%= usuario.getNombre() + " " + usuario.getApellidos()%>
                    <% } else { %>
                    Nombre de Usuario
                    <% } %>
                </h5>

                <p class="card-text" style="text-align: center; margin-bottom: 40px;">
                    <% if (usuario != null) {%>
                    <%= usuario.getEmail()%>
                    <% } else { %>
                    Correo de Usuario
                    <% }%>
                </p>

                <div class="button-container">
                    <button type="button" class="btn btn-success" id="eventosButton">Eventos</button>
                </div>
            </div>

        </div>
                

        <section class="material-destacados section-right">
            <h2>Materiales más Destacados</h2>
            <div class="material-container">
                <div class="material">
                    <a href="detallestienda.jsp">
                        <img src="img/mat/machine.jpg" alt="material 1">
                        <p>Curso Machine Learning</p>
                    </a>

                </div>
                <div class="material">
                    <a href="detallestienda.jsp">

                        <img src="img/mat/matematicas.png" alt="material 2">
                        <p>Libro matematicas</p>
                    </a>

                </div>
                <div class="material">
                    <a href="detallestienda.jsp">

                        <img src="img/mat/creaempresa.png" alt="material 3">
                        <p>Curso Crea tu empresa</p>
                    </a>

                </div>
                <div class="material">
                    <a href="detallestienda.jsp">

                        <img src="img/mat/claseprogramacion.jpg" alt="material 4">
                        <p>Videos clase Programacion</p>
                    </a>

                </div>

            </div>
        </section>

        <section class="galeria-imagenes section-right">
            <h2>Galería de Imágenes</h2>
            <div class="imagenes-container">
                <div class="imagen">
                    <img src="img/gal/gal1.jpg" alt="Imagen 1">
                </div>
                <div class="imagen">
                    <img src ="img/gal/gal2.jpg" alt="Imagen 2">
                </div>
                <div class="imagen">
                    <img src="img/gal/gal3.png" alt="Imagen 3">
                </div>
                <div class="imagen">
                    <img src="img/gal/gal4.png" alt="Imagen 4">
                </div>

            </div>
        </section>


    </body>
</html>
<script>
    document.getElementById("eventosButton").addEventListener("click", function () {
        window.location.href = "gestionevento.jsp";
    });
</script>
<script>
    localStorage.setItem('lastVisitedPage', window.location.href);
</script>
