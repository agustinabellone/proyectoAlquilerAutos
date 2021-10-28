<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<body>
<jsp:include page="header.jsp" />
<section>
    <div class="container">

        <div class="col-sm-12 d-flex justify-content-center">
            <h1 class="text-center m-5">¡Ingresaste a tu cuenta!</h1>
            <h1 class="text-center m-5">¡Bienvenid@ <c:out value="${nombre}"/>!</h1>
        </div>

        <div class="d-flex justify-content-around">
            <div class="card col-sm-3">
                <img class="card-img-top mt-3" src="https://img.freepik.com/vector-gratis/ilustracion-coche-alquiler-joven-feliz_179970-626.jpg?size=626&ext=jpg" alt="Card image">
                <div class="d-flex justify-content-center">
                    <a href="alquilar-auto" class="btn btn-primary mt-3 mb-3">Alquilar auto</a>
                </div>
            </div>

            <div class="card col-sm-3">
                <img class="card-img-top mt-3" src="https://cdni.iconscout.com/illustration/free/thumb/task-list-2080780-1753768.png" alt="Card image">
                <div class="d-flex justify-content-center">
                    <a href="lista-viajes"  class="btn btn-primary mt-3">Ver mis viajes</a>
                </div>
            </div>
        </div>
        <div class="col-sm-12 d-flex justify-content-center" style="margin-top: 10px">
            <h1 class="text-center">Alquileres actuales</h1>
        </div>

        <div class="d-flex justify-content-around">
            <div class="card col-sm-9">
                <div class="d-flex justify-content-center">
                    <c:forEach items="${alquileres}" var="alquiler">
                        <c:forEach items="${fechas}" var="fechas">
                            <td>${fechas}</td>
                        </c:forEach>
                        <c:forEach items="${modelos}" var="modelo">
                            <td>${modelo.descripcion}</td>
                        </c:forEach>
                        <c:forEach items="${marcas}" var="marca">
                            <td>${marca.descripcion}</td>
                        </c:forEach>
                        <a href='finalizar-alquiler?alquilerID=${alquiler.id}'>Finalizar alquiler</a>
                    </c:forEach>
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
