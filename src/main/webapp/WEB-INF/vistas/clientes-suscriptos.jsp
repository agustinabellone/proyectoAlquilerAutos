<%--
  Created by IntelliJ IDEA.
  User: ezequiel
  Date: 11/11/21
  Time: 09:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

        <title>Administracion</title>

    <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="css/sb-admin-2.min.css" rel="stylesheet">

</head>

<body id="page-top">

<!-- Page Wrapper -->
<div id="wrapper">

    <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

        <!-- Sidebar - Brand -->
        <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.html">
            <div class="text-center sidebar-brand-text mx-3">Alquiler de Autos</div>
        </a>

        <!-- Divider -->
        <hr class="sidebar-divider my-0">

        <!-- Nav Item - Panel Principal -->
        <li class="nav-item active">
            <a class="nav-link" href="panel-principal">
                <i class="fas fa-user-cog"></i>
                <span>Panel Principal</span></a>
        </li>

        <!-- Divider -->
        <hr class="sidebar-divider">

        <!-- Heading -->
        <div class="sidebar-heading">
            Gestiones
        </div>

        <!-- Nav Item - Autos -->
        <li class="nav-item">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo"
               aria-expanded="true" aria-controls="collapseTwo">
                <i class="fas fa-car"></i>
                <span>Autos</span>
            </a>
            <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <a class="collapse-item" href="autos-disponibles">Disponibles</a>
                    <a class="collapse-item" href="autos-alquilados">Alquilados</a>
                    <a class="collapse-item" href="autos-en-mantenimiento">En mantenimiento</a>
                </div>
            </div>
        </li>

        <!-- Nav Item - Clientes -->
        <li class="nav-item">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseClientes"
               aria-expanded="true" aria-controls="collapseClientes">
                <i class="fas fa-user"></i>
                <span>Clientes</span>
            </a>
            <div id="collapseClientes" class="collapse" aria-labelledby="headingClientes"
                 data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <a class="collapse-item" href="utilities-border.html">Suscriptos</a>
                    <a class="collapse-item" href="utilities-color.html">Nuevos</a>
                </div>
            </div>
        </li>

        <!-- Nav Item - Empleados -->
        <li class="nav-item">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseEmpleados"
               aria-expanded="true" aria-controls="collapseEmpleados">
                <i class="fas fa-hard-hat"></i>
                <span>Empleados</span>
            </a>
            <div id="collapseEmpleados" class="collapse" aria-labelledby="headingEmpleados"
                 data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <a class="collapse-item" href="utilities-color.html">Administradores</a>
                    <a class="collapse-item" href="utilities-border.html">Encargado Service</a>
                    <a class="collapse-item" href="utilities-animation.html">Encargado Suscripcion</a>
                    <a class="collapse-item" href="utilities-color.html">Encargado Mantenimiento</a>
                </div>
            </div>
        </li>

        <!-- Divider -->
        <hr class="sidebar-divider my-0">

        <!-- Nav Item - Reportes -->
        <li class="nav-item">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseReportes"
               aria-expanded="true" aria-controls="collapseReportes">
                <i class="fas fa-download fa-sm text-white-50"></i>
                <span>Generar Reportes</span>
            </a>
            <div id="collapseReportes" class="collapse" aria-labelledby="headingReportes"
                 data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <a class="collapse-item" href="utilities-color.html">Autos</a>
                    <a class="collapse-item" href="utilities-border.html">Clientes</a>
                    <a class="collapse-item" href="utilities-other.html">Empleados</a>
                </div>
            </div>
        </li>

        <!-- Divider -->
        <hr class="sidebar-divider">
    </ul>
    <!-- End of Sidebar -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content" class="bg-gray-200">

            <!-- Topbar -->
            <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

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

            <!-- Begin Page Content -->
            <div class="container-fluid">

                <!-- Page Heading -->
                <div class="col-12 mb-4">
                    <h1 class="h3 mb-0 text-gray-800 text-center">Bienvenido ${nombre}</h1>
                </div>

                <!-- Content Row -->
                <div class="row">

                    <!-- Usuarios nuevos -->
                    <div class="col-xl-3 col-md-6 mb-4">
                        <div class="card border-left-primary shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                            Clientes Nuevos
                                        </div>
                                        <div class="h5 mb-0 font-weight-bold text-gray-800">0
                                        </div>
                                    </div>
                                    <div class="col-auto">
                                        <a href="" class="text-primary font-weight-bold text-lg">Ver</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Suscripciones nuevas -->
                    <div class="col-xl-3 col-md-6 mb-4">
                        <div class="card border-left-success shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                            Clientes Suscriptos
                                        </div>
                                        <div class="h5 mb-0 font-weight-bold text-gray-800">1
                                        </div>
                                    </div>
                                    <div class="col-auto">
                                        <a href="" class="text-success font-weight-bold text-lg">Ver</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Alquilers nuevos -->
                    <div class="col-xl-3 col-md-6 mb-4">
                        <div class="card border-left-info shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold text-info text-uppercase mb-1">Alquileres
                                            Nuevos
                                        </div>
                                        <div class="row no-gutters align-items-center">
                                            <div class="col-auto">
                                                <div class="h5 mb-0 mr-3 font-weight-bold text-gray-800">0
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-auto">
                                        <a href="" class="text-info font-weight-bold text-lg">Ver</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Asignaciones de roles pendientes -->
                    <div class="col-xl-3 col-md-6 mb-4">
                        <div class="card border-left-warning shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold text-warning text-uppercase mb-1">
                                            Pendiente de Rol
                                        </div>
                                        <div class="h5 mb-0 font-weight-bold text-gray-800">3
                                        </div>
                                    </div>
                                    <div class="col-auto">
                                        <a href="" class="text-warning font-weight-bold text-lg">Ver</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Empieza FOR-EAH-->
                <c:forEach items="${usuarios_suscriptos}" var="usuarios">
                    <div class="col-md-12">
                        <div class="card card-shadow border-0 mb-4">
                            <div class="card-body p-4">
                                <div class="d-flex align-items-center">
                                    <h5 class="font-weight-medium mb-0">Tipo Suscripcion: ${usuarios.tipoSuscripcion}</h5>
                                </div>
                                <div class="col">
                                    <div class="col-lg-12">
                                        <div class="row mt-3">
                                            <div class="col-lg-6 align-self-center">
                                                <ul class="list-inline pl-3 font-16 font-weight-medium text-dark mt-3">
                                                    <li>
                                                        <span class="badge badge-danger font-weight-normal p-2">Fecha Inicio: ${usuarios.fechaInicio}</span>
                                                    </li>
                                                    <li class="py-2"><i class="icon-check text-info mr-2"></i>
                                                        Nombre: <span>${usuarios.usuario.nombre}</span>
                                                    </li>
                                                    <li class="py-2"><i class="icon-check text-info mr-2"></i>
                                                        Mail: <span>${usuarios.usuario.email}</span>
                                                    </li>
                                                </ul>
                                            </div>
                                            <p class="font-14 border-0 text-white text-center p-3 btn-block mt-3 bg-primary"
                                            >Fecha Fin Suscripcion: ${usuarios.fechaFin}</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </c:forEach>
                <!-- termina FOR-EACH -->
                <!-- Content Row -->
                <c:if test="${not empty error_no_hay_clientes}">
                    <div class="alert alert-danger text-center container mt-3 col-12" role="alert">
                            ${error_no_hay_clientes}
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
            <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
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

</html></title>
</head>
<body>

</body>
</html>