<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <title>Proyecto - Alquiler de autos</title>
</head>
<body>
<jsp:include page="header.jsp" />
<section class="bg-light p-5">
    <div class="container">
        <h1 class="mb-5">¡Hola <c:out value="${nombre}"/>!</h1>

        <c:if test="${not empty confirmacionDarDeBaja}">
            <div class="alert alert-success alert-dismissible fade show" role="alert">
                <strong>-</strong> ${confirmacionDarDeBaja}<strong>-</strong>
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </c:if>
        <c:if test="${not empty confirmacionDarDeAlta}">
            <div class="alert alert-success alert-dismissible fade show" role="alert">
                <strong>-</strong> ${confirmacionDarDeAlta}<strong>-</strong>
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </c:if>

        <div class="d-flex justify-content-around">
            <div class="card col-sm-3">
                <img class="card-img-top mt-3" src="img/alquilarAuto.jpg" alt="Mujer en un auto">
                <div class="d-flex justify-content-center">
                    <a href="alquilar-auto" class="btn btn-dark mt-3 mb-3">Alquilar auto</a>
                </div>
            </div>

            <div class="container col-sm-3 d-flex justify-content-center text-center">
                <c:choose>
                    <c:when test="${tieneSuscripcion==false}">
                        <div class="card" style="width: 18rem;">
                            <img class="card-img-top" src="img/noTieneSuscripcion.png" alt="no tiene suscripcion">
                            <div class="card-body">
                                <h5 class="card-title">Suscripción</h5>
                                <p class="card-text">No se encuentra suscripto a ningun plan :(</p>
                                <a href="ir-a-suscribir" class="btn btn-success">¡Suscribirme!</a>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="card" style="width: 18rem;">
                            <img class="card-img-top" src="img/suscribirse.jpg" alt="tiene suscripcion">
                            <div class="card-body">
                                <h5 class="card-title">Suscripción</h5>
                                <p class="card-text">Nivel: <span class="text-primary">${tipoSuscripcion.getNombre()}</span></p>
                                <c:if test="${suscripcion.getRenovacion() == false}">
                                    <p class="card-text text-danger"><strong>Su suscripcion acabara al finalizar el plazo</strong> </p>
                                    <p class="card-text text-danger"><strong>${suscripcion.getFechaFin()}</strong> </p>
                                </c:if>
                                <a href="administrar-suscripcion" class="btn btn-success">Administrar</a>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>

            <div class="card col-sm-3">
                <img class="card-img-top mt-3" src="img/verHistorialAlquileres.jpg" alt="Hombre con un auto">
                <div class="d-flex justify-content-center">
                    <a href="lista-viajes" class="btn btn-dark mt-4">Historial de alquileres</a>
                </div>
            </div>
        </div>
        <c:if test = "${alquileres.size() > 0}">
            <div class="col-sm-12 d-flex justify-content-center" style="margin-top: 10px">
                <h1 class="text-center">Alquileres actuales: ${alquileres.size()}</h1>
            </div>
        </c:if>
        <div class="container d-flex justify-content-center text-center">
            <div class="col-sm-9">
                    <c:forEach items="${alquileres}" var="alquiler">
                            <div class="d-flex flex-column bg-dark p-2 mt-2 mb-2 text-light justify-content-center rounded">
                                <div>
                                    <td><span class="font-weight-bold">Auto: </span> ${alquiler.auto.marca.descripcion} ${alquiler.auto.modelo.descripcion}</td>
                                </div>
                                <div>
                                    <td><span class="font-weight-bold">Retiro del auto: </span> ${alquiler.f_egreso}</td>
                                </div>
                                <div>
                                    <td><span class="font-weight-bold">Devolución del auto: </span> ${alquiler.f_ingreso}</td>
                                </div>
                                <div class="col-sm-12 d-flex justify-content-center mt-2">
                                    <a class="btn btn-danger" href='finalizar-alquiler?alquilerID=${alquiler.id}'>Finalizar alquiler</a>
                                </div>
                            </div>
                    </c:forEach>
            </div>
        </div>

    </div>
    <c:if test="${not empty mensaje}">
        <caption><p class="text-center text-danger">${mensaje}</p></caption>
        <br>
    </c:if>
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
