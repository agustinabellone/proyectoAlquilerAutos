<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <!-- Required meta tags -->

    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="css/planes.css">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <title>Proyecto - Alquiler de autos</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<section>
    <div class="container text-center">
        <div class="col-sm-12 d-flex justify-content-center">
            <h1 class="text-center mt-5">Registro de devolucion</h1>
        </div>
    </div>
</section>
<section>
    <div class="container">
        <div class="">
            <div class="col-lg-12">
                <div class="d-flex ">
                    <div class="col-lg-6 justify-content-center">
                            <form:form action="finalizarAlquiler?solicitud=${esperandoConfirmacion.id}" method="get" >
                                <div class="form-group">
                                    <input style="opacity:1;" type="number" name="kilometros" placeholder="Kilometros actuales">
                                </div>
                                <div class="form-group">
                                    <input style="opacity:1;" type="checkbox" data-required="1" name="terminos">
                                    <label style="color:black;padding-left:20px;" name="terminos">Auto en condiciones</label>
                                </div>
                                <div>
                                    <p><textarea class="form-control mt-3" name="comentario" rows="5" required
                                                 placeholder="Agregar comentario... "></textarea></p>
                                </div>
                                <div class="form-group m-0">
                                    <button class="btn btn-lg btn-dark btn-block" Type="Submit"/>Finalizar</button>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>


</section>
<jsp:include page="footer.jsp"/>
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

