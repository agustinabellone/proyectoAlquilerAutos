<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
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
    <link rel="stylesheet" type="text/css" href="css/planes.css"/>
    <title>Proyecto - Alquiler de autos</title>
</head>
<body>
<jsp:include page="header.jsp" />
<section>
    <div class="container">
        <div>
            <h1 class="display-4 p-4 text-center">Administre su Suscripcion</h1>
            <c:if test="${not empty errorDarDeBaja}">
                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    <strong>-Error-</strong> ${errorDarDeBaja}.
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
            </c:if>
        </div>
        <div>
            <h3 class="display-6 p-4 text-left">Usted se encuentra suscripto al nivel <span class="text-primary">${tipoSuscripcion.getNombre()}</span></h3>
            <c:if test="${suscripcion.getRenovacion() == false}">
                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    <strong class="text-danger p-2">Su suscripcion fue dada de baja y no sera renovada automaticamente</strong>
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
            </c:if>
        </div>
        <div>
            <h4 class="display-8 p-4 text-left text-success">Aqui puede mejorar su nivel de suscripcion o descactivarla</h4>
        </div>
    </div>
</section>
<section>
    <div class="pricing6 py-5 bg-light">
        <div class="container">
            <!-- row  -->
            <c:if test="${tipoSuscripcion.getNombre()=='Basico'}">
                <div class="row mt-4">
                    <!-- column  -->
                    <div class="col-md-6">
                        <div class="card card-shadow border-0 mb-4">
                            <div class="card-body p-4">
                                <div class="d-flex align-items-center">
                                    <h5 class="font-medium m-b-0">Plan Oro</h5>
                                </div>
                                <div class="row">
                                    <div class="col-lg-5 text-center">
                                        <div class="price-box my-3">
                                            <sup>$</sup><span class="text-dark display-5">15.000</span>
                                            <h6 class="font-weight-light">1 MES</h6>
                                            <a class="btn btn-dark border-0 font-14 text-white p-3 btn-block mt-5" href="mejorar-suscripcion?id_mejora=2&nombre_mejora=Oro">Subir de Nivel!</a>
                                        </div>
                                    </div>
                                    <div class="col-lg-7 align-self-center">
                                        <ul class="list-inline pl-3 font-14 font-weight-medium text-dark">
                                            <li><i class="icon-check text-info "></i><p>80 km de recorrido </p></li>
                                            <li><i class="icon-check text-info "></i><p>KM excedido: $300</p></li>
                                            <li><i class="icon-check text-info "></i><p>Permite eleccion de vehiculo</p></li>
                                            <li><i class="icon-check text-info "></i><p>Permite reservas con 3 días de anticipación</p></li>
                                            <li><i class="icon-check text-info "></i><p>Cambio de tiempo/lugar estipulado: $800</p></li>
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
                                    <h5 class="font-weight-medium mb-0">Plan Diamante</h5>
                                </div>
                                <div class="row">
                                    <div class="col-lg-5 text-center">
                                        <div class="price-box my-3">
                                            <sup>$</sup><span class="text-dark display-5">20.000</span>
                                            <h6 class="font-weight-light">1 MES</h6>
                                            <a class="btn btn-dark border-0 font-14 text-white p-3 btn-block mt-5" href="mejorar-suscripcion?id_mejora=3&nombre_mejora=Diamante">Subir de Nivel!</a>
                                        </div>
                                    </div>
                                    <div class="col-lg-7 align-self-center">
                                        <ul class="list-inline pl-3 font-14 font-weight-medium text-dark">
                                            <li><i class="icon-check text-info "></i><p>200 km de recorrido</p></li>
                                            <li><i class="icon-check text-info "></i><p>Km excedido: $500</p></li>
                                            <li><i class="icon-check text-info "></i><p>Eleccion de vehiculo (incluye gama alta)</p></li>
                                            <li><i class="icon-check text-info "></i><p>Reservar con cualquier anticipación</p></li>
                                            <li><i class="icon-check text-info "></i><p>Cambio de tiempo/lugar estipulado SIN COSTO</p></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- column  -->
                </div>
            </c:if>
            <c:if test="${tipoSuscripcion.getNombre()=='Oro'}">
                <div >
                    <!-- column  -->
                    <div >
                        <div class="card card-shadow border-0 mb-4">
                            <div class="card-body p-4">
                                <div class="d-flex align-items-center">
                                    <h5 class="font-weight-medium mb-0">Plan Diamante</h5>
                                </div>
                                <div class="row">
                                    <div class="col-lg-5 text-center">
                                        <div class="price-box my-3">
                                            <sup>$</sup><span class="text-dark display-5">20.000</span>
                                            <h6 class="font-weight-light">1 MES</h6>
                                            <a class="btn btn-dark border-0 font-14 text-white p-3 btn-block mt-5" href="mejorar-suscripcion?id_mejora=3&nombre_mejora=Diamante">Subir de Nivel!</a>
                                        </div>
                                    </div>
                                    <div class="col-lg-7 align-self-center">
                                        <ul class="list-inline pl-3 font-14 font-weight-medium text-dark">
                                            <li><i class="icon-check text-info "></i><p>200 km de recorrido</p></li>
                                            <li><i class="icon-check text-info "></i><p>Km excedido: $500</p></li>
                                            <li><i class="icon-check text-info "></i><p>Eleccion de vehiculo (incluye gama alta)</p></li>
                                            <li><i class="icon-check text-info "></i><p>Reservar con cualquier anticipación</p></li>
                                            <li><i class="icon-check text-info "></i><p>Cambio de tiempo/lugar estipulado SIN COSTO</p></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- column  -->
                </div>
            </c:if>
            <!-- row  -->
                    <div class="card card-shadow p-4 text-center  ">
                        <h3>Cancele su suscripcion al finalizar el mes</h3>
                        <button type="button" class="btn btn-danger border-0 text-white p-3 mt-3" style="width: 50%; margin: auto" data-toggle="modal" data-target="#desactivacionModal">Cancelar</button>
                    </div>
        </div>
    </div>
    <!-- Modal -->
    <div class="modal fade" id="desactivacionModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Cancelar suscripcion</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    ¿Estás seguro? Su suscripcion continuara hasta que se venza.
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                    <a href="darDeBajaSuscripcion"><button type="button" class="btn btn-danger">Cancelar suscripcion</button></a>
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