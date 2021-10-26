<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<header class = "d-flex flex-row-reverse p-3">
  <div class = " d-flex flex-row-reverse col-md-6 col-lg-4 col-xl-4 justify-content">

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
              Esta seguro que quiere cerrar sesion?
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
              <a href="logout"><button type="button" class="btn btn-primary">Cerrar sesion</button></a>
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
    <c:if test = "${id == null}">
      <a href="login" type="button" class="btn btn-primary">Iniciar sesion</a>
      <a href="registro" type="button" class="btn btn-warning">Crear cuenta</a>
    </c:if>
    <a href="home" type="button" class="btn btn-success">Inicio</a>
  </div>
</header>