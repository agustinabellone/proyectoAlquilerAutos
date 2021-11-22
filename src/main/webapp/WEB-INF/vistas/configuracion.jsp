<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css"
          integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
          crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@fortawesome/fontawesome-free@5.15.4/css/fontawesome.min.css" integrity="sha384-jLKHWM3JRmfMU0A5x5AkjWkw/EYfGUAGagvnfryNV3F9VqM98XiIH7VBGVoxVSc7" crossorigin="anonymous">
    <link rel="icon" href="img/favicon.ico" type="image/png" />
    <title>Proyecto - Alquiler de autos</title>
</head>
<body>
<jsp:include page="header.jsp" />
<body>
<section class="pb-5 bg-light">
    <div class="container">
        <div class="col-sm-12 d-flex justify-content-center">
            <h1 class="display-4 p-4">Configuración</h1>
        </div>
        <c:if test="${empty mensaje && empty cambiosActualizados && empty error}">
        <h4>Editá tus datos personales</h4>
            <form id="form" action="guardar-cambios" method="get">
        <article style="height: 300px" class="d-flex flex-column justify-content-around">
                <div>
                    <label>Nombre</label>
                    <input type="text" class="form-control" name="nombre" id="nombre"  value="${usuario.nombre}">
                </div>
                <div>
                    <input type="email" class="form-control"  value="${usuario.email}" disabled>
                    <span  style="font-size: 10px">Tu email no se puede cambiar ya que tu cuenta depende del mismo.</span>
                </div>
                <div>
                    <label>Contraseña</label>
                    <div class="d-flex mb-3">
                        <input class="form-control" type="password" name="password" id="password" value="${usuario.clave}"/>
                        <button type="button" class="btn btn-dark" onclick="if (password.type == 'text') password.type = 'password';
                            else password.type = 'text';"><i class="fas fa-eye"></i></button>
                    </div>
                </div>
        </article>
                <div class="d-flex mt-3">
                    <div>
                        <button type="submit" class="btn btn-success" >Guardar cambios</button>
                    </div>
                    <div class="ml-3">
                        <button type="button" class="btn btn-outline-danger" data-toggle="modal" data-target="#eliminarCuentaModal">Eliminar cuenta</button>
                    </div>
                </div>
            </form>

        </c:if>
        <c:if test="${not empty mensaje}">
            <div class="alert alert-success text-center container mt-3" role="alert">
                    ${mensaje}
            </div>
            <div class="col-sm-12 d-flex justify-content-center">
                <a href="logout" class="btn btn-dark" >Salir</a>
            </div>
        </c:if>
        <c:if test="${not empty cambiosActualizados}">
            <div class="alert alert-success text-center container mt-3" role="alert">
                    ${cambiosActualizados}
            </div>
            <div class="col-sm-12 d-flex justify-content-center">
                <a href="configuracion" class="btn btn-dark" >Volver</a>
            </div>
        </c:if>

        <c:if test="${not empty error}">
            <div class="alert alert-danger text-center container mt-3" role="alert">
                    ${error}
            </div>
            <div class="col-sm-12 d-flex justify-content-center">
                <a href="configuracion" class="btn btn-dark" >Volver</a>
            </div>
        </c:if>
    </div>

    <!-- Modal -->
    <div class="modal fade" id="eliminarCuentaModal" tabindex="-1" role="dialog" aria-labelledby="eliminarCuentaModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="eliminarCuentaModalLabel">Eliminar cuenta</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    ¿Estás seguro? Perderás todo y tendrás que crear una cuenta de nuevo.
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                    <a href="eliminar-cuenta"><button type="button" class="btn btn-danger">Eliminar</button></a>
                </div>
            </div>
        </div>
    </div>
    <!--Fin del modal-->

</section>
<c:if test="${empty mensaje && empty cambiosActualizados && empty error}">
<jsp:include page="footer.jsp" />
</c:if>
</body>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"></script>
<script
        src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script
        src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>

</html>
