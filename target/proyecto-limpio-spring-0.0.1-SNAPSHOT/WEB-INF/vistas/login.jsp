<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
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
    <title>Proyecto - Alquiler de autos</title>
</head>
<body>
<header class = "d-flex flex-row-reverse p-3">
    <div class = " d-flex flex-row-reverse col-md-6 col-lg-4 col-xl-3 justify-content-around">
        <a href="login" type="button" class="btn btn-primary">Iniciar sesion</a>
        <a href="registro" type="button" class="btn btn-warning">Crear cuenta</a>
        <a href="ir-a-suscribir" type="button" class="btn btn-danger">Suscribirse a un plan</a>
    </div>
</header>
<section class="h-100">
    <div class="container h-100">
        <div class="row justify-content-center h-100">
            <div class="card-wrapper">
                <div class="card fat">
                    <div class="card-body">
                        <h4 class="card-title">Iniciar sesión</h4>
                        <form:form action="validar-login" method="POST" modelAttribute="datosLogin">
                            <div class="form-group">
                                <label >Email</label>
                                <form:input path="email" type="email" class="form-control" name="email"  />
                            </div>

                            <div class="form-group">
                                <label>Contraseña</label>
                                <form:input path="clave" type="password" class="form-control" name="password" />
                            </div>

                            <div class="form-group m-0">
                                <button class="btn btn-lg btn-primary btn-block" Type="Submit"/>Ingresar</button>
                            </div>
                            <div class="mt-4 text-center">
                                ¿No tienes una cuenta? <a href="registro">Crear una</a>
                            </div>
                        </form:form>
                    </div>

                    <c:if test="${not empty error}">
                        <caption><p class="text-center text-danger">${error}</p></caption>
                        <br>
                    </c:if>
                </div>
                <a href="home" class="btn col text-center">Volver</a>
            </div>
        </div>
    </div>
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