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
            <h1 class="text-center">Opiniones sobre el auto </h1>
        </div>
        <section>
            <div class="row mt-3">
                <div class="col-lg-6 align-self-center">
                    <h2>${valoracionPromedio}</h2>
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
                    <h3>Promedio entre ${cantidadValoraciones} valoraciones</h3>
                </div>

                <div class="col-lg-6">
                    <div class="container">
                        <img src="${auto.imagen}" alt="" style="width: 100%; height: auto;">
                    </div>
                </div>

<c:forEach var="valoracion" items= "${listadoValoracionesAuto}" >
                <div class="col-md-12">
                    <div class="card card-shadow border-0 mb-4">
                        <div class="card-body p-4">
                            <div class="col">
                                <div class="col-lg-12">
                                    <div class="row mt-3">
                                        <div class="col-lg-6 align-self-center">
                                            <ul class="list-inline pl-3 font-16 font-weight-medium text-dark mt-3">
                                                <li class="py-2"><i class="icon-check text-info mr-2"></i> <span> Cantidad de estrellas: ${valoracion.valoracion} </span>
                                                </li>
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

                                                <li class="py-2"><i class="icon-check text-info mr-2"></i> <span>${valoracion.comentarios}</span>
                                                </li>

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
            <caption><p class="text-center text-danger">${mensaje}</p></caption>
            <br>
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
