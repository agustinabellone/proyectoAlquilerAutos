<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
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
    <link rel="stylesheet" type="text/css" href="css/planes.css"/>
    <title>Proyecto - Alquiler de autos</title>
</head>
<body>
<jsp:include page="header.jsp" />
<section class="bg-light">
    <div class="container">
        <div class="col-sm-12 d-flex justify-content-center">
            <h1 class="display-4 p-4">Confirme su suscripción</h1>
        </div>
    </div>
</section>
<section class="bg-light">
    <div class="pricing6 py-5">
        <div class="container">
            <div class="card text-center">
                <div class="card-header"></div>
                <div class="card-body">
                    <h5 class="card-title">Por favor <c:out value="${nombre}"/>, confirmá tu suscripción</h5>
                    <p class="card-text">¿Te suscribimos al plan número <span style="font-size: 120%; color: black"><c:out value="${id_tipo}"/></span> ?</p>
                    <a href="suscribirse?id_tipo=${id_tipo}&id_usuario=${id}" class="btn btn-success">Confirmar</a>
                    <a href="ir-a-suscribir" class="btn btn-secondary">Volver</a>
                </div>
                <div class="card-footer text-muted">
                    <c:if test="${not empty error}">
                        <caption><p class="text-center text-danger">${error}</p></caption>
                        <br>
                    </c:if>

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

<jsp:include page="footer.jsp" />

</html>