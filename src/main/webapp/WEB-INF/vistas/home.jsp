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
    <link rel="stylesheet" type="text/css" href="css/home.css"/>
    <link rel="stylesheet" type="text/css" href="css/planes.css"/>

    <title>Proyecto - Alquiler de autos</title>
</head>
<jsp:include page="header.jsp" />
<body>
<section>
    <div class="portada">
        <div class="container portada-contenido">
            <h1 class="display-3 mt-3 text-center">¡Te damos la bienvenida!</h1>
            <h2 class="text-center">Conocé nuestros vehiculos y elegí el que más te guste.</h2>
        </div>
    </div>
</section>
<section class="bg-light">
    <div class="pricing6 py-5">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-8 text-center">
                    <h3 class="mb-3">Alquilá un auto a un precio accesible</h3>
                    <h6 class="subtitle font-weight-normal">Contamos con 3 planes diferentes</h6>
                </div>
            </div>
            <div class="container mt-5">
                <div class="row">
                    <div class="col-md-4">
                        <div class="card-box bg-secondary text-white text-center rounded">
                            <div class="card-title">
                                <h2 class="mb-5 text-white">PLAN BÁSICO</h2>
                                <p>50 Km de recorrido</p>
                                <p>KM excedido: $350</p>
                                <p> Cambio de tiempo/lugar estipulado: $1200</p>
                            </div>
                            <div>
                                <div class="price-box my-3">
                                    <sup>$</sup><span class="text-white display-5 text-center">10.000</span>
                                    <h6 class="font-weight-medium text-white text-center">1 MES</h6>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 ">
                        <div class="card-box bg-dark text-light text-center rounded">
                            <div class="card-title">
                                <h2 class="mb-5 text-light">PLAN ORO</h2>
                                <p>80 km de recorrido </p>
                                <p>KM excedido: $300</p>
                                <p> Permite eleccion de vehiculo</p>
                                <p>Permite reservas con 3 días de anticipación</p>
                                <p> Cambio de tiempo/lugar estipulado: $800</p>
                            </div>
                            <div>
                                <div class="price-box my-3">
                                    <sup class="text-light">$</sup><span class=" display-5 text-light">15.000</span>
                                    <h6 class="font-weight-medium text-light">1 MES</h6>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 text-dark text-center">
                        <div class="card-box bg-secondary text-white rounded">
                            <div class="card-title">
                                <h2 class="mb-5 text-white">PLAN DIAMANTE</h2>
                                <p>200 km de recorrido</p>
                                <p> Km excedido: $500</p>
                                <p> Eleccion de vehiculo (incluye gama alta)</p>
                                <p> Reservar con cualquier anticipación</p>
                                <p>Cambio de tiempo/lugar estipulado SIN COSTO</p>
                            </div>
                            <div>
                                <div class="price-box my-3">
                                    <sup>$</sup><span class="text-white display-5">20.000</span>
                                    <h6 class="font-weight-medium text-white">1 MES</h6>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
    <div class="pricing6 py-5">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-8 text-center">
                    <h3 class="mb-3">Elegí el lugar que te quede mejor</h3>
                    <h6 class="subtitle font-weight-normal mb-3">Contamos con 3 garages</h6>
                </div>
            </div>
            <div class="d-flex justify-content-around">
                <div class="card" style="width: 18rem;">
                    <img class="card-img-top" src="img/sedeMorón.jpg" alt="Card image cap">
                    <div class="card-body">
                        <h5 class="text-center">Sede Morón</h5>
                        <p class="card-text text-center">Av. Argentina 784 entre Primera Junta y Noguera</p>
                    </div>
                </div>
                <div class="card" style="width: 18rem;">
                    <img class="card-img-top" src="img/sedeLaMatanza.jpg" alt="Card image cap">
                    <div class="card-body">
                        <h5 class="text-center">Sede La Matanza</h5>
                        <p class="card-text text-center">Av. Argentina 784 entre Primera Junta y Noguera</p>
                    </div>
                </div>
                <div class="card" style="width: 18rem;">
                    <img class="card-img-top" src="img/sedeLiniers.jpg" alt="Card image cap">
                    <div class="card-body">
                        <h5 class="text-center">Sede Liniers</h5>
                        <p class="card-text text-center">Av. Argentina 784 entre Primera Junta y Noguera</p>
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