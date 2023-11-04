<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                <img src="img/imguser/perfil1.jpg" class="rounded-image" alt="Profile Image">
                <h5 style="text-align: center; font-size: 20px">Michael Rojas</h5>
                <p class="card-text" style="text-align: center; margin-bottom: 40px;">Sleandroteza@gmail.com</p>
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
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h1 class="modal-title ">Publicar producto</h1>
                            <span class="close" onclick="cerrarModal()"></span>
                        </div>
                        <div class="modal-body">
                            <form action="publicar_producto" method="POST" enctype="multipart/form-data">
                                <input type="text" style="display: none;" name="texto_id_usuario" value="1" />
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
                                    <label for="cantidad" class="form-label">Cantidad</label>
                                    <input type="number" name="texto_cantidad" id="cantidad" min="1" max="10">
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
                                </div>

                            </form>
                        </div>

                    </div>
                    <button type="button" class="boton-publicar" onclick="publicarProducto()">Publicar</button>
                    <button type="button" class="boton-cerrar" onclick="cerrarModal()">Cerrar</button>

                </div>
            </div>

            <div class="cursos-container">
                <div class="curso">
                    <div class="producto">
                        <div class="producto-imagen">
                            <img src="img/mat/machine.jpg" alt="Producto 1" />
                        </div>
                        <div class="producto-info">
                            <h2>Curso Machine Learning</h2>
                            <p class="precio">S/50.00</p>
                            <button class="ver-detalles" onclick="window.location.href = 'detallestienda.jsp'">Ver</button>
                        </div>
                    </div>
                </div>
                <div class="curso">
                    <div class="producto">
                        <div class="producto-imagen">
                            <img src="img/mat/matematicas.png" alt="Producto 2" />
                        </div>
                        <div class="producto-info">
                            <h2>Libro matematicas</h2>
                            <p class="precio">S/15.00</p>
                            <button class="ver-detalles" onclick="window.location.href = 'detallestienda.jsp'">Ver</button>
                        </div>
                    </div>
                </div>

                <div class="curso">
                    <div class="producto">
                        <div class="producto-imagen">
                            <img src="img/mat/creaempresa.png" alt="Producto 3" />
                        </div>
                        <div class="producto-info">
                            <h2>Curso Crea tu empresa</h2>
                            <p class="precio">S/34.00</p>
                            <button class="ver-detalles" onclick="window.location.href = 'detallestienda.jsp'">Ver</button>
                        </div>
                    </div>
                </div>

                <div class="curso">
                    <div class="producto">
                        <div class="producto-imagen">
                            <img src="img/mat/claseprogramacion.jpg" alt="Producto 4" />
                        </div>
                        <div class="producto-info">
                            <h2>Videos clase Programacion</h2>
                            <p class="precio">S/56.00</p>
                            <button class="ver-detalles" onclick="window.location.href = 'detallestienda.jsp'">Ver</button>
                        </div>
                    </div>
                </div>
                <div class="curso">
                    <div class="producto">
                        <div class="producto-imagen">
                            <img src="img/mat/redaccion.jpg" alt="Producto 5" />
                        </div>
                        <div class="producto-info">
                            <h2>Redaccíon 1</h2>
                            <p class="precio">S/15.00</p>
                            <button class="ver-detalles" onclick="window.location.href = 'detallestienda.jsp'">Ver</button>
                        </div>
                    </div>
                </div>
                <div class="curso">
                    <div class="producto">
                        <div class="producto-imagen">
                            <img src="img/mat/gestion.png" alt="Producto 6" />
                        </div>
                        <div class="producto-info">
                            <h2>Libro de Gestión logística</h2>
                            <p class="precio">S/42.00</p>
                            <button class="ver-detalles" onclick="window.location.href = 'detallestienda.jsp'">Ver</button>
                        </div>
                    </div>
                </div>
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
