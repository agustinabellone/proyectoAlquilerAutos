<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
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
    <link rel="stylesheet" type="text/css" href="css/planes.css"/>
    <title>Proyecto - Alquiler de autos</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<section class="bg-light pb-5">
    <div class="pricing6 py-2">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-8 text-center">
                    <h1 class="display-5 p-4">Seleccioná un nuevo garage de llegada</h1>
                </div>
            </div>
            <!-- row  -->
            <div class="row mt-12">
                <!-- column  -->
                <div class="col">
                    <div class="card card-shadow border-0 mb-4">
                        <div class="card-body p-4">
                            <form action="seleccionNuevoGarageSeleccionado">
                                    <select class="form-select" name="nuevoGarage">
                                        <c:forEach var="garages" items="${garages}">
                                            <option name="nuevoGarage" value="${garages.id}">${garages.direccion}</option>
                                        </c:forEach>
                                    </select>
                                <input type="hidden" name="alquilerID" value='${alquiler.id}'>
                                <div class="col-lg-12 text-center" style="margin: 12px">
                                    <button class="btn btn-outline-success btn-lg mt-2" Type="Submit">Confirmar</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>
</section>

<jsp:include page="footer.jsp" />
</body>
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
</html>
