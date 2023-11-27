<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.example.entidades.Usuario"%>
<%@page import="java.util.Base64"%>
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
        <form method="post" action="UsuarioServlet?accion=GUARDAR_CAMBIOS" enctype="multipart/form-data">
            <h1 class="form-heading">Formulario de datos personales</h1>
            <%
                Usuario usuario = (Usuario) session.getAttribute("usuario");

                if (usuario != null) {%>
            <label for="nombre">Nombre:</label>
            <input type="text" id="nombre" name="nombre" value="<%= usuario.getNombre()%>" required readonly>

            <label for="apellidos">Apellidos:</label>
            <input type="text" id="apellidos" name="apellidos" value="<%= usuario.getApellidos()%>" required readonly>

            <label for="correo">Correo:</label>
            <input type="email" id="correo" name="correo" value="<%= usuario.getEmail()%>" required readonly>

            <label for="imagen">Subir imagen:</label>
            <input type="file" id="imagen" name="imagen" accept="image/*" required>

            <input type="submit" value="Guardar Cambios">

            <div class="premium-button-container">
                <button type="button" class="button-premium" onclick="window.location.href = 'premium.jsp'">Obtener Premium</button>
            </div>

            <% } else { %>
            <% out.println("Usuario no encontrado en la sesión. Inicie sesión para acceder a esta página."); %>
            <% }%>
        </form>

    </body>
</html>
