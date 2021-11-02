<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<header class = "mt-3 mb-3">
  <div class = "d-flex col-md-12 justify-content-around align-items-center">

    <c:if test = "${id != null}">
      <a href="home" type="button" class="btn btn-secondary">Inicio</a>
      <a href="main" type="button" class="btn btn-secondary">Mi Cuenta</a>

      <c:choose>

        <c:when test = "${rol == 'admin' }">
          <a href="admin-suscripcion" type="button" class="btn btn-warning">Manejo de suscripciones</a>
        </c:when>

        <c:when test = "${rol == 'encargado' }">
          <%-- POR EL MOMENTO NO HAY OPCIONES EXTRA PARA ESTE ROL --%>
        </c:when>

        <c:when test = "${rol == 'mecanico' }">
          <%-- POR EL MOMENTO NO HAY OPCIONES EXTRA PARA ESTE ROL --%>
        </c:when>

        <c:otherwise>
          <a href="ir-a-suscribir" type="button" class="btn btn-secondary">Suscribirse a un plan</a>
        </c:otherwise>


      </c:choose>

      <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#exampleModal">
        Cerrar sesion
      </button>

    </c:if>

      <c:if test = "${id == null}">
      <div class="d-flex justify-content-around align-items-center col-lg-3">
          <a href="registro" type="button" class="btn btn-outline-dark">Crear cuenta</a>
          <a href="login" type="button" class="btn btn-dark">Iniciar sesion</a>
      </div>
      </c:if>


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
  </div>
</header>