<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Formulario Revision</title>

    <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="css/sb-admin-2.min.css" rel="stylesheet">
</head>
<body id="page-top">

<header class="header">
    <!-- Topbar -->
    <nav class="navbar navbar-expand navbar-light topbar ">

        <!-- Sidebar Toggle (Topbar) -->
        <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
            <i class="fa fa-bars"></i>
        </button>


        <!-- Topbar Navbar -->
        <ul class="navbar-nav ml-auto">

            <!-- Nav Item - Search Dropdown (Visible Only XS) -->
            <li class="nav-item dropdown no-arrow d-sm-none">
                <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button"
                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <i class="fas fa-search fa-fw"></i>
                </a>
            </li>

            <div class="topbar-divider d-none d-sm-block"></div>

            <!-- Nav Item - User Information -->
            <li class="nav-item dropdown no-arrow">
                <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <span class="mr-2 d-none d-lg-inline text-gray-600 small">${nombre}</span>
                    <img class="img-profile rounded-circle"
                         src="img/undraw_profile.svg">
                </a>
                <!-- Dropdown - User Information -->
                <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                     aria-labelledby="userDropdown">
                    <a class="dropdown-item" href="#">
                        <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                        Mi Perfil
                    </a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                        <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                        Cerrar Session
                    </a>
                </div>
            </li>
        </ul>
    </nav>
</header>
<div class="container">
    <nav class="navbar bg-gray-300">
        <a class="nav-link active" href="ir-a-pantalla-principal">Ir a Pantalla Principal</a>
        <a class="nav-link" href="autos-en-revision">Ir a autos en Revision</a>
    </nav>
</div>
<div class="container mt-4">
    <h1 class="text-center">Formulario Revision</h1>
    <div class="col-md-12 card-shadow">
        <ul class="list-inline pl-3 font-16 font-weight-medium text-dark mt-3">
            <li class="py-2">
                <span><i
                        class="icon-check text-info mr-2"></i>Fecha en la que se envio a revision: ${auto_para_formulario.fecha_inicio_mantenimiento}</span>
            </li>
            <li class="py-2"><i class="icon-check text-info mr-2"></i>
                Patente: <span>${auto_para_formulario.patente}</span>
            </li>
            <li class="py-2"><i class="icon-check text-info mr-2"></i>
                Marca: <span>${auto_para_formulario.marca.descripcion}</span>
            </li>
            <li class="py-2"><i class="icon-check text-info mr-2"></i>
                Modelo: <span>${auto_para_formulario.modelo.descripcion}</span>
            </li>
            <li class="py-2"><i class="icon-check text-info mr-2"></i>
                Kilometraje: <span>${auto_para_formulario.km}</span>
            </li>
            <li class="py-2"><i class="icon-check text-info mr-2"></i>
                Fecha de retorno de revision: <span><b>A confirmar cuando finalize la revision</b></span>
            </li>
        </ul>
    </div>
    <form action="finalizar-revision">
        <input type="hidden" name="id_auto" value="${auto_para_formulario.id}">
        <div>
            <p><textarea class="form-control mt-3" name="comentario" rows="5" required
                         placeholder="Ingrese una descripcion de lo realizado al vechiculo... "></textarea></p>
        </div>
        <button class="font-14 border-0 text-white text-center p-3 btn-block mt-3 bg-secondary" type="submit">
            FINALIZAR REVISION
        </button>
    </form>
</div>
</div>
<c:if test="${not empty error}">
    <div class="alert alert-danger text-center container mt-3 col-12" role="alert">
            ${error}
    </div>
</c:if>
</div>
<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>

<!-- Logout Modal-->
<div class="modal fade " id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">??</span>
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
