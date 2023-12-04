<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Mis Publicaciones</title>
        <link rel="stylesheet" type="text/css" href="styles.css">
    </head>
    <body>
        <%@include file="WEB-INF/jspf/enlaces.jspf" %>
        <%@include file="WEB-INF/jspf/encabezado.jspf" %>
        <h1 class="titulo">Mis Publicaciones</h1> 
        <c:forEach var="publicacion" items="${publicaciones}">
            <div class="publicacion">
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
                    <form action="ComentarioServlet?accion=REGISTRAR_COMENTARIO2" method="POST">
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
                                </div>
                            </div>
                        </c:forEach>
                    </div>

                </div>          
            </div>
        </c:forEach>
    </body>
</html>
