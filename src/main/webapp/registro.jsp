<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="WEB-INF/jspf/enlaces.jspf" %>

        <title>Registro</title>
    </head>
    <body>
        <div class="form-container">
            <div class="conta">
                <h2 class="login-form__title">Regístrate</h2>


                <form class="login-form" action="UsuarioServlet?accion=INS" method="post">
                    <div class="form-group">
                        <label for="nombre">Nombre</label>
                        <input type="text" id="nombre" name="nombre" required pattern="[A-Za-záéíóúÁÉÍÓÚñÑüÜ\s]+" title="Ingrese solo letras">
                    </div>
                    <div class="form-group">
                        <label for="apellido">Apellido</label>
                        <input type="text" id="apellido" name="apellidos" required pattern="[A-Za-záéíóúÁÉÍÓÚñÑüÜ\s]+" title="Ingrese solo letras">
                    </div>
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input type="email" id="email" name="email" required>
                    </div>
                    <div class="form-group">
                        <label for="dni">DNI</label>
                        <input type="text" id="dni" name="dni" required maxlength="8" pattern="[0-9]{8}" title="Ingrese solo números de 8 dígitos">
                    </div>
                    <div class="form-group">
                        <label for="password">Contraseña</label>
                        <input type="password" id="password" name="contrasenia" required>
                    </div>
                    <div class="form-group">
                        <label for="confirmPassword">Repetir Contraseña</label>
                        <input type="password" id="confirmPassword" name="confirmContrasenia" required>
                    </div>

                    <% String mensajeError = (String) request.getAttribute("mensajeError"); %>
                    <% if (mensajeError != null && !mensajeError.isEmpty()) {%>
                    <div class="alert alert-danger" role="alert">
                        <%= mensajeError%>
                    </div>
                    <% }%>

                    <button type="submit" class="btn btn-primary">Registrarse</button>
                </form>

                <p class="login-form__link">¿Ya tienes una cuenta? <a href="login.jsp" class="login-link">Iniciar sesión</a></p>
            </div>
        </div>
    </body>
</html>
