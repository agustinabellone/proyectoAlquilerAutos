<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@fortawesome/fontawesome-free@5.15.4/css/fontawesome.min.css" integrity="sha384-jLKHWM3JRmfMU0A5x5AkjWkw/EYfGUAGagvnfryNV3F9VqM98XiIH7VBGVoxVSc7" crossorigin="anonymous">
    <title>Proyecto - Alquiler de autos</title>
</head>
<body>
<jsp:include page="header.jsp" />
<body>
<section>
    <div class="container">
        <div class="col-sm-12 d-flex justify-content-center">
            <h1 class="text-center mt-3">Configuración</h1>
        </div>

        <h3>Editar datos personales</h3>
        <label>Nombre</label>
        <input type="text" class="form-control"  value="${usuario.nombre}">
        <br>
        <label>Contraseña</label>
        <div class="d-flex">
            <input class="form-control" type="password" id="password" value="${usuario.clave}"/>
            <button onclick="if (password.type == 'text') password.type = 'password';
                else password.type = 'text';">Ver</button>
        </div>
        <div class="d-flex mt-3">
            <div>
                <a href="" class="btn btn-success" >Guardar cambios</a>
            </div>
            <div class="ml-3">
                <a href="" class="btn btn-danger" >Eliminar cuenta</a>
            </div>
        </div>

    </div>

</section>
</body>
</html>
