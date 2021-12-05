<%--
  Created by IntelliJ IDEA.
  User: Ezequiel
  Date: 3/12/2021
  Time: 12:52
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mecanico</title>
    <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="css/sb-admin-2.min.css" rel="stylesheet">
</head>
<body>
<!-- Content Wrapper -->
<div id="content-wrapper" class="d-flex flex-column">

    <!-- Main Content -->
    <div id="content" class="bg-gray-200">

        <!-- Begin Page Content -->
        <div class="container-fluid">
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
            <!-- End of Topbar -->
            <!-- Page Heading -->
            <div class="col-12 mb-4">
                <h1 class="h3 mb-0 text-gray-800 text-center">Bienvenido ${nombre}</h1>
            </div>
            <c:if test="${not empty auto_enviado}">
                <div class="alert alert-success text-center container mt-3 col-12" role="alert">
                        ${auto_enviado}
                </div>
            </c:if>
            <div class="container">
                <c:forEach items="${para_mantenimiento}" var="autos">
                    <div class="col-md-12">
                        <div class="card card-shadow border-0 mb-4">
                            <div class="card-body p-4 col-lg-12">
                                <div class="col">
                                    <div class="col-lg-12">
                                        <div class="row">
                                            <div class="col-lg-6 align-self-center">
                                                <ul class="list-inline pl-3 font-16 font-weight-medium text-dark mt-3">
                                                    <li class="py-2"><i class="icon-check text-info mr-2"></i>
                                                        <span>Patente: ${autos.patente}</span>
                                                    </li>
                                                    <li class="py-2"><i class="icon-check text-info mr-2"></i>
                                                        <span>Marca: ${autos.marca.descripcion}</span>
                                                    </li>
                                                    <li class="py-2"><i class="icon-check text-info mr-2"></i>
                                                        <span>Modelo: ${autos.modelo.descripcion}</span>
                                                    </li>
                                                    <li class="py-2"><i class="icon-check text-info mr-2"></i>
                                                        <span>Kilometraje: ${autos.km}</span>
                                                    </li>
                                                </ul>
                                            </div>
                                            <div class="col-lg-6">
                                                <div class="container">
                                                    <img src="${autos.imagen}"
                                                         alt="" style="width: 100%; height: auto;">
                                                </div>
                                            </div>
                                            <a class="btn btn-primary font-14 border-0 text-white p-3 btn-block mt-3"
                                               href="revisar-auto/patente/${auto.patente}">AGREGAR A MI LISTA DE AUTOS EN REVISION</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </c:forEach>
            </div>
            <c:if test="${not empty error}">
                <div class="alert alert-danger text-center container mt-3 col-12" role="alert">
                        ${error}
                </div>
            </c:if>
            <div class="row">

            </div>

            <!-- Content Row -->
            <div class="row">

            </div>

        </div>
        <!-- /.container-fluid -->

    </div>
    <!-- End of Main Content -->

    <!-- Footer -->
    <footer class="sticky-footer bg-white">
        <div class="container my-auto">
            <div class="copyright text-center my-auto">
                <span>Copyright &copy; Your Website 2021</span>
            </div>
        </div>
    </footer>
    <!-- End of Footer -->

</div>
<!-- End of Content Wrapper -->

</div>
<!-- End of Page Wrapper -->

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
