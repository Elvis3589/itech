<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Perfil</title>
        <link rel="stylesheet" type="text/css" href="perfil.css">
    </head>
    <body>
        <%@include file="WEB-INF/jspf/enlaces.jspf" %>
        <%@include file="WEB-INF/jspf/encabezado.jspf" %>
        <div class="hero-image">
            <div class="hero-overlay"></div>
            <h1 class="hero-title">Editar Perfil</h1>
            <p class="hero-subtitle">"¡Bienvenido a la sección de 'Editar perfil'! Aquí puedes realizar cambios en tu información personal, modificar tu contraseña y personalizar tu perfil subiendo una imagen de usuario."</p>
        </div>
        <form action="guardar_perfil.jsp" method="post" enctype="multipart/form-data">
            <h1 class="form-heading">Formulario de datos personales</h1>
            <label for="nombre">Nombre:</label>
            <input type="text" id="nombre" name="nombre" required>

            <label for="apellidos">Apellidos:</label>
            <input type="text" id="apellidos" name="apellidos" required>

            <label for="correo">Correo:</label>
            <input type="email" id="correo" name="correo" required>

            <label for="password">Contraseña:</label>
            <input type="password" id="password" name="password" required>

            <label for="confirm_password">Repetir Contraseña:</label>
            <input type="password" id="confirm_password" name="confirm_password" required>

            <label for="imagen">Subir imagen:</label>
            <input type="file" id="imagen" name="imagen" accept="image/*">

            <input type="submit" value="Guardar Cambios">
        </form>
    </body>
</html>

