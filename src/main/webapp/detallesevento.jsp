<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Detalles del evento</title>
    </head>

    <body>
        <%@include file="WEB-INF/jspf/enlaces.jspf" %>
        <%@include file="WEB-INF/jspf/encabezado.jspf" %>
        <div class="container">
            <div class="custom-header"> 
                <h1>Detalles del Evento</h1>
            </div>
            <div class="product-details">
                <div class="product-image">
                    <img src="img/eve/cine.jpg" alt="Imagen del Producto">
                </div>

                <div class="product-info">

                    <div class="product-info-item">
                        <label>Nombre del evento:</label>
                        <span>Cine Arte: Festival Universitario</span>
                    </div>
                    <div class="product-info-item">
                        <label>Nombre:</label>
                        <span>Michael </span>
                    </div>

                    <div class="product-info-item">
                        <label>Apellido:</label>
                        <span>Rojas</span>
                    </div>

                    <div class="product-info-item">
                        <label>Correo:</label>
                        <span>Sleandroteza@gmail.com</span>
                    </div>

                    <div class="product-info-item">
                        <label>Lugar:</label>
                        <span>Las Flores</span>
                    </div>

                    <div class="product-info-item">
                        <label>Hora:</label>
                        <span>6am</span>
                    </div>

                    <div class="product-info-item">
                        <label>Fecha:</label>
                        <span>03-12-2024</span>
                    </div>

                    <div class="product-info-item">
                        <label>Número de celular:</label>
                        <span>991596818</span>
                    </div>

                    <div class="product-info-item">
                        <label>Descripcion del evento:</label>
                        <p>Un festival que presenta películas creadas por estudiantes universitarios. Celebramos la creatividad y el talento cinematográfico emergente, con proyecciones de cortometrajes y largometrajes seguidos de debates con los cineastas.</p>
                    </div>
                    <div class="product-info-item">
                        <label>Máxima cantidad de asistentes:</label>
                        <span>10/20</span>
                    </div>
                </div>

            </div>

        </div>
        <div class="buttons">
            <button class="contact-button">Reservar evento</button>
            <button class="back-button" onclick="window.history.back()">Volver Atrás</button>
        </div>
    </body>
</html>

