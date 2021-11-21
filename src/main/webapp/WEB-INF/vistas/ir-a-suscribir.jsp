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
<section>
    <div class="container">
        <div class="col-sm-12 d-flex justify-content-center">
            <h1 class="text-center mt-5 mb-5">Suscribite a uno de nuestros planes</h1>
        </div>
    </div>
</section>
<section>
    <div class="pricing6 py-5 bg-light">
        <div class="container">
            <!-- row  -->
            <div class="row mt-4">
                <!-- column  -->
                <div class="col-md-6">
                    <div class="card card-shadow border-0 mb-4">
                        <div class="card-body p-4">
                            <div class="d-flex align-items-center">
                                <h5 class="font-weight-medium mb-0">Plan Básico</h5>
                                <div class="ml-auto"><span class="badge badge-success font-weight-normal p-2">Más Económico</span></div>
                            </div>
                            <div class="row">
                                <div class="col-lg-5 text-center">
                                    <div class="price-box my-3">
                                        <sup>$</sup><span class="text-dark display-5">10.000</span>
                                        <h6 class="font-weight-light">1 MES</h6>
                                        <a class="btn btn-dark font-14 border-0 text-white p-3 btn-block mt-3" href="confirmar-suscripcion?id_tipo=1">SUSCRIBIRME</a>
                                    </div>
                                </div>
                                <div class="col-lg-7 align-self-center">
                                    <ul class="list-inline pl-3 font-14 font-weight-medium text-dark">
                                        <li><i class="icon-check text-info "></i><p>50 Km de recorrido</p></li>
                                        <li><i class="icon-check text-info "></i><p>KM excedido: $350</p></li>
                                        <li><i class="icon-check text-info "></i><p> Cambio de tiempo/lugar estipulado: $1200</p></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- column  -->
                <!-- column  -->
                <div class="col-md-6">
                    <div class="card card-shadow border-0 mb-4">
                        <div class="card-body p-4">
                            <div class="d-flex align-items-center">
                                <h5 class="font-medium m-b-0">Plan Oro</h5>
                            </div>
                            <div class="row">
                                <div class="col-lg-5 text-center">
                                    <div class="price-box my-3">
                                        <sup>$</sup><span class="text-dark display-5">15.000</span>
                                        <h6 class="font-weight-light">1 MES</h6>
                                        <a class="btn btn-dark border-0 font-14 text-white p-3 btn-block mt-5" href="confirmar-suscripcion?id_tipo=2">SUSCRIBIRME</a>
                                    </div>
                                </div>
                                <div class="col-lg-7 align-self-center">
                                    <ul class="list-inline pl-3 font-14 font-weight-medium text-dark">
                                        <li><i class="icon-check text-info "></i><p>80 km de recorrido </p></li>
                                        <li><i class="icon-check text-info "></i><p>KM excedido: $300</p></li>
                                        <li><i class="icon-check text-info "></i><p>Permite eleccion de vehiculo</p></li>
                                        <li><i class="icon-check text-info "></i><p>Permite reservas con 3 días de anticipación</p></li>
                                        <li><i class="icon-check text-info "></i><p>Cambio de tiempo/lugar estipulado: $800</p></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- column  -->
            </div>
            <!-- row  -->
            <div class="row mt-4 ">
                <!-- column  -->
                <div class="col">
                    <div class="card card-shadow border-0 mb-4">
                        <div class="card-body p-4">
                            <div class="d-flex align-items-center">
                                <h5 class="font-weight-medium mb-0">Plan Diamante</h5>
                                <div class="ml-auto"><span class="badge badge-info font-weight-normal p-2">Popular</span></div>
                            </div>
                            <div class="row">
                                <div class="col-lg-5 text-center">
                                    <div class="price-box my-3">
                                        <sup>$</sup><span class="text-dark display-5">20.000</span>
                                        <h6 class="font-weight-light">1 MES</h6>
                                        <a class="btn btn-dark font-18 border-0 text-white p-3 btn-block mt-3" href="confirmar-suscripcion?id_tipo=3">SUSCRIBIRME</a>
                                    </div>
                                </div>
                                <div class="col-lg-7 align-self-center">
                                    <ul class="list-inline pl-3 font-14 font-weight-medium text-dark">
                                        <li><i class="icon-check text-info "></i><p>200 km de recorrido</p></li>
                                        <li><i class="icon-check text-info "></i><p>Km excedido: $500</p></li>
                                        <li><i class="icon-check text-info "></i><p>Eleccion de vehiculo (incluye gama alta)</p></li>
                                        <li><i class="icon-check text-info "></i><p>Reservar con cualquier anticipación</p></li>
                                        <li><i class="icon-check text-info "></i><p>Cambio de tiempo/lugar estipulado SIN COSTO</p></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
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