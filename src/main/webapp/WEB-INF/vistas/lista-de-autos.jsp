<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: ezequiel
  Date: 7/10/21
  Time: 06:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Listado De Autos</title>
</head>
<body>
  <h1 style="text-align: center">Listado de Autos</h1>


<form:form action="mantenimiento" method="post" modelAttribute="datosMantenimiento">
  <div>
      Auto 1
      <input type="submit" value="Enviar a Mantenimiento" name="auto1">
  </div>
    <br>
    <br>
  <div>
      Auto 2
      <input type="submit" value="Enviar a Mantenimiento" name="auto2">
  </div>
    <br>
    <br>
  <div>
      Auto 3
      <input type="submit" value="Enviar a Mantenimiento" name="auuto3">
  </div>
    ${mensaje}
    </form:form>
</body>
</html>
