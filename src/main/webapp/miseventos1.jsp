<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Eventos creados</title>
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
            <h1>Eventos creados activos</h1>
            <div class="eventos-container">
                <div class="evento">
                    <div class="imagen-evento">
                        <img src="img/eve/creados/danza.jpg" alt="Imagen del Evento">
                    </div>
                    <div class="informacion-evento">
                        <h2>Festival de Danza</h2>
                        <p>Únete a nosotros en un emocionante "Festival de Danza". Disfruta de una tarde llena de gracia y talento mientras nuestros bailarines universitarios exhiben sus habilidades en una variedad de estilos de danza. Una experiencia única de arte y movimiento te espera. ¡No faltes!</p>
                    </div>
                </div>

                <div class="evento">
                    <div class="imagen-evento">
                        <img src="img/eve/creados/futbol.jpg" alt="Imagen del Evento">
                    </div>
                    <div class="informacion-evento">
                        <h2>Tarde de fútbol universitario</h2>
                        <p>Únete a nosotros para disfrutar de un emocionante partido de fútbol universitario en nuestro torneo anual. Una tarde llena de acción y entretenimiento deportivo te espera. ¡No te lo pierdas!</p>
                    </div>
                </div>
                <div class="evento">
                    <div class="imagen-evento">
                        <img src="img/eve/creados/salud.jpg" alt="Imagen del Evento">
                    </div>
                    <div class="informacion-evento">
                        <h2>Semana de Cuidado Mental: Rompiendo Estigmas</h2>
                        <p>Un evento dedicado a promover la conciencia y el cuidado de la salud mental entre los estudiantes universitarios. Ofrecemos talleres, charlas, actividades de relajación y recursos para ayudar a los estudiantes a manejar el estrés y la ansiedad.</p>
                    </div>
                </div>
                <div class="evento">
                    <div class="imagen-evento">
                        <img src="img/eve/creados/LimpiezaInicio.jpg" alt="Imagen del Evento">
                    </div>
                    <div class="informacion-evento">
                        <h2>Dia Univesitario Voluntario</h2>
                        <p>Te invitamos al "Día Universitario Voluntario", una jornada de limpieza y embellecimiento en la comunidad. Únete a otros estudiantes para contribuir al bienestar de nuestro entorno y crear un impacto positivo. Además de hacer una diferencia, tendrás la oportunidad de conocer a otros estudiantes comprometidos. ¡Participa y sé parte de esta jornada de servicio y solidaridad!</p>
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

