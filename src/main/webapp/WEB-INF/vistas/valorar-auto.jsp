<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <title>Proyecto - Alquiler de autos</title>
</head>
<body>
<header class = "d-flex flex-row-reverse p-3"></header>
<section>
    <div class="container">
        <div class="col-sm-12 d-flex justify-content-center">
            <h1 class="text-center">Auto <c:>${auto.modelo} </c:> </h1>
        </div>
        <div class="col-lg-6 align-self-center">
            <form class="valoracion" action="guardar-valoracion-Auto?autoID=${auto.id}" method="post">
            <input type="radio" id="uno" name="estrellasValoracion" value="1">
            <label for="uno"><i class="glyphicon glyphicon-star"></i></label>
            <input type="radio" id="dos" name="estrellasValoracion" value="2">
            <label for="dos"><i class="glyphicon glyphicon-star"></i></label>
            <input type="radio" id="tres" name="estrellasValoracion" value="3">
            <label for="tres"><i class="glyphicon glyphicon-star"></i></label>
            <input type="radio" id="cuatro" name="estrellasValoracion" value="4">
            <label for="cuatro"><i class="glyphicon glyphicon-star"></i></label>
            <input type="radio" id="cinco" name="estrellasValoracion" value="5">
            <label for="cinco"><i class="glyphicon glyphicon-star"></i></label>
            <br><br>
            <p><textarea name="comentario" rows="5" cols="50" placeholder="Deje su comentario: "></textarea></p>
            <br><br>
            <div class="form-group m-0">
                <button class="btn btn-lg btn-primary btn-block" Type="Submit" />Cargar</button>
            </div>
            <br><br>
            </form>
        </div>





    </div>
</section>

</body>



<style type="text/css">
    input{
        display: none;
    }
    label {
        font-size: 20px;
        color: grey;
    }
    label:hover,
    label:hover ~ label {
        color: orange;
    }
    input[type="radio"]:checked ~ label {
        color: orange;
    }
    form{
        direction: rtl;
        unicode-bidi: bidi-override;
    }
</style>

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

