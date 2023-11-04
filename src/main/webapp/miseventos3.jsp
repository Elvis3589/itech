<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Eventos finalizados</title>
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
        <div class="menu-container2">
            <h2>Estado del Evento</h2>
            <ul class="menu2">
                <li><a class="menu2-link activos" href="miseventos1.jsp">Activos</a></li>
                <li><a class="menu2-link reservados" href="miseventos2.jsp">Reservados</a></li>
            </ul>
            <ul class="menu2">
                <li><a class="menu2-link finalizados" href="miseventos3.jsp">Finalizados</a></li>
            </ul>
        </div>
        <div class="container">
            <h1>Eventos finalizados</h1>
            <div class="eventos-container">
                <div class="evento">
                    <div class="imagen-evento">
                        <img src="img/eve/finalizados/comida.jpg" alt="Imagen del Evento">
                    </div>
                    <div class="informacion-evento">
                        <h2>Degustacion de Comidas Exoticas</h2>
                        <p>Sumérgete en un viaje culinario único en nuestro evento de degustación de platos exóticos. En un ambiente encantadormente decorado, podrás saborear una variedad de delicias culinarias de todo el mundo, cuidadosamente preparadas por chefs expertos.</p>
                    </div>
                </div>

                <div class="evento">
                    <div class="imagen-evento">
                        <img src="img/eve/finalizados/concierto.jpg" alt="Imagen del Evento">
                    </div>
                    <div class="informacion-evento">
                        <h2>Concierto de Musicos Universitarios</h2>
                        <p>Únete a nosotros para una emocionante noche de música en vivo, presentada por talentosos estudiantes universitarios. Este concierto es una muestra del increíble talento musical que florece en nuestra comunidad académica.</p>
                    </div>
                </div>
                <div class="evento">
                    <div class="imagen-evento">
                        <img src="img/eve/finalizados/festival.jpg" alt="Imagen del Evento">
                    </div>
                    <div class="informacion-evento">
                        <h2>Festival de Danza</h2>
                        <p>Únete a nosotros en un emocionante "Festival de Danza". Disfruta de una tarde llena de gracia y talento mientras nuestros bailarines universitarios exhiben sus habilidades en una variedad de estilos de danza. Una experiencia única de arte y movimiento te espera. ¡No faltes!</p>
                    </div>
                </div>
                <div class="evento">
                    <div class="imagen-evento">
                        <img src="img/eve/finalizados/taller.jpeg" alt="Imagen del Evento">
                    </div>
                    <div class="informacion-evento">
                        <h2>Taller de Oritacion de Tesis</h2>
                        <p>¿Te sientes abrumado por la perspectiva de abordar tu tesis de grado? ¡No estás solo! El taller de orientación de tesis de la Universidad Lucho Carrión está diseñado para brindarte las herramientas y la orientación necesaria.  </p>
                    </div>
                </div>

            </div>
        </div>
        <button class="volver-atras" onclick="goBack()">Volver Atrás</button>
    </body>
</html>
<script>
    function goBack() {
        var lastVisitedPage = localStorage.getItem('lastVisitedPage');

        if (lastVisitedPage) {
            window.location.href = lastVisitedPage;
        } else {
            window.location.href = 'index.jsp';
        }
    }
</script>
