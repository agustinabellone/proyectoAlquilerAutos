<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap theme -->
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
          crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="css/planes.css"/>
    <link href="css/bootstrap-theme.min.css" rel="stylesheet">
</head>
<body>
<section>
    <div class="pricing6 py-5 bg-light">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-8 text-center">
                    <h2 class="mb-3">Seleccione nuevo garage de llegada</h2>
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
                                    <button class="btn btn-outline-success btn-lg" Type="Submit">Confirmar</button>
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

<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>
