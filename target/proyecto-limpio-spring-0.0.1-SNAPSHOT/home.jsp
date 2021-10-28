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
    <link rel="stylesheet" type="text/css" href="css/home.css"/>

    <title>Proyecto - Alquiler de autos</title>
</head>
<body>
<jsp:include page="WEB-INF/vistas/header.jsp" />

<section>
    <div class="portada">
        <div class="container">
            <h1 class="display-3 p-4">Bienvenidos</h1>
        </div>
    </div>
</section>
<section>
    <div class="pricing6 py-5 bg-light">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-8 text-center">
                    <h3 class="mb-3">Alquil� un auto a un precio accesible</h3>
                    <h6 class="subtitle font-weight-normal">Contamos con 3 planes diferentes</h6>
                </div>
            </div>
            <div class="container mt-5">
                <div class="row">
                    <div class="col-md-4">
                        <div class="card-box">
                            <div class="card-title">
                                <h2>PLAN B�SICO</h2>
                                <p>50Km de recorrido</p>
                                <p>KM excedido: $350</p>
                                <p> Cambio de tiempo/lugar estipulado: $1200</p>
                            </div>
                            <div class="col-lg-5">
                                <div class="price-box my-3">
                                    <sup>$</sup><span class="text-dark display-5">2.600</span>
                                    <h6 class="font-weight-light">1 MES</h6>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 ">
                        <div class="card-box bg-dark text-light">
                            <div class="card-title">
                                <h2 class="text-light">PLAN ORO</h2>
                                <p>80km de recorrido </p>
                                <p>KM excedido: $300</p>
                                <p> Permite eleccion de vehiculo</p>
                                <p>Permite reservas con 3 d�as de anticipaci�n</p>
                                <p> Cambio de tiempo/lugar estipulado: $800</p>
                            </div>
                            <div class="col-lg-5">
                                <div class="price-box my-3">
                                    <sup class="text-light">$</sup><span class=" display-5 text-light">24.000</span>
                                    <h6 class="font-weight-light text-light">12 Meses</h6>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="card-box">
                            <div class="card-title">
                                <h2>PLAN DIAMANTE</h2>
                                <p>200km de recorrido</p>
                                <p> Km excedido: $500</p>
                                <p> Eleccion de vehiculo (incluye gama alta)</p>
                                <p> Reservar con cualquier anticipaci�n</p>
                                <p>Cambio de tiempo/lugar estipulado SIN COSTO</p>
                            </div>
                            <div class="col-lg-5">
                                <div class="price-box my-3">
                                    <sup>$</sup><span class="text-dark display-5">30.000</span>
                                    <h6 class="font-weight-light">12 Meses</h6>
                                </div>
                            </div>
                        </div>
                    </div>

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