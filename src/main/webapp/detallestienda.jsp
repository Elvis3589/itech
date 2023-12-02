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
                            <span>${detalle.getUsuario().getNombre()} ${detalle.getUsuario().getApellidos()}</span>
                        </div>

                        <div class="product-info-item">
                            <label>Producto o servicio:</label>
                            <span>${detalle.getProducto()}</span>
                        </div>

                        <div class="product-info-item">
                            <label>Tipo de Venta:</label>
                            <span>${detalle.getTipo_venta()}</span>
                        </div>

                        <div class="product-info-item">
                            <label>Categoría:</label>
                            <span>${detalle.getCategoria()}</span>
                        </div>

                        <div class="product-info-item">
                            <label>Cantidad:</label>
                            <span>${detalle.getCantidad()}</span>
                        </div>

                        <div class="product-info-item">
                            <label>Descripción:</label>
                            <p>${detalle.getDescripcion()}</p>
                        </div>

                        <div class="product-info-item">
                            <label>Precio:</label>
                            <span>S/${detalle.getTienda().getPrecio()}</span>
                        </div>

                        <div class="product-info-item">
                            <label>Estado:</label>
                            <span>${detalle.getEstado()}</span>
                        </div>

                        <div class="product-info-item">
                            <label>Número de Contacto:</label>
                            <span>${detalle.getContacto()}</span>
                        </div>

                    </div>

                </div>
            </c:forEach>

        </div>
        <div class="buttons">
            <button class="contact-button">Contactar</button>
            <button class="back-button" onclick="window.history.back()">Volver Atrás</button>
        </div>
    </body>
</html>
