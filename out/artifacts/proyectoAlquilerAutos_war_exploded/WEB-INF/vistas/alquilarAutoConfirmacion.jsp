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
            <h1 class="text-center">Confirmaci�n</h1>
        </div>
        <div class=" container card col-md-6 my-4" >
            <img src="https://http2.mlstatic.com/D_NQ_NP_897110-MLU43129754655_082020-O.jpg" class="card-img-top" alt="...">
            <div class="card-body">
                <h5 class="card-title">Auto: ${id_auto}</h5>
                <p class="card-text">D�as: ${f_ingreso_dia}/${f_ingreso_mes} - ${f_salida_dia}/${f_salida_mes}</p>
                <div class="col-sm-12 d-flex justify-content-center">
                    <a href="#" class="btn btn-primary mt-5">ALQUILAR</a>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>
