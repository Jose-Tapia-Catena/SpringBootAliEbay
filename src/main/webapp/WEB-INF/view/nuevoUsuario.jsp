<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.taw.aliebay.dto.UsuarioDTO" %><%--
  Created by IntelliJ IDEA.
  User: felip
  Date: 08/06/2022
  Time: 17:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Registrar usuario</title>
</head>
<body>
  <%
    String tipoUsuario  = (String)request.getAttribute("tipoUsuario");
  %>
  <h1>Datos usuario</h1>
  <%--@elvariable id="usuario" type="es.taw.aliebay.dto.UsuarioDTO"--%>
  <form:form method="post" action="/administrador/usuario/guardar/" modelAttribute="usuario">
    <table>
      <tr>
        <td>Nombre:</td>
        <td><form:input path="nombre" type="text"/></td>
      </tr>
      <tr>
        <td>Apellidos:</td>
        <td><form:input path="apellidos" type="text"/></td>
      </tr>
      <tr>
        <td>Domicilio:</td>
        <td><form:input path="domicilio" type="text" /></td>
      </tr>
      <tr>
        <td>Ciudad:</td>
        <td><form:input path="ciudadResidencia" /></td>
      </tr>
      <tr>
        <td>Edad:</td>
        <td><form:input path="edad" type="number" /></td>
      </tr>
      <tr>
        <td>Sexo:</td>
        <td>
          <form:radiobutton path="sexo" value="Hombre"/>Hombre
          <form:radiobutton path="sexo" value="Mujer"/> Mujer
        </td>
      </tr>

      <%
        if(tipoUsuario == null){
      %>
      <tr>
        <td>Tipo Usuario:</td>
        <td>
          <form:radiobutton  path="tipoUsuario" value="comprador"/>Comprador
          <form:radiobutton path="tipoUsuario" value="vendedor"/> Vendedor
        </td>
      </tr>
      <%
      }else{
      %>
      <form:hidden path="tipoUsuario" value="<%=tipoUsuario%>"/>
      <%
        }
      %>
      <tr>
        <td>Usuario:</td>
        <td><form:input path="userName" type="text"/></td>
      </tr>
      <tr>
        <td>Contraseña:</td>
        <td><form:input type="password" path="password"/></td>
      </tr>

      <tr>
        <td><form:button>Crear</form:button></td>
      </tr>
    </table>
  </form:form>
</body>
</html>
