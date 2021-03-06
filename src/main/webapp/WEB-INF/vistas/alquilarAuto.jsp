<%@ page language="java" contentType="text/html;charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
            <h1 class="display-4 p-4">Nuestros autos</h1>
        </div>
        <section>
            <div class="pricing6 py-5 my-4">
                <div class="container col-md-9">
                    <div class="col">
                        <c:forEach items="${autosDisponibles}" var="auto">
                        <div class="col-md-12 ">
                            <div class="card card-shadow border-0 mb-4">
                                <div class="card-body p-4">
                                    <div class="d-flex justify-content-between">
                                        <h5 class="font-weight-bold">${auto.marca.descripcion}</h5>
                                        <span class="font-weight-bold text-info"> Gama ${auto.gama}</span>
                                    </div>
                                    <div class="col">
                                        <div class="col-lg-12">
                                            <div class="row mt-3">
                                                <div class="col-lg-6 align-self-center">
                                                    <ul class="list-inline pl-3 font-16 font-weight-medium text-dark mt-3">
                                                        <li class="py-2"><i class="icon-check text-info mr-2"></i>Patente: <span class="font-weight-bold">${auto.patente}</span>
                                                        </li>
                                                        <li class="py-2"><i class="icon-check text-info mr-2"></i>Marca:<span class="font-weight-bold"> ${auto.marca.descripcion}</span>
                                                        </li>
                                                        <li class="py-2"><i class="icon-check text-info mr-2"></i>Modelo: <span class="font-weight-bold"> ${auto.modelo.descripcion}</span>
                                                        </li>
                                                        <li class="py-2"><i class="icon-check text-info mr-2"></i>Kilometros: <span class="font-weight-bold"> ${auto.km} km</span>
                                                        </li>
                                                    </ul>
                                                </div>
                                                <div class="col-lg-6">
                                                    <div class="container">
                                                        <img src= "${auto.imagen}"
                                                             alt="" style="width: 100%; height: auto;">
                                                    </div>
                                                </div>
                                                <div class="col-sm-12 d-flex justify-content-around">
                                                    <a href="elegir-fechas?id_auto=${auto.id}&imagen_auto=${auto.imagen}" class="btn btn-info mt-5">ALQUILAR AUTO</a>
                                                    <a href="valoraciones-auto?id_auto=${auto.id}" class="btn btn-outline-dark mt-5">VER VALORACIONES</a>
                                                </div>
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

</html>