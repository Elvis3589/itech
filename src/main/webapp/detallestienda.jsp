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
            <div class="product-details">
                <div class="product-image">
                    <img src="img/mat/machine.jpg" alt="Imagen del Producto">
                </div>

                <div class="product-info">
                    <div class="product-info-item">
                        <label>Vendedor:</label>
                        <span>Roberth García</span>
                    </div>

                    <div class="product-info-item">
                        <label>Producto o servicio:</label>
                        <span>Curso Machine Learning</span>
                    </div>

                    <div class="product-info-item">
                        <label>Tipo de Venta:</label>
                        <span>Venta</span>
                    </div>

                    <div class="product-info-item">
                        <label>Categoría:</label>
                        <span>Curso</span>
                    </div>

                    <div class="product-info-item">
                        <label>Cantidad:</label>
                        <span>1</span>
                    </div>

                    <div class="product-info-item">
                        <label>Descripción:</label>
                        <p>Curso de Machine Learning: Aprende los fundamentos del aprendizaje automático y cómo aplicarlos. Incluye material didáctico y ejercicios prácticos. ¡Desarrolla tus habilidades en ML por solo S/50.00!</p>
                    </div>

                    <div class="product-info-item">
                        <label>Precio:</label>
                        <span>S/50.00</span>
                    </div>

                    <div class="product-info-item">
                        <label>Estado:</label>
                        <span>Nuevo</span>
                    </div>

                    <div class="product-info-item">
                        <label>Número de Contacto:</label>
                        <span>934554648</span>
                    </div>

                </div>

            </div>

        </div>
        <div class="buttons">
            <button class="contact-button">Contactar</button>
            <button class="back-button" onclick="window.history.back()">Volver Atrás</button>
        </div>
    </body>
</html>
