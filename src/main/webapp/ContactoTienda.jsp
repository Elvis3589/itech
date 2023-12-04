<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.example.entidades.Contacto"%>
<%@page import="java.util.List"%>
<%@page import="com.example.dao.DaoContacto"%>
<%@page import="com.example.dao.impl.DaoContactoImpl"%>
<%@page import="com.example.entidades.DetallesTienda"%>
<%@page import="com.example.dao.DaoDetallesTienda"%>
<%@page import="com.example.dao.impl.DaoDetallesTiendaImpl"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administrar Contactos</title>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/enlaces.jspf" %>

        <h1>Administrar Contactos</h1>

        <a href="EventoServlet?accion=MOSTRAR_DATOS_PRINCIPALES" class="volver-btn">Volver a Inicio</a>

        <table border="1">
            <tr>
                <th>ID</th>
                <th>Producto</th>
                <th>Nombre Comprador</th>
                <th>Email Comprador</th>
                <th>Mensaje</th>
                <th>Fecha de Contacto</th>
                <th>Acciones</th>
            </tr>


            <%
                DaoContacto daoContacto = new DaoContactoImpl();
                int idUsuario = (int) request.getSession().getAttribute("id_usuario");
                List<Contacto> contactos = daoContacto.obtenerContactosPorUsuario(idUsuario);

                for (Contacto contacto : contactos) {
                    DaoDetallesTienda daoDetallesTienda = new DaoDetallesTiendaImpl();
                    DetallesTienda detallesTienda = daoDetallesTienda.obtenerDetallesPorId(contacto.getId_detalles_tienda());
            %>
            <tr>
                <td><%= contacto.getId_contacto()%></td>
                <td><%= detallesTienda.getProducto()%></td>
                <td><%= contacto.getNombre_comprador()%></td>
                <td><%= contacto.getEmail_comprador()%></td>
                <td><%= contacto.getMensaje()%></td>
                <td><%= contacto.getFecha_contacto()%></td>
                <td>
                    <a href="ContactoServlet?accion=ELIMINAR&id=<%= contacto.getId_contacto()%>" class="eliminar-btn">Eliminar</a>
                </td>
            </tr>
            <%
                }
            %>
        </table>
    </body>
</html>