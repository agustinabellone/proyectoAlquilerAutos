<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Pantalla Principal</title>

    <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="css/sb-admin-2.min.css" rel="stylesheet">

</head>

<body id="page-top">


<c:forEach items="${lista_autos_mantenimiento}" var="auto">
    <div class="col-md-12">
        <div class="card card-shadow border-0 mb-4">
            <div class="card-body p-4">
                <div class="d-flex align-items-center">
                    <h5 class="font-weight-medium mb-0">Situacion: ${auto.situacion}</h5>
                </div>
                <div class="col">
                    <div class="col-lg-12">
                        <div class="row mt-3">
                            <div class="col-lg-6 align-self-center">
                                <ul class="list-inline pl-3 font-16 font-weight-medium text-dark mt-3">
                                    <li>
                                        <span class="badge badge-danger font-weight-normal p-2">Fecha Inicio: dd/mm/yy</span>
                                    </li>
                                    <li class="py-2"><i class="icon-check text-info mr-2"></i>
                                        Patente: <span>${auto.patente}</span>
                                    </li>
                                    <li class="py-2"><i class="icon-check text-info mr-2"></i>
                                        Marca: <span>${auto.marca.descripcion}</span>
                                    </li>
                                    <li class="py-2"><i class="icon-check text-info mr-2"></i>
                                        Modelo: <span>${auto.modelo.descripcion}</span>
                                    </li>
                                    <li class="py-2"><i class="icon-check text-info mr-2"></i>
                                        Kilometraje: <span>${auto.km}</span>
                                    </li>
                                </ul>
                            </div>
                            <div class="col-lg-6">
                                <div class="container">
                                    <img src="${auto.imagen}"
                                         alt="" style="width: 100%; height: auto;">
                                </div>
                            </div>
                            <p class="font-14 border-0 text-white text-center p-3 btn-block mt-3 bg-primary"
                            >Fecha Retorno: dd/mm/yy</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</c:forEach>
<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>

<!-- Logout Modal-->
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">Estas seguro de cerrar la sesion?</div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancelar</button>
                <a class="btn btn-primary" href="logout">Cerrar Sesion</a>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap core JavaScript-->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="js/sb-admin-2.min.js"></script>

<!-- Page level plugins -->
<script src="vendor/chart.js/Chart.min.js"></script>

<!-- Page level custom scripts -->
<script src="js/demo/chart-area-demo.js"></script>
<script src="js/demo/chart-pie-demo.js"></script>

</body>
</html>
