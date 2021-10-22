<%@ page language="java" contentType="text/html;charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
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
<header class = "d-flex flex-row-reverse p-3"></header>
<section>
    <div class="container">
        <div class="col-sm-12 d-flex justify-content-center">
            <h1 class="text-center">Alquilar auto ${id_auto}</h1>
        </div>
    </div>
    <div class="container my-5">
        <div class="d-flex flex-row-reverse bd-highlight mb-2">
            <span class="badge badge-success font-weight-normal p-2">5 días</span>
        </div>
        <div class="card col text-center">
            <div class="card-body">
                <h5 class="card-title">18/10 - 23/10</h5>
                <p class="card-text">El alquiler empieza el lunes 18 y termina el viernes 23.</p>
                <a href="alquiler-confirmación?id_auto=${id_auto}&f_salida_dia=18&f_salida_mes=10&f_ingreso_dia=23&f_ingreso_mes=10" class="btn btn-primary">Quiero estos días</a>
            </div>
        </div>
    </div>

    <div class="container">
        <div class="col-sm-12 d-flex justify-content-around">
            <form>
                <div>
                    <h3>Salida del auto:</h3>
                    <input id="salida" type="date">
                </div>
                <div>
                    <h3>Ingreso del auto:</h3>
                    <input id="ingreso" type="date">
                </div>
        </div>
            <div class="col-sm-12 d-flex justify-content-around">
                <a href="#" class="btn btn-primary">Confirmar días</a>
            </div>
        </form>
    </div>




</section>
</body>
</html>