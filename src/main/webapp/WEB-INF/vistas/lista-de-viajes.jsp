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
<jsp:include page="header.jsp" />
<section>
    <div class="container text-center">
        <div>
            <h1 class="display-4 p-4">Alquileres finalizados</h1>
        </div>
    </div>
</section>
<section class="mb-5">
    <div class="pricing6 py-5 bg-light">
        <div class="container">
            <div class="col center-block">
                <div class="d-flex align-items-center">
                    <h5 class="font-weight-medium mb-3">Cantidad: ${viajesObtenidos.size()} </h5>
                </div>
            </div>
                <c:forEach var="viaje" items= "${viajesObtenidos}" >
                    <div class="col-md-12">
                        <div class="card card-shadow border-0 mb-4">
                            <div class="card-body p-4">
                                <div class="col">
                                    <div class="col-lg-12">
                                        <div class="row mt-3">
                                            <div class="col-lg-6 align-self-center">
                                                <ul class="list-inline pl-3 font-16 font-weight-medium text-dark mt-3">
                                                    <li class="py-2"><i class="icon-check text-info mr-2"></i> <span>Auto: ${viaje.auto.marca.descripcion} ${viaje.auto.modelo.descripcion}</span>
                                                    </li>
                                                    <li class="py-2"><i class="icon-check text-info mr-2"></i> <span>Patente: ${viaje.auto.patente}</span>
                                                    </li>
                                                    <li class="py-2"><i class="icon-check text-info mr-2"></i> <span> Retiro del auto: ${viaje.f_ingreso}</span>
                                                    </li>
                                                    <li class="py-2"><i class="icon-check text-info mr-2"></i> <span> Devolución del auto: ${viaje.f_egreso}</span>

                                                </ul>
                                            </div>
                                            <div class="col-lg-6">
                                                <div class="container">
                                                    <img src="${viaje.auto.imagen}" class="card-img-top" alt="...">
                                                </div>
                                            </div>
                                            <c:if test="${viaje.estadoValoracionAuto== false}">
                                            <div class="col-sm-12 d-flex justify-content-center">
                                                <a class="btn btn-dark mt-5"
                                               href="valorar-auto?id_auto=${viaje.auto.id}&id_alquiler=${viaje.id}">VALORAR VEHICULO</a>
                                            </div>
                                            </c:if>
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

<jsp:include page="footer.jsp" />
</body>
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
</html>
