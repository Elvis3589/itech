<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="WEB-INF/jspf/enlaces.jspf" %>
        <title>Iniciar Sesión</title>
    </head>
    <body>
        <div class="form-container">
            <div class="login-info">
                <h2 class="login-form__title">Explora y Participa en Eventos Educativos</h2>
                <p class="login-form__description">
                    ¡Bienvenidos a nuestro mundo educativo en línea! Conéctate con estudiantes y profesores, aprende, comparte conocimientos y participa en eventos educativos y sociales. Además, puedes acceder a una tienda en línea para recursos educativos esenciales. Únete a nosotros y transforma tu experiencia educativa en un viaje interactivo e inspirador.
                </p>
            </div>

            <div class="conta">
                <div class="text-center">
                    <img width="190px" src="img/logo.png" alt="alt" />
                </div>
                <form method="post" class="login-form" action="index.jsp">
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input type="email" id="email" name="email" required>
                    </div>

                    <div class="form-group">
                        <label for="password">Contraseña</label>
                        <input type="password" id="password" name="password" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Iniciar sesión</button>
                </form>
                <p class="login-form__link">¿No tienes una cuenta? <a href="registro.jsp" class="register-link">Regístrate aquí</a></p>
            </div>
        </div>
    </body>
</html>