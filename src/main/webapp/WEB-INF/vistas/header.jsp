<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<header class="p-3">
  <nav class="navbar navbar-expand-lg navbar-light">
    <div class="d-flex align-items-center">
      <img style="height: 50px; width: 100%" src="img/nombreConLogo.svg" alt="logo">
    </div>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav ">
        <c:if test = "${id != null}">
          <li class="nav-item">
            <div class="text-secondary avatar dropdown" >
              <a class="nav-link dropdown-toggle waves-effect waves-light" id="navbarDropdownMenuLink-5" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                <c:if test="${notificaciones !=null}">
                  <span id="cantidadNotis" class="badge badge-danger ml-2">${notificaciones.size()}</span>
                </c:if>
                <i class="fas fa-bell"></i>
              </a>
              <div class="dropdown-menu dropdown-menu-lg-right dropdown-secondary" aria-labelledby="navbarDropdownMenuLink-5">
                <c:forEach items="${notificaciones}" var="noti">
                  <div class="alert alert-${noti.getColor()} alert-dismissible fade show" style="margin-bottom: 5px; width: 350px" role="alert">
                    <strong>${noti.getDescripcion()}</strong>
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close" onclick="actualizarNotificaciones(${noti.getId()},${noti.getUsuario().getId()})">
                      <span aria-hidden="true" >&times;</span>
                    </button>
                  </div>
                </c:forEach>
              </div>
            </div>
          </li>
        <li class="nav-item">
          <a class="nav-link" href="home">Inicio</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="main">Mi Cuenta</a>
        </li>

          <c:choose>
          <c:when test = "${rol == Rol.ADMIN }">
            <li class="nav-item">
              <a class="nav-link" href="admin-suscripcion">Manejo de suscripciones</a>
            </li>
          </c:when>
          <c:when test = "${rol == Rol.ENCARGADO_DEVOLUCION }">
            <%-- POR EL MOMENTO NO HAY OPCIONES EXTRA PARA ESTE ROL --%>
          </c:when>


          <c:when test = "${rol == Rol.MECANICO }">
            <%-- POR EL MOMENTO NO HAY OPCIONES EXTRA PARA ESTE ROL --%>
          </c:when>
            <c:otherwise>
              <c:if test = "${tieneSuscripcion==false}">
                <li class="nav-item">
                  <a class="nav-link" href="ir-a-suscribir">Suscribirse a un plan</a>
                </li>
              </c:if>
              <c:if test = "${tieneSuscripcion==true}">
                <li class="nav-item">
                  <a class="nav-link" href="administrar-suscripcion">Administrar Suscripcion</a>
                </li>
              </c:if>
              <li class="nav-item">
                <a class="nav-link" href="configuracion">Configuración</a>
              </li>
            </c:otherwise>
          </c:choose>
          <li class="nav-item">
            <a type="button" class="nav-link text-danger" data-toggle="modal" data-target="#exampleModal">
              Cerrar sesion
            </a>
          </li>
        </c:if>
        <c:if test = "${id == null}">
          <li class="nav-item">
            <a class="nav-link text-dark" href="registro">Crear cuenta</a>
          </li>
          <li class="nav-item">
            <a class="nav-link text-info" href="login">Iniciar sesión</a>
          </li>
        </c:if>
      </ul>
    </div>
  </nav>

    <!-- Modal -->
    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">Cerrar sesion</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            ¿Estás seguro? Tendrás que iniciar sesión de nuevo.
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
            <a href="logout"><button type="button" class="btn btn-danger">Cerrar</button></a>
          </div>
        </div>
      </div>
    </div>
    <!--Fin del modal-->
</header>

<script type="text/javascript">
  function actualizarNotificaciones(id_noti, id_usuario){
    $.ajax({
      url: "${pageContext.request.contextPath}/actualizarNotificaciones?id_noti="+id_noti+"&id_usuario="+id_usuario,
      type: "GET",
      dataType: "JSON",
      success: function (data){
            $('#cantidadNotis').html(data.cantidadNotis);
      }
    });

  }
</script>

<style>
  .navbar-nav {
    margin-left: auto;
  }
</style>

