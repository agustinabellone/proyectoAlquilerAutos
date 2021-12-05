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
    <link rel="icon" href="img/favicon.ico" type="image/png" />
    <title>Proyecto - Alquiler de autos</title>
</head>
<body>
<jsp:include page="header.jsp" />

<c:if test="${empty confirmacionSuscripcionGratuita}">
<section class="bg-light">
    <div class="pricing6 py-3">
        <div class="container">
            <!-- row  -->
            <div class="row d-flex align-content-center justify-content-center mt-4">
                <!-- column  -->
                <div class="col-md-4">
                    <div class="card card-shadow border-0 mb-4">
                        <div class="card-body p-4">
                            <div class="d-flex align-items-center">
                                <h5 class="font-weight-medium mb-0">Plan Diamante</h5>
                                <div class="ml-auto"><span class="badge badge-info font-weight-normal p-2">Popular</span></div>
                            </div>
                            <div class="d-flex flex-column">
                                <div class="col-lg-12 align-self-center">
                                    <ul class="list-inline mt-3 pl-3 font-14 text-center font-weight-medium text-dark">
                                        <li><i class="icon-check text-info "></i><p>200 km de recorrido</p></li>
                                        <li><i class="icon-check text-info "></i><p>Km excedido: $500</p></li>
                                        <li><i class="icon-check text-info "></i><p>Eleccion de vehiculo (incluye gama alta)</p></li>
                                        <li><i class="icon-check text-info "></i><p>Reservar con cualquier anticipación</p></li>
                                        <li><i class="icon-check text-info "></i><p>Cambio de tiempo/lugar estipulado SIN COSTO</p></li>
                                    </ul>
                                </div>
                                <div class="col-lg-12 text-center">
                                    <div class="price-box my-3">
                                        <sup>$</sup><span class="text-dark display-5">0</span>
                                        <h6 class="font-weight-light">1 MES</h6>
                                        <a class="btn btn-dark font-18 border-0 text-white p-3 btn-block mt-3" href="confirmar-suscripcion-gratis">SUSCRIBIRME</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

</c:if>

<c:if test="${not empty confirmacionSuscripcionGratuita}">
    <section class="bg-light">
        <div class="container">
            <div class="d-flex justify-content-center">
                <h1 class="display-4 p-4 text-center">Confirmación</h1>
            </div>
        </div>
    <div class="alert alert-success text-center container mt-2" role="alert">
            ${confirmacionSuscripcionGratuita}
    </div>
        <p class="text-center">
                ${informacionPuntaje}
        </p>
    <div class="col-sm-12 d-flex justify-content-center">
        <a href="main" class="btn btn-dark mb-5" >Volver a Mi Cuenta</a>
    </div>
    </section>
</c:if>
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