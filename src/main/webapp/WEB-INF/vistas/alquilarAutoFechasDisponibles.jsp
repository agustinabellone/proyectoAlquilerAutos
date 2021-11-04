<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
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
<section>
    <div class="container">
        <div class="col-sm-12 d-flex justify-content-center">
            <h1 class="text-center">Elegir fechas</h1>
        </div>
    </div>
<br>
    <div class="container">
        <div class="col-sm-12 d-flex justify-content-around">
            <form action="confirmacion?id_auto=${id_auto}&imagen_auto=${imagen_auto}" method="post">
                   <div class="container col-sm-12 d-flex justify-content-around">
                        <div class="col-sm-5 d-flex flex-column justify-content-center">
                            <p>Fecha que planeo retirar el auto:</p>
                            <input name="salida" id="salida" type="date" required>
                        </div>
                        <div class="col-sm-5 d-flex flex-column justify-content-center">
                            <p>Fecha que planeo devolverlo:</p>
                            <input name="ingreso" id="ingreso" type="date" required>
                        </div>
                   </div>
                <br>
                <div class="col-sm-12 d-flex justify-content-around">
                    <button class="btn btn-lg btn-dark btn-block" type="submit"/>Confirmar días</button>
                </div>
            </form>
        </div>
    </div>


</section>
</body>
<script type="text/javascript">
    document.getElementById('salida').setAttribute('min', new Date().toISOString().split('T')[0])
    document.getElementById('ingreso').setAttribute('min', new Date().toISOString().split('T')[0])
</script>
</html>