<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Detalles del Producto</title>
    </head>

    <body>
        <%@include file="WEB-INF/jspf/enlaces.jspf" %>
        <%@include file="WEB-INF/jspf/encabezado.jspf" %>
        <div class="container">
            <div class="custom-header"> 
                <h1>Detalles del Producto</h1>
            </div>
            <c:forEach var="detalle" items="${detalles}">
                <div class="product-details">


                    <div class="product-image">
                        <img src="data:image/jpeg;base64,${detalle.getTienda().getImagenBase64()}" alt="Imagen del Producto"/>

                    </div>

                    <div class="product-info">
                        <div class="product-info-item">
                            <label>Vendedor:</label>
                            <p>${detalle.getUsuario().getNombre()} ${detalle.getUsuario().getApellidos()}</p>
                        </div>

                        <div class="product-info-item">
                            <label>Producto o servicio:</label>
                            <p>${detalle.getProducto()}</p>
                        </div>

                        <div class="product-info-item">
                            <label>Tipo de Venta:</label>
                            <p>${detalle.getTipo_venta()}</p>
                        </div>

                        <div class="product-info-item">
                            <label>Categoría:</label>
                            <p>${detalle.getCategoria()}</p>
                        </div>

                        <div class="product-info-item">
                            <label>Descripción:</label>
                            <p>${detalle.getDescripcion()}</p>
                        </div>

                        <div class="product-info-item">
                            <label>Precio:</label>
                            <p>S/${detalle.getTienda().getPrecio()}<p>
                        </div>

                        <div class="product-info-item">
                            <label>Estado:</label>
                            <p>${detalle.getEstado()}</p>
                        </div>

                        <div class="product-info-item">
                            <label>Número de Contacto:</label>
                            <p>${detalle.getContacto()}</p>
                        </div>

                    </div>

                </div>


                <div class="contact-form">
                    <div class="custom-header"> 
                        <h1>Contactar al Vendedor</h1>
                    </div>
                    <form action="ContactoServlet?accion=REGISTRAR_CONTACTO" method="post" class="contact-form-inner">
                        <input type="hidden" name="id_detalles_tienda" value="${detalle.getId_detalles_tienda()}">
                        <% String mensajeError = (String) request.getAttribute("mensajeError"); %>
                        <% if (mensajeError != null && !mensajeError.isEmpty()) {%>
                        <div class="alert alert-danger" role="alert">
                            <%= mensajeError%>
                        </div>
                        <% } %>

                        <% String mensajeExito = (String) request.getAttribute("mensajeExito");
                        if (mensajeExito != null && !mensajeExito.isEmpty()) {%>
                        <div class="alert alert-success" role="alert">
                            <%= mensajeExito%>
                        </div>
                        <% }%>
                        <div class="form-group">
                            <label for="nombre_comprador">Nombre:</label>
                            <input type="text" name="nombre_comprador" class="form-control" required>
                        </div>

                        <div class="form-group">
                            <label for="email_comprador">Correo electrónico:</label>
                            <input type="email" name="email_comprador" class="form-control" required>
                        </div>

                        <div class="form-group">
                            <label for="mensaje">Mensaje:</label>
                            <textarea name="mensaje" rows="4" class="form-control" required></textarea>
                        </div>

                        <div class="buttons">
                            <button type="submit" class="contact-button">Enviar Mensaje</button>
                            <button class="back-button" onclick="window.location.href = 'Tienda?accion=SEL'">Volver Atrás</button>
                        </div>
                    </form>
                </div>

            </c:forEach>

        </div>

    </body>
</html>
