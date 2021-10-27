<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<header class = "p-3">
  <div class = " d-flex  col-md-12 justify-content-end ">

    <c:if test = "${id != null}">

      <!-- Button trigger modal -->
      <button type="button" class="btn btn-dark" data-toggle="modal" data-target="#exampleModal">
        Cerrar sesion
      </button>

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
          <a href="ir-a-suscribir" type="button" class="btn btn-danger">Suscribirse a un plan</a>
        </c:otherwise>

      </c:choose>
    </c:if>
    <div class="d-flex justify-content-around align-items-center col-lg-3">
      <!--<a href="home" type="button" class="btn btn-light">Inicio</a>-->
      <c:if test = "${id == null}">
        <a href="registro" type="button" class="btn btn-outline-primary">Crear cuenta</a>
        <a href="login" type="button" class="btn btn-primary">Iniciar sesion</a>
      </c:if>
    </div>
  </div>
</header>