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
    <link rel="icon" href="img/favicon.ico" type="image/png" />
    <title>Proyecto - Alquiler de autos</title>
</head>
<body>
<jsp:include page="header.jsp" />
<section class="pb-5 bg-light">
    <div class="container mb-2">
        <div class="col-sm-12 d-flex flex-column justify-content-center text-center">
            <h1 class="display-4 p-4">Elegir fechas y lugares disponibles</h1>
        </div>
    </div>
    <div class="container">
        <div class="col-sm-12 d-flex justify-content-around">
            <form action="confirmacion?id_auto=${id_auto}&imagen_auto=${imagen_auto}" method="post">
                   <div class="container col-sm-12 d-flex justify-content-around text-center">
                        <div class="col-sm-6 d-flex flex-column justify-content-center">
                            <p>Fecha que planeo retirar el auto:</p>
                            <input name="salida" id="salida" type="date" required>

                        </div>
                        <div class="col-sm-6 d-flex flex-column justify-content-center">
                            <p>Lugar de retiro:</p>
                            <select name="lugarRetiro" id="lugarRetiro" class="form-select mt-4" required>
                                <option value="">Seleccionar</option>
                                <c:forEach items="${garages}" var="garage">
                                    <option value=${garage.id}>${garage.direccion}</option>
                                </c:forEach>
                            </select>
                        </div>
                   </div>
                    <div class="container col-sm-12 d-flex justify-content-around mt-3 text-center">
                        <div class="col-sm-6 d-flex flex-column justify-content-center">
                            <p>Fecha que planeo devolverlo:</p>
                            <input name="ingreso" id="ingreso" type="date" required>
                        </div>
                        <div class="col-sm-6 d-flex flex-column justify-content-center">
                            <p>Lugar de devoluci?n:</p>
                            <select name="lugarDevolucion" id="lugarDevolucion" class="form-select mt-4" required>
                                <option value="">Seleccionar</option>
                                <c:forEach items="${garages}" var="garage">
                                    <option value=${garage.id}>${garage.direccion}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                <br>
                <c:if test="${not empty mensaje}">
                    <div class="alert alert-danger text-center container mt-3" role="alert">
                            ${mensaje}
                    </div>
                </c:if>
                <div class="col-sm-12 d-flex justify-content-around">
                    <button class="btn btn-lg btn-info btn-block" type="submit"/>Confirmar</button>
                </div>
            </form>
        </div>
    </div>


</section>
<jsp:include page="footer.jsp" />
</body>

<script type="text/javascript">
    document.getElementById('salida').setAttribute('min', new Date().toISOString().split('T')[0])
    document.getElementById('ingreso').setAttribute('min', new Date().toISOString().split('T')[0])
</script>
</html>