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
<jsp:include page="header.jsp" />
<section>
    <div class="container text-center">
        <div>
            <h1 class="display-4 p-4"></h1>
        </div>
    </div>
</section>
<section>
    <div class="pricing6 py-5 bg-light">
    <div class="container">
        <div class="col center-block">
            <div class="col-md-12">
                <div class="card card-shadow border-0 mb-4">
                    <div class="card-body p-4">
                        <div class="d-flex align-items-center">
                            <h5 class="font-weight-medium mb-0"><c:>${auto.modelo} </c:></h5>
                        </div>
                    </div>
                    <div class="col">
                        <div class="col-lg-12">
                            <div class="row mt-3">
                                <div class="col-lg-6 align-self-center">
                                    <form class="valoracion" action="guardar-valoracion-Auto?autoID=${auto.id}&alquilerID=${alquilerID}" method="post">
                                        <input type="radio" id="uno" name="estrellasValoracion" value="5">
                                        <label for="uno"><i class="glyphicon glyphicon-star"></i></label>
                                        <input type="radio" id="dos" name="estrellasValoracion" value="4">
                                        <label for="dos"><i class="glyphicon glyphicon-star"></i></label>
                                        <input type="radio" id="tres" name="estrellasValoracion" value="3">
                                        <label for="tres"><i class="glyphicon glyphicon-star"></i></label>
                                        <input type="radio" id="cuatro" name="estrellasValoracion" value="2">
                                        <label for="cuatro"><i class="glyphicon glyphicon-star"></i></label>
                                        <input type="radio" id="cinco" name="estrellasValoracion" value="1">
                                        <label for="cinco"><i class="glyphicon glyphicon-star"></i></label>
                                        <br><br>
                                        <p><textarea class="form-control" name="comentario" rows="5" placeholder="Deje su comentario: "></textarea></p>
                                        <br><br>
                                        <div class="form-group m-0">
                                            <button class="btn btn-lg btn-primary btn-block" Type="Submit" />Cargar</button>
                                        </div>
                                        <br><br>
                                    </form>
                                </div>
                                <div class="col-lg-6">
                                    <div class="container">
                                        <img src="${auto.imagen}" class="card-img-top" alt="...">
                                    </div>
                                </div>
                                <button class="btn btn-info-gradiant font-14 border-0 text-white p-3 btn-block mt-3" Type="Submit" />Cargar</button>
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

