<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Eventos Disponibles</title>
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

        <div class="container">
            <h1>Reserva algún evento de tu preferencia</h1>
            <div class="eventos-container">
                <div class="evento">
                    <div class="imagen-evento">
                        <img src="img/eve/cine.png" alt="Imagen del Evento">
                    </div>
                    <div class="informacion-evento">
                        <h2>Cine Arte: Festival Universitario</h2>
                        <p>Una celebración para dar la bienvenida a los estudiantes de primer año a la universidad. Incluye música en vivo, juegos, comida y una oportunidad para que los nuevos estudiantes conozcan a sus compañeros.</p>
                    </div>
                </div>

                <div class="evento">
                    <div class="imagen-evento">
                        <img src="img/eve/bienvenida.png" alt="Imagen del Evento">
                    </div>
                    <div class="informacion-evento">
                        <h2>Fiesta de Inicio: ¡Bienvenidos a la Familia Universitaria!</h2>
                        <p>Una celebración para dar la bienvenida a los estudiantes de primer año a la universidad. Incluye música en vivo, juegos, comida y una oportunidad para que los nuevos estudiantes conozcan a sus compañeros.</p>
                    </div>
                </div>
                <div class="evento">
                    <div class="imagen-evento">
                        <img src="img/eve/tecnologia.png" alt="Imagen del Evento">
                    </div>
                    <div class="informacion-evento">
                        <h2>Innovación en Acción: Feria Tecnológica Universitaria</h2>
                        <p>Una feria que muestra los proyectos de tecnología desarrollados por estudiantes, desde aplicaciones móviles hasta robótica avanzada. Los asistentes pueden interactuar con las demostraciones y aprender sobre las últimas tendencias tecnológicas.</p>
                    </div>
                </div>
                <div class="evento">
                    <div class="imagen-evento">
                        <img src="img/eve/empleo.png" alt="Imagen del Evento">
                    </div>
                    <div class="informacion-evento">
                        <h2>Feria de oportunidades laborales</h2>
                        <p>Un evento que conecta a estudiantes universitarios con empleadores y oportunidades profesionales. Los estudiantes pueden explorar opciones de carrera, presentar sus currículos y aprender sobre las demandas del mercado laboral</p>
                    </div>
                </div>
                <div class="evento">
                    <div class="imagen-evento">
                        <img src="img/eve/debate.png" alt="Imagen del Evento">
                    </div>
                    <div class="informacion-evento">
                        <h2>Torneo de Debate Universitario</h2>
                        <p>¡Prepárate para el intelecto en acción en nuestro "Torneo de Debate Universitario"! Los estudiantes universitarios se enfrentarán en debates fascinantes sobre temas de relevancia actual. Es una oportunidad única para presenciar argumentos persuasivos y pensamiento crítico en su máxima expresión. Únete a nosotros y sé parte de esta emocionante competencia intelectual. ¡No te lo pierdas!</p>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
