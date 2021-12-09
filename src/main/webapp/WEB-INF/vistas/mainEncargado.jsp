<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
          crossorigin="anonymous">
    <link rel="icon" href="img/favicon.ico" type="image/png"/>
    <title>Proyecto - Alquiler de autos</title>
</head>
<body>
<header class="p-3">
    <nav class="navbar navbar-expand-lg navbar-light">
        <div class="d-flex align-items-center">
            <img style="height: 50px; width: 100%" src="img/nombreConLogo.svg" alt="logo">
        </div>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ">
                <li class="nav-item">
                    <a class="nav-link" href="home">Inicio</a>
                </li>
                <li class="nav-item">
                    <a type="button" class="nav-link text-danger" data-toggle="modal" data-target="#exampleModal">
                        Cerrar sesion
                    </a>
                </li>
            </ul>
        </div>
    </nav>
    <!-- Modal -->
    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Cerrar sesion</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    ¿Estás seguro? Tendrás que iniciar sesión de nuevo.
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                    <a href="logout">
                        <button type="button" class="btn btn-danger">Cerrar</button>
                    </a>
                </div>
            </div>
        </div>
    </div>
    <!--Fin del modal-->
</header>
<section class="bg-light p-5">
    <div class="container">
        <h1 class="text-center display-4 p-4">¡Hola <c:out value="${nombre}"/>!</h1>
        <c:if test="${not empty esperandoConfirmacion}">
            <div class="col-sm-12 d-flex justify-content-center" style="margin-top: 10px">
                <c:if test="${not empty funciono}">
                    <div class="d-flex justify-content-center">
                        <a href="main" class="btn btn-dark mt-3 mb-3">${funciono}</a>
                    </div>
                </c:if>
                <c:forEach items="${esperandoConfirmacion}" var="esperandoConfirmacion">
                    <div class="d-flex justify-content-around align-items-center col-sm-12 border p-3 rounded">
                        <h5 class="text-center">${esperandoConfirmacion.usuario.nombre} desea dar por finalizado el
                            alquiler.</h5>
                        <a href='cierreDevolucion?solicitud=${esperandoConfirmacion.id}' class="btn btn-success">CONFIRMAR</a>
                    </div>
                </c:forEach>
            </div>
        </c:if>

        <div class="container d-flex justify-content-center text-center">
            <div class="col-sm-9">

            </div>
        </div>

    </div>
    <c:if test="${not empty mensaje}">
        <caption><p class="text-center text-danger">${mensaje}</p></caption>
        <br>
    </c:if>
</section>
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
</body>
</html>
