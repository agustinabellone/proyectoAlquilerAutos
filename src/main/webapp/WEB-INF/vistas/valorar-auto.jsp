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
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="css/planes.css">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <title>Proyecto - Alquiler de autos</title>
</head>
<body>
<jsp:include page="header.jsp" />
<section>
    <div class="container text-center">
        <div class="col-sm-12 d-flex justify-content-center">
            <h1 class="text-center mt-5">Valorar Vehiculo</h1>
        </div>
    </div>
</section>
<section>
    <div class="container">
        <div class="col-sm-12 d-flex justify-content-center">
            <h1 class="text-center mt-5">Confirmación</h1>
        </div>
            <div class="">
                <div class="col-lg-12">
                    <div class="d-flex ">
                        <div class="col-lg-6">
                            <form class="valoracion" action="guardar-valoracion-Auto?autoID=${auto.id}&alquilerID=${alquilerID}" method="post">
                                <input type="radio" id="uno" name="estrellasValoracion" value="5" required>
                                <label for="uno"><i class="fa fa-star"></i></label>
                                <input type="radio" id="dos" name="estrellasValoracion" value="4">
                                <label for="dos"><i class="fa fa-star"></i></label>
                                <input type="radio" id="tres" name="estrellasValoracion" value="3">
                                <label for="tres"><i class="fa fa-star"></i></label>
                                <input type="radio" id="cuatro" name="estrellasValoracion" value="2">
                                <label for="cuatro"><i class="fa fa-star"></i></label>
                                <input type="radio" id="cinco" name="estrellasValoracion" value="1">
                                <label for="cinco"><i class="fa fa-star"></i></label>
                                <div>
                                    <p><textarea class="form-control mt-3" name="comentario" rows="5" required placeholder="Escribir comentario... "></textarea></p>
                                    <div class="col-sm-12 d-flex justify-content-center mb-3">
                                        <button class="btn btn-lg btn-dark btn-block" Type="Submit" />Comentar</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="col-lg-6">
                            <div class="container">
                                <img src="${auto.imagen}" alt="auto" style="width: 100%; height: auto;">
                            </div>
                        </div>
                    </div>

                </div>

            </div>
    </div>



</section>
<jsp:include page="footer.jsp" />
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
        color: #ff8400;
    }
    input[type="radio"]:checked ~ label {
        color: orange;
    }
    form{
        direction: rtl;
        unicode-bidi: bidi-override;
    }
    textarea{
        direction: ltr;
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

