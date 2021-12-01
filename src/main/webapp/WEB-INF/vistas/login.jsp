<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
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
    <link rel="icon" href="img/favicon.ico" type="image/png" />
    <title>Proyecto - Alquiler de autos</title>
</head>
<body class="bg-light">
<header>
        <div class = "d-flex col-md-12 justify-content-around align-items-center">
            <div class="d-flex align-items-center">
                <img style="height: 120px; width: 200px" src="img/nombreConLogo.svg" alt="logo">
            </div>
        </div>
</header>
<section class="mb-3">
    <div class="container">
        <div class="justify-content-center">
            <div class="card-wrapper mt-2">
                <c:if test="${not empty cuentaVerificada}">
                    <div class="alert alert-success text-center container mt-3" role="alert">${cuentaVerificada}</div>>
                    <br>
                </c:if>
                <div class="card">
                    <div class="card-body">
                        <h4 class="mb-3 text-center">Iniciar Sesión</h4>
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
                                <button class="btn btn-lg btn-dark btn-block" Type="Submit"/>Ingresar</button>
                            </div>
                            <div class="mt-4 text-center">
                                ¿No tienes una cuenta? <a class="text-info" href="registro">Crear una</a>
                            </div>
                        </form:form>
                    </div>

                    <c:if test="${not empty error}">
                        <div class="alert alert-danger text-center container mt-3" role="alert">
                                ${error}
                        </div>
                    </c:if>
                </div>

                <c:if test="${not empty errorSinPermisos}">
                    <div class="alert alert-danger text-center container mt-3" role="alert">
                            ${errorSinPermisos}
                    </div>
                </c:if>
                <div class="mt-3 text-center">
                    <a href="home" class="text-dark col font-weight-medium">Volver</a>
                </div>
            </div>
        </div>
    </div>
</section>
<jsp:include page="footer.jsp" />
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