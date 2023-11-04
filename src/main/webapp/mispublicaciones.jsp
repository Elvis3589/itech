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

        <div class="publicacion">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Michael Rojas</h5>
                    <hr/>
                    <p class="card-text">¡Hola a todos los amantes del aprendizaje y la exploración académica! Hoy quiero compartir con ustedes mi emocionante experiencia en la universidad. Como estudiante universitario, he tenido la oportunidad de sumergirme en un mundo de conocimiento sin fin y descubrir nuevas pasiones y perspectivas. 

                        En mi último semestre, me inscribí en un curso de matematica II, y debo decir que ha sido una experiencia transformadora. Las discusiones en clase, las investigaciones y los proyectos me han desafiado a pensar de manera crítica y a profundizar en temas que nunca antes había considerado.</p>
                    <p class="card-text"><small class="text-body-secondary">14-11-2024</small></p>
                </div>

                <img src="img/pub/experiencia.jpg" class="card-img-bottom" alt="Contenido de la publicación">

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
        <div class="publicacion">

            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Michael Rojas</h5>
                    <hr/>
                    <p class="card-text">Hola a todos los universitarios comprometidos con la igualdad y la diversidad en la educación.Hoy quiero hablar sobre un tema que considero esencial en nuestras instituciones académicas.

                        La diversidad no solo se trata de diferentes orígenes étnicos o culturales, sino también de diversidad de perspectivas, experiencias y pensamientos. Cuando nuestros campus reflejan una amplia gama de identidades y voces, enriquecemos nuestro entorno de aprendizaje y preparamos a futuros líderes para un mundo diverso y globalizado.

                        Hoy, quiero alentar a todos a reflexionar sobre cómo podemos contribuir a la inclusión y la diversidad en nuestras universidades. ¿Qué medidas están tomando en sus campus para promover la igualdad y la comprensión intercultural? Compartan sus ideas y experiencias.</p>
                    <p class="card-text"><small class="text-body-secondary">03-10-2024</small></p>
                </div>

                <img src="img/pub/diversidad.jpg" class="card-img-bottom" alt="Contenido de la publicación">

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
                                <h6 class="comment-username">Joseph Sanchez</h6>
                            </div>
                            <div class "comment-body">
                                <p class="comment-text">¡Totalmente de acuerdo! La diversidad en la educación es esencial. En mi universidad, hemos estado promoviendo la inclusión a través de grupos estudiantiles y eventos que celebran diferentes culturas y perspectivas. También es importante que los programas académicos incluyan lecturas y discusiones que aborden cuestiones de diversidad y justicia social. Esto nos ayuda a comprender mejor el mundo que nos rodea y a prepararnos para ser ciudadanos globales.

                                    Siéntete libre de personalizar este comentario o agregar más respuestas para fomentar la conversación en tu red social.</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
