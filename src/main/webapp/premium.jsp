<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Función Premium</title>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/enlaces.jspf" %>
        <%@include file="WEB-INF/jspf/encabezado.jspf" %>

        <div class="container">
            <div class="header">
                <h1 class="premium-title">Función Premium</h1>
            </div>
            <p class="premium-description">¡Adquiere la función premium para disfrutar de características exclusivas!</p>
            <form method="post" action="PremiumServlet" class="premium-form">
                <input type="hidden" name="accion" value="INS">

                <div class="form-group">
                    <div class="nombre">

                        <label for="nombre" class="premium-label">Nombre en la Tarjeta:</label>
                        <input type="text" name="nombre" class="premium-input" required pattern="[A-Za-záéíóúÁÉÍÓÚñÑüÜ\s]+" title="Ingrese solo letras">
                    </div>
                </div>
                <div class="form-group">
                    <div class="numero">

                        <label for="numeroTarjeta" class="premium-label">Número de Tarjeta:</label>
                        <input type="text" name="numeroTarjeta" class="premium-input" required maxlength="16" pattern="[0-9]{16}" title="Ingrese solo números de 16 dígitos">
                    </div>

                </div>
                <div class="form-group">
                    <div class="mes">
                        <label for="mesVencimiento" class="premium-label">Mes:</label>
                        <select name="mesVencimiento" class="premium-input" required>
                            <option value="01">01</option>
                            <option value="02">02</option>
                            <option value="03">03</option>
                            <option value="04">04</option>
                            <option value="05">05</option>
                            <option value="06">06</option>
                            <option value="07">07</option>
                            <option value="08">08</option>
                            <option value="09">09</option>
                            <option value="10">10</option>
                            <option value="11">11</option>
                            <option value="12">12</option>
                        </select>
                    </div>
                    <div class="ano">
                        <label for="anoVencimiento" class="premium-label">Año:</label>
                        <select name="anoVencimiento" class="premium-input" required>                        
                            <option value="2023">23</option>
                            <option value="2024">24</option>
                            <option value="2025">25</option>
                            <option value="2026">26</option>
                            <option value="2027">27</option>
                            <option value="2028">28</option>
                            <option value="2029">29</option>
                            <option value="2030">30</option>
                            <option value="2031">31</option>
                            <option value="2032">32</option>
                            <option value="2033">33</option>
                            <option value="2034">34</option>
                            <option value="2035">35</option>
                        </select>

                    </div>
                    <div class="ccv">
                        <label for="codigoSeguridad" class="premium-label">CVV:</label>
                        <input type="text" name="codigoSeguridad" class="premium-input" required maxlength="3" pattern="[0-9]{3}" title="Ingrese solo números de 3 dígitos">
                    </div>
                    <div class="monto-pago">
                        <label for="montoPago" class="premium-label">Pago:</label>
                        <input type="text" name="montoPago" class="premium-input" value="S/15.00" readonly>
                    </div>

                </div>

                <%
                    String mensajeError = (String) request.getAttribute("mensajeError");
                    if (mensajeError != null && !mensajeError.isEmpty()) {
                %>
                <div class="error-message">
                    <%= mensajeError%>
                </div>
                <%
                    }
                %>
                <div class="form-group" style="display: flex; ">
                    <button type="submit" class="premium-button">Obtener Premium</button>
                    <button type="button" class="volver-button" onclick="window.location.href = 'perfil.jsp'">Volver atrás</button>
                </div>


            </form>
        </div>
    </body>
</html>
