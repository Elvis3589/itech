<%@page import="com.example.dao.impl.DaoPremiumImpl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.Base64"%>
<%@page import="com.example.entidades.Usuario"%>
<%@page import="com.example.entidades.Publicacion"%>
<%@page import="com.example.dao.DaoPremium"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%
    DaoPremium daoPremium = new DaoPremiumImpl();
%>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="UTF-8">
        <title>Publicaciones</title>
        <%@include file="WEB-INF/jspf/enlaces.jspf" %>
    </head>

    <body>
        <%@include file="WEB-INF/jspf/encabezado.jspf" %>
        <div class="container">
            <div class="profile-info">
                <div class="card">
                    <%
                        Usuario usuario = (Usuario) session.getAttribute("usuario");
                        byte[] imagen = (usuario != null && usuario.getImagen() != null) ? usuario.getImagen() : null;
                        boolean esPremium = (usuario != null && daoPremium.tieneSuscripcionPremium(usuario.getIdUsuario()));

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

                    <p class="card-text" style="text-align: center; margin-bottom: 40px;">
                        <% if (usuario != null) {%>
                        <%= "Rol:" + usuario.getRol()%>
                        <% } else { %>
                        Rol de Usuario
                        <% }%>
                    </p>
                    <% if (esPremium) { %>
                    <p class="card-text" style="text-align: center; margin-bottom: 40px; color: green; font-weight: bold;">
                        Usuario Premium
                    </p>
                    <% } else { %>
                    <p class="card-text" style="text-align: center; margin-bottom: 40px; color: red; font-weight: bold;">
                        Usuario no Premium
                    </p>
                    <% }%>


                    <div class="button-container">
                        <button type="button" class="btn btn-success" id="eventosButton">Eventos</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="write-publication">
            <div class="card">
                <div class="card-header">Publicar</div>
                <div class="card-body">
                    <button type="button" class="write-button" id="write-button">Escribe aquí tu publicación...</button>
                </div>
            </div>
            <div class="overlay" id="overlay"></div> 

            <div class="modal" id="exampleModal">
                <div class="modal-dialog modal-dialog-scrollable">
                    <div class="modal-content">
                        <form action="Publicacion" method="POST" enctype="multipart/form-data">
                            <div class="modal-header">
                                <h1 class="modal-title">Escribe tu Publicación</h1>
                            </div>
                            <div class="modal-body">
                                <div class="container-fluid">
                                    <div class="row">
                                        <div class="col-9">
                                            <textarea class="form-control" id="texto" name="texto_descripcion" rows="4" placeholder="Escribe aquí" required></textarea>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <label class="col-3 col-form-label">Selecciona una imagen</label>
                                        <div class="col-9">
                                            <input type="file" class="form-control" id="imagen" name="archivo_imagen" required>
                                        </div>
                                        <input type="hidden" name="id_usuario" value="${id_usuario}">
                                        <input type="hidden" name="accion" value="INS">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-12">
                                    <button type="submit" class="btn btn-primary">Publicar</button>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" id="close-modal">Cerrar</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>         

        <c:forEach var="publicacion" items="${publicaciones}">
            <div class="publications">   
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">${publicacion.getUsuario().getNombre()} ${publicacion.getUsuario().getApellidos()}</h5>
                        <hr/>
                        <p class="card-text">${publicacion.getDescripcion()}</p>
                        <p class="card-text"><small class="text-body-secondary">${publicacion.getFecha()}</small></p>
                    </div>
                    <c:if test="${not empty publicacion.getContenidoBase64()}">
                        <img src="data:image/jpeg;base64,${publicacion.getContenidoBase64()}" class="card-img-bottom" alt="Contenido de la publicación">
                    </c:if>
                </div> 

                <div class="card-body">
                    <h6 class="card-text" style=" font-size: 1.2rem;margin-bottom: 5px;">Comentarios</h6>
                    <hr/>
                    <form action="ComentarioServlet?accion=REGISTRAR_COMENTARIO" method="POST">
                        <input type="hidden" name="id_usuario" value="${id_usuario}">
                        <input type="hidden" name="id_publicacion" value="${publicacion.getId_publicacion()}">
                        <input class="comment-input" name="texto_comentario" type="text" placeholder="Escribe tu comentario..." required>
                        <button type="submit" class="comment-button" style="width: 15%;">Enviar</button> 
                    </form>

                    <div class="comment-container">
                        <c:set var="comentarios" value="${daoComentarios.obtenerComentariosPorPublicacion(publicacion.id_publicacion)}" />
                        <c:forEach var="comentario" items="${comentarios}">
                            <div class="comment">
                                <div class="comment-header">
                                    <h6 class="comment-username">${comentario.usuario.nombre} ${comentario.usuario.apellidos}</h6>
                                </div>
                                <div class="comment-body">
                                    <p class="comment-text">${comentario.contenido}</p>
                                    <p class="text-body-secondary">${comentario.fechaComentario}</p> 

                                </div>
                            </div>
                        </c:forEach>
                    </div>

                </div>          
            </div>
        </c:forEach>



        <script>
            const writeButton = document.getElementById('write-button');
            const modal = document.getElementById('exampleModal');
            const overlay = document.getElementById('overlay');
            const closeModal = document.getElementById('close-modal');

            writeButton.addEventListener('click', () => {
                modal.style.display = 'block';
                overlay.style.display = 'block';
            });

            closeModal.addEventListener('click', () => {
                modal.style.display = 'none';
                overlay.style.display = 'none';
            });
        </script>

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
