<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
          crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="css/planes.css"/>
    <title>Document</title>
</head>
<jsp:include page="header.jsp"/>
<body>
<section>
    <div class="pricing6 py-5 bg-light">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-8 text-center">
                    <h3 class="mb-3">${cliente.nombre} confirma los datos para finalizar el alquiler</h3>
                </div>
            </div>
            <!-- row  -->
            <div class="row mt-12">
                <!-- column  -->
                <div class="col">
                    <div class="card card-shadow border-0 mb-4">
                        <div class="card-body p-4">
                            <div class="d-flex align-items-center">
                                <h5 class="font-weight-medium mb-0">Codigo de alquiler: ${alquiler.id}</h5>
                            </div>
                            <div class="row">
                                <div class="col-lg-6 text-center">
                                    <div class="class="list-inline pl-3 font-14 font-weight-medium text-dark">
                                    <h5 style="padding-top: 5px; padding-bottom: 5px">Inicio:</h5>
                                    <h5 style="padding-top: 5px; padding-bottom: 5px">Desde:</h5>
                                    <h5 style="padding-top: 5px; padding-bottom: 5px">Entrega el vehiculo en: </h5>
                                </div>
                                </div>
                                <div class="col-lg-6 align-self-center">
                                    <ul class="list-inline pl-3 font-16 font-weight-medium text-dark">
                                        <li class="py-2"><i class="icon-check text-info mr-2"></i> <span> ${fechaInicio}</span>
                                        </li>
                                        <li class="py-2"><i class="icon-check text-info mr-2"></i> <span> ${garagePartida.direccion}</span>
                                        </li>
                                        <c:if test="${not empty garage}">
                                            <li class="py-2"><i class="icon-check text-info mr-2"></i> <span>${garage.direccion}</span>
                                            </li>
                                        </c:if>
                                        <c:if test="${empty garage}">
                                            <li class="py-2"><i class="icon-check text-info mr-2"></i> <span>${garageLlegada.direccion}</span>
                                            </li>
                                        </c:if>
                                    </ul>
                                </div>
                            <div class="col-lg-12 text-left">
                                <p style="color:black;padding-left:20px; margin-bottom: 2px; text-align: center" >En caso de no ser asi debe <a
                                    href='modificar-garage-llegada?alquilerID=${alquiler.id}'>MODIFICAR GARAGE
                                DE LLEGADA</a></p>
                                <h6 class="subtitle font-weight-normal" style="text-align: center">Recordar que se cobrara una tarifa por la modificacion</h6>
                            </div>
                            <div class="col-lg-12 text-center" style="margin: 12px">
                                <h3 class="btn btn-outline-success btn-lg" style="margin: 20px"><a href='confirmacion-fin-alquiler?alquilerID=${alquiler.id}'>CONFIRMAR FINALIZACION DE ALQUILER</a></h3>
                            </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
<jsp:include page="footer.jsp" />
</html>