<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="css/planes.css">
    <title>Proyecto - Alquiler de autos</title>
</head>
<body>
<header class="d-flex flex-row-reverse p-3">

</header>
<section>
    <div class="container text-center">
        <div>
            <h1 class="display-4 p-4">Lista de alquileres</h1>
        </div>
    </div>
</section>
<section>
    <div class="pricing6 py-5 bg-light">
        <div class="container">
            <div class="col center-block">
                <c:forEach var="viaje" items= "${viajesObtenidos}" >
                    <div class="col-md-12">
                        <div class="card card-shadow border-0 mb-4">
                            <div class="card-body p-4">
                                <div class="d-flex align-items-center">
                                    <h5 class="font-weight-medium mb-0">Viajes</h5>
                                    </div>
                                </div>
                                <div class="col">
                                    <div class="col-lg-12">
                                        <div class="row mt-3">
                                            <div class="col-lg-6 align-self-center">
                                                <ul class="list-inline pl-3 font-16 font-weight-medium text-dark mt-3">
                                                    <li class="py-2"><i class="icon-check text-info mr-2"></i> <span> ${viaje.auto.id}</span>
                                                    </li>
                                                    <li class="py-2"><i class="icon-check text-info mr-2"></i> <span>${viaje.f_ingreso}</span>
                                                    </li>
                                                    <li class="py-2"><i class="icon-check text-info mr-2"></i> <span>${viaje.f_egreso}</span>

                                                </ul>
                                            </div>
                                            <div class="col-lg-6">
                                                <div class="container">
                                                    <img src="https://www.automobilemag.com/uploads/sites/11/2014/02/2015-Ford-Focus-hatchback-front-view2.jpg?fit=around%7C875:492"
                                                         alt="" style="width: 100%; height: auto;">
                                                </div>
                                            </div>
                                            <a class="btn btn-info-gradiant font-14 border-0 text-white p-3 btn-block mt-3"
                                               href="valorar-auto?id_auto=${viaje.auto.id}">VALORAR VEHICULO</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </c:forEach>
            </div>
        </div>
    </div>
</section>


<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>


</body>
</html>
