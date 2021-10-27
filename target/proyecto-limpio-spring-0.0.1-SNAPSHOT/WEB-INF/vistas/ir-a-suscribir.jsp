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
    <link rel="stylesheet" type="text/css" href="css/planes.css"/>
    <title>Proyecto - Alquiler de autos</title>
</head>
<body>
<jsp:include page="header.jsp" />
<section>
    <div class="container">
        <div>
            <h1 class="display-4 p-4">Suscribase a uno de nuestros PLANES</h1>
        </div>
    </div>
</section>
<section>
    <div class="pricing6 py-5 bg-light">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-8 text-center">
                    <h3 class="mb-3">Alquila un auto a un precio accesible</h3>
                    <h6 class="subtitle font-weight-normal">Contamos con 3 planes diferentes</h6>
                </div>
            </div>
            <!-- row  -->
            <div class="row mt-4">
                <!-- column  -->
                <div class="col-md-6">
                    <div class="card card-shadow border-0 mb-4">
                        <div class="card-body p-4">
                            <div class="d-flex align-items-center">
                                <h5 class="font-weight-medium mb-0">Plan basico</h5>
                                <div class="ml-auto"><span class="badge badge-danger font-weight-normal p-2">Mas Economico</span></div>
                            </div>
                            <div class="row">
                                <div class="col-lg-5 text-center">
                                    <div class="price-box my-3">
                                        <sup>$</sup><span class="text-dark display-5">2.600</span>
                                        <h6 class="font-weight-light">1 MES</h6>
                                        <a class="btn btn-info-gradiant font-14 border-0 text-white p-3 btn-block mt-3" href="confirmar-suscripcion?id_tipo=1">SUSCRIBIRME</a>
                                    </div>
                                </div>
                                <div class="col-lg-7 align-self-center">
                                    <ul class="list-inline pl-3 font-14 font-weight-medium text-dark">
                                        <li class="py-2"><i class="icon-check text-info mr-2"></i> <span>Lorem ipsum dolor sit amet</span></li>
                                        <li class="py-2"><i class="icon-check text-info mr-2"></i> <span>Lorem ipsum dolor sit amet</span></li>
                                        <li class="py-2"><i class="icon-check text-info mr-2"></i> <span>Lorem ipsum dolor sit amet</span></li>
                                        <li class="py-2"><i class="icon-check text-info mr-2"></i> <span>Lorem ipsum dolor sit amet</span></li>
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
                                <h5 class="font-medium m-b-0">Plan Premium</h5>
                            </div>
                            <div class="row">
                                <div class="col-lg-5 text-center">
                                    <div class="price-box my-3">
                                        <sup>$</sup><span class="text-dark display-5">24.000</span>
                                        <h6 class="font-weight-light">12 Meses</h6>
                                        <a class="btn btn-info-gradiant border-0 font-14 text-white p-3 btn-block mt-3" href="confirmar-suscripcion?id_tipo=2">SUSCRIBIRME</a>
                                    </div>
                                </div>
                                <div class="col-lg-7 align-self-center">
                                    <ul class="list-inline pl-3 font-14 font-weight-medium text-dark">
                                        <li class="py-2"><i class="icon-check text-info mr-2"></i> <span>Lorem ipsum dolor sit amet</span></li>
                                        <li class="py-2"><i class="icon-check text-info mr-2"></i> <span>Lorem ipsum dolor sit amet</span></li>
                                        <li class="py-2"><i class="icon-check text-info mr-2"></i> <span>Lorem ipsum dolor sit amet</span></li>
                                        <li class="py-2"><i class="icon-check text-info mr-2"></i> <span>Lorem ipsum dolor sit amet</span></li>
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
                                <h5 class="font-weight-medium mb-0">Plan basico</h5>
                                <div class="ml-auto"><span class="badge badge-danger font-weight-normal p-2">Popular</span></div>
                            </div>
                            <div class="row">
                                <div class="col-lg-5 text-center">
                                    <div class="price-box my-3">
                                        <sup>$</sup><span class="text-dark display-5">2.600</span>
                                        <h6 class="font-weight-light">1 MES</h6>
                                        <a class="btn btn-info-gradiant font-18 border-0 text-white p-3 btn-block mt-3" href="confirmar-suscripcion?id_tipo=3">SUSCRIBIRME</a>
                                    </div>
                                </div>
                                <div class="col-lg-7 align-self-center">
                                    <ul class="list-inline pl-3 font-14 font-weight-medium text-dark">
                                        <li class="py-2"><i class="icon-check text-info mr-2"></i> <span>Lorem ipsum dolor sit amet</span></li>
                                        <li class="py-2"><i class="icon-check text-info mr-2"></i> <span>Lorem ipsum dolor sit amet</span></li>
                                        <li class="py-2"><i class="icon-check text-info mr-2"></i> <span>Lorem ipsum dolor sit amet</span></li>
                                        <li class="py-2"><i class="icon-check text-info mr-2"></i> <span>Lorem ipsum dolor sit amet</span></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="d-flex justify-content-center">
            <form action=renovar-suscripcion method="post">
                <div class="form-group">
                    <label for="idCliente">Id del Cliente</label>
                    <input type="text" class="form-text" name="id" id="idCliente" aria-describedby="emailHelp" placeholder="Ingrese Id">
                    <small id="idHelp" class="form-text text-muted">Ingresa el Id del usuario ya suscripto</small>
                </div>
                <button type="submit" class="btn btn-primary ">Renovar</button>
            </form>
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