<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Eventos reservados</title>
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
            <h1>Eventos reservados en espera</h1>
            <div class="eventos-container">
                <div class="evento">
                    <div class="imagen-evento">
                        <img src="img/eve/reservados/ajedrez.jpg" alt="Imagen del Evento">
                    </div>
                    <div class="informacion-evento">
                        <h2>Concurso de Ajedrez</h2>
                        <p>En el emocionante mundo del ajedrez, te invitamos a participar en un torneo de alto nivel que se celebrará en nuestro moderno polideportivo. Experimenta la estrategia y la destreza mental en un ambiente competitivo y amigable. Este evento reúne a jugadores de todas las edades y niveles.</p>
                    </div>
                </div>

                <div class="evento">
                    <div class="imagen-evento">
                        <img src="img/eve/reservados/basquet.jpg" alt="Imagen del Evento">
                    </div>
                    <div class="informacion-evento">
                        <h2>Noche de basquet</h2>
                        <p>Únete a nosotros para disfrutar de un emocionante juego de basquet universitario. Una noche llena de acción y entretenimiento deportivo te espera. </p>
                    </div>
                </div>
                <div class="evento">
                    <div class="imagen-evento">
                        <img src="img/eve/reservados/voley.jpg" alt="Imagen del Evento">
                    </div>
                    <div class="informacion-evento">
                        <h2>Torneo Universitario de Voleibol</h2>
                        <p>Un emocionante torneo de voleibol universitario te espera. Únete a la diversión, la competencia y la emoción en el campus. ¡No te lo pierdas!</p>
                    </div>
                </div>
                <div class="evento">
                    <div class="imagen-evento">
                        <img src="img/eve/reservados/teatro.jpg" alt="Imagen del Evento">
                    </div>
                    <div class="informacion-evento">
                        <h2>"Chillay" Evento de Teatro</h2>
                        <p>¡Prepárate para una noche de emociones y drama en la Universidad Lucho Carrión! "Chillay" es un evento teatral excepcional que presenta un elenco talentoso de actores universitarios dispuestos a cautivar tu imaginación y emociones.</p>
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

