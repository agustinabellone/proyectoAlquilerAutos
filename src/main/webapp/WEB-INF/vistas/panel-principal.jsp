<%--
  Created by IntelliJ IDEA.
  User: Ezequiel
  Date: 22/12/21
  Time: 07:03
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
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
    <link rel="icon" href="img/favicon.ico" type="image/png"/>
    <title>Administracion</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<h1 class="text-center">Bienvenido ${nombre}</h1>
<!-- Content Row -->
<div class="row">
    <!-- Usuarios nuevos -->
    <div class="col-xl-3 col-md-6 mb-4">
        <div class="card border-left-primary shadow h-100 py-2">
            <div class="card-body">
                <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                        <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                            Clientes no Suscriptos
                        </div>
                        <div class="h5 mb-0 font-weight-bold text-gray-800">
                            <c:if test="${not empty clientes_no_suscriptos}">
                                ${clientes_no_suscriptos.size()}
                            </c:if>
                            <c:if test="${empty clientes_no_suscriptos}">
                                0
                            </c:if>
                        </div>
                    </div>
                    <div class="col-auto">
                        <a href="clientes-no-suscriptos" class="text-primary font-weight-bold text-lg">Ver</a>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Suscripciones nuevas -->
    <div class="col-xl-3 col-md-6 mb-4">
        <div class="card border-left-success shadow h-100 py-2">
            <div class="card-body">
                <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                        <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                            Clientes Suscriptos
                        </div>
                        <div class="h5 mb-0 font-weight-bold text-gray-800">
                            <c:if test="${not empty lista_de_suscripto}">
                                ${lista_de_suscripto.size()}
                            </c:if>
                            <c:if test="${empty lista_de_suscripto}">
                                0
                            </c:if>
                        </div>
                    </div>
                    <div class="col-auto">

                        <a href="clientes-suscriptos"
                           class="text-success font-weight-bold text-lg">Ver</a>

                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- alquilres nuevos -->
    <div class="col-xl-3 col-md-6 mb-4">
        <div class="card border-left-info shadow h-100 py-2">
            <div class="card-body">
                <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                        <div class="text-xs font-weight-bold text-info text-uppercase mb-1">
                            Alquileres Nuevos
                        </div>
                        <div class="h5 mb-0 font-weight-bold text-gray-800">
                            <c:if test="${not empty autos_alquilados}">
                                ${autos_alquilados.size()}
                            </c:if>
                            <c:if test="${empty autos_alquilados}">
                                0
                            </c:if>
                        </div>
                    </div>
                    <div class="col-auto">
                        <a href="autos-alquilados" class="text-info font-weight-bold text-lg">Ver</a>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Asignaciones de roles pendientes -->
    <div class="col-xl-3 col-md-6 mb-4">
        <div class="card border-left-warning shadow h-100 py-2">
            <div class="card-body">
                <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                        <div class="text-xs font-weight-bold text-warning text-uppercase mb-1">
                            Pendiente de Rol
                        </div>
                        <div class="h5 mb-0 font-weight-bold text-gray-800">
                            <c:if test="${not empty pendientes_de_rol}">
                                ${pendientes_de_rol.size()}
                            </c:if>
                            <c:if test="${empty pendientes_de_rol}">
                                0
                            </c:if>
                        </div>
                    </div>
                    <div class="col-auto">

                        <a href="asignacion-de-rol"
                           class="text-warning font-weight-bold text-lg">Ver</a>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<section>
    <div class="pricing6 py-5 my-4">
        <div class="container col-md-9">
            <div class="col">
                <c:forEach items="${autos_alquilados}" var="auto">
                    <div class="col-md-12 ">
                        <div class="card card-shadow border-0 mb-4">
                            <div class="card-body p-4">
                                <div class="d-flex justify-content-between">
                                    <h4 class="font-weight-bold">${auto.situacion.toString()}</h4>
                                    <h5 class="font-weight-bold">${auto.marca.descripcion}</h5>
                                    <span class="font-weight-bold text-info"> Gama ${auto.gama}</span>
                                </div>
                                <div class="col">
                                    <div class="col-lg-12">
                                        <div class="row mt-3">
                                            <div class="col-lg-6 align-self-center">
                                                <ul class="list-inline pl-3 font-16 font-weight-medium text-dark mt-3">
                                                    <li class="py-2"><i class="icon-check text-info mr-2"></i>Patente:
                                                        <span class="font-weight-bold">${auto.patente}</span>
                                                    </li>
                                                    <li class="py-2"><i
                                                            class="icon-check text-info mr-2"></i>Marca:<span
                                                            class="font-weight-bold"> ${auto.marca.descripcion}</span>
                                                    </li>
                                                    <li class="py-2"><i class="icon-check text-info mr-2"></i>Modelo:
                                                        <span class="font-weight-bold"> ${auto.modelo.descripcion}</span>
                                                    </li>
                                                    <li class="py-2"><i class="icon-check text-info mr-2"></i>Kilometros:
                                                        <span class="font-weight-bold"> ${auto.km} km</span>
                                                    </li>
                                                </ul>
                                            </div>
                                            <div class="col-lg-6">
                                                <div class="container">
                                                    <img src="${auto.imagen}"
                                                         alt="" style="width: 100%; height: auto;">
                                                </div>
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
<jsp:include page="footer.jsp"/>
</body>
</html>
