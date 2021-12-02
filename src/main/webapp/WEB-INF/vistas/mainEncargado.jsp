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
    <title>Proyecto - Alquiler de autos</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<section class="bg-light p-5">
    <div class="container">
        <h1 class="mb-5">¡Hola <c:out value="${nombre}"/>!</h1>
        <c:if test="${not empty esperandoConfirmacion}">
            <div class="col-sm-12 d-flex justify-content-center" style="margin-top: 10px">
                <c:if test="${not empty funciono}">
                    <div class="d-flex justify-content-center">
                        <a href="main" class="btn btn-dark mt-3 mb-3">${funciono}</a>
                    </div>
                </c:if>
               <c:forEach items="${esperandoConfirmacion}" var="esperandoConfirmacion">
                    <h1 class="text-center">${esperandoConfirmacion.usuario.nombre} desea dar por finalizado el
                        alquiler.</h1>
                    <a href="cierreDevolucion?solicitud=${esperandoConfirmacion.id}" class="btn btn-success">CONFIRMAR</a>
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
<jsp:include page="footer.jsp"/>
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
