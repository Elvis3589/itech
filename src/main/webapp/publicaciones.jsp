<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                    <img src="img/imguser/perfil1.jpg" class="rounded-image" alt="Profile Image">
                    <h5 style="text-align: center; font-size: 20px">Michael Rojas</h5>
                    <p class="card-text" style="text-align: center; margin-bottom: 40px;">Sleandroteza@gmail.com</p>
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
                        <div class="modal-header">
                            <h1 class="modal-title">Escribe tu Publicación</h1>
                        </div>
                        <div class="modal-body">
                            <div class="container-fluid">
                                <form action="publicacionServ" method="POST" enctype="multipart/form-data">
                                    <input type="text" name="texto_usuario" style="display: none;">
                                    <div class="row">
                                        <div class="col-9">
                                            <textarea class="form-control" id="texto" name="texto_descripcion" rows="4" placeholder="Escribe aquí" required=""></textarea>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <label class="col-3 col-form-label">Selecciona una imagen</label>
                                        <div class="col-9">
                                            <input type="file" class="form-control" id="imagen" name="archivo_imagen" required="">
                                        </div>
                                    </div>

                                </form>
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
                    </div>
                </div>
            </div>
        </div>
        <div class="publications">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Roberth García</h5>
                    <hr/>
                    <p class="card-text">¡Hola a todos! Quiero compartir mi nueva estrategia para aprender matemáticas que me está funcionando muy bien. Estoy utilizando una aplicación de resolución de problemas de matemáticas que hace que el proceso sea mucho más interactivo y divertido. También estoy tomando apuntes con colores y estoy viendo videos explicativos en YouTube. ¿Alguna otra sugerencia para mejorar mis habilidades matemáticas? ¡Gracias de antemano! </p>
                    <p class="card-text"><small class="text-body-secondary">10-11-2023</small></p>
                </div>

                <img src="img/pub/matematica.jpg" class="card-img-bottom" alt="Contenido de la publicación">

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
                                <h6 class="comment-username">Carlos Rodriguez</h6>
                            </div>
                            <div class "comment-body">
                                <p class="comment-text">¡Eso suena genial! Yo también soy un amante de las matemáticas, y me encanta experimentar con diferentes métodos de estudio. Te recomendaría que intentes trabajar en problemas prácticos y desafiantes para poner a prueba tus habilidades. También, unirte a un grupo de estudio o un club de matemáticas puede ser una excelente manera de colaborar con otros estudiantes apasionados por la materia. ¡Sigue así, estás en el camino correcto para dominar las matemáticas!</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
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
