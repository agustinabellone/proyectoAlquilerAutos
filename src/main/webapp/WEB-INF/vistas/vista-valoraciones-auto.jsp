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
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <title>Proyecto - Alquiler de autos</title>
</head>
<body>
<jsp:include page="header.jsp" />
<section>
    <div class="container">
        <div class="col-sm-12 d-flex justify-content-center">
            <h1 class="display-4 p-4">Opiniones sobre el auto </h1>
        </div>
        <section>
            <div class="row mt-3">
                <div class="col-lg-6 align-self-center">
                    <div class="d-flex align-items-center justify-content-around">
                    <h2>Puntuación: ${valoracionPromedio}</h2>
                    <div>
                    <c:choose>
                        <c:when test = "${valoracionPromedio == 1 }">
                            <i class="fa fa-star"></i>
                        </c:when>

                        <c:when test = "${valoracionPromedio == 2 }">
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                        </c:when>

                        <c:when test = "${valoracionPromedio == 3 }">
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                        </c:when>

                        <c:when test = "${valoracionPromedio == 4}">
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                        </c:when>
                        <c:when test = "${valoracionPromedio == 5}">
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                        </c:when>
                        <c:otherwise>
                        </c:otherwise>
                    </c:choose>
                    </div>
                    </div>
                    <c:if test="${empty mensaje}">
                        <p class="text-center">Promedio entre ${cantidadValoraciones} valoraciones</p>
                    </c:if>

                </div>

                <div class="col-lg-6">
                    <div class="container">
                        <img src="${auto.imagen}" alt="" style="width: 100%; height: auto;">
                    </div>
                </div>

            <c:forEach var="valoracion" items= "${listadoValoracionesAuto}" >
                <div class="col-md-12">
                    <div class="card card-shadow border-0">
                        <div class="card-body">
                            <div class="col">
                                <div class="col-lg-12">
                                    <div class="row">
                                        <div class="col-lg-6 d-flex flex-column align-content-center bg-dark rounded">
                                            <ul class="list-inline font-16 font-weight-medium text-white mt-3">
                                                <li class=""><i class="icon-check text-info"></i> <span> Puntuación: ${valoracion.valoracion} </span></li>
                                                <c:choose>

                                                    <c:when test = "${valoracion.valoracion == 1 }">
                                                        <i class="fa fa-star"></i>
                                                    </c:when>

                                                    <c:when test = "${valoracion.valoracion == 2 }">
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star"></i>
                                                    </c:when>

                                                    <c:when test = "${valoracion.valoracion == 3 }">
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star"></i>
                                                    </c:when>

                                                    <c:when test = "${valoracion.valoracion == 4}">
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star"></i>
                                                    </c:when>
                                                    <c:when test = "${valoracion.valoracion == 5}">
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star"></i>
                                                    </c:when>

                                                    <c:otherwise>
                                                    </c:otherwise>

                                                </c:choose>

                                                <li class=""><span class="font-italic ">"${valoracion.comentarios}"</span></li>

                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            </c:forEach>
        </section>
        <c:if test="${not empty mensaje}">
            <div class="alert alert-danger text-center container mt-3" role="alert">
                    ${mensaje}
            </div>
            <div class="col-sm-12 d-flex justify-content-center">
                <a href="alquilar-auto" class="btn btn-dark" >Volver</a>
            </div>
        </c:if>
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
