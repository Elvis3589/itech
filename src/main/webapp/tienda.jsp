<%@page import="com.example.dao.impl.DaoPremiumImpl"%>
<%@page import="com.example.dao.DaoPremium"%>
<%@page import="java.util.Base64"%>
<%@page import="com.example.entidades.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    DaoPremium daoPremium = new DaoPremiumImpl();
%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Tienda</title>

    </head>

    <body>
        <%@include file="WEB-INF/jspf/enlaces.jspf" %>
        <%@include file="WEB-INF/jspf/encabezado.jspf" %>
        <div class="profile-info">
            <div class="card">
                <%
                    Usuario usuario = (Usuario) session.getAttribute("usuario");
                    byte[] imagen = (usuario != null && usuario.getImagen() != null) ? usuario.getImagen() : null;
                    boolean esPremium = (usuario != null && daoPremium.tieneSuscripcionPremium(usuario.getIdUsuario()));

                %>

                <% if (imagen != null) {%>
                <img src="data:image/jpeg;base64,<%= Base64.getEncoder().encodeToString(imagen)%>" class="rounded-image" alt="Profile Image">
                <% } %>

                <h5 style="text-align: center; font-size: 20px">
                    <% if (usuario != null) {%>
                    <%= usuario.getNombre() + " " + usuario.getApellidos()%>
                    <% } else { %>
                    Nombre de Usuario
                    <% } %>
                </h5>

                <p class="card-text" style="text-align: center; margin-bottom: 40px;">
                    <% if (usuario != null) {%>
                    <%= usuario.getEmail()%>
                    <% } else { %>
                    Correo de Usuario
                    <% }%>
                </p>

                <p class="card-text" style="text-align: center; margin-bottom: 40px;">
                    <% if (usuario != null) {%>
                    <%= "Rol:" + usuario.getRol()%>
                    <% } else { %>
                    Rol de Usuario
                    <% }%>
                </p>
                <% if (esPremium) { %>
                <p class="card-text" style="text-align: center; margin-bottom: 40px; color: green; font-weight: bold;">
                    Usuario Premium
                </p>
                <% } else { %>
                <p class="card-text" style="text-align: center; margin-bottom: 40px; color: red; font-weight: bold;">
                    Usuario no Premium
                </p>
                <% }%>
                <div class="button-container">
                    <button type="button" class="btn btn-success" id="eventosButton">Eventos</button>
                </div>
            </div>
        </div>
        <section class="container section-right">

            <h1 class="titulo-tienda">Tienda</h1>
            <div id="overlay" class="overlay"></div>

            <div class="publicar-button-container">
                <button type="button" onclick="mostrarModal()" class="publicar-button">Publicar</button>
            </div>
            <div id="myModal" class="modal">
                <form action="Tienda?accion=REGISTRAR_TIENDA" method="POST" enctype="multipart/form-data">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h1 class="modal-title ">Publicar producto</h1>
                                <span class="close" onclick="cerrarModal()"></span>
                            </div>
                            <div class="modal-body">

                                <div>
                                    <label for="titulo" class="form-label">Título</label>
                                    <input type="text" name="texto_titulo" id="titulo" placeholder="Título o nombre de producto">
                                </div>
                                <div>
                                    <label for="tipo_venta" class="form-label">Tipo de venta</label>
                                    <select name="texto_tipo" id="tipo_venta">
                                        <option selected>Selecciona</option>
                                        <option value="Venta">Venta</option>
                                        <option value="Intercambio">Intercambio</option>
                                        <option value="Gratis">Gratis</option>
                                    </select>
                                </div>
                                <div>
                                    <label for="categoria" class="form-label">Categoría</label>
                                    <select name="texto_categoria" id="categoria">
                                        <option selected>Selecciona</option>
                                        <option value="Apuntes">Apuntes</option>
                                        <option value="Libros">Libros</option>
                                        <option value="Multimedia">Multimedia</option>
                                    </select>
                                </div>
                                <div>
                                    <label class="form-label">Subir imagen</label>
                                    <input name="archivo_imagen" type="file" id="imagen">
                                </div>

                                <div>
                                    <label for="descripcion" class="form-label">Descripción</label>
                                    <textarea name="texto_descripcion" id="descripcion" rows="3"></textarea>
                                </div>
                                <div>
                                    <label for="precio" class="form-label">Precio</label>
                                    <input type="number" name="texto_precio" id="precio" min="0" step="0.01">
                                </div>
                                <div>
                                    <label for="estado" class="form-label">Estado</label>
                                    <select name="texto_estado" id="estado">
                                        <option selected>Selecciona</option>
                                        <option value="Nuevo">Nuevo</option>
                                        <option value="Usado">Usado</option>
                                    </select>
                                </div>
                                <div>
                                    <label for="contacto" class="form-label">Número de contacto</label>
                                    <input type="text" name="texto_contacto" id="contacto" pattern="[0-9]{9}" placeholder="9 dígitos">
                                    <input type="hidden" name="id_usuario" value="${id_usuario}">
                                </div>


                            </div>

                        </div>
                        <button type="submit" class="boton-publicar" onclick="publicarProducto()">Publicar</button>
                        <button type="button" class="boton-cerrar" onclick="cerrarModal()">Cerrar</button>

                    </div>
                </form>
            </div>

            <div class="cursos-container">
                <c:forEach var="producto" items="${productos}">
                    <div class="curso">
                        <div class="producto">
                            <div class="producto-imagen">
                                <img src="data:image/jpeg;base64,${producto.getImagenBase64()}" alt="Producto 6" />
                            </div>
                            <div class="producto-info">
                                <h2>${producto.getTitulo()}</h2>
                                <p class="precio">S/${producto.getPrecio()}</p>
                                <a href="Detalles?id=${producto.getId_tienda()}"><button class="ver-detalles">Ver</button></a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </section>

        <script>
            function mostrarModal() {
                var modal = document.getElementById("myModal");
                var overlay = document.getElementById("overlay");
                modal.style.display = "block";
                overlay.style.display = "block";
            }

            function cerrarModal() {
                var modal = document.getElementById("myModal");
                var overlay = document.getElementById("overlay");
                modal.style.display = "none";
                overlay.style.display = "none";
            }

            function verDetalles(id) {
                alert("Ver detalles del producto con ID: " + id);
            }
        </script>
        <script>
            document.getElementById("eventosButton").addEventListener("click", function () {
                window.location.href = "gestionevento.jsp";
            });
        </script>

    </body>
</html>
<script>
    localStorage.setItem('lastVisitedPage', window.location.href);
</script>
