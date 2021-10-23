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
            <h1 class="text-center">Elegir fechas para el auto ${id_auto}</h1>
        </div>
    </div>
<br>
    <div class="container">
        <div class="col-sm-12 d-flex justify-content-around">
            <form action="alquiler-confirmacion" method="get">
                    <div>
                        <h3>Salida del auto:</h3>
                        <input value="${salida}"  name="salida" type="date">
                    </div>
                <br>
                    <div>
                        <h3>Ingreso del auto:</h3>
                        <input value="${ingreso}"  name="ingreso" type="date">
                    </div>
                <br>
                <div class="col-sm-12 d-flex justify-content-around">
                    <button class="btn btn-lg btn-primary btn-block" Type="Submit"/>Confirmar d�as</button>
                </div>
            </form>
        </div>
    </div>


</section>
</body>
</html>