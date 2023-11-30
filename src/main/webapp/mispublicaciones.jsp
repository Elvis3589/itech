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

                    <c:if test="${not empty publicacion.getContenido()}">
                        <img src="${publicacion.getContenido()}" class="card-img-bottom" alt="Contenido de la publicación">
                    </c:if>

                    <div class="card-body">
                        <h6 class="card-text" style=" font-size: 1.2rem;margin-bottom: 5px;">Comentarios</h6>
                        <hr/>
                        <form action="#" method="POST">
                            <input class="comment-input" name="texto_comentario" type="text" placeholder="Escribe tu comentario...">
                            <button type="submit" class="comment-button" style="width: 15%;">Enviar</button> 
                        </form>
                        <div class="comment-container">
                            <div class="comment">
                                <div class "comment-header">
                                    <h6 class="comment-username">Juan Revoredo</h6>
                                </div>
                                <div class "comment-body">
                                    <p class="comment-text">¡Hola! Estoy totalmente de acuerdo contigo. La universidad es un lugar increíble para explorar nuevas ideas y perspectivas. También estoy en mi último año y, al igual que tú, he tenido algunas experiencias académicas asombrosas. Mi curso de [nombre del curso] también ha sido un punto culminante para mí. Las discusiones en clase y las investigaciones me han desafiado de maneras que nunca imaginé. ¡Es genial poder aprender y crecer juntos en este viaje académico!</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </body>
</html>
