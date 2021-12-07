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
    <link rel="icon" href="img/favicon.ico" type="image/png" />
    <title>Proyecto - Alquiler de autos</title>
</head>
<body>
<jsp:include page="header.jsp" />
<section class="bg-light pb-5 py-2">
    <div class="container">
        <h1 class="text-center display-4 p-4">¡Hola <c:out value="${nombre}"/>!</h1>
        <p class="text-center">Te queremos comentar que al suscribirte, alquilar y valorar autos sumas puntos para obtener un mes con nuestro mejor plan gratis!</p>
        <div class="d-flex justify-content-end mr-4">
            <p style="font-size: 20px" class="text-success p-3 rounded">Tu puntaje actual es: <span class="font-weight-bold">${puntaje}</span> </p>
        </div>

        <c:if test="${puntaje >= 1000}">
            <div class="alert alert-success alert-dismissible fade show text-center" role="alert">
                <strong>¡ CONSEGUISTE 1000 PUNTOS !</strong>
                <p class="mt-3">Tenés un mes gratis con el plan Diamante</p>
                <a href="suscripcion-gratis" class="btn btn-dark mt-2 mb-3">SUSCRIBIRME AL PLAN</a>
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </c:if>

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

        <div class="row d-flex justify-content-around">
            <div class="card col-md-3 mb-2">
                <img class="card-img-top mt-3" src="img/alquilarAuto.jpg" alt="Mujer en un auto">
                <div class="d-flex justify-content-center">
                    <div class="card-body d-flex flex-column">
                        <h5 class="card-title text-center">Alquilar</h5>
                        <p class="card-text text-center">Reservá el auto que mas te guste.</p>
                        <c:choose>
                            <c:when test="${tieneSuscripcion==false}">
                                <button type="button" class="btn btn-dark mt-3 mb-3" data-toggle="modal" data-target="#modalAlquilerAuto">Alquilar auto</button>
                            </c:when>
                            <c:otherwise>
                            <a href="alquilar-auto" class="btn btn-dark mt-3 mb-3">Ver autos</a>
                            </c:otherwise>
                         </c:choose>
                    </div>
                </div>
            </div>

                <c:choose>
                    <c:when test="${tieneSuscripcion==false}">
                        <div class="card col-md-3 text-center mb-2" >
                            <img class="card-img-top mt-3" src="img/noTieneSuscripcion.png" alt="no tiene suscripcion">
                            <div class="card-body d-flex flex-column">
                                <h5 class="card-title">Suscripción</h5>
                                <p class="card-text">No se encuentra suscripto a ningun plan :(</p>
                                <a href="ir-a-suscribir" class="btn btn-info">¡Suscribirme!</a>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="card col-md-3 text-center mb-2">
                            <img class="card-img-top mt-3" src="img/suscribirse.jpg" alt="tiene suscripcion">
                            <div class="card-body d-flex flex-column">
                                <h5 class="card-title">Suscripción</h5>
                                <p class="card-text">Nivel: <span class="text-primary">${tipoSuscripcion.getNombre()}</span></p>
                                <p>Finaliza el: ${suscripcion.getFechaFin()}</p>
                                <c:if test="${suscripcion.getRenovacion() == false}">
                                    <p class="card-text text-danger"><strong>Su suscripcion acabara al finalizar el plazo</strong> </p>
                                    <p class="card-text text-danger"><strong>${suscripcion.getFechaFin()}</strong> </p>
                                </c:if>
                                <a href="administrar-suscripcion" class="btn btn-info">Administrar</a>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>


            <div class="card col-md-3 text-center mb-2">
                <img class="card-img-top mt-3" src="img/verHistorialAlquileres.jpg" alt="Hombre con un auto">
                    <div class="card-body d-flex flex-column justify-content-center">
                        <h5 class="card-title text-center">Mis Alquileres</h5>
                        <p class="card-text text-center">Podrás verlos una vez que finalicen.</p>
                        <a href="lista-viajes" class="btn btn-dark ">Ver historial</a>
                    </div>
            </div>
        </div>
        <c:if test = "${alquileres.size() > 0}">
            <div class="col-sm-12 d-flex justify-content-center" style="margin-top: 10px">
                <h1 class="text-center">Alquileres actuales: ${alquileres.size()}</h1>
            </div>
        </c:if>
        <c:if test = "${not empty esperandoConfirmacion}">
            <div class="col-sm-12 d-flex justify-content-center" style="margin-top: 10px">
                <h1 class="text-center">El alquiler aún no recibe confirmacion de finalizacion. Una vez terminado aparecera en el historia de tus alquileres.</h1>
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
                                    <td><span class="font-weight-bold">Retiro del auto: </span> ${alquiler.f_egreso.plusDays(1)}</td>
                                </div>
                                <div>
                                    <td><span class="font-weight-bold">Devolución del auto: </span> ${alquiler.f_ingreso.plusDays(1)}</td>
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

        <!-- Modal -->
        <div class="modal fade" id="modalAlquilerAuto" tabindex="-1" role="dialog" aria-labelledby="modalAlquilerAutoLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5>Lo sentimos</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        No podés alquilar un auto sin una suscripción activa.
                    </div>
                    <div class="modal-footer">
                        <a href="ir-a-suscribir"><button type="button" class="btn btn-info">Suscribirme</button></a>
                    </div>
                </div>
            </div>
        </div>
        <!--Fin del modal-->


</section>
<jsp:include page="footer.jsp" />
</body>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
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
