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

    <title>Proyecto - Alquiler de autos</title>
</head>

<body class="bg-dark">
<jsp:include page="header.jsp" />
<section>
    <div class="container">
        <div class="mt-5 justify-content-center">
            <div class="card-wrapper mt-5">
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
                                ¿No tienes una cuenta? <a class="text-primary" href="registro">Crear una</a>
                            </div>
                        </form:form>
                    </div>

                    <c:if test="${not empty error}">
                        <caption><p class="text-center text-danger">${error}</p></caption>
                        <br>
                    </c:if>
                </div>

                <c:if test="${not empty errorSinPermisos}">
                    <div class="alert alert-danger text-center container mt-3" role="alert">
                            ${errorSinPermisos}
                    </div>
                </c:if>
                <div class="mt-3 text-center">
                    <a href="home" class="text-white col font-weight-medium">Volver</a>
                </div>
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