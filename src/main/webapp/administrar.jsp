<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.example.entidades.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="com.example.dao.DaoUsuario"%>
<%@page import="com.example.dao.impl.DaoUsuarioImpl"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administrar Usuarios</title>
        <script>
            function mostrarUsuario(id, nombre, apellidos, email, rol) {
                document.getElementById('editarId').value = id;
                document.getElementById('editarNombre').value = nombre;
                document.getElementById('editarApellidos').value = apellidos;
                document.getElementById('editarEmail').value = email;
                document.getElementById('editarRol').value = rol;
            }
        </script>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/enlaces.jspf" %>

        <h1>Administrar Usuarios</h1>

        <a href="index.jsp" class="volver-btn">Volver a Inicio</a>

        <table border="1">
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Apellidos</th>
                <th>Email</th>
                <th>Rol</th>
                <th>Acciones</th>
            </tr>

            <%
                DaoUsuario daoUsuario = new DaoUsuarioImpl();
                List<Usuario> usuarios = daoUsuario.obtenerTodosLosUsuarios();

                for (Usuario usuario : usuarios) {
            %>
            <tr>
                <td><%= usuario.getIdUsuario()%></td>
                <td><%= usuario.getNombre()%></td>
                <td><%= usuario.getApellidos()%></td>
                <td><%= usuario.getEmail()%></td>
                <td><%= usuario.getRol()%></td>
                <td>
                    <a href="#" onclick="mostrarUsuario(<%= usuario.getIdUsuario()%>, '<%= usuario.getNombre()%>', '<%= usuario.getApellidos()%>', '<%= usuario.getEmail()%>', '<%= usuario.getRol()%>')">Mostrar</a>
                    <a href="UsuarioServlet?accion=ELIMINAR&id=<%= usuario.getIdUsuario()%>">Eliminar</a>
                </td>
            </tr>
            <%
                }
            %>
        </table>

        <h2>Editar Usuario</h2>
        <form action="UsuarioServlet" method="post">
            <input type="hidden" name="accion" value="EDITAR">

            <table>
                <tr>
                    <td><label for="editarId">ID:</label></td>
                    <td><input type="text" id="editarId" name="editarId" readonly></td>
                    <td><label for="editarNombre">Nombre:</label></td>
                    <td><input type="text" id="editarNombre" name="editarNombre"></td>
                    <td><label for="editarApellidos">Apellidos:</label></td>
                    <td><input type="text" id="editarApellidos" name="editarApellidos"></td>
                    <td></td>
                </tr>

                <tr>
                    <td><label for="editarEmail">Email:</label></td>
                    <td><input type="text" id="editarEmail" name="editarEmail" readonly></td>
                    <td><label for="editarRol">Rol:</label></td>
                    <td><input type="text" id="editarRol" name="editarRol"></td>
                    <td colspan="2">
                        <button type="submit" style="margin-top: 0px; margin-left: 0px; width: 100px;">Editar</button>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
