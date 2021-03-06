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
<body>
<jsp:include page="header.jsp" />
<section class="bg-light">
    <div class="container">
        <div class="col-sm-12 d-flex justify-content-center">
            <h1 class="display-4 p-4">Confirmación</h1>
        </div>
        <c:if test="${empty mensaje && empty mensajeFallido && empty errorFechas}" >
            <div id="contenido" class="container card col-md-6 my-4" >
                <img src="${imagen_auto}" class="card-img-top" alt="imagen auto">
                <div class="card-body">
                    <h5 class="card-title">${marca_auto} ${modelo_auto}  </h5>
                    <p class="card-text">Retiro el auto: ${formattedDateSalida} en ${lugarRetiro.direccion}</p>
                    <p class="card-text">Devuelvo el auto: ${formattedDateIngreso} en ${lugarDevolucion.direccion}</p>
                    <div class="col-sm-12 d-flex justify-content-center">
                        <a href="validar-alquiler?id_auto=${id_auto}&salida=${salida}&ingreso=${ingreso}&lugarRetiro=${lugarRetiro.id}&lugarDevolucion=${lugarDevolucion.id}" class="btn btn-info" >ALQUILAR</a>
                    </div>
                </div>
            </div>
        </c:if>
        <c:if test="${not empty errorFechas}">
            <div class="alert alert-danger text-center container mt-3" role="alert">
                    ${errorFechas}
            </div>
            <div class="col-sm-12 d-flex justify-content-center">
                <a href="alquilar-auto" class="btn btn-dark" >Volver</a>
            </div>
        </c:if>
        <c:if test="${not empty mensaje}">
            <div class="alert alert-success text-center container mt-3" role="alert">
               ${mensaje}
            </div>
            <p class="text-center">ĄSumaste 50 puntos!</p>
            <div class="col-sm-12 d-flex justify-content-center pb-3">
                <a href="main" class="btn btn-dark" >Volver a mi cuenta</a>
            </div>
        </c:if>
        <c:if test="${not empty mensajeFallido}">
            <div class="alert alert-danger text-center container mt-3" role="alert">
                    ${mensajeFallido}
            </div>
            <div class="col-sm-12 d-flex justify-content-center pb-3">
                <a href="alquilar-auto" class="btn btn-dark" >Volver</a>
            </div>
        </c:if>
    </div>

</section>
<c:if test="${empty mensaje && empty mensajeFallido && empty errorFechas}" >
<jsp:include page="footer.jsp" />
</c:if>
</body>
</html>
