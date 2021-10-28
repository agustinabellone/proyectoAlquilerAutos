<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
nuevo
<!DOCTYPE html>
<html>
<head>
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet" >
    <!-- Bootstrap theme -->
    <link href="css/bootstrap-theme.min.css" rel="stylesheet">
</head>
<body>
<form:form action="seleccionNuevoGarageSeleccionado">
    <h1>Seleccione nuevo garage de llegada</h1>
    <label>
        <select name="nuevoGarage">
            <c:forEach var="garages" items="${garages}">
                <option value="${garages.id}">${garages.direccion}</option>
            </c:forEach>
        </select>
    </label>
    <input type="hidden" id="alquilerID" name="alquilerID" value='${alquiler.id}'>
    <button class="btn btn-lg btn-primary btn-block" Type="Submit">Confirmar</button>
</form:form>

<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>
