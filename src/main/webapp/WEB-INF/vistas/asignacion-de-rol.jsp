<%--
  Created by IntelliJ IDEA.
  User: Ezequiel
  Date: 30/11/2021
  Time: 10:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

    <title>Administracion</title>

    <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="css/sb-admin-2.min.css" rel="stylesheet">
</head>
<body>

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
                    <a class="collapse-item" href="clientes-suscriptos">Suscriptos</a>
                    <a class="collapse-item" href="clientes-no-suscriptos">No Suscriptos</a>
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
                    <a class="collapse-item" href="encargados-devolucion">Encargados de Devolucion</a>
                    <a class="collapse-item" href="mecanicos">Mecanicos</a>
                    <a class="collapse-item" href="asignacion-de-rol">Asignacion de Rol</a>
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
<<<<<<< HEAD

=======
                <c:if test="${not empty mensaje_exito}">
                    <div class="alert alert-success text-center container mt-3 col-12" role="alert">
                            ${mensaje_exito}
                    </div>
                </c:if>
>>>>>>> ce987c5892b8d70c8ed35bbaba2529cf88c404fa
                <!-- Empieza FOR-EAH-->
                <c:forEach items="${pendientes_de_rol}" var="empleados">
                    <div class="col-md-12">
                        <div class="card card-shadow border-0 mb-4">
                            <div class="card-body p-4">
                                <div class="col">
                                    <div class="col-lg-12">
                                        <div class="row mt-3">
                                            <div class="col-lg-6 align-self-center">
                                                <ul class="list-inline pl-3 font-16 font-weight-medium text-dark mt-3">
                                                    <li class="py-2"><i class="icon-check text-info mr-2"></i>
                                                        Nombre: <span>${empleados.nombre}</span>
                                                    </li>
                                                    <li class="py-2"><i class="icon-check text-info mr-2"></i>
                                                        Mail: <span>${empleados.email}</span>
                                                        <form action="confirmar-rol" method="get">
                                                            <select name="rol" class="form-select" aria-label="Default select example">
                                                                <option selected>Seleccionar Rol...</option>
                                                                <option value="1">Administrador</option>
                                                                <option value="2">Mecanico</option>
                                                                <option value="3">Encargado de Devolucion</option>
                                                            </select>
<<<<<<< HEAD
                                                            <input type="submit" value="Enviar" class="btn-info"/>
=======
                                                            <input type="hidden" name="id_usuario" value="${empleados.id}">
                                                            <input type="submit" value="Confirmar" class="btn-info"/>
>>>>>>> ce987c5892b8d70c8ed35bbaba2529cf88c404fa
                                                        </form>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </c:forEach>
                <!-- termina FOR-EACH -->
                <!-- Content Row -->
<<<<<<< HEAD
                <c:if test="${not empty error_no_hay_pendientes_de_rol}">
                    <div class="alert alert-danger text-center container mt-3 col-12" role="alert">
                            ${error_no_hay_pendientes_de_rol}
=======
                <c:if test="${not empty error}">
                    <div class="alert alert-danger text-center container mt-3 col-12" role="alert">
                            ${error}
>>>>>>> ce987c5892b8d70c8ed35bbaba2529cf88c404fa
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
